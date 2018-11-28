<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>这里输入标题</title>
    <style type="text/css">
    td{ margin: 0 20px; text-align: center;}
    fieldset{ width: 590px;}
    </style>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
  </head>
  
  <body>
  
  	<fieldset title="查询条件">
  		<legend>查询条件</legend>
  		<form action="Get/user/getUsersBy">
  		<span>姓名：</span><input name="username" type="text" id="username">
  		&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="查询">
  		&nbsp;&nbsp;<a href="Get/user/add"><input type="button" value="新增"></a>
  		&nbsp;&nbsp;<a href="Get/user/logout"><input type="button" value="退出"></a>
  		</form>
  	</fieldset >
  	<br>
  
    <table style="border:1px solid;">
    	<thead><tr><th>用户编号</th><th><a href="Get/user/orderBy">用户名</a></th><th>姓名</th><th>密码</th><th>描述</th><th>操作</th></tr></thead>
    	<tbody>
    	<c:forEach items="${requestScope.users}" var="user">
    	<tr><td><a href="Get/user?id=${user.id}">${user.id}</a></td><td>${user.name}</td><td>${user.username}</td><td>${user.password}</td>
    	<td>${user.remark}</td>
    	<td><a href="Get/user/${user.id}/edit">修改</a>&nbsp;/&nbsp;<a href="javascript:void(0);" onclick="delUser(this);">删除</a></td>
    	</tr>
    	</c:forEach>
    	</tbody>
    </table>
    
  </body>
  <script type="text/javascript">
 	function delUser(t) {
 		var id=$(t).parent().parent().find("a:first-child").html();
 		var name=$(t).parent().parent().find("td:nth-child(2)").html();
 		alert(id);
		if(confirm("确定要删除用户"+name+"么？")){
			$.ajax({
				url:"Get/user/"+id+"/confirmDelete",
				type:"POST",
				dataType:"JSON",
				data:{
					id:id,
				},
				success:function(msg){
  						$(t).parent().parent().remove();
  						alert(msg.msg);
				},
				erro:function(msg){
					$(t).parent().parent().remove();
						alert(msg.msg);
				}
				
			});
		}
	}
  </script>
</html>
