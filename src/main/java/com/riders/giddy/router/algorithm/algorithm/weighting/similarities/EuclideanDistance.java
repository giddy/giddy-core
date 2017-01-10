package com.riders.giddy.router.algorithm.algorithm.weighting.similarities;

/**
 * Created by tothe on 5/25/16.
 */
public class EuclideanDistance implements SimilarityAlgorithm {
    @Override
    public double computeDistance(float[] p1, float[] p2) {
        if (p1.length != p2.length) {
            return -2;
        }

        double distance = 0;
        for (int i = 0; i < p1.length; i++) {
            distance += (p1[i] - p2[i]) * (p1[i] - p2[i]);
        }
        return Math.sqrt(distance) / (2 * p1.length);
    }
}
