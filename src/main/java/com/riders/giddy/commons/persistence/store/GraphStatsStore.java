package com.riders.giddy.commons.persistence.store;

import com.riders.giddy.router.algorithm.algorithm.StatsDescriptor;

public interface GraphStatsStore {

    StatsDescriptor getDescriptorByNode(Integer nodeId);

}
