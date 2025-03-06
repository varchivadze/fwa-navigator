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

    private String showPathToUser(String rawPath, String startAddress, String destinationAddress) {

        String[] tokens = rawPath.split("/");

        java.util.List<String> streets = new java.util.ArrayList<>();
        for (String token : tokens) {
            if (!token.isEmpty()) {
                try {
                    Long nodeId = Long.parseLong(token);
                    AddressNode node = mapMainNodes.get(nodeId);
                    if (node != null) {
                        String street = node.getStreet();

                        if (streets.isEmpty() || !streets.get(streets.size() - 1).equalsIgnoreCase(street)) {
                            streets.add(street);
                        }
                    }
                } catch (NumberFormatException e) {

                }
            }
        }

        java.util.List<String> friendlyPath = new java.util.ArrayList<>();
        if (streets.isEmpty() || !streets.get(0).equalsIgnoreCase(startAddress)) {
            friendlyPath.add(startAddress);
        }
        friendlyPath.addAll(streets);
        if (friendlyPath.isEmpty() || !friendlyPath.get(friendlyPath.size() - 1).equalsIgnoreCase(destinationAddress)) {
            friendlyPath.add(destinationAddress);
        }
        return String.join(" - ", friendlyPath);
    }
}