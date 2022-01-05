package com.xk.service;

import com.xk.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> selectByState(int state);
    List<Product> selectCname(String cname);
    Product selectByPid(int pid);

    List<Product> selectProductPage(String name,Integer state,String startTime,String endTime,Integer currentPageNo,Integer pageSize);
    Integer selectProductCount(String name,Integer state,String startTime,String endTime);

    int updateProduct(Product product);

    int addProduct(Product product);
}
