package com.riders.giddy.commons.persistence;

import com.riders.giddy.commons.models.SessionRoute;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SessionRouteRepository extends MongoRepository<SessionRoute,Integer>{
    List<SessionRoute> findByPath();
}
