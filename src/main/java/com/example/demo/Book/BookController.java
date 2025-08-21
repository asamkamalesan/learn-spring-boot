package com.example.demo.Book;

import java.util.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/books")
public class BookController {
    private List<Book> books = new ArrayList<>();

    public BookController() {
        books.add(new Book(1, "1984", "George Orwell"));
        books.add(new Book(2, "To Kill a Mockingbird", "Harper Lee"));
        books.add(new Book(3, "The Great Gatsby", "F. Scott Fitzgerald"));
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return books;
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    public List<Book> addBook(@RequestBody List<Book> book) {
        if (book == null || book.isEmpty()) {
            return null;
        }

        for (Book b: book) {
            if (b.getId() <= 0 || b.getTitle() == null || b.getAuthor() == null) {
                return null; // Invalid book data
            }

            books.add(b);
        }
        return books;
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
        for (Book b: books) {
            if (b.getId() == id) {
                b.setTitle((updatedBook.getTitle() != null ? updatedBook.getTitle() : b.getTitle()));
                b.setAuthor(updatedBook.getAuthor() != null ? updatedBook.getAuthor() : b.getAuthor());
                return b;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public List<Book> deleteBook(@PathVariable int id) {
        books.removeIf(b -> b.getId() == id);
        return books;
    }
    
}
