package com.riders.giddy.api.v1.services.core.dispatcher;

import org.springframework.stereotype.Service;

@Service
class GPXMatcher {
/*
    List<GPXEntry> inputGPXEntries;

    @Autowired
    GiddyRouter coreRouter;

    public List<EdgeMatch> importGpx(String filename, String encoderName) {


        inputGPXEntries = new GPXFile().doImport(filename).getEntries();

// create MapMatching object, can and should be shared accross threads

        GraphHopperStorage graph = coreRouter.getGraphHopperStorage();
        LocationIndexMatch locationIndex = new LocationIndexMatch(graph,
                (LocationIndexTree) coreRouter.getLocationIndex());
        MapMatching mapMatching = new MapMatching(graph, locationIndex, coreRouter.getEncodingManager().getEncoder(encoderName));

// do the actual matching, get the GPX entries from a file or via stream
        mapMatching.setForceRepair(true);
        MatchResult mr = mapMatching.doWork(inputGPXEntries);

// return GraphHopper edges with all associated GPX entries

        List<EdgeMatch> matches = mr.getEdgeMatches();
// now do something with the edges like storing the edgeIds or doing fetchWayGeometry etc
        return matches;

    }


  *//*  public String getGpxEntries(List<GPXEntry>) {
        String resp = "";
        for (GPXEntry e : inputGPXEntries) {
            resp += String.valueOf(e.getLat()) + "," + String.valueOf(e.getLon() + ",");
        }

        return resp;

    }*/
}
