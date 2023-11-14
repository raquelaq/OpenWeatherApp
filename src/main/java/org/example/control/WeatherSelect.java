package org.example.control;

import org.example.model.Weather;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class WeatherSelect {

    public static List<Weather> selectWeatherData(String tableName) {
        try (Connection conn = DatabaseManager.getConnection("database.db")) {
            return SqliteWeatherStore.selectWeatherData(conn, tableName);
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
            return null;
        }
    }
}
