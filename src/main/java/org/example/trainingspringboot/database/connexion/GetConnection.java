package org.example.trainingspringboot.database.connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnection {
   private final String username = System.getenv("DB_USERNAME");
   private final String url = System.getenv("DB_URL");
   private final String password = System.getenv("DB_PASSWORD");

   private Connection connection;

    public Connection getConnection() {
       try {
          connection = DriverManager.getConnection(url, username, password);
          return connection;
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }

}
