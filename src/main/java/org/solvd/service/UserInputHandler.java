package org.solvd.service;

import org.solvd.model.AddressNode;
import org.solvd.model.EdgeNode;
import org.solvd.service.Impl.AddressServiceImpl;
import org.solvd.service.Impl.EdgeServiceImpl;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class UserInputHandler {

    private Scanner scanner;
    private AddressServiceImpl addressService;
    private EdgeServiceImpl edgeService;
    private PathProcessor pathProcessor;

    public UserInputHandler() {
        scanner = new Scanner(System.in);
        addressService = new AddressServiceImpl();
        edgeService = new EdgeServiceImpl();
        pathProcessor = new PathProcessor();
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
        System.out.println("--- Navigator ---");
        System.out.println("Start point:");
        AddressNode start = inputAddress();
        System.out.println("Destimation:");
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
    public EdgeNode getPath(){
        List<AddressNode> addressNodes = getAddressesDb();
        EdgeNode edgeNode= new EdgeNode();
        edgeNode.setFrom(addressNodes.get(0).getId());
        edgeNode.setTo(addressNodes.get(1).getId());
        EdgeNode returnNode = edgeService.read(edgeNode);
        System.out.println(returnNode);
        return returnNode;
    }
    public String getDetailPath(){
        EdgeNode edgeNode = getPath();
        System.out.println(edgeNode);
        List<List<Long>> pathList= pathProcessor.parseFullPath(edgeNode);
        StringBuilder finalPath = new StringBuilder();
        for (List<Long> fromTo:pathList){
            EdgeNode edgeNode1 = new EdgeNode();
            edgeNode1.setFrom(fromTo.get(0));
            edgeNode1.setTo(fromTo.get(1));
            finalPath.append(edgeService.read(edgeNode1).toString());
        }
        return finalPath.toString();
    }

    public void showPath(){
        String pathDisplay = getDetailPath();
        System.out.println("Final path :" + pathDisplay);
    }


}