package org.solvd.database;

import org.solvd.model.Address;
import org.solvd.model.AddressDescription;
import org.solvd.model.IntersectionConnection;

import java.util.List;

public interface AddressStore {

    AddressDescription getById(String addressId);

    Address lookup(String country, String city, String street, String unit);

    List<String> getValidCountries();

    List<String> getValidCities(String country);

    List<String> getValidStreets(String country, String city);

    List<String> getValidUnits(String country, String city, String street);

    List<String> getAllIntersections();

    List<IntersectionConnection> getAllConnections(String transportType);
}