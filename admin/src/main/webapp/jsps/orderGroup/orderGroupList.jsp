<%@page pageEncoding="UTF-8"%>
<c:forEach var="item" items="${dtoList}" varStatus="o">
	<input type="hidden" id="version_${item.id}" value="${item.version}" name="version"/>
	<div>
	<div class="head indent-head" style="cursor:pointer;">
		<span style="margin:0 60px 0 10px;" class="fa fa-angle-right icon" ></span>	
		订单号 : <span class="indent-size">${item.orderSerialNo}</span> 
		订单时间 : <span class="indent-time"><liguo:dateFormatLabel value="${item.createDt}" pattern="yyyy-MM-dd  HH:mm:ss" /></span> 
		品牌名称: <span class="indent-brand">${item.brandName}</span>
		合计金额 : <span class="indent-sum">${item.totalMoney}</span>	
		订单状态 : <span class="indent-state"><liguo:dictLabel key="${item.orderStatus}" /></span>
	</div>
	<table class="table table-bordered"  style="display:none;">
		<thead>
			<tr>
				<th>商品信息</th>
				<th>单价</th>
				<th>规格</th>
				<th>订购数量</th>
				<th>小计</th>    
				<th>合计</th>   
				<th>总计</th>   
				<th>订单状态</th>
				<th>订单操作</th>
			</tr>
		</thead>
		<tbody style="margin-top: -1px;">
		<c:forEach var="pitem" items="${item.productList}" varStatus="p">
				<c:forEach var="orderDetail" items="${pitem.orderDetailList}" varStatus="s">
					<c:if test="${p.index == 0}">
						<c:if test="${s.index == 0}">
							<tr>
								<td style="width:25%;" rowspan="${fn:length(pitem.orderDetailList)} "><dl><dd>${pitem.productName}</dd></dl></td>
								<td  style="width:10%;" id="price${orderDetailId}">${orderDetail.price}元
									<c:if test="${item.orderStatus==202}">
										<span style="text-align:right;">
											<a href="javascript:void(0)" onclick="updateItemPrice(${orderDetail.orderDetailId})" class="btn btn-primary btn-xs">改单价</a>
										</span>
									</c:if>									
								</td>
								<td style="width:10%;" >${orderDetail.spec}</td>
								<td style="width:10%;">${orderDetail.buyNum}${orderDetail.meterageUnit}</td>
								<!-- ---ajax -->
								<td style="width:10%;">${orderDetail.buyNum }${orderDetail.meterageUnit}<br>￥<span id="detailPayMoney${orderDetail.id}">${orderDetail.detailPayMoney}<span>元</td>
								<!-- ajax -->
								<td rowspan="${fn:length(pitem.orderDetailList)}">${pitem.buyNumCount}<br>￥<span id="buyMoneyCount${orderDetail.id}">${pitem.buyMoneyCount}</span>元</td>
								<!-- ajax --> 
								<td rowspan="${item.detailCount}" style="width:15%;">
									${item.buyCount}<!-- 件 -->
									<br> <c:if test='${not empty item.couponMoney }'>
										<del id="">￥${item.totalMoney}元</del> 
									￥${item.totalMoney - item.couponMoney}元<br>
									(使用优惠券-${item.couponMoney}) 
								</c:if> 
								<c:if test='${empty item.couponMoney }'>
										<%-- <del>￥${item.totalMoney}元</del>  --%>
									￥${item.totalMoney}元<br>
								</c:if> <br>
								<clochase:dictLabel key="${item.orderType}" /> 
								<br> <liguo:dictLabel
										key="${item.settleType}" />
								</td>
								<td rowspan="${item.detailCount}" style="width:10%;"><liguo:dictLabel key="${item.orderStatus}" /></td>
								<td rowspan="${item.detailCount}" style="width:10%;">
								    <a href="${ctx}/orderTracking/doSearchOrderTracking?orderGroupId=${item.id}">订单详情</a><br>
									<!-- 待确认 --> 
									<c:if test="${item.orderStatus == 200}">
										<a onclick="configOrderGroup('${item.id}');">确认订单</a><br>  
										<a onclick="showCancleOrderDivlog('${item.id}');">取消订单</a><br>
									</c:if> 
									<!-- 待付款 --> 
									<c:if test="${item.orderStatus == 202}">
										<!-- 线上支付 -->
										<a  href="#" onclick="updateTotalPrice(${item.id})">改总价</a><br> 
										<c:if test="${item.settleType == 242}">
											<!-- 线下支付 -->
											<a onclick="showCancleOrderDivlog('${item.id}');">取消订单</a><br> 
											<a onclick="configReceivePayment('${item.id}');">确认到款</a>
											<br>
										</c:if>
									</c:if> 
										<!-- 终止订单 -->
									<c:if test="${item.orderStatus == 208 || item.orderStatus == 210 || item.orderStatus == 204}">
										<a onclick="showStopOrderDivlog('${item.id}');">终止订单</a>  
										<br>
									</c:if>
									<c:if test="${item.orderStatus == 212 }">  
										<a onclick="#">登记发票</a>
									</c:if>
									<c:if test="${item.orderStatus == 218 }">
										<a onclick="configStopOrderGroupAdmin('${item.id}');">确认终止</a>
										<br>
										<a onclick="cancleStopOrderGroupAdmin('${item.id}');">取消终止</a>
										<br>
									</c:if>
								</td>
							</tr>
					</c:if>
				<c:if test="${s.index != 0}">
					<tr>
						<td>${orderDetail.price}元
							<c:if test="${item.orderStatus==202}">
								<span style="text-align:right;">
									<a href="javascript:void(0)" onclick="updateItemPrice(${orderDetail.orderDetailId})" class="btn btn-primary btn-xs">改单价</a>
								</span>								
							</c:if>	
						</td>
						<td>${orderDetail.spec}</td>  
						<td>${orderDetail.buyNum }${orderDetail.meterageUnit}</td>
						<td>${orderDetail.buyNum }${orderDetail.meterageUnit}<br>${orderDetail.detailPayMoney}元</td>  
					</tr>
				</c:if>
			</c:if>
				<c:if test="${p.index != 0}">    
				 		<c:if test="${s.index == 0}">
						<tr>  
							<td rowspan="${fn:length(pitem.orderDetailList)} "><div class="info"><div class="pull-left info-text"><p>${pitem.productName }</p></div></div></td> 
							<td>${orderDetail.price}元
								<span style="text-align:right;">
									<a href="javascript:void(0)" onclick="updateItemPrice(${orderDetail.orderDetailId})" class="btn btn-primary btn-xs">改单价</a>
								</span>
							 </td>
							<td>${orderDetail.spec }</td>  
							<td>${orderDetail.buyNum }${orderDetail.meterageUnit}</td>
							<td>${orderDetail.buyNum }${orderDetail.meterageUnit}
								<br>${orderDetail.detailPayMoney}元
							</td>
							<td rowspan="${fn:length(pitem.orderDetailList)} ">${pitem.buyNumCount}<br>￥${pitem.buyMoneyCount}元</td>   
						</tr>
						</c:if>
						
						 <c:if test="${s.index != 0}">
						 	<tr>
							<td>${orderDetail.price}元
								<c:if test="${item.orderStatus==202}">
									<span style="text-align:right;">
										<a href="javascript:void(0)" onclick="updateItemPrice(${orderDetail.orderDetailId})" class="btn btn-primary btn-xs">改单价</a>
									</span>									
								</c:if>	 
							</td>
								<td>${orderDetail.spec }</td>  
								<td>${orderDetail.buyNum }${orderDetail.meterageUnit}</td>
								<td>${orderDetail.buyNum }${orderDetail.meterageUnit}<br>${orderDetail.detailPayMoney}元</td>
							</tr>  
						</c:if> 
					</c:if> 
			</c:forEach>
		</c:forEach>
		</tbody>
	</table>
	</div>	
</c:forEach>
<liguo:pagination dataDomId="key_orderGroup_list" pageAction="serachOrderGroup" />