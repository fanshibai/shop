package com.fan.dao.impl;

import com.fan.dao.IGoodsInfoDao;
import com.fan.entity.GoodsInfo;
import com.fan.untils.DBManger;

import java.util.List;

public class GoodsInfoDaoImpl implements IGoodsInfoDao {
    String sql=null;
    @Override
    public Integer addObject(GoodsInfo goodsInfo) {
        sql="insert into t_goods_info(goods_name,goods_parentid,goods_fatherid,goods_pic,goods_price,goods_description) values(?,?,?,?,?,?)";
        return DBManger.commonsUpdate(sql,goodsInfo.getGoods_name(),goodsInfo.getGoods_parentid(),goodsInfo.getGoods_fatherid(),goodsInfo.getGoods_pic(),goodsInfo.getGoods_price(),goodsInfo.getGoods_description());
    }

    @Override
    public Integer deleteObject(Integer id) {
        sql="update t_goods_info set flag=0 where id=?";
        return DBManger.commonsUpdate(sql,id);
    }

    @Override
    public Integer updateObject(GoodsInfo goodsInfo) {
        sql="update t_goods_info set goods_name=?,goods_parentid=?,goods_fatherid=?,goods_pic=?,goods_price=?,goods_description=? where id=?";
        return DBManger.commonsUpdate(sql,goodsInfo.getGoods_name(),goodsInfo.getGoods_parentid(),goodsInfo.getGoods_fatherid(),goodsInfo.getGoods_pic(),goodsInfo.getGoods_price(),goodsInfo.getGoods_description(),goodsInfo.getId());
    }

    @Override
    public List<GoodsInfo> getListObject() {
        sql="select * from t_goods_info where flag=1";
        return DBManger.commonsListUser(sql, GoodsInfo.class);
    }

    @Override
    public GoodsInfo getObjectById(Integer id) {
        sql="select * from t_goods_info where id=?";
        List<GoodsInfo> list = DBManger.commonsListUser(sql, GoodsInfo.class, id);
        if (!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public Integer getTotalCount() {
        sql="select Count(1) from t_goods_info where flag=1";
        return DBManger.commonsCount(sql);
    }

    @Override
    public List<GoodsInfo> getListObject(Integer index, Integer pageSize) {
        sql="select * from t_goods_info where flag=1 limit ?,?";
        return DBManger.commonsListUser(sql,GoodsInfo.class,index,pageSize);
    }

    @Override
    public Integer deleteBatchObjects(String[] ids) {
        StringBuilder sb=new StringBuilder("update t_goods_info set flag=0 where id in (");
        for(int i =0;i<ids.length;i++){
            if (i!=ids.length-1){
                sb.append("?,");
            }else {
                sb.append("?)");
            }
        }
        return DBManger.commonsUpdate(sb.toString(),ids);
    }
}
