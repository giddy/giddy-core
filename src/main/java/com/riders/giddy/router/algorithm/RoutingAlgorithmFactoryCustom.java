package com.riders.giddy.router.algorithm;

import com.graphhopper.routing.AlgorithmOptions;
import com.graphhopper.routing.RoutingAlgorithm;
import com.graphhopper.routing.RoutingAlgorithmFactory;
import com.graphhopper.routing.util.FlagEncoder;
import com.graphhopper.routing.util.ShortestWeighting;
import com.graphhopper.storage.Graph;
import com.graphhopper.util.Helper;
import com.riders.giddy.commons.persistence.store.GiddyScoreServiceI;

import org.springframework.stereotype.Component;

import static com.graphhopper.util.Parameters.Algorithms.ASTAR;

@Component
public class RoutingAlgorithmFactoryCustom implements RoutingAlgorithmFactory {

    private final GiddyScoreServiceI store;

    public RoutingAlgorithmFactoryCustom(GiddyScoreServiceI store) {
        this.store = store;
    }

    public CustomAstar initAstarAlgorithm(Graph g, FlagEncoder encoder) {

        AlgorithmOptions opts = new AlgorithmOptions(ASTAR, encoder, new ShortestWeighting(encoder));
        CustomAstar aStar = new CustomAstar(g, opts.getFlagEncoder(), opts.getWeighting(), opts.getTraversalMode());
        aStar.setApproximation(getApproximation(opts, g));

        return aStar;
    }

    private HeuristicWeightApproximator getApproximation(AlgorithmOptions opts, Graph graph) {
        HeuristicWeightApproximator approx = new HeuristicWeightApproximator(graph.getNodeAccess(), opts.getWeighting(), this.store);
        approx.setDistanceCalc(Helper.DIST_PLANE);

        return approx;
    }


    @Override
    @Deprecated
    public RoutingAlgorithm createAlgo(Graph g, AlgorithmOptions opts) {
        return new CustomAstar(g, opts.getFlagEncoder(), opts.getWeighting(), opts.getTraversalMode());
    }

}
