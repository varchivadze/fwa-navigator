package org.solvd.service.Impl;

import org.solvd.database.EdgeNodeMapper;

import org.solvd.database.persistence.EdgeNodeMapperImpl;

import org.solvd.model.EdgeNode;
import org.solvd.service.EdgeService;

import java.util.List;

public class EdgeServiceImpl implements EdgeService {

        private final EdgeNodeMapper edgesImpl;

        public EdgeServiceImpl() {
                this.edgesImpl = new EdgeNodeMapperImpl();
        }

        @Override
        public void create(EdgeNode edge) {
                edgesImpl.create(edge);
        }

        public void createList(List<EdgeNode> edges) {
                edgesImpl.createList(edges);
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