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
	<table class="table table-bordered" style="display:none;">
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
								<td style="width:25%;" rowspan="${fn:length(pitem.orderDetailList)} "><dl><dd>${pitem.productName }</dd></dl></td>
								<td style="width:10%;">${orderDetail.outstockPrice }元</td>
								<td style="width:10%;" >${orderDetail.spec }</td>
								<td style="width:10%;">${orderDetail.buyNum }${orderDetail.meterageUnit}</td>
								<td style="width:10%;">${orderDetail.buyNum   }${orderDetail.meterageUnit}<br>￥${orderDetail.detailPayMoney}元</td>
								<td rowspan="${fn:length(pitem.orderDetailList)} ">${pitem.buyNumCount}<br>￥${pitem.buyMoneyCount}元</td> 
								<td rowspan="${item.detailCount}" style="width:15%;">
									${item.buyCount}<!-- 件 -->
									<br> <c:if test='${not empty item.couponMoney }'>
										<del>￥${item.totalMoney}元</del> 
									￥${item.totalMoney - item.couponMoney}元<br>
									(使用优惠券-${item.couponMoney}) 
								</c:if> 
								<c:if test='${empty item.couponMoney }'>
										<%-- <del>￥${item.totalMoney}元</del>  --%>
									￥${item.totalMoney}元<br>
								</c:if> <br>
								<clochase:dictLabel key="${item.orderType}" /> <liguo:dictLabel key="${item.settleType}" />
								</td>
		
								<td rowspan="${item.detailCount}" style="width:10%;"><liguo:dictLabel key="${item.orderStatus}" /></td>
								<td rowspan="${item.detailCount}" style="width:10%;">
									<c:if test="${item.orderStatus == 208}">
										<a onclick="sendProduct('${item.id}','${item.version}');">发货</a>  
										<br>
									</c:if>
									 <c:if test="${item.orderStatus == 204}">   
										<a onclick="sendProduct('${item.id}','${item.version}');">待补发货</a>  
										<br>
									</c:if>
									 
								</td>
							</tr>
					</c:if>
				<c:if test="${s.index != 0}">
					<tr>
						<td>${orderDetail.price }元</td>
						<td>${orderDetail.spec }</td>  
						<td>${orderDetail.buyNum }${orderDetail.meterageUnit}</td>
						<td>${orderDetail.buyNum}${orderDetail.meterageUnit}<br>${orderDetail.detailPayMoney}元</td>  
					</tr>
				</c:if>
			</c:if>
				<c:if test="${p.index != 0}">     
				 		<c:if test="${s.index == 0}">
						<tr>  
							<td rowspan="${fn:length(pitem.orderDetailList)} "><div class="info"><div class="pull-left info-text"><p>${pitem.productName }</p></div></div></td> 
							<td>${orderDetail.price }元</td>
							<td>${orderDetail.spec }</td>  
							<td>${orderDetail.buyNum }${orderDetail.meterageUnit}</td>
							<td>${orderDetail.buyNum}${orderDetail.meterageUnit}
								<br>${orderDetail.detailPayMoney}元
							</td>
							<td rowspan="${fn:length(pitem.orderDetailList)} ">${pitem.buyNumCount}<br>￥${pitem.buyMoneyCount}元</td> 
						</tr>
						</c:if>
						
						<c:if test="${s.index != 0}">
							<tr>
							<td>${orderDetail.price }元</td>
								<td>${orderDetail.spec }</td>  
								<td>${orderDetail.buyNum }${orderDetail.meterageUnit}</td>
								<td>${orderDetail.buyNum }${orderDetail.meterageUnit}<br>${orderDetail.detailPayMoney}元
							</td>
							</tr>  
						</c:if> 
					</c:if> 
			</c:forEach>
		</c:forEach>  
		</tbody>
	</table>
	</div>
</c:forEach>
<liguo:pagination dataDomId="key_sendOutGoods_list" pageAction="serachOrderGroup" />