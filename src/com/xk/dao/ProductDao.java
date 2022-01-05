package com.xk.dao;

import com.xk.entity.Product;

import java.util.List;

public interface ProductDao {
    //根据状态来进行查询
    List<Product> selectByState(int state);
    //根据分类名称查询
    List<Product> selectCname(String cname);
    //根据pid进行查询
    Product selectByPid(int pid);
    //分页查询
    List<Product> selectProductPage(String name,Integer state,String startTime,String endTime,Integer currentPageNo,Integer pageSize);
    Integer selectProductCount(String name,Integer state,String startTime,String endTime);

    int updateProduct(Product product);
    int addProduct(Product product);
}
