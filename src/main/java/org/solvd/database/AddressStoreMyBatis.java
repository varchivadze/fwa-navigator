package org.solvd.database;

import org.solvd.model.Address;
import org.solvd.model.AddressDescription;
import org.solvd.model.IntersectionConnection;

import java.util.List;

public class AddressStoreMyBatis implements AddressStore {


    @Override
    public AddressDescription getById(String addressId) {
        return null;
    }

    @Override
    public Address lookup(String country, String city, String street, String unit) {
        return null;
    }

    @Override
    public List<String> getValidCountries() {
        //todo
        return List.of();
    }

    @Override
    public List<String> getValidCities(String country) {
        return List.of();
    }

    @Override
    public List<String> getValidStreets(String country, String city) {
        return List.of();
    }

    @Override
    public List<String> getValidUnits(String country, String city, String street) {
        return List.of();
    }

    @Override
    public List<String> getAllIntersections() {
        return List.of();
    }

    @Override
    public List<IntersectionConnection> getAllConnections(String transportType) {
        return List.of();
    }
}
