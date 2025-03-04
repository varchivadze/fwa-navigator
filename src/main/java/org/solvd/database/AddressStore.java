package org.solvd.database;

import org.solvd.model.AddressNode;

public interface AddressStore<T> {

    void create(T address);

    AddressNode read(T address);

    void update(T edgeNode);

    void delete(T edgeNode);

}
