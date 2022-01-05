package com.xk.entity;

import java.io.Serializable;

public class Trolley implements Serializable {
    private int tid;//编号
    private int uid;//用户id
    private int pid;//商品id
    private int number;//商品数量
    private String orders_number;//订单号

    //手动建立关系一对一关系
    private Product product;


    public Trolley() {
    }

    public Trolley(int tid, int uid, int pid, int number, String orders_number, Product product) {
        this.tid = tid;
        this.uid = uid;
        this.pid = pid;
        this.number = number;
        this.orders_number = orders_number;
        this.product = product;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getOrders_number() {
        return orders_number;
    }

    public void setOrders_number(String orders_number) {
        this.orders_number = orders_number;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Trolley{" +
                "tid=" + tid +
                ", uid=" + uid +
                ", pid=" + pid +
                ", number=" + number +
                ", orders_number='" + orders_number + '\'' +
                ", product=" + product +
                '}';
    }
}


