package org.solvd.controller;

import org.solvd.model.Route;
import org.solvd.model.TransportType;
import org.solvd.service.AlgorithmService;
import org.solvd.service.UserInputHandler;
import org.solvd.service.ValidationService;
import org.solvd.view.OutputFormatter;

public class NavigatorController {
    private UserInputHandler userInputHandler;
    private ValidationService validationService;
    private AlgorithmService algorithmService;
    private OutputFormatter outputFormatter;

    public NavigatorController(AlgorithmService algorithmService) {
        this.algorithmService = algorithmService;
        this.validationService = new ValidationService(algorithmService);
        this.userInputHandler = new UserInputHandler();
        this.outputFormatter = new OutputFormatter();
    }

    public void start() {
        System.out.println("-- Navigator --");

        String startAddress = userInputHandler.getStartAddress();
        String destinationAddress = userInputHandler.getDestinationAddress();
        TransportType transportType = userInputHandler.getTransportType();

        if (!validationService.isValidAddress(startAddress) || !validationService.isValidAddress(destinationAddress)) {
            System.out.println("Error: wrong address");
            return;
        }

        System.out.println("Calculating Route");
        Route computedRoute = algorithmService.calculateRoute(startAddress, destinationAddress, transportType);

        if (computedRoute != null) {
            outputFormatter.displayResult(computedRoute);
        } else {
            System.out.println("Cannot find route");
        }
    }
}