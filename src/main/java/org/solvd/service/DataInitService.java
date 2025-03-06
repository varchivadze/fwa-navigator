package org.solvd.service;

import org.solvd.model.AddressNode;
import org.solvd.model.EdgeNode;
import org.solvd.service.Impl.AddressServiceImpl;
import org.solvd.service.Impl.EdgeServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataInitService {

    private AddressService addressDAO;
    private EdgeService edgeDAO;
    private PathProcessor processor;

    public DataInitService() {
        this.addressDAO = new AddressServiceImpl();
        this.edgeDAO = new EdgeServiceImpl();
        this.processor = new PathProcessor();
    }

    public Map<Long, AddressNode> loadGraphFromDatabase() {

        ClassLoader classLoader = DataInitService.class.getClassLoader();
        java.net.URL mainNodesURL = classLoader.getResource("warsaw_main_nodes.csv");
        java.net.URL edgesURL = classLoader.getResource("warsaw_edges.csv");

        if (mainNodesURL == null || edgesURL == null) {
            throw new RuntimeException("Couldn't find CSV file!");
        }

        java.io.File csvMain = new java.io.File(mainNodesURL.getFile());
        java.io.File csvEdges = new java.io.File(edgesURL.getFile());

        Map<Long, AddressNode> graph = processor.parseAddressNodes(csvMain, csvEdges);
        processor.FWParser(graph);

        return graph;
    }
    public void saveGraphToDatabase(Map<Long, AddressNode> graph) {
        for (AddressNode node : graph.values()) {

            addressDAO.create(node);
        }
        for (AddressNode node : graph.values()) {
            List<EdgeNode> edgeNodeList = new ArrayList<>();
            edgeNodeList.addAll(node.getBestDist().values());
            edgeDAO.createList(edgeNodeList);

        }
    }
}
