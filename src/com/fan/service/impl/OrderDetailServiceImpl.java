package com.fan.service.impl;

import com.fan.dao.IOrderDetailDao;
import com.fan.dao.impl.OrderDetailDaoImpl;
import com.fan.entity.OrderDetail;
import com.fan.entity.Page;
import com.fan.service.IOrderDetailService;

import java.util.List;

public class OrderDetailServiceImpl implements IOrderDetailService {
    private IOrderDetailDao orderDetailDao=new OrderDetailDaoImpl();
    @Override
    public Integer addObject(OrderDetail orderDetail) {
        return orderDetailDao.addObject(orderDetail);
    }

    @Override
    public Integer deleteObject(Integer id) {
        return null;
    }

    @Override
    public Integer updateObject(OrderDetail orderDetail) {
        return null;
    }

    @Override
    public List<OrderDetail> getListObject() {
        return null;
    }

    @Override
    public OrderDetail getObjectById(Integer id) {
        return null;
    }

    @Override
    public List<OrderDetail> getListObject(Integer index, Integer pageSize) {
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
