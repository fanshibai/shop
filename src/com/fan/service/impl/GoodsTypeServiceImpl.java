package com.fan.service.impl;

import com.fan.dao.IGoodsTypeDao;
import com.fan.dao.impl.GoodsTypeDaoImpl;
import com.fan.entity.GoodsType;
import com.fan.entity.Page;
import com.fan.service.IGoodsTypeService;

import java.util.List;

public class GoodsTypeServiceImpl implements IGoodsTypeService {
    private IGoodsTypeDao goodsTypeDao=new GoodsTypeDaoImpl();

    @Override
    public Integer addObject(GoodsType goodsType) {
        return goodsTypeDao.addObject(goodsType);
    }

    @Override
    public Integer deleteObject(Integer id) {
        return goodsTypeDao.deleteObject(id);
    }

    @Override
    public Integer updateObject(GoodsType goodsType) {
        return goodsTypeDao.updateObject(goodsType);
    }

    @Override
    public List<GoodsType> getListObject() {
        return goodsTypeDao.getListObject();
    }

    @Override
    public GoodsType getObjectById(Integer id) {
        return goodsTypeDao.getObjectById(id);
    }

    @Override
    public List<GoodsType> getListObject(Integer index, Integer pageSize) {
        return goodsTypeDao.getListObject();
    }

    @Override
    public Page getPage(String current) {
        Page page=new Page();
        if (current!=null){
            page.setCurrentPage(Integer.parseInt(current));
        }
        page.setTotalCount(goodsTypeDao.getTotalCount());
        page.setList(goodsTypeDao.getListObject((page.getCurrentPage()-1)*page.getPageSize(),page.getPageSize()));
        page.setUrl("GoodsTypeServlet?action=getPage");
        return page;
    }

    @Override
    public Integer deleteBatchObjects(String[] ids) {
        return goodsTypeDao.deleteBatchObjects(ids);
    }

    @Override
    public List<GoodsType> getGoodsTypeByParentIdList() {
        return goodsTypeDao.getGoodsTypeByParentIdList();
    }
}
