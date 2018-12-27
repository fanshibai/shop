package com.fan.service.impl;

import com.fan.dao.IGoodsInfoDao;
import com.fan.dao.impl.GoodsInfoDaoImpl;
import com.fan.entity.GoodsInfo;
import com.fan.entity.Page;
import com.fan.service.IGoodsInfoService;

import java.util.List;

public class GoodsInfoServiceImpl implements IGoodsInfoService {
    private IGoodsInfoDao goodsInfoDao=new GoodsInfoDaoImpl();
    @Override
    public Integer addObject(GoodsInfo goodsInfo) {
        return goodsInfoDao.addObject(goodsInfo);
    }

    @Override
    public Integer deleteObject(Integer id) {
        return goodsInfoDao.deleteObject(id);
    }

    @Override
    public Integer updateObject(GoodsInfo goodsInfo) {
        return goodsInfoDao.updateObject(goodsInfo);
    }

    @Override
    public List<GoodsInfo> getListObject() {
        return goodsInfoDao.getListObject();
    }

    @Override
    public GoodsInfo getObjectById(Integer id) {
        return goodsInfoDao.getObjectById(id);
    }

    @Override
    public List<GoodsInfo> getListObject(Integer index, Integer pageSize) {
        return goodsInfoDao.getListObject(index,pageSize);
    }

    @Override
    public Page getPage(String current, String servletName) {
        PageService pageService=new PageService();
        Page page = pageService.getPage(current, goodsInfoDao, servletName);
        return page;
    }

    @Override
    public Integer deleteBatchObjects(String[] ids) {
        return goodsInfoDao.deleteBatchObjects(ids);
    }
}
