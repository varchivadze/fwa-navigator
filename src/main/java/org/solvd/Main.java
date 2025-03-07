package org.solvd;

import org.solvd.model.AddressNode;
import org.solvd.model.EdgeNode;
import org.solvd.service.*;
import org.solvd.service.Impl.AddressServiceImpl;
import org.solvd.service.Impl.PedestrianServiceImpl;


import java.util.ArrayList;
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
//        AddressStoreMyBatis addressStoreMyBatis = new AddressStoreMyBatis();
//        AddressService addressDAO = new AddressServiceImpl();
//

//        AddressService addressService = new AddressServiceImpl();
//        AddressNode addressNode = new AddressNode();
//        addressNode.setId(21L);
//        addressNode.setCountry("t");
//        addressNode.setCity("e");
//        addressNode.setStreet("s");
//        addressNode.setUnit("t");
//        addressService.create(addressNode);
//
//
//        AddressNode addressNode2 = new AddressNode();
//        addressNode2.setId(22L);
//        addressNode2.setCountry("ttes");
//        addressNode2.setCity("esss");
//        addressNode2.setStreet("sss");
//        addressNode2.setUnit("tsss");
//        addressDAO.create(addressNode2);
//

//        AddressNode retrievedAddress = addressDAO.read(addressNode);

//

//        retrievedAddress.setCity("updated");
//        addressDAO.update(retrievedAddress);

//
//
//        PedestrianServiceImpl edgeDAO = new PedestrianServiceImpl();
//

//        EdgeNode edgeNode = new EdgeNode();
//        edgeNode.setId(22L);
//        edgeNode.setFrom(21L);
//        edgeNode.setTo(22L);
//        edgeNode.setWeight(12.5);
//        EdgeNode edgeNode2 = new EdgeNode();
//        edgeNode2.setId(22L);
//        edgeNode2.setFrom(21L);
//        edgeNode2.setTo(22L);
//        edgeNode2.setWeight(12.5);
//        List<EdgeNode> list = new ArrayList<>();
//        list.add(edgeNode);
//        list.add(edgeNode2);
//        edgeDAO.createList(list);
//

//        EdgeNode retrievedEdge = edgeDAO.read(edgeNode);

//

//        retrievedEdge.setWeight(15.0);
//        edgeDAO.update(retrievedEdge);


//        edgeDAO.delete(edgeNode);
//        System.out.println("Deleted Edge");
//        addressDAO.delete(addressNode);
//        addressDAO.delete(addressNode2);



        DataInitService dataInitService = new DataInitService();
        dataInitService.fullDump();

    }
}