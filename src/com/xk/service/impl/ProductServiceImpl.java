package com.xk.service.impl;

import com.xk.dao.ProductDao;
import com.xk.dao.impl.ProductDaoImpl;
import com.xk.entity.Product;
import com.xk.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao = new ProductDaoImpl();
    @Override
    public List<Product> selectByState(int state) {
        return productDao.selectByState(state);
    }

    @Override
    public List<Product> selectCname(String cname) {
        return productDao.selectCname(cname);
    }

    @Override
    public Product selectByPid(int pid) {
        return productDao.selectByPid(pid);
    }

    @Override
    public List<Product> selectProductPage(String name, Integer state, String startTime, String endTime, Integer currentPageNo, Integer pageSize) {
        return productDao.selectProductPage(name,state,startTime,endTime,currentPageNo,pageSize);
    }

    @Override
    public Integer selectProductCount(String name, Integer state, String startTime, String endTime) {
        return productDao.selectProductCount(name,state,startTime,endTime);
    }

    @Override
    public int updateProduct(Product product) {
        return productDao.updateProduct(product);
    }

    @Override
    public int addProduct(Product product) {
        return productDao.addProduct(product);
    }
}
