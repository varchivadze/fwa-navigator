package org.solvd.service;

import org.solvd.model.EdgeNode;

import java.util.List;

public interface TransportService {

    void create(EdgeNode transport);

    EdgeNode read(EdgeNode transport);
    void createList(List<EdgeNode> TransportNodes);

    void update(EdgeNode transport);
    void delete(EdgeNode transport);

}