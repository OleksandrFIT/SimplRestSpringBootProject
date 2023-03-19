package com.example.sobesresttask.controller;

import com.example.sobesresttask.model.User;
import com.example.sobesresttask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/findUserById/{id}")
    Optional<User> findUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
