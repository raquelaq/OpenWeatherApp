package org.example.control;

import org.example.model.Coordinates;
import org.example.model.Weather;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
                        "City TEXT, " +
                        "System_ts TEXT, " +
                        "dateTime TEXT, " +
                        "Temperature REAL, " +
                        "Rain REAL, " +
                        "WindSpeed REAL, " +
                        "Humidity REAL, " +
                        "Clouds REAL, " +
                        "Latitude REAL, " +
                        "Longitude REAL)",
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

        String insertSql = String.format("INSERT INTO \"%s\" (City, System_ts, dateTime, Temperature, Rain, WindSpeed, Humidity, Clouds, Latitude, Longitude) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", tablename);

        try (PreparedStatement preparedStatement = conn.prepareStatement(insertSql)) {
            conn.setAutoCommit(false);

            preparedStatement.setString(1, city);
            preparedStatement.setString(2, formatInstant(weather.getTs()));
            preparedStatement.setString(3, formatInstant(weather.getForecastTime()));
            preparedStatement.setDouble(4, weather.getTemperature());
            preparedStatement.setDouble(5, weather.getRain());
            preparedStatement.setDouble(6, weather.getWindSpeed());
            preparedStatement.setDouble(7, weather.getHumidity());
            preparedStatement.setDouble(8, weather.getClouds());
            preparedStatement.setDouble(9, latitude);
            preparedStatement.setDouble(10, longitude);
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
                String city = resultSet.getString("City");
                Timestamp ts = resultSet.getTimestamp("System_ts");
                Instant forecastTime = resultSet.getTimestamp("dateTime").toInstant();
                double temperature = resultSet.getDouble("Temperature");
                double rain = resultSet.getDouble("Rain");
                double windSpeed = resultSet.getDouble("WindSpeed");
                double humidity = resultSet.getDouble("Humidity");
                double clouds = resultSet.getDouble("Clouds");
                double latitude = resultSet.getDouble("Latitude");
                double longitude = resultSet.getDouble("Longitude");

                Coordinates coordinates = new Coordinates(city, latitude, longitude);
                Weather weather = new Weather(ts.toInstant(), forecastTime, temperature, rain, humidity, clouds, windSpeed, coordinates);
                weatherList.add(weather);
            }
        } catch (SQLException e) {
            System.out.println("Error executing select query: " + e.getMessage());
        }
        return weatherList;
    }

    private static String formatInstant(Instant instant) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return localDateTime.format(formatter);
    }

}
