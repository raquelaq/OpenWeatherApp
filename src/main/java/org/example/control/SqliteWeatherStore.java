package org.example.control;

import org.example.model.Coordinates;
import org.example.model.Weather;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.sql.Timestamp;

public class SqliteWeatherStore {
    private static void createTable(Statement statement, String tableName) throws SQLException {
        String createTableSql = String.format(
                "CREATE TABLE IF NOT EXISTS \"%s\" (" +
                        "city TEXT, " +
                        "ts TIMESTAMP, " +
                        "temperature REAL, " +
                        "rain REAL, " +
                        "windSpeed REAL, " +
                        "humidity REAL, " +
                        "clouds REAL, " +
                        "latitude REAL, " +
                        "longitude REAL)",
                tableName
        );

        statement.execute(createTableSql);
    }

    public static void createTables(Connection conn, Map<String, Coordinates> coordinatesMap) {
        try (Statement statement = conn.createStatement()) {
            for (Map.Entry<String, Coordinates> entry : coordinatesMap.entrySet()) {
                createTable(statement, entry.getKey());
            }
            System.out.println("Tables have been created");
        } catch (SQLException e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }


    public static void insertWeatherData(Connection conn, String tablename, String city, Weather weather, double latitude, double longitude) {
        String insertSql = String.format("INSERT INTO \"%s\" (city, ts, temperature, rain, windSpeed, humidity, clouds, latitude, longitude) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", tablename);

        try (PreparedStatement preparedStatement = conn.prepareStatement(insertSql)) {
            preparedStatement.setString(1, city);
            preparedStatement.setTimestamp(2, weather.getTs());
            preparedStatement.setDouble(3, weather.getTemperature());
            preparedStatement.setDouble(4, weather.getRain());
            preparedStatement.setDouble(5, weather.getWindSpeed());
            preparedStatement.setDouble(6, weather.getHumidity());
            preparedStatement.setDouble(7, weather.getClouds());
            preparedStatement.setDouble(8, latitude);
            preparedStatement.setDouble(9, longitude);
            preparedStatement.executeUpdate();
            System.out.println("Data inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Error inserting data: " + e.getMessage());
        }
    }
}
