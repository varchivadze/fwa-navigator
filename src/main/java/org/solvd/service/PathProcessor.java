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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, AddressNode> addEdgesToAddressNodes(Map<String, AddressNode> addressNodes) {
        List<IntersectionConnection> allConnections = addressStore.getAllConnections();



            for (IntersectionConnection connection : allConnections) {
                EdgeNode edgeNode = new EdgeNode();
                edgeNode.setId(connection.getEdgeId());
                edgeNode.setFrom(connection.getFromId());
                edgeNode.setTo(connection.getToId());
                edgeNode.setWeight(connection.getWeight());

                //todo change path from / delimiter String to List of String IDs
                edgeNode.setFullPath(String.format("/%s/%s/", addressNodes.get(edgeNode.getFrom()).getId(), addressNodes.get(edgeNode.getTo()).getId()));
                EdgeNode reversed = edgeNode.clone();
                reversed.setFrom(edgeNode.getTo());
                reversed.setTo(edgeNode.getFrom());
                reversed.setFullPath(String.format("/%s/%s/", addressNodes.get(edgeNode.getTo()).getId(), addressNodes.get(edgeNode.getFrom()).getId()));

                addressNodes.get(edgeNode.getFrom()).getBestDist().put(edgeNode.getTo(), edgeNode);
                addressNodes.get(edgeNode.getTo()).getBestDist().put(edgeNode.getFrom(), reversed);
            }
        return addressNodes;

    }

    public Map<String, AddressNode> parseAddressNodes() {
        return addEdgesToAddressNodes(initAddressNodes());
    }

/*    public Map<String , AddressNode> addEdgesToAddressNodes(List<AddressNode> addressNodes, List<EdgeNode> edgeNodes) {
        HashMap<String, AddressNode> addressNodeHashMap = new HashMap<>();
        addressNodes.stream().peek(System.out::println).forEach(node -> addressNodeHashMap.put(node.getId(), node));

        for (EdgeNode edgeNode : edgeNodes) {
            edgeNode.setFullPath(String.format("/%s/%s/", addressNodeHashMap.get(edgeNode.getFrom()).getId(), addressNodeHashMap.get(edgeNode.getTo()).getId()));
            EdgeNode reversed = edgeNode.clone();
            reversed.setFrom(edgeNode.getTo());
            reversed.setTo(edgeNode.getFrom());
            reversed.setFullPath(String.format("/%s/%s/", addressNodeHashMap.get(edgeNode.getTo()).getId(), addressNodeHashMap.get(edgeNode.getFrom()).getId()));

            addressNodeHashMap.get(edgeNode.getFrom()).getBestDist().put(edgeNode.getTo(), edgeNode);
            addressNodeHashMap.get(edgeNode.getTo()).getBestDist().put(edgeNode.getFrom(), reversed);
        }
        return addressNodeHashMap;
    }*/

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
                            newPath.setFullPath(startToMed.getFullPath() + medToEnd.getFullPath());
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

    public List<List<Integer>> parseFullPath(EdgeNode edgeNode) {
        List<List<Integer>> listOfNodes = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        for (String character : edgeNode.getFullPath().split("/")) {
            if ((character == null || character.isEmpty())) {
                if (!temp.isEmpty()) {
                    listOfNodes.add(new ArrayList<>(temp));
                    temp.clear();
                }
            } else {
                temp.add(Integer.parseInt(character));
            }
        }
        System.out.println(listOfNodes);
        return listOfNodes;
    }
}