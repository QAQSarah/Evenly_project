<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>地址详情</title>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<style type="text/css">
th {
	background-color: #FF7F00;
	border: 1px solid #70DBDB;
	opacity: 0.8;
}

td {
	background-color: #C0C0C0;
	opacity: 0.8;
}

.overCurtain {
	border-top: 1px solid rgb(230, 245, 255);
	position: absolute;
	height: 674px;
	width: 100%;
	left: 0px;
	top: 0px;
	opacity: 0.7;
	display: none;
	background-color: rgb(230, 245, 255);
}

.hide-center {
	width: 308px;
	height: 300px;
	position: absolute;
	right: 0;
	left: 0;
	bottom: 0;
	top: 0;
	margin: auto;
	display: none;
	z-index: 1;
}

.hide-center #formhead {
	width: 300px;
	height: 42px;
	margin: 0;
	padding-top: 12px;
	padding-bottom: -30px;
	border-top-left-radius: 10px;
	border-top-right-radius: 10px;
	background-color: #00CCCC;
}

.hide-center #formhead-title {
	width: 100px;
	height: 24px;
	margin-left: 106px;
	margin-right: 36px;
	color: black;
	font-weight: 900;
	display: inline-block;
}

.hide-center #close {
	width: 35px;
	height: 20px;
	border: 0;
	border-radius: 20%;
	background-color: red;
	color: white;
	display: inline-block;
}

.hide-center #formbody {
	width: 300px;
	height: 245px;
	background-color: #CCFFFF;
	border-bottom-left-radius: 10px;
	border-bottom-right-radius: 10px;
}

.hide-center .loginHead {
	margin-right: -8px;
	padding: 13px;
	width: 25px;
	height: 28px;
	background-color: #141414;
	vertical-align: middle;
}

.hide-center .loginInput {
	height: 52px;
	width: 230px;
	padding-left: 10px;
	border: 0;
	background-color: #141414;
	color: aliceblue;
}

.hide-center .loginUserName {
	padding-left: 30px;
	padding-top: 30px;
}

.hide-center .login1 #img-topleft-loginHead {
	border-top-left-radius: 5px;
}

.hide-center .login1 #input-topright-loginInput {
	border-top-right-radius: 5px;
}

.hide-center .login1 #img-bottomleft-loginHead {
	border-bottom-left-radius: 5px;
}

.hide-center .login1 #input-bottomright-loginInput {
	border-bottom-right-radius: 5px;
}

.hide-center .loginPassword {
	padding-left: 30px;
}

.hide-center #formfoot {
	margin-top: 30px;
	margin-left: 30px;
}

.hide-center #BSignIn {
	border: 0;
	background: #329d90;
	color: #ffffff;
	width: 240px;
	height: 40px;
	border-bottom-left-radius: 5px;
	border-bottom-right-radius: 5px;
	border-top-left-radius: 5px;
	border-top-right-radius: 5px;
}
</style>
</head>
<body>
	<center>
		<label>用户名:</label><label>${user.username}</label> <br /> <input
			type="button" value="新增收货地址" onclick="add(${user.id})"
			class="login-button" /><br />
		<form id="form1">
			<table>
				<thead>
					<th>所在地区</th>
					<th>详细地址</th>
					<th>手机号码</th>
					<th colspan="2">操作</th>
				</thead>
				<tbody>
					<c:forEach items="${user.getAddresses()}" var="a">
						<tr id="${a.id}">
							<td>${a.province}&nbsp;${a.city}&nbsp;${a.county}</td>
							<td>${a.detailAddress}</td>
							<td>${user.phone}</td>
							<td><input type="button" value="修改" onclick="update(${a.id})" /></td>
							<td><input type="button" value="删除"
								onclick="delAddress(${a.id})" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</form>
		<div class="overCurtain"></div>

		<div class="hide-center">
			<div id="formhead">
				<div id="formhead-title">地址添加</div>
				<button type="button" id="close">X</button>
			</div>
			<div id="formbody">
				<form action="">
					<table>
						<tr>
							<td>省:</td>
							<td><select id="sheng" onchange="loadInfo()">
									<option value="">--请选择- -</option>
									<c:forEach items="${provinces}" var="province">
										<option value="${province.id}">${province.shortName}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td>市:</td>
							<td><select id="shi" onchange="loadInfo1()">
									<option value="">--请选择- -</option>
							</select></td>
						</tr>
						<tr>
							<td>县:</td>
							<td><select id="xian">
									<option value="">--请选择- -</option>
							</select></td>
						</tr>
						<tr>
							<td><span>详细地址:</span></td>
							<td><input type="text" name="detailAddress"
								id="detailAddress"></td>
						</tr>
						<tr>
							<td><input type="button" value="提交"  onclick="saveAddress(${user.id})"/></td>
							<td><input type="button" value="取消" id="close1" /></td>
						</tr>
					</table>
				</form>
			</div>

		</div>
	</center>
</body>
<script type="text/javascript">
function delAddress(id) {
if(confirm("确定要删除该地址么？")){
	$.ajax({
		url:"/SSH2/delAddress",
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
function add(id) {
	$(".hide-center").fadeIn("slow");
    $(".overCurtain").fadeIn("slow");
}
$("#close").click(function () {
    $(".hide-center").fadeOut("slow")
    $(".overCurtain").fadeOut("slow")
})
$("#close1").click(function () {
    $(".hide-center").fadeOut("slow")
    $(".overCurtain").fadeOut("slow")
})
function loadInfo() {
		var praviceID = $("#sheng").val();
		$.ajax({
			url : "/SSH2/ejld/" + praviceID,
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
			url : "/SSH2/ejld/" + praviceID,
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
	function saveAddress(id){
		var provinceId = $("#sheng").val();
		var cityId = $("#shi").val();
		var countyId = $("#xian").val();
		var detailAdress = $("#detailAddress").val();
		var userid=id;
		var data = {
			"provinceId" : provinceId,
			"cityId" : cityId,
			"countyId" : countyId,
			"detailAdress" : detailAdress,
			"userid":userid,
		}
		$.ajax({
			url : "/SSH2/saveAddress", // 提交到controller的url路径
			type : "post", // 提交方式
			data : data, // data为String类型，必须为 Key/Value 格式。
			dataType : "json", // 服务器端返回的数据类型
			success : function(data) { // 请求成功后的回调函数，其中的参数data为controller返回的map,也就是说,@ResponseBody将返回的map转化为JSON格式的数据，然后通过data这个参数取JSON数据中的值
				alert(data.msg)
				window.location.href = '/SSH2/showAddress/'+id;
			},
			erro:function(){
				alert(data.msg)
				window.location.href = '/SSH2/showAddress/'+id;
			}
		});
	}
	function update(id){
		window.location.href = "/SSH2/show/"+id;
	}
	
</script>
</html>