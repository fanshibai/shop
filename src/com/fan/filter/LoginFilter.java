package com.fan.filter;

import com.fan.entity.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter extends MyFilter{
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req= (HttpServletRequest)request;
        HttpServletResponse resp= (HttpServletResponse)response;
        User user = (User) req.getSession().getAttribute("customer");
        if (user!=null){
            chain.doFilter(req,resp);
        }else {
            resp.sendRedirect("login.jsp");
        }
    }
}
