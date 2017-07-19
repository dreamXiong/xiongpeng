<%@page pageEncoding="UTF-8"%>
<table class="col-10 hover" id="mytable">
	<thead class="bg-gray">
		<tr>
			<th>序号</th>
			<th>客户</th>
			<th>盈利</th>
			<th>销售额</th>
			<th>推荐人</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${fn:length(customerInterestsList) <= 0}">
			<tr>
				<td colspan="5">没有数据</td>
			</tr>
		</c:if>
		<c:forEach var="item" items="${customerInterestsList}" varStatus="s">
			<tr>
				<td>${s.index+1 }</td>
				<td>${item.userName }</td>
				<td>${item.profitMoney }</td>
				<td>${item.salePrice }</td>
				<td>${item.recommendName }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>	
<liguo:pagination pageAction="serachInventory" />