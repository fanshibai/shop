package com.fan.domain;

public class GoodsInfoDomain {
    private Integer id;
    private String goods_name;
    private String goods_description;
    private String goods_pic;
    private Double goods_price;
    private Double goods_price_off;
    private Double goods_discount;
    private Integer goods_stock;
    private Integer goods_fatherid;
    private Integer goods_parentid;
    private Integer flag;
    private Integer Count;

    public GoodsInfoDomain() {
    }

    public GoodsInfoDomain(Integer id, String goods_name, String goods_description, String goods_pic, Double goods_price, Double goods_price_off, Double goods_discount, Integer goods_stock, Integer goods_fatherid, Integer goods_parentid, Integer flag, Integer count) {
        this.id = id;
        this.goods_name = goods_name;
        this.goods_description = goods_description;
        this.goods_pic = goods_pic;
        this.goods_price = goods_price;
        this.goods_price_off = goods_price_off;
        this.goods_discount = goods_discount;
        this.goods_stock = goods_stock;
        this.goods_fatherid = goods_fatherid;
        this.goods_parentid = goods_parentid;
        this.flag = flag;
        Count = count;
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

    public Double getGoods_discount() {
        return goods_discount;
    }

    public void setGoods_discount(Double goods_discount) {
        this.goods_discount = goods_discount;
    }

    public Integer getGoods_stock() {
        return goods_stock;
    }

    public void setGoods_stock(Integer goods_stock) {
        this.goods_stock = goods_stock;
    }

    public Integer getGoods_fatherid() {
        return goods_fatherid;
    }

    public void setGoods_fatherid(Integer goods_fatherid) {
        this.goods_fatherid = goods_fatherid;
    }

    public Integer getGoods_parentid() {
        return goods_parentid;
    }

    public void setGoods_parentid(Integer goods_parentid) {
        this.goods_parentid = goods_parentid;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getCount() {
        return Count;
    }

    public void setCount(Integer count) {
        Count = count;
    }
    public Double getSum(){
        Double sum=0.0;
        sum=Count*getGoods_price_off();
        return sum;
    }
    @Override
    public String toString() {
        return "GoodsInfoDomain{" +
                "id=" + id +
                ", goods_name='" + goods_name + '\'' +
                ", goods_description='" + goods_description + '\'' +
                ", goods_pic='" + goods_pic + '\'' +
                ", goods_price=" + goods_price +
                ", goods_price_off=" + goods_price_off +
                ", goods_discount=" + goods_discount +
                ", goods_stock=" + goods_stock +
                ", goods_fatherid=" + goods_fatherid +
                ", goods_parentid=" + goods_parentid +
                ", flag=" + flag +
                ", Count=" + Count +
                '}';
    }
}
