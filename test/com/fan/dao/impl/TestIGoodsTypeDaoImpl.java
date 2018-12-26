package com.fan.dao.impl;

import com.fan.dao.IGoodsTypeDao;
import com.fan.entity.GoodsType;
import org.junit.Test;

public class TestIGoodsTypeDaoImpl {
    private IGoodsTypeDao goodsTypeDao=new GoodsTypeDaoImpl();
    @Test
    public void TestAddUser(){
        int result=goodsTypeDao.addObject(new GoodsType("水果",1));
        System.out.println(result);
    }
}
