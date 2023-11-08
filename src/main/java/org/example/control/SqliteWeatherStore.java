package org.example.control;

import java.sql.*;

public class SqliteWeatherStore {
    public static void createTable(Connection conn) {
        String granCanariaTable = "CREATE TABLE IF NOT EXISTS GranCanaria (" +
                "city TEXT, " +
                "ts TIMESTAMP, " +
                "temperature REAL, " +
                "rain REAL, " +
                "windSpeed REAL, " +
                "latitude REAL, " +
                "longitude REAL)";

        try (Statement statement = conn.createStatement()) {
            statement.execute(granCanariaTable);
            System.out.println("Table has been created");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    public static void insertWeatherData(Connection conn, String city, Timestamp ts, double temperature, double rain, double windSpeed, double latitude, double longitude) {
        String insertSql = "INSERT INTO GranCanaria (city, ts, temperature, rain, windSpeed, latitude, longitude) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(insertSql)) {
            preparedStatement.setString(1, city);
            preparedStatement.setTimestamp(2, ts);
            preparedStatement.setDouble(3, temperature);
            preparedStatement.setDouble(4, rain);
            preparedStatement.setDouble(5, windSpeed);
            preparedStatement.setDouble(6, latitude);
            preparedStatement.setDouble(7, longitude);
            preparedStatement.executeUpdate();
            System.out.println("Data inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Error inserting data: " + e.getMessage());
        }
    }
}
