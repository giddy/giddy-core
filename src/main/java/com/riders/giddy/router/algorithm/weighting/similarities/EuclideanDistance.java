package com.riders.giddy.router.algorithm.weighting.similarities;

import com.riders.giddy.commons.persistence.store.entities.StatNames;

import java.util.Map;

public class EuclideanDistance implements SimilarityAlgorithm {

    @Override
    public double computeDistance(Map<StatNames, Float> p1, Map<StatNames, Float> p2) {
        double distance = 0;
        for (Map.Entry<StatNames, Float> stat : p1.entrySet()) {
            distance += Math.pow(stat.getValue() - p2.get(stat.getKey()), 2);
        }
        return Math.sqrt(distance) / (2 * p1.size());
    }
}
