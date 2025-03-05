package org.solvd.database;

import org.solvd.model.EdgeNode;

import java.util.List;

public interface EdgeNodeMapper {
    void create(EdgeNode edge);

    void createList(List<EdgeNode> edgeNodes);

    EdgeNode read(EdgeNode edge);

    void update(EdgeNode edgeNode);

    void delete(EdgeNode edgeNode);
}