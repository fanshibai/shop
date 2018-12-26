package com.fan.entity;

public class GoodsType {
    private Integer id;
    private String name;
    private Integer parent_id;
    private String pic;
    private Integer flag;
    private String parent_name;

    public GoodsType() {
    }

    public GoodsType(Integer id, String name, Integer parent_id, String pic, Integer flag, String parent_name) {
        this.id = id;
        this.name = name;
        this.parent_id = parent_id;
        this.pic = pic;
        this.flag = flag;
        this.parent_name = parent_name;
    }

    public GoodsType(Integer id, String name, Integer parent_id, String pic, Integer flag) {
        this.id = id;
        this.name = name;
        this.parent_id = parent_id;
        this.pic = pic;
        this.flag = flag;
    }

    public GoodsType(Integer id, String name, Integer parent_id) {
        this.id = id;
        this.name = name;
        this.parent_id = parent_id;
    }

    public GoodsType(String name, Integer parent_id) {
        this.name = name;
        this.parent_id = parent_id;
    }

    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "GoodsType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parent_id='" + parent_id + '\'' +
                ", pic='" + pic + '\'' +
                ", flag=" + flag +
                '}';
    }
}
