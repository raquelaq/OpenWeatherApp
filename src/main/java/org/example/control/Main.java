package org.example.control;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.example.model.Weather;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        OpenWeatherMapProvider openWeatherMapProvider = new OpenWeatherMapProvider();
        List<Weather> weatherList = openWeatherMapProvider.buildWeather();

        try (Connection conn = DatabaseManager.getConnection("database.db")) {
            SqliteWeatherStore.createTable(conn);

            for (Weather weather : weatherList) {
                SqliteWeatherStore.insertWeatherData(conn, "Gran Canaria", weather.getTs(), weather.getTemperature(),
                        weather.getRain(), weather.getWindSpeed(), weather.getCoordinates().getLatitude(), weather.getCoordinates().getLongitude());
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}