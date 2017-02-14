package com.riders.giddy.api.v1.services;

import com.riders.giddy.commons.persistence.store.entities.StatNames;

import java.util.HashMap;
import java.util.Map;

import static com.riders.giddy.commons.persistence.store.entities.StatNames.CHALLENGING;
import static com.riders.giddy.commons.persistence.store.entities.StatNames.CROWDED;
import static com.riders.giddy.commons.persistence.store.entities.StatNames.FAST;
import static com.riders.giddy.commons.persistence.store.entities.StatNames.RECREATIONAL;
import static com.riders.giddy.commons.persistence.store.entities.StatNames.SAFE;
import static com.riders.giddy.commons.persistence.store.entities.StatNames.TOURISTY;

public class GiddyScoreHelper {

    public static Map<StatNames, Float> buildStatsMap(float safe, float touristy, float recreational, float fast, float challenging, float crowded) {
        Map<StatNames, Float> statsMap = new HashMap<>();
        statsMap.put(SAFE, safe);
        statsMap.put(TOURISTY, touristy);
        statsMap.put(RECREATIONAL, recreational);
        statsMap.put(FAST, fast);
        statsMap.put(CHALLENGING, challenging);
        statsMap.put(CROWDED, crowded);
        return statsMap;
    }
}
