﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <link rel="stylesheet" href="css/index.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <script type="text/javascript">

        function checkall() {
            var alls = document.getElementsByName("check");
            var ch = document.getElementById("checkall");
            if (ch.checked) {
                for (var i = 0; i < alls.length; i++) {
                    alls[i].checked = true;
                }
            } else {
                for (var i = 0; i < alls.length; i++) {
                    alls[i].checked = false;
                }
            }
        }

        function delAll() {
            var alls = document.getElementsByName("check");
            var ids = new Array();
            for (var i = 0; i < alls.length; i++) {
                if (alls[i].checked) {
                    ids.push(alls[i].value);
                }
            }
            if (ids.length > 0) {
                if (confirm("确认操作?")) {
                    //alert(ids);
                    var tid = ids+"";
                    deleteBy(tid);
                }
            } else {
                alert("请选中要操作的项");
            }
        }

        function addOrders() {
            var alls = document.getElementsByName("check");
            var ids = new Array();
            for (var i = 0; i < alls.length; i++) {
                if (alls[i].checked) {
                    ids.push(alls[i].value);
                }
            }
            if (ids.length > 0) {
                if (confirm("确认操作?")) {
                    //alert(ids);
                    var tid = ids+"";
                    // alert(tid);
                    //发送ajax请求生成一个订单号
                    addOrdersByTid(tid);
                    // deleteBy(tid);
                }
            } else {
                alert("请选中要操作的项");
            }
        }

        function addOrdersByTid(tid) {
            $.ajax({
                "url": "${pageContext.request.contextPath}/ordersServlet",
                "type": "post",
                "data": {"mark": "addOrders", "tid": tid},
                "dataType": "text",
                "success": function (data) {
                    if (data == "true") {
                        alert("订单生成成功");
                        <%--window.location.href="${pageContext.request.contextPath}/trolleyServlet?mark=showTrolley";--%>
                        window.location.href="${pageContext.request.contextPath}/payServlet?mark=pay";
                    } else {
                        alert("订单生成失败");
                    }
                },
                "error": function () {
                    alert("请求失败");
                }
            });

        }

        function deleteBy(tid) {
            //使用ajax发送请求
            $.ajax({
                "url": "${pageContext.request.contextPath}/trolleyServlet",
                "type": "post",
                "data": {"mark": "deleteBy", "tid": tid},
                "dataType": "text",
                "success": function (data) {
                    if (data == "true") {
                        alert("删除成功");
                        window.location.href = "${pageContext.request.contextPath}/trolleyServlet?mark=showTrolley"
                    } else {
                        alert("删除失败");
                    }
                },
                "error": function () {
                    alert("请求失败");
                }
            });

        }
    </script>
</head>
<body>
<div class="box">
    <!-- 购物车数据动态临时存放数量的一个input标签 -->
    <input id="trollyNumber" type="hidden" value="">
    <div class="inner whiteGL">
        <div class="left">
            <a class="mix" href="">仿小米商城-学习专用</a>
        </div>
        <div class="right">
            <!-- 判断是否登录 -->
            <c:if test="${sessionScope.user == null || sessionScope.user == '' }">
                <a class="mix" href="login.jsp">登录</a>
                <a href="register.jsp">注册</a>
            </c:if>
            <c:if test="${sessionScope.user != null }">
                <a class="mix" href="xiangqing.html"> 欢迎
                        ${sessionScope.user.username } 登录！</a>
            </c:if>

            <a class="max" href="">消息通知</a>
            <a href="trolleyServlet?fs=findTrolleyGoodsList">购物车(${sessionScope.count})</a>
        </div>
    </div>
</div>

<div class="order_head">
    <div class="head_background">
        <div class="head_box">
            <a href="index.html" class="head_left_a"><img src="img/logo.jpg" alt="" class="head_left_p"></a>
            <h1 class="head_h1">我的购物车</h1>
            <div class="head_right">
                <span class="head_right_in" style="cursor: pointer;" onclick="delAll()">清空购物车 </span>
            </div>

        </div>
    </div>
</div>
<div class="trolley_background">
    <div class="trolley_background_in">
        <div class="tro_tab_h">
            <div class="col tro_tab_check">
                <h1 class="tro_tab_check_p">全选</h1>
                <span class="tro_tab_check_sp"><input type="checkbox" id="checkall" onChange="checkall();"></span>

            </div>
            <div class="col tro_tab_img">
            </div>
            <p class="col tro_tab_name">商品名称</p>
            <div class="col tro_tab_price">单价</div>
            <div class="col tro_tab_num">数量</div>
            <div class="col tro_tab_total">小计</div>
            <div class="col tro_tab_action">操作</div>
        </div>
        <c:forEach items="${trolleyList}" var="trolley">
            <div class="tro_tab_h1">
                <div class="col tro_tab_check">
                    <h1 class="tro_tab_check_p" style="background-color: #fff">
                        <input type="checkbox" name="check" value="${trolley.tid}">
                    </h1>
                    <span class="tro_tab_check_sp"></span>

                </div>
                <div class="col tro_tab_img">
                </div>
                <div class="col tro_tab_name">
                    <!--<h2 tro_tab_name_h2>小米电视4A 32英寸 黑色 32英寸</h2>-->
                    <li class="tro_tab_name_li1" style="font-size: 16px;">${trolley.product.name}</li>
                </div>
                <div class="col tro_tab_price">
                    <span id="price">${trolley.product.price}</span><span>元</span>
                </div>
                <div class="col tro_tab_num">
                    <input type="count" value="${trolley.number}" id="num">
                </div>
                <div class="col tro_tab_total ">

                    <span class="tro_tab_total_value" id="prices">${trolley.number*trolley.product.price}</span>元
                </div>
                <div class="col tro_tab_action" style="cursor: pointer;width: 40px;height: 40px;"
                     onclick="deleteBy('${trolley.tid}')">删除
                </div>
            </div>
        </c:forEach>


    </div>

    <div class="tro_close_bot ">
        <!--<p class="tro_bot_ppp">+</p>-->

        <p class="tro_close_p "><a href="">继续购物 </a></p>

        <p class="tro_close_p_r" style="cursor: pointer;" onclick="addOrders()">去结算</p>
    </div>

</div>
</body>
</html>