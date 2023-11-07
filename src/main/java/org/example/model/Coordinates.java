package org.example.model;

public class Coordinates {
    private final double latitude;
    private final double longitude;

    public Coordinates(double lat, double lon) {
        this.latitude = lat;
        this.longitude = lon;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
