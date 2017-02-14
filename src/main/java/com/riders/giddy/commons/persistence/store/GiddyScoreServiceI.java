package com.riders.giddy.commons.persistence.store;

import com.riders.giddy.api.v1.models.dao.GiddyEdgeScore;
import com.riders.giddy.commons.persistence.store.entities.GiddyScoreDescriptor;

public interface GiddyScoreServiceI {

    GiddyEdgeScore updateEdgeDescriptor(int edgeId, int baseId, GiddyScoreDescriptor routeDescriptor);

    GiddyEdgeScore findByBaseNodeId(int baseNodeId);

    void addStats(GiddyScoreDescriptor routeDescriptor, GiddyScoreDescriptor graphEdgeDescriptor);

    float getNormalisedStat(float addingStat, float baseStat, long totalStatRecords);

    GiddyScoreDescriptor getDescriptorByNode(Integer nodeId);

}
