<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>修改用户信息</title>
</head>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<body>
	<form id="newUser" action="/SSH2/updateVipUser">
		<span>编 号 :</span><input type="text" name="id" id="id"
			value="${mduser.id}"><br> <span>用户名:</span><input
			type="text" id="username" name="username" value="${mduser.username }"><br>
		<span>姓 名 :</span><input type="text" name="realname" id="realname"
			value="${mduser.realname }"><br> <span>手机号码:</span><input
			type="text" name="phone" id="phone" value="${mduser.phone }"><br>
		<input type="hidden" name="password" id="password"
			value="${mduser.password }"><br>
		<c:set var="index" value="0" />
		<c:forEach items="${mduser.getAddresses()}" var="address">
			<div id="div">
				<h4>地址</h4>
				<input type="hidden" name="addresses[${index}].id" 
					value="${address.id}">
				<input type="text" name="addresses[${index}].province" id="province"
					value="${address.province}"><span>省</span><br> <input
					type="text" id="city" name="addresses[${index}].city"
					value="${address.city }"><span>市</span><br> <input
					type="text" name="addresses[${index}].county" id="county"
					value="${address.county }"><span>县(区)</span><br> <span>详细地址</span><input
					type="text" name="addresses[${index}].detailAddress"
					id="detailAddress" value="${address.detailAddress }"><br>
				<c:set var="index" value="${index+1}" />
			</div>
		</c:forEach>
		<input type="submit" value="保存修改"> <a href="showvip"
			style="text-decoration: none;"><input type="button" value="返回"></a>
	</form>
</body>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript"
	src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
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
					equalTo : "#password"
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
