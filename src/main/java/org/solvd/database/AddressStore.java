package org.solvd.database;

import org.solvd.model.AddressNode;

import java.util.List;

public interface AddressStore {

    AddressNode lookup(String country, String city, String street, String unit);

    List<String> getValidCountries();

    List<String> getValidCities(String country);

    List<String> getValidStreets(String country, String city);

    List<String> getValidUnits(String country, String city, String street);

    List<String> getAllIntersections();

}