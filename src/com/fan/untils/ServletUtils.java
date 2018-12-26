package com.fan.untils;

import com.fan.entity.GoodsType;
import com.fan.entity.Page;
import com.fan.entity.User;
import com.fan.service.IGoodsTypeService;
import com.fan.service.impl.GoodsTypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletUtils extends HttpServlet {
    private static IGoodsTypeService goodsTypeService=new GoodsTypeServiceImpl();

    public static void commonsAction(HttpServletRequest req, HttpServletResponse resp, Integer result) throws ServletException, IOException {
        if(result>0){
            String currentPage = req.getParameter("currentPage");
            Page page=goodsTypeService.getPage(currentPage);
            req.setAttribute("page",page);
                req.getRequestDispatcher("back/goodstype/goodstype.jsp").forward(req,resp);
        }else {
            resp.setContentType("text/html;charset=utf-8");
            try {
                resp.getWriter().write("<script> alert('操作失败');location.href='GoodsTypeServlet?action=getPage'</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static User setEntity(HttpServletRequest req){
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
    public static GoodsType setGoodsTypeEntity(HttpServletRequest req){
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String parent_id = req.getParameter("parent_id");
        if(id!=null){
            return new GoodsType(Integer.parseInt(id),name,Integer.parseInt(parent_id));
        }
        return new GoodsType(name,Integer.parseInt(parent_id));
    }
}
