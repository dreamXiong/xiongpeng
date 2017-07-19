<%@page pageEncoding="UTF-8"%>
<form action="saveInventory" method="post" id="saveInventory">
<div>
	<label><b>锁定量</b></label>
	<span>${pifd.unsaleInventory}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<label><b>入库量</b></label>
	<span>${pifd.totalInventory}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<label><b>销售量</b></label>
	<span>${pifd.salesCount}</span>
</div>
<br/>
<div>
	<label>货号</label>
	<input type="text" value="${pifd.code}" name="code" id="code" disabled="disabled" maxlength="20">
	<input type="hidden" value="${pifd.id}" name="id">
	<input type="hidden" value="${pifd.productId}" name="productId" id="addProductId">
	<span id="codeTips"></span>
</div>
<div>
	<label>名称</label>
	<input type="text" value="${pifd.name}" name="name" id="name" maxlength="20">
</div>
<div>
	<label>市场价</label>
	<input type="text" value="${pifd.salesPrice}" name="salesPrice" id="salesPrice" maxlength="5">
</div>
<div>
	<label>成本价</label>
	<input type="text" value="${pifd.instockPrice}" name="instockPrice" id="instockPrice" maxlength="5">
</div>
<div>
	<label>销售价</label>
	<input type="text" value="${pifd.outstockPrice}" name="outstockPrice" id="outstockPrice" maxlength="5">
</div>
<div>
	<label>规格</label>
	<input type="text" value="${pifd.spec}" name="spec" id="spec" maxlength="20">
</div>
<div>
	<label>库存量</label>
	<input type="text" value="${pifd.saleInventory}" name="saleInventory" id="saleInventory" maxlength="5">
</div>
<div>
	<label>材质</label>
	<input type="text" value="${pifd.material}" name="material" id="material" maxlength="20">
</div>
<%-- <div>
	<label>装箱数</label>
	<input type="text" value="${pifd.oneboxCount}" name="oneboxCount" id="oneboxCount" maxlength="5">
</div> --%>
<c:forEach var="item" items="${attr}" varStatus="s">
   <c:if test="${not empty item}">
	   <div>
			<label>${item}</label>
			<input type="text" value="" name="attributeValues">
		</div>
   </c:if>
 </c:forEach>
 <c:forEach var="attributeValue" items="${pifd.attributeValueList}" varStatus="s">
 <div>
	<label>${attributeValue.key}</label>
	<input type="text" value="${attributeValue.value}" name="attributeValues">
</div>
</c:forEach>
</form>