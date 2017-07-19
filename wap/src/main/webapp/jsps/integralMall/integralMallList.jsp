<%@page pageEncoding="UTF-8"%>
<!-- 分类的内页开始  -->
<c:forEach var="item" items="${integralMallList}" varStatus="index">
	<c:if test="${index.index==0 }">
		<h2 class="integral-classify">${item.goodsTypeName }</h2>
	</c:if>	
</c:forEach>
<ul class="clearfix integral">
	<c:if test="${empty integralMallList}">
		<img src="${ctx}/images/null2.png" alt="">
	</c:if>
	<c:forEach var="pItem" items="${integralMallList}" varStatus="pindex">	
		<li>
			<a href="${ctx}/integralMall/getIntegralMallInfo?id=${pItem.id}">
				<h3 class="text-hidden">${pItem.goodsName}</h3>
				<div><small>市场参考价：${pItem.marketAmount}元</small></div>
				<div class="col-10 price">
					<div  class="col-6">
						<img src="${ctx}/integralMall/generateImage?id=${pItem.id}&imgName=${pItem.goodsImage}">	 
					</div>
					<div class="col-4 text-right">
						<span style="color:red;">${pItem.integral}</span> 分
					</div>	
				</div>	
		</li>
	</c:forEach>
</ul> 
<!-- 分类的内页结束  -->



















