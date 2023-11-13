package org.example.control;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.example.model.Coordinates;
import org.example.model.Weather;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenWeatherMapProvider {
    // Clase encargada de obtener todos los datos
    private final List<Weather> weatherList = new ArrayList<>();
    private static final String query = "https://api.openweathermap.org/data/2.5/forecast?lat=%s&lon=%s&appid=96401e11b3d4fbefb6ff23c1a69fde24";  //En donde están los # añado la lat y lon de cada isla.

    public JsonObject generate(Coordinates coordinates) throws IOException {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        String res = responseBuilder.response(coordinates, query);
        JsonParser parser = new JsonParser();
        return (JsonObject) parser.parse(res);
    }


    public List<Weather> buildWeather(Coordinates coordinates) throws IOException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        List<Weather> weatherList = new ArrayList<>();

        JsonObject json = generate(coordinates);
        JsonArray jsonArray = (JsonArray) json.get("list");

        for (JsonElement jsonElement : jsonArray) {
            JsonObject jsonObject = (JsonObject) jsonElement;
            String datetime = jsonObject.get("dt_txt").toString().substring(12, 20);

            if (datetime.equals("12:00:00")) {
                buildWeatherList(timestamp, jsonObject, coordinates);
            }
        }
        return weatherList;
    }


    private double getPrecipitation(JsonObject jsonObject) {
        return jsonObject.get("pop").getAsDouble() * 100;
    }

    private double getTemperature(JsonObject jsonObject) {
        JsonObject main = jsonObject.get("main").getAsJsonObject();
        double temperature = main.get("temp").getAsDouble();
        return kelvinToCelcius(temperature);
    }

    private double kelvinToCelcius(double temperature) {
        return temperature - 273.15;
    }

    private double getHumidity(JsonObject jsonObject) {
        JsonObject main = jsonObject.get("main").getAsJsonObject();
        return main.get("humidity").getAsDouble();
    }

    private double getClouds(JsonObject jsonObject) {
        JsonObject clouds = jsonObject.get("clouds").getAsJsonObject();
        return clouds.get("all").getAsDouble();
    }

    private double getWindSpeed(JsonObject jsonObject) {
        JsonObject wind = jsonObject.get("wind").getAsJsonObject();
        return wind.get("speed").getAsDouble();
    }

    private void buildWeatherList(Timestamp timestamp, JsonObject jsonObject, Coordinates coordinates) {
        double precipitation = getPrecipitation(jsonObject);
        double temperature = getTemperature(jsonObject);
        double humidity = getHumidity(jsonObject);
        double clouds = getClouds(jsonObject);
        double windSpeed = getWindSpeed(jsonObject);
        weatherList.add(new Weather(timestamp, temperature, precipitation, humidity, clouds, windSpeed, galdarCoord));
    }

    public static Map<String, Coordinates> createMap() {
        Map<String, Coordinates> dict = new HashMap<>();
        dict.put("Gran Canaria", new Coordinates("Galdar", 28.14701,-15.6502));
        dict.put("Tenerife", new Coordinates("Garachico", 28.373686, -16.7640491));
        dict.put("Fuerteventura", new Coordinates("Antigua",28.4160163, -14.0118473));
        dict.put("Lanzarote", new Coordinates("Yaiza",28.9567800, -13.7653500));
        dict.put("La Palma", new Coordinates("Barlovento",28.816667, -17.766667));
        dict.put("El Hierro", new Coordinates("Valverde",27.809685, -17.915147));
        dict.put("La Gomera", new Coordinates("Vallehermoso",28.179868, -17.264683));
        dict.put("La Graciosa", new Coordinates("Caleta del Sebo",29.231389, -13.501944));

        return dict;
    }


}
