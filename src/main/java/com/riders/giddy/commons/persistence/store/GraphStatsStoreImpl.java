package com.riders.giddy.commons.persistence.store;

import com.riders.giddy.router.algorithm.StatsDescriptor;

import org.springframework.stereotype.Component;

@Component
public class GraphStatsStoreImpl implements GraphStatsStore {
    @Override
    public StatsDescriptor getDescriptorByNode(Integer nodeId) {
        return null;
    }
}
