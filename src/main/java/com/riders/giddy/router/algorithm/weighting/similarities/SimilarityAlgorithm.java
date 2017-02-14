package com.riders.giddy.router.algorithm.weighting.similarities;

import com.riders.giddy.commons.persistence.store.entities.StatNames;

import java.util.Map;

public interface SimilarityAlgorithm {
    double computeDistance(Map<StatNames, Float> p1, Map<StatNames, Float> p2);
}
