package com.riders.giddy.api.v1.services.score;

import com.riders.giddy.api.v1.models.score.GiddyEdgeScore;
import com.riders.giddy.api.v1.models.score.embeddable.GiddyScore;
import com.riders.giddy.api.v1.models.score.embeddable.GiddyScoreDescriptor;

public interface GiddyScoreServiceI {

    GiddyEdgeScore updateEdgeDescriptor(int edgeId, int baseId, GiddyScore routeDescriptor);

    GiddyEdgeScore findByBaseNodeId(int baseNodeId);

    void addStats(GiddyScore routeDescriptor, GiddyScoreDescriptor graphEdgeDescriptor);

    float getNormalisedStat(float addingStat, float baseStat, long totalStatRecords);

    GiddyScoreDescriptor getDescriptorByNode(Integer nodeId);

}
