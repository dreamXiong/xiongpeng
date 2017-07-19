<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="cart-body">
<head>
	<meta charset="UTF-8">
	<c:set value="邀请分享" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/my.css"/>  
	<%@include file="../common/common.jsp"%>  
	<style>
		.head header{
			box-shadow:none;
		}
	</style>      
</head>	
<body>

	<div class="head">
		<%@include file="../common/header.jsp"%>
	</div>
<div class="container share" style="padding-top:54px;">
	<div class="text-center">我的推荐码：<%-- <input value="${recommendCode}" readonly="readonly" style="width: 100px;"> --%><span>${recommendCode}</span></div>
	<img alt="" src="${ctx }/personalCenter/logoCode?code=${saveShopCode}" style="width: 200px;height: 200px;margin:10px auto;">
</div>
</body>
<script>
	$(function() {
	
		$('.box1').click(function(event) {
			$(this).prependTo($('.box'));
		});
	});
</script>

</html>


