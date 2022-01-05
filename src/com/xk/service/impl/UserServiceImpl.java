package com.xk.service.impl;

import com.xk.dao.UserDao;
import com.xk.dao.impl.UserDaoImpl;
import com.xk.entity.User;
import com.xk.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();


    //登录查询用户名和密码
    @Override
    public User selectByUsernameAndPassword(String username, String password) {
        return userDao.selectByUsernameAndPassword(username, password);
    }


    //注册查询电话号码
    @Override
    public boolean selectByPhoneNum(String phone_number) {
        User user = userDao.selectByPhoneNum(phone_number);
        if(user == null){
            return true;
        }
        return false;
    }

    //注册查询用户名
    @Override
    public boolean selectUserName(String username) {
        User user = userDao.selectUserName(username);
        if (user == null){
            return true;
        }

        return false;
    }

    @Override
    public int register(User user) {
        return userDao.register(user);
    }

    @Override
    public User selectByUsernameAndPasswordAndManager(String username, String password, int manager) {
        return userDao.selectByUsernameAndPasswordAndManager(username, password, manager);
    }

    @Override
    public List<User> selectPage(Integer currentPageNo, Integer pageSize) {
        return userDao.selectPage(currentPageNo,pageSize);
    }

    @Override
    public Integer selectPageCount() {
        return userDao.selectPageCount();
    }

    @Override
    public int updateManager(int id, int manager) {
        return userDao.updateManager(id, manager);
    }

    @Override
    public int deleteAllBatch(String ids) {
        return userDao.deleteAllBatch(ids);
    }


//    @Override
//    public List<User> selectAll() {
//        return userDao.selectAll();
//    }
}

