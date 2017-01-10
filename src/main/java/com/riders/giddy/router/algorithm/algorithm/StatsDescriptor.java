package com.riders.giddy.router.algorithm.algorithm;

import com.riders.giddy.commons.models.RouteEdge;

import java.util.HashMap;
import java.util.Map;

public class StatsDescriptor {
    public static final String SAFE = "safe";
    public static final String TOURISTY = "touristy";
    public static final String RECREATIONAL = "recreational";
    public static final String FAST = "fast";
    public static final String CHALLENGING = "challenging";
    public static final String CROWDED = "crowded";


    //"stats":{"safe":0,"touristy":0,"recreational":0,"fast":0,"challenging":0,"crowded":0}
    int safe;
    int touristy;
    int recreational;
    int fast;
    int challenging;
    int crowded;

    int[] pos;
    int[] neg;
    long totalRecords;


    RouteEdge edge;

    public StatsDescriptor(int safe, int touristy, int recreationa, int fast, int challenging, int crowded) {
        this.safe = safe;
        this.touristy = touristy;
        this.recreational = recreationa;
        this.fast = fast;
        this.challenging = challenging;
        this.crowded = crowded;
        totalRecords = 0;
        pos = new int[6];
        neg = new int[6];
        edge = new RouteEdge();
    }

    public StatsDescriptor() {
        safe = 0;
        touristy = 0;
        recreational = 0;
        fast = 0;
        challenging = 0;
        crowded = 0;
        totalRecords = 0;
        pos = new int[6];
        neg = new int[6];
        edge = new RouteEdge();
    }

    public RouteEdge getEdge() {
        return edge;
    }

    public void setEdge(RouteEdge edge) {
        this.edge = edge;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public void addStats(int safe, int touristy, int recreationa, int fast, int challenging, int crowded) {
        this.safe += safe;
        this.touristy += touristy;
        this.recreational += recreationa;
        this.fast += fast;
        this.challenging += challenging;
        this.crowded += crowded;
        incStat(safe, 0);
        incStat(touristy, 1);
        incStat(recreationa, 2);
        incStat(fast, 3);
        incStat(challenging, 4);
        incStat(crowded, 5);
        totalRecords++;
    }

    private void incStat(int stat, int index) {
        if (stat > 0) {
            pos[index]++;
        } else neg[index]++;
    }

    public float[] getNormalisedScores() {
        float[] normalisedScores = new float[6];
        normalisedScores[0] = ((float) getCrowded() / getTotalRecords());
        normalisedScores[1] = ((float) getChallenging() / getTotalRecords());
        normalisedScores[2] = ((float) getFast() / getTotalRecords());
        normalisedScores[3] = ((float) getRecreational() / getTotalRecords());
        normalisedScores[4] = ((float) getSafe() / getTotalRecords());
        normalisedScores[5] = ((float) getTouristy() / getTotalRecords());
        return normalisedScores;
    }

    public int[][] getExplicitStats() {
        int[][] resp = new int[2][6];
        resp[0] = neg;
        resp[1] = pos;
        return resp;
    }

    private Map statsToMap() {
        Map<String, Integer> stats = new HashMap<String, Integer>();
        stats.put(SAFE, safe);
        stats.put(TOURISTY, touristy);
        stats.put(RECREATIONAL, recreational);
        stats.put(FAST, fast);
        stats.put(CHALLENGING, challenging);
        stats.put(CROWDED, crowded);
        return stats;
    }

    public String getDominantStat() {
        Map<String, Integer> stats = statsToMap();
        return stats.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
    }

    public int getSafe() {
        return safe;
    }

    public int getTouristy() {
        return touristy;
    }

    public int getRecreational() {
        return recreational;
    }

    public int getFast() {
        return fast;
    }

    public int getChallenging() {
        return challenging;
    }

    public int getCrowded() {
        return crowded;
    }
}
