package com.riders.giddy.router.algorithm.weighting.similarities;

import org.springframework.stereotype.Component;

@Component
public class CosineSimilarity implements SimilarityAlgorithm {

    @Override
    public double computeDistance(float[] p1, float[] p2) {
        if (checkInOrigin(p1) || checkInOrigin(p2)) {
            //neutral if one is in origin, prevent division by 0
            return 0;
        }
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < p1.length; i++) {
            dotProduct += p1[i] * p2[i];
            normA += Math.pow(p1[i], 2);
            normB += Math.pow(p2[i], 2);
        }
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }

    //prevent division by zero, if one of the candidates is in origin, we need to translate the two points
    private boolean checkInOrigin(float[] p) {
        boolean inOrigin = true;
        for (float i : p) {
            if (i != 0) inOrigin = false;
        }
        return inOrigin;
    }
}
