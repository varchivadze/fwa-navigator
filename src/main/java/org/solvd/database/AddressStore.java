package org.solvd.database;

import org.solvd.model.AddressNode;
import org.solvd.model.EdgeNode;

import java.util.List;

public interface AddressStore<T> {

    void create(T address);

    AddressNode read(T address);

    void update(T address);

    void delete(T address);

    AddressNode lookup(String country, String city, String street, String unit);

    List<String> getValidCountries();

    List<String> getValidCities(String country);

    List<String> getValidStreets(String country, String city);

    List<String> getValidUnits(String country, String city, String street);

}