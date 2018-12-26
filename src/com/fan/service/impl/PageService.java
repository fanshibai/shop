package com.fan.service.impl;

import com.fan.dao.IBaseDao;
import com.fan.entity.Page;

public class PageService {
    public Page getPage(String current, IBaseDao baseDao,String servletName) {
        Page page=new Page();
        if (current!=null){
            page.setCurrentPage(Integer.parseInt(current));
        }
        page.setTotalCount(baseDao.getTotalCount());
        page.setList(baseDao.getListObject((page.getCurrentPage()-1)*page.getPageSize(),page.getPageSize()));
        page.setUrl(servletName+"?action=getPage");
        return page;
    }
}
