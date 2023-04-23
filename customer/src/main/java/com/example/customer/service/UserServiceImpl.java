package com.example.customer.service;

import com.example.customer.entity.User;
import com.example.customer.repo.UserRepo;
import com.example.customer.util.ImageValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;

    @Override
    public User addUser(User user) {
        return userRepo.save(user);
    }
    @Override
    public User findUserByEmail(String email) {
        Optional<User> optional = userRepo.findUserByEmail(email);
        if(optional.isEmpty()) return null;
        else return optional.get();
    }
    @Override
    public User findUserById(UUID uid) {
        Optional<User> optional = userRepo.findById(uid);
        if(optional.isEmpty()) return null;
        else return optional.get();
    }
    @Override
    public void updateUser(User user) {
        Optional<User> optional = userRepo.findById(user.getId());
        if(optional.isPresent()) {
            userRepo.save(user);
        }
    }
    @Override
    public boolean addAvatarToUser(UUID userId, MultipartFile file) {
        if(ImageValidator.IsValidAvatar(file)) {
            try {
                byte[] avatar = file.getInputStream().readAllBytes();
                User user = userRepo.findById(userId).get();
                user.setAvatar(avatar);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
