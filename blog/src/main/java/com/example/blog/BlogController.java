package com.example.blog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/blog")
public class BlogController {

    @GetMapping
    public String getBlog() {
        return "blog";
    }
}
