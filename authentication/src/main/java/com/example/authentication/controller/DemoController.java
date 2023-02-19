package com.example.authentication.controller;

import com.example.authentication.entity.User;
import com.example.authentication.service.EmailService;
import com.example.authentication.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/demo")
@CrossOrigin
public class DemoController {

    private final EmailService emailService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<String> sayHello(String username) {
        List<User> userList = userService.getUsers();
        return ResponseEntity.ok(userList.toString());
    }

    @GetMapping("email")
    public String sendEmail(@RequestBody String email) {
        log.info("Start to send email...");
//        emailService.send(email, "yuruojie777@gmail.com");
        return "finish";
    }
}
