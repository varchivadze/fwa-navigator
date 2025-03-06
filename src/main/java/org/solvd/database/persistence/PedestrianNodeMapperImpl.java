package org.solvd.database.persistence;

import org.apache.ibatis.session.SqlSession;

import org.solvd.database.PedestrianNodeMapper;
import org.solvd.database.TransportNodeMapper;

import org.solvd.database.AddressStoreMyBatis;
import org.solvd.model.EdgeNode;

import java.util.List;

public class PedestrianNodeMapperImpl implements PedestrianNodeMapper {

    @Override
    public void create(EdgeNode pedestrian) {
        try (SqlSession session = AddressStoreMyBatis.getSession()) {
            PedestrianNodeMapper mapper = session.getMapper(PedestrianNodeMapper.class);
            mapper.create(pedestrian);
            session.commit();
        }
    }

    @Override
    public EdgeNode read(EdgeNode pedestrian) {
        try (SqlSession session = AddressStoreMyBatis.getSession()) {
            PedestrianNodeMapper mapper = session.getMapper(PedestrianNodeMapper.class);
            return mapper.read(pedestrian);
        }
    }

    @Override
    public void update(EdgeNode pedestrian) {
        try (SqlSession session = AddressStoreMyBatis.getSession()) {
            PedestrianNodeMapper mapper = session.getMapper(PedestrianNodeMapper.class);
            mapper.update(pedestrian);
            session.commit();
        }
    }

    @Override
    public void delete(EdgeNode pedestrian) {
        try (SqlSession session = AddressStoreMyBatis.getSession()) {
            PedestrianNodeMapper mapper = session.getMapper(PedestrianNodeMapper.class);
            mapper.delete(pedestrian);
            session.commit();
        }
    }
    @Override
    public void createList(List<EdgeNode> pedestrian) {
        try (SqlSession session = AddressStoreMyBatis.getSession()) {
            PedestrianNodeMapper mapper = session.getMapper(PedestrianNodeMapper.class);
            mapper.createList(pedestrian);
            session.commit();
        }
    }

}