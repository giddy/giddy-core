package com.riders.giddy.api.v1.models;

/**
 * Created by rik on 11/13/16.
 */
public class GORouteRequest {

    private GOScore goScore;
    private float lowerBoundValue;
    private GOGeoNode start;
    private GOGeoNode destination;

    private static final GOScore GO_SCORE_DEFAULT = new GOScore();
    private static final float GO_LOWER_BOUND_DEFAULT = 1;

    public GORouteRequest(GOGeoNode start, GOGeoNode destination) {
        this(GO_SCORE_DEFAULT, GO_LOWER_BOUND_DEFAULT, start, destination);
    }

    public GORouteRequest(GOScore goScore, float lowerBoundValue, GOGeoNode start, GOGeoNode destination) {
        this.goScore = goScore;
        this.lowerBoundValue = lowerBoundValue;
        this.start = start;
        this.destination = destination;
    }

    public GOGeoNode getStart() {
        return start;
    }

    public void setStart(GOGeoNode start) {
        this.start = start;
    }

    public GOGeoNode getDestination() {
        return destination;
    }

    public void setDestination(GOGeoNode destination) {
        this.destination = destination;
    }

    public float getLowerBoundValue() {
        return lowerBoundValue;
    }

    public void setLowerBoundValue(float lowerBoundValue) {
        this.lowerBoundValue = lowerBoundValue;
    }

    public float[] getGaugeScore() {
        return goScore.toFloat();
    }

    public void setGoScore(GOScore goScore) {
        this.goScore = goScore;
    }

}
