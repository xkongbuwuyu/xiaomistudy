<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册页面</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
	<style>
		span{
			color: red;
		}
	</style>
</head>
<body>
<div class="sign_background">
    <div class="sign_background_in">
        <div class="sign_background_no1">
            <a href="index.html"><img src="img/logo.jpg" alt=""></a>
        </div>
        <div class="sign_background_no2">注册小米帐号</div>
           <div class="sign_background_no3">
        	<font color='red'></font>
            <div class="sign_background_no5">
             	<!-- enctype="multipart/form-data" -->
             	<form id="f4"  method="post" action="${pageContext.request.contextPath}/userServlet?mark=register"
				 enctype="multipart/form-data">
         
             		
             		<table style="width: 500px;" border="0" cellspacing="0">
             			<tr>
             				<td width="25%" class="_left">姓名：</td>
             				<td><input type="text" name="name"><span id="s_name"></span></td>
             			</tr>
             			<tr>
             				<td width="25%" class="_left">性别：</td>
             				<td>
             					男<input type="radio" name="sex" checked="checked" value="1">
             				 	女<input type="radio" name="sex" value="0">
							</td>
             			</tr>
             			<tr>
             				<td width="25%" class="_left">电话号码：</td>
             				<td><input type="text" id="phone_number" name="phone_number" ><span id="s_phone_number"></span></td>
             			</tr>
             			<tr>
             				<td width="25%" class="_left">所在地区：</td>
             				<td><input type="text" name="area"></td>
             			</tr>
             			<tr>
             				<td width="25%" class="_left">用户名：</td>
             				<td><input type="text" id="username" name="username" ><span id="s_username"></span></td>
             			</tr>
             			<tr>
             				<td width="25%" class="_left">密码：</td>
             				<td><input type="password" id="password" name="password" ><span id="s_password"></span></td>
             			</tr>
						<tr>
							<td width="25%" class="_left">上传头像：</td>
							<td><input type="file" name="photo"> </td>
						</tr>


             		</table>
             		<div class="sign_background_no6" id="btn" >立即注册</div>
             	</form>
             	 
            </div>
        </div>
        <div class="sign_background_no7">注册帐号即表示您同意并愿意遵守小米 <span>用户协议</span>和<span>隐私政策</span> </div>


    </div>
    <div class="sign_background_no8"><img src="img/sign01.jpg" alt=""></div>

	<script>
		//定义两个标记，记录状态是否可以提交表单（符合注册要求）
		var phoneFlag = false;
		var userNameFlag = false;

		//验证电话号码
		$("#phone_number").blur(function () {
			var phone_number = $("#phone_number").val();
			if (phone_number == null || phone_number == ""){
				$("#s_phone_number").html("电话号码不能为空")
			}else {
				//发送ajax到后端验证是否存在
				$.ajax({
					url:"${pageContext.request.contextPath}/userServlet",
					type:"post",
					data:{"mark":"checkedPhoneNumber","phoneNumber":phone_number},
					dataType:"text",
					success:function (data) {
						//alert(data);
						if (data != "true"){
							$("#s_phone_number").html("手机号已被注册");

						}else {
							$("#s_phone_number").html("可以使用");
							phoneFlag = true;
						}
					},
					error:function () {
						alert("请求失败");
					}
				});
			}

		}).focus(function () {
			$("#s_phone_number").html("")

		});

		//验证用户名
		$("#username").blur(function () {
			var username = $("#username").val();
			if (username == null || username == ""){
				$("#s_username").html("用户名不能为空")
			}else {
				//发送ajax到后端验证是否存在
				$.ajax({
					url:"${pageContext.request.contextPath}/userServlet",
					type:"post",
					data:{"mark":"checkedUserName","userName":username},
					dataType:"text",
					success:function (data) {
						//alert(data);
						if (data != "true"){
							$("#s_username").html("用户名被占用了，换一个吧");
						}else {
							$("#s_username").html("用户名可以使用");
							userNameFlag = true;
						}
					},
					error:function () {
						alert("请求失败");
					}
				});
			}

		}).focus(function () {
			$("#s_username").html("")

		});


		//提交form表单
		$("#btn").click(function () {
			if (phoneFlag == true && userNameFlag == true){
				$("#f4").submit();
			}else {
				alert("用户名与电话号码必须是唯一的")
			}

		})


	</script>

</div>

</body>
</html>