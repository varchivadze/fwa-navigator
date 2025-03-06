package org.solvd.service;

import org.solvd.model.AddressNode;
import org.solvd.service.Impl.AddressServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class UserInputHandler {

    private Scanner scanner;
    private AddressServiceImpl addressService;

    public UserInputHandler() {
        scanner = new Scanner(System.in);
        addressService = new AddressServiceImpl();
    }


    private AddressNode inputAddress() {


        AddressNode addressNode = new AddressNode();

        System.out.println("");

        System.out.println("Enter country");
        String country = scanner.nextLine();
        addressNode.setCountry(country);

        System.out.println("Enter city");

        String city = scanner.nextLine();
        addressNode.setCity(city);


        System.out.println("Enter street");

        String street = scanner.nextLine();
        addressNode.setStreet(street);


        System.out.println("Enter unit");

        String unit = scanner.nextLine();
        addressNode.setUnit(unit);

        return addressNode;

    }

    private List<AddressNode> getAddresses() {
        List<AddressNode> addresses = new ArrayList<>();
        AddressNode start = inputAddress();
        AddressNode end = inputAddress();
        addresses.add(start);
        addresses.add(end);
        return addresses;
    }

    public List<AddressNode> getAddressesDb() {
        List<AddressNode> addresses = getAddresses();
        List<AddressNode> dbAddresses = new ArrayList<>();
        for (AddressNode address : addresses) {
            try {
                dbAddresses.add(addressService.read(address));


            } catch (Exception e) {
                System.out.println("Couldn't retrieve address from database" + e.getMessage());
            }
        }
        dbAddresses.forEach(System.out::println);
        if (dbAddresses.stream().anyMatch(Objects::nonNull)) {
            return dbAddresses;
        } else {
            System.out.println("There is no address from db");
            return null;
        }
    }


}