<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title>抽奖</title>
		<link rel="stylesheet" href="${ctx}/css/style.css" />
		<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.7.1.min.js"></script>
	</head>	
	<body  bgcolor="red">
		
		<div id="user_list" class="user_list" >
			<canvas id="tutorial"></canvas>
			<select id="nowLevel">
				<option value="1">幸运奖</option>
				<option value="2">三等奖</option>
				<option value="3">二等奖</option>
				<option value="4">一等奖</option>
				<option value="5">特等奖</option>
			</select>
			<div id="stop_button"><a href="javascript:;">STOP</a></div>
		</div>
		
		<div id="winner_list">
			<div class="winner_title">获奖名单</div>
			<div class="winner_table">
				<table>
					<thead>
						<th>头像</th>
						<th>姓名</th>
						<th>获奖等级</th>
						<th>操作</th>
					</thead>
					<tbody class="list">
					</tbody>
				</table>
				
				<div><a href="javascript:;" id="clear">清空</a></div>
			</div>
		</div>
		
		<div id="cover_up">
			<div id="start_button_up">开始抽奖</div>
		</div>
		<div id="cover_bottom">
			<div id="start_button_bottom">开始抽奖</div>
		</div>
		<div style="display: none;" id="loadImagDiv"></div>
		<script type="text/javascript" src="${ctx}/js/jquery/jQuery.publicBox.js"></script>
		<script>		
		users = ${userArray};
		
		function lordImag(users){
			var htmlStr="";
			for(var i = 0, l = users.length; i < l; i++) {
				htmlStr += "<img id='image"+users[i].id+"head' src='"+users[i].headimgurl+"'>";
			}	
			$("#loadImagDiv").append(htmlStr);
		}
		lordImag(users);
		
		window.onbeforeunload=function(){
		    $.ajax({
		        type: "POST",
		        url: "ernie/endErnie",
		        data: null,
		        success: function (data) {
		           
		        }
		    });
		}
		</script>
		<script type="text/javascript" src="${ctx}/js/wap/ernie/users.js"></script>
		<script type="text/javascript" src="${ctx}/js/wap/ernie/lottery.js"></script>
		<script type="text/javascript" src="${ctx}/js/wap/ernie/index.js"></script>
	</body>
</html>