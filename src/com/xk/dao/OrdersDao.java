package com.xk.dao;

import com.xk.entity.Orders;

import java.util.List;

public interface OrdersDao {
    int addOrders(Orders orders);
    List<Orders> selectByUid(int uid);
}
