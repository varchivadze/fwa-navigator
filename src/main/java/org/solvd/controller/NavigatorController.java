package org.solvd.controller;

import org.solvd.database.AddressStore;
import org.solvd.database.AddressStoreJDBC;
import org.solvd.model.Address;
import org.solvd.model.Route;
import org.solvd.model.AddressDescription;
import org.solvd.service.AlgorithmService;
import org.solvd.service.PathProcessor;
import org.solvd.service.UserInputHandler;
import org.solvd.service.ValidationService;
import org.solvd.view.OutputFormatter;

public class NavigatorController {
    private UserInputHandler userInputHandler;
    private ValidationService validationService;
    private AlgorithmService algorithmService;
    private OutputFormatter outputFormatter;

    public NavigatorController() {
        AddressStore addressStore = new AddressStoreJDBC();
        this.userInputHandler = new UserInputHandler(addressStore);
        this.validationService = new ValidationService(addressStore);
        PathProcessor pathProcessor = new PathProcessor(addressStore);
        this.algorithmService = new AlgorithmService(pathProcessor);
        this.outputFormatter = new OutputFormatter(addressStore);
    }

    public void start() {
        System.out.println("= FWA NAVIGATOR =");

        AddressDescription startAddressInput = userInputHandler.getAddress("Please provide start point: ");
        AddressDescription destinationAddressInput = userInputHandler.getAddress("Please provide end point: ");

        Address startAddress = validationService.validateAddressInput(startAddressInput);
        Address destinationAddress = validationService.validateAddressInput(destinationAddressInput);

        if (startAddress == null) {
            System.out.println("ERROR: Incorrect Start Address");
            return;
        }

        if (destinationAddress == null) {
            System.out.println("ERROR: Incorrect Destination Address");
            return;
        }

        String transportType = userInputHandler.getTransportType();
        System.out.println("Calculating Route...");
        Route computedRoute = algorithmService.calculateRoute(startAddress, destinationAddress, transportType);

        if (computedRoute != null) {
            outputFormatter.displayResult(computedRoute);
        } else {
            System.out.println("Didn't manage to find Route for given points");
        }
    }
}