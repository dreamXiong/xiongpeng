<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<c:set value="商品分类" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<script src="${pageContext.request.contextPath}/js/common/base.js"></script>
	<script src="${pageContext.request.contextPath}/js/hgl/master.js"></script>
	<%@include file="../common/common.jsp"%> 
</head>
<body>
	<div class="integral-head">
		<%@include file="../common/header.jsp"%>
	</div>	
	<!-- 分类的内页开始  -->
<div class="container">
	<h2 class="integral-classify">美食</h2>
	<ul class="clearfix integral">
		<c:if test="${empty integralMallList}">
			<img src="${ctx}/images/null2.png" alt="">
		</c:if>
		<c:forEach var="pItem" items="${integralMallList}" varStatus="pindex">	
			<li>
				<a href="${ctx}/integralMall/getIntegralMallInfo?id=${pItem.id}">
					<h3 class="text-hidden">${pItem.goodsName}</h3>
					<div><small>市场参考价：${pItem.marketAmount}元</small></div>
					<div class="box price">
						<div  class="box1">
							<img  <%-- src="${ctx}/integralMall/generateImage?id=${pItem.id}&imgName=${pItem.goodsImage}" --%>
							src="${ctx}/images/user-bg.png">	 
						</div>
						<div class="box1">
							<span style="color:red;">${pItem.integral}</span> 分
						</div>	
					</div>	
					<%-- <img data-original="${ctx}/integralMall/generateImage?id=${pItem.id}&imgName=${pItem.goodsImage}" alt="" 
						src="${ctx}/images/no-load.gif" class="lazy"> --%>
				</a>   
				<%-- <div class="details">
					<h3 class="name clearfox box">
						<span class="pull-left box2">${pItem.goodsName}<br/>
						<font color="red" style="font-size: 13px;"><b>${pItem.integral}</b></font>&nbsp;积分<br/>
						<font>市场参考价：${pItem.marketAmount}</font>&nbsp;元
						</span>
					</h3>
				</div> --%>
			</li>
		</c:forEach>
	</ul> 
</div>   
<!-- 分类的内页结束  -->

</body>
</html>