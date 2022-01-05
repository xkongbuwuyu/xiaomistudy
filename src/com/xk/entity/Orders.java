package com.xk.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Orders implements Serializable {
    private String orders_number;
    private int uid;
    private double sum_price;
    private int state;
    private Date create_time;
    private int count_number;

    //手动建立一对多
    List<Trolley> trolleyList;


    public Orders() {
    }

    public Orders(String orders_number, int uid, double sum_price, int state, Date create_time, int count_number, List<Trolley> trolleyList) {
        this.orders_number = orders_number;
        this.uid = uid;
        this.sum_price = sum_price;
        this.state = state;
        this.create_time = create_time;
        this.count_number = count_number;
        this.trolleyList = trolleyList;
    }

    public String getOrders_number() {
        return orders_number;
    }

    public void setOrders_number(String orders_number) {
        this.orders_number = orders_number;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public double getSum_price() {
        return sum_price;
    }

    public void setSum_price(double sum_price) {
        this.sum_price = sum_price;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public int getCount_number() {
        return count_number;
    }

    public void setCount_number(int count_number) {
        this.count_number = count_number;
    }

    public List<Trolley> getTrolleyList() {
        return trolleyList;
    }

    public void setTrolleyList(List<Trolley> trolleyList) {
        this.trolleyList = trolleyList;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orders_number='" + orders_number + '\'' +
                ", uid=" + uid +
                ", sum_price=" + sum_price +
                ", state=" + state +
                ", create_time=" + create_time +
                ", count_number=" + count_number +
                ", trolleyList=" + trolleyList +
                '}';
    }
}
