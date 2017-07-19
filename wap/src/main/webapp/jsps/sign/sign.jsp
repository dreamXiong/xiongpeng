<%@page pageEncoding="UTF-8"%>
	<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<c:set value="签到" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<%@include file="../common/common.jsp"%>
	<script src="${ctx}/js/hgl/sign.js"></script> 
</head>	

<body>           
<%@include file="../common/header.jsp"%>
<div class="container" style="background: url(../images/bg_sign.png) no-repeat;height: 600px;width: 400px;">
	立即签到获得 ${signInregral} 积分<br />
	<c:if test="${canSign==0}">
		您的当前积分为：${integral}<br />
		<input type="button" value="签到" onclick="sign()"></input>
	</c:if>
	<c:if test="${canSign==1}">
		用户信息异常请重新登录！
	</c:if>
	<c:if test="${canSign==2}">
		您的当前积分为：${integral}<br />
		<input type="button" value="兑换礼品" ></input>
	</c:if>
	
</div>
</body>
</html>