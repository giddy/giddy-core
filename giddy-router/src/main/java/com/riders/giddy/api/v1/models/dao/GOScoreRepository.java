package com.riders.giddy.api.v1.models.dao;

import com.riders.giddy.api.v1.models.GOScore;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rik on 11/13/16.
 */
public interface GOScoreRepository extends CrudRepository<GOScore, Integer> {
    GOScore findByEdgeId(int edgeId);
}
