package org.solvd.f_v_algo;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import com.opencsv.bean.CsvBindByName;

public class AddressNode {

    @CsvBindByName(column = "ID")
    private Long id;

    @CsvBindByName(column = "Street Address")
    private String street;

    @CsvBindByName(column = "City")
    private String city;

    @CsvBindByName(column = "Country")
    private String country;

    @CsvBindByName(column = "Unit")
    private String unit;

    @CsvBindByName(column = "Latitude")
    private String lat;

    @CsvBindByName(column = "Longitude")
    private String lon;

    private Map<Long, EdgeNode> bestDist = new HashMap<>();

    private Map<Long, EdgeNode> secondBestDist = new HashMap<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public Map<Long, EdgeNode> getBestDist() {
        return bestDist;
    }

    public void setBestDist(Map<Long, EdgeNode> bestDist) {
        this.bestDist = bestDist;
    }

    public Map<Long, EdgeNode> getSecondBestDist() {
        return secondBestDist;
    }

    public void setSecondBestDist(Map<Long, EdgeNode> secondBestDist) {
        this.secondBestDist = secondBestDist;
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, street, city, country, unit);
    }

    @Override
    public boolean equals(Object object){
        if (this == object) {
            return true;
        }

        if (object == null || getClass() == object.getClass()) {
            return false;
        }

        AddressNode addressNode = (AddressNode) object;
        return Objects.equals(addressNode.id, id) &&
                Objects.equals(addressNode.street, street) &&
                Objects.equals(addressNode.city, city) &&
                Objects.equals(addressNode.country, country) &&
                Objects.equals(addressNode.unit, unit);
    }

    @Override
    public String toString() {
        return String.format("Address (id - %s %s %s %s %s %s)", id, country, city, street, unit, bestDist);
    }

    public String shortAddress() {
        return String.format("Address (id - %s %s %s)", id, street, unit);
    }

    public String fullAddress() {
        return String.format("Address (id - %s %s %s %s %s)", id, country, city, street, unit);
    }
}
