package org.example.trainingspringboot.database.connexion;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class GetConnection {
   private final String username = System.getenv("DB_USERNAME");
   private final String url = System.getenv("DB_URL");
   private final String password = System.getenv("DB_PASSWORD");

    public Connection getConnection() {
       try {
           return DriverManager.getConnection(url, username, password);
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }

}
