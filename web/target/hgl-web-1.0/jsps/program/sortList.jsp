<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${ctx}/js/jquery/jquery1.9.2/jquery-1.8.3.js"></script>	
</head>
<body style="margin:0;">
<div style="width: 540px;height:1018px; margin: 0 auto;background-image: url('${ctx}/images/bj.png');backgroud-repeat:no-repeat;" >

<div style="width:540px;padding-left:20px;font-weight:800;text-align:center; height: 165px;font-size:25px;margin: 0 auto;line-height:165px; color: orange;">
	2016年度晚会节目榜单
</div>
<div id="sortData" style="width: 540px;margin: 0 auto;">
	<%@include file="sortData.jsp"%>
</div>
</div>
<script>
var refreshEv;
$(function(){
	refreshEv = setInterval(refreshPage,5000);
});
function refreshPage(){
	  $.ajax({
	        type: "POST",
	        url: "${ctx}/vote/sortDataPage",
	        data: null,
	        success: function (response) {
	        	$("#sortData").html(response);
	        }
	    });
}
</script>	
</body>
</html>
