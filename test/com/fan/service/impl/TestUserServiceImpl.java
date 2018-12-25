package com.fan.service.impl;

import com.fan.entity.Page;
import com.fan.service.IUserService;
import org.junit.Test;

public class TestUserServiceImpl {

    private IUserService userService=new UserServiceImpl();
    @Test
    public void TestGetPage(){
        Page page = userService.getPage("1");
        System.out.println(page);
    }
}
