package org.example.control;

import org.example.model.Weather;
import org.example.model.Coordinates;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;

public class WeatherStore {
    private final OpenWeatherMapProvider openWeatherMapProvider;

    public WeatherStore() {
        this.openWeatherMapProvider = new OpenWeatherMapProvider();
    }

    public void storeWeatherData() throws IOException {
        Map<String, Coordinates> coordinatesMap = openWeatherMapProvider.createMap();
        DatabaseManager databaseManager = new DatabaseManager();
        SqliteWeatherStore sqliteWeatherStore = new SqliteWeatherStore();

        try (Connection conn = databaseManager.getConnection("database.db")) {
            SqliteWeatherStore.createTables(conn, coordinatesMap);

            for (Map.Entry<String, Coordinates> entry : coordinatesMap.entrySet()) {
                String tablename = entry.getKey();
                String city = entry.getValue().getName();
                Coordinates coordinates = entry.getValue();

                List<Weather> weatherList = openWeatherMapProvider.buildWeather(coordinates);
                for (Weather weather : weatherList) {
                    sqliteWeatherStore.update(tablename, city, weather, coordinates.getLatitude(), coordinates.getLongitude());
                    sqliteWeatherStore.insertWeatherData(tablename, city, weather, coordinates.getLatitude(), coordinates.getLongitude());
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

