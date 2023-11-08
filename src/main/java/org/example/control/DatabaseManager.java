package org.example.control;

import java.sql.*;

public class DatabaseManager {
    // Crear la tabla (para galdar), crear el insert, crear el select
    private static final String path = "C:\\Users\\ralme\\IdeaProjects\\OpenWeatherApp\\src\\main\\resources\\";

    public static Connection getConnection(String fileName) throws SQLException {
        String url = "jdbc:sqlite:" + path + fileName;
        return DriverManager.getConnection(url);
    }

    // Creación de la base de datos
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
