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

    // Graf danych pobrany z bazy
    public Map<Long, AddressNode> mapMainNodes;

    /**
     * Konstruktor przyjmujący graf danych.
     *
     * @param graph - struktura danych reprezentująca węzły i powiązane krawędzie
     */
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
        // Dynamicznie przeliczamy wagi krawędzi w grafie dla wybranego typu transportu
        adjustGraphWeights(transportType);

        Long startId = findAddressId(startAddress);
        Long destinationId = findAddressId(destinationAddress);

        if (startId == null || destinationId == null) {
            System.out.println("Błąd: Nie znaleziono adresów w bazie!");
            return null;
        }

        EdgeNode bestPath = mapMainNodes.get(startId).getBestDist().get(destinationId);
        if (bestPath == null) {
            System.out.println("Błąd: Brak dostępnej trasy między podanymi adresami.");
            return null;
        }

        // Generujemy przyjazny ciąg trasy dla użytkownika
        String friendlyPath = showPathToUser(bestPath.getFullPath(), startAddress, destinationAddress);

        return new Route(startAddress, destinationAddress, friendlyPath, bestPath.getWeight());
    }

    /**
     * Przelicza wagi wszystkich krawędzi w grafie na podstawie wybranego typu transportu.
     *
     * @param transportType - wybrany typ transportu
     */
    private void adjustGraphWeights(TransportType transportType) {
        for (AddressNode node : mapMainNodes.values()) {
            for (Map.Entry<Long, EdgeNode> entry : node.getBestDist().entrySet()) {
                EdgeNode edge = entry.getValue();
                double baseWeight = edge.getWeight(); // Oryginalna waga
                double adjustedWeight = baseWeight * transportType.getWeightMultiplier();
                edge.setWeight(adjustedWeight);
            }
        }
    }

    /**
     * Wyszukuje identyfikator adresu na podstawie nazwy ulicy.
     *
     * @param address - wprowadzony adres
     * @return identyfikator AddressNode lub null, jeśli adres nie został znaleziony
     */
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

    /**
     * Generuje przyjazną dla użytkownika reprezentację trasy na podstawie surowego ciągu identyfikatorów.
     *
     * @param rawPath            surowy ciąg w formacie "/5/739//739/224//.../1/"
     * @param startAddress       adres początkowy
     * @param destinationAddress adres końcowy
     * @return String z przyjazną reprezentacją trasy, np.: "plac mickiewicza - ulica X - ulica Y - ulica widok"
     */
    /**
     * Generuje przyjazną dla użytkownika reprezentację trasy.
     * Oczekiwany format: "początek - ulica 1 - ulica 2 - ... - koniec"
     *
     * @param rawPath            surowy ciąg identyfikatorów w formacie "/5/739//739/224//.../1/"
     * @param startAddress       adres początkowy (podany przez użytkownika)
     * @param destinationAddress adres końcowy (podany przez użytkownika)
     * @return przyjazna reprezentacja trasy
     */
    private String showPathToUser(String rawPath, String startAddress, String destinationAddress) {
        // Podział surowego ciągu na tokeny
        String[] tokens = rawPath.split("/");
        // Lista, która zachowa kolejność i usunie kolejne duplikaty
        java.util.List<String> streets = new java.util.ArrayList<>();
        for (String token : tokens) {
            if (!token.isEmpty()) {
                try {
                    Long nodeId = Long.parseLong(token);
                    AddressNode node = mapMainNodes.get(nodeId);
                    if (node != null) {
                        String street = node.getStreet();
                        // Dodajemy, jeżeli lista jest pusta lub ostatni dodany element jest inny
                        if (streets.isEmpty() || !streets.get(streets.size() - 1).equalsIgnoreCase(street)) {
                            streets.add(street);
                        }
                    }
                } catch (NumberFormatException e) {
                    // Pomijamy tokeny, które nie są liczbą
                }
            }
        }
        // Budujemy listę wynikową: upewniamy się, że zaczynamy od startAddress i kończymy na destinationAddress
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