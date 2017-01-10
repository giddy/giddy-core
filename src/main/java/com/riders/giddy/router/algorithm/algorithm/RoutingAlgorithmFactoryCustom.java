package com.riders.giddy.router.algorithm.algorithm;

import com.graphhopper.routing.AlgorithmOptions;
import com.graphhopper.routing.RoutingAlgorithm;
import com.graphhopper.routing.RoutingAlgorithmFactory;
import com.graphhopper.routing.util.BeelineWeightApproximator;
import com.graphhopper.routing.util.FlagEncoder;
import com.graphhopper.routing.util.ShortestWeighting;
import com.graphhopper.routing.util.WeightApproximator;
import com.graphhopper.storage.Graph;
import com.graphhopper.storage.NodeAccess;
import com.graphhopper.util.Helper;
import com.graphhopper.util.Parameters;

import org.springframework.stereotype.Component;

import static com.graphhopper.util.Parameters.Algorithms.ASTAR;

@Component
public class RoutingAlgorithmFactoryCustom implements RoutingAlgorithmFactory {

    public static CustomAstar initAstarAlgorithm(Graph g, FlagEncoder encoder) {

        AlgorithmOptions opts = new AlgorithmOptions(ASTAR, encoder, new ShortestWeighting(encoder));

        CustomAstar aStar = new CustomAstar(g, opts.getFlagEncoder(), opts.getWeighting(), opts.getTraversalMode());
        aStar.setApproximation(getApproximation(opts, g.getNodeAccess()));
        return aStar;
    }

    @Override
    public RoutingAlgorithm createAlgo(Graph g, AlgorithmOptions opts) {
        CustomAstar aStar = new CustomAstar(g, opts.getFlagEncoder(), opts.getWeighting(), opts.getTraversalMode());
        aStar.setApproximation(getApproximation(opts, g.getNodeAccess()));
        //aStar.setAlgorithmDescriptions(register, descriptor.getLower_bound_value(), descriptor.getGaugeScore());
        return aStar;
    }

    private static WeightApproximator getApproximation(AlgorithmOptions opts, NodeAccess na) {
        double epsilon = opts.getHints().getDouble(Parameters.Algorithms.ASTAR + ".epsilon", 1);

        BeelineWeightApproximator approx = new BeelineWeightApproximator(na, opts.getWeighting());
        approx.setEpsilon(epsilon);
        approx.setDistanceCalc(Helper.DIST_PLANE);

        return approx;
    }

}
