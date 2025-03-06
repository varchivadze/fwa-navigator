package org.solvd.service;

import org.solvd.model.AddressNode;

public interface AddressService {

    void create(AddressNode address);
    AddressNode readById(Long id);
    AddressNode read(AddressNode address);

    void update(AddressNode address);
    void delete(AddressNode address);

}