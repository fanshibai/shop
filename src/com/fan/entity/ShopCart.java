package com.fan.entity;

import com.fan.domain.GoodsInfoDomain;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ShopCart {
    private List<GoodsInfoDomain> list=new ArrayList<GoodsInfoDomain>();
    public static ShopCart getShopCart(HttpSession session){
        ShopCart shopCart = (ShopCart) session.getAttribute("shopCart");
        if (shopCart==null){
            shopCart=new ShopCart();
        }
        return shopCart;
    }
    public void add(GoodsInfoDomain info){
        for (GoodsInfoDomain goodsInfo:list){
            if (goodsInfo.getId().toString().equals(info.getId().toString())){
                goodsInfo.setCount(goodsInfo.getCount()+info.getCount());
                return;
            }
        }
        list.add(info);
    }
    public void deleteOne(String id){
        for (GoodsInfoDomain goodsInfoDomain:list){
            if (goodsInfoDomain.getId().toString().equals(id)){
                if (goodsInfoDomain.getCount()>1) {
                    goodsInfoDomain.setCount(goodsInfoDomain.getCount() - 1);
                    return;
                }else {
                    list.remove(goodsInfoDomain);
                    return;
                }
            }
        }
    }
    public void delete(String id){
        for (GoodsInfoDomain goodsInfoDomain:list){
            if (goodsInfoDomain.getId().toString().equals(id)){
                list.remove(goodsInfoDomain);
                return;
            }
        }
    }
    public List<GoodsInfoDomain> getList(){
        return list;
    }
    public Integer getCount(){
        Integer count=0;
        if (list!=null){
            for (GoodsInfoDomain goodsInfo:list){
                count=count+goodsInfo.getCount();
            }
            return count;
        }
        return count;
    }
    public Double getSums(){
        Double sums=0.0;
        if (list!=null) {
            for (GoodsInfoDomain goodsInfo : list) {
                sums = sums + goodsInfo.getCount() * goodsInfo.getGoods_price_off();
            }
        }
        return sums;
    }
}
