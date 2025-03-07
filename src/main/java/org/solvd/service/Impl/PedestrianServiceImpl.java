package org.solvd.service.Impl;

import org.solvd.database.PedestrianNodeMapper;
import org.solvd.database.persistence.PedestrianNodeMapperImpl;
import org.solvd.model.EdgeNode;
import org.solvd.service.PedestrianService;

import java.util.List;

public class PedestrianServiceImpl implements PedestrianService {

    private final PedestrianNodeMapper pedestrianImpl;

    public PedestrianServiceImpl() {
        this.pedestrianImpl = new PedestrianNodeMapperImpl();
    }

    @Override
    public void create(EdgeNode pedestrian) {
        pedestrianImpl.create(pedestrian);
    }

    @Override
    public EdgeNode read(EdgeNode pedestrian) {
        return pedestrianImpl.read(pedestrian);
    }

    public void createList(List<EdgeNode> pedestrian) {
        pedestrianImpl.createList(pedestrian);
    }

    @Override
    public void update(EdgeNode pedestrian) {
        pedestrianImpl.update(pedestrian);
    }

    @Override
    public void delete(EdgeNode pedestrian) {
        pedestrianImpl.delete(pedestrian);
    }

}