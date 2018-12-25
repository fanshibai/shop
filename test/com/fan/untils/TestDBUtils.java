package com.fan.untils;

import org.junit.Test;

import java.sql.Connection;

public class TestDBUtils {
    @Test
    public void TestGetConnection(){
        Connection connection=DBUtils.getConnection();
        System.out.println(connection);
    }
}
