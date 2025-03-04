package org.solvd.database.dao;

import org.apache.ibatis.session.SqlSession;
import org.solvd.database.AddressNodeMapper;

import org.solvd.model.AddressNode;
import org.solvd.database.AddressStoreMyBatis;

public class AddressDAO implements AddressNodeMapper {

    @Override
    public void insert(AddressNode address) {
        try (SqlSession session = AddressStoreMyBatis.getSession()) {
            AddressNodeMapper mapper = session.getMapper(AddressNodeMapper.class);
            mapper.insert(address);
            session.commit();
        }
    }

    @Override
    public AddressNode findByAddress(AddressNode address) {
        try (SqlSession session = AddressStoreMyBatis.getSession()) {
            AddressNodeMapper mapper = session.getMapper(AddressNodeMapper.class);
            return mapper.findByAddress(address);
        }
    }

}