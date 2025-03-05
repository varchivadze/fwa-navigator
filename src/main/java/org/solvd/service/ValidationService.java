package org.solvd.service;

import java.util.Set;

public class ValidationService {
    private AlgorithmService algorithmService;

    public ValidationService(AlgorithmService algorithmService) {
        this.algorithmService = algorithmService;
    }

    public boolean isValidAddress(String address) {
        Set<String> availableAddresses = algorithmService.getAvailableAddresses();
        // Zakładamy, że adresy są przechowywane w unormowanej (trim + lowercase) postaci
        System.out.println("Dostępne adresy: " + availableAddresses);
        System.out.println("Wprowadzony adres: " + address);
        return availableAddresses.contains(address);
    }
}
