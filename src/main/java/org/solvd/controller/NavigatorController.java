package org.solvd.controller;

import org.solvd.model.Route;
import org.solvd.service.AlgorithmService;
import org.solvd.service.UserInputHandler;
import org.solvd.service.ValidationService;
import org.solvd.view.OutputFormatter;

public class NavigatorController {
    private UserInputHandler userInputHandler;
    private ValidationService validationService;
    private AlgorithmService algorithmService;
    private OutputFormatter outputFormatter;

    public NavigatorController() {
        this.algorithmService = new AlgorithmService();
        this.validationService = new ValidationService(algorithmService);
        this.userInputHandler = new UserInputHandler();
        this.outputFormatter = new OutputFormatter();
    }

    public void start() {
        System.out.println("= FWA NAVIGATOR =");

        String startAddress = userInputHandler.getStartAddress();
        String destinationAddress = userInputHandler.getDestinationAddress();

        if (!validationService.isValidAddress(startAddress)) {
            System.out.println("ERROR: Incorrect Start Address");
            return;
        }

        if (!validationService.isValidAddress(destinationAddress)) {
            System.out.println("ERROR: Incorrect Destination Address");
            return;
        }

        System.out.println("Calculating Route...");
        Route computedRoute = algorithmService.calculateRoute(startAddress, destinationAddress);

        if (computedRoute != null) {
            outputFormatter.displayResult(computedRoute);
        } else {
            System.out.println("Didn't manage to find Route for given points");
        }
    }
}