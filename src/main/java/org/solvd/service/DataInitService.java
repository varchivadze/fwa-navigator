package org.solvd.service;

import org.solvd.model.AddressNode;
import org.solvd.model.EdgeNode;
import org.solvd.service.impl.AddressServiceImpl;
import org.solvd.service.impl.EdgeServiceImpl;

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

    /**
     * Inicjalizuje graf na podstawie danych z CSV.
     * Na razie pobieramy dane z CSV – w przyszłości ta metoda będzie pobierać dane z bazy.
     *
     * @return graf jako Map<Long, AddressNode>
     */
    public Map<Long, AddressNode> loadGraphFromDatabase() {
        // Implementacja z kroku 1 – wczytanie CSV i budowa grafu
        ClassLoader classLoader = DataInitService.class.getClassLoader();
        // Pobieramy pliki CSV z zasobów
        java.net.URL mainNodesURL = classLoader.getResource("warsaw_main_nodes.csv");
        java.net.URL edgesURL = classLoader.getResource("warsaw_edges.csv");

        if (mainNodesURL == null || edgesURL == null) {
            throw new RuntimeException("Nie znaleziono plików CSV z danymi!");
        }

        java.io.File csvMain = new java.io.File(mainNodesURL.getFile());
        java.io.File csvEdges = new java.io.File(edgesURL.getFile());

        // Parsujemy dane i budujemy graf
        Map<Long, AddressNode> graph = processor.parseAddressNodes(csvMain, csvEdges);
        processor.FWParser(graph);  // Uruchamiamy algorytm (np. Floyd-Warshall) na grafie

        return graph;
    }

    /**
     * Zapisuje wygenerowany graf do bazy danych.
     * Dla każdego AddressNode wywołuje metodę create() w AddressDAO,
     * a następnie dla każdej krawędzi (EdgeNode) w mapie bestDist wywołuje metodę create() w EdgeDAO.
     *
     * @param graph graf w postaci Map<Long, AddressNode>
     */
    public void saveGraphToDatabase(Map<Long, AddressNode> graph) {
        for (AddressNode node : graph.values()) {
            // Zapisujemy węzeł do bazy
            addressDAO.create(node);
        }
        for (AddressNode node : graph.values()) {
            List<EdgeNode> edgeNodeList = new ArrayList<>();
            edgeNodeList.addAll(node.getBestDist().values());
            edgeDAO.createList(edgeNodeList);


        }
    }
}
