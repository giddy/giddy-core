package com.riders.giddy.api.v1.services.score;

import com.riders.giddy.api.v1.models.score.GiddyEdgeScore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiddyScoreRepository extends JpaRepository<GiddyEdgeScore, Integer> {
    GiddyEdgeScore findByEdgeId(int edgeId);

    GiddyEdgeScore findByBaseNodeId(int baseNodeId);
}
