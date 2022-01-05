package com.xk.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "payServlet",urlPatterns = "/payServlet")
public class PayServlet extends BaseServlet {
    public void pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //跳转界面
        request.getRequestDispatcher("pay/index.jsp").forward(request, response);
    }

}
