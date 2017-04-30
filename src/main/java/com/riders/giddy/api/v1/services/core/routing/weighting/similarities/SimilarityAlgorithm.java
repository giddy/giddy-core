package com.riders.giddy.api.v1.services.core.routing.weighting.similarities;

import com.riders.giddy.api.v1.models.score.StatNames;

import java.util.Map;

public interface SimilarityAlgorithm {
    double computeDistance(Map<StatNames, Float> p1, Map<StatNames, Float> p2);
}
