package org.solvd.service;

import org.solvd.model.EdgeNode;

import java.util.List;

public interface TransportService {

    void create(EdgeNode transport);

    EdgeNode read(EdgeNode transport);
    void createList(List<EdgeNode> transportNodes);

    void update(EdgeNode transport);
    void delete(EdgeNode transport);

}