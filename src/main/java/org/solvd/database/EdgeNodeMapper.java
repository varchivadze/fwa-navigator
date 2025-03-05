package org.solvd.database;

import org.solvd.model.EdgeNode;

public interface EdgeNodeMapper {
    void create(EdgeNode edge);
    EdgeNode read(EdgeNode edge);
    void update(EdgeNode edgeNode);
    void delete(EdgeNode edgeNode);
}