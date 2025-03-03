package org.solvd.model;

import com.opencsv.bean.CsvBindByName;

import java.util.List;

public class EdgeNode implements Cloneable {

    private String id;
    private String from;
    private String to;
    private double weight;

    private List<String> fullPath;

    private String busses;

    @Override
    public EdgeNode clone() {
        try {
            return (EdgeNode) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getFullPath() {
        return fullPath;
    }

    public void setFullPath(List<String> fullPath) {
        this.fullPath = fullPath;
    }

    public String getBusses() {
        return busses;
    }

    public void setBusses(String busses) {
        this.busses = busses;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("From ID %s to ID %s weight %s", from, to, weight);
    }

    public String toStringBus() {
        return String.format("From ID %s to ID %s weight %s busses %s", from, to, weight, busses);
    }
}
