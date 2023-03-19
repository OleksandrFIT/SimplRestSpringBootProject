package com.example.sobesresttask.service;

import com.example.sobesresttask.model.User;

import java.util.Optional;

public interface UserService {
        User createUser(User user);
        Optional<User> getUserById(Long id);
}
