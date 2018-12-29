package com.fan.service;

import com.fan.entity.Address;

import java.util.List;

public interface IAddressService extends IBaseService<Address>{
    List<Address> getAddressList(Integer userId);
}
