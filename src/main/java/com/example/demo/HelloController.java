package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
    @GetMapping("/hello")
    public String sayHello() {
        return message + " " + version;
    }

    @Value("${app.message}")
    private String message;

    @Value("${app.version}")
    private String version;
    
}
// Note: The @Value annotation is used to inject the value of 'app.message' from the properties file into the 'message' variable.