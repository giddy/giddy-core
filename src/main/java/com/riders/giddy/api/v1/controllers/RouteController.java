package com.riders.giddy.api.v1.controllers;

import com.riders.giddy.api.v1.models.GiddyPoint;
import com.riders.giddy.api.v1.services.GiddyRouter;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.jsondoc.core.annotation.ApiVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Api(name = "giddy-core", description = "This is the core routing API of Giddy")
@ApiVersion(since = "v1")
@RestController
@RequestMapping("/api/v1")
public class RouteController {

 @Autowired
 private GiddyRouter giddyRouter;

 @ApiMethod(description = "Please document api methods here")
 @RequestMapping(value = "/route", method = GET)
 //TODO Refactor request params in an array-like object
 @ResponseBody
 public String route(
         @ApiQueryParam(description = "This is latitude from")
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
     return giddyRouter.computeRoute(new GiddyPoint(latFrom, lonFrom), new GiddyPoint(latTo, lonTo)).toString();
 }
}
