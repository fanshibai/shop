package com.fan.dao.impl;

import com.fan.dao.IOrderDetailDao;
import com.fan.entity.OrderDetail;
import com.fan.untils.DBManger;

import java.util.List;

public class OrderDetailDaoImpl implements IOrderDetailDao {

    @Override
    public Integer addObject(OrderDetail orderDetail) {
        String sql="insert into t_order_detail(goods_date,o_orderid,goodsid,goodsname,goodsprice,goods_description,goodsnum,goodspic,goods_total_price) values(?,?,?,?,?,?,?,?,?)";
        return DBManger.commonsUpdate(sql,orderDetail.getGoods_date(),orderDetail.getO_orderid(),orderDetail.getGoodsid(),orderDetail.getGoods_name(),orderDetail.getGoods_price(),orderDetail.getGoods_description(),orderDetail.getCount(),orderDetail.getGoods_pic(),orderDetail.getGoods_total_price());
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
    public Integer getTotalCount() {
        return null;
    }

    @Override
    public List<OrderDetail> getListObject(Integer index, Integer pageSize) {
        return null;
    }

    @Override
    public Integer deleteBatchObjects(String[] ids) {
        return null;
    }
}
