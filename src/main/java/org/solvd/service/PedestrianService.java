package org.solvd.service;

import org.solvd.model.EdgeNode;

import java.util.List;

public interface PedestrianService {

    void create(PedestrianNode pedestrian);

    PedestrianNode read(PedestrianNode pedestrian);
    void createList(List<PedestrianNode> PedestrianNodes);

    void update(PedestrianNode pedestrian);
    void delete(PedestrianNode pedestrian);

}