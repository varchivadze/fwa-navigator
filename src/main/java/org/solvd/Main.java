package org.solvd;

import org.solvd.database.AddressStoreMyBatis;
import org.solvd.database.InitDumpCalculation;
import org.solvd.database.dao.AddressDAO;
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
        AddressNode addressNode = new AddressNode();
        addressNode.setId(2L);
        addressNode.setCountry("t");
        addressNode.setCity("e");
        addressNode.setStreet("s");
        addressNode.setUnit("t");
        addressDAO.create(addressNode);
    }
}