package com.fan.servlet;

import com.alibaba.fastjson.JSON;
import com.fan.entity.GoodsType;
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
    String servletName="GoodsTypeServlet";
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
    //多选删除
    private void toBatchDel(HttpServletRequest req, HttpServletResponse resp) {
        String[] ids = req.getParameterValues("ids[]");
        result = goodsTypeService.deleteBatchObjects(ids);
        ServletUtils.commonsAction(req,resp,result,servletName,goodsTypeService);
    }
    //增加
    private void toAddGoodsType(HttpServletRequest req, HttpServletResponse resp) {
        GoodsType goodsType = ServletUtils.setGoodsTypeEntity(req);
        result = goodsTypeService.addObject(goodsType);
        ServletUtils.commonsAction(req,resp,result,servletName,goodsTypeService);
    }
    //修改
    private void toUpdateGoodsType(HttpServletRequest req, HttpServletResponse resp) {
        GoodsType goodsType = ServletUtils.setGoodsTypeEntity(req);
        result = goodsTypeService.updateObject(goodsType);
        ServletUtils.commonsAction(req,resp,result,servletName,goodsTypeService);
    }
    //获取大类小类的集合（ajax）
    private void toGoodsTypeByParentIdList(HttpServletRequest req, HttpServletResponse resp) {
        String parent_id = req.getParameter("parent_id");
        List<GoodsType> list = goodsTypeService.getGoodsTypeByParentIdList(Integer.parseInt(parent_id));
        String json= JSON.toJSONString(list);
        try {
            resp.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //通过id获取对象（回显）
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
    //删除
    private void toDelete(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        result = goodsTypeService.deleteObject(Integer.parseInt(id));
        ServletUtils.commonsAction(req,resp,result,servletName,goodsTypeService);

    }
    //分页
    private void getPage(HttpServletRequest req, HttpServletResponse resp) {
        ServletUtils.commonsAction(req,resp,1,servletName,goodsTypeService);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


}
