package licence.business.routing;

import com.graphhopper.GraphHopper;
import com.graphhopper.matching.EdgeMatch;
import com.graphhopper.routing.VirtualEdgeIteratorState;
import com.graphhopper.storage.GraphHopperStorage;
import com.graphhopper.util.EdgeIterator;
import com.graphhopper.util.EdgeIteratorState;
import com.graphhopper.util.PointList;
import com.graphhopper.util.shapes.GHPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tothe on 5/27/16.
 */
@Service
public class GraphUtils {

    private CoreRouter router;
    private GraphHopperStorage graph;


    @Autowired
    public GraphUtils(CoreRouter router) {

        this.router = router;
        graph = router.getGraphHopperStorage();
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
        return router.findEdgeId(lat, lon);
    }


    public int getNodeByCoords(double lat, double lon) {
        return router.getLocationIndex().findID(lat, lon);
    }

    public int getEdgeByBaseId(int baseNodeId) {
        EdgeIterator e=router.getGraphHopperStorage().createEdgeExplorer().setBaseNode(baseNodeId);
        while (e.next()){
            if(e.getBaseNode()==baseNodeId){
                return e.getEdge();
            }
        }
        return -1;
    }

    public int getAdjByBaseIdEdgeId(int baseNodeId,int edgeId) {
        EdgeIterator e=router.getGraphHopperStorage().createEdgeExplorer().setBaseNode(baseNodeId);
        while (e.next()){
            if(e.getBaseNode()==baseNodeId&&edgeId==e.getEdge()){
                return e.getAdjNode();
            }
        }
        return -1;
    }




    public CoreRouter getRouter() {
        return router;

    }

    public void setRouter(CoreRouter router) {
        this.router = router;
    }

    //EdgeIteratorState edgeIteratorState=router.getGraphHopperStorage().getEdgeIteratorState(edgeId,edgeNodeId);

}
