package org.solvd.service;

import org.solvd.database.AddressStore;
import org.solvd.model.AddressDescription;

import java.util.List;
import java.util.Scanner;

public class UserInputHandler {
    private final Scanner scanner;
    private final AddressStore addressStore;

    public UserInputHandler(AddressStore addressStore) {
        this.scanner = new Scanner(System.in);
        this.addressStore = addressStore;
    }


    public AddressDescription getAddress(String prompt) {
        System.out.println(prompt);

        System.out.println("Enter country");
        List<String> validCountries = addressStore.getValidCountries();
        System.out.println("\t valid options: " + String.join(", ", validCountries));
        String country = scanner.nextLine();

        System.out.println("Enter city");
        List<String> validCities = addressStore.getValidCities(country.trim().toLowerCase());
        System.out.println("\t valid options: " + String.join(", ", validCities));
        String city = scanner.nextLine();

        System.out.println("Enter street");
        List<String> validStreets = addressStore.getValidStreets(country.trim().toLowerCase(), city.trim().toLowerCase());
        System.out.println("\t valid options: " + String.join(", ", validStreets));
        String street = scanner.nextLine();

        System.out.println("Enter unit");
        List<String> validUnits = addressStore.getValidUnits(country.trim().toLowerCase(), city.trim().toLowerCase(), street.trim().toLowerCase());
        System.out.println("\t valid options: " + String.join(", ", validUnits));
        String unit = scanner.nextLine();

        return new AddressDescription(country, city, street, unit);
    }

    public String getTransportType() {
        System.out.println("Please choose transport type -> (car/transport/pedestrian): ");
        return scanner.nextLine();
    }
}
