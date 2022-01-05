package com.xk.service.impl;

import com.xk.dao.ProductDao;
import com.xk.dao.TrolleyDao;
import com.xk.dao.impl.ProductDaoImpl;
import com.xk.dao.impl.TrolleyDaoImpl;
import com.xk.entity.Product;
import com.xk.entity.Trolley;
import com.xk.service.TrolleyService;

import java.util.List;

public class TrolleyServiceImpl implements TrolleyService {
    private TrolleyDao trolleyDao = new TrolleyDaoImpl();
    private ProductDao productDao = new ProductDaoImpl();

    @Override
    public int addTrolley(Trolley trolley) {
        int num = -1;
        int pid = trolley.getPid();
        int uid = trolley.getUid();
        int number = trolley.getNumber();
        Trolley t = trolleyDao.selectByUidAndPid(uid, pid);
        //表示该商品没有加入过购物车
        if (t == null) {
            //构建一个新的对象
            Trolley t1 = new Trolley();
            t1.setPid(pid);
            t1.setUid(uid);
            t1.setNumber(number);
            num = trolleyDao.addTrolley(t1);
        } else {
            //t.setNumber(t.getNumber()+number);
            num = trolleyDao.updateByUidAndPid(uid, pid, t.getNumber() + number);
        }
        return num;

    }

    @Override
    public Integer selectTrolleyCount(int uid) {
        return trolleyDao.selectTrolleyCount(uid);
    }

    @Override
    public List<Trolley> selectAllTrolley(int uid) {
        List<Trolley> trolleyList = trolleyDao.selectAllTrolley(uid);
        for (Trolley t : trolleyList) {
            Product product = productDao.selectByPid(t.getPid());
            t.setProduct(product);
        }

        return trolleyList;
    }

    @Override
    public int deleteOrAll(String str) {
        int num = -1;
        if (str.contains(",")){
            //批量删除
            num = trolleyDao.deleteByAll(str);
        }else {
            //单个删除
            return trolleyDao.deleteByTid(Integer.parseInt(str));
        }
        return num;
    }

    @Override
    public Trolley selectByTid(int tid) {
        return trolleyDao.selectByTid(tid);
    }

}
