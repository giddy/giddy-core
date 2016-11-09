package com.riders.giddy.commons.models;

import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Path {

    @Id
    private String id;

    @Indexed
    private List<Position> geoPointList;

    public List<Position> getGeoPointList() {
        return geoPointList;
    }

    public void setGeoPointList(List<Position> geoPointList) {
        this.geoPointList = geoPointList;
    }
}
