package com.fan.service;


import com.fan.entity.User;

public interface IUserService extends IBaseService<User>{

    User backLogin(String username, String password);
}
