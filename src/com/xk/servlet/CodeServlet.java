package com.xk.servlet;

import com.xk.utils.VerifyCodeUtils;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name="codeServlet",urlPatterns="/codeServlet")
public class CodeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        //设置一些头的信息
        //表示禁止一切缓存包括浏览器包括jsp
        resp.setHeader("Pragma", "No-cache");
        resp.setHeader("Cache-Control", "no-cache");
        //时时更新也就是可以点击就换图片
        resp.setDateHeader("Expires", 0);
        // 表示响应的格式是图片
        resp.setContentType("image/jpeg");
        //生成验证码
        String code= VerifyCodeUtils.generateVerifyCode(4);

        //获取到session对象
        HttpSession session = req.getSession();
        //session以键值来储存
        session.setAttribute("code",code);

        //服务器生成一张图片写给客户端
        int width=200;
        int height=80;
        VerifyCodeUtils.outputImage(width,height,resp.getOutputStream(),code);
    }
}
