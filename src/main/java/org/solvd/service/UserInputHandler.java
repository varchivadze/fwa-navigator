package org.solvd.service;

import org.solvd.model.AddressNode;
import org.solvd.model.EdgeNode;
import org.solvd.model.TransportType;
import org.solvd.service.Impl.AddressServiceImpl;
import org.solvd.service.Impl.EdgeServiceImpl;
import org.solvd.service.Impl.PedestrianServiceImpl;
import org.solvd.service.Impl.TransportServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class UserInputHandler {

    private final Scanner scanner;
    private final AddressServiceImpl addressService;
    private final EdgeServiceImpl edgeService;
    private final PathProcessor pathProcessor;
    private final PedestrianService pedestrianService;
    private final TransportService transportService;
    private TransportType transportType;

    public UserInputHandler() {
        scanner = new Scanner(System.in);
        addressService = new AddressServiceImpl();
        edgeService = new EdgeServiceImpl();
        pathProcessor = new PathProcessor();
        transportType = TransportType.CAR;
        pedestrianService = new PedestrianServiceImpl();
        transportService = new TransportServiceImpl();
    }


    private AddressNode inputAddress() {


        AddressNode addressNode = new AddressNode();

        System.out.println();

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
        System.out.println("--- Navigator ---");


        System.out.println("Type of transport:");
        System.out.println("1 - CAR");
        System.out.println("2 - BUS");
        System.out.println("3 - PEDESTRIAN");
        String type = scanner.nextLine();
        if (!type.isEmpty()) {
            transportType = TransportType.getTransportType(type);
        }

        System.out.println("Start point:");
        AddressNode start = inputAddress();
        System.out.println("Destination:");
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

    public EdgeNode getPath() {
        List<AddressNode> addressNodes = getAddressesDb();
        EdgeNode edgeNode = new EdgeNode();
        edgeNode.setFrom(addressNodes.get(0).getId());
        edgeNode.setTo(addressNodes.get(1).getId());
        EdgeNode returnNode = null;
        switch (transportType) {
            case TransportType.CAR -> returnNode = edgeService.read(edgeNode);
            case TransportType.TRANSPORT -> returnNode = transportService.read(edgeNode);
            default -> returnNode = pedestrianService.read(edgeNode);
        }

        System.out.println(returnNode);
        return returnNode;
    }

    public String getDetailPath() {
        EdgeNode edgeNode = getPath();
        System.out.println(edgeNode);
        List<List<Long>> pathList = pathProcessor.parseFullPath(edgeNode);
        StringBuilder finalPath = new StringBuilder();
        finalPath.append(addressService.readById(pathList.get(0).get(0)).shortAddress());
        for (List<Long> fromTo : pathList) {
            EdgeNode edgeNode1 = new EdgeNode();
            edgeNode1.setFrom(fromTo.get(0));
            edgeNode1.setTo(fromTo.get(1));

            finalPath.append("\n Go to ->>>" + addressService.readById(fromTo.get(1)).shortAddress());

            if (transportType.equals(TransportType.TRANSPORT)) {
                String currentBussNode = transportService.read(edgeNode1).getBusses();
                System.out.println(edgeNode1);
                if (currentBussNode != null && !currentBussNode.trim().isEmpty()) {
                    finalPath.append(" \n----> Busses ");
                    finalPath.append(currentBussNode);
                    finalPath.append(" <----\n");
                }

            }

        }
        return finalPath.toString();
    }

    public void showPath() {
        String pathDisplay = getDetailPath();
        System.out.println("Final path :" + pathDisplay);
    }


}