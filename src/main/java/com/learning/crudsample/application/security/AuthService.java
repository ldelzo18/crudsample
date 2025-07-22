package com.learning.crudsample.application.security;

import com.learning.crudsample.infrastructure.security.User;
import com.learning.crudsample.infrastructure.security.UserRepository;
import com.learning.crudsample.presentation.security.AuthRequestDTO;
import com.learning.crudsample.presentation.security.AuthResponseDTO;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JWTService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponseDTO login(AuthRequestDTO request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        String token = jwtService.generateToken(user);
        return new AuthResponseDTO(token, request.email());
    }
}
