<%--
  Created by IntelliJ IDEA.
  User: NuanYang
  Date: 2021/12/28
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>二维码生成</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js">
    </script>
    <script src="${pageContext.request.contextPath}/js/qrcode.js"></script>
    <style>
        div {
            width: 200px;
            height: 200px;
            margin: 0px auto;
            margin-top: 40px;
        }
    </style>
</head>
<body>
<div id="qrcode">
</div>
<script>
    var qrcode = new QRCode(document.getElementById("qrcode"), {
        text: "涛哥暗恋萍乡班花很久了,准备进行表白",
        width: 200,
        height: 200,
        colorDark: "#000000",
        colorLight: "#ffffff",
        correctLevel: QRCode.CorrectLevel.H
    });

</script>
</body>
</html>
