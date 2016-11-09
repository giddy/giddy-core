package com.riders.giddy.commons.persistence;

import com.riders.giddy.commons.models.Path;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PathRepository extends MongoRepository<Path,String> {

}
