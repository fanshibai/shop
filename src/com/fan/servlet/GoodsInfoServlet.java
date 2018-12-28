package com.fan.servlet;

import com.fan.entity.GoodsInfo;
import com.fan.service.IGoodsInfoService;
import com.fan.service.impl.GoodsInfoServiceImpl;
import com.fan.untils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoodsInfoServlet extends HttpServlet {
    private IGoodsInfoService goodsInfoService=new GoodsInfoServiceImpl();
    String servletName="GoodsInfoServlet";
    Integer result=0;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action){
            case "getPage":
                toGetPage(req,resp);
                break;
            case "delete":
                toDelete(req,resp);
                break;
            case "batchDel":
                toBatchDel(req,resp);
                break;
            case "getGoodsInfo":
                toGetGoodsInfo(req,resp);
                break;
            case "updateGoodsInfo":
                toUpdateGoodsInfo(req,resp);
                break;
            case "addGoodsInfo":
                toAddGoodsInfo(req,resp);
                break;
        }
    }
    //增加
    private void toAddGoodsInfo(HttpServletRequest req, HttpServletResponse resp) {
        GoodsInfo goodsInfo = ServletUtils.setGoodsInfoEntity(req);
        result = goodsInfoService.addObject(goodsInfo);
        ServletUtils.commonsAction(req,resp,result,servletName,goodsInfoService);
    }
    //修改
    private void toUpdateGoodsInfo(HttpServletRequest req, HttpServletResponse resp) {
        GoodsInfo goodsInfo = ServletUtils.setGoodsInfoEntity(req);
        result = goodsInfoService.updateObject(goodsInfo);
        ServletUtils.commonsAction(req,resp,result,servletName,goodsInfoService);
    }
    //tongguoid获取对象（回显）
    private void toGetGoodsInfo(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        GoodsInfo goodsInfo = goodsInfoService.getObjectById(Integer.parseInt(id.trim()));
        req.setAttribute("goodsInfo",goodsInfo);
        try {
            req.getRequestDispatcher("back/goods/goodsupdate.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //多选删除
    private void toBatchDel(HttpServletRequest req, HttpServletResponse resp) {
        String[] ids = req.getParameterValues("ids[]");
        result = goodsInfoService.deleteBatchObjects(ids);
        ServletUtils.commonsAction(req,resp,result,servletName,goodsInfoService);
    }
    //删除
    private void toDelete(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        result = goodsInfoService.deleteObject(Integer.parseInt(id));
        ServletUtils.commonsAction(req,resp,result,servletName,goodsInfoService);
    }
    //分页
    private void toGetPage(HttpServletRequest req, HttpServletResponse resp) {
        ServletUtils.commonsAction(req,resp,1,servletName,goodsInfoService);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
