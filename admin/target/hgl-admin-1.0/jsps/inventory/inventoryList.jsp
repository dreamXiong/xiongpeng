<%@page pageEncoding="UTF-8"%>
<table
	class="table table-bordered table-hover dataTable text-center table-bordered">
	<tr class="info">
		<th><input type="checkbox" id="tbpInventoryCheckbox" ></th>
		<th>名称</th>
		<th>分类</th>
		<th>品牌</th>
		<th>产品</th>
		<th>入库量</th>
		<th>库存量</th>
		<th>锁定量</th>
		<th>入库价格</th>
		<th>出库价格</th>
		<th>零售标价</th>
		<th>状态</th>
		<th>操作选项</th>
	</tr>
	<c:forEach items="${tbpiList}" var="tbpiItem" varStatus="s">
		<tr>
		<td><input type="checkbox" name="tbpInventoryIds[]" value="${tbpiItem.id}" class="tbpInventoryIds"></td>
		<td id="nameTd_${tbpiItem.id}">${tbpiItem.name}</td>
		<td>${tbpiItem.firstProductTypeName}<c:if test="${tbpiItem.secondProductTypeName!=null && tbpiItem.secondProductTypeName!=''}">-${tbpiItem.secondProductTypeName}</c:if><c:if test="${tbpiItem.thirdProductTypeName!=null && tbpiItem.thirdProductTypeName!=''}">-${tbpiItem.thirdProductTypeName}</c:if></td>
		<td>${tbpiItem.brandName}</td>
		<td>${tbpiItem.productName}</td>
		<td>${tbpiItem.totalInventory}</td>
		<td>${tbpiItem.saleInventory}</td>
		<td>${tbpiItem.unsaleInventory}</td>
		<td>${tbpiItem.instockPrice}</td>
		<td>${tbpiItem.outstockPrice}</td>
		<td>${tbpiItem.salesPrice}</td>
		<td id="statusTd_${tbpiItem.id}">
			<c:if test="${tbpiItem.status==1}">上架中</c:if>
			<c:if test="${tbpiItem.status!=1}">已下架</c:if>
		</td>
		<td id="buttonTd_${tbpiItem.id}">
			<button type="button" class="btn btn-primary btn-xs"
				onclick="modalAjax(${tbpiItem.id},'info')">详情</button>
			<%--<button type="button" class="btn btn-primary btn-xs"
				onclick="modalAjax(${tbpiItem.id},'update')">修改</button> --%>
			<button type="button" class="btn btn-primary btn-xs" onclick="updateStatus(${tbpiItem.id},${tbpiItem.version},${tbpiItem.status})">
				<c:if test="${tbpiItem.status==1}">下架</c:if>
				<c:if test="${tbpiItem.status!=1}">上架</c:if>
			</button>
			<%--<button type="button" class="btn btn-primary btn-xs"
				onclick="deleteAjax(${tbpiItem.id})">删除</button> --%>
		</td>
	</tr>
	</c:forEach>
</table>
<liguo:pagination pageAction="serachInventory" />