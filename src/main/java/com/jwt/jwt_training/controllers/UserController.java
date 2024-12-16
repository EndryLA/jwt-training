package com.jwt.jwt_training.controllers;

import com.jwt.jwt_training.models.User;
import com.jwt.jwt_training.services.AuthenticationService;
import com.jwt.jwt_training.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    public UserController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }


    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/register")
    public User getUsers(@RequestBody User user) {
        return authenticationService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return authenticationService.login(user);
    }

}
