package com.riders.giddy.commons.persistence.store;

import com.riders.giddy.router.algorithm.algorithm.StatsDescriptor;

/**
 * Created by Tudor on 11/24/2016.
 */
public interface HraphStatsStore {

    StatsDescriptor getDescriptorByNode(Integer nodeId);

}
