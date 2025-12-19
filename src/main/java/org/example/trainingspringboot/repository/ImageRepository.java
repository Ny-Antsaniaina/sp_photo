package org.example.trainingspringboot.repository;

import org.example.trainingspringboot.Entity.Image;
import org.example.trainingspringboot.database.connexion.GetConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class ImageRepository {
    private final GetConnection dataSource;

    @Autowired
    public ImageRepository(GetConnection dataSource) {
        this.dataSource = dataSource;
    }

    public Image createImage(Image image){
        String sql = "insert into img (name,url) values (?,?)";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1 , image.getName());
            statement.setString(2 , image.getUrl());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                image.setId(rs.getInt(1));
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return image;
    }
}
