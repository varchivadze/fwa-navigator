package org.solvd.database;

public interface TransportNodeMapper {
    void create(TransportNode transport);
    TransportNode read(TransportNode transport);
    void update(TransportNode transport);
    void delete(TransportNode transport);
}