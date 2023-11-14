package org.example.control;

import org.example.model.Weather;
import org.example.model.Coordinates;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class WeatherStore {
    private final OpenWeatherMapProvider openWeatherMapProvider;
    private final Connection connection;

    public WeatherStore(Connection connection) {
        this.openWeatherMapProvider = new OpenWeatherMapProvider();
        this.connection = connection;
    }

    public void storeWeatherData() throws IOException {
        Map<String, Coordinates> coordinatesMap = openWeatherMapProvider.createMap();

        try (Connection conn = DatabaseManager.getConnection("database.db")) {
            SqliteWeatherStore.createTables(conn, coordinatesMap);

            for (Map.Entry<String, Coordinates> entry : coordinatesMap.entrySet()) {
                String tablename = entry.getKey();
                String city = entry.getValue().getName();
                Coordinates coordinates = entry.getValue();

                List<Weather> weatherList = openWeatherMapProvider.buildWeather(coordinates);
                for (Weather weather : weatherList) {
                    SqliteWeatherStore.insertWeatherData(conn, tablename, city, weather, coordinates.getLatitude(), coordinates.getLongitude());
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

