package com.example.authentication.controller;

import com.example.authentication.controller.request.AuthenticationRequest;
import com.example.authentication.controller.request.RegisterRequest;
import com.example.authentication.controller.response.AuthenticationResponse;
import com.example.authentication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) throws URISyntaxException {
        AuthenticationResponse response = service.register(request);
        if(response.getErrorMsg() == null) {
            return ResponseEntity.created(new URI("/register")).body(response);
        }

        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
