package com.riders.giddy.api.v1.services;

import com.graphhopper.GraphHopper;
import com.graphhopper.matching.LocationIndexMatch;
import com.graphhopper.matching.MapMatching;
import com.graphhopper.routing.Path;
import com.graphhopper.routing.util.DefaultEdgeFilter;
import com.graphhopper.routing.util.EdgeFilter;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.storage.GraphHopperStorage;
import com.graphhopper.storage.index.LocationIndexTree;
import com.graphhopper.storage.index.QueryResult;
import com.riders.giddy.api.v1.models.GiddyPath;
import com.riders.giddy.api.v1.models.GiddyPoint;
import com.riders.giddy.commons.persistence.store.GraphStatsStore;
import com.riders.giddy.router.algorithm.CustomAstar;
import com.riders.giddy.router.algorithm.RoutingAlgorithmFactoryCustom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.graphhopper.routing.util.FlagEncoderFactory.BIKE;

@Component
public class GiddyRouter {

    private static final float[] DEFAULT_GAUGE = new float[]{1, 1, 1, 1, 1, 1};
    private static final float DEFAULT_LOWER_BOUND = 1;

    @Value("${city}") // found in src/main/resources/application.properties
    private String CITY;

    @Value("${systemProperties.download_osm_map}")
    private boolean DOWNLOAD_MAP;

    private GraphHopper hopper;

    private CustomAstar astar;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public GiddyRouter(GraphStatsStore store) {
        hopper = new GraphHopper();
        hopper.setEncodingManager(new EncodingManager("car,bike,foot"));

        initialiseOSM();

        RoutingAlgorithmFactoryCustom algorithmFactory = new RoutingAlgorithmFactoryCustom(store);
        astar = algorithmFactory.initAstarAlgorithm(hopper.getGraphHopperStorage(),
                hopper.getEncodingManager().getEncoder(BIKE));
    }

    private void initialiseOSM() {
        hopper.setOSMFile("./.pbfs/cluj.osm.pbf");
        hopper.setGraphHopperLocation("./.graphs/graph_" + CITY);
        hopper.importOrLoad();
    }

    public GiddyPath computeRoute(GiddyPoint from, GiddyPoint to, float[] gaugeScore, float lowerBound) {
        long startTime = System.currentTimeMillis();

        Path path = astar.computePathOnUserParameters(getNearestPoint(from),
                getNearestPoint(to),
                gaugeScore,
                lowerBound);
        GiddyPath giddyPath = new GiddyPath();
        path.calcPoints().forEach(ghPoint3D -> giddyPath.add(new GiddyPoint(ghPoint3D.lat, ghPoint3D.lon)));
        String computationTime = String.valueOf(System.currentTimeMillis() - startTime);

        logger.info("computation took " + computationTime + " ms");
        logger.info("path description:" + path.getDebugInfo());
        return giddyPath;
    }

    public GiddyPath computeRoute(GiddyPoint from, GiddyPoint to) {
        return computeRoute(from, to, DEFAULT_GAUGE, DEFAULT_LOWER_BOUND);
    }

    private int getNearestPoint(GiddyPoint point) {
        EdgeFilter edgeFilter = new DefaultEdgeFilter(hopper.getEncodingManager().getEncoder(BIKE));
        QueryResult res = hopper.getLocationIndex().findClosest(point.getLat(), point.getLon(), edgeFilter);
        return res.getClosestNode();
    }

    public MapMatching buildMapMatching() {
        GraphHopperStorage graph = hopper.getGraphHopperStorage();
        LocationIndexMatch locationIndex = new LocationIndexMatch(graph,
                (LocationIndexTree) hopper.getLocationIndex());
        return new MapMatching(graph, locationIndex, hopper.getEncodingManager().getEncoder(BIKE));
    }

    protected GraphHopper getHopper() {
        return hopper;
    }
}
