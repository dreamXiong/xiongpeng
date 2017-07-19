<%@page pageEncoding="UTF-8"%>
<c:forEach var="item" items="${dtoList}" varStatus="o">
	<div class="merchandise">
		<table class="col-10">
			<thead>
				<tr>
					<th width="270">商品信息</th>
					<th width="66">单价</th>
					<th width="46">规格</th>  
					<th width="68">订购数量</th>
					<th width="91">小计</th>
					<th width="91">邮费</th>
					<th width="188">总计</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="witem" items="${item.wapOrderDetailDtoList}" varStatus="p">
				<c:if test="${p.index == 0}">
					<tr>
						<td>
							<div class="info">
								<div class="pull-left info-img">
									<img src="${ctx}/myOrderGroup/generateImage?id=${witem.productId}&imgName=${witem.pImgOne}" height="80" width="80" alt="">
								</div>
								<div class="pull-left info-text">
									<p>${witem.productName}/${witem.brandName}</p>
								</div>
							</div>
						</td>
						<td>${witem.price }元</td>
						<td>${witem.spec }</td>
						<td>${witem.buyNum }</td>
						<td>${witem.buyNum * witem.price }元</td>  
						<td rowspan="${fn:length(item.wapOrderDetailDtoList)}">
							￥${item.postage == null ? 0.0 : item.postage}元
						</td>
						<td rowspan="${fn:length(item.wapOrderDetailDtoList)}">
							￥${item.totalMoney+item.postage}元
						</td>
					</c:if>
					<c:if test="${p.index != 0}">
						<tr>
							<td>
								<div class="info">
									<div class="pull-left info-img">
										<img src="${ctx}/myOrderGroup/generateImage?id=${witem.productId}&imgName=${witem.pImgOne}" height="80" width="80" alt="">
									</div>
									<div class="pull-left info-text">
										<p>${witem.productName}/${witem.brandName}</p>
									</div>
								</div>
							</td>
							<td>${witem.price }元</td>
							<td>${witem.spec }</td>
							<td>${witem.buyNum }</td>
							<td>${witem.buyNum * witem.price }元</td>  
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	</div>
</c:forEach>