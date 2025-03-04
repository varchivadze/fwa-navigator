package org.solvd.database.dao;

import org.apache.ibatis.session.SqlSession;
import org.solvd.database.AddressNodeMapper;

import org.solvd.model.AddressNode;
import org.solvd.database.AddressStoreMyBatis;

public class AddressDAO implements AddressNodeMapper {

    @Override
    public void create(AddressNode address) {
        try (SqlSession session = AddressStoreMyBatis.getSession()) {
            AddressNodeMapper mapper = session.getMapper(AddressNodeMapper.class);
            mapper.create(address);
            session.commit();
        }
    }

    @Override
    public AddressNode read(AddressNode address) {
        try (SqlSession session = AddressStoreMyBatis.getSession()) {
            AddressNodeMapper mapper = session.getMapper(AddressNodeMapper.class);
            return mapper.read(address);
        }
    }

}