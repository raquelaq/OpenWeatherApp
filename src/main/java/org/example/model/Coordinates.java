package org.example.model;

public class Coordinates {
    private final String name;
    private final double latitude;
    private final double longitude;

    public Coordinates(String name, double lat, double lon) {
        this.name = name;
        this.latitude = lat;
        this.longitude = lon;
    }

    public String getName() {
        return name;
    }
    public double getLatitude() {
        return latitude;
    }


    public double getLongitude() {
        return longitude;
    }
}
