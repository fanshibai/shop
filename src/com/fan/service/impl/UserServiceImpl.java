package com.fan.service.impl;

import com.fan.dao.IUserDao;
import com.fan.dao.impl.UserDaoImpl;
import com.fan.entity.Page;
import com.fan.entity.User;
import com.fan.service.IUserService;

import java.util.List;

public class UserServiceImpl implements IUserService {
    private IUserDao userDao=new UserDaoImpl();

    @Override
    public Integer addObject(User user) {
        return userDao.addObject(user);
    }

    @Override
    public Integer deleteObject(Integer id) {
        return userDao.deleteObject(id);
    }

    @Override
    public Integer updateObject(User user) {
        return userDao.updateObject(user);
    }

    @Override
    public List<User> getListObject() {
        return userDao.getListObject();
    }

    @Override
    public User getObjectById(Integer id) {
        return userDao.getObjectById(id);
    }

    @Override
    public List<User> getListObject(Integer index, Integer pageSize) {
        return userDao.getListObject(index,pageSize);
    }

    @Override
    public Page getPage(String current,String servletName) {
        PageService pageService = new PageService();
        Page page = pageService.getPage(current, userDao,servletName);
        return page;
    }

    @Override
    public Integer deleteBatchObjects(String[] ids) {
        return userDao.deleteBatchObjects(ids);
    }
}
