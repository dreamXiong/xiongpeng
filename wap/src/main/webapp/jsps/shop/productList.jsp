<%@page pageEncoding="UTF-8"%>
	<c:if test="${empty wapProductDtoList}">
		<img src="${ctx}/images/null2.png" alt="">
	</c:if>
	<ul class="clearfix">
		 <c:forEach var="item" items="${wapProductDtoList}" varStatus="s">
			<li class="dealer-list">
				<a href="javascript:;">
					<img onclick="pickDetail(${item.id});" data-original="${ctx}/generateProductImage?id=${item.id}&imgName=${item.pimgOne}" src="${ctx}/images/no-load.gif" class="lazy"  alt="">
				</a>
				<h3>${item.name}</h3>
				<div class="clearfix sales">          
					<div class="pull-left">
						<span class="text-red">¥ ${item.price}</span>起
					</div>
					<div class="pull-right">
						销量${item.saleNum}
					</div>
				</div>
				
				<span class="brand">${item.brandName}</span>
			</li>
		</c:forEach> 
	</ul>
	