package org.solvd.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.solvd.database.AddressStore;
import org.solvd.model.AddressNode;
import org.solvd.model.EdgeNode;
import org.solvd.model.IntersectionConnection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathProcessor {
    private final AddressStore addressStore;

    public PathProcessor(AddressStore addressStore) {
        this.addressStore = addressStore;
    }

    public Map<String, AddressNode> initAddressNodes() {
        Map<String, AddressNode> addressNodeHashMap = new HashMap<>();
        List<String> allIntersectionIds = addressStore.getAllIntersections();
        for (String intersectionId : allIntersectionIds) {
            addressNodeHashMap.put(intersectionId, new AddressNode(intersectionId));
        }
        return addressNodeHashMap;
    }

    public Map<String, AddressNode> addEdgesToAddressNodes(Map<String, AddressNode> addressNodes, String transportType) {
        List<IntersectionConnection> allConnections = addressStore.getAllConnections(transportType);


            for (IntersectionConnection connection : allConnections) {
                EdgeNode edgeNode = new EdgeNode();
                edgeNode.setId(connection.getEdgeId());
                edgeNode.setFrom(connection.getFromId());
                edgeNode.setTo(connection.getToId());
                edgeNode.setWeight(connection.getWeight());

                edgeNode.setFullPath(Arrays.asList(edgeNode.getFrom(),edgeNode.getTo()));
                EdgeNode reversed = edgeNode.clone();
                reversed.setFrom(edgeNode.getTo());
                reversed.setTo(edgeNode.getFrom());
                edgeNode.setFullPath(Arrays.asList(edgeNode.getTo(),edgeNode.getFrom()));

                addressNodes.get(edgeNode.getFrom()).getBestDist().put(edgeNode.getTo(), edgeNode);
                addressNodes.get(edgeNode.getTo()).getBestDist().put(edgeNode.getFrom(), reversed);
            }
        return addressNodes;

    }

    public Map<String, AddressNode> parseAddressNodes(String transportType) {
        return addEdgesToAddressNodes(initAddressNodes(), transportType);
    }

    private static List<String> joinLists(List<String> list1, List<String> list2){
        List<String> combined = Stream.concat(list1.stream(), list2.stream())
                .collect(Collectors.toList());
        return combined;
    }

    public void FWParser(Map<String, AddressNode> addressNodes) {
        for (Map.Entry<String, AddressNode> med : addressNodes.entrySet()) {
            for (Map.Entry<String, AddressNode> start : addressNodes.entrySet()) {
                for (Map.Entry<String, AddressNode> end : addressNodes.entrySet()) {

                    EdgeNode startToMed = start.getValue().getBestDist().get(med.getValue().getId());
                    EdgeNode medToEnd = med.getValue().getBestDist().get(end.getValue().getId());
                    EdgeNode startToEnd = start.getValue().getBestDist().get(end.getValue().getId());
                    EdgeNode secondBestStartToEnd = start.getValue().getSecondBestDist().get(end.getValue().getId());

                    if (startToMed != null && medToEnd != null) {
                        Double newDistance = startToMed.getWeight() + medToEnd.getWeight();

                        if (startToEnd == null || newDistance < startToEnd.getWeight()) {
                            EdgeNode newPath = new EdgeNode();
                            newPath.setFrom(start.getValue().getId());
                            newPath.setTo(end.getValue().getId());
                            newPath.setWeight(newDistance);
                            newPath.setFullPath(joinLists(startToMed.getFullPath(), medToEnd.getFullPath()));
                            if (secondBestStartToEnd == null || secondBestStartToEnd.getWeight() > newDistance) {
                                start.getValue().getSecondBestDist().put(end.getValue().getId(), start.getValue().getBestDist().get(end.getValue().getId()));
                            }
                            start.getValue().getBestDist().put(end.getValue().getId(), newPath);
                        }

                    }
                }
            }
        }
    }
}