package com.riders.giddy.api.v1.models.route;

import com.riders.giddy.api.v1.models.geo.GeoPoint;

/**
 * Created by alex_ on 11/6/2016.
 */
public class RouteDescriptor {
    private GeoPoint startGeoPoint;
    private GeoPoint finishGeoPoint;

    public GeoPoint getStartGeoPoint() {
        return startGeoPoint;
    }

    public void setStartGeoPoint(GeoPoint startGeoPoint) {
        this.startGeoPoint = startGeoPoint;
    }

    public GeoPoint getFinishGeoPoint() {
        return finishGeoPoint;
    }

    public void setFinishGeoPoint(GeoPoint finishGeoPoint) {
        this.finishGeoPoint = finishGeoPoint;
    }
}
