package com.riders.giddy.api.v1.services.core.utils;

import com.riders.giddy.api.v1.models.score.StatNames;

import java.util.HashMap;
import java.util.Map;

import static com.riders.giddy.api.v1.models.score.StatNames.CHALLENGING;
import static com.riders.giddy.api.v1.models.score.StatNames.CROWDED;
import static com.riders.giddy.api.v1.models.score.StatNames.FAST;
import static com.riders.giddy.api.v1.models.score.StatNames.RECREATIONAL;
import static com.riders.giddy.api.v1.models.score.StatNames.SAFE;
import static com.riders.giddy.api.v1.models.score.StatNames.TOURISTY;

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
