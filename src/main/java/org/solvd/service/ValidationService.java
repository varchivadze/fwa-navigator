package org.solvd.service;

import java.util.Set;

public class ValidationService {
    private AlgorithmService algorithmService;

    public ValidationService(AlgorithmService algorithmService) {
        this.algorithmService = algorithmService;
    }

    public boolean isValidAddress(String address) {
        Set<String> availableAddresses = algorithmService.getAvailableAddresses();

        System.out.println("available addresses: " + availableAddresses);
        System.out.println("provided address: " + address);
        return availableAddresses.contains(address);
    }
}
