<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=Utf-8" pageEncoding="utf-8"%>
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

<title>欢迎登录jsp</title>
<link rel="shortcut icon" href="/favicon.ico">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.0/jquery-confirm.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.0/jquery-confirm.min.js"></script>

<style type="text/css">
.code {
	font-family: Arial;
	font-style: italic;
	color: blue;
	font-size: 30px;
	border: 0;
	padding: 2px 3px;
	letter-spacing: 3px;
	font-weight: bolder;
	float: left;
	cursor: pointer;
	width: 150px;
	height: 50px;
	line-height: 60px;
	text-align: center;
	vertical-align: middle;
	background-color: #D8B7E3;
}

span {
	text-decoration: none;
	font-size: 12px;
	color: #288bc4;
	padding-left: 10px;
}

span:hover {
	text-decoration: underline;
	cursor: pointer;
}
</style>
</head>

<body>
	<form id="log_frm" action="Get/login" >
	<label id="msg" style="color: red;">${sessionScope.lg_msg}</label><br/>
		用户名：<input type="text" name="name"><br>
		密&nbsp;&nbsp;&nbsp;码：<input type="password" name="password"><br>
		<div>
			<table border="0" cellspacing="5" cellpadding="5">
				<tr>
					<td>
						<div id="checkCode" class="code" onclick="createCode(4)"></div>
					</td>
					<td><span onclick="createCode(4)">看不清换一张</span></td>
				</tr>
				<tr>
					<td>验证码：</td>
					<td><input type="text" id="inputCode" style="float: left;" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="button" id="my_button" value="登录"
						onclick="validateCode()" /></td>
				</tr>
			</table>
		</div>



	</form>
</body>
<script type="text/javascript">
	//页面加载时，生成随机验证码
	window.onload = function() {
		createCode(4);
	}

	//生成验证码的方法
	function createCode(length) {
		var code = "";
		var codeLength = parseInt(length); //验证码的长度
		var checkCode = document.getElementById("checkCode");
		////所有候选组成验证码的字符，当然也可以用中文的
		var codeChars = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'a', 'b', 'c',
				'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
				'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A',
				'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
				'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
		//循环组成验证码的字符串
		for (var i = 0; i < codeLength; i++) {
			//获取随机验证码下标
			var charNum = Math.floor(Math.random() * 62);
			//组合成指定字符验证码
			code += codeChars[charNum];
		}
		if (checkCode) {
			//为验证码区域添加样式名
			checkCode.className = "code";
			//将生成验证码赋值到显示区
			checkCode.innerHTML = code;
		}
	}

	//检查验证码是否正确
	function validateCode() {
		//获取显示区生成的验证码
		var checkCode = document.getElementById("checkCode").innerHTML;
		//获取输入的验证码
		var inputCode = document.getElementById("inputCode").value;
		console.log(checkCode);
		console.log(inputCode);
		if (inputCode.length <= 0) {
			alert("请输入验证码！");
		} else if (inputCode.toUpperCase() != checkCode.toUpperCase()) {
			alert("验证码输入有误！");
			createCode(4);
		} else {
			alert("验证码正确！");
			var form = document.getElementById('log_frm');
			form.submit();
		}
	}
</script>
</html>
