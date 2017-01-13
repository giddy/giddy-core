package com.riders.giddy;

import com.riders.giddy.commons.persistence.store.GraphStatsStore;
import com.riders.giddy.router.algorithm.StatsDescriptor;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class MockedGraphStore implements GraphStatsStore {

    private Map<Integer, StatsDescriptor> mockedStore = new HashMap<>();
    private Random generator = new Random();

    @Override
    public StatsDescriptor getDescriptorByNode(Integer nodeId) {
        StatsDescriptor descriptor = mockedStore.get(nodeId);
        if (descriptor == null) {
            descriptor = new StatsDescriptor(getRandomStat(),
                    getRandomStat(),
                    getRandomStat(),
                    getRandomStat(),
                    getRandomStat(),
                    getRandomStat());
            descriptor.setTotalRecords(10);
            mockedStore.put(nodeId, descriptor);
        }
        return descriptor;
    }

    private Integer getRandomStat() {
        return generator.nextInt(20) - 10;
    }
}
