package com.riders.giddy.dispatcher;

import com.graphhopper.matching.EdgeMatch;
import com.graphhopper.matching.GPXFile;
import com.graphhopper.matching.MapMatching;
import com.graphhopper.matching.MatchResult;
import com.graphhopper.util.GPXEntry;
import com.riders.giddy.api.v1.services.GiddyRouter;
import com.riders.giddy.api.v1.services.GraphUtils;
import com.riders.giddy.commons.persistence.store.GiddyScoreServiceI;
import com.riders.giddy.commons.persistence.store.entities.GiddyScoreDescriptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

@Service
public class RouteDispatcher {

    private final Logger logger = LoggerFactory.getLogger(RouteDispatcher.class);

    private MapMatching mapMatching;
    private GraphUtils graphUtils;
    private GiddyScoreServiceI giddyScoreService;

    @Autowired
    public RouteDispatcher(GiddyRouter router, GraphUtils graphUtils, @Qualifier("persistStore") GiddyScoreServiceI giddyScoreService) {
        this.mapMatching = router.buildMapMatching();
        this.graphUtils = graphUtils;
        this.giddyScoreService = giddyScoreService;
    }

    public boolean updateRouteDescription(File gpxRoute, GiddyScoreDescriptor scoreDescriptor) {
        try {
            List<EdgeMatch> matches = matchToGraphEdges(gpxRoute);
            matches.forEach(edgeMatch -> {
                int edgeId = graphUtils.getEdgeIdByMatch(edgeMatch);
                int baseId = graphUtils.getBaseNodeIdByMatch(edgeMatch);

                giddyScoreService.updateEdgeDescriptor(edgeId, baseId, scoreDescriptor);
            });
        } catch (ProcessingException e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    private List<EdgeMatch> matchToGraphEdges(File gpxRoute) throws ProcessingException {
        isValidFormat(gpxRoute);

        List<GPXEntry> inputGPXEntries = new GPXFile().doImport(gpxRoute.getName()).getEntries();
        mapMatching.setForceRepair(true);
        MatchResult mr = mapMatching.doWork(inputGPXEntries);
        return mr.getEdgeMatches();
    }

    private boolean isValidFormat(File gpxRoute) throws ProcessingException {
        try {
            DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(gpxRoute);
        } catch (Exception e) {
            throw new ProcessingException(String.format("Could not parse gpx file %s. Exception: %s", gpxRoute.getName(), e.getMessage()));
        }
        return true;
    }
}
