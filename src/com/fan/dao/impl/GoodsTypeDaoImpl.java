package com.fan.dao.impl;

import com.fan.dao.IGoodsTypeDao;
import com.fan.entity.GoodsType;
import com.fan.untils.DBManger;

import java.util.List;

public class GoodsTypeDaoImpl implements IGoodsTypeDao {
    String sql=null;
    @Override
    public Integer addObject(GoodsType goodsType) {
        sql="insert into t_goods_type(name,parent_id) values(?,?)";
        return DBManger.commonsUpdate(sql,goodsType.getName(),goodsType.getParent_id());
    }

    @Override
    public Integer deleteObject(Integer id) {
        sql="update t_goods_type set flag=0 where id=?";
        return DBManger.commonsUpdate(sql,id);
    }

    @Override
    public Integer updateObject(GoodsType goodsType) {
        sql = "update t_goods_type set name = ?,parent_id = ? where id = ?";
        return DBManger.commonsUpdate(sql,goodsType.getName(),goodsType.getParent_id(),goodsType.getId());
    }

    @Override
    public List<GoodsType> getListObject() {
        sql="select * from t_goods_type where flag=1";
        return DBManger.commonsListUser(sql,GoodsType.class);
    }

    @Override
    public GoodsType getObjectById(Integer id) {
        sql="select * from t_goods_type where id=?";
        List<GoodsType> list = DBManger.commonsListUser(sql, GoodsType.class, id);
        if (!list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public Integer getTotalCount() {
        sql="select Count(1) from t_goods_type where flag=1";
        return DBManger.commonsCount(sql);
    }

    @Override
    public List<GoodsType> getListObject(Integer index, Integer pageSize) {
        sql="select t1.*,IFNULL(t2.name,'无') parent_name from t_goods_type t1 left join t_goods_type t2 on t1.parent_id= t2.id WHERE t1.flag=1 limit ?,?";
        return DBManger.commonsListUser(sql,GoodsType.class,index,pageSize);
    }

    @Override
    public Integer deleteBatchObjects(String[] ids) {
        StringBuilder sb=new StringBuilder("update t_goods_type set flag=0 where id in (");
        for (int i=0;i<ids.length;i++) {
            if (i==ids.length-1){
                sb.append("?)");
            }else {
                sb.append("?,");
            }
        }
        return DBManger.commonsUpdate(sb.toString(),ids);
    }

    @Override
    public List<GoodsType> getGoodsTypeByParentIdList(Integer parent_id) {
        sql="select id,name from t_goods_type where flag=1 and parent_id=?";
        return DBManger.commonsListUser(sql,GoodsType.class,parent_id);
    }
}
