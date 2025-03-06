package org.solvd.service;

import org.solvd.model.EdgeNode;

import java.util.List;

public interface PedestrianService {

    void create(EdgeNode pedestrian);

    EdgeNode read(EdgeNode pedestrian);
    void createList(List<EdgeNode> pedestrianNodes);

    void update(EdgeNode pedestrian);
    void delete(EdgeNode pedestrian);

}