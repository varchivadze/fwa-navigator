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
        System.out.println("\nShorest Route : ");

        AddressDescription startAddressDescription = addressStore.getById(route.getStartAddress().getEnteredAddressId());
        System.out.println("From: " + formatAddressDescription(startAddressDescription));

        AddressDescription destinationAddressDescription = addressStore.getById(route.getDestinationAddress().getEnteredAddressId());
        System.out.println("To: " + formatAddressDescription(destinationAddressDescription));

        System.out.println("Route: ");
        //todo for StartAddress, if entered AddressId != NearestIntersectionId then print first step to get to intersection
        //todo use addressStore by getId to fetch addressDecryption for nearestIntersection and use formatAddressDescription
        for (String intersectionId : route.getPath()){
            //todo use AddressStore.getById to fetch addressDescription for intersection
            //todo format and print addressDescription for intersection
        }
        //todo for DestinationAddress, if entered AddressId != NearestIntersectionId then print first step to get to intersection
        //todo use addressStore by getId to fetch addressDecryption for nearestIntersection and use formatAddressDescription
        System.out.println("Distance: " + route.getDistance() + " km");
    }

    private static String formatAddressDescription(AddressDescription addressDescription) {
        return addressDescription.getCountry() + ", " + addressDescription.getCity(); //todo
    }
}
