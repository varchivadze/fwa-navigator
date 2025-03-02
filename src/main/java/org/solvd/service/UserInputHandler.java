package org.solvd.service;

import java.util.Scanner;

public class UserInputHandler {
    private Scanner scanner;

    public UserInputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public String getStartAddress() {
        System.out.println("Please provide start point: ");
        return scanner.nextLine();
    }

    public String getDestinationAddress() {
        System.out.println("Please provide end point: ");
        return scanner.nextLine();
    }

    /*public String getTransportType() {
        System.out.println("Please choose transport type -> (car/transport/pedestrian): ");
        return scanner.nextLine(); */
}
