package com.xk.service;

import com.xk.entity.User;

import java.util.List;

public interface UserService {
    //登录验证
    User selectByUsernameAndPassword(String username, String password);
    //注册时验证电话号码
    boolean selectByPhoneNum(String phone_number);
    //注册时验证用户名
    boolean selectUserName(String username);
    //完善注册功能
    int register(User user);

    User selectByUsernameAndPasswordAndManager(String username,String password,int manager);

    //根据当前页与页量查询出当前的数据
    List<User> selectPage(Integer currentPageNo,Integer pageSize);
    //查询出总数据数
    Integer selectPageCount();
    int updateManager(int id,int manager);

    int deleteAllBatch(String ids);



    //List<User> selectAll();



}
