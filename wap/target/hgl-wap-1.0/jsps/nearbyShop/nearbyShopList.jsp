<%@page pageEncoding="UTF-8"%>


<input type="hidden" value="${pageCount}" id="pageCount"/>
<c:if test="${not empty  shopInfoList}">
	<c:forEach var="item" items="${shopInfoList}" varStatus="s">
		<li class="box" >
				<img src="${ctx}/login/generateImage?id=${item.id}&imgName=${item.shopImage1}" />	
				<div class="box1" onclick="goShopIndex('${item.id}','${item.rebate}');">
					<h3 class="text-hidden">${item.shopName}</h3>
					<p>${item.scope}</p>		
				</div>
				
				<div style="width:66px;padding:10px 0;">
						<a onclick="goShop('${item.lat}','${item.lon}');" style="color:#fff;background:rgba(0,122,255,.8);
						display: block;text-align:center;line-height:24px;margin-bottom:8px;">导航</a>
						<div class="text-hidden" >
							<span class="icon-map-marker"></span>	
							${item.rebate}km
						</div>	
						
				</div>
			<!-- </a> -->	
		</li>
	</c:forEach>
</c:if>
           