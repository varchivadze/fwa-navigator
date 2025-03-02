package org.solvd.model;

import com.opencsv.bean.CsvBindByName;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AddressNode {

    private final String id;

    public AddressNode(String id) {
        this.id = id;
    }

    private Map<String, EdgeNode> bestDist = new HashMap<>();

    private Map<String, EdgeNode> secondBestDist = new HashMap<>();

    public String getId() {
        return id;
    }

    public Map<String, EdgeNode> getBestDist() {
        return bestDist;
    }

    public void setBestDist(Map<String, EdgeNode> bestDist) {
        this.bestDist = bestDist;
    }

    public Map<String, EdgeNode> getSecondBestDist() {
        return secondBestDist;
    }

    public void setSecondBestDist(Map<String, EdgeNode> secondBestDist) {
        this.secondBestDist = secondBestDist;
    }

}
