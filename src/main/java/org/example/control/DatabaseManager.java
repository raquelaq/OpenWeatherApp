package org.example.control;

import java.sql.*;


public class DatabaseManager {
    private static final String path = "INSERT YOUR RESOURCES PATH HERE";

    public Connection getConnection(String fileName) throws SQLException {
        String url = "jdbc:sqlite:" + path + fileName;
        return DriverManager.getConnection(url);
    }

    public void createDatabase(String fileName) {
        String url = "jdbc:sqlite:" + path + fileName;
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
