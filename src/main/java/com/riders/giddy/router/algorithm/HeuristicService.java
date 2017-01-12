package com.riders.giddy.router.algorithm;


import com.riders.giddy.commons.persistence.store.GraphStatsStore;
import com.riders.giddy.router.algorithm.weighting.similarities.SimilarityAlgorithm;

/**
 * Computes the similarity of a given node based on its so far stored scores, with respect to
 * given gauge values.
 * The computation is defined by the algorithm that is responsible for similarity
 * check(may vary depending on tehnique) and the result is a score that represents how much the candidate
 * nodes are alike based on their descriptions
 */
class HeuristicService {

    private final GraphStatsStore graphStatsStore;

    private final SimilarityAlgorithm similarityAlgorithm;

    HeuristicService(SimilarityAlgorithm cosineSimilarityAlgorithm, GraphStatsStore graphStatsStore) {
        this.similarityAlgorithm = cosineSimilarityAlgorithm;
        this.graphStatsStore = graphStatsStore;
    }

    public double getHeuristicFactor(Integer nodeId, float[] gaugeScore, float lowerBound) {
     if (nodeId == null || gaugeScore == null) {
            return 1;//algorithmService.getNeutralFactor();
        }

        StatsDescriptor nodeDescriptor = graphStatsStore.getDescriptorByNode(nodeId);
        if (nodeDescriptor != null) {
            return calculateHeuristicFacort(nodeDescriptor, gaugeScore, lowerBound);
        }
        return 1;//algorithmService.getNeutralFactor();

    }

    private double calculateHeuristicFacort(StatsDescriptor userStats, float[] gaugeScore, float lowerBound) {
        //moves the interval of (-1,1), resulted from cosine similarity to (1,k), where k is <1
        double similarity = similarityAlgorithm.computeDistance(userStats.getNormalisedScores(), gaugeScore);
        return computeWeightFactor(similarity, lowerBound);
    }

    //as a reducing factor of weight inverse proportional to the range of similarity
    private static double computeWeightFactor(double normValue, double lowerBoundValue) {
        return ((Math.abs(normValue - 1) * (1 - lowerBoundValue)) / 2) + lowerBoundValue;
    }

}
