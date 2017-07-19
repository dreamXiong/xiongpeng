<%@page pageEncoding="UTF-8"%>
<c:forEach var="item" items="${cashList}" varStatus="i">
	<li class="box text-center">     
		<div class="box1" style="border-right:1px solid #f8f8f8;">${item.dateString}<br/>${item.dateTimeString}</div>
		<div class="box1" style="border-right:1px solid #f8f8f8;">${item.platformString}</div>
		<div class="box2 text-hidden" style="border-right:1px solid #f8f8f8;color:#6060F3;font-size:0.8rem;">
			<%-- <c:if test="${not empty item.shopBalance}">
				<a href="#" onclick="orderGroupDetail('${item.id}','${item.shopBalance}')">${item.orderSerialNo}</a>
			</c:if> --%>
				${item.orderSerialNo}
			<%-- <c:if test="${empty item.shopBalance}">
			
			</c:if> --%>
			</div>
		<div class="box1">${item.cashString}</div>
	</li>    
</c:forEach>     
