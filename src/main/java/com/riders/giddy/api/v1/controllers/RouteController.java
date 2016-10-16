package com.riders.giddy.api.v1.controllers;

import com.graphhopper.GHRequest;
import com.graphhopper.util.shapes.GHPoint;
import com.riders.giddy.api.v1.services.GiddyHopper;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.jsondoc.core.annotation.ApiVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(name = "giddy-core", description = "This is the core routing API of Giddy")
@ApiVersion(since = "v1")

@RestController
@RequestMapping("/api/v1")
public class RouteController {

 @Autowired
 private GiddyHopper giddyHopper;

 @ApiMethod(description = "Please document api methods here")
 @RequestMapping("/route")
 //TODO Refactor request params in an array-like object
 public String route (@ApiQueryParam(description = "This is latitude from")
                      @RequestParam(value = "latFrom", required = false)
                           double latFrom,
                      @ApiQueryParam(description = "This is longitude from")
                      @RequestParam(value = "lonFrom", required = false)
                          double lonFrom,
                      @ApiQueryParam(description = "This is latitude to")
                      @RequestParam(value = "latTo", required = false)
                           double latTo,
                      @ApiQueryParam(description = "This is longitude to")
                      @RequestParam(value = "lonTo", required = false)
                           double lonTo) {
  GHRequest req = new GHRequest(new GHPoint(latFrom, lonFrom), new GHPoint(latTo, lonTo));
  return giddyHopper.route(req).toString();
 }
}
