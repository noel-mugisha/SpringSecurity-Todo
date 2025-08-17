package com.demo.springsec_todo_app.services;

import com.demo.springsec_todo_app.dto.AuthenticateUserRequest;
import com.demo.springsec_todo_app.dto.AuthenticationResponse;
import com.demo.springsec_todo_app.dto.RegisterUserRequest;
import com.demo.springsec_todo_app.enums.Role;
import com.demo.springsec_todo_app.models.User;
import com.demo.springsec_todo_app.repository.UserRepository;
import com.demo.springsec_todo_app.security.UserPrincipal;
import com.demo.springsec_todo_app.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse registerUser (RegisterUserRequest userDTO) {
        User user = User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        String token = jwtService.generateToken(new UserPrincipal(user));
        return AuthenticationResponse.builder()
                .message("User has been registered successfully!.." )
                .token(token)
                .build();
    }

    public AuthenticationResponse authenticateUser (AuthenticateUserRequest userDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword())
        );
        var user = userRepository.findByEmail(userDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("User with email: " + userDTO.getEmail() + "not found!.."));
        String token = jwtService.generateToken(new UserPrincipal(user));

        return AuthenticationResponse.builder()
                .message("Successful Login!...")
                .token(token)
                .build();
    }

}
