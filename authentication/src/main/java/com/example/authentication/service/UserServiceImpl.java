package com.example.authentication.service;
import com.example.authentication.entity.Result;
import com.example.authentication.entity.Role;
import com.example.authentication.entity.User;
import com.example.authentication.repo.UserRepo;
import com.example.authentication.util.ImageValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public User saveUser(User user) {
        log.info("Saving user {} to the database", user.getUsername());
        return userRepo.save(user);
    }

    @Override
    public boolean addRoleToUser(String username, String roleName) {
        Optional<User> optional = userRepo.findUserByUsername(username);
        if(optional.isPresent()) {
            User user = optional.get();
            if(user.getAuthorities().contains(Role.ADMIN)) return false;
            else {
                user.setRole(Role.valueOf(roleName));
                return true;
            }
        }
        else return false;
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}", username);
        User user = userRepo.findUserByUsername(username).get();
        return User.builder().username(user.getUsername())
                .name(user.getName())
                .role(user.getRole())
                .avatar(user.getAvatar())
                .build();
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> optional = userRepo.findUserByUsername(username);
        if(optional.isPresent()) return optional.get();
        else return null;
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepo.findAll();
    }

    @Override
    public boolean updateName(String username, String name) {
        Optional<User> optional = userRepo.findUserByUsername(username);
        if(optional.isPresent()) {
            optional.get().setName(name);
            return true;
        }
        else return false;
    }

    @Override
    public boolean addAvatarToUser(String username, MultipartFile file) {
        if(ImageValidator.IsValidAvatar(file)) {
            try {
                byte[] avatar = file.getInputStream().readAllBytes();
                User user = userRepo.findUserByUsername(username).get();
                user.setAvatar(avatar);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
