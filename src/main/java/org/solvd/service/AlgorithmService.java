package org.solvd.service;

import org.solvd.model.Address;
import org.solvd.model.AddressNode;
import org.solvd.model.EdgeNode;
import org.solvd.model.Route;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AlgorithmService {
    private PathProcessor processor;

    public AlgorithmService(PathProcessor processor) {
        this.processor = processor;
    }

    public Route calculateRoute(Address startAddress, Address destinationAddress, String transportType) {
        Map<String, AddressNode> mapMainNodes = processor.parseAddressNodes(transportType);
        processor.FWParser(mapMainNodes); // Floyd-Warshall
        EdgeNode bestPath = mapMainNodes
                .get(startAddress.getNearestIntersectionId())
                .getBestDist()
                .get(destinationAddress.getNearestIntersectionId());

        if (bestPath == null) {
            System.out.println("Error: no route between given points");
            return null;
        }

        return new Route(startAddress, destinationAddress, bestPath.getFullPath(), bestPath.getWeight());
    }

}