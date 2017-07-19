<%@page pageEncoding="UTF-8"%>
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
	<label>状态</label>
	<input type="text" disabled="disabled" value="${pifd.status == 0 ? '已下架' : '上架中' }">
</div>
<div>
	<label>分类</label>
	<input type="text" value='${pifd.firstProductTypeName}' disabled="disabled" style="width:64px;">
	<input type="text" value='${pifd.secondProductTypeName}' disabled="disabled" style="width:64px;">
	<input type="text" value='${pifd.thirdProductTypeName}' disabled="disabled" style="width:65px;">
</div>
<div>
	<label>品牌</label>
	<input type="text" value='${pifd.brandName}' disabled="disabled">
</div>	
<div>
	<label>产品</label>
	<input type="text" value='${pifd.productName}' disabled="disabled">
</div>	
<div>
	<label>货号</label>
	<input type="text" value="${pifd.code}" disabled="disabled">
</div>
<div>
	<label>名称</label>
	<input type="text" value="${pifd.name}" disabled="disabled">
</div>
<div>
	<label>市场价</label>
	<input type="text" value="${pifd.salesPrice}" disabled="disabled">
</div>
<div>
	<label>成本价</label>
	<input type="text" value="${pifd.instockPrice}" disabled="disabled">
</div>
<div>
	<label>销售价</label>
	<input type="text" value="${pifd.outstockPrice}" disabled="disabled">
</div>
<div>
	<label>规格</label>
	<input type="text" value="${pifd.spec}" disabled="disabled">
</div>
<div>
	<label>库存量</label>
	<input type="text" value="${pifd.saleInventory}" disabled="disabled">
</div>
<div>
	<label>材质</label>
	<input type="text" value="${pifd.material}" disabled="disabled">
</div>
<%-- <div>
	<label>装箱数</label>
	<input type="text" value="${pifd.oneboxCount}" disabled="disabled">
</div> --%>
<c:forEach var="attributeValue" items="${pifd.attributeValueList}" varStatus="s">
	<div>
		<label>${attributeValue.key}</label>
		<input type="text" value="${attributeValue.value}" disabled="disabled">
	</div>
</c:forEach>