package org.solvd.database;

import org.solvd.model.EdgeNode;

public interface TransportNodeMapper {
    void create(EdgeNode transport);
    EdgeNode read(EdgeNode transport);
    void update(EdgeNode transport);
    void delete(EdgeNode transport);
}