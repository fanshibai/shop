package com.fan.servlet;

import com.fan.domain.GoodsInfoDomain;
import com.fan.entity.*;
import com.fan.service.IAddressService;
import com.fan.service.IGoodsInfoService;
import com.fan.service.IGoodsTypeService;
import com.fan.service.IOrderService;
import com.fan.service.impl.AddressServiceImpl;
import com.fan.service.impl.GoodsInfoServiceImpl;
import com.fan.service.impl.GoodsTypeServiceImpl;
import com.fan.service.impl.OrderServiceImpl;
import com.fan.untils.ServletUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ShopCartServlet extends HttpServlet {
    private IGoodsInfoService goodsInfoService=new GoodsInfoServiceImpl();
    private IGoodsTypeService goodsTypeService=new GoodsTypeServiceImpl();
    private IAddressService addressService=new AddressServiceImpl();
    private IOrderService orderService=new OrderServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action){
            case "findAll":
                toFindAll(req,resp);
                break;
            case "getGoodsInfo":
                toGetGoodsInfo(req,resp);
                break;
            case "shopCart":
                toShopCart(req,resp);
                break;
            case "toShopCart":
                shopCart(req,resp);
                break;
            case "delete":
                toDelete(req,resp);
                break;
            case "deleteOne":
                toDeleteOne(req,resp);
                break;
            case "getAddressList":
                toGetAddressList(req,resp);
                break;
            case "makeOrder":
                toMakeOrder(req,resp);
                break;
                
        }
    }

    private void toMakeOrder(HttpServletRequest req, HttpServletResponse resp) {
        Integer result=0;
        ShopCart shopCart = (ShopCart) req.getSession().getAttribute("shopCart");
        Double totalMoney=shopCart.getSums()+10;
        req.setAttribute("totalMoney",totalMoney);
        Order order = ServletUtils.setOrderEntity(req,totalMoney);
        result = orderService.addObject(order);
        if(result>0) {
            try {
                req.setAttribute("order", order);
                req.getRequestDispatcher("success.jsp").forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void toGetAddressList(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("customer");
        if (user==null){
            try {
                resp.sendRedirect("login.jsp");
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<Address> addressList = addressService.getAddressList(user.getId());
        req.setAttribute("addressList",addressList);
        try {
            req.getRequestDispatcher("pay.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void toDeleteOne(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        ShopCart shopCart = (ShopCart) req.getSession().getAttribute("shopCart");
        shopCart.deleteOne(id);
        shopCart(req,resp);
    }

    private void toDelete(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        ShopCart shopCart = (ShopCart) req.getSession().getAttribute("shopCart");
        shopCart.delete(id);
        shopCart(req,resp);
    }

    private void shopCart(HttpServletRequest req, HttpServletResponse resp) {
        ShopCart shopCart = (ShopCart) req.getSession().getAttribute("shopCart");
        if (shopCart==null){
            shopCart=new ShopCart();
        }
        List<GoodsInfoDomain> list = shopCart.getList();
        req.setAttribute("list",list);
        try {
            req.getRequestDispatcher("shopcat.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void toShopCart(HttpServletRequest req, HttpServletResponse resp) {
        GoodsInfoDomain goodsInfoDomain = new GoodsInfoDomain();
        String id = req.getParameter("id");
        String count = req.getParameter("count");
        GoodsInfo goodsInfo = goodsInfoService.getObjectById(Integer.parseInt(id));
        try {
            BeanUtils.copyProperties(goodsInfoDomain,goodsInfo);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        goodsInfoDomain.setCount(Integer.parseInt(count));
        ShopCart shopCart=ShopCart.getShopCart(req.getSession());
        shopCart.add(goodsInfoDomain);
        req.getSession().setAttribute("shopCart",shopCart);
    }

    private void toGetGoodsInfo(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        GoodsInfo goodsInfo = goodsInfoService.getObjectById(Integer.parseInt(id));
        req.setAttribute("goodsInfo",goodsInfo);
        try {
            req.getRequestDispatcher("introduction.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void toFindAll(HttpServletRequest req, HttpServletResponse resp) {
        List<GoodsInfo> goodsInfos = goodsInfoService.getListObject();
        List<GoodsType> goodsTypes = goodsTypeService.getListObject();
        req.setAttribute("gtList",goodsTypes);
        req.setAttribute("giList",goodsInfos);
        try {
            req.getRequestDispatcher("index.jsp").forward(req,resp);
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
}
