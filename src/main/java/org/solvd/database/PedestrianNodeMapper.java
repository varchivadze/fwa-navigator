package org.solvd.database;

public interface PedestrianNodeMapper {
    void create(PedestrianNode pedestrian);
    PedestrianNode read(PedestrianNode pedestrian);
    void update(PedestrianNode pedestrian);
    void delete(PedestrianNode pedestrian);
}