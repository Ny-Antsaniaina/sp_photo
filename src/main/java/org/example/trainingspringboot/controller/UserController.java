package org.example.trainingspringboot.controller;

import org.example.trainingspringboot.Entity.User;
import org.example.trainingspringboot.Entity.UserResponse;
import org.example.trainingspringboot.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add/user")
    public UserResponse saveUser(@RequestBody User user) {
        User saved = userService.add(user);

        return new UserResponse(
                saved.getId(),
                saved.getUsername(),
                saved.getName(),
                saved.getCreatedAt()
        );
    }
}
