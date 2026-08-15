package com.thameem.leettrack.service;

import com.thameem.leettrack.model.User;
import com.thameem.leettrack.repository.UserRepository;
import com.thameem.leettrack.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                        AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public void register(String username, String rawPassword) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already taken");
        }

        String hashedPassword = passwordEncoder.encode(rawPassword);
        User user = new User(username, hashedPassword);
        userRepository.save(user);
    }

    public String login(String username, String rawPassword) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, rawPassword)
        );

        return jwtUtil.generateToken(username);
    }
}