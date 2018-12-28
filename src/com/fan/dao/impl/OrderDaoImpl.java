package com.fan.dao.impl;

import com.fan.dao.IOrderDao;
import com.fan.entity.Order;
import com.fan.untils.DBManger;

import java.util.List;

public class OrderDaoImpl implements IOrderDao {
    @Override
    public Integer addObject(Order order) {
        String sql="insert into t_order(o_sendtype,o_paytype,o_paycount,userid,o_shperson,o_shphone,o_shaddress) values(?,?,?,?,?,?,?)";
        return DBManger.commonsUpdate(sql,order.getO_sendtype(),order.getO_paytype(),order.getO_paycount(),order.getUserid(),order.getO_shperson(),order.getO_shphone(),order.getO_shaddress());
    }

    @Override
    public Integer deleteObject(Integer id) {
        return null;
    }

    @Override
    public Integer updateObject(Order order) {
        return null;
    }

    @Override
    public List<Order> getListObject() {
        return null;
    }

    @Override
    public Order getObjectById(Integer id) {
        return null;
    }

    @Override
    public Integer getTotalCount() {
        return null;
    }

    @Override
    public List<Order> getListObject(Integer index, Integer pageSize) {
        return null;
    }

    @Override
    public Integer deleteBatchObjects(String[] ids) {
        return null;
    }
}
