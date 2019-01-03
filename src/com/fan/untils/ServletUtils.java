package com.fan.untils;

import com.fan.domain.GoodsInfoDomain;
import com.fan.entity.*;
import com.fan.service.IBaseService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServletUtils extends HttpServlet {
    //通用获取分页页面
    public static void commonsAction(HttpServletRequest req, HttpServletResponse resp,Integer result, String servletName, IBaseService baseService){
        if(result>0){
            String currentPage = req.getParameter("currentPage");
            Page page=baseService.getPage(currentPage,servletName);
            req.setAttribute("page",page);
            if("GoodsTypeServlet".equals(servletName)) {
                try {
                    req.getRequestDispatcher("back/goodstype/goodstype.jsp").forward(req, resp);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if("UserServlet".equals(servletName)){
                try {
                    req.getRequestDispatcher("back/user/userinfo.jsp").forward(req, resp);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if("GoodsInfoServlet".equals(servletName)){
                try {
                    req.getRequestDispatcher("back/goods/goodsList.jsp").forward(req, resp);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            resp.setContentType("text/html;charset=utf-8");
            try {
                resp.getWriter().write("<script> alert('操作失败');location.href='"+servletName+"?action=getPage'</script>");
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
        if(id!=null){//修改对象
            return new User(Integer.parseInt(id),username,password,phone,email,Integer.parseInt(is_role));
        }else {//添加对象
            return new User(username, password, phone, email, Integer.parseInt(is_role));
        }
    }

    public static GoodsType setGoodsTypeEntity(HttpServletRequest req){
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String parent_id = req.getParameter("parent_id");
        if(id!=null){//修改对象
            return new GoodsType(Integer.parseInt(id),name,Integer.parseInt(parent_id));
        }else {//添加对象
            return new GoodsType(name, Integer.parseInt(parent_id));
        }
    }
    public static Order setOrderEntity(HttpServletRequest req,Double totalMoney){
        String express = req.getParameter("express");
        String shouhuoren = req.getParameter("shouhuoren");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String bank = req.getParameter("bank");
        User user = (User) req.getSession().getAttribute("customer");
        return new Order(express,bank,totalMoney,user.getId(),shouhuoren,phone,address);
    }
    public static Address setAdressEntuty(HttpServletRequest req){
        User user = (User) req.getSession().getAttribute("customer");
        String address = req.getParameter("address");
        String shouhuoren = req.getParameter("shouhuoren");
        String phone = req.getParameter("phone");
        return  new Address(shouhuoren,phone,address,user.getId());
    }
    public static GoodsInfo setGoodsInfoEntity(HttpServletRequest req){
        String imagesPath =req.getServletContext().getRealPath("images");
        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload upload=new ServletFileUpload(factory);
        GoodsInfo goodsInfo = null;
        Map map=new HashMap();
        try {
            List<FileItem> list = upload.parseRequest(req);
            for (FileItem item:list){
                if(item.isFormField()){
                    String name = item.getFieldName();
                    String value = item.getString("utf-8");
//                    value=new String(value.getBytes("iso-8859-1"),"utf-8");
                    map.put(name,value);
                }else {
                    //图片名
                    String pic_name = item.getName();
                    String name = item.getFieldName();
                    InputStream is=item.getInputStream();
                    if (pic_name!=null&&!"".equals(pic_name)) {
                        map.put(name, pic_name);
                        FileOutputStream fos=new FileOutputStream(imagesPath+ File.separator+pic_name);
                        try {
                            IOUtils.copy(is,fos);
                        }finally {
                            IOUtils.closeQuietly(fos);
                        }
                    }
                }
            }
            goodsInfo=new GoodsInfo();
            BeanUtils.populate(goodsInfo,map);
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return goodsInfo;
    }

    public static List<OrderDetail> setOrderDetail(List<GoodsInfoDomain> list,Integer o_orderid) {
        List<OrderDetail> orderDetailList=new ArrayList<OrderDetail>();
        OrderDetail orderDetail=null;
        Integer goodsid=null;
        String goods_name=null;
        String goods_description=null;
        String goods_pic=null;
        Double goods_price=null;
        Double goods_price_off=null;
        Integer count=null;
        String goods_date=DateUtils.getDate();
        Double goods_total_price=null;
        for (GoodsInfoDomain goodsInfoDomain:list) {
            goodsid=goodsInfoDomain.getId();
            goods_name=goodsInfoDomain.getGoods_name();
            goods_description=goodsInfoDomain.getGoods_description();
            goods_pic=goodsInfoDomain.getGoods_pic();
            goods_price=goodsInfoDomain.getGoods_price();
            goods_price_off=goodsInfoDomain.getGoods_price_off();
            count=goodsInfoDomain.getCount();
            goods_total_price=goodsInfoDomain.getSum();
            orderDetail=new OrderDetail(goodsid,goods_name,goods_description,goods_pic,goods_price,goods_price_off,count,o_orderid,goods_total_price,goods_date);
            orderDetailList.add(orderDetail);
        }
        return orderDetailList;
    }
}
