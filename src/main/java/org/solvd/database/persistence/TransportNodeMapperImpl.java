package org.solvd.database.persistence;

import org.apache.ibatis.session.SqlSession;

import org.solvd.database.PedestrianNodeMapper;
import org.solvd.database.TransportNodeMapper;

import org.solvd.database.AddressStoreMyBatis;
import org.solvd.model.EdgeNode;

import java.util.List;

public class TransportNodeMapperImpl implements TransportNodeMapper {

    @Override
    public void create(EdgeNode transport) {
        try (SqlSession session = AddressStoreMyBatis.getSession()) {
            TransportNodeMapper mapper = session.getMapper(TransportNodeMapper.class);
            mapper.create(transport);
            session.commit();
        }
    }

    @Override
    public EdgeNode read(EdgeNode transport) {
        try (SqlSession session = AddressStoreMyBatis.getSession()) {
            TransportNodeMapper mapper = session.getMapper(TransportNodeMapper.class);
            return mapper.read(transport);
        }
    }

    @Override
    public void update(EdgeNode transport) {
        try (SqlSession session = AddressStoreMyBatis.getSession()) {
            TransportNodeMapper mapper = session.getMapper(TransportNodeMapper.class);
            mapper.update(transport);
            session.commit();
        }
    }

    @Override
    public void delete(EdgeNode transport) {
        try (SqlSession session = AddressStoreMyBatis.getSession()) {
            TransportNodeMapper mapper = session.getMapper(TransportNodeMapper.class);
            mapper.delete(transport);
            session.commit();
        }
    }
    @Override
    public void createList(List<EdgeNode> transport) {
        try (SqlSession session = AddressStoreMyBatis.getSession()) {
            TransportNodeMapper mapper = session.getMapper(TransportNodeMapper.class);
            mapper.createList(transport);
            session.commit();
        }
    }

}