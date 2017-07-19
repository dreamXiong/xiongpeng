<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
	<tiles:put name="title" value="产品信息" />
	<tiles:put name="body" type="String">
		<c:set value="takl" var="modalName"></c:set>
		<html>
<link rel="stylesheet" href="${ctx}/css/me.css">
<link rel="stylesheet" href="${ctx}/css/jquery-ui.min.css">
<link rel="stylesheet" href="${ctx}/css/jq-ui-rest.css">

<script src="${ctx}/js/jquery/jquery1.9.2/ui/jquery.ui.widget.js"></script>
<script src="${ctx}/js/hgl/product.js"></script>


<body>
	<c:set value="product" var="modalName"></c:set>
	<c:if test="${success==1}">
		<div class="main-right pull-right text-center" style="line-height:40px;">
					<span class="icon-ok-sign" style="color:#3CAD3C;font-size:30px;line-height:40px;display:inline-block;vertical-align:middle;"></span> 上传成功
		</div>
	</c:if>
	<c:if test="${success==2}">
		<div class="main-right pull-right text-center" style="line-height:40px;">
					<span class="icon-remove-sign" style="color:#E85445;font-size:30px;line-height:40px;display:inline-block;vertical-align:middle;"></span> ${message }
		</div>
	</c:if>
	<c:if test="${success==3}">
	
	<!-- 内容板块开始 -->
	<div class="area me">
		<div class="main-right pull-right">
			<div class="stock">
				<form action="${ctx}/import/productImport"
					enctype="multipart/form-data" method="post" class="form-inline"
					id="key_${modalName}_qryFrm">
					<div class="stock_nav" style="overflow-x:scroll;height:auto;line-height:24px;">
						<table class="hover" id="list" style="width:3000px;line-height:1.2;">
							<thead class="bg-gray">
								<tr>   
									<th width="120">出错原因</th>
									<th width="150">产品名称</th>
									<th>品牌名称</th>
									<th>产品价格</th>
									<th>分类</th>
									<th>附加属性</th>
									<th width="400">产品描述</th>
									<th>产品图片1</th>
									<th>产品图片2</th>
									<th>产品图片3</th>
									<th>描述图片1</th>
									<th>描述图片2</th>
									<th>描述图片3</th>
									<th>计量单位</th> 
									<th>货号</th>
									<th>库存</th>  
									<th>属性值</th>
									<th>入库价格</th>
									<th>出库价格</th>
									<th>零售价格</th>
									<th>规格</th>
									<th>材质</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${productAllList}" varStatus="s">
									<tr>
										<td>${item.message }</td>
										<td>${item.name }</td>
										<td>${item.brandId }</td>
										<td>${item.price }</td>
										<td>${item.producttypeId }</td>
										<td>${item.attributes }</td>
										<td>${item.describes }</td>
										<td>${item.pimgOne }</td>
										<td>${item.pimgTwo }</td>
										<td>${item.pimgThree }</td>
										<td>${item.dimgOne }</td>
										<td>${item.dimgTwo }</td>
										<td>${item.dimgThree }</td>
										<td>${item.meterageUnit }</td>
										<td>${item.code }</td>
										<td>${item.totalInventory }</td>
										<td>${item.attributesValues }</td>
										<td>${item.instockPrice }</td>
										<td>${item.outstockPrice }</td>
										<td>${item.salesPrice }</td>
										<td>${item.spec }</td>
										<td>${item.material }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</form>
			</div>
		</div>
	</div>
</c:if>
</body>
		</html>
		<script>
			EcWeb.currentModalName = '${modalName}';
		</script>
	</tiles:put>
</tiles:insert>