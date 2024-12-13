package com.jwt.jwt_training.services;

import com.jwt.jwt_training.models.User;
import com.jwt.jwt_training.models.UserPrincipal;
import com.jwt.jwt_training.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user != null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new UserPrincipal(user);

    }

}
