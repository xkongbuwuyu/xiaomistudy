package com.xk.servlet;

import com.xk.entity.Trolley;
import com.xk.entity.User;
import com.xk.service.TrolleyService;
import com.xk.service.impl.TrolleyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "trolleyServlet", urlPatterns = "/trolleyServlet")
public class TrolleySerlet extends BaseServlet {
    private TrolleyService trolleyService = new TrolleyServiceImpl();

    /**
     * 增加购物车
     *
     * @param request
     * @param response
     * @throws IOException
     */
    public void addTrolley(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取到输入流对象
        PrintWriter pw = response.getWriter();
        //获取前端传递的参数
        int pid = Integer.parseInt(request.getParameter("pid"));
        int num = Integer.parseInt(request.getParameter("num"));
        User user = (User) request.getSession().getAttribute("user");
        int uid = user.getId();
        Trolley t = new Trolley();
        t.setUid(uid);
        t.setPid(pid);
        t.setNumber(num);
        int n = trolleyService.addTrolley(t);
        if (n > 0) {
            pw.print(true);
        } else {
            pw.print(false);
        }
        pw.close();
    }

    /**
     * 用来获取当前用户的购物车信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    public void showTrolley(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        //获取到uid
        User user = (User) request.getSession().getAttribute("user");
        int uid = user.getId();
        List<Trolley> trolleyList = trolleyService.selectAllTrolley(uid);
        Integer count = trolleyService.selectTrolleyCount(uid);
        request.getSession().setAttribute("count",count);
        //存入到作用域中
        request.setAttribute("trolleyList",trolleyList);
        //进行页面转发
        request.getRequestDispatcher("trolley.jsp").forward(request,response);

    }

    public void deleteBy(HttpServletRequest request,HttpServletResponse response) throws IOException{
        //获取到输出流
        PrintWriter pw = response.getWriter();
        //获取前端参数
        String tid = request.getParameter("tid");
        int num = trolleyService.deleteOrAll(tid);
        if (num >0){
            pw.print(true);
        }else {
            pw.print(false);
        }

        //关闭资源
        pw.close();

    }

}
