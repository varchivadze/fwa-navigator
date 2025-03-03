package org.solvd.service;

import org.solvd.database.AddressStore;
import org.solvd.model.Address;
import org.solvd.model.AddressDescription;

public class ValidationService {
    private final AddressStore addressStore;

    public ValidationService(AddressStore addressStore) {
        this.addressStore = addressStore;
    }

    public Address validateAddressInput(AddressDescription addressInput) {
        return addressStore.lookup(
                addressInput.getCountry().trim().toLowerCase(),
                addressInput.getCity().trim().toLowerCase(),
                addressInput.getStreet().trim().toLowerCase(),
                addressInput.getUnit().trim().toLowerCase()
        );
    }

}