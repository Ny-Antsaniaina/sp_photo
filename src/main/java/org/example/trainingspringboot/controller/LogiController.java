package org.example.trainingspringboot.controller;

import org.example.trainingspringboot.Entity.UserLogin;
import org.example.trainingspringboot.service.UserService;
import org.example.trainingspringboot.service.UserServiceLogin;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LogiController {
    private final UserServiceLogin  userServiceLogin;
    public LogiController(UserServiceLogin userServiceLogin) {
        this.userServiceLogin = userServiceLogin;
    }
    @PostMapping("/login")
    public UserLogin getUserLogin(@RequestParam String username, @RequestParam String password) {
        return userServiceLogin.loginUser(username, password);
    }
}
