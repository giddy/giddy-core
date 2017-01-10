package com.riders.giddy.api.v1.models;

public class GiddyPoint {

    private final double lat;
    private final double lon;

    public GiddyPoint(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    @Override
    public String toString() {
        return lat + ", " + lon;
    }
}
