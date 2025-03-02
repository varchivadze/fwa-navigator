package org.solvd.model;

public class Address {
    private final String enteredAddressId;
    private final String nearestIntersectionId;

    public Address(String enteredAddressId, String nearestIntersectionId) {
        this.enteredAddressId = enteredAddressId;
        this.nearestIntersectionId = nearestIntersectionId;
    }

    public String getEnteredAddressId() {
        return enteredAddressId;
    }

    public String getNearestIntersectionId() {
        return nearestIntersectionId;
    }
}
