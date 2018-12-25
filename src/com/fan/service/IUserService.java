package com.fan.service;

import com.fan.entity.Page;
import com.fan.entity.User;

import java.util.List;

public interface IUserService {
    public Integer addUser(User user);
    public Integer deleteUser(Integer id);
    public Integer updateUser(User user);
    public List<User> getListUser();
    public User getUserById(Integer id);
    public List<User> getListUser(Integer index,Integer pageSize);
    public Page getPage(String current);
    public Integer deleteBatchUsers(String []ids);
}
