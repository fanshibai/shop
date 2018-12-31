package com.fan.entity;

public class Address {
    private Integer id;
    private String shouhuoren;
    private String phone;
    private String address;
    private Integer userid;
    private Integer isdefault;

    public Address() {
    }

    public Address(String shouhuoren, String phone, String address, Integer userid) {
        this.shouhuoren = shouhuoren;
        this.phone = phone;
        this.address = address;
        this.userid = userid;
    }

    public Address(Integer id, String shouhuoren, String phone, String address, Integer userid, Integer isdefault) {
        this.id = id;
        this.shouhuoren = shouhuoren;
        this.phone = phone;
        this.address = address;
        this.userid = userid;
        this.isdefault = isdefault;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShouhuoren() {
        return shouhuoren;
    }

    public void setShouhuoren(String shouhuoren) {
        this.shouhuoren = shouhuoren;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Integer isdefault) {
        this.isdefault = isdefault;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", shouhuoren='" + shouhuoren + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", userid=" + userid +
                ", isdefault=" + isdefault +
                '}';
    }
}
