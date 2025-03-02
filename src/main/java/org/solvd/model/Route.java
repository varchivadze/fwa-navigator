package org.solvd.model;

public class Route {
    private String startAddress;
    private String destinationAddress;
    private String path;
    private double distance;

    public Route(String startAddress, String destinationAddress, String path, double distance) {
        this.startAddress = startAddress;
        this.destinationAddress = destinationAddress;
        this.path = path;
        this.distance = distance;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public String getPath() {
        return path;
    }

    public double getDistance() {
        return distance;
    }
}