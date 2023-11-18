package org.example.control;

import org.example.model.Weather;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class WeatherSelect {

    public static List<Weather> selectWeatherData(String tableName) {

        DatabaseManager databaseManager = new DatabaseManager();

        try (Connection conn = databaseManager.getConnection("database.db")) {
            return SqliteWeatherStore.selectWeatherData(tableName);
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
            return null;
        }
    }
}
