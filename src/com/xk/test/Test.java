package com.xk.test;

import com.xk.dao.ProductDao;
import com.xk.dao.impl.ProductDaoImpl;
import com.xk.entity.Orders;
import com.xk.entity.Product;
import com.xk.service.OrdersService;
import com.xk.service.impl.OrdersServiceImpl;
import com.xk.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.util.List;

public class Test {
    public static void main(String[] args) {
//        QueryRunner qrDb = DBUtils.getInstance().getQrDb();
//        System.out.println(qrDb);
//        OrdersService ordersService = new OrdersServiceImpl();
//        List<Orders> ordersList = ordersService.selectByUid(1);
//        System.out.println(ordersList);

        ProductDao productDao = new ProductDaoImpl();
        List<Product> productList = productDao.selectProductPage("小米", null, null, null, 1, 10);
        for (Product pro : productList) {
            System.out.println(pro.getName() + "\t" + pro.getState() + "\t" + pro.getProduct_date());
        }
        Integer count = productDao.selectProductCount(null, 4, null, null);
        System.out.println(count);


    }
}
