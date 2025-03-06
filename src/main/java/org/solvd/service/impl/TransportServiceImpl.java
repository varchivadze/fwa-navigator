package org.solvd.service.impl;

import org.solvd.database.TransportNodeMapper;
import org.solvd.database.persistence.TransportNodeMapperImpl;
import org.solvd.model.EdgeNode;
import org.solvd.service.TransportService;

import java.util.List;

public class TransportServiceImpl implements TransportService {

    private final TransportNodeMapper transportImpl;

    public TransportServiceImpl() {
        this.transportImpl = new TransportNodeMapperImpl();
    }

    @Override
    public void create(EdgeNode transport) {
        transportImpl.create(transport);
    }

    @Override
    public EdgeNode read(EdgeNode transport) {
        return transportImpl.read(transport);
    }

    public void createList(List<EdgeNode> transport) {
        transportImpl.createList(transport);
    }

    @Override
    public void update(EdgeNode transport) {
        transportImpl.update(transport);
    }

    @Override
    public void delete(EdgeNode transport) {
        transportImpl.delete(transport);
    }

}