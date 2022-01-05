package com.xk.dao.impl;

import com.xk.dao.OrdersDao;
import com.xk.entity.Orders;
import com.xk.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class OrdersDaoImpl implements OrdersDao {

    @Override
    public int addOrders(Orders orders) {
        int num = 0;
        try {
            QueryRunner qr = DBUtils.getInstance().getQrDb();
            String sql ="insert into orders(orders_number,uid,sum_price,state,create_time,count_number)values(?,?,?,?,?,?)";
            Object [] objects= {orders.getOrders_number(),orders.getUid(),orders.getSum_price(),orders.getState(),orders.getCreate_time(),orders.getCount_number()};
            num = qr.update(sql, objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public List<Orders> selectByUid(int uid) {
        List<Orders> ordersList = null;
        try {
            QueryRunner qr = DBUtils.getInstance().getQrDb();
            String sql = "select * from orders where uid=?";
            ordersList = qr.query(sql,new BeanListHandler<>(Orders.class),uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersList;
    }
}
