package com.riders.giddy.dispatcher;

import com.graphhopper.matching.EdgeMatch;
import com.graphhopper.matching.GPXFile;
import com.graphhopper.matching.MapMatching;
import com.graphhopper.matching.MatchResult;
import com.graphhopper.util.GPXEntry;
import com.riders.giddy.api.v1.services.GiddyRouter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

@Component
public class Dispatcher {

    MapMatching mapMatching;

    @Autowired
    public Dispatcher(GiddyRouter router) {
        this.mapMatching = router.buildMapMatching();
    }

    public boolean checkXmlFile(File fXmlFile) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fXmlFile);
        return true;
    }


    private List<EdgeMatch> importGpx(File file) {


        List<GPXEntry> inputGPXEntries = new GPXFile().doImport(file.getName()).getEntries();
        mapMatching.setForceRepair(true);
        MatchResult mr = mapMatching.doWork(inputGPXEntries);

// return GraphHopper edges with all associated GPX entries

        List<EdgeMatch> matches = mr.getEdgeMatches();
// now do something with the edges like storing the edgeIds or doing fetchWayGeometry etc
        return matches;
    }

    public void dispatch(File gpxFile) {

    }

}
