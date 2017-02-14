package com.riders.giddy.commons.persistence.store.entities;

import java.util.Map;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class GiddyScoreDescriptor {

    @Embedded
    private GiddyScore giddyScore;

    private long totalRecords;

    public GiddyScoreDescriptor() {
        giddyScore = new GiddyScore();
        totalRecords = 0;
    }

    public void updateStats(Map<StatNames, Float> statMap) {
        giddyScore.putAll(statMap);
        totalRecords++;
    }

    public Map<StatNames, Float> getStatsMap() {
        return giddyScore.getAll();
    }

    public long getTotalRecords() {
        return totalRecords;
    }
}
