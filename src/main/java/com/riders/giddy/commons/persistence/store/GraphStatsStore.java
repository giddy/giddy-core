package com.riders.giddy.commons.persistence.store;

import com.riders.giddy.router.algorithm.StatsDescriptor;

import org.springframework.stereotype.Component;

@Component
public interface GraphStatsStore {

    StatsDescriptor getDescriptorByNode(Integer nodeId);

}