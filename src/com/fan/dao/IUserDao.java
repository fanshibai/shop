package com.fan.dao;

import com.fan.entity.User;

import java.util.List;

public interface IUserDao {
    public Integer addUser(User user);
    public Integer deleteUser(Integer id);
    public Integer updateUser(User user);
    public List<User> getListUser();
    public User getUserById(Integer id);
    public Integer getTotalCount();
    public List<User> getListUser(Integer index,Integer pageSize);
    public Integer deleteBatchUsers(String[] ids);
}
