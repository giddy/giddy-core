package com.riders.giddy.api.v1.services.core.routing;

import com.graphhopper.routing.util.WeightApproximator;
import com.graphhopper.routing.util.Weighting;
import com.graphhopper.storage.NodeAccess;
import com.graphhopper.util.DistanceCalc;
import com.graphhopper.util.Helper;
import com.riders.giddy.api.v1.models.score.StatNames;
import com.riders.giddy.api.v1.services.core.routing.weighting.similarities.CosineSimilarity;
import com.riders.giddy.api.v1.services.score.GiddyScoreServiceI;

import java.util.Map;

public class HeuristicWeightApproximator implements WeightApproximator {

    private final NodeAccess nodeAccess;
    private final Weighting weighting;
    private DistanceCalc distanceCalc = Helper.DIST_EARTH;
    private double toLat, toLon;


    private Map<StatNames, Float> gaugeScore;
    private float lowerBound;

    private final HeuristicService heuristicService;
    private final GiddyScoreServiceI store;


    public HeuristicWeightApproximator(NodeAccess nodeAccess, Weighting weighting, GiddyScoreServiceI store) {
        this.nodeAccess = nodeAccess;
        this.weighting = weighting;
        heuristicService = new HeuristicService(new CosineSimilarity(), store);
        this.store = store;
    }

    @Override
    public void setGoalNode(int toNode) {
        toLat = nodeAccess.getLatitude(toNode);
        toLon = nodeAccess.getLongitude(toNode);
    }

    @Override
    public WeightApproximator duplicate() {
        return new HeuristicWeightApproximator(nodeAccess, weighting, store)
                .setDistanceCalc(distanceCalc);
    }

    @Override
    public double approximate(int fromNode) {

        double fromLat = nodeAccess.getLatitude(fromNode);
        double fromLon = nodeAccess.getLongitude(fromNode);
        double dist2goal = distanceCalc.calcDist(toLat, toLon, fromLat, fromLon);
        double weight2goal = weighting.getMinWeight(dist2goal);


        return weight2goal * getHeuristicFactor(fromNode);
    }

    public HeuristicWeightApproximator setDistanceCalc(DistanceCalc distanceCalc) {
        this.distanceCalc = distanceCalc;
        return this;
    }

    double getHeuristicFactor(int nodeId) {
        return heuristicService.getHeuristicFactor(nodeId, gaugeScore, lowerBound);
    }

    void setUserRouteParameters(Map<StatNames, Float> gaugeScore, float lowerBound) {
        this.gaugeScore = gaugeScore;
        this.lowerBound = lowerBound;
    }
}
