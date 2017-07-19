<%@page pageEncoding="UTF-8"%>
<div class="box" id="integral">
	<div class="box1">
		<span class="icon-bookmark-empty"></span>	
		<b style="color:red;">${integral.integral}</b> 积分
	</div>
	<div class="box1"  onclick="window.location.href = '${ctx}/integralMallRecord/index'">	
		<span class="icon-building"></span>	
		兑换记录
	</div>
	<div class="box1"  onclick="window.location.href = '${ctx}/integralMallRecord/integralRules'">
		<span class="icon-star-empty"></span>
		积分规则
	</div>
</div>   
<h2>商品分类</h2>       
<ul class="clearfix" id="classify">
 	<c:forEach var="pItem" items="${integralMallTypeList}" varStatus="pindex">	
		<li onclick="window.location.href = '${ctx}/integralMall/index?integralMallTypeId=${pItem.id }'">
			<h3>${pItem.goodsTypeName }</h3>
			<div class="img-warp">
				<img src="${ctx}/integralMallType/generateImage?id=${pItem.id}&imgName=${pItem.goodsTypeImage}"/>
			</div>					
		</li>
	</c:forEach>
</ul>	