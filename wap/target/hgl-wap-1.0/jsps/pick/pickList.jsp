<%@page pageEncoding="UTF-8"%>
<ul class="clearfix">
<c:if test="${empty pickList}">
	<div class="container">
		<img src="${ctx}/images/null2.png" alt="">
	</div>
</c:if>
<c:forEach var="item" items="${pickList}" varStatus="s">
		<li class="dealer-list">
			<a href="javascript:;">
				<img onclick="pickDetail(${item.id});" data-original="${ctx}/generateProductImage?id=${item.id}&imgName=${item.pimgOne}" src="${ctx}/images/no-load.gif" class="lazy" alt=""/>
			</a>
			<div class="box sales" style="line-height:1.5em;">
				<h3 class="box1 text-hidden"  style="line-height:1.5em;">${item.name}</h3>
				<div class="box1 text-right text-hidden">销量${item.saleNum}</div>
			</div>
			<div class="box sales" style="line-height:1.5em;">
			<!-- 	<div class="box1 text-hidden">
					立国科技有限公司
				</div> -->
				<%-- <div class="box1 text-hidden text-right">
					<span class="icon-map-marker"></span>
					${item.distance}km
				</div> --%>
			</div>
			
			<div class="box sales">
				<div class="box1 text-hidden"><span class="text-red">¥ ${item.price}</span>起</div>
				<div class="box1 text-right text-hidden">含<span>${item.statusCount}</span>种规格</div>
			</div>
			
			
			<%-- <h3>${item.name}</h3>	
			<div class="clearfix sales">
				<div class="pull-left">
					<span class="text-red">¥ ${item.price}</span>起
				</div>
				<div class="pull-right">
					<span class="icon-map-marker"></span>
					${item.distance}km
				</div>
			</div>
			<div class="clearfix sales">
				<div class="pull-left">
					含<span>${item.statusCount}</span>种规格
				</div>
				<div class="pull-right">
					销量${item.saleNum}
				</div>
			</div> --%>
		</li>
</c:forEach>
</ul>
<script>   
function goShop(id,distance){
	window.location.href ="shop/index?id="+id+"&distance="+distance;
}; 
</script>