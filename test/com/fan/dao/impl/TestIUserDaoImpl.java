package com.fan.dao.impl;

import com.fan.entity.User;
import org.junit.Test;

import java.util.List;

public class TestIUserDaoImpl {
    private UserDaoImpl userDao=new UserDaoImpl();
    @Test
    public void TestAddUser(){
        int result=userDao.addObject(new User("zhaowu","123","123456","456qq.com",1));
        System.out.println(result);
    }
    @Test
    public void TestDeleteUser(){
        int result=userDao.deleteObject(1);
        System.out.println(result);
    }
    @Test
    public void TestUpdateUser(){
        int result=userDao.updateObject(new User(1,"ls","admin","123456","456qq.com",1));
        System.out.println(result);
    }
    @Test
    public void TestGetUserById(){
        User user = userDao.getObjectById(1);
        System.out.println(user);
    }
    @Test
    public void TestGetListUser(){
        List<User> list= userDao.getListObject();
        System.out.println(list);
    }
    @Test
    public void TestGetTotalCount(){
        Integer totalCount = userDao.getTotalCount();
        System.out.println(totalCount);

    }
}
