package org.solvd.model;

public class IntersectionConnection {
    private final String edgeId;
    private final String fromId;
    private final String toId;
    private final double weight;


    public IntersectionConnection(String edgeId, String fromId, String toId, double weight) {
        this.edgeId = edgeId;
        this.fromId = fromId;
        this.toId = toId;
        this.weight = weight;
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
