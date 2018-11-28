<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>修改用户信息</title>
  </head>
  <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
  <body>
    <form action="Get/user/updt" id="newUser">
    	<span>编号</span><input type="text" name="id" readonly value="${mduser.id}"><br>
     	<span>用户名</span><input type="text" id="name" name="name"value="${mduser.name }"><br>
     	<span>姓名</span><input type="text" name="username" id="username"value="${mduser.username }"><br>
    	<span>密码</span><input type="text" name="password" id="password" value="${mduser.password }"><br>
    	<span>重复密码</span><input type="text" id="repassword" value="${mduser.password }"><br>
     	<span>用户描述</span><input type="text" name="remark" id="remark" value="${mduser.remark }"><br>
     	<input type="submit" value="保存修改">
     	<a href="Get/users" style="text-decoration: none;"><input type="button" value="返回"></a>
    </form>
  </body>
  <script type="text/javascript" src="js/jquery.validate.js"></script>
  <script type="text/javascript"
	src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
  <script type="text/javascript">
  $(function() {
		$("#newUser").validate({
			rules: {
			      name: {
			        required: true,
			      },
			      username:{
			    	  required:true
			      },
			      password:{
			    	  required:true
			      },
			      repassword:{
			    	  equalTo:"#password"
			      },
			      remark:{
			    	  required:true
			      }
			      
			    },
			    messages: {
			    	name: {
				        required: "请输入您的用户名",
				      },
				      username:{
				    	  required:"请输入姓名"
				      },
				      password:{
				    	  required:"密码不能为空"
				      },
				      repassword:{
				    	  equalTo:"两次输入密码不一致"
				      },
				      remark:{
				    	  required:"描述为必填字段"
				      }
			    }
			});
	});
  </script>
</html>
