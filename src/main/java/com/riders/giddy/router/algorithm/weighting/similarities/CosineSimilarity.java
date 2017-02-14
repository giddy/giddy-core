package com.riders.giddy.router.algorithm.weighting.similarities;

import com.riders.giddy.commons.persistence.store.entities.StatNames;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CosineSimilarity implements SimilarityAlgorithm {

    @Override
    public double computeDistance(Map<StatNames, Float> p1, Map<StatNames, Float> p2) {
        if (checkInOrigin(p1) || checkInOrigin(p2)) {
            //neutral if one is in origin, prevent division by 0
            return 0;
        }
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (Map.Entry<StatNames, Float> entry : p1.entrySet()) {
            dotProduct += entry.getValue() * p2.get(entry.getKey());
            normA += Math.pow(entry.getValue(), 2);
            normB += Math.pow(p2.get(entry.getKey()), 2);
        }
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }

    //prevent division by zero, if one of the candidates is in origin, we need to translate the two points
    private boolean checkInOrigin(Map<StatNames, Float> p) {
        final boolean[] inOrigin = {true};
        p.entrySet().forEach(entry -> {
            if (entry.getValue() != 0) inOrigin[0] = false;
        });
        return inOrigin[0];
    }
}
