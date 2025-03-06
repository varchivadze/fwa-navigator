package org.solvd.database.persistence;

import org.apache.ibatis.session.SqlSession;

import org.solvd.database.PedestrianNodeMapper;
import org.solvd.database.TransportNodeMapper;

import org.solvd.database.AddressStoreMyBatis;

public class PedestrianNodeMapperImpl implements PedestrianNodeMapper {

    @Override
    public void create(PedestrianNode pedestrian) {
        try (SqlSession session = AddressStoreMyBatis.getSession()) {
            TransportNodeMapper mapper = session.getMapper(PedestrianNodeMapper.class);
            mapper.create(pedestrian);
            session.commit();
        }
    }

    @Override
    public PedestrianNode read(PedestrianNode pedestrian) {
        try (SqlSession session = AddressStoreMyBatis.getSession()) {
            PedestrianNodeMapper mapper = session.getMapper(PedestrianNodeMapper.class);
            return mapper.read(pedestrian);
        }
    }

    @Override
    public void update(PedestrianNode pedestrian) {
        try (SqlSession session = AddressStoreMyBatis.getSession()) {
            TransportNodeMapper mapper = session.getMapper(PedestrianNodeMapper.class);
            mapper.update(pedestrian);
            session.commit();
        }
    }

    @Override
    public void delete(PedestrianNode pedestrian) {
        try (SqlSession session = AddressStoreMyBatis.getSession()) {
            TransportNodeMapper mapper = session.getMapper(PedestrianNodeMapper.class);
            mapper.delete(pedestrian);
            session.commit();
        }
    }

}