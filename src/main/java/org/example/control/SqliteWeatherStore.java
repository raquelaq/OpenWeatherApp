package org.example.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteWeatherStore {
    public static void createTable(Connection conn) {
        String granCanariaTable = "CREATE TABLE IF NOT EXISTS Gran_Canaria (" +
                "city TEXT, " +
                "instant TIMESTAMP, " +
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
}
