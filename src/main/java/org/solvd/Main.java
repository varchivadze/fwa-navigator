package org.solvd;

import org.solvd.database.AddressStoreMyBatis;
import org.solvd.database.InitDumpCalculation;
import org.solvd.database.dao.AddressDAO;
import org.solvd.database.dao.EdgeDAO;
import org.solvd.model.AddressNode;
import org.solvd.model.EdgeNode;
import org.solvd.service.AlgorithmService;
import org.solvd.service.PathProcessor;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

//        PathProcessor processor = new PathProcessor();
//
//        ClassLoader classLoader = Main.class.getClassLoader();
//        URL mainNodes = classLoader.getResource("warsaw_main_nodes.csv");
//        URL edges = classLoader.getResource("warsaw_edges.csv");
//
//        Map<Long, AddressNode> mapMainNodes = null;
//        if (mainNodes != null && edges != null) {
//            File csvMain = new File(mainNodes.getFile());
//            File csvEdges = new File(edges.getFile());
//            mapMainNodes = processor.parseAddressNodes(csvMain, csvEdges);
//        }
////        System.out.println(mapMainNodes);
//        System.out.println(mapMainNodes.get(1L));
//        System.out.println(mapMainNodes.get(1L).getBestDist().get(5L));
//        System.out.println(mapMainNodes.get(5L).getBestDist().get(5L));
//        System.out.println(mapMainNodes.get(999L).getBestDist().get(5L));
//        processor.FWParser(mapMainNodes);
//        System.out.println(mapMainNodes.get(1L).getBestDist().get(5L).getFullPath());
//        System.out.println(mapMainNodes.get(1L).getBestDist().get(5L).getWeight());
//        System.out.println(mapMainNodes.get(5L).getBestDist().get(5L).getFullPath());
//        System.out.println(mapMainNodes.get(999L).getBestDist().get(5L).getFullPath());
//        System.out.println(mapMainNodes.get(999L).getBestDist().get(5L).getWeight());
//        List<List<Integer>> parsedPath = processor.parseFullPath(new EdgeNode());

//        InitDumpCalculation.mapNodes();
        AddressStoreMyBatis addressStoreMyBatis = new AddressStoreMyBatis();
        AddressDAO addressDAO = new AddressDAO();

        // Create AddressNode
        AddressNode addressNode = new AddressNode();
        addressNode.setId(21L);
        addressNode.setCountry("t");
        addressNode.setCity("e");
        addressNode.setStreet("s");
        addressNode.setUnit("t");
        addressDAO.create(addressNode);


        AddressNode addressNode2 = new AddressNode();
        addressNode2.setId(22L);
        addressNode2.setCountry("ttes");
        addressNode2.setCity("esss");
        addressNode2.setStreet("sss");
        addressNode2.setUnit("tsss");
        addressDAO.create(addressNode2);

        // Read AddressNode
        AddressNode retrievedAddress = addressDAO.read(addressNode);
        System.out.println("Read Address: " + retrievedAddress);

        // Update AddressNode
        retrievedAddress.setCity("updated");
        addressDAO.update(retrievedAddress);
        System.out.println("Updated Address: " + addressDAO.read(retrievedAddress));

        // Delete AddressNode


        // -------------------------

        EdgeDAO edgeDAO = new EdgeDAO();

        // Create EdgeNode
        EdgeNode edgeNode = new EdgeNode();
        edgeNode.setId(22L);
        edgeNode.setFrom(21L);
        edgeNode.setTo(22L);
        edgeNode.setWeight(12.5);
        edgeDAO.create(edgeNode);

        // Read EdgeNode
        EdgeNode retrievedEdge = edgeDAO.read(edgeNode);
        System.out.println("Read Edge: " + retrievedEdge);

        // Update EdgeNode
        retrievedEdge.setWeight(15.0);
        edgeDAO.update(retrievedEdge);
        System.out.println("Updated Edge: " + edgeDAO.read(retrievedEdge));

//        edgeDAO.delete(edgeNode);
//        System.out.println("Deleted Edge");
//        addressDAO.delete(addressNode);
//        addressDAO.delete(addressNode2);
//        System.out.println("Deleted Address");
    }
}