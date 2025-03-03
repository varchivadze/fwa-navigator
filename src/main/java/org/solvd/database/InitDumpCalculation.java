package org.solvd.database;

import org.solvd.model.AddressNode;
import org.solvd.model.EdgeNode;
import org.solvd.service.AlgorithmService;

import java.util.Map;

public class InitDumpCalculation {

    public static final AlgorithmService algorithmService = new AlgorithmService();

    public static void mapNodes() {
        for (Map.Entry<Long, AddressNode> addressNodeMap : algorithmService.mapMainNodes.entrySet()) {
            dumpAddress(addressNodeMap.getValue());
            for (Map.Entry<Long, EdgeNode> edgeNodeEntry : addressNodeMap.getValue().getBestDist().entrySet()) {
                dumpEdgeNode(edgeNodeEntry.getValue());
            }
        }
    }

    private static void dumpAddress(AddressNode addressNode) {
        //
    }

    private static void dumpEdgeNode(EdgeNode edgeNode) {
        //
    }
}
