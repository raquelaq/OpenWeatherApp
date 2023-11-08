package org.example.control;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseDELETE {
    private static final String path = "C:\\Users\\ralme\\IdeaProjects\\OpenWeatherApp\\src\\main\\resources\\";
    public static void createDatabase(String path, String fileName) {
        String url = "jdbc:sqlite:" + path + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        createDatabase(path, "database.db");
    }
}
