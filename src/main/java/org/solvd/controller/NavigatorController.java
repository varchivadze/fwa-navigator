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
        userInputHandler.showPath();
    }
}