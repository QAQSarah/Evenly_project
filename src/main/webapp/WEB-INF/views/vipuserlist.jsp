<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
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

<title>vip用户列表</title>
<style type="text/css">
td {
	margin: 0 20px;
	text-align: center;
}

fieldset {
	width: 590px;
}

.hide {
	display: none;
}

.c1 {
	position: fixed;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	background: rgba(0, 0, 0, .5);
	z-index: 2;
}

.c2 {
	background-color: white;
	position: fixed;
	width: 400px;
	height: 300px;
	top: 50%;
	left: 50%;
	z-index: 3;
	margin-top: -150px;
	margin-left: -200px;
}

#modal p {
	margin-left: 80px;
}
</style>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>

<body>
	<center>
		<fieldset title="查询条件">
			<legend>查询条件</legend>
			<form action="seleteByName">
				<span>用户名：</span><input name="username" type="text" id="username">
				&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="查询">
				&nbsp;&nbsp;<a href="addvip"><input type="button" value="新增"></a>
				&nbsp;&nbsp;<a href="Get/user/logout"><input type="button"
					value="退出"></a>
			</form>
		</fieldset>
		<br>
		<table id="show_tab" border="1">
			<thead>
				<tr class="trhead" id="show_tab_one">
					<th>用户编号</th>
					<th>用户名</th>
					<th>姓名</th>
					<th>密码</th>
					<th>手机号码</th>
					<th>创建时间</th>
					<th>详情地址</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.pageBean.list}" var="user">
					<tr id="${user.id}">
						<td>${user.id}</td>
						<td id="id${user.id}">${user.username}</td>
						<td>${user.realname}</td>
						<td>${user.password}</td>
						<td>${user.phone}</td>
						<td>${user.creatTime}</td>
						<td><a href="javascript:void(0);"
							onclick="showAddress(${user.id})">查看地址</a></td>
						<td><a href="javascript:void(0);" onclick="editUser(${user.id})">修改</a>&nbsp;/&nbsp;<a
							href="javascript:void(0);" onclick="delUser(${user.id});">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br /> <a
			href="${pageContext.request.contextPath}/showvip?pageNum=1">首页</a>
		<%--如果当前页为第一页时，就没有上一页这个超链接显示 --%>
		<c:if test="${requestScope.pageBean.pageNum ==1}">
			<c:forEach begin="${requestScope.pageBean.start}"
				end="${requestScope.pageBean.end}" step="1" var="i">
				<c:if test="${requestScope.pageBean.pageNum == i}">
                        ${i}
                    </c:if>
				<c:if test="${requestScope.pageBean.pageNum != i}">
					<a
						href="${pageContext.request.contextPath}/showvip?pageNum=${i}">${i}</a>
				</c:if>
			</c:forEach>
			<a
				href="${pageContext.request.contextPath}/showvip?pageNum=${requestScope.pageBean.pageNum+1}">下一页</a>
		</c:if>

		<%--如果当前页不是第一页也不是最后一页，则有上一页和下一页这个超链接显示 --%>
		<c:if
			test="${requestScope.pageBean.pageNum > 1 && requestScope.pageBean.pageNum < requestScope.pageBean.totalPage}">
			<a
				href="${pageContext.request.contextPath}/showvip?pageNum=${requestScope.pageBean.pageNum-1}">上一页</a>
			<c:forEach begin="${requestScope.pageBean.start}"
				end="${requestScope.pageBean.end}" step="1" var="i">
				<c:if test="${requestScope.pageBean.pageNum == i}">
                        ${i}
                    </c:if>
				<c:if test="${requestScope.pageBean.pageNum != i}">
					<a
						href="${pageContext.request.contextPath}/showvip?pageNum=${i}">${i}</a>
				</c:if>
			</c:forEach>
			<a
				href="${pageContext.request.contextPath}/showvip?pageNum=${requestScope.pageBean.pageNum+1}">下一页</a>
		</c:if>

		<%-- 如果当前页是最后一页，则只有上一页这个超链接显示，下一页没有 --%>
		<c:if
			test="${requestScope.pageBean.pageNum == requestScope.pageBean.totalPage}">
			<a
				href="${pageContext.request.contextPath}/showvip?pageNum=${requestScope.pageBean.pageNum-1}">上一页</a>
			<c:forEach begin="${requestScope.pageBean.start}"
				end="${requestScope.pageBean.end}" step="1" var="i">
				<c:if test="${requestScope.pageBean.pageNum == i}">
                        ${i}
                    </c:if>
				<c:if test="${requestScope.pageBean.pageNum != i}">
					<a
						href="${pageContext.request.contextPath}/showvip?pageNum=${i}">${i}</a>
				</c:if>
			</c:forEach>
		</c:if>
		<%--尾页 --%>
		<a
			href="${pageContext.request.contextPath}/showvip?pageNum=${requestScope.pageBean.totalPage}">尾页</a>

	</center>
</body>
<script type="text/javascript">
function showAddress(id) {
	 window.location.href = 'showAddress/'+id;
	 
}
function delUser(id) {
	if(confirm("确定要删除编号为"+id+"用户么？")){
		$.ajax({
			url:"delvip",
			type:"POST",
			dataType:"JSON",
			data:{
				"id":id,
			},
			success:function(msg){
						alert(msg.msg);
						$("#"+id).remove();
			},
			erro:function(msg){
					alert(msg.msg);
			}
			
		});
	}
}
function editUser(id) {
	window.location.href = 'editVip/'+id;
}
</script>
</html>
