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
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

/**
 * TODO Add javadoc
 */
@Service
public class GiddyHopper extends GraphHopper {

    private static final String PBF_DOWLOAD_FOLDER = "osm_files/";

    private static final String CITY = "cluj";

    private static final String VEHICLE = "bike";

    private static final String PBF_URL = "https://s3.amazonaws.com/metro-extracts.mapzen.com/cluj_romania.osm.pbf";

    private EncodingManager appEncoder;

    /**
     * TODO Add javadoc
     */
    @PostConstruct
    public void initialize() {
        try {
            File pbfFile = new File(PBF_DOWLOAD_FOLDER + CITY+ ".osm.pbf");
            URL pbfUrl = new URL(PBF_URL);
            FileUtils.copyURLToFile(pbfUrl, pbfFile);
            this.setOSMFile(pbfFile.getPath());
            this.setGraphHopperLocation("./graphs/graph_" + CITY);
            appEncoder = new EncodingManager("car,bike,foot");
            this.setEncodingManager(appEncoder);
            this.importOrLoad();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
