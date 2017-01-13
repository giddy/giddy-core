package com.riders.giddy.api.v1.models;

import java.util.ArrayList;
import java.util.List;

public class GiddyPath {
    private final List<GiddyPoint> path;

    public GiddyPath() {
        this.path = new ArrayList<>();
    }

    public List<GiddyPoint> getPath() {
        return path;
    }

    public void add(GiddyPoint point) {
        path.add(point);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GiddyPath giddyPath = (GiddyPath) o;

        return path.equals(giddyPath.path);

    }

    @Override
    public int hashCode() {
        return path.hashCode();
    }

    @Override
    public String toString() {
        String resp = "";
        for (GiddyPoint point : path) {
            resp += point.toString() + ", ";
        }
        return "GiddyPath{" +
                resp
                +
                '}';
    }
}
