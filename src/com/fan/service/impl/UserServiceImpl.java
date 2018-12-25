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
    public Integer addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public Integer deleteUser(Integer id) {
        return userDao.deleteUser(id);
    }

    @Override
    public Integer updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public List<User> getListUser() {
        return userDao.getListUser();
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getListUser(Integer index, Integer pageSize) {
        return userDao.getListUser(index,pageSize);
    }

    @Override
    public Page getPage(String current) {
        Page page=new Page();
        if (current!=null){
            page.setCurrentPage(Integer.parseInt(current));
        }
        page.setTotalCount(userDao.getTotalCount());
        page.setList(userDao.getListUser((page.getCurrentPage()-1)*page.getPageSize(),page.getPageSize()));
        page.setUrl("UserServlet?action=getPage");
        return page;
    }

    @Override
    public Integer deleteBatchUsers(String[] ids) {
        return userDao.deleteBatchUsers(ids);
    }
}
