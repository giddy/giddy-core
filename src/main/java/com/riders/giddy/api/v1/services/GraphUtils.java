package com.riders.giddy.api.v1.services;

import org.springframework.stereotype.Service;

@Service
class GraphUtils {
/*
    private GiddyRouter giddyHopper;
    private GraphHopperStorage graph;


    @Autowired
    public GraphUtils(GiddyRouter giddyHopper) {

        this.giddyHopper = giddyHopper;
        graph = giddyHopper.getGraphHopperStorage();
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
        EdgeIterator edgeIterator = graph.createEdgeExplorer().setBaseNode(baseNode);
        //         edgeIterator.setDistance(distance);
        List<Integer> adjNodeIds = new ArrayList<Integer>();
        while (edgeIterator.next()) {

            adjNodeIds.add(edgeIterator.getAdjNode());
            //         Path path= dijkstra.calcPath(edge.getBaseNode(), edgeNo)  ;
        }
        return adjNodeIds;
    }

    public GHPoint getPointById(int nodeId) {
        return new GHPoint(graph.getNodeAccess().getLat(nodeId), graph.getNodeAccess().getLon(nodeId));

    }

    public int getEdgeByBaseCoords(double lat, double lon) {
        return giddyHopper.findEdgeId(lat, lon);
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
    }*/
}
