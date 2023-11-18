package org.example.control;

import org.example.model.Weather;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class WeatherSelect {
    public List<Weather> selectWeatherData(String tableName) {
        DatabaseManager databaseManager = new DatabaseManager();
        SqliteWeatherStore sqliteWeatherStore = new SqliteWeatherStore();
        try (Connection conn = databaseManager.getConnection("database.db")) {
            return sqliteWeatherStore.selectWeatherData(tableName);
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
            return null;
        }
    }
}
