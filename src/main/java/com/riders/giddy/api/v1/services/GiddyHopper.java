package com.riders.giddy.api.v1.services;

import com.graphhopper.GraphHopper;
import com.graphhopper.routing.util.EncodingManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class GiddyHopper extends GraphHopper {

    @Value("${city}") // found in src/main/resources/application.properties
    private String CITY;

    @Value("${systemProperties.download_osm_map}")
    private boolean DOWNLOAD_MAP;

    private EncodingManager appEncoder;

    @PostConstruct
    public void initialize() {

        this.setOSMFile("./.pbfs/" + CITY + ".osm.pbf");
        this.setGraphHopperLocation("./.graphs/graph_" + CITY);
        appEncoder = new EncodingManager("car,bike,foot");
        this.setEncodingManager(appEncoder);
        if (DOWNLOAD_MAP) {
            this.importOrLoad();
        }
    }
}
