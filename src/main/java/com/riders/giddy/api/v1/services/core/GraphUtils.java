package com.riders.giddy.api.v1.services.core;

import com.graphhopper.GraphHopper;
import com.graphhopper.matching.EdgeMatch;
import com.graphhopper.util.EdgeIterator;
import com.graphhopper.util.shapes.GHPoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GraphUtils {
    private GraphHopper giddyHopper;


    @Autowired
    public GraphUtils(GiddyRouter giddyHopper) {
        this.giddyHopper = giddyHopper.getHopper();
    }

    public int getEdgeIdByMatch(EdgeMatch edgeMatch) {
        return edgeMatch.getEdgeState().getEdge();
    }

    public int getBaseNodeIdByMatch(EdgeMatch edgeMatch) {
        return edgeMatch.getEdgeState().getBaseNode();
    }

    public int getAdjNodeIdByMatch(EdgeMatch edgeMatch) {
        return edgeMatch.getEdgeState().getAdjNode();
    }


    public List<Integer> getAdjNodes(int baseNode) {
        EdgeIterator edgeIterator = giddyHopper.getGraphHopperStorage().createEdgeExplorer().setBaseNode(baseNode);
        List<Integer> adjNodeIds = new ArrayList<>();
        while (edgeIterator.next()) {

            adjNodeIds.add(edgeIterator.getAdjNode());
            //         Path path= dijkstra.calcPath(edge.getBaseNode(), edgeNo)  ;
        }
        return adjNodeIds;
    }

    public GHPoint getPointById(int nodeId) {
        return new GHPoint(giddyHopper.getGraphHopperStorage().getNodeAccess().getLat(nodeId),
                giddyHopper.getGraphHopperStorage().getNodeAccess().getLon(nodeId));

    }


    public int getNodeByCoords(double lat, double lon) {
        return giddyHopper.getLocationIndex().findID(lat, lon);
    }

    public int getEdgeByBaseId(int baseNodeId) {
        EdgeIterator e= giddyHopper.getGraphHopperStorage().createEdgeExplorer().setBaseNode(baseNodeId);
        while (e.next()){
            if(e.getBaseNode()==baseNodeId){
                return e.getEdge();
            }
        }
        return -1;
    }

    public int getAdjByBaseIdEdgeId(int baseNodeId,int edgeId) {
        EdgeIterator e= giddyHopper.getGraphHopperStorage().createEdgeExplorer().setBaseNode(baseNodeId);
        while (e.next()){
            if(e.getBaseNode()==baseNodeId&&edgeId==e.getEdge()){
                return e.getAdjNode();
            }
        }
        return -1;
    }
}
