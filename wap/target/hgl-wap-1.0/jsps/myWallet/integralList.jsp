<%@page pageEncoding="UTF-8"%>
<c:forEach var="item" items="${tbIntegralDetailed}" varStatus="i">
		<li class="box text-center">     
			<div class="box1" style="border-right:1px solid #f8f8f8;">${item.dateString}<br/>${item.dateTimeString}</div>
			<div class="box1" style="border-right:1px solid #f8f8f8;">${item.typeString}</div>
			<div class="box2 text-hidden" style="border-right:1px solid #f8f8f8;color:#6060F3;font-size:0.8rem;">${item.orderSerialNo}</div>
			<div class="box1">${item.typeString=='商品兑换' ? '-' : ''}${item.integral}</div>                  
		</li>    
	</c:forEach>     