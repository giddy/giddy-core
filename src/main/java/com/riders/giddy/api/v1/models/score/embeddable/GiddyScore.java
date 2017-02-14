package com.riders.giddy.api.v1.models.score.embeddable;

import com.riders.giddy.api.v1.models.score.StatNames;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;

@Embeddable
public class GiddyScore {

    @ElementCollection
    private Map<StatNames, Float> scores;

    public GiddyScore() {
        scores = new HashMap<>();
        for (StatNames statName : StatNames.values()) {
            scores.put(statName, 0f);
        }
    }

    public Float get(StatNames statName) {
        return scores.get(statName);
    }

    void putAll(Map<StatNames, Float> scores) {
        for (StatNames statName : StatNames.values()) {
            this.scores.put(statName, scores.get(statName));
        }
    }

    public Map<StatNames, Float> getAll() {
        HashMap<StatNames, Float> scopresCopy = new HashMap<>();
        scopresCopy.putAll(scores);
        return scopresCopy;
    }
}
