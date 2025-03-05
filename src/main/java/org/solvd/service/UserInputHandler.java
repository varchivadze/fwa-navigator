package org.solvd.service;

import org.solvd.model.TransportType;

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

    public TransportType getTransportType() {
        while (true) {
            System.out.print("please choose type of transport (CAR/TRANSPORT/PEDESTRIAN): ");
            String input = scanner.nextLine().trim().toUpperCase();

            try {
                return TransportType.valueOf(input);  // conversion to enum
            } catch (IllegalArgumentException e) {
                System.out.println("Error: Wrong match, please use: CAR, TRANSPORT or PEDESTRIAN.");
            }
        }
    }
}