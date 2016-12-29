package com.riders.giddy.api.v1.models;

/**
 * Created by rik on 11/13/16.
 */
public class GiddyGeoNode {
    private Double latitude;
    private Double longitude;

    public GiddyGeoNode(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
