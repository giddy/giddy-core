package com.riders.giddy.api.v1.models.score;

public enum StatNames {
    SAFE("safe"), TOURISTY("touristy"), RECREATIONAL("recreational"), FAST("fast"), CHALLENGING("challenging"), CROWDED("crowded");

    private final String text;

    private StatNames(String text) {
        this.text = text;
    }

    public static boolean exists(String stat) {
        for (StatNames deinedStat : values()) {
            if (deinedStat.toString().equals(stat)) {
                return true;
            }
        }
        return false;
    }

    public static StatNames getStat(String stat) {
        for (StatNames statNames : values()) {
            if (statNames.toString().equals(stat)) {
                return statNames;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return text;
    }

}
