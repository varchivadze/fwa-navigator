package org.solvd.database;

import org.solvd.model.AddressNode;

public interface AddressNodeMapper {
    void create(AddressNode address);
    AddressNode read(AddressNode address);
    void update(AddressNode address);
    void delete(AddressNode address);
}