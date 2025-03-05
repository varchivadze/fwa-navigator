package org.solvd;

import org.solvd.database.InitDumpCalculation;

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

        InitDumpCalculation.mapNodes();
    }
}