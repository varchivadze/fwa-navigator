package org.solvd.database;

import org.solvd.model.EdgeNode;

import java.util.List;

public interface PedestrianNodeMapper {
    void create(EdgeNode pedestrian);
    EdgeNode read(EdgeNode pedestrian);
    void update(EdgeNode pedestrian);
    void delete(EdgeNode pedestrian);
    void createList(List<EdgeNode> edgeNodes);
}