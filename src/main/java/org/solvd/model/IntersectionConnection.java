package org.solvd.model;

public class IntersectionConnection {
    private final String edgeId;
    private final String fromId;
    private final String toId;
    private final double weight;
    private final String routes;


    public IntersectionConnection(String edgeId, String fromId, String toId, double weight, String routes) {
        this.edgeId = edgeId;
        this.fromId = fromId;
        this.toId = toId;
        this.weight = weight;
        this.routes = routes;
    }

    public String getRoutes() {
        return routes;
    }

    public String getEdgeId() {
        return edgeId;
    }

    public String getFromId() {
        return fromId;
    }

    public String getToId() {
        return toId;
    }

    public double getWeight() {
        return weight;
    }
}
