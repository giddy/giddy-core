package com.riders.giddy.api.v1.services.core.dispatcher;

import com.graphhopper.matching.EdgeMatch;
import com.graphhopper.matching.GPXFile;
import com.graphhopper.matching.MapMatching;
import com.graphhopper.matching.MatchResult;
import com.graphhopper.storage.index.LocationIndexTree;
import com.graphhopper.util.GPXEntry;
import com.riders.giddy.api.v1.models.score.embeddable.GiddyScoreDescriptor;
import com.riders.giddy.api.v1.services.core.GiddyRouter;
import com.riders.giddy.api.v1.services.core.GraphUtils;
import com.riders.giddy.api.v1.services.score.GiddyScoreServiceI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

@Service
public class RouteDispatcher {

    private final Logger logger = LoggerFactory.getLogger(RouteDispatcher.class);

    private MapMatching mapMatching;
    private GraphUtils graphUtils;
    private GiddyScoreServiceI giddyScoreService;

    @Autowired
    public RouteDispatcher(GiddyRouter router, GraphUtils graphUtils, GiddyScoreServiceI giddyScoreService) {
        this.mapMatching = router.buildMapMatching();
        this.graphUtils = graphUtils;
        this.giddyScoreService = giddyScoreService;
    }

    public synchronized boolean updateRouteDescription(MultipartFile gpxRoute, GiddyScoreDescriptor scoreDescriptor) throws IOException {
        File tempFile = new File("temp");
        byte[] content = gpxRoute.getBytes();
        BufferedOutputStream stream = new BufferedOutputStream(
                new FileOutputStream(tempFile));
        stream.write(content);
        stream.close();

        boolean isUpdated;

        try {
            List<EdgeMatch> matches = matchToGraphEdges(tempFile);
            matches.forEach(edgeMatch -> {
                int edgeId = graphUtils.getEdgeIdByMatch(edgeMatch);
                int baseId = graphUtils.getBaseNodeIdByMatch(edgeMatch);

                giddyScoreService.updateEdgeDescriptor(edgeId, baseId, scoreDescriptor);
            });
            isUpdated = true;
        } catch (ProcessingException e) {
            logger.error(e.getMessage());
            isUpdated = false;
        } finally {
            tempFile.delete();
        }
        return isUpdated;
    }

    private List<EdgeMatch> matchToGraphEdges(File gpxRoute) throws ProcessingException {

        isValidFormat(gpxRoute);

        List<GPXEntry> inputGPXEntries = new GPXFile().doImport(gpxRoute.getName()).getEntries();
        mapMatching.setForceRepair(true);
        LocationIndexTree locationIndexTree;
        MatchResult mr = mapMatching.doWork(inputGPXEntries);
        return mr.getEdgeMatches();
    }

    private boolean isValidFormat(File gpxRoute) throws ProcessingException {
        try {
            DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(gpxRoute);
        } catch (SAXException e) {
            throw new ProcessingException(String.format("Could not parse gpx file %s. Exception: %s", gpxRoute.getName(), e.getMessage()));
        } catch (ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
