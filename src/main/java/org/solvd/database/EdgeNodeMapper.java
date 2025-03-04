package org.solvd.database;

import org.solvd.model.EdgeNode;
import java.util.List;

public interface EdgeNodeMapper {
    void insert(EdgeNode edge);
    EdgeNode findByEdge(Long id);
}