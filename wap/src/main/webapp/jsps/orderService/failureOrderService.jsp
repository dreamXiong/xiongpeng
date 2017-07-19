<%@page pageEncoding="UTF-8"%>
	<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<c:set value="下单失败" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<%@include file="../common/common.jsp"%>
</head>	
<body>
<%@include file="../common/header.jsp"%>
<div class="container">
	<div class="pay">
		<div class="box-shadow info text-center">
			<div>
				<img src="${ctx}/images/succeed.png" alt="" class="img">
				<h3>抱歉,下单失败!</h3>
			</div>
		</div>
	</div>
</div>
</body>
</html>