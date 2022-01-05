package com.xk.service.impl;

import com.xk.dao.OrdersDao;
import com.xk.dao.ProductDao;
import com.xk.dao.TrolleyDao;
import com.xk.dao.impl.OrdersDaoImpl;
import com.xk.dao.impl.ProductDaoImpl;
import com.xk.dao.impl.TrolleyDaoImpl;
import com.xk.entity.Orders;
import com.xk.entity.Product;
import com.xk.entity.Trolley;
import com.xk.service.OrdersService;
import com.xk.utils.OrdersCodeUtils;
import com.xk.utils.VerifyCodeUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

public class OrdersServiceImpl implements OrdersService {
    private TrolleyDao trolleyDao = new TrolleyDaoImpl();
    private ProductDao productDao = new ProductDaoImpl();
    private OrdersDao ordersDao = new OrdersDaoImpl();


    @Override
    public int addOrders(String tid, int uid,HttpServletRequest request) {
        int num = 0;
        try {
            //生成订单号
            String order_number = OrdersCodeUtils.generateOrderCode((long) 9580);
            //String order_number = VerifyCodeUtils.generateOrderCode(10);
            //定义一个变量表示总数
            int count=0;
            //定义一个变量表示总价
            double sum =0;
            //插入条数据
            //修改购物车中相关联的购物车的订单号
            //对字符串进行分割
            String[] arrays = tid.split(",");
            //对数组进行非空验证
            if (arrays !=null && arrays.length >0) {
                for (String s : arrays) {
                    Trolley tro = trolleyDao.selectByTid(Integer.parseInt(s));
                    int i = trolleyDao.updateTrolley(tro.getTid(), order_number);
                    //System.out.println("更新购物车："+i);

                    int pid = tro.getPid();
                    Product pro = productDao.selectByPid(pid);
                    count += tro.getNumber();
                    sum+=(Double.parseDouble(pro.getPrice())*tro.getNumber());
                }
            }
            Orders orders= new Orders();
            orders.setUid(uid);
            orders.setCount_number(count);
            orders.setState(2);
            orders.setOrders_number(order_number);
            orders.setCreate_time(new Date());
            orders.setSum_price(sum);
            request.getSession().setAttribute("sum",sum);

            num = ordersDao.addOrders(orders);

        } catch (NumberFormatException e) {
            return -1;
        }

        return num;
    }

    @Override
    public List<Orders> selectByUid(int uid) {
        List<Orders> ordersList = ordersDao.selectByUid(uid);
        if (ordersList != null && ordersList.size()>0){
            for (Orders ord:ordersList){
                //获取订单号
                String orders_number = ord.getOrders_number();
                //根据订单号查购物车
                List<Trolley> trolleyList = trolleyDao.selectOrders_number(orders_number);
                ord.setTrolleyList(trolleyList);
                //遍历购物车对象
                if (trolleyList !=null && trolleyList.size()>0){
                    for (Trolley t : trolleyList){
                        int pid = t.getPid();
                        Product product = productDao.selectByPid(pid);
                        t.setProduct(product);
//                        System.out.println(product+"测试\n");
                    }
                }
            }
        }

        //System.out.println();
        return ordersList;
    }


}
