package org.solvd.database.dao;

import org.apache.ibatis.session.SqlSession;
import org.solvd.database.AddressStore;
import org.solvd.database.AddressStoreMyBatis;
import org.solvd.model.AddressNode;

public class AddressDAO implements AddressStore<AddressNode> {

    @Override
    public void create(AddressNode address) {
        try (SqlSession session = AddressStoreMyBatis.getSession()) {
            AddressStore mapper = session.getMapper(AddressStore.class);
            mapper.create(address);
            session.commit();
        }
    }

    @Override
    public AddressNode read(AddressNode address) {
        try (SqlSession session = AddressStoreMyBatis.getSession()) {
            AddressStore mapper = session.getMapper(AddressStore.class);
            return mapper.read(address);
        }
    }

    @Override
    public void update(AddressNode address) {
        try (SqlSession session = AddressStoreMyBatis.getSession()) {
            AddressStore mapper = session.getMapper(AddressStore.class);
            mapper.update(address);
            session.commit();
        }
    }

    @Override
    public void delete(AddressNode address) {
        try (SqlSession session = AddressStoreMyBatis.getSession()) {
            AddressStore mapper = session.getMapper(AddressStore.class);
            mapper.delete(address);
            session.commit();
        }
    }
}