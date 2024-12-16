package com.jwt.jwt_training.services;

import com.jwt.jwt_training.models.User;
import com.jwt.jwt_training.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            JwtService jwtService,
            UserRepository userRepository,
            AuthenticationManager authenticationManager) {

        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    public String login(User user) {
        System.out.println("Attempting to authenticate user: " + user.getEmail());
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getEmail(),
                            user.getPassword()
                    )
            );
            System.out.println("Authentication successful: " + authentication);

            if (authentication.isAuthenticated()) {
                return jwtService.generateToken(user.getEmail());
            }
        } catch (Exception e) {
            System.err.println("Authentication failed for user " + user.getEmail() + ": " + e.getMessage());
        }
        return "failure";
    }


    public User register(User user) {

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("User already exists");
        }

        User userToSave = new User();
        userToSave.setEmail(user.getEmail());
        userToSave.setPassword(passwordEncoder.encode(user.getPassword()));
        userToSave.setRole(user.getRole());

        return userRepository.save(userToSave);
    }

}
