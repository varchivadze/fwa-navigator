package org.solvd.model;

public enum TransportType {
    CAR(1.0),
    TRANSPORT(0.5),
    PEDESTRIAN(3.0);

    private final double weightMultiplier;

    TransportType(double weightMultiplier) {
        this.weightMultiplier = weightMultiplier;
    }

    public double getWeightMultiplier() {
        return weightMultiplier;
    }
}
