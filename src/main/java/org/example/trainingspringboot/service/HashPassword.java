package org.example.trainingspringboot.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class HashPassword {
     private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
     
     public String hashPassword(String password){
         return encoder.encode(password);
     }
     public boolean checkPassword(String password, String hashedPassword){
         return encoder.matches(password,hashedPassword);
     }
}
