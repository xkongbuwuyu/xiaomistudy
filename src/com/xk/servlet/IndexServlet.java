package com.xk.servlet;

import com.xk.entity.Category;
import com.xk.entity.Product;
import com.xk.entity.User;
import com.xk.service.CategoryService;
import com.xk.service.ProductService;
import com.xk.service.TrolleyService;
import com.xk.service.impl.CategoryServiceImpl;
import com.xk.service.impl.ProductServiceImpl;
import com.xk.service.impl.TrolleyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "indexServlet",urlPatterns = "/indexServlet")
public class IndexServlet extends BaseServlet {
    private CategoryService categoryService = new CategoryServiceImpl();
    private ProductService productService = new ProductServiceImpl();
    private TrolleyService trolleyService = new TrolleyServiceImpl();

    public void showInfoIndex(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        List<Category> categoryList = categoryService.selectCategory(10);
        //将数据存入到作用域中
        request.setAttribute("categoryList", categoryList);

        //查询产品信息
        List<Product> productListming = productService.selectByState(2);
        //将数据存入作用域中
        request.setAttribute("productListming",productListming);

        //查询出商品分类为家电产品
        List<Product> productListjiadian = productService.selectCname("家电");
        //将数据存入作用域中
        request.setAttribute("productListjiadian",productListjiadian);

        //从session获取用户
        User user = (User) request.getSession().getAttribute("user");
        //查询当前用户购物车的总数
        Integer count = trolleyService.selectTrolleyCount(user.getId());
        //将数据存入作用域中
        request.getSession().setAttribute("count",count);

        //转发到界面
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}

