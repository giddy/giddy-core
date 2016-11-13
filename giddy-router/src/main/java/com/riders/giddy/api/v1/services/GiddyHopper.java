package com.riders.giddy.api.v1.services;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.PathWrapper;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.util.Parameters;
import com.graphhopper.util.PointList;
import com.graphhopper.util.shapes.GHPoint3D;
import com.riders.giddy.api.v1.models.GiddyGeoNode;
import com.riders.giddy.api.v1.models.GiddyRouteRequest;
import com.riders.giddy.api.v1.models.GiddyRouteResponse;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Iterator;

/**
 * TODO Add javadoc
 */
@Service
public class GiddyHopper extends GraphHopper {

    private static final String CITY = "cluj";

    private static final String VEHICLE = "bike";

    private EncodingManager appEncoder;

    /**
     * TODO Add javadoc
     */
    @PostConstruct
    public void initialize() {

        this.setOSMFile("./.pbfs/" + CITY + ".osm.pbf");
        this.setGraphHopperLocation("./.graphs/graph_" + CITY);
        appEncoder = new EncodingManager("car,bike,foot");
        this.setEncodingManager(appEncoder);
        this.importOrLoad();
    }

    /**
     * TODO Add javadoc
     * @param giddyRouteRequest
     * @return
     */
    public GiddyRouteResponse route(GiddyRouteRequest giddyRouteRequest) {
        GHRequest ghRequest = new GHRequest(giddyRouteRequest.getStart().getLatitude(),
                giddyRouteRequest.getStart().getLongitude(),
                giddyRouteRequest.getDestination().getLatitude(),
                giddyRouteRequest.getDestination().getLongitude())
                .setVehicle(VEHICLE).setAlgorithm(Parameters.Algorithms.ASTAR_BI);

        GHResponse ghResponse = route(ghRequest);

        PathWrapper pathWrapper = ghResponse.getBest();

        GiddyRouteResponse giddyRouteResponse = new GiddyRouteResponse();

        PointList pointList = pathWrapper.getPoints();
        GHPoint3D ghPoint;
        for (Iterator<GHPoint3D> it = pointList.iterator(); it.hasNext(); ) {
            ghPoint = it.next();
            giddyRouteResponse.add(new GiddyGeoNode(ghPoint.getLat(), ghPoint.getLon()));
        }

        return giddyRouteResponse;
    }

}
