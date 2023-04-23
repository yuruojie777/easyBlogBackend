package com.example.customer.controller;

import com.example.customer.entity.User;
import com.example.customer.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping
    public User getUser(@RequestParam UUID uid) {
        return userService.findUserById(uid);
    }
    @GetMapping("/email")
    public User getUserByEmail(@RequestParam String email) {
        return userService.findUserByEmail(email);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        log.info(user.toString());
        return userService.addUser(user);
    }

    @PutMapping
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    @PutMapping("/{id}/avatar")
    public ResponseEntity<Boolean> updateUserAvatar(
            @PathVariable String id,
            @RequestParam MultipartFile file) throws URISyntaxException {

        return ResponseEntity.created(new URI(String.format("/%s/avatar",id)))
                .body(userService.addAvatarToUser(UUID.fromString(id), file));
    }
}
