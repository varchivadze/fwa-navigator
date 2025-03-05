package org.solvd.service.impl;


import org.solvd.database.AddressNodeMapper;
import org.solvd.database.EdgeNodeMapper;
import org.solvd.database.persistence.AddressNodeMapperImpl;
import org.solvd.database.persistence.EdgeNodeMapperImpl;
import org.solvd.model.AddressNode;
import org.solvd.model.EdgeNode;
import org.solvd.service.EdgeService;

public class EdgeServiceImpl implements EdgeService {

        private final EdgeNodeMapper edgesImpl;

        public EdgeServiceImpl() {
                this.edgesImpl = new EdgeNodeMapperImpl();
        }

        @Override
        public void create(EdgeNode edge) {
                edgesImpl.create(edge);
        }

        @Override
        public EdgeNode read(EdgeNode edge) {
                return edgesImpl.read(edge);
        }

        @Override
        public void update(EdgeNode edge) {
                edgesImpl.update(edge);
        }

        @Override
        public void delete(EdgeNode edge) {
                edgesImpl.delete(edge);
        }

}