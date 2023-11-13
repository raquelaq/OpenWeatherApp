package org.example.model;

import java.sql.Timestamp;

public class Weather {
    private Timestamp ts;
    private double temperature;
    private double rain;
    private double humidity;
    private double clouds;
    private double windSpeed;
    private Coordinates coordinates;

    public Weather(Timestamp ts, double temperature, double rain, double humidity, double clouds, double windSpeed, Coordinates coordinates) {
        this.ts = ts;
        this.temperature = temperature;
        this.rain = rain;
        this.humidity = humidity;
        this.clouds = clouds;
        this.windSpeed = windSpeed;
        this.coordinates = coordinates;
    }

    public Timestamp getTs() {
        return ts;
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
}
