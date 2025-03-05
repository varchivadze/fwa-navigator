package org.solvd.service;

import org.solvd.model.Route;
import org.solvd.model.TransportType;

import java.util.Set;

public interface AlgorithmService {

    Set<String> getAvailableAddresses();

    Route calculateRoute(String startAddress, String destinationAddress, TransportType transportType);
}
