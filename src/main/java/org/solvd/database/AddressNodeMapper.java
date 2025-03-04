package org.solvd.database;

import org.solvd.model.AddressNode;
import java.util.List;

public interface AddressNodeMapper {
    void create(AddressNode address);
    AddressNode read(AddressNode address);
}