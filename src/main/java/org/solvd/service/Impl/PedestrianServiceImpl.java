package org.solvd.service.impl;

import org.solvd.database.PedestrianNodeMapper;
import org.solvd.database.TransportNodeMapper;
import org.solvd.database.persistence.PedestrianNodeMapperImpl;
import org.solvd.database.persistence.TransportNodeMapperImpl;
import org.solvd.model.EdgeNode;
import org.solvd.service.PedestrianService;
import org.solvd.service.TransportService;

import java.util.List;

public class PedestrianServiceImpl implements PedestrianService {

    private final PedestrianNodeMapper pedestrianImpl;

    public PedestrianServiceImpl() {
        this.pedestrianImpl = new PedestrianNodeMapperImpl();
    }

    @Override
    public void create(PedestrianNode pedestrian) {
        pedestrianImpl.create(pedestrian);
    }

    @Override
    public PedestrianNode read(PedestrianNode pedestrian) {
        return pedestrianImpl.read(pedestrian);
    }

    public void createList(List<PedestrianNode> pedestrian) {
        pedestrianImpl.createList(pedestrian);
    }

    @Override
    public void update(PedestrianNode pedestrian) {
        pedestrianImpl.update(pedestrian);
    }

    @Override
    public void delete(PedestrianNode pedestrian) {
        pedestrianImpl.delete(pedestrian);
    }

}