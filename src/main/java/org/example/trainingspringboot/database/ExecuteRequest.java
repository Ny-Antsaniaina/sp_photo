package org.example.trainingspringboot.database;

import org.example.trainingspringboot.database.table.CreateImg;

public class ExecuteRequest {
    public static void main(String[] args) {
        CreateImg createImg = new CreateImg();
        createImg.createImg();
    }
}
