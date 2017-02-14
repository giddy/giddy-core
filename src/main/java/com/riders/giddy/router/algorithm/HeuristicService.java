package com.riders.giddy.router.algorithm;


import com.riders.giddy.commons.persistence.store.GiddyScoreServiceI;
import com.riders.giddy.commons.persistence.store.entities.GiddyScoreDescriptor;
import com.riders.giddy.commons.persistence.store.entities.StatNames;
import com.riders.giddy.router.algorithm.weighting.similarities.SimilarityAlgorithm;

import java.util.Map;

/**
 * Computes the similarity of a given node based on its so far stored scores, with respect to
 * given gauge values.
 * The computation is defined by the algorithm that is responsible for similarity
 * check(may vary depending on tehnique) and the result is a score that represents how much the candidate
 * nodes are alike based on their descriptions
 */
class HeuristicService {

    private final GiddyScoreServiceI giddyScoreServiceI;

    private final SimilarityAlgorithm similarityAlgorithm;

    HeuristicService(SimilarityAlgorithm cosineSimilarityAlgorithm, GiddyScoreServiceI giddyScoreServiceI) {
        this.similarityAlgorithm = cosineSimilarityAlgorithm;
        this.giddyScoreServiceI = giddyScoreServiceI;
    }

    public double getHeuristicFactor(Integer nodeId, Map<StatNames, Float> gaugeScore, float lowerBound) {
     if (nodeId == null || gaugeScore == null) {
            return 1;//algorithmService.getNeutralFactor();
        }

        GiddyScoreDescriptor graphEdgeDescriptor = giddyScoreServiceI.getDescriptorByNode(nodeId);
        if (graphEdgeDescriptor != null) {
            return calculateHeuristicFactor(graphEdgeDescriptor, gaugeScore, lowerBound);
        }
        return 1;//algorithmService.getNeutralFactor();
    }

    private double calculateHeuristicFactor(GiddyScoreDescriptor graphEdgeDescriptor, Map<StatNames, Float> gaugeScore, float lowerBound) {
        //moves the interval of (-1,1), resulted from cosine similarity to (1,k), where k is <1
        double similarity = similarityAlgorithm.computeDistance(graphEdgeDescriptor.getStatsMap(), gaugeScore);
        return computeWeightFactor(similarity, lowerBound);
    }

    //as a reducing factor of weight inverse proportional to the range of similarity
    private static double computeWeightFactor(double normValue, double lowerBoundValue) {
        return ((Math.abs(normValue - 1) * (1 - lowerBoundValue)) / 2) + lowerBoundValue;
    }

}
