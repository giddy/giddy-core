package com.riders.giddy.api.v1.models;

import javax.persistence.Embeddable;

@Embeddable
public class GiddyScore {

    private Integer safe;

    private Integer touristy;

    private Integer recreational;

    private Integer fast;

    private Integer challenging;

    private Integer crowded;

    public GiddyScore() {
        safe = 0;
        touristy = 0;
        recreational = 0;
        fast = 0;
        challenging = 0;
        crowded = 0;
    }

    public GiddyScore(Integer safe, Integer touristy, Integer recreational, Integer fast, Integer challenging, Integer crowded) {
        this.safe = safe;
        this.touristy = touristy;
        this.recreational = recreational;
        this.fast = fast;
        this.challenging = challenging;
        this.crowded = crowded;
    }

    public Integer getCrowded() {
        return crowded;
    }

    public void setCrowded(Integer crowded) {
        this.crowded = crowded;
    }

    public Integer getSafe() {
        return safe;
    }

    public void setSafe(Integer safe) {
        this.safe = safe;
    }

    public Integer getTouristy() {
        return touristy;
    }

    public void setTouristy(Integer touristy) {
        this.touristy = touristy;
    }

    public Integer getRecreational() {
        return recreational;
    }

    public void setRecreational(Integer recreational) {
        this.recreational = recreational;
    }

    public Integer getFast() {
        return fast;
    }

    public void setFast(Integer fast) {
        this.fast = fast;
    }

    public Integer getChallenging() {
        return challenging;
    }

    public void setChallenging(Integer challenging) {
        this.challenging = challenging;
    }
}
