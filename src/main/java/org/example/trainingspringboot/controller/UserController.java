package org.example.trainingspringboot.controller;

import org.example.trainingspringboot.Entity.User;
import org.example.trainingspringboot.Entity.UserResponse;
import org.example.trainingspringboot.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @PutMapping("/edit/{id}")
    public UserResponse updateUser(@RequestBody User user , @PathVariable int id) {
        User update = userService.editUser(id,user);
        return new UserResponse(
                update.getId(),
                update.getUsername(),
                update.getName(),
                update.getCreatedAt()
        );
    }

    @DeleteMapping("/delete/user/{id}")
    public void delete(@PathVariable int id) {
        userService.deleteUser(id);
    }

    @GetMapping("/users")
    public List<UserResponse> getAllUsers(){
        List<User> saved = userService.getAllUsers();

        List<UserResponse> users = new ArrayList<>();
        for (User user : saved) {
            users.add(
                    new UserResponse(
                            user.getId(),
                            user.getUsername(),
                            user.getName(),
                            user.getCreatedAt()
                    )
            );
        }
        return users;
    }
}
