package com.example.authentication.service;

import com.example.authentication.controller.request.AuthenticationRequest;
import com.example.authentication.controller.request.RegisterRequest;
import com.example.authentication.controller.response.AuthenticationResponse;
import com.example.authentication.entity.Role;
import com.example.authentication.entity.User;
import com.example.authentication.repo.UserRepo;
import com.example.authentication.vo.UserModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.HashMap;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AuthenticationService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .name(request.getName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();
        if(userRepo.findUserByUsername(user.getUsername()).isEmpty()){
            userRepo.save(user);
            HashMap obj = WebClient.builder()
                    .baseUrl("http://localhost:8090/api/v1/customer")
                    .build()
                    .post()
                    .uri("/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Mono.just(UserModel.builder().name(request.getName()).email(request.getUsername()).build()), UserModel.class)
                    .retrieve()
                    .bodyToMono(HashMap.class)
                    .block();

            log.info(obj.get("id").toString());
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .uid(UUID.fromString(obj.get("id").toString()))
                    .build();
        } else {
            return AuthenticationResponse.builder()
                    .errorMsg("User already exists")
                    .build();
        }
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepo.findUserByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        log.info("The username is " + request.getUsername());
        HashMap obj = WebClient.builder()
                .baseUrl("http://localhost:8090/api/v1/customer")
                .build()
                .get()
                .uri("/users/email?email=" + request.getUsername())
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();

        log.info(obj.get("id").toString());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .uid(UUID.fromString(obj.get("id").toString()))
                .build();
    }

    public void logout(AuthenticationRequest request) {

    }
}
