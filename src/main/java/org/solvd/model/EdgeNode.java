package org.solvd.model;

import com.opencsv.bean.CsvBindByName;

public class EdgeNode implements Cloneable {

    @CsvBindByName(column = "ID")
    private Long id;
    @CsvBindByName(column = "From")
    private Long from;
    @CsvBindByName(column = "To")
    private Long to;
    @CsvBindByName(column = "Weight")
    private double weight;

    private String fullPath;
    private String test;
    private String test2;
    @CsvBindByName(column = "Routes")
    private String busses;

    @Override
    public EdgeNode clone() {
        try {
            return (EdgeNode) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getTest2() {
        return test2;
    }

    public void setTest2(String test2) {
        this.test2 = test2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String getBusses() {
        return busses;
    }

    public void setBusses(String busses) {
        this.busses = busses;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
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
        return String.format("From ID %s to ID %s weight %s %s %s", from, to, weight, fullPath,busses);
    }

    public String toStringBus() {
        return String.format("From ID %s to ID %s weight %s busses %s", from, to, weight, busses);
    }
}
