package com.xk.servlet;

import com.xk.entity.Category;
import com.xk.entity.Product;
import com.xk.service.CategoryService;
import com.xk.service.ProductService;
import com.xk.service.impl.CategoryServiceImpl;
import com.xk.service.impl.ProductServiceImpl;
import com.xk.utils.PageUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static javax.swing.text.StyleConstants.Size;

@WebServlet(name = "productServlet", urlPatterns = "/productServlet")
public class ProductServlet extends BaseServlet {
    private ProductService productService = new ProductServiceImpl();
    private CategoryService categoryService = new CategoryServiceImpl();

    /**
     * 产品详情页
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void showByPid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取前端传递的参数
        String pid = request.getParameter("pid");
        Product product = productService.selectByPid(Integer.parseInt(pid));
        //将对象存入到作用域中
        request.setAttribute("product", product);
        //转发到页面
        request.getRequestDispatcher("product_detail.jsp").forward(request, response);
    }


    /**
     * 分页管理
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void showProductAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String stateStr = request.getParameter("state");
        Integer state = null;
        if (stateStr == null || "".equals(stateStr)) {
            state = null;
        } else {
            state = Integer.parseInt(stateStr);
        }
        //获取到当前页与页量
        String currentPageNoStr = request.getParameter("currentPageNo");
        Integer currentPageNo = null;
        if (currentPageNoStr == null || "".equals(currentPageNoStr)) {
            currentPageNo = 1;
        } else {
            currentPageNo = Integer.parseInt(currentPageNoStr);
        }
        //获取页量
        String pageSizeStr = request.getParameter("pageSize");
        Integer pageSize = null;
        if (pageSizeStr == null || "".equals(pageSizeStr)) {
            pageSize = 5;
        } else {
            pageSize = Integer.parseInt(pageSizeStr);
        }
        //查询出总记录数
        Integer totalPageCount = productService.selectProductCount(name, state, startTime, endTime);
        //根据总记录数 与页量计算总页数
        Integer totalPageSize = totalPageCount % pageSize == 0 ? totalPageCount / pageSize : totalPageCount / pageSize + 1;
        //查询出集合
        List<Product> productList = productService.selectProductPage(name, state, startTime, endTime, currentPageNo, pageSize);
        //实例化工具类
        PageUtils<Product> pageUtils = new PageUtils<>();
        pageUtils.setCurrentPageNo(currentPageNo);
        pageUtils.setPageSize(pageSize);
        pageUtils.setTotalPageCount(totalPageCount);
        pageUtils.setTotalPageSize(totalPageSize);
        pageUtils.setList(productList);
        //将工具类对象存入到作用域中
        request.setAttribute("pageUtils", pageUtils);
        //回显状态
        request.setAttribute("name", name);
        request.setAttribute("state", state);
        request.setAttribute("startTime", startTime);
        request.setAttribute("endTime", endTime);
        //进行页面转发

        request.getRequestDispatcher("admin/product_list.jsp").forward(request, response
        );
    }

    /**
     * 产品后台
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void goUpdateProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获取前端传递的参数
        String pid = request.getParameter("pid");
        Product product = productService.selectByPid(Integer.parseInt(pid));
        List<Category> categoryList = categoryService.selectCategory(14);
        //将对象存入到作用域中
        request.setAttribute("product", product);
        request.setAttribute("categoryList", categoryList);
        //转发到页面
        request.getRequestDispatcher("admin/product_update.jsp").forward(request, response);
    }

    //封装一个工具的方法返回对象
    public Product getProduct(HttpServletRequest request,HttpServletResponse
            response) throws ParseException {
        String cid = request.getParameter("cid");
        String name = request.getParameter("name");
        String color = request.getParameter("color");
        String price = request.getParameter("price");
        String description = request.getParameter("description");
        String state = request.getParameter("state");
        String version = request.getParameter("version");
        String product_date = request.getParameter("product_date");
        String pid = request.getParameter("pid");
        //实例化产品对象
        Product product = new Product();
        product.setCid(Integer.parseInt(cid));
        product.setName(name);
        product.setColor(color);
        product.setPrice(price);
        product.setDescription(description);
        product.setState(Integer.parseInt(state));
        product.setVersion(version);
        product.setProduct_date(new SimpleDateFormat("yyyy-MM-dd").parse(product_date));
        return product;
    }


    public void updateProduct(HttpServletRequest request, HttpServletResponse
            response) throws ParseException, ServletException, IOException {
        Product product = getProduct(request, response);
        String pid = request.getParameter("pid");
        product.setPid(Integer.parseInt(pid));
        //调用service层
        int num = productService.updateProduct(product);
        //重定向(两次请求) 与转发
        response.sendRedirect("productServlet?mark=showProductAdmin");
        //showProductAdmin(request,response);
    }

    public void goAddProduct(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        List<Category> categoryList = categoryService.selectCategory(14);
        request.setAttribute("categoryList", categoryList);
        request.getRequestDispatcher("admin/product_add.jsp").forward(request, response);
    }

    public void addProduct(HttpServletRequest request,HttpServletResponse
            response) throws ParseException, IOException {
        Product product = getProduct(request, response);
        product.setPic("d673a98c-4721-47f7-b61b-ddaa3398da05582.jpg");
        int num = productService.addProduct(product);
        //重定向(两次请求) 与转发
        response.sendRedirect("productServlet?mark=showProductAdmin");
    }




}