package org.solvd.database;

import org.solvd.model.EdgeNode;
import java.util.List;

public interface EdgeNodeMapper {
    void create(EdgeNode edge);
    EdgeNode read(EdgeNode edge);
}