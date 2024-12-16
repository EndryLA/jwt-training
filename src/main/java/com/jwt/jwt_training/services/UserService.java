package com.jwt.jwt_training.services;

import com.jwt.jwt_training.models.User;
import com.jwt.jwt_training.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public List<User> getUsers() {

        return userRepository.findAll();
    }

    public Optional<User> getUser(int userId) {

        return userRepository.findById(userId);
    }

    public User createUser(User user) {

        return userRepository.save(user);
    }

    public User updateUser(User user, int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {

            User userToSave = optionalUser.get();

            userToSave.setEmail(user.getEmail());
            userToSave.setPassword(user.getPassword());
            userToSave.setRole(user.getRole());

            return userRepository.save(userToSave);
        } else {
            throw new RuntimeException("User with an id of " + userId + " was not found");
        }
    }

    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }





}
