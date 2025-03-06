package org.solvd.database.persistence;

import org.apache.ibatis.session.SqlSession;

import org.solvd.database.TransportNodeMapper;

import org.solvd.database.AddressStoreMyBatis;

public class TransportNodeMapperImpl implements TransportNodeMapper {

    @Override
    public void create(TransportNode transport) {
        try (SqlSession session = AddressStoreMyBatis.getSession()) {
            TransportNodeMapper mapper = session.getMapper(TransportNodeMapper.class);
            mapper.create(transport);
            session.commit();
        }
    }

    @Override
    public TransportNode read(TransportNode transport) {
        try (SqlSession session = AddressStoreMyBatis.getSession()) {
            TransportNodeMapper mapper = session.getMapper(TransportNodeMapper.class);
            return mapper.read(transport);
        }
    }

    @Override
    public void update(TransportNode transport) {
        try (SqlSession session = AddressStoreMyBatis.getSession()) {
            TransportNodeMapper mapper = session.getMapper(TransportNodeMapper.class);
            mapper.update(transport);
            session.commit();
        }
    }

    @Override
    public void delete(TransportNode transport) {
        try (SqlSession session = AddressStoreMyBatis.getSession()) {
            TransportNodeMapper mapper = session.getMapper(TransportNodeMapper.class);
            mapper.delete(transport);
            session.commit();
        }
    }

}