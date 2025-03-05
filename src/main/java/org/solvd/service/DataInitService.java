package org.solvd.service;

import org.solvd.database.dao.AddressDAO;
import org.solvd.database.dao.EdgeDAO;
import org.solvd.model.AddressNode;

import java.io.File;
import java.net.URL;
import java.util.Map;

public class DataInitService {

    private AddressDAO addressDAO;
    private EdgeDAO edgeDAO;
    private PathProcessor processor;
    private Map<Long, AddressNode> graph;

    public DataInitService() {
        this.addressDAO = new AddressDAO();
        this.edgeDAO = new EdgeDAO();
        this.processor = new PathProcessor();
    }

    public void migrateDataIfNecessary() {

        ClassLoader classLoader = DataInitService.class.getClassLoader();
        URL mainNodesURL = classLoader.getResource("warsaw_main_nodes.csv");
        URL edgesURL = classLoader.getResource("warsaw_edges.csv");

        if (mainNodesURL == null || edgesURL == null) {
            throw new RuntimeException("no CSV file for data migration!");
        }

        File csvMain = new File(mainNodesURL.getFile());
        File csvEdges = new File(edgesURL.getFile());

        Map<Long, AddressNode> addressNodes = processor.parseAddressNodes(csvMain, csvEdges);

        for (AddressNode addressNode : addressNodes.values()) {
            addressDAO.create(addressNode);
        }

    }


    // TODO: changing impl to get data from db using DAO.
    public Map<Long, AddressNode> loadGraphFromDatabase() {
        // TODO: checking if data exists in DB
        migrateDataIfNecessary();

        // TODO: changing to DB
        ClassLoader classLoader = DataInitService.class.getClassLoader();
        URL mainNodesURL = classLoader.getResource("warsaw_main_nodes.csv");
        URL edgesURL = classLoader.getResource("warsaw_edges.csv");

        File csvMain = new File(mainNodesURL.getFile());
        File csvEdges = new File(edgesURL.getFile());
        this.graph = processor.parseAddressNodes(csvMain, csvEdges);
        processor.FWParser(graph);

        return graph;
    }
}

