package com.fan.dao.impl;

import com.fan.dao.IUserDao;
import com.fan.entity.User;
import com.fan.untils.DBManger;

import java.util.List;

public class UserDaoImpl implements IUserDao {
    @Override
    public Integer addUser(User user) {
        String sql="insert into user(username,password,phone,email,is_role) values(?,?,?,?,?)";
        return DBManger.commonsUpdate(sql,user.getUsername(),user.getPassword(),user.getPhone(),user.getEmail(),user.getIs_role());
    }

    @Override
    public Integer deleteUser(Integer id) {
        String sql="update user set flag=0 where id=?";
        return DBManger.commonsUpdate(sql,id);
    }

    @Override
    public Integer updateUser(User user) {
        String sql="update user set username=?,password=?,phone=?,email=?,is_role=? where id=?";
        return DBManger.commonsUpdate(sql,user.getUsername(),user.getPassword(),user.getPhone(),user.getEmail(),user.getIs_role(),user.getId());
    }

    @Override
    public List<User> getListUser() {
        String sql="select * from user where flag=1";
        return DBManger.commonsListUser(sql,User.class);
    }

    @Override
    public User getUserById(Integer id) {
        String sql="select * from user where id=?";
        List<User> list= DBManger.commonsListUser(sql, User.class, id);
        if (!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public Integer getTotalCount() {
        String sql="select Count(1) from user where flag=1";
        return DBManger.commonsCount(sql);
    }

    @Override
    public List<User> getListUser(Integer index, Integer pageSize) {
        String sql="select * from user where flag=1 limit ?,?";
        return DBManger.commonsListUser(sql,User.class,index,pageSize);
    }

    @Override
    public Integer deleteBatchUsers(String[] ids) {
        StringBuilder sb=new StringBuilder("update user set flag=0 where id in (");
        for (int i=0;i<ids.length;i++){
            if(i==ids.length-1){
                sb.append("?)");
            }else {
                sb.append("? ,");
            }
        }
        return DBManger.commonsUpdate(sb.toString(),ids);
    }
}
