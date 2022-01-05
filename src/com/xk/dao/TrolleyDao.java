package com.xk.dao;

import com.xk.entity.Trolley;

import java.util.List;

public interface TrolleyDao {
    //根据uid和pid进行查询
    Trolley selectByUidAndPid(int uid,int pid);
    //根据pid和uid来修改数量
    int updateByUidAndPid(int uid,int pid,int number);
    //增加购物车 插入一条数据
    int addTrolley(Trolley trolley);
    //查询出总记录数 根据uid查询出当前用户购物车总所
    Integer selectTrolleyCount (int uid);
    //根据uid查询
    List<Trolley> selectAllTrolley(int uid);
    //根据tid进行删除
    int deleteByTid(int tid);
    //批量删除
    int deleteByAll(String s);
    //根据tid进行查询
    Trolley selectByTid(int tid);
    //根据tid来修改订单编号
    int updateTrolley(int tid,String orders_number);
    //根据订单号查购物车
    List<Trolley> selectOrders_number(String orders_number);


}
