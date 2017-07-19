<%@page pageEncoding="UTF-8"%>
<ul class="clearfix">
	<c:forEach var="pItem" items="${cList}" varStatus="pindex">	
		<li>
			<a href="javascript:;">
				<img onclick="goServiceDetail(${pItem.id});" data-original="${ctx}/customerIndex/generateImage?id=${pItem.id}&imgName=${pItem.cimgOne}" alt="" src="${ctx}/images/no-load.gif" class="lazy">
			</a>
			<div class="details">
				<h3 class="name clearfox box">
					<span class="pull-left box2">${pItem.name}</span>
					<span class="pull-right box1 text-right"><i style="color:#f2000e;">${pItem.price}</i>元起</span>
				</h3>
				<p class="distance">${pItem.describes}&nbsp;</p>
			</div>
		</li>
	</c:forEach>
</ul>    