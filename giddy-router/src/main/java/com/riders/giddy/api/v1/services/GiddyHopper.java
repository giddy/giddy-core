package com.riders.giddy.api.v1.services;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.PathWrapper;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.util.Parameters;
import com.graphhopper.util.PointList;
import com.graphhopper.util.shapes.GHPoint3D;
import com.riders.giddy.api.v1.models.GOGeoNode;
import com.riders.giddy.api.v1.models.GORouteRequest;
import com.riders.giddy.api.v1.models.GORouteResponse;
import com.riders.giddy.commons.models.SessionRoute;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Iterator;

@Service
public class GiddyHopper extends GraphHopper {

    @Value("${city}") // found in src/main/resources/application.properties
    private String CITY;

    @Value("${vehicle}")
    private String VEHICLE;

    @Value("${download_osm_map}")
    private boolean DOWNLOAD_MAP;

    private EncodingManager appEncoder;

    @PostConstruct
    public void initialize() {

        this.setOSMFile("./.pbfs/" + CITY + ".osm.pbf");
        this.setGraphHopperLocation("./.graphs/graph_" + CITY);
        appEncoder = new EncodingManager("car,bike,foot");
        this.setEncodingManager(appEncoder);
        this.importOrLoad();
    }

    public GORouteResponse route(GORouteRequest goRouteRequest) {

        GHRequest ghRequest = new GHRequest(goRouteRequest.getStart().getLatitude(),
                goRouteRequest.getStart().getLongitude(),
                goRouteRequest.getDestination().getLatitude(),
                goRouteRequest.getDestination().getLongitude()).setVehicle(VEHICLE).setAlgorithm(Parameters.Algorithms.ASTAR_BI);
        GHResponse ghResponse = route(ghRequest);

        PathWrapper pathWrapper = ghResponse.getBest();

        GORouteResponse goRouteResponse = new GORouteResponse();

        PointList pointList = pathWrapper.getPoints();
        GHPoint3D ghPoint;
        for (Iterator<GHPoint3D> it = pointList.iterator(); it.hasNext(); ) {
            ghPoint = it.next();
            goRouteResponse.add(new GOGeoNode(ghPoint.getLat(), ghPoint.getLon()));
        }

        return goRouteResponse;
    }

}
