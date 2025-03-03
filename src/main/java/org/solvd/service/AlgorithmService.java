package org.solvd.service;

import org.solvd.model.AddressNode;
import org.solvd.model.EdgeNode;
import org.solvd.model.Route;

import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AlgorithmService {
    private PathProcessor processor;
    public Map<Long, AddressNode> mapMainNodes;

    public AlgorithmService() {
        this.processor = new PathProcessor();
        loadGraphData();
    }

    private void loadGraphData() {
        ClassLoader classLoader = AlgorithmService.class.getClassLoader();
        URL mainNodes = classLoader.getResource("warsaw_main_nodes.csv");
        URL edges = classLoader.getResource("warsaw_edges.csv");

        if (mainNodes != null && edges != null) {
            File csvMain = new File(mainNodes.getFile());
            File csvEdges = new File(edges.getFile());
            this.mapMainNodes = processor.parseAddressNodes(csvMain, csvEdges);
            processor.FWParser(mapMainNodes); // Floyd-Warshall
        } else {
            throw new RuntimeException("File does not exist");
        }
    }

    public Route calculateRoute(String startAddress, String destinationAddress) {
        Long startId = findAddressId(startAddress);
        Long destinationId = findAddressId(destinationAddress);

        if (startId == null || destinationId == null) {
            System.out.println("Error : couldn't find data in db");
            return null;
        }

        EdgeNode bestPath = mapMainNodes.get(startId).getBestDist().get(destinationId);

        if (bestPath == null) {
            System.out.println("Error: no route between given points");
            return null;
        }

        return new Route(startAddress, destinationAddress, bestPath.getFullPath(), bestPath.getWeight());
    }

    private Long findAddressId(String address) {
        for (AddressNode node : mapMainNodes.values()) {
            String formattedNodeAddress = node.getStreet().trim().toLowerCase();
            String formattedInputAddress = address.trim().toLowerCase();

            if (formattedNodeAddress.equals(formattedInputAddress)) {
                return node.getId();
            }
        }
        return null;
    }

    public Set<String> getAvailableAddresses() {
        return mapMainNodes.values().stream()
                .map(node -> node.getStreet().trim().toLowerCase())
                .collect(Collectors.toSet());
    }
}