package com.xk.dao;

import com.xk.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    //根据用户名和密码进行查询
    User selectByUsernameAndPassword(String username,String password);
    //根据phone_number进行查询
    User selectByPhoneNum(String phone_number);
    //根据 selectUserName进行查询
    User selectUserName(String username);
    //注册功能完善
    int register(User user);

    User selectByUsernameAndPasswordAndManager(String username,String password,int manager);

    //根据当前页与页量查询出当前的数据
    List<User> selectPage(Integer currentPageNo,Integer pageSize);
    //查询出总数据数
    Integer selectPageCount();

    int updateManager(int id,int manager);

    //批量删除用户
    int deleteAllBatch(String ids);

    //查询User所有属性
    //List<User> selectAll();


}
