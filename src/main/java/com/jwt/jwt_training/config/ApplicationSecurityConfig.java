package com.jwt.jwt_training.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public class ApplicationSecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(request -> request
                    .requestMatchers("/api/auth/**").permitAll()
                    .anyRequest().authenticated()

            );


        return http.build();

    }


}
