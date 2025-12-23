package org.example.trainingspringboot.repository;

import org.example.trainingspringboot.Entity.Image;
import org.example.trainingspringboot.database.connexion.GetConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ImageRepository {
    private final GetConnection dataSource;

    @Autowired
    public ImageRepository(GetConnection dataSource) {
        this.dataSource = dataSource;
    }

    public List<Image> getAllImages(){
     List<Image> images = new ArrayList<>();
     String query = "select * from img";
     try(Connection connection = dataSource.getConnection();
     PreparedStatement statement = connection.prepareStatement(query)){
         ResultSet resultSet = statement.executeQuery();
          while (resultSet.next()){
             Image image = new Image();
             image.setId(resultSet.getInt("id"));
             image.setName(resultSet.getString("name"));
             image.setUrl(resultSet.getString("url"));
             images.add(image);
         }
     }catch (SQLException e){
         System.out.println(e.getMessage());
     }
     return images;
    }

    public Image getImageById(int id){
        String sql = "SELECT * FROM img WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql)){
           statement.setInt(1,id);
           ResultSet resultSet = statement.executeQuery();

           if (resultSet.next()){
               Image image = new Image();
               image.setId(resultSet.getInt("id"));
               image.setName(resultSet.getString("name"));
               image.setUrl(resultSet.getString("url"));
               return image;
           }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return null;
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

    public Image updateImage(Image image){
        String sql = "update img set name=? , url=? where id=?";
        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, image.getName());
            statement.setString(2, image.getUrl());
            statement.setInt(3, image.getId());
            statement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        System.out.println("update image successfully");
        return image;
    }

    public void deleteImageById(int id){
        String sql = "delete from img where id = ?";
        try(Connection conn = dataSource.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setInt(1,id);
            statement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        System.out.println("delete image successfully");
    }
}
