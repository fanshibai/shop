package com.fan.untils;

import com.fan.entity.User;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBManger {
    public static Integer commonsUpdate(String sql,Object ...obj){
        Connection connection= DBUtils.getConnection();
        PreparedStatement ps=null;
        int result=0;
        try {
            ps = connection.prepareStatement(sql);
            for(int i=0;i<obj.length;i++){
                ps.setObject(i+1,obj[i]);
            }
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeAll(connection,ps);
        }
        return result;
    }

    public static <T> List<T> commonsListUser(String sql,Class<T> clazz,Object ...obj){
        List<T> list=new ArrayList<T>();
        Connection connection= DBUtils.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        Object value=null;
        String name=null;
        try {
            ps = connection.prepareStatement(sql);
            if(obj!=null){
                for(int i=0;i<obj.length;i++){
                    ps.setObject(i+1,obj[i]);
                }
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                T cls = clazz.newInstance();
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    name = field.getName();
                    value = rs.getObject(name);
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
        }
        return 0;
    }
}

