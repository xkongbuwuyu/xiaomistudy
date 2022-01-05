package com.xk.servlet;

import com.xk.entity.User;
import com.xk.service.UserService;
import com.xk.service.impl.UserServiceImpl;
import com.xk.utils.PageUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "userServlet", urlPatterns = "/userServlet")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();


    //注册功能
    public void register(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //定义一个标记记录文件上传的状态
        boolean saveFlag = false;
        //构架一个新的User对象
        User user = new User();
        //创建一个固定的文件夹来保存所有上传图片
        String realPath = request.getServletContext().getRealPath("/upload");
        //构建一个文件对象
        File f = new File(realPath);
        //判断文件夹是否存在 如果不存在则创建
        if (!f.exists()) {
            //创建这个文件夹
            f.mkdirs();
        }
        //判断是否以文件的格式提交
        //如果是true 表示是以文件的形式提交
        boolean flag = ServletFileUpload.isMultipartContent(request);
        if (flag) {
            //得到工厂对象
            FileItemFactory factory = new DiskFileItemFactory();
            //获取到核心对象
            ServletFileUpload sf = new ServletFileUpload(factory);
            //获取到所有的input标签
            List<FileItem> fileItemList = sf.parseRequest(request);
            //转换为迭代器
            Iterator<FileItem> iterator = fileItemList.iterator();
            //进行遍历
            // iterator.hasNext()判断是否有下一个元素
            while (iterator.hasNext()) {
                FileItem item = iterator.next();
                //判断是图片还是是普通文本 //返回值为true 则是普通的文本
                //否则则是文件
                if (item.isFormField()) {
                    //获取input标签的name属性值
                    String typeName = item.getFieldName();
                    if ("name".equals(typeName)) {
                        user.setName(item.getString("UTF-8"));
                    } else if ("sex".equals(typeName)) {
                        user.setSex(Integer.parseInt(item.getString("UTF-8")));
                    } else if ("phone_number".equals(typeName)) {
                        user.setPhone_number(item.getString("UTF-8"));
                    } else if ("area".equals(typeName)) {
                        user.setArea(item.getString("UTF-8"));
                    } else if ("username".equals(typeName)) {
                        user.setUsername(item.getString("UTF-8"));
                    } else if ("password".equals(typeName)) {
                        user.setPassword(item.getString("UTF-8"));
                    }

                } else {
                    //获取文件的名称
                    String fileName = item.getName();
                    //保证是图片 根据后缀名来进行判断
                    if (fileName.endsWith("png") || fileName.endsWith("jpg") || fileName.endsWith("jpeg") || fileName.endsWith("gif")) {
                        //文件是唯一
                        long time = System.currentTimeMillis();
                        String newFileName = time + fileName;
                        //将文件写入到服务器中
                        File fp = new File(f, newFileName);
                        item.write(fp);
                        user.setPhoto(newFileName);
                        //将状态设置为true
                        saveFlag = true;

                    } else {
                        request.setAttribute("msg", "文件的名称不合法");
                        request.getRequestDispatcher("register.jsp").forward(request, response);
                    }
                }
            }
            //将数据保存到数据库中
            if (saveFlag) {
                //再将数据插入数据库中
                user.setCreate_time(new Date());
                int num = userService.register(user);
                if (num > 0) {
                    //跳转到登录界面
                    response.sendRedirect("login.jsp");
                } else {
                    request.setAttribute("msg", "注册失败");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("msg", "图片上传失败");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("msg", "不是正常的格式进行提交");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
        //System.out.println("注册");
    }

    public void login(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("登录");
    }

    public void checkedCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取到输入流对象
        PrintWriter pw = response.getWriter();
        //获取前端传递的验证码
        String code = request.getParameter("code");
        //需要服务器的code进行验证
        // 从session中获取值
        String sessionCode = (String) request.getSession().getAttribute("code");
        //定义一个标记来记录其状态
        boolean codeFlag = false;
        //使用选择结构进行判断
        //equalsIgnoreCase 忽略字符串的大小写
        if (sessionCode.equalsIgnoreCase(code)) {
            codeFlag = true;
        } else {
            codeFlag = false;
        }
        pw.print(codeFlag);
        //关闭资源
        pw.close();
    }

    public void checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //获取前端传输的参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.selectByUsernameAndPassword(username, password);
        if (user != null) {
            //将用户的信息存入到session中
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            //实例化对象
            IndexServlet indexServlet = new IndexServlet();
            indexServlet.showInfoIndex(request, response);

            //登录成功跳转index.jsp
            //response.sendRedirect("index.jsp");
        } else {
            //登录失败跳转到登录界面
            request.setAttribute("msg", "用户名或者密码不正确");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }


    //注册查询电话号码
    public void checkedPhoneNumber(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取到输出流
        PrintWriter pw = response.getWriter();
        //获取前端传递参数
        String phoneNum = request.getParameter("phoneNumber");
        //调用service
        boolean flag = userService.selectByPhoneNum(phoneNum);
        pw.print(flag);
        //关闭资源
        pw.close();

    }


    //注册查询用户名
    public void checkedUserName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取到输出流
        PrintWriter pw = response.getWriter();
        //获取前端传递参数
        String userName = request.getParameter("userName");
        //调用service
        boolean flag = userService.selectUserName(userName);
        pw.print(flag);
        //关闭资源
        pw.close();
    }

    /**
     * 后台登录
     *
     * @param request
     * @param response
     */
    public void adminLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.selectByUsernameAndPasswordAndManager(username,
                password, 1);
        if (user == null) {
            //跳转到登录界面
            request.setAttribute("msg", "用户民与密码输入不正确");
            request.getRequestDispatcher("admin/login.jsp").forward(request, response);
        } else {
            //跳转到首页
            response.sendRedirect("admin/main.jsp");
        }
    }

    /**
     * 用户管理
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void showAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取前端传递的参数 当前页 页量
        String currentPageNoStr = request.getParameter("currentPageNo");
        String pageSizeStr = request.getParameter("pageSizeStr");
        Integer currentPageNo = null;
        if (currentPageNoStr == null || "".equals(currentPageNoStr)) {
            currentPageNo = 1;
        } else {
            currentPageNo = Integer.parseInt(currentPageNoStr);
        }
        Integer pageSize = null;
        if (pageSizeStr == null || "".equals(pageSizeStr)) {
            pageSize = 2;
        } else {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        //查询出总记录数
        Integer totalPageCount = userService.selectPageCount();
        //根据总记录数来算总页数
        Integer totalPageSize = totalPageCount % pageSize == 0 ? totalPageCount / pageSize : totalPageCount / pageSize + 1;
        List<User> userList = userService.selectPage(currentPageNo, pageSize);


        //实例化工具类
        PageUtils<User> pageUtils = new PageUtils<>();
        pageUtils.setCurrentPageNo(currentPageNo);
        pageUtils.setPageSize(pageSize);
        pageUtils.setTotalPageCount(totalPageCount);
        pageUtils.setTotalPageSize(totalPageSize);
        pageUtils.setList(userList);
        request.setAttribute("pageUtils", pageUtils);

        request.getRequestDispatcher("admin/user_list.jsp").forward(request, response);


//        List<User> userList = userService.selectAll();
//        request.setAttribute("userList",userList);
//
//        request.getRequestDispatcher("admin/user_list.jsp").forward(request,response);
    }

    /**
     * 管理员的操作
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void changeManager(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取前端传递的参数
        String uid = request.getParameter("id");
        String manager = request.getParameter("manager");
        //调用service层的方法
        int num = userService.updateManager(Integer.parseInt(uid), Integer.parseInt(manager));
        //调用方法返回到首页
        showAdmin(request, response);

    }

    public void deleteUserBatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();
        //获取前端传递的参数
        String id = request.getParameter("id");
        int num = userService.deleteAllBatch(id);
        if (num > 0) {
            pw.print(true);
        } else {
            pw.print(false);
        }
        pw.close();
    }

}

