package com.fan.entity;


public class OrderDetail {
    private Integer id;
    private Integer goodsid;
    private String goods_name;
    private String goods_description;
    private String goods_pic;
    private Double goods_price;
    private Double goods_price_off;
    private Integer Count;
    private Integer o_orderid;
    private String goods_date;
    private Double goods_total_price;
    public OrderDetail() {
    }

    public OrderDetail(Integer goodsid, String goods_name, String goods_description, String goods_pic, Double goods_price, Double goods_price_off, Integer count, Integer o_orderid,Double goods_total_price, String goods_date) {
        this.goodsid = goodsid;
        this.goods_name = goods_name;
        this.goods_description = goods_description;
        this.goods_pic = goods_pic;
        this.goods_price = goods_price;
        this.goods_price_off = goods_price_off;
        Count = count;
        this.o_orderid = o_orderid;
        this.goods_date = goods_date;
        this.goods_total_price=goods_total_price;
    }

    public Double getGoods_total_price() {
        return goods_total_price;
    }

    public void setGoods_total_price(Double goods_total_price) {
        this.goods_total_price = goods_total_price;
    }

    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }

    public String getGoods_date() {
        return goods_date;
    }

    public void setGoods_date(String goods_date) {
        this.goods_date = goods_date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_description() {
        return goods_description;
    }

    public void setGoods_description(String goods_description) {
        this.goods_description = goods_description;
    }

    public String getGoods_pic() {
        return goods_pic;
    }

    public void setGoods_pic(String goods_pic) {
        this.goods_pic = goods_pic;
    }

    public Double getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(Double goods_price) {
        this.goods_price = goods_price;
    }

    public Double getGoods_price_off() {
        return goods_price_off;
    }

    public void setGoods_price_off(Double goods_price_off) {
        this.goods_price_off = goods_price_off;
    }

    public Integer getCount() {
        return Count;
    }

    public void setCount(Integer count) {
        Count = count;
    }

    public Integer getO_orderid() {
        return o_orderid;
    }

    public void setO_orderid(Integer o_orderid) {
        this.o_orderid = o_orderid;
    }
    public Double getSum(){
        Double sum=0.0;
        sum=Count*getGoods_price_off();
        return sum;
    }
}
