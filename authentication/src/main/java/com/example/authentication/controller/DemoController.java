package com.example.authentication.controller;

import com.example.authentication.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/demo")
@CrossOrigin
public class DemoController {

    private final UserService userService;

    @GetMapping
    public String get() {
        return "Get";
    }

    @PostMapping
    public String post() {
        return "Post";
    }

}
