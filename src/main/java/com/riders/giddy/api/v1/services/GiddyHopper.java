package com.riders.giddy.api.v1.services;

import com.graphhopper.GraphHopper;
import com.graphhopper.routing.util.EncodingManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by rik on 10/16/16.
 */
@Service
public class GiddyHopper extends GraphHopper {

 @Value("${city}") // found in src/main/resources/application.properties
 private String CITY;

 EncodingManager appEncoder;

 @PostConstruct
 public void initialize() {

  this.setOSMFile("./.pbfs/" + CITY + ".osm.pbf");
  this.setGraphHopperLocation("./.graphs/graph_" + CITY);
  appEncoder = new EncodingManager("car,bike,foot");
  this.setEncodingManager(appEncoder);

  this.importOrLoad();
 }
}
