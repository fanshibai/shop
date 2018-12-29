package com.fan.untils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库连接工具类
 * 提供获取链接对象方法
 */
public class DBUtils {
    private static Connection connection =null;
    private static String className=null;
    private static String url=null;
    private static String username=null;
    private static String password=null;
    static {
        Properties properties=new Properties();
        try {
            properties.load(DBUtils.class.getClassLoader().getResourceAsStream("JDBC.properties"));
            className = properties.getProperty("className");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        try {
            Class.forName(className);
            connection= DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static void closeAll(AutoCloseable ...close){
        for (AutoCloseable able:close) {
            if(able!=null){
                try {
                    able.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
