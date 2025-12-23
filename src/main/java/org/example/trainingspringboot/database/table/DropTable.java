package org.example.trainingspringboot.database.table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DropTable {
    public static void main(String[] args) {
        final String username = System.getenv("DB_USERNAME");
        final String url = System.getenv("DB_URL");
        final String password = System.getenv("DB_PASSWORD");

        try(Connection connection = DriverManager.getConnection(url,username,password);
                Statement statement = connection.createStatement()){
                String dropTableSql = "DROP table img";
                statement.execute(dropTableSql);
                System.out.print("Dropped table" +  dropTableSql);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
