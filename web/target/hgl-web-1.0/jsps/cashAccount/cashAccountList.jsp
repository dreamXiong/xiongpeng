<%@page pageEncoding="UTF-8"%>
	<div class="merchandise stock">

		<table class="col-10 hover">
			<thead>
				<tr>
					<th width="270">时间</th>
					<th width="66">金额</th>
					<th width="126">账户总额</th>  
					<th width="68">类型</th>
					<th width="91">订单号</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="item" items="${cList}" varStatus="p">
					<tr  class="${p.index % 2 == 1 ? 'odd' : ''}">
						<td width="270"><liguo:dateFormatLabel value="${item.operationDt}" pattern="yyyy-MM-dd  HH:mm:ss" /></td>
						
						<c:if test="${item.cashOut == 0 || empty item.cashOut}">
							<td width="66">+<fmt:formatNumber value="${item.cashIn}" pattern="#,##0.00" />元</td>
						</c:if>
						<c:if test="${item.cashIn == 0 || empty item.cashIn}">
							<td width="66">-<fmt:formatNumber value="${item.cashOut}" pattern="#,##0.00" />元</td>
						</c:if>
						<td width="66"><fmt:formatNumber value="${item.balance}" pattern="#,##0.00" />元</td>
						
						<c:if test="${item.platform == 1}">
							<td width="66">订单支付</td>
						</c:if>
						<c:if test="${item.platform == 2}">
							<td width="66">返利</td>
						</c:if>
						<c:if test="${item.platform == 3}">
							<td width="66">提现</td>
						</c:if>
						<td width="91">${item.orderSerialNo}</td>  
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
<liguo:pagination dataDomId="key_cashAccount_list" pageAction="serachCashAccount" />