package org.solvd.service.impl;

import org.solvd.database.TransportNodeMapper;
import org.solvd.database.persistence.TransportNodeMapperImpl;
import org.solvd.service.TransportService;

import java.util.List;

public class TransportServiceImpl implements TransportService {

    private final TransportNodeMapper transportImpl;

    public TransportServiceImpl() {
        this.transportImpl = new TransportNodeMapperImpl();
    }

    @Override
    public void create(TransportNode transport) {
        transportImpl.create(transport);
    }

    @Override
    public TransportNode read(TransportNode transport) {
        return transportImpl.read(transport);
    }

    public void createList(List<TransportNode> transport) {
        transportImpl.createList(transport);
    }

    @Override
    public void update(TransportNode transport) {
        transportImpl.update(transport);
    }

    @Override
    public void delete(TransportNode transport) {
        transportImpl.delete(transport);
    }

}