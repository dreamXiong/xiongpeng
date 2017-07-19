<%@ page language="java" pageEncoding="UTF-8"%>
<form onsubmit="return false;" class="form-horizontal" id="saveInvertoryForm">
<input type="hidden" value="${warehouseId}" name="warehouseId"/>
<input type="hidden" value="${productId}" name="productId"/>
<h3>已有规格</h3>
<table class="table  no-margin">
	<tr class="bg-info">
		<th width="200">规格</th>
		<th width="200">材质</th>
		<th width="100">装箱数</th>
		<th width="100">入库量</th>
		<th width="100">库存量</th>
		<th width="100">锁定量</th>
		<th width="100">入库价格</th>
		<th width="100">出库价格</th>
		<th width="100">零售标价</th>
		<th width="100">状态</th>
		<th width="100">操作</th>
	</tr>
</table>
<div class="table-height" id="newInvertoryListDiv">
	<table class="table table-hover" id="norms">
			<c:forEach items="${specList}" var="item" varStatus="s">
				<tr id="SEPCTR_${item.id}">
					<td width="200">${item.spec}</td>
					<td width="200">${item.material}</td>
					<td width="100">${item.oneboxCount}</td>
					<td width="100">${item.totalInventory}</td>
					<td width="100">${item.saleInventory}</td>
					<td width="100">${item.unsaleInventory}</td>
					<td width="100">${item.instockPrice}</td>
					<td width="100">${item.outstockPrice}</td>
					<td width="100">${item.salesPrice}</td>
					<td width="100"><c:choose><c:when test="${item.status==1}">上架中</c:when><c:otherwise>已下架</c:otherwise></c:choose></td>
					<td width="100">
						<button for="${item.id}" type="button" class="btn btn-primary btn-xs updateButton">修改</button>
						<button for="${item.id}" type="button" class="btn btn-primary btn-xs deleteButton">删除</button>
					</td>
				</tr>
			</c:forEach>
	</table>
</div>
<h3>规格</h3>
<table class="table  table-bordered " id="addInventoryTable">
	<tr>
		<td>货号</td>
		<td>名称</td>
		<td>厂家指导价</td>
		<td>入库价格</td>
		<td>出库价格</td>
		<td>规格</td>
		<td>库存量</td>
	</tr>
	<tr>
		<td><input type="text" maxlength="30" class="form-control inputNotNull" name="code" id="codeInput"></td>
		<td><input type="text" maxlength="100" class="form-control" name="name" id="nameInput"></td>
		<td><input type="text" maxlength="10" class="form-control inputNotNull float" name="salesPrice" id="salesPriceInput"></td>
		<td><input type="text" maxlength="10" class="form-control inputNotNull float" name="instockPrice" id="instockPriceInput"></td>
		<td><input type="text" maxlength="10" class="form-control float" name="outstockPrice" id="outstockPriceInput"></td>
		<td><input type="text" maxlength="30" class="form-control inputNotNull" name="spec" id="specInput"></td>
		<td><input type="text" maxlength="8" class="form-control inputNotNull number" name="saleInventory" id="saleInventoryInput"></td>
	</tr>
	<tr>
		<td>材质</td>
		<td>装箱数</td>
		<c:forEach items="${attributeList}" var="attribute" varStatus="s">
			<td>${attribute}</td>
		</c:forEach>
	</tr>
	<tr>
		<td><input type="text" maxlength="30" class="form-control inputNotNull" name="material" id="materialInput"></td>
		<td><input type="text" maxlength="8" class="form-control inputNotNull number" name="oneboxCount" id="oneboxCountInput"></td>
		<c:forEach items="${attributeList}" var="attribute" varStatus="s">
			<td><input type="text" class="form-control" name="attributeValues[]" id="${attributeList}"></td>
		</c:forEach>
	</tr>
</table>
<div class="row text-right">
	<div class="col-sm-5">
		<button class="btn btn-primary" type="button" onclick="saveInventory('saveInvertoryForm')">保存</button>
	</div>
</div>
</form>