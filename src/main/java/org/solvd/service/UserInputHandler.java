package org.solvd.service;

import org.solvd.model.TransportType;

import java.util.Scanner;

public class UserInputHandler {
    private Scanner scanner;

    public UserInputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public String getStartAddress() {
        System.out.print("Provide starting address (ex. 'plac Mickiewicza'): ");
        return scanner.nextLine().trim().toLowerCase();
    }

    public String getDestinationAddress() {
        System.out.print("Provide destination (ex. 'ulica Widok'): ");
        return scanner.nextLine().trim().toLowerCase();
    }

    public TransportType getTransportType() {
        while (true) {
            System.out.print("Please choose transport type (CAR/TRANSPORT/PEDESTRIAN): ");
            String input = scanner.nextLine().trim().toUpperCase();
            try {
                return TransportType.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Incorect selection, please use : CAR, TRANSPORT lub PEDESTRIAN.");
            }
        }
    }
}