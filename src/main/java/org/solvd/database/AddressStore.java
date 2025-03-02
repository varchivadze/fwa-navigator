package org.solvd.database;

import org.solvd.model.Address;
import org.solvd.model.AddressDescription;
import org.solvd.model.IntersectionConnection;

import java.util.List;

public interface AddressStore {

    AddressDescription getById(String addressId);

    Address lookup(String county, String city, String street, String unit);

    List<String> getValidCountries();

    List<String> getValidCities(String country);
    //todo city, streets

    List<String> getAllIntersections();

    List<IntersectionConnection> getAllConnections();
}