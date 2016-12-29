package com.riders.giddy.api.v1.models.dao;

import com.riders.giddy.api.v1.models.GiddyScore;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rik on 11/13/16.
 */
public interface GiddyScoreRepository extends CrudRepository<GiddyScore, Integer> {
    GiddyScore findByEdgeId(int edgeId);
}
