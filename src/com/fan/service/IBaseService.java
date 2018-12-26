package com.fan.service;

import com.fan.entity.Page;

import java.util.List;

public interface IBaseService<T> {
    public Integer addObject(T t);
    public Integer deleteObject(Integer id);
    public Integer updateObject(T t);
    public List<T> getListObject();
    public T getObjectById(Integer id);
    public List<T> getListObject(Integer index,Integer pageSize);
    public Page getPage(String current,String servletName);
    public Integer deleteBatchObjects(String []ids);
}
