package com.fan.servlet;

import com.fan.entity.Page;
import com.fan.entity.User;
import com.fan.service.IUserService;
import com.fan.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    private IUserService userService=new UserServiceImpl();
    Integer result=0;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        }
    }

    private void toBatchDel(HttpServletRequest req, HttpServletResponse resp) {
        String[] ids = req.getParameterValues("ids[]");
        result= userService.deleteBatchUsers(ids);
        commonsAction(req,resp,result);
    }

    private void toAddUser(HttpServletRequest req, HttpServletResponse resp) {
        User user = setEntity(req);
        result = userService.addUser(user);
        commonsAction(req,resp,result);
    }

    private void toGetUser(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        User user = userService.getUserById(Integer.parseInt(id));
        req.setAttribute("user",user);
        try {
            req.getRequestDispatcher("back/user/updateuser.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void toUpdateUser(HttpServletRequest req, HttpServletResponse resp) {
        User user = setEntity(req);
        result = userService.updateUser(user);
        commonsAction(req,resp,result);
    }

    private void toDelete(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        result = userService.deleteUser(Integer.parseInt(id));
        commonsAction(req,resp,result);
    }

    private void getPage(HttpServletRequest req, HttpServletResponse resp) {
        String currentPage = req.getParameter("currentPage");
        Page page=userService.getPage(currentPage);
        req.setAttribute("page",page);
        try {
            req.getRequestDispatcher("back/user/userinfo.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
    private User setEntity(HttpServletRequest req){
        String id = req.getParameter("id");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String is_role = req.getParameter("is_role");
        if(id!=null){
            return new User(Integer.parseInt(id),username,password,phone,email,Integer.parseInt(is_role));
        }
        return new User(username,password,phone,email,Integer.parseInt(is_role));
    }
    private void commonsAction(HttpServletRequest req, HttpServletResponse resp,Integer result){
        if(result>0){
            getPage(req,resp);
        }else {
            resp.setContentType("text/html;charset=utf-8");
            try {
                resp.getWriter().write("<script> alert('操作失败');location.href='UserServlet?action=getPage'</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
