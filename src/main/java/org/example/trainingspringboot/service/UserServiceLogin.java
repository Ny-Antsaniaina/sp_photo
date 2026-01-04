package org.example.trainingspringboot.service;

import org.apache.el.parser.Token;
import org.example.trainingspringboot.Entity.UserLogin;
import org.example.trainingspringboot.repository.LoginRepository;
import org.example.trainingspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceLogin {
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private HashPassword hashPassword;
    public UserLogin  loginUser(String username, String password) {
        loginRepository.Login(username, password);
        String hashedPassword = hashPassword.hashPassword(password);
        boolean decode = hashPassword.checkPassword(password,hashedPassword);
        if(!decode){
            System.out.println("Wrong password");
        }
        UserLogin userLogin = new UserLogin();
        userLogin.setUsername(username);
        userLogin.setPassword(hashedPassword);
        return userLogin;
    }
}
