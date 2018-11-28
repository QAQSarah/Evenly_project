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

<title>新增VIP用户页面</title>
<link rel="shortcut icon" href="/favicon.ico">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.0/jquery-confirm.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.0/jquery-confirm.min.js"></script>
</head>
<style type="text/css">
</style>

<body>
	<center>
		<form id="newUser">
			<span>用 户 名:</span><input type="text" id="username" name="username"><br>
			<span>姓 &nbsp;&nbsp;&nbsp;&nbsp;名:</span><input type="text"
				name="realname" id="realname"><br> <span>密
				&nbsp;&nbsp;&nbsp;&nbsp;码:</span><input type="password" name="password"
				id="password"><br> <span>重复密码:</span><input
				type="password" id="repassword"><br> <span>手机号码:</span><input
				type="text" name="phone" id="phone"><br> <span>地&nbsp;&nbsp;&nbsp;&nbsp;
				址: </span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;</span>
			<br />&nbsp;&nbsp; <select id="sheng" onchange="loadInfo()">
				<option value="">--请选择- -</option>
				<c:forEach items="${provinces}" var="province">
					<option value="${province.id}">${province.shortName}</option>
				</c:forEach>
			</select> 省&nbsp;&nbsp; <select id="shi" onchange="loadInfo1()">
				<option value="">--请选择- -</option>
			</select> 市 &nbsp;&nbsp; <select id="xian">
				<option value="">--请选择- -</option>
			</select>县 <br> <span>详细地址:</span><input type="text" name="detailAddress"
				id="detailAddress"><br> <br> <input type="button"
				value="保存" onclick="addVip()"> <a href="/showvip"
				style="text-decoration: none;"><input type="button" value="返回"></a>
		</form>
	</center>
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
	
	function loadInfo() {
		var praviceID = $("#sheng").val();
		$.ajax({
			url : "ejld/" + praviceID,
			type : "POST",
			dataType : "json",
			data : {
				"pravice" : praviceID
			},
			success : function(data) {
				$("#shi").html("");//先清空
				//遍历传过来的list集合
				$.each(data.cities, function(i, sheng) {
					$("#shi").append(
							"<option value='"+sheng.id+"'>" + sheng.name
									+ "</option>")
				})
				$("#xian").html("");//先清空
			}
		});
	}
	function loadInfo1() {
		var praviceID = $("#shi").val();
		$.ajax({
			url : "ejld/" + praviceID,
			type : "POST",
			dataType : "json",
			data : {
				"pravice" : praviceID
			},
			success : function(data) {
				$("#xian").html("");//先清空
				//遍历传过来的list集合
				$.each(data.cities, function(i, sheng) {
					$("#xian").append(
							"<option value='"+sheng.id+"'>" + sheng.name
									+ "</option>")
				})
			}
		});
	}
	function addVip() {
		var provinceId = $("#sheng").val();
		var cityId = $("#shi").val();
		var countyId = $("#xian").val();
		var detailAdress = $("#detailAddress").val();
		var username = $("#username").val();
		var realname = $("#realname").val();
		var phone = $("#phone").val();
		var password = $("#password").val();
		var data = {
			"provinceId" : provinceId,
			"cityId" : cityId,
			"countyId" : countyId,
			"detailAdress" : detailAdress,
			"username" : username,
			"realname" : realname,
			"phone" : phone,
			"password" : password

		}

		$.ajax({
			url : "saveVip", // 提交到controller的url路径
			type : "post", // 提交方式
			data : data, // data为String类型，必须为 Key/Value 格式。
			dataType : "json", // 服务器端返回的数据类型
			success : function(data) { // 请求成功后的回调函数，其中的参数data为controller返回的map,也就是说,@ResponseBody将返回的map转化为JSON格式的数据，然后通过data这个参数取JSON数据中的值
				alert(data.msg)
				window.location.href = 'showvip';
			},
			erro:function(){
				alert(data.msg)
				window.location.href = 'addvip';
			}
		});

	}
</script>
</html>
