package org.example.trainingspringboot.service;

import org.example.trainingspringboot.Entity.User;
import org.example.trainingspringboot.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final HashPassword hashPassword;

    public UserService(UserRepository userRepository, HashPassword hashPassword) {
        this.userRepository = userRepository;
        this.hashPassword = hashPassword;
    }

    public User add(User user){
        String verifyUsername = user.getUsername();
        boolean exist = userRepository.getUserByUsername(verifyUsername);
        if(exist){
            System.out.println("username is exist");
            throw new RuntimeException("username is exist");
        }else {
            String password = user.getPassword();
            String hashPass = hashPassword.hashPassword(password);
            user.setPassword(hashPass);

            return userRepository.addUser(user);
        }
    }
}
