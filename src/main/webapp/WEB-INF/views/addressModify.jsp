<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>地址修改页面</title>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>
<body>
	<center>
		<div>
			<h4>地址</h4>
			<form>
				<table>
					<tr>
						<td><input type="hidden" name="id" readonly="readonly"
							id="id" value="${address.id}"></td>
					</tr>
					<tr>
						<td><span>省</span></td>
						<td><input type="text" name="province" id="province"
							value="${address.province}"></td>
					</tr>
					<tr>
						<td><span>市</span></td>
						<td><input type="text" id="city" name="city"
							value="${address.city }"></td>
					</tr>
					<tr>
						<td><span>县(区)</span></td>
						<td><input type="text" name="county" id="county"
							value="${address.county }"></td>
					</tr>
					<tr>
						<td><span>详细地址</span></td>
						<td><input type="text" name="detailAddress"
							id="detailAddress" value="${address.detailAddress }"></td>
					</tr>
					<tr>
						<td><input type="submit" value="确认修改" id="update1" /></td>
						<td><button ><a href="javascript:history.go(-1)">取消修改</a></button></td>
					</tr>
				</table>
			</form>
		</div>
	</center>
</body>
<script type="text/javascript">
	$("#update1").click(function(){
		var id = $("#id").val();
		var detailAdress = $("#detailAddress").val();
		var province = $("#province").val();
		var city = $("#city").val();
		var county = $("#county").val();
		var data = {
			"id" : id,
			"detailAdress" : detailAdress,
			"province" : province,
			"city" : city,
			"county" : county,

		}
		$.ajax({
			url : "/SSH2/post/saveupdate", // 提交到controller的url路径
			type : "post", // 提交方式
			data : data, // data为String类型，必须为 Key/Value 格式。
			dataType : "json", // 服务器端返回的数据类型
			success : function(msg) { // 请求成功后的回调函数，其中的参数data为controller返回的map,也就是说,@ResponseBody将返回的map转化为JSON格式的数据，然后通过data这个参数取JSON数据中的值
				alert(msg.msg)
				window.location.href = '/SSH2/showvip';
			},
			erro : function(msg) {
				alert(msg.msg)
			}
		});

	});
</script>
</html>