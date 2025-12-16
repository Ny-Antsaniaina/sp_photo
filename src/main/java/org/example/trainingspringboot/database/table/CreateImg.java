package org.example.trainingspringboot.database.table;

import org.example.trainingspringboot.database.connexion.GetConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateImg {
    private final String username = System.getenv("DB_USERNAME");
    private final String url = System.getenv("DB_URL");
    private final String password = System.getenv("DB_PASSWORD");

    public void createImg(){
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement statement = conn.createStatement()){
            String img = "create table img (" +
                    "id serial primary key ," +
                    "name varchar , " +
                    "url varchar )";
            statementSafe(statement , img , "image");
        }catch (SQLException e){
            throw new RuntimeException("Error creating table" + e.getMessage());
        }

    }

    public void statementSafe(Statement statement , String sql , String name){
        try {
            statement.execute(sql);
            System.out.println("create table " + name + " success");
        }catch (SQLException e){
            if (e.getMessage().contains("already exists")) {
                System.out.println("table " + name + " already exists");
            }else {
                throw new RuntimeException("Error creating table" + e.getMessage());
            }
        }
    }
}
