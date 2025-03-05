package org.solvd.database.dao;

import org.apache.ibatis.session.SqlSession;
import org.solvd.database.EdgeNodeMapper;
import org.solvd.model.EdgeNode;
import org.solvd.database.AddressStoreMyBatis;

public class EdgeDAO implements EdgeNodeMapper {

    @Override
    public void create(EdgeNode edge) {
        try (SqlSession session = AddressStoreMyBatis.getSession()) {
            EdgeNodeMapper mapper = session.getMapper(EdgeNodeMapper.class);
            mapper.create(edge);
            session.commit();
        }
    }

    @Override
    public EdgeNode read(EdgeNode edge) {
        try (SqlSession session = AddressStoreMyBatis.getSession()) {
            EdgeNodeMapper mapper = session.getMapper(EdgeNodeMapper.class);
            return mapper.read(edge);
        }
    }
}