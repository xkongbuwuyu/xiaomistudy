package com.xk.dao.impl;

import com.xk.dao.TrolleyDao;
import com.xk.entity.Orders;
import com.xk.entity.Trolley;
import com.xk.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class TrolleyDaoImpl implements TrolleyDao {


    @Override
    public Trolley selectByUidAndPid(int uid, int pid) {
        Trolley trolley = null;

        try {
            QueryRunner qr = DBUtils.getInstance().getQrDb();
            String sql = "select * from trolley where uid=? and pid=? and orders_number is null";
            Object [] objects={uid,pid};
            trolley = qr.query(sql,new BeanHandler<Trolley>(Trolley.class),objects);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return trolley;
    }

    @Override
    public int updateByUidAndPid(int uid, int pid, int number) {
        int num = 0;
        try {
            QueryRunner qr = DBUtils.getInstance().getQrDb();
            String sql = "update trolley set number=? where uid=? and pid=?";
            Object [] objects={number,uid,pid};
            num = qr.update(sql,objects);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return num;
    }

    @Override
    public int addTrolley(Trolley trolley) {
        int num = 0;
        try {
            QueryRunner qr = DBUtils.getInstance().getQrDb();
            String sql ="insert into trolley(uid,pid,number)values(?,?,?)";
            Object [] objects= {trolley.getUid(),trolley.getPid(),trolley.getNumber()};
            num = qr.update(sql, objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public Integer selectTrolleyCount(int uid) {

        try{
            QueryRunner qr = DBUtils.getInstance().getQrDb();
            String sql = "SELECT COUNT(1) from trolley where uid =? and orders_number is null";
            //new ScalarHandler() 用于返回总记录数
            Long count = (Long) qr.query(sql,new ScalarHandler(),uid);
            if (count != null){
                //不会进行基本数据类型转换的时候 将基本数据类型首先转为String 然后再进行转换
                return  Integer.parseInt(count+"");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Trolley> selectAllTrolley(int uid) {
        List<Trolley> trolleyList = null;
        try {
            QueryRunner qr = DBUtils.getInstance().getQrDb();
            String sql = "select * from trolley where uid =? and orders_number is null";
            trolleyList =qr.query(sql,new BeanListHandler<Trolley>(Trolley.class),uid);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return trolleyList;
    }

    @Override
    public int deleteByTid(int tid) {
        int num = 0;
        try {
            String sql = "delete from trolley where tid =?";
            QueryRunner qr = DBUtils.getInstance().getQrDb();
            num = qr.update(sql,tid);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public int deleteByAll(String s) {
        int num = 0;
        try {
            QueryRunner qr = DBUtils.getInstance().getQrDb();
            String sql = "delete from trolley where tid in("+s+")";
            num = qr.update(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public Trolley selectByTid(int tid) {
        Trolley trolley = null;
        try {
            QueryRunner qr = DBUtils.getInstance().getQrDb();
            String sql = "select * from trolley where tid =?";
            trolley = qr.query(sql,new BeanHandler<Trolley>(Trolley.class),tid);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return trolley;
    }

    @Override
    public int updateTrolley(int tid, String orders_number) {

        int num = 0;
        try {
            String sql = "update trolley set orders_number=? where tid=?";
            Object[] objects = {orders_number,tid};
            QueryRunner qr = DBUtils.getInstance().getQrDb();
            num = qr.update(sql,objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return num;
    }

    @Override
    public List<Trolley> selectOrders_number(String orders_number) {
        List<Trolley> trolleyList = null;
        try {
            QueryRunner qr = DBUtils.getInstance().getQrDb();
            String sql = "select * from trolley where orders_number =?";
            trolleyList = qr.query(sql,new BeanListHandler<>(Trolley.class),orders_number);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trolleyList;
    }


}
