package com.example.customer.service;

import com.example.customer.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface UserService {
    User addUser(User user);
    User findUserById(UUID uid);
    User findUserByEmail(String email);
    void updateUser(User user);
    boolean addAvatarToUser(UUID userId, MultipartFile file);
}
