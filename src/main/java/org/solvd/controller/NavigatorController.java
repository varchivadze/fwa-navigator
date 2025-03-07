package org.solvd.controller;

import org.solvd.service.UserInputHandler;


public class NavigatorController {
    private UserInputHandler userInputHandler;

    public NavigatorController() {

        this.userInputHandler = new UserInputHandler();

    }

    public void start() {
        userInputHandler.showPath();
    }
}