package org.solvd.model;

import com.opencsv.bean.CsvBindByName;

public class PedestrianNode implements Cloneable {

    @CsvBindByName(column = "ID")
    private Long id;
    @CsvBindByName(column = "From")
    private Long from;
    @CsvBindByName(column = "To")
    private Long to;
    @CsvBindByName(column = "Weight (km)")
    private double weight;

    @CsvBindByName(column = "path")
    private String fullPath;

    @Override
    public PedestrianNode clone() {
        try {
            return (PedestrianNode) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
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
        return String.format("From ID %s to ID %s weight %s", from, to, weight);
    }

    public String toStringBus() {
        return String.format("From ID %s to ID %s weight %s ", from, to, weight);
    }
}
