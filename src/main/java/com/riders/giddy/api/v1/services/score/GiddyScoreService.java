package com.riders.giddy.api.v1.services.score;

import com.riders.giddy.api.v1.models.score.GiddyEdgeScore;
import com.riders.giddy.api.v1.models.score.StatNames;
import com.riders.giddy.api.v1.models.score.embeddable.GiddyScoreDescriptor;
import com.sun.istack.internal.Nullable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class GiddyScoreService implements GiddyScoreServiceI {

    private final int maxScoreValue = StatNames.values().length;

    private GiddyScoreRepository repository;

    @Autowired
    public GiddyScoreService(GiddyScoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public GiddyEdgeScore updateEdgeDescriptor(int edgeId, int baseId, GiddyScoreDescriptor routeDescriptor) {
        GiddyEdgeScore edgeScore = repository.findByBaseNodeId(baseId);
        if (edgeScore == null) {
            edgeScore = new GiddyEdgeScore(baseId, edgeId);
        }
        addStats(routeDescriptor, edgeScore.getGiddyScore());
        return repository.save(edgeScore);
    }

    @Override
    public GiddyEdgeScore findByBaseNodeId(int baseNodeId) {
        return repository.findByBaseNodeId(baseNodeId);
    }

    public GiddyEdgeScore findByEdgeId(int edgeId) {
        return repository.findByEdgeId(edgeId);
    }

    @Override
    public void addStats(GiddyScoreDescriptor routeDescriptor, GiddyScoreDescriptor graphEdgeDescriptor) {
        long totalRecords = graphEdgeDescriptor.getTotalRecords();
        Map<StatNames, Float> stats = graphEdgeDescriptor.getStatsMap();
        for (Map.Entry<StatNames, Float> stat : stats.entrySet()) {
            float normalisedStat = getNormalisedStat(routeDescriptor.getStatsMap().get(stat.getKey()), stat.getValue(), totalRecords);
            stats.put(stat.getKey(), normalisedStat);
        }
        graphEdgeDescriptor.updateStats(stats);
    }

    @Override
    public float getNormalisedStat(float addingStat, float baseStat, long totalStatRecords) {
        return (maxScoreValue * (totalStatRecords * baseStat) + addingStat) / (maxScoreValue * (totalStatRecords + 1));
    }

    @Override
    @Nullable
    public GiddyScoreDescriptor getDescriptorByNode(Integer nodeId) {
        GiddyEdgeScore score = findByBaseNodeId(nodeId);
        if (score != null) {
            return score.getGiddyScore();
        }
        return null;
    }
}
