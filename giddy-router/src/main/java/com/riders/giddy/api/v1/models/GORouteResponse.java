package com.riders.giddy.api.v1.models;

import java.util.ArrayList;

/**
 * Created by rik on 11/13/16.
 */
public class GORouteResponse {

    private ArrayList<GOGeoNode> geoNodes = new ArrayList<GOGeoNode>();

    public void add(GOGeoNode goGeoNode) {
        geoNodes.add(goGeoNode);
    }

    public ArrayList<GOGeoNode> getGeoNodes() {
        return new ArrayList<GOGeoNode>(geoNodes);
    }
}
