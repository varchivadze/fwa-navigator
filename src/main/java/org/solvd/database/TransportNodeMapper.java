package org.solvd.database;

import org.solvd.model.EdgeNode;

import java.util.List;

public interface TransportNodeMapper {
    void create(EdgeNode transport);
    EdgeNode read(EdgeNode transport);
    void update(EdgeNode transport);
    void delete(EdgeNode transport);
    void createList(List<EdgeNode> edgeNodes);
}