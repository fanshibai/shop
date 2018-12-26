package com.fan.dao;


import java.util.List;

public interface IBaseDao<T> {
    public Integer addObject(T t);
    public Integer deleteObject(Integer id);
    public Integer updateObject(T t);
    public List<T> getListObject();
    public T getObjectById(Integer id);
    public Integer getTotalCount();
    public List<T> getListObject(Integer index,Integer pageSize);
    public Integer deleteBatchObjects(String[] ids);
}
