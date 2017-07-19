<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>选择城市</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<%@include file="../common/common.jsp"%>
</head>	
<body style="background: #fff;">

	<header class="cart verify-head" style="box-shadow:none;">
		<span class="icon-angle-left text-center" onclick="history.back(-1);"></span>
		<h3 class="text-center">城市</h3>
	</header>
<form method="post" id="rectIndexShopInfo" action="rectIndexShopInfo" style="padding-top:44px;">
	<div class="container select-city">
		<div class="clearfix">
			<div class="pull-left">
				<span class="icon-map-marker"></span> 当前定位城市
			</div>
			<div class="pull-right">${cityName}</div>
		</div>
				<input type="hidden" id="actionName_opened" value="${actionName}">
				<input type="hidden" id="typeId_opened" value="${param.typeId}">
		<dl>
			<dt>已开通城市</dt>
			<c:forEach var="item" items="${tbCityInfoList}" varStatus="s">
				<input type="hidden" id="adminRole_${s.index}" value="${item.id}">
				<dd>${item.name}</dd>
			</c:forEach>
		</dl>
	</div>
</form>
</body>

</html>

<script>
	$(function() {
		$('.select-city dd').click(function() {
			/* alert($(this).text()); */
			var actionName =$("#actionName_opened").val();
			var action = "";
			if(actionName=="cust"){
				action = ctx+"/customerIndex/rectCustomerIndex?cityName=";
			}
			if(actionName =="master"){
				action = ctx+"/master/restIndex?cityName=";
			}
			if(actionName =="pick"){
				action = ctx+"/pick/restIndex?cityName=";
			}
			if(actionName =="company"){
				action = ctx+"/master/restCompanyIndex?typeId="+$("#typeId_opened").val()+"&cityName=";
			}
			$("#rectIndexShopInfo").attr("action",action+$(this).text());
			$("#rectIndexShopInfo").submit();
		});
	});
</script>
