package com.riders.giddy.api.v1.models;

/**
 * Created by rik on 11/13/16.
 */
public class GiddyRouteRequest {

    private GiddyScore giddyScore;
    private float lowerBoundValue;
    private GiddyGeoNode start;
    private GiddyGeoNode destination;

    private static final GiddyScore GO_SCORE_DEFAULT = new GiddyScore();
    private static final float GO_LOWER_BOUND_DEFAULT = 1;

    public GiddyRouteRequest(GiddyGeoNode start, GiddyGeoNode destination) {
        this(GO_SCORE_DEFAULT, GO_LOWER_BOUND_DEFAULT, start, destination);
    }

    public GiddyRouteRequest(GiddyScore giddyScore, float lowerBoundValue, GiddyGeoNode start, GiddyGeoNode destination) {
        this.giddyScore = giddyScore;
        this.lowerBoundValue = lowerBoundValue;
        this.start = start;
        this.destination = destination;
    }

    public GiddyGeoNode getStart() {
        return start;
    }

    public void setStart(GiddyGeoNode start) {
        this.start = start;
    }

    public GiddyGeoNode getDestination() {
        return destination;
    }

    public void setDestination(GiddyGeoNode destination) {
        this.destination = destination;
    }

    public float getLowerBoundValue() {
        return lowerBoundValue;
    }

    public void setLowerBoundValue(float lowerBoundValue) {
        this.lowerBoundValue = lowerBoundValue;
    }

    public float[] getGaugeScore() {
        return giddyScore.toFloat();
    }

    public void setGiddyScore(GiddyScore giddyScore) {
        this.giddyScore = giddyScore;
    }

}
