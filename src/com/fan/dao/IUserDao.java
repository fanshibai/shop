package com.fan.dao;


import com.fan.entity.User;

public interface IUserDao extends IBaseDao<User>{

    User backLogin(String username, String password);
}
