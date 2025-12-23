package org.example.trainingspringboot.repository;

import org.example.trainingspringboot.Entity.User;
import org.example.trainingspringboot.database.connexion.GetConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class UserRepository {
    private final GetConnection dataSource;

    @Autowired
    public UserRepository(GetConnection dataSource) {
        this.dataSource = dataSource;
    }

    public boolean getUserByUsername(String username) {
        String sql = "SELECT 1 FROM users WHERE username = ?";
        try(Connection conn = dataSource.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            return  resultSet.next();
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setCreatedAt(rs.getDate("created_at"));
                return user;
            }
            throw new RuntimeException("User not found");
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public User addUser(User user){
        String sql = "insert into users(username , name , password) values(?,?,?)";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1,user.getUsername());
            statement.setString(2,user.getName());
            statement.setString(3,user.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()){
                user.setId(resultSet.getInt(1));
            }

            int id = resultSet.getInt(1);
            return getUserById(id);

        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return user;
    }
}
