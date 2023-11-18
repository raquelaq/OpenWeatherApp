package org.example.model;

import java.time.Instant;

public class Weather {
    private Instant ts;
    private Instant forecastTime;
    private double temperature;
    private double rain;
    private double humidity;
    private double clouds;
    private double windSpeed;
    private Coordinates coordinates;

    public Weather(Instant ts, Instant forecastTime, double temperature, double rain, double humidity, double clouds, double windSpeed, Coordinates coordinates) {
        this.ts = ts;
        this.forecastTime = forecastTime;
        this.temperature = temperature;
        this.rain = rain;
        this.humidity = humidity;
        this.clouds = clouds;
        this.windSpeed = windSpeed;
        this.coordinates = coordinates;
    }

    public Instant getTs() {
        return ts;
    }

    public Instant getForecastTime() {
        return forecastTime;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getRain() {
        return rain;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getClouds() {
        return clouds;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        return String.format("DateTime: %s, Temperature: %.2f °C, Rain: %.2f mm, Humidity: %.2f%%, Clouds: %.2f%%, WindSpeed: %.2f m/s",
                forecastTime, temperature, rain, humidity, clouds, windSpeed);
    }

    public String buildJson() {
        return "\"Weather[System_ts = " + ts.toString() + ", dateTime = " + forecastTime.toString()
                + ", Temperature = " + temperature + ", Rain = " + rain + ", Humidity = " + humidity
                + ", Clouds = " + clouds + ", windSpeed = " + windSpeed + ", Latitude = " + coordinates.getLatitude()
                + ", Longitude = " + coordinates.getLongitude() + "]";
    }
}
