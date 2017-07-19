<%@page pageEncoding="UTF-8"%>
<table class="col-10 hover" id="mytable">
	<thead class="bg-gray">
		<tr>
			<th>年月份</th>
			<th>利润</th>
			<th>销量</th>
			<th>销售额</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${fn:length(profitsChangeList) <= 0}">
			<tr>
				<td colspan="4">没有数据</td>
			</tr>
		</c:if>
		<c:forEach var="item" items="${profitsChangeList}" varStatus="s">
			<tr>
				<td>${item.yearName }</td>
				<td>${item.profitMoney }</td>
				<td>${item.saleNum }</td>
				<td>${item.saleMoney }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>	
<liguo:pagination pageAction="serachInventory" />