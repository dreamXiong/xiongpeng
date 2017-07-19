<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<c:set var="ctx" value="<%=request.getContextPath() %>"></c:set>
<head>
	<meta charset="UTF-8">
	<c:set value="兑换记录" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/my.css">
	<%@include file="../common/common.jsp"%>
</head>	
<body style="background:#fff;">
<div class="no-shadow">
	<%@include file="../common/header.jsp"%>       
</div>
<div class="container" style="margin-bottom: 0;">                             
	<div class="box text-center" id="prolist-head">
		<div class="box2">商品名称</div>        
		<div class="box1">积分</div>
		<div class="box1">数量</div>
		<div class="box1">单价</div>
		<div class="box1">状态</div>
	</div>
</div>
<ul class="prolist">   
	<c:forEach var="item" items="${integralMallRecordList}" varStatus="i">
		<li class="box text-center">
			<div class="box2">${item.goodsName}</div>
			<div class="box1">${item.remainingIntegral}</div>
			<div class="box1">${item.exchangeNum}</div>
			<div class="box1">${item.payMoney}</div>
			<div class="box1"><liguo:dictLabel key="${item.platStatus }" /></div>                  
		</li>    
	</c:forEach>
</ul>
</body>
</html>