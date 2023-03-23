package com.example.authentication.controller;

import com.example.authentication.dto.UserDto;
import com.example.authentication.entity.Role;
import com.example.authentication.entity.User;
import com.example.authentication.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    @GetMapping
    public ResponseEntity<UserDto> getUser(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUser(userDetails.getUsername());
        return ResponseEntity.ok(user.mapToDto());
    }


    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@AuthenticationPrincipal UserDetails userDetails,
                                                  @PathVariable String username) {
        User user = userService.getUserByUsername(username);
        if(user != null) {
            return ResponseEntity.ok(user.mapToDto());
        }
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers(@AuthenticationPrincipal UserDetails userDetails) {
        if(userDetails.getAuthorities().contains(new SimpleGrantedAuthority(Role.ADMIN.name()))) {
            List<User> users = userService.getUsers();
            return ResponseEntity.ok(users.stream().map(User::mapToDto).toList());
        } else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

    }

    @PutMapping("/name")
    public ResponseEntity<Boolean> updateUserName(@AuthenticationPrincipal UserDetails userDetails,
                                           @RequestParam String name) throws URISyntaxException {

        return ResponseEntity.created(new URI("/name")).body(userService.updateName(userDetails.getUsername(), name));
    }

    @PutMapping("/avatar")
    public ResponseEntity<Boolean> updateUserAvatar(@AuthenticationPrincipal UserDetails userDetails,
                                                   @RequestParam MultipartFile file) throws URISyntaxException {

        return ResponseEntity.created(new URI("/avatar")).body(userService.addAvatarToUser(userDetails.getUsername(), file));
    }

    @PutMapping("/role")
    public ResponseEntity<Boolean> updateUserRole(@AuthenticationPrincipal UserDetails userDetails,
                                                  @RequestParam String username,
                                                  @RequestParam String roleName) throws URISyntaxException {
        if(userDetails.getAuthorities().contains(new SimpleGrantedAuthority(Role.ADMIN.name()))) {
            return ResponseEntity.created(new URI("/role")).body(userService.addRoleToUser(username, roleName));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
