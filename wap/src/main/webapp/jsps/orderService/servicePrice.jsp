<%@page pageEncoding="UTF-8"%>
	<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<c:set value="服务编辑" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<%@include file="../common/common.jsp"%>
</head>	
<body>
<div>
<%@include file="../common/header.jsp"%>
</div>
<div class="container eva">
	<div class="eva-info box-shadow">
		<h3>
			<span>${orderServiceDto.serciceName}</span>
		</h3>
		<div style="margin-top: 10px;margin-bottom:5px;">   
			<label>请输入服务价格(单位/元)：</label>
		</div>
		<form action="doServicePrice" id="doServicePrice" method="post">
			<input name="price" id="price" type="" style="line-height: 40px;height: 40px;" value="${orderServiceDto.totalMoney}"></input>
			<input name="id" id="id" value='${param.id}' type="hidden" ></input>
			<span id="msg" style="color: red;"></span>
			<div class="refer">
				<button type="button" onclick="doServicePrice()">提交</button>
			</div>
		</form>
		
	</div>
</div>
</body>
<script>
function doServicePrice(){
	var price = $("#price").val();
	var twoScale = new RegExp(/(^\d+(\.\d{0,2})?$)/);
	if(price=="" || price==null){
		$("#msg").text("价格不能为空,并且为数字");
		return false;
	}
	$("#msg").text();
	if(isNaN(price) || !twoScale.test(price)){
		$("#msg").text("价格不能为空,并且为数字");
		return false;
	}
	$("#msg").text("");
	$.ajax({                       
        type: "POST",
        url: "../wapOrderService/checkCityLowerPrice",
        data: "id="+$("#id").val()+"&price="+$("#price").val(),
        success: function(response){ 
        	if(response.errcode == 0){
        		$("#doServicePrice").submit();
        	}else{
        		$("#msg").text("金额必须大于等于"+response.lowerPrice);
        		return false;
        	}
        }
	});
	
}
</script>
</html>
