<%@page pageEncoding="UTF-8"%>
<ul class="clearfix">
	<c:forEach var="pItem" items="${cList}" varStatus="pindex">	
		<li>
			<a href="javascript:;">
				<img onclick="goServiceDetail(${pItem.id});" data-original="${ctx}/customerIndex/generateImage?id=${pItem.id}&imgName=${pItem.cimgOne}" alt="" src="${ctx}/images/no-load.gif" class="lazy">
			</a>
			<div class="details">
				<h3 class="name col-10">
					<div class="col-7 text-hidden">${pItem.name}</div>
					<div class="col-3 text-right text-hidden"><i style="color:#f2000e;">${pItem.price}</i>元起</div>
				</h3>
				<p class="distance">${pItem.describes}&nbsp;</p>
			</div>
		</li>
	</c:forEach>
</ul>    