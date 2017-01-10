package licence.model.geo;

/**
 * Created by tothe on 4/20/16.
 */
public class RouteEdge implements Comparable<RouteEdge>{

    public int edgeID;

    public int adjNodeId;

    public int baseNodeId;

    public int getEdgeID() {
        return edgeID;
    }

    public void setEdgeID(int edgeID) {
        this.edgeID = edgeID;
    }

    public int getAdjNodeId() {
        return adjNodeId;
    }

    public void setAdjNodeId(int adjNodeId) {
        this.adjNodeId = adjNodeId;
    }

    public int getBaseNodeId() {
        return baseNodeId;
    }

    public void setBaseNodeId(int baseNodeId) {
        this.baseNodeId = baseNodeId;
    }

    public StatsDescriptor getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(StatsDescriptor descriptor) {
        this.descriptor = descriptor;
    }

    StatsDescriptor descriptor;

    public RouteEdge(int edgeID, int adjNodeId, int baseNodeId) {
        this.edgeID = edgeID;
        this.adjNodeId = adjNodeId;
        this.baseNodeId = baseNodeId;
    }

    public RouteEdge(int edgeID, int baseNodeId) {
        this.edgeID = edgeID;

        this.baseNodeId = baseNodeId;
    }


    public RouteEdge(){

    }

    @Override
    public int compareTo(RouteEdge o) {
        return Integer.compare(o.getBaseNodeId(),baseNodeId);
    }
}
