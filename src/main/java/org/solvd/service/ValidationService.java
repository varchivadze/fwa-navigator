package org.solvd.service;

import java.util.Set;

public class ValidationService {
    private AlgorithmService algorithmService;

    public ValidationService(AlgorithmService algorithmService) {
        this.algorithmService = algorithmService;
    }

    public boolean isValidAddress(String address) {
        Set<String> availableAddresses = algorithmService.getAvailableAddresses();
        System.out.println("Available addresses: " + availableAddresses);
        System.out.println("Input address: " + address.trim().toLowerCase());
        return availableAddresses.contains(address.trim().toLowerCase());
    }
}