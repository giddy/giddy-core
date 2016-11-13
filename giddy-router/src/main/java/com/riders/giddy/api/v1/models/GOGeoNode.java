package com.riders.giddy.api.v1.models;

/**
 * Created by rik on 11/13/16.
 */
public class GOGeoNode {
    private Double latitude;
    private Double longitude;

    public GOGeoNode(Double latitude, Double longitude) {
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
