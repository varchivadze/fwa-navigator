package org.solvd.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.solvd.model.AddressNode;
import org.solvd.model.EdgeNode;

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

    public Map<Long, AddressNode> readAddressNodesFromCsv(File file) {
        HashMap<Long, AddressNode> addressNodeHashMap = new HashMap<>();
        try {
            FileReader fileReader = new FileReader(file);

            CsvToBean<AddressNode> csvToBean = new CsvToBeanBuilder<AddressNode>(fileReader)
                    .withType(AddressNode.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<AddressNode> addressNodes = csvToBean.parse();


            addressNodes.stream().peek(System.out::println).forEach(node -> addressNodeHashMap.put(node.getId(), node));

        } catch (IOException e) {
            System.out.println("Could not read file " + file.getPath() + e.getMessage());
        }
        return addressNodeHashMap;
    }

    public Map<Long, AddressNode> addEdgesToAddressNodes(Map<Long, AddressNode> addressNodes, File fileEdges) {
        try {
            FileReader fileReader = new FileReader(fileEdges, StandardCharsets.UTF_8);

            CsvToBean<EdgeNode> csvToBean = new CsvToBeanBuilder<EdgeNode>(fileReader)
                    .withType(EdgeNode.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<EdgeNode> edgeNodes = csvToBean.parse();
            for (EdgeNode edgeNode : edgeNodes) {
                edgeNode.setFullPath(String.format("/%s/%s/", addressNodes.get(edgeNode.getFrom()).getId(), addressNodes.get(edgeNode.getTo()).getId()));
                EdgeNode reversed = edgeNode.clone();
                reversed.setFrom(edgeNode.getTo());
                reversed.setTo(edgeNode.getFrom());
                reversed.setFullPath(String.format("/%s/%s/", addressNodes.get(edgeNode.getTo()).getId(), addressNodes.get(edgeNode.getFrom()).getId()));

                addressNodes.get(edgeNode.getFrom()).getBestDist().put(edgeNode.getTo(), edgeNode);
                addressNodes.get(edgeNode.getTo()).getBestDist().put(edgeNode.getFrom(), reversed);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return addressNodes;

    }

    public Map<Long, AddressNode> parseAddressNodes(File mainNodes, File edgeNodes) {
        return addEdgesToAddressNodes(readAddressNodesFromCsv(mainNodes), edgeNodes);
    }

    public Map<Long, AddressNode> addEdgesToAddressNodes(List<AddressNode> addressNodes, List<EdgeNode> edgeNodes) {
        HashMap<Long, AddressNode> addressNodeHashMap = new HashMap<>();
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
    }

    public void FWParser(Map<Long, AddressNode> addressNodes) {
        for (Map.Entry<Long, AddressNode> med : addressNodes.entrySet()) {
            for (Map.Entry<Long, AddressNode> start : addressNodes.entrySet()) {
                for (Map.Entry<Long, AddressNode> end : addressNodes.entrySet()) {

                    if (start.getValue().getId().equals(end.getValue().getId())) {
                        EdgeNode newPath = new EdgeNode();
                        newPath.setFrom(start.getValue().getId());
                        newPath.setTo(end.getValue().getId());
                        newPath.setWeight(0);
                        start.getValue().getBestDist().put(end.getValue().getId(), newPath);
                        continue;
                    }

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

    public List<List<Long>> parseFullPath(EdgeNode edgeNode) {
        List<List<Long>> listOfNodes = new ArrayList<>();
        List<Long> temp = new ArrayList<>();
        for (String character : edgeNode.getFullPath().split("/")) {
            if ((character == null || character.isEmpty())) {
                if (!temp.isEmpty()) {
                    listOfNodes.add(new ArrayList<>(temp));
                    temp.clear();
                }
            } else {
                temp.add(Long.parseLong(character));
            }
        }
        if (!temp.isEmpty()) {
            listOfNodes.add(new ArrayList<>(temp));
        }
        System.out.println(listOfNodes);
        return listOfNodes;
    }
}