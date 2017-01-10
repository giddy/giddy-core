package com.riders.giddy.router.algorithm.algorithm.weighting.similarities;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
public class PearsonCorrelation implements SimilarityAlgorithm {

    public static double[] convertFloatsToDoubles(float[] input) {
        if (input == null) {
            return null; // Or throw an exception - your choice
        }
        double[] output = new double[input.length];
        for (int i = 0; i < input.length; i++) {
            output[i] = input[i];
        }
        return output;
    }

    @Override
    public double computeDistance(float[] p1, float[] p2) {

        return new PearsonsCorrelation().correlation(convertFloatsToDoubles(p1), convertFloatsToDoubles(p2));

    }
}
