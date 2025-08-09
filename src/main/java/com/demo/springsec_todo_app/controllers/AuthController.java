package com.demo.springsec_todo_app.controllers;

import com.demo.springsec_todo_app.dto.AuthenticateUserDTO;
import com.demo.springsec_todo_app.dto.AuthenticationResponse;
import com.demo.springsec_todo_app.dto.RegisterUserDTO;
import com.demo.springsec_todo_app.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register (@RequestBody RegisterUserDTO userDTO) {
        return ResponseEntity.ok(authService.registerUser(userDTO));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login (@RequestBody AuthenticateUserDTO userDTO) {
        return ResponseEntity.ok(authService.authenticateUser(userDTO));
    }
}
