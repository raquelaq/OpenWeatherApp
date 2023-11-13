package org.example.control;

import org.example.model.Weather;
import org.example.model.Coordinates;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;

public class Main {
    public static void main(String[] args) throws IOException {
        OpenWeatherMapProvider openWeatherMapProvider = new OpenWeatherMapProvider();
        Map<String, Coordinates> coordinatesMap = OpenWeatherMapProvider.createMap();

        try (Connection conn = DatabaseManager.getConnection("database.db")) {
            // Crear tablas para cada coordenada en el diccionario
            SqliteWeatherStore.createTables(conn, coordinatesMap);

            for (Map.Entry<String, Coordinates> entry : coordinatesMap.entrySet()) {
                String tablename = entry.getKey();
                String city = entry.getValue().getName();
                Coordinates coordinates = entry.getValue();

                // Obtener datos meteorológicos para cada ciudad
                List<Weather> weatherList = openWeatherMapProvider.buildWeather(coordinates);

                // Insertar datos meteorológicos en la base de datos
                for (Weather weather : weatherList) {
                    SqliteWeatherStore.insertWeatherData(conn, tablename, city, weather, coordinates.getLatitude(), coordinates.getLongitude());
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}