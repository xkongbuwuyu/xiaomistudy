<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>用户信息</title>
    <link href="${pageContext.request.contextPath}/admin/css/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>

    <script type="text/javascript">

        // old write
        $(document).ready(function () {
            $(".click").click(function () {
                $(".tip").fadeIn(200);
            });

            $(".tiptop a").click(function () {
                $(".tip").fadeOut(200);
            });

            $(".sure").click(function () {
                $(".tip").fadeOut(100);
            });

            $(".cancel").click(function () {
                $(".tip").fadeOut(100);
            });

        });


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
                    //ajax 需要将数组转化为字符串来进行传递
                    var id = ids + "";
                    deleteUserBatch(id);

                }
            } else {
                alert("请选中要操作的项");
            }
        }

        //使用ajax来进行删除
        function deleteUserBatch(id) {
            //发送ajax请求
            $.ajax({
                "url": "${pageContext.request.contextPath}/userServlet",
                "type": "post",
                "data": {"mark": "deleteUserBatch", "id": id},
                "dataType": "text",
                "success": function (data) {
                    if (data == "true") {
                        alert("删除成功");
                        window.location.href = "${pageContext.request.contextPath}/userServlet?mark=showAdmin";
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

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">商品管理</a></li>
    </ul>
</div>

<div class="rightinfo">

    <div class="tools">

        <ul class="toolbar">

            <!--

            <li class="click"><span><img src="images/t02.png" /></span>修改</li>
            <li><span><img src="images/t04.png" /></span>统计</li>
             -->
            <li id="batchDelete" onclick="delAll()" style="cursor: pointer;"><span>
			<img src="${pageContext.request.contextPath}/admin/images/t03.png"/></span>批量删除
            </li>
        </ul>

    </div>
    <table class="tablelist">
        <thead>
        <tr>
            <th><input type="checkbox" id="checkall" onChange="checkall();"/></th>
            <th>序号<i class="sort"><img src="${pageContext.request.contextPath}/admin/images/px.gif"/></i></th>
            <th>姓名</th>
            <th>性别</th>
            <th>电话号码</th>
            <th>所在地区</th>
            <th>权限</th>
            <th>账号</th>
            <th>头像</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageUtils.list}" var="user">
            <tr>
                <td><input name="check" type="checkbox" value="${user.id}"/></td>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>
                    <c:if test="${user.sex==0}">男</c:if>
                    <c:if test="${user.sex==1}">女</c:if>
                </td>
                <td>${user.phone_number}</td>
                <td>${user.area}</td>
                <td>
                    <c:if test="${user.manager==0}">用户</c:if>
                    <c:if test="${user.manager==1}"> <font style="font-weight: bold;color: red">管理员</font></c:if>
                </td>
                <td>${user.username}</td>
                <td style="text-align: center;">
                    <img src="${pageContext.request.contextPath}/file/1.jpg" width="100" height="100" alt=""/>
                </td>
                <td>
                    <c:if test="${user.manager==0}">
                        <a href="${pageContext.request.contextPath}/userServlet?mark=changeManager&id=${user.id}&manager=1">指定管理员</a>
                    </c:if>
                    <c:if test="${user.manager==1}">
                        <a style="color: red"
                           href="${pageContext.request.contextPath}/userServlet?mark=changeManager&id=${user.id}&manager=0">撤销管理员</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="pagin">
        <div class="message">共<i class="blue">${pageUtils.totalPageCount}</i>条记录，
            当前显示第&nbsp;<i class="blue">${pageUtils.currentPageNo}&nbsp;</i>页
        </div>
        <ul class="paginList">

            <li class="paginItem"><a
                    href="${pageContext.request.contextPath}/userServlet?mark=showAdmin&currentPageNo=1">首页</a></li>
            <c:if test="${pageUtils.currentPageNo >1}">
                <li class="paginItem"><a
                        href="${pageContext.request.contextPath}/userServlet?mark=showAdmin&currentPageNo=${pageUtils.currentPageNo-1}">上一页</a>
                </li>
            </c:if>
            <c:if test="${pageUtils.currentPageNo < pageUtils.totalPageSize}">
                <li class="paginItem"><a
                        href="${pageContext.request.contextPath}/userServlet?mark=showAdmin&currentPageNo=${pageUtils.currentPageNo+1}">下一页</a>
                </li>
            </c:if>
            <li class="paginItem"><a
                    href="${pageContext.request.contextPath}/userServlet?mark=showAdmin&currentPageNo=${pageUtils.totalPageSize}">尾页</a>
            </li>
        </ul>
    </div>


    <div class="tip">
        <div class="tiptop"><span>提示信息</span><a></a></div>

        <div class="tipinfo">
            <span><img src="images/ticon.png"/></span>
            <div class="tipright">
                <p>是否确认对信息的修改 ？</p>
                <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
            </div>
        </div>

        <div class="tipbtn">
            <input name="" type="button" class="sure" value="确定"/>&nbsp;
            <input name="" type="button" class="cancel" value="取消"/>
        </div>

    </div>


</div>

<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>
</body>
</html>
