package com.fan.servlet;

import com.fan.entity.Page;
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
        result= userService.deleteBatchObjects(ids);
        commonsAction(req,resp,result);
    }

    private void toAddUser(HttpServletRequest req, HttpServletResponse resp) {
        User user = ServletUtils.setEntity(req);
        result = userService.addObject(user);
        commonsAction(req,resp,result);
    }

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

    private void toUpdateUser(HttpServletRequest req, HttpServletResponse resp) {
        User user = ServletUtils.setEntity(req);
        result = userService.updateObject(user);
        commonsAction(req,resp,result);
    }

    private void toDelete(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        result = userService.deleteObject(Integer.parseInt(id));
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
