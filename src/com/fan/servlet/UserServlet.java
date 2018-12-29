package com.fan.servlet;

import com.fan.entity.User;
import com.fan.service.IUserService;
import com.fan.service.impl.UserServiceImpl;
import com.fan.untils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    private IUserService userService=new UserServiceImpl();
    String servletName="UserServlet";
    Integer result=0;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        String action = req.getParameter("action");
        switch (action){
            case "getPage":
                getPage(req,resp);
                break;
            case "delete":
                toDelete(req,resp);
                break;
            case "getUser":
                toGetUser(req,resp);
                break;
            case "updateUser":
                toUpdateUser(req,resp);
                break;
            case "addUser":
                toAddUser(req,resp);
                break;
            case "batchDel":
                toBatchDel(req,resp);
                break;
            case "backLogin":
                toBackLogin(req,resp);
                break;
            case "login":
                toLogin(req,resp);
                break;
        }
    }

    private void toLogin(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user=userService.backLogin(username,password);
        if (user!=null){
            req.getSession().setAttribute("customer",user);
            try {
                resp.sendRedirect("home.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                resp.getWriter().write("<script> alert('用户名或密码错误');location.href='login.jsp'</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void toBackLogin(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user=userService.backLogin(username,password);
        if (user!=null){
            if ("1".equals(user.getIs_role().toString())){
                req.getSession().setAttribute("user",user);
                try {
                    resp.sendRedirect("back/main.jsp");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    resp.getWriter().write("<script> alert('没有访问该页面的权限');location.href='backLogin.jsp'</script>");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            try {
                resp.getWriter().write("<script> alert('用户名或密码错误');location.href='backLogin.jsp'</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //多选删除
    private void toBatchDel(HttpServletRequest req, HttpServletResponse resp) {
        String[] ids = req.getParameterValues("ids[]");
        result= userService.deleteBatchObjects(ids);
        ServletUtils.commonsAction(req,resp,result,servletName,userService);
    }
    //添加
    private void toAddUser(HttpServletRequest req, HttpServletResponse resp) {
        User user = ServletUtils.setEntity(req);
        result = userService.addObject(user);
        ServletUtils.commonsAction(req,resp,result,servletName,userService);
    }
    //通过id获取对象（回显）
    private void toGetUser(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        User user = userService.getObjectById(Integer.parseInt(id));
        req.setAttribute("user",user);
        try {
            req.getRequestDispatcher("back/user/updateuser.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //修改
    private void toUpdateUser(HttpServletRequest req, HttpServletResponse resp) {
        User user = ServletUtils.setEntity(req);
        result = userService.updateObject(user);
        ServletUtils.commonsAction(req,resp,result,servletName,userService);
    }
    //删除
    private void toDelete(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        result = userService.deleteObject(Integer.parseInt(id));
        ServletUtils.commonsAction(req,resp,result,servletName,userService);
    }
    //分页
    private void getPage(HttpServletRequest req, HttpServletResponse resp) {
        ServletUtils.commonsAction(req,resp,1,servletName,userService);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
