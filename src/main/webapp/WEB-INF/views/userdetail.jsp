<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户详情页面</title>


  </head>
  
  <body>
    	<label id="add_msg" style="color: red">${add_msg}</label><br>
     	<span>用户名</span><input type="text" id="name" name="name"value="${user.name }"><br>
     	<span>姓名</span><input type="text" name="username" id="username"value="${user.username }"><br>
    	<span>密码</span><input type="text" name="password" id="password" value="${user.password }"><br>
     	<span>用户描述</span><input type="text" name="remark" id="remark" value="${user.remark }"><br>
     	<a href="Get/users" style="text-decoration: none;"><input type="button" value="返回首页"></a>
  </body>
</html>
