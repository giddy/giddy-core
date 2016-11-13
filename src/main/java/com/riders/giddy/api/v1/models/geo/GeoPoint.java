package com.riders.giddy.api.v1.models.geo;

import com.graphhopper.util.shapes.GHPoint;

/**
 * Created by alex_ on 11/6/2016.
 */
public class GeoPoint {
    private double latitude;
    private double longitude;

    public GeoPoint() {
        this(0, 0);
    }

    public GeoPoint(double latitude, double longitude) {
        setLatitude(latitude);
        setLongitude(longitude);
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public GHPoint toGHPoint() {
        return new GHPoint(getLatitude(), getLongitude());
    }
}
