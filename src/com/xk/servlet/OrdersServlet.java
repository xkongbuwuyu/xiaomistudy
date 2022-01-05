package com.xk.servlet;

import com.xk.dao.ProductDao;
import com.xk.dao.impl.ProductDaoImpl;
import com.xk.entity.Orders;
import com.xk.entity.Product;
import com.xk.entity.Trolley;
import com.xk.entity.User;
import com.xk.service.OrdersService;
import com.xk.service.TrolleyService;
import com.xk.service.impl.OrdersServiceImpl;
import com.xk.service.impl.TrolleyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ordersServlet",urlPatterns = "/ordersServlet")
public class OrdersServlet extends BaseServlet {
    private TrolleyService trolleyService = new TrolleyServiceImpl();
    private ProductDao productDao = new ProductDaoImpl();
    private OrdersService ordersService = new OrdersServiceImpl();



    public void addOrders(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取到输出流
        PrintWriter pw = response.getWriter();
        //获取前端传递参数
        String tid = request.getParameter("tid");
        User user = (User) request.getSession().getAttribute("user");
        int uid = user.getId();
        int num = ordersService.addOrders(tid, uid,request);
        if (num>0){
            pw.print(true);
        }else {
            pw.print(false);
        }
    }

    public void selectOrders(HttpServletRequest request,HttpServletResponse response) throws SecurityException, IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        int uid = user.getId();
        List<Orders> ordersList = ordersService.selectByUid(uid);
        //将数据存入作用域中
        request.setAttribute("ordersList",ordersList);
        //进行转发
        request.getRequestDispatcher("orders_history.jsp").forward(request,response);
    }


//    public void addOrders(HttpServletRequest request, HttpServletResponse response) {
//        //获取前端传递参数
//        String tid = request.getParameter("tid");
//        //定义个变量记录总数
//        int count = 0;
//        //定义一个变量记录总价
//        double sum= 0;
//        //对字符串进行分割
//        String[] arrays = tid.split(",");
//        //对数组进行非空验证
//        if (arrays != null && arrays.length>0){
//            for (String s:arrays){
//                //根据tid进行查询  购物车对象及相关联对象
//                Trolley tro = trolleyService.selectByTid(Integer.parseInt(s));
//                count+= tro.getNumber();
//                Product product = productDao.selectByPid(tro.getPid());
//                sum+=(tro.getNumber()*Double.parseDouble(product.getPrice()));
//            }
//        }
//        System.out.println(sum);
//        System.out.println(count);
//
//    }
}
