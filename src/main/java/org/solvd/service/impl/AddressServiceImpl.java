package org.solvd.service.Impl;

import org.solvd.database.AddressNodeMapper;
import org.solvd.database.persistence.AddressNodeMapperImpl;
import org.solvd.model.AddressNode;
import org.solvd.service.AddressService;

public class AddressServiceImpl implements AddressService {

    private final AddressNodeMapper addressesImpl;

    public AddressServiceImpl() {
        this.addressesImpl = new AddressNodeMapperImpl();
    }

    @Override
    public void create(AddressNode address) {
        addressesImpl.create(address);
    }

    @Override
    public AddressNode read(AddressNode address) {
        return addressesImpl.read(address);
    }

    @Override
    public void update(AddressNode address) {
        addressesImpl.update(address);
    }

    @Override
    public void delete(AddressNode address) {
        addressesImpl.delete(address);
    }

}