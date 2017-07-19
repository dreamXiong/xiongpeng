<%@page pageEncoding="UTF-8"%>
<c:forEach var="pItem" items="${cList}" varStatus="pindex">	
		<li>
			<a href="javascript:;">
				<img onclick="goServiceDetail(${pItem.id});" data-original="${ctx}/master/generateComImage?id=${pItem.id}&imgName=${pItem.companyImage1}" alt="" src="${ctx}/images/no-load.gif" class="lazy">
			</a>
			<div class="details">
				<h3 class="name clearfox box"><span class="pull-left box2">${pItem.companyName}</span></h3>
				<p class="distance">${pItem.describes}&nbsp;</p>
			</div>
		</li>
	</c:forEach>