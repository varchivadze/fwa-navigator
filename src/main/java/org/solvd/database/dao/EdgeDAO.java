package org.solvd.database.dao;

import org.apache.ibatis.session.SqlSession;
import org.solvd.database.AddressStore;
import org.solvd.database.EdgeNodeMapper;
import org.solvd.model.AddressNode;
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
    public AddressNode read(EdgeNode edge) {
        try (SqlSession session = AddressStoreMyBatis.getSession()) {
            EdgeNodeMapper mapper = session.getMapper(EdgeNodeMapper.class);
            return mapper.read(edge);
        }
    }

    @Override
    public void update(EdgeNode edge) {
        try (SqlSession session = AddressStoreMyBatis.getSession()) {
            EdgeNodeMapper mapper = session.getMapper(EdgeNodeMapper.class);
            mapper.update(edge);
            session.commit();
        }
    }

    @Override
    public void delete(EdgeNode edge) {
        try (SqlSession session = AddressStoreMyBatis.getSession()) {
            EdgeNodeMapper mapper = session.getMapper(EdgeNodeMapper.class);
            mapper.delete(edge);
            session.commit();
        }
    }
}