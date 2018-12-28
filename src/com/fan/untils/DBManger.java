package com.fan.untils;


import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBManger {
    /**
     * 通用增删改
     * @param sql   sql语句
     * @param obj  可变参数 给占位符赋值
     * @return  改变行数
     */
    public static Integer commonsUpdate(String sql,Object ...obj){
        Connection connection= DBUtils.getConnection();
        PreparedStatement ps=null;
        int result=0;
        try {
            ps = connection.prepareStatement(sql);
            for(int i=0;i<obj.length;i++){
                ps.setObject(i+1,obj[i]);//给sql语句占位符赋值
            }
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(connection,ps);
        }
        return result;
    }

    /**
     * 通用查询
     * @param sql     sql语句
     * @param clazz  反射对象
     * @param obj    可变参数 给占位符赋值
     * @param <T>  返回对象的类型
     * @return     查询到的对象集合
     */
    public static <T> List<T> commonsListUser(String sql,Class<T> clazz,Object ...obj){
        List<T> list=new ArrayList<T>();
        Connection connection= DBUtils.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        Object value=null;
        String name=null;
        try {
            ps = connection.prepareStatement(sql);
            if(obj!=null){//判断是否为空，有些sql语句没有参数
                for(int i=0;i<obj.length;i++){
                    ps.setObject(i+1,obj[i]);//给sql语句占位符赋值
                }
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                T cls = clazz.newInstance();
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    name = field.getName();
                    try {
                        value = rs.getObject(name);
                    }catch (SQLException e){
                         value=null;
                    }
                    field.set(cls,value);
                }
                list.add(cls);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(ps,connection,rs);
        }
     return list;
    }

    /**
     * 通用查询总记录数
     * @param sql  sql语句
     * @return  总记录数
     */
    public static Integer commonsCount(String sql) {
        Connection connection= DBUtils.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps=connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(connection,ps,rs);
        }
        return 0;
    }
}

