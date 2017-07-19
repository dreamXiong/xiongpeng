<%@page pageEncoding="UTF-8"%>
<c:forEach var="item" items="${dtoList}" varStatus="o">
	<input type="hidden" id="version_${item.id}" value="${item.version}" name="version"/>
	<div class="merchandise">
		<div class="info-title clear">
			<div class="pull-left">
				<i>订单号: </i><a href="javascript:;">${item.orderSerialNo}</a>
			</div>
			<div class="pull-left">
				<i>订单时间: </i><span> <liguo:dateFormatLabel value="${item.createDt}" pattern="yyyy-MM-dd  HH:mm:ss" /></span>
			</div>  
		</div>
		<table class="col-10 stock">
			<thead>
				<tr>
					<th width="270">商品信息</th>
					<th width="66">单价</th>
					<th width="126">规格</th>  
					<th width="68">订购数量</th>
					<th width="91">小计</th>
					<th width="100">总计</th>
					<th width="97">订单状态</th>
					<th width="99">订单操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="witem" items="${item.wapOrderDetailDtoList}" varStatus="p">
				<c:if test="${p.index == 0}">
					<tr style="border-bottom:1px solid #eee;">
						<td width="270"><div class="info"><div class="pull-left info-text"><p>${witem.productName}/${witem.brandName}</p></div></div></td>
						<td width="66">${witem.price }元</td>
						<td class="text-ellipsis" width="126">${witem.spec }</td>
						<td width="68">${witem.buyNum }</td>
						<td width="91">${witem.buyNum * witem.price}元</td>  
						<td width="100" rowspan="${fn:length(item.wapOrderDetailDtoList)}">
							<br>￥${item.totalMoney}元<liguo:dictLabel key="${item.orderType}" /><br>          
							<liguo:dictLabel key="${item.settleType}" />
						</td>
						<td width="97" rowspan="${fn:length(item.wapOrderDetailDtoList)}">
							<span class="color_${item.orderStatus}">
								<liguo:dictLabel key="${item.orderStatus}" />
							</span>
						</td>
						<td width="99" rowspan="${fn:length(item.wapOrderDetailDtoList)}">
							<a class="btn" href="javascript:;" onclick="orderGroupDetail('${item.id}')">订单详情</a><br>
							<a class="btn" href="javascript:;" onclick="removeOrderDivlog('${item.id}')">删除订单</a><br>
						</td>
					</tr>
					</c:if>
					<c:if test="${p.index != 0}">
						<tr>
							<td><div class="info"><div class="pull-left info-text"><p>${witem.productName}/${witem.brandName}</p></div></div></td>
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
<liguo:pagination dataDomId="key_stopOrder_list" pageAction="serachStopOrder" />