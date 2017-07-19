<%@page pageEncoding="UTF-8"%>
<table class="col-10 hover" style="margin-bottom:10px;">
	<thead>   
		<tr>
			<th width="220">名称</th>
			<th width="210">分类</th>
			<th width="179">品牌</th>
			<th width="97">价格</th> 
			<th width="100">计量单位</th>     
			<th width="200">操作选项</th>  
		</tr>
	</thead>
	<tbody>
		<c:if test="${fn:length(productInfoList) <= 0}">
			<tr>
				<td colspan="6">没有数据</td>
			</tr>
		</c:if>
		<c:forEach var="item" items="${productInfoList}" varStatus="s">
			<c:if test="${s.index%2 == '0'}" var="isqi">
			  <tr>
			</c:if>
			<c:if test="${!isqi}">
			  <tr class="odd">
			</c:if>   
			   <td>${item.name}</td>
                  <td>
                     <c:if test="${item.mainType == item.parentType }">
                     	${item.parentType}-${item.thirdType}
                     </c:if>
                     <c:if test="${item.mainType != item.parentType }">
                     	${item.mainType}-${item.parentType}-${item.thirdType}
                     </c:if> 
                   </td>
                   <td> ${item.brandname}</td>   
                   <td>￥ ${item.price}</td>
                   <td> ${item.meterageUnit}</td>
				<td>
					<button type="button" class="btn" onclick="updateProduct(${item.id})">修改</button> |
					<button type="button" class="btn" onclick="modalAjax(${item.id})">详情</button> |
					<button type="button" class="btn" onclick="submitInventory('${item.id}')">编辑库存</button>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<form id="submitInventory" method="post" action="${ctx}/inventory/index">
	<input type="hidden" value="" id="productId" name="productId"/>
</form>
<liguo:pagination pageAction="serachProduct" />