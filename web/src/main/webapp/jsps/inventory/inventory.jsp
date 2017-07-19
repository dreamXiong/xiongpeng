<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
	<tiles:put name="title" value="库存信息" />
	<tiles:put name="body" type="String">
	<c:set value="takl" var="modalName"></c:set>	
	<html>
		<link rel="stylesheet" href="${ctx}/css/jquery-ui.min.css">
		<link rel="stylesheet" href="${ctx}/css/jq-ui-rest.css">
		<link rel="stylesheet" href="${ctx}/css/me.css">
		<script src="${ctx}/js/hgl/inventory.js"></script>
	<body>
	<c:set value="inventory" var="modalName"></c:set>
<!-- 内容板块开始 -->
		<div class="area me">
			<div class="main-right pull-right">
				<div class="stock-edit stock">
					<form onsubmit="return false;" class="form-inline" id="key_${modalName}_qryFrm">
						<div class="stock_nav" style="margin-bottom:8px;">
							<div class="clear">
								<div class="pull-left" style="margin-right:10px;">
									产品状态   :   
									<select class="form-control" name="status" style="margin-right:10px;width:120px;" >
										<option value="">全部</option>
										<option value="1">上架中</option>
										<option value="0">已下架</option>    
									</select>       
								</div>
								<div class="pull-left" style="margin-top:5px;">  
									<button type="button" class="btn-bg" onclick="submitform()">
										<span>查询</span>
									</button>
									<button type="button" class="btn-bg" onclick="addInventory(${productId })">
										<span>添加</span>
									</button>
									<button type="button" class="btn-bg" id="put" onclick="batchUpdateStatus(1)">
										<span>批量上架</span>
									</button>
									<button type="button" class="btn-bg" id="but" onclick="batchUpdateStatus(0)">
										<span>批量下架</span>
									</button>
									<button type="button" class="btn-bg" id="pricing" onclick="batchUpdatePrice()">
										<span>批量定价</span>
									</button>
									<button type="button" class="btn-bg" onclick="history.back();">
										<span>返回</span>
									</button>
								</div>
							</div>	
							<div class="text-ellipsis" style="margin-top:5px;padding-top:8px;border-top:2px solid #f2000e;">
									产品名称 : <b>${tbpiItem.productName } --
											${tbpiItem.firstProductTypeName}<c:if test="${tbpiItem.secondProductTypeName!=null && tbpiItem.secondProductTypeName!=''}">-${tbpiItem.secondProductTypeName}</c:if><c:if test="${tbpiItem.thirdProductTypeName!=null && tbpiItem.thirdProductTypeName!=''}">-${tbpiItem.thirdProductTypeName}</c:if> -- 
											${tbpiItem.brandName }</b>
							</div>
							<input type="hidden" value="" name="id" id="inventoryId">
							<input type="hidden" value="${productId }" name="productId" id="inventProductId">
							<input type="hidden" value="" name="state" id="state">
      					  	<input type="hidden" value="" name="asPrice" id="asPrice">
      					  	<input type="hidden" value="" name="priceMethod" id="priceMethod">
      					  	<input type="hidden" value="" name="price" id="price">
							
						</div>	
					</form>
					<%-- <h3>
						<c:forEach items="${tbpiList}" var="tbpiItem" varStatus="s">
							<c:if test="${s.index == 0 }">
								<b>${tbpiItem.productName } -- </b>
								<b>${tbpiItem.firstProductTypeName}<c:if test="${tbpiItem.secondProductTypeName!=null && tbpiItem.secondProductTypeName!=''}">-${tbpiItem.secondProductTypeName}</c:if><c:if test="${tbpiItem.thirdProductTypeName!=null && tbpiItem.thirdProductTypeName!=''}">-${tbpiItem.thirdProductTypeName}</c:if></b>
								<b> -- ${tbpiItem.brandName }</b>
							
								<!-- <b>维修级尖嘴大力钳</b> -- <b>手动工具 - 扳手 - 呆扳手</b> -- <b>TAJIMA/田岛</b> -->
							</c:if>
						</c:forEach>
					</h3> --%>
					<div id="key_${modalName}_list" class="col-sm-12">
						<%@include file="inventoryList.jsp"%>
					</div>
				</div>

					
		</div>
	</div>	
<div id="dialog" class="pricing" style="display: none;">
	<label class="warp">
		<input type="radio" name="price" value="1">按成本价格
		<div class="inner">
			<label>
				<input type="radio" checked="checked" name="priceMethod1" value="1">按比例
				<input type="text" name="price1priceMethod1">%
			</label>
			<!-- <label>
				<input type="radio" name="priceMethod1" value="2">按单价
				<input type="text" name="price1priceMethod2">元
			</label> -->
		</div>
	</label>
	<label class="warp">
		<input type="radio" name="price" value="2">按市场标价
		<div class="inner">
			<label>
				<input type="radio" checked="checked" name="priceMethod2" value="1">按比例
				<input type="text" name="price2priceMethod1">%
			</label>
			<!-- <label>
				<input type="radio" name="priceMethod2" value="2">按单价
				<input type="text" name="price2priceMethod2">元
			</label> -->
		</div>
	</label>
	<label class="warp">
		<input type="radio" name="price" value="3">按销售标价
		<div class="inner">
			<label>
				<input type="radio" checked="checked" name="priceMethod3" value="1">按单价
				<input type="text" name="price3priceMethod1">元
			</label>
		</div>
	</label>
</div>
<!-- 批量定价的modal结束 -->

<!-- 详情的modal开始 -->
<div id="dialog1" class="details-modal" title="库存详情">
	
</div>
<!-- 批量定价的modal开始 -->
<div id="dialog2" class="put-warp" style="display: none;">
	您确定要批量上架？？
</div>
<div id="dialog4" class="put-warp" style="display: none;">
	您确定要删除吗？？
</div>
<div id="dialog5" class="put-warp">

</div>
<div id="dialog6" class="put-warp">

</div>
<div id="dialog7" class="put-warp">
	
</div>
<div id="dialog8" class="details-modal" title="库存管理" style="display: none;">
	<%@include file="operationInventory.jsp"%>
</div>
<div id="dialog9" class="put-warp">
	
</div>
<!-- 详情的modal结束 -->
</body>
<form id="submitInventory" method="post" action="${ctx}/inventory/index">
	<input type="hidden" value="" id="productId" name="productId"/>
</form>     
</html>
<script>
EcWeb.currentModalName = '${modalName}';

</script>
	</tiles:put>
</tiles:insert>