package org.solvd.model;

import java.util.List;

public class Route {
    private final Address startAddress;
    private final Address destinationAddress;
    private List<String> path;
    private double distance;

    public Route(Address startAddress, Address destinationAddress, List<String> path, double distance) {
        this.startAddress = startAddress;
        this.destinationAddress = destinationAddress;
        this.path = path;
        this.distance = distance;
    }

    public Address getStartAddress() {
        return startAddress;
    }

    public Address getDestinationAddress() {
        return destinationAddress;
    }

    public List<String> getPath() {
        return path;
    }

    public double getDistance() {
        return distance;
    }
}