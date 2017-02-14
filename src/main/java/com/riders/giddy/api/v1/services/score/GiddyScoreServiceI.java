package com.riders.giddy.api.v1.services.score;

import com.riders.giddy.api.v1.models.score.GiddyEdgeScore;
import com.riders.giddy.api.v1.models.score.embeddable.GiddyScoreDescriptor;

public interface GiddyScoreServiceI {

    GiddyEdgeScore updateEdgeDescriptor(int edgeId, int baseId, GiddyScoreDescriptor routeDescriptor);

    GiddyEdgeScore findByBaseNodeId(int baseNodeId);

    void addStats(GiddyScoreDescriptor routeDescriptor, GiddyScoreDescriptor graphEdgeDescriptor);

    float getNormalisedStat(float addingStat, float baseStat, long totalStatRecords);

    GiddyScoreDescriptor getDescriptorByNode(Integer nodeId);

}
