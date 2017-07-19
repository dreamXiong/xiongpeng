<%@page pageEncoding="UTF-8"%>
<c:forEach var="item" items="${cashList}" varStatus="i">
	<li class="col-10 text-center">     
		<div class="col-3 text-hidden" style="border-right:1px solid #f8f8f8;">${item.dateString} ${item.dateTimeString}</div>
		<div class="col-2" style="border-right:1px solid #f8f8f8;">${item.platformString}</div>
		<div class="col-3 text-hidden" style="border-right:1px solid #f8f8f8;color:#6060F3;font-size:0.8rem;">
			<%-- <c:if test="${not empty item.shopBalance}">
				<a href="#" onclick="orderGroupDetail('${item.id}','${item.shopBalance}')">${item.orderSerialNo}</a>
			</c:if> --%>
				${item.orderSerialNo}
			<%-- <c:if test="${empty item.shopBalance}">
			
			</c:if> --%>
			</div>
		<div class="col-2">${item.cashString}</div>
	</li>    
</c:forEach>     
                                   