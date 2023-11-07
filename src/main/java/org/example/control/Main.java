package org.example.control;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.example.model.Weather;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        OpenWeatherMapProvider openWeatherMapProvider = new OpenWeatherMapProvider();
        List<Weather> weatherList = openWeatherMapProvider.buildWeather();
    }


}