package org.solvd.service;

import org.solvd.model.EdgeNode;

import java.util.List;

public interface TransportService {

    void create(TransportNode transport);

    TransportNode read(TransportNode transport);
    void createList(List<TransportNode> TransportNodes);

    void update(TransportNode transport);
    void delete(TransportNode transport);

}