package com.example.springbootrestwhitelabel.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/home")
public class HomeRestController {

    @GetMapping
    public String greeting() {
        return "Hello, Spring Boot Developer";
    }
}
