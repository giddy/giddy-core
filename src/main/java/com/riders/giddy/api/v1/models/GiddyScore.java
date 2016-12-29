package com.riders.giddy.api.v1.models;


import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

/**
 * Created by rik on 11/13/16.
 */
@Entity
public class GiddyScore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @NotBlank(message = "edgeId must not be blank!")
    @Column
    private Integer edgeId;

    @NotBlank(message = "safe must not be blank!")
    @Column
    private Integer safe;

    @NotBlank(message = "touristy must not be blank!")
    @Column
    private Integer touristy;

    @NotBlank(message = "recreational must not be blank!")
    @Column
    private Integer recreational;

    @NotBlank(message = "fast must not be blank!")
    @Column
    private Integer fast;

    @NotBlank(message = "challenging must not be blank!")
    @Column
    private Integer challenging;

    @NotBlank(message = "crowded must not be blank!")
    @Column
    private Integer crowded;

    public GiddyScore() {
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
