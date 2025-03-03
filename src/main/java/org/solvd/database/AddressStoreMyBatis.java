package org.solvd.database;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.solvd.model.AddressNode;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class AddressStoreMyBatis implements AddressStore {
    private static SqlSessionFactory sqlSessionFactory;

    public AddressStoreMyBatis() {
        try (Reader reader = org.apache.ibatis.io.Resources.getResourceAsReader("mybatis-config.xml")) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load MyBatis configuration", e);
        }
    }

    public static SqlSession getSession() {
        return sqlSessionFactory.openSession();
    }

    @Override
    public void create(Object address) {

    }

    @Override
    public AddressNode read(Object address) {
        return null;
    }

    @Override
    public void update(Object address) {

    }

    @Override
    public void delete(Object address) {

    }

    @Override
    public AddressNode lookup(String country, String city, String street, String unit) {
        return null;
    }

    @Override
    public List<String> getValidCountries() {
        return List.of();
    }

    @Override
    public List<String> getValidCities(String country) {
        return List.of();
    }

    @Override
    public List<String> getValidStreets(String country, String city) {
        return List.of();
    }

    @Override
    public List<String> getValidUnits(String country, String city, String street) {
        return List.of();
    }

}