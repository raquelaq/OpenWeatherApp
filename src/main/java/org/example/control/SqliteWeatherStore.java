package org.example.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class SqliteWeatherStore {
    public static void createTable(Connection conn) {
        String granCanariaTable = "CREATE TABLE IF NOT EXISTS GranCanaria (" +
                "city TEXT, " +
                "getTs TIMESTAMP, " +
                "temperature REAL, " +
                "rain REAL, " +
                "windSpeed REAL, " +
                "lat REAL, " +
                "long REAL)";

        try (Statement statement = conn.createStatement()) {
            statement.execute(granCanariaTable);
            System.out.println("Table has been created");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    public static void insertWeatherData(Connection conn, String granCanaria, Timestamp ts, double temperature, double rain, double windSpeed, double latitude, double longitude) {
    }
}
