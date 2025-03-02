package org.solvd.view;

import org.solvd.model.Route;

public class OutputFormatter {
    public void displayResult(Route route) {
        System.out.println("\nShorest Route : ");
        System.out.println("From: " + route.getStartAddress());
        System.out.println("To: " + route.getDestinationAddress());
        System.out.println("Route: " + route.getPath());
        System.out.println("Distance: " + route.getDistance() + " km");
    }
}
