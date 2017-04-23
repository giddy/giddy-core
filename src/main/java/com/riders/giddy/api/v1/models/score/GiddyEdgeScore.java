package com.riders.giddy.api.v1.models.score;


import com.riders.giddy.api.v1.models.score.embeddable.GiddyScoreDescriptor;

import javax.persistence.Cacheable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Cacheable
public class GiddyEdgeScore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    private Integer baseNodeId;

    private Integer edgeId;

    @Embedded
    private GiddyScoreDescriptor giddyScore;

    private GiddyEdgeScore() {
        baseNodeId = 0;
        edgeId = 0;
        giddyScore = new GiddyScoreDescriptor();
    }

    public GiddyEdgeScore(Integer baseNodeId, Integer edgeNodeId) {
        this.baseNodeId = baseNodeId;
        this.edgeId = edgeNodeId;
        this.giddyScore = new GiddyScoreDescriptor();
    }

    public Integer getBaseNodeId() {
        return baseNodeId;
    }

    public GiddyScoreDescriptor getGiddyScore() {
        return giddyScore;
    }

    public Integer getEdgeId() {
        return edgeId;
    }

    public void setEdgeId(Integer edgeId) {
        this.edgeId = edgeId;
    }

    public void setGiddyScoreDescriptor(GiddyScoreDescriptor giddyScore) {
        this.giddyScore = giddyScore;
    }

    public float[] toFloat() {
        return new float[0];
    }
}
