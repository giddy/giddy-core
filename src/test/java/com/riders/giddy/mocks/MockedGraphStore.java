package com.riders.giddy.mocks;

import com.riders.giddy.api.v1.models.score.GiddyEdgeScore;
import com.riders.giddy.api.v1.models.score.StatNames;
import com.riders.giddy.api.v1.models.score.embeddable.GiddyScoreDescriptor;
import com.riders.giddy.api.v1.services.score.GiddyScoreServiceI;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.riders.giddy.api.v1.services.core.utils.GiddyScoreHelper.buildStatsMap;

public class MockedGraphStore implements GiddyScoreServiceI {

    private Map<Integer, GiddyEdgeScore> mockedStore = new HashMap<>();
    private Random generator = new Random();

    @Override
    public GiddyEdgeScore updateEdgeDescriptor(int edgeId, int baseId, GiddyScoreDescriptor routeDescriptor) {
        return null;
    }

    @Override
    public GiddyEdgeScore findByBaseNodeId(int nodeId) {
        GiddyEdgeScore score = mockedStore.get(nodeId);
        if (score == null) {
            score = new GiddyEdgeScore(nodeId, nodeId);
            score.setGiddyScoreDescriptor(getMockedDescriptor());
            mockedStore.put(nodeId, score);
        }
        return score;
    }

    @Override
    public void addStats(GiddyScoreDescriptor routeDescriptor, GiddyScoreDescriptor graphEdgeDescriptor) {

    }

    @Override
    public float getNormalisedStat(float addingStat, float baseStat, long totalStatRecords) {
        return 0;
    }

    @Override
    public GiddyScoreDescriptor getDescriptorByNode(Integer nodeId) {
        return returnMockedDescriptor(nodeId);
    }

    private GiddyScoreDescriptor returnMockedDescriptor(Integer nodeId) {
        return findByBaseNodeId(nodeId).getGiddyScore();
    }

    private GiddyScoreDescriptor getMockedDescriptor() {
        GiddyScoreDescriptor descriptor = new GiddyScoreDescriptor();

        Map<StatNames, Float> stats = buildStatsMap(getRandomStat(), getRandomStat(), getRandomStat()
                , getRandomStat(), getRandomStat(), getRandomStat());
        descriptor.updateStats(stats);
        return descriptor;
    }

    private Float getRandomStat() {
        return ((float) generator.nextInt(20) - 10) / 10;
    }
}
