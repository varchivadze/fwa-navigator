package org.solvd.service;

import org.solvd.model.EdgeNode;

public interface EdgeService {

    void create(EdgeNode edge);

    EdgeNode read(EdgeNode edge);

    void update(EdgeNode edgeNode);
    void delete(EdgeNode edgeNode);

}