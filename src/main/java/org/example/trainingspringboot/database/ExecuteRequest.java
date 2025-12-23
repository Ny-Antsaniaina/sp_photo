package org.example.trainingspringboot.database;

import org.example.trainingspringboot.database.table.CreateTables;

public class ExecuteRequest {
    public static void main(String[] args) {
        CreateTables createTables = new CreateTables();
        createTables.createManyTables();
    }
}
