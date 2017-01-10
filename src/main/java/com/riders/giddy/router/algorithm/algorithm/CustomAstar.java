package com.riders.giddy.router.algorithm.algorithm;

import com.graphhopper.routing.AbstractRoutingAlgorithm;
import com.graphhopper.routing.Path;
import com.graphhopper.routing.util.FlagEncoder;
import com.graphhopper.routing.util.TraversalMode;
import com.graphhopper.routing.util.WeightApproximator;
import com.graphhopper.routing.util.Weighting;
import com.graphhopper.storage.Graph;
import com.graphhopper.storage.SPTEntry;
import com.graphhopper.util.DistancePlaneProjection;
import com.graphhopper.util.EdgeExplorer;
import com.graphhopper.util.EdgeIterator;
import com.graphhopper.util.Parameters;

import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.PriorityQueue;

import licence.model.geo.GraphRegister;

public class CustomAstar extends AbstractRoutingAlgorithm {


    private WeightApproximator weightApprox;
    private int visitedCount;
    private TIntObjectMap<AStarEntry> fromMap;
    private PriorityQueue<AStarEntry> prioQueueOpenSet;
    private AStarEntry currEdge;
    private int to1 = -1;

    @Autowired
    private HeuristicService heuristicService;


    public CustomAstar(Graph g, FlagEncoder encoder, Weighting weighting, TraversalMode tMode) {


        super(g, encoder, weighting, tMode);

        initCollections(1000);
        HeuristicWeightApproximator heuristicApprox = new HeuristicWeightApproximator(nodeAccess, weighting);
        heuristicApprox.setDistanceCalc(new DistancePlaneProjection());
        setApproximation(heuristicApprox);
    }

    /**
     * @param approx defines how distance to goal Node is approximated
     */
    public CustomAstar setApproximation(WeightApproximator approx) {
        weightApprox = approx;
        return this;
    }

    protected void initCollections(int size) {
        fromMap = new TIntObjectHashMap<AStarEntry>();
        prioQueueOpenSet = new PriorityQueue<AStarEntry>(size);
    }

    @Override
    public Path calcPath(int from, int to) {
        checkAlreadyRun();
        to1 = to;

        weightApprox.setGoalNode(to);
        double weightToGoal = weightApprox.approximate(from);
        currEdge = new AStarEntry(EdgeIterator.NO_EDGE, from, 0 + weightToGoal, 0);
        if (!traversalMode.isEdgeBased()) {
            fromMap.put(from, currEdge);
        }
        return runAlgo();
    }

    private Path runAlgo() {
        double currWeightToGoal, estimationFullWeight;
        EdgeExplorer explorer = outEdgeExplorer;
        while (true) {
            int currVertex = currEdge.adjNode;

            visitedCount++;
            if (isWeightLimitExceeded())
                return createEmptyPath();

            if (finished())
                break;

            EdgeIterator iter = explorer.setBaseNode(currVertex);
            while (iter.next()) {
                if (!accept(iter, currEdge.edge))
                    continue;

                int neighborNode = iter.getAdjNode();
                int traversalId = traversalMode.createTraversalId(iter, false);
                //compute the heuristic factor for the candidate edge
                double alreadyVisitedWeight = weighting.calcWeight(iter, false, currEdge.edge) * getHeuristicFactor(iter.getBaseNode())
                        + currEdge.weightOfVisitedPath;
                if (Double.isInfinite(alreadyVisitedWeight))
                    continue;

                AStarEntry ase = fromMap.get(traversalId);
                if (ase == null || ase.weightOfVisitedPath > alreadyVisitedWeight) {
                    //compute the heuristic factor for next possible node
                    currWeightToGoal = weightApprox.approximate(neighborNode) * getHeuristicFactor(neighborNode);
                    estimationFullWeight = alreadyVisitedWeight + currWeightToGoal;
                    if (ase == null) {
                        ase = new AStarEntry(iter.getEdge(), neighborNode, estimationFullWeight, alreadyVisitedWeight);
                        fromMap.put(traversalId, ase);
                    } else {
                        assert (ase.weight > 0.9999999 * estimationFullWeight) : "Inconsistent distance estimate "
                                + ase.weight + " vs " + estimationFullWeight + " (" + ase.weight / estimationFullWeight + "), and:"
                                + ase.weightOfVisitedPath + " vs " + alreadyVisitedWeight + " (" + ase.weightOfVisitedPath / alreadyVisitedWeight + ")";
                        prioQueueOpenSet.remove(ase);
                        ase.edge = iter.getEdge();
                        ase.weight = estimationFullWeight;
                        ase.weightOfVisitedPath = alreadyVisitedWeight;
                    }

                    ase.parent = currEdge;
                    prioQueueOpenSet.add(ase);

                    updateBestPath(iter, ase, traversalId);
                }
            }

            if (prioQueueOpenSet.isEmpty())
                return createEmptyPath();

            currEdge = prioQueueOpenSet.poll();
            if (currEdge == null)
                throw new AssertionError("Empty edge cannot happen");
        }

        return extractPath();
    }

    @Override
    protected Path extractPath() {
        return new Path(graph, flagEncoder).setWeight(currEdge.weight).setSPTEntry(currEdge).extract();
    }

    @Override
    protected boolean finished() {
        return currEdge.adjNode == to1;
    }

    @Override
    public int getVisitedNodes() {
        return visitedCount;
    }

    @Override
    public String getName() {
        return Parameters.Algorithms.ASTAR;
    }

    public double getHeuristicFactor(int nodeId) {
        return heuristicService.getHeuristicFactor(nodeId, GraphRegister.getGaugeScore());
    }

    public static class AStarEntry extends SPTEntry {
        double weightOfVisitedPath;

        public AStarEntry(int edgeId, int adjNode, double weightForHeap, double weightOfVisitedPath) {
            super(edgeId, adjNode, weightForHeap);
            this.weightOfVisitedPath = weightOfVisitedPath;
        }

        @Override
        public final double getWeightOfVisitedPath() {
            return weightOfVisitedPath;
        }
    }

}
