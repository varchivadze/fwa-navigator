package org.solvd.view;

import org.solvd.database.AddressStore;
import org.solvd.model.AddressDescription;
import org.solvd.model.Route;

public class OutputFormatter {
    private final AddressStore addressStore;

    public OutputFormatter(AddressStore addressStore) {
        this.addressStore = addressStore;
    }

    public void displayResult(Route route) {
        System.out.println("\nShortest Route : ");

        AddressDescription startAddressDescription = addressStore.getById(route.getStartAddress().getEnteredAddressId());
        System.out.println("From: " + formatAddressDescription(startAddressDescription));

        AddressDescription destinationAddressDescription = addressStore.getById(route.getDestinationAddress().getEnteredAddressId());
        System.out.println("To: " + formatAddressDescription(destinationAddressDescription));

        System.out.println("Route: ");
        if (!route.getStartAddress().getEnteredAddressId().equals(route.getStartAddress().getNearestIntersectionId())){
            AddressDescription startIntersectionDescription = addressStore.getById(route.getStartAddress().getNearestIntersectionId());
            System.out.println("Go to the nearest intersection " + formatAddressDescription(startIntersectionDescription));
        }

        for (String intersectionId : route.getPath()){
            AddressDescription intersectionAddressDescription = addressStore.getById(intersectionId);
            System.out.println("* " + formatAddressDescription(intersectionAddressDescription));
        }
        if (!route.getDestinationAddress().getEnteredAddressId().equals(route.getDestinationAddress().getNearestIntersectionId())){
            AddressDescription finalIntersectionDescription = addressStore.getById(route.getDestinationAddress().getEnteredAddressId());
            System.out.println("Reach your destination from last intersection " + formatAddressDescription(finalIntersectionDescription));
        }

        System.out.println("Distance: " + route.getDistance() + " km");
    }

    private static String formatAddressDescription(AddressDescription addressDescription) {
        return addressDescription.getCountry() + ", " + addressDescription.getCity() + ", " + addressDescription.getStreet() + ", " + addressDescription.getUnit();
    }
}
