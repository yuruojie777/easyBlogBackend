package com.example.authentication.service;

import com.example.authentication.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    boolean addRoleToUser(String username, String roleName);
    User getUser(String username);
    User getUserByUsername(String username);
    List<User> getUsers();
    boolean addAvatarToUser(String username, MultipartFile file);
    boolean updateName(String username, String name);
}
