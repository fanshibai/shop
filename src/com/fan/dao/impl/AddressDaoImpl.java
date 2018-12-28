package com.fan.dao.impl;

import com.fan.dao.IAddressDao;
import com.fan.entity.Address;
import com.fan.untils.DBManger;

import java.util.List;

public class AddressDaoImpl implements IAddressDao {
    @Override
    public Integer addObject(Address address) {
        return null;
    }

    @Override
    public Integer deleteObject(Integer id) {
        return null;
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
    public Integer getTotalCount() {
        return null;
    }

    @Override
    public List<Address> getListObject(Integer index, Integer pageSize) {
        return null;
    }

    @Override
    public Integer deleteBatchObjects(String[] ids) {
        return null;
    }

    @Override
    public List<Address> getAddressList(Integer userId) {
        String sql="select * from t_address where userid=?";
        return DBManger.commonsListUser(sql,Address.class,userId);
    }
}
