package com.riders.giddy.api.v1.models;

import java.util.ArrayList;

/**
 * Created by rik on 11/13/16.
 */
public class GiddyRouteResponse {

    private ArrayList<GiddyGeoNode> geoNodes = new ArrayList<GiddyGeoNode>();

    public void add(GiddyGeoNode giddyGeoNode) {
        geoNodes.add(giddyGeoNode);
    }

    public ArrayList<GiddyGeoNode> getGeoNodes() {
        return new ArrayList<GiddyGeoNode>(geoNodes);
    }
}
