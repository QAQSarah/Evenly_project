<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>新增用户页面</title>
<link rel="shortcut icon" href="/favicon.ico">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.0/jquery-confirm.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.0/jquery-confirm.min.js"></script>
</head>

<body>
	<form action="Get/user/new" id="newUser">
		<label id="add_msg" style="color: red">${add_msg}</label><br> 
		<span>用户名</span><input
			type="text" id="name" name="name"><br> <span>姓名</span><input
			type="text" name="username" id="username"><br> <span>密码</span><input
			type="text" name="password" id="password"><br> <span>重复密码</span><input
			type="text" id="repassword"><br> <span>用户描述</span><input
			type="text" name="remark" id="remark"><br> <input
			type="submit" value="保存"> <a href="Get/users"
			style="text-decoration: none;"><input type="button" value="返回"></a>
	</form>
</body>
<script type="text/javascript"
	src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<script src="js/gVerify.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$("#newUser").validate({
			rules : {
				name : {
					required : true,
				},
				username : {
					required : true
				},
				password : {
					required : true
				},
				repassword : {
					equalTo : "#pswd"
				},
				remark : {
					required : true
				}

			},
			messages : {
				name : {
					required : "请输入您的用户名",
				},
				username : {
					required : "请输入姓名"
				},
				password : {
					required : "密码不能为空"
				},
				repassword : {
					equalTo : "两次输入密码不一致"
				},
				remark : {
					required : "描述为必填字段"
				}
			}
		});
	});
</script>
</html>
