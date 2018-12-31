package com.fan.service.impl;

import com.fan.dao.IAddressDao;
import com.fan.dao.impl.AddressDaoImpl;
import com.fan.entity.Address;
import com.fan.entity.Page;
import com.fan.service.IAddressService;

import java.util.List;

public class AddressServiceImpl implements IAddressService {
    private IAddressDao addressDao=new AddressDaoImpl();
    @Override
    public List<Address> getAddressList(Integer userId) {
        return addressDao.getAddressList(userId);
    }

    @Override
    public Integer addObject(Address address) {
        return addressDao.addObject(address);
    }

    @Override
    public Integer deleteObject(Integer id) {
        return addressDao.deleteObject(id);
    }

    @Override
    public Integer updateObject(Address address) {
        return null;
    }

    @Override
    public List<Address> getListObject() {
        return null;
    }

    @Override
    public Address getObjectById(Integer id) {
        return null;
    }

    @Override
    public List<Address> getListObject(Integer index, Integer pageSize) {
        return null;
    }

    @Override
    public Page getPage(String current, String servletName) {
        return null;
    }

    @Override
    public Integer deleteBatchObjects(String[] ids) {
        return null;
    }
}
