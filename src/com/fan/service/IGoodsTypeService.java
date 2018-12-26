package com.fan.service;

import com.fan.entity.GoodsType;

import java.util.List;

public interface IGoodsTypeService extends IBaseService<GoodsType> {
    List<GoodsType> getGoodsTypeByParentIdList();
}
