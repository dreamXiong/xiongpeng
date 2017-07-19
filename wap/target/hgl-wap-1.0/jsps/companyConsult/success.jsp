<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<c:set var="ctx" value="<%=request.getContextPath() %>"></c:set>
<head>
	<meta charset="UTF-8">
	<c:set value="咨询信息" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/my.css">
	<%@include file="../common/common.jsp"%>
</head>	
<body>             
<%@include file="../common/header.jsp"%>
<div class="container" >              
	<h3 class="text-center" style="padding-top:20px;margin-bottom:15px;">        
		<span class="icon-ok-sign" style="color:#11CD6D;font-size:30px;display:inline-block;vertical-align: middle;"></span>      
		您的咨询信息提交成功，我们将及时给您反馈！
	</h3>
	<div class="text-center">
		<a href="${ctx }/shop/userShop" style="border:1px solid #aaa;border-radius:5px;padding:5px 8px;">回到首页</a>	
		<a href="${ctx }/master/index" style="border:1px solid #aaa;border-radius:5px;padding:5px 8px;">回到找师傅</a>	
	</div>	    
</div>	        
	
</body>
<script>
	
</script>
</html>