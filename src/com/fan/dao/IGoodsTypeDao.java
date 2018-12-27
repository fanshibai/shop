package com.fan.dao;

import com.fan.entity.GoodsType;

import java.util.List;

public interface IGoodsTypeDao extends IBaseDao<GoodsType> {
    List<GoodsType> getGoodsTypeByParentIdList(Integer parent_id);
}
