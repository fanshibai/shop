package com.fan.servlet;

import com.alibaba.fastjson.JSON;
import com.fan.entity.GoodsType;
import com.fan.entity.Page;
import com.fan.service.IGoodsTypeService;
import com.fan.service.impl.GoodsTypeServiceImpl;
import com.fan.untils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoodsTypeServlet extends HttpServlet {
    private IGoodsTypeService goodsTypeService=new GoodsTypeServiceImpl();
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
            case "getGoodsType":
                toGetGoodsType(req,resp);
                break;
            case "goodsTypeByParentIdList":
                toGoodsTypeByParentIdList(req,resp);
                break;
            case "updateGoodsType":
                toUpdateGoodsType(req,resp);
                break;
            case "addGoodsType":
                toAddGoodsType(req,resp);
                break;
            case "batchDel":
                toBatchDel(req,resp);
                break;
        }
    }

    private void toBatchDel(HttpServletRequest req, HttpServletResponse resp) {
        String[] ids = req.getParameterValues("ids[]");
        result = goodsTypeService.deleteBatchObjects(ids);
        try {
            ServletUtils.commonsAction(req,resp,result);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void toAddGoodsType(HttpServletRequest req, HttpServletResponse resp) {
        GoodsType goodsType = ServletUtils.setGoodsTypeEntity(req);
        result = goodsTypeService.addObject(goodsType);
        try {
            ServletUtils.commonsAction(req,resp,result);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void toUpdateGoodsType(HttpServletRequest req, HttpServletResponse resp) {
        GoodsType goodsType = ServletUtils.setGoodsTypeEntity(req);
        result = goodsTypeService.updateObject(goodsType);
        try {
            ServletUtils.commonsAction(req,resp,result);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void toGoodsTypeByParentIdList(HttpServletRequest req, HttpServletResponse resp) {
        List<GoodsType> list = goodsTypeService.getGoodsTypeByParentIdList();
        String json= JSON.toJSONString(list);
        try {
            resp.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void toGetGoodsType(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        GoodsType goodsType= goodsTypeService.getObjectById(Integer.parseInt(id));
        req.setAttribute("goodsType",goodsType);
        try {
            req.getRequestDispatcher("back/goodstype/goodstypeupdate.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void toDelete(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        result = goodsTypeService.deleteObject(Integer.parseInt(id));
        try {
            ServletUtils.commonsAction(req,resp,result);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getPage(HttpServletRequest req, HttpServletResponse resp) {
        String currentPage = req.getParameter("currentPage");
        Page page=goodsTypeService.getPage(currentPage);
        req.setAttribute("page",page);
        try {
            req.getRequestDispatcher("back/goodstype/goodstype.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


}
