package org.solvd.model;

public class AddressDescription {
    private final String country;
    private final String city;
    private final String street;
    private final String unit;

    public AddressDescription(String country, String city, String street, String unit) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.unit = unit;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getStreet() {
        return street;
    }

    public String getUnit() {
        return unit;
    }

}