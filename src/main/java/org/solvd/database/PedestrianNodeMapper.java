package org.solvd.database;

import org.solvd.model.EdgeNode;

public interface PedestrianNodeMapper {
    void create(EdgeNode pedestrian);
    EdgeNode read(EdgeNode pedestrian);
    void update(EdgeNode pedestrian);
    void delete(EdgeNode pedestrian);
}