package com.xk.dao.impl;

import com.xk.dao.UserDao;
import com.xk.entity.User;
import com.xk.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {

    //登录查询用户名和密码
    @Override
    public User selectByUsernameAndPassword(String username, String password) {
        User user = null;
        try {
            QueryRunner qr = DBUtils.getInstance().getQrDb();
            //sql语句
            String sql = "select * from user where username=?  and password=?";
            //构建一个数组给占位符进行赋值
            Object[] objects = {username, password};
            user = qr.query(sql, new BeanHandler<User>(User.class), objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    //注册查询电话号码
    @Override
    public User selectByPhoneNum(String phone_number) {
        User user = null;
        try {
            QueryRunner qr = DBUtils.getInstance().getQrDb();
            String sql = "select * from user where phone_number=?";
            user = qr.query(sql, new BeanHandler<User>(User.class), phone_number);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    //注册查询用户名
    @Override
    public User selectUserName(String username) {
        User user = null;
        try{
            QueryRunner qr = DBUtils.getInstance().getQrDb();
            String sql = "select * from user where username=?";
            user = qr.query(sql, new BeanHandler<User>(User.class), username);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int register(User user) {
        int num = 0;
        try {
            String sql ="insert into user(name,sex,phone_number,area,username,password,photo,create_time)values(?,?,?,?,?,?,?,?)";
            Object [] objects= {user.getName(),user.getSex(),user.getPhone_number(),user.getArea(),user.getUsername(),user.getPassword(),user.getPhoto(),user.getCreate_time()};
            QueryRunner qr = DBUtils.getInstance().getQrDb();
            num = qr.update(sql, objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;

    }

    @Override
    public User selectByUsernameAndPasswordAndManager(String username, String password, int manager) {
        User user = null;
        try {
            QueryRunner qr = DBUtils.getInstance().getQrDb();
            String sql ="select * from user where username=? and password=? and manager=?";
            Object [] objects={username,password,manager};
            user = qr.query(sql, new BeanHandler<User>(User.class), objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;

    }

    @Override
    public List<User> selectPage(Integer currentPageNo, Integer pageSize) {
        List<User> userList = null;
        try {
            QueryRunner qr = DBUtils.getInstance().getQrDb();
            //当前页与数据库索引之间的公式：(当前页-1）*页量
            //逗号前表示索引，逗号后个表示页量
            String sql ="select * from USER LIMIT ?,?";
            Object[] objects = {(currentPageNo-1)*pageSize,pageSize};
            userList = qr.query(sql,new BeanListHandler<User>(User.class),objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public Integer selectPageCount() {
        Long count = null;
        try {
            QueryRunner qr = DBUtils.getInstance().getQrDb();
            String sql ="select count(1) from user";
            count = (Long) qr.query(sql,new ScalarHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(count+"");
    }

    @Override
    public int updateManager(int id, int manager) {
        int num = 0;
        try {
            QueryRunner qr = DBUtils.getInstance().getQrDb();
            String sql ="update user set manager=? where id=?";
            Object[] objects = {manager,id};
            num = qr.update(sql,objects);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    @Override
    public int deleteAllBatch(String ids) {
        int num = 0;
        try {
            QueryRunner qr = DBUtils.getInstance().getQrDb();
            String sql ="delete from user where id in("+ids+")";
            num = qr.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }


//    @Override
//    public List<User> selectAll() {
//        List<User> userList=null;
//        try {
//            QueryRunner qr = DBUtils.getInstance().getQrDb();
//            String sql="select * from user";
//            userList = qr.query(sql, new BeanListHandler<>(User.class));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return userList;
//    }
}
