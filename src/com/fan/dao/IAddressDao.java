package com.fan.dao;

import com.fan.entity.Address;

import java.util.List;

public interface IAddressDao extends IBaseDao<Address>{
    List<Address> getAddressList(Integer userId);
}
