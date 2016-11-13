package com.riders.giddy.commons.models;

import com.mongodb.client.model.geojson.Point;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class SessionRoute {

    public SessionRoute() {
    }

    public SessionRoute(Descriptor descriptor, Path path) {
        this.descriptor = descriptor;
        this.path = path;

    }

    @Id
    private String id;
    private Descriptor descriptor;
    private Path path;

    private List<Point> geoPointList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Descriptor getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(Descriptor descriptor) {
        this.descriptor = descriptor;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }


    public List<Point> getGeoPointList() {
        return geoPointList;
    }

    public void setGeoPointList(List<Point> geoPointList) {
        this.geoPointList = geoPointList;
    }

}
