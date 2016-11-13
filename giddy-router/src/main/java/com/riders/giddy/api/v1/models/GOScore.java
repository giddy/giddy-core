package com.riders.giddy.api.v1.models;


import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by rik on 11/13/16.
 */
@Entity
public class GOScore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @Column
    private Integer edgeId;
    @Column
    private Integer safe;
    @Column
    private Integer touristy;
    @Column
    private Integer recreational;
    @Column
    private Integer fast;
    @Column
    private Integer challenging;
    @Column
    private Integer crowded;

    public GOScore() {
        safe = 0;
        touristy = 0;
        recreational = 0;
        fast = 0;
        challenging = 0;
        crowded = 0;
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

    public Integer getCrowded() {
        return crowded;
    }

    public void setCrowded(Integer crowded) {
        this.crowded = crowded;
    }

    public Integer getEdgeId() {
        return edgeId;
    }

    public void setEdgeId(Integer edgeId) {
        this.edgeId = edgeId;
    }

    public float[] toFloat() {
        return new float[0];
    }
}
