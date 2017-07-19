<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<c:set value="惠豆商城" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<%@include file="../common/common.jsp"%> 
	<script src="${pageContext.request.contextPath}/js/common/base.js"></script>
	<script src="${pageContext.request.contextPath}/js/hgl/master.js"></script>
</head>
<body>
	<div class="integral-head">
		<%@include file="../common/header.jsp"%>
	</div>	
	<div id="integralMallId" class="new-list" style="padding-top:44px;margin-top:0;">
		<%@include file="integralMallList.jsp"%>
	</div>
</body>
</html>