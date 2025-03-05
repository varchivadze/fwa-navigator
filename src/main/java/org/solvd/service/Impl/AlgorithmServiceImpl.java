package org.solvd.service.Impl;

import org.solvd.model.AddressNode;
import org.solvd.model.EdgeNode;
import org.solvd.model.Route;
import org.solvd.model.TransportType;
import org.solvd.service.AlgorithmService;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AlgorithmServiceImpl implements AlgorithmService {


    public Map<Long, AddressNode> mapMainNodes;


    public AlgorithmServiceImpl(Map<Long, AddressNode> graph) {
        this.mapMainNodes = graph;
    }

    @Override
    public Set<String> getAvailableAddresses() {
        return mapMainNodes.values().stream()
                .map(node -> node.getStreet().trim().toLowerCase())
                .collect(Collectors.toSet());
    }

    @Override
    public Route calculateRoute(String startAddress, String destinationAddress, TransportType transportType) {
        Long startId = findAddressId(startAddress);
        Long destinationId = findAddressId(destinationAddress);

        if (startId == null || destinationId == null) {
            System.out.println("Error : wrong addresses");
            return null;
        }

        EdgeNode bestPath = mapMainNodes.get(startId).getBestDist().get(destinationId);
        if (bestPath == null) {
            System.out.println("Error : no Route found for the given addresses");
            return null;
        }

        double adjustedWeight = bestPath.getWeight() * transportType.getWeightMultiplier();
        return new Route(startAddress, destinationAddress, bestPath.getFullPath(), adjustedWeight);
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
}
