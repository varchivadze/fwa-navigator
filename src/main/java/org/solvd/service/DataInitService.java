package org.solvd.service;

import org.solvd.model.AddressNode;
import org.solvd.model.EdgeNode;
import org.solvd.service.Impl.AddressServiceImpl;
import org.solvd.service.Impl.EdgeServiceImpl;
import org.solvd.service.Impl.PedestrianServiceImpl;
import org.solvd.service.Impl.TransportServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataInitService {

    private final AddressService addressDAO;
    private final  EdgeService edgeDAO;
    private final PathProcessor processor;
    private final PedestrianService pedestrianService;
    private final TransportService transportService;

    public DataInitService() {
        this.addressDAO = new AddressServiceImpl();
        this.edgeDAO = new EdgeServiceImpl();
        this.processor = new PathProcessor();
        pedestrianService = new PedestrianServiceImpl();
        transportService = new TransportServiceImpl();
    }

    public Map<Long, AddressNode> loadGraphFromDatabase(String main, String edges) {

        ClassLoader classLoader = DataInitService.class.getClassLoader();
        java.net.URL mainNodesURL = classLoader.getResource(main);
        java.net.URL edgesURL = classLoader.getResource(edges);

        if (mainNodesURL == null || edgesURL == null) {
            throw new RuntimeException("Couldn't find CSV file!");
        }

        java.io.File csvMain = new java.io.File(mainNodesURL.getFile());
        java.io.File csvEdges = new java.io.File(edgesURL.getFile());

        Map<Long, AddressNode> graph = processor.parseAddressNodes(csvMain, csvEdges);
        processor.FWParser(graph);

        return graph;
    }
    public void saveGraphToDatabase(Map<Long, AddressNode> graph, Boolean dumpMainNodes) {
        if (dumpMainNodes) {
            for (AddressNode node : graph.values()) {

                addressDAO.create(node);
            }
        }
        for (AddressNode node : graph.values()) {
            List<EdgeNode> edgeNodeList = new ArrayList<>();
            edgeNodeList.addAll(node.getBestDist().values());
            edgeDAO.createList(edgeNodeList);

        }
    }

    public void saveGraphToDatabasePed(Map<Long, AddressNode> graph, Boolean dumpMainNodes) {
        if (dumpMainNodes) {
            for (AddressNode node : graph.values()) {

                addressDAO.create(node);
            }
        }
        for (AddressNode node : graph.values()) {
            List<EdgeNode> edgeNodeList = new ArrayList<>();
            edgeNodeList.addAll(node.getBestDist().values());
            pedestrianService.createList(edgeNodeList);

        }
    }

    public void saveGraphToDatabaseTra(Map<Long, AddressNode> graph, Boolean dumpMainNodes) {
        if (dumpMainNodes) {
            for (AddressNode node : graph.values()) {

                addressDAO.create(node);
            }
        }
        for (AddressNode node : graph.values()) {
            List<EdgeNode> edgeNodeList = new ArrayList<>();
            edgeNodeList.addAll(node.getBestDist().values());
            transportService.createList(edgeNodeList);

        }
    }

    public void fullDump() {

        Map<Long, AddressNode> data = loadGraphFromDatabase("warsaw_main_nodes.csv", "warsaw_edges.csv");
        saveGraphToDatabase(data, true);

        Map<Long, AddressNode> dataPed = loadGraphFromDatabase("warsaw_main_nodes.csv", "warsaw_edges_pedestrian.csv");
        saveGraphToDatabasePed(dataPed, false);

        Map<Long, AddressNode> dataTransp = loadGraphFromDatabase("warsaw_main_nodes.csv", "warsaw_edges_transport.csv");
        saveGraphToDatabaseTra(dataTransp, false);
    }
}
