package com.xk.entity;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {
    //私有属性
    private int pid;
    private int cid;
    private String name;
    private String color;
    private String price;
    private String size;
    private String description;
    private String full_description;
    private String pic;
    private String version;
    private int state;
    private Date product_date;

    //映射实体类名称
    private String cname;

    //构造方法

    public Product() {
    }

    public Product(int pid, int cid, String name, String color, String price, String size, String description, String full_description, String pic, String version, int state, Date product_date, String cname) {
        this.pid = pid;
        this.cid = cid;
        this.name = name;
        this.color = color;
        this.price = price;
        this.size = size;
        this.description = description;
        this.full_description = full_description;
        this.pic = pic;
        this.version = version;
        this.state = state;
        this.product_date = product_date;
        this.cname = cname;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFull_description() {
        return full_description;
    }

    public void setFull_description(String full_description) {
        this.full_description = full_description;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getProduct_date() {
        return product_date;
    }

    public void setProduct_date(Date product_date) {
        this.product_date = product_date;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid=" + pid +
                ", cid=" + cid +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", price='" + price + '\'' +
                ", size='" + size + '\'' +
                ", description='" + description + '\'' +
                ", full_description='" + full_description + '\'' +
                ", pic='" + pic + '\'' +
                ", version='" + version + '\'' +
                ", state=" + state +
                ", product_date=" + product_date +
                ", cname='" + cname + '\'' +
                '}';
    }
}

