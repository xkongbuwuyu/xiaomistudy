package com.xk.service;

import com.xk.entity.Orders;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrdersService {
    int addOrders(String tid, int uid,HttpServletRequest request);
    List<Orders> selectByUid(int uid);
}
