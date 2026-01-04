package org.example.trainingspringboot.repository;

import org.example.trainingspringboot.Entity.User;
import org.example.trainingspringboot.Entity.UserLogin;
import org.example.trainingspringboot.database.connexion.GetConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class LoginRepository {

    private GetConnection datasource;
    private UserRepository userRepository;
    public UserLogin Login(String username, String password) {
        boolean exist = userRepository.getUserByUsername(username);
        if (!exist) {
            System.out.println("User not found");
            return null;
        }else {
            String query = "select username , password from users where username=? and password=? ";
            try (Connection connection = datasource.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    UserLogin userLogin = new UserLogin();
                    userLogin.setUsername(resultSet.getString("username"));
                    userLogin.setPassword(resultSet.getString("password"));
                    return userLogin;
                }
            }catch (SQLException e){
                throw new RuntimeException(e.getMessage());
            }
        }
        return null;
    }
}
