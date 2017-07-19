<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="cart-body">
<head>
	<meta charset="UTF-8">
	<c:set value="站内信列表" var="titleName"></c:set>
	<title>站内信列表</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/my.css">
	
	
	<%@include file="../common/common.jsp"%> 
	<style>
		.pox header{
			box-shadow:none;
		}
	</style>
</head>	


	
<body>
<div class="pox">    
	<%-- <%@include file="../common/header.jsp"%> --%>
	<header class="cart verify-head " style="z-index:10000;">
		<span class="icon-angle-left text-center" onclick="window.location.href = '${ctx}/personalCenter/index'"></span>
		<h3 class="text-center"><c:out value="${titleName}"/></h3>
		<span class="icon-reorder"></span>
		<%@include file="../common/topBar.jsp"%>
	</header>	
</div>
<div class="container letter">
	<div class="letter-list">
		<ul id="info">
			<c:forEach var="item" items="${userLetterList}" varStatus="s">     
			<li class="box" onclick="window.location.href='${ctx}/letter/letter?uId=${item.userId}&num=${item.letterCount}'">
				
				<c:if test="${isdp}">
					<img src="${ctx}/letter/showHeaderImage?id=${item.userId}" alt="">
					<div class="box1 text-hidden" >
						${item.userName}
					</div>
				</c:if>
				<c:if test="${!isdp}">
					<img src="${ctx}/letter/generateImage?id=${item.shopId}&imgName=${item.shopImage}" alt="">
					<div class="box1">
						${item.shopName}
					</div>
				</c:if>
				<%-- <div class="box1" style="line-height: 85px;">${item.letterCount}</div> --%>
			</li>
			</c:forEach>
		</ul>
	</div>
</div>

</body>
	
</html>