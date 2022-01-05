package com.xk.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text.html;charset=UTF-8");

        //传递前端传递需要执行的方法
        String mark = req.getParameter("mark");
        //对字符串进行非空判断
        if (mark == null || "".equals(mark)) {
            //抛出一个异常
            throw new RuntimeException("方法名不存在");

        }

        try {
            //获取Class对象
            Class cla = this.getClass();
            //获取方法
            Method method = cla.getMethod(mark, HttpServletRequest.class, HttpServletResponse.class);
            //调用方法
            method.invoke(this, req, resp);
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
