package com.riders.giddy.api.v1.models.dao;

import com.riders.giddy.api.v1.models.GiddyActivity;
import org.springframework.data.repository.CrudRepository;


/**
 * Created by rik on 12/30/16.
 */
public interface GiddyActivityRepository extends CrudRepository<GiddyActivity, Integer> {
}
