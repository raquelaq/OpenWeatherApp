package org.example.control;

import org.example.model.Coordinates;
import org.example.model.Weather;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class SqliteWeatherStore {
    private static void createTable(Statement statement, String tableName) throws SQLException {
        String createTableSql = String.format(
                "CREATE TABLE IF NOT EXISTS \"%s\" (" +
                        "city TEXT, " +
                        "ts TEXT, " +
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
            conn.setAutoCommit(false);

            preparedStatement.setString(1, city);
            preparedStatement.setString(2, weather.getTs().toString());
            preparedStatement.setDouble(3, weather.getTemperature());
            preparedStatement.setDouble(4, weather.getRain());
            preparedStatement.setDouble(5, weather.getWindSpeed());
            preparedStatement.setDouble(6, weather.getHumidity());
            preparedStatement.setDouble(7, weather.getClouds());
            preparedStatement.setDouble(8, latitude);
            preparedStatement.setDouble(9, longitude);
            preparedStatement.executeUpdate();

            conn.commit();

            System.out.println("Data inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Error inserting data: " + e.getMessage());
        }
    }

    public static List<Weather> selectWeatherData(Connection conn, String tablename) {
        List<Weather> weatherList = new ArrayList<>();

        String selectSql = String.format("SELECT * FROM \"%s\"", tablename);

        try (Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(selectSql)) {
            while (resultSet.next()) {
                String city = resultSet.getString("city");
                Timestamp ts = resultSet.getTimestamp("ts");
                double temperature = resultSet.getDouble("temperature");
                double rain = resultSet.getDouble("rain");
                double windSpeed = resultSet.getDouble("windSpeed");
                double humidity = resultSet.getDouble("humidity");
                double clouds = resultSet.getDouble("clouds");
                double latitude = resultSet.getDouble("latitude");
                double longitude = resultSet.getDouble("longitude");

                Coordinates coordinates = new Coordinates(city, latitude, longitude);
                Weather weather = new Weather(ts.toInstant(), temperature, rain, humidity, clouds, windSpeed, coordinates);
                weatherList.add(weather);
            }
        } catch (SQLException e) {
            System.out.println("Error executing select query: " + e.getMessage());
        }
        return weatherList;
    }

}
