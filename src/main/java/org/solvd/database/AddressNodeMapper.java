package org.solvd.database;

import org.solvd.model.AddressNode;
import java.util.List;

public interface AddressNodeMapper {
    void insert(AddressNode address);
    AddressNode findByAddress(AddressNode address);
}