<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-switch-master/dist/css/bootstrap3/bootstrap-switch.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app-base.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Font-Awesome/css/font-awesome.min.css">
	<script src="${pageContext.request.contextPath}/js/common/jquery-1.12.2.min.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap-switch-master/dist/js/bootstrap-switch.min.js"></script>
</head>
<body>
<form id="loginForm" method="post" action="${success.actionUrl}">
	<c:forEach var="item" items="${success.list}" varStatus="i">
		<input type="hidden" name="${item.key }" value="${item.value }" />
	</c:forEach>
</form>
</body>
</html>
<script>
$(function(){
	$("#loginForm").submit();                 
});
</script>