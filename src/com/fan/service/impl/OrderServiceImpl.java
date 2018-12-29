package com.fan.service.impl;

import com.fan.dao.IOrderDao;
import com.fan.dao.impl.OrderDaoImpl;
import com.fan.entity.Order;
import com.fan.entity.Page;
import com.fan.service.IOrderService;

import java.util.List;

public class OrderServiceImpl implements IOrderService {
    IOrderDao orderDao=new OrderDaoImpl();
    @Override
    public Integer addObject(Order order) {
        return orderDao.addObject(order);
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
    public List<Order> getListObject(Integer index, Integer pageSize) {
        return null;
    }

    @Override
    public Page getPage(String current, String servletName) {
        return null;
    }

    @Override
    public Integer deleteBatchObjects(String[] ids) {
        return null;
    }
}
