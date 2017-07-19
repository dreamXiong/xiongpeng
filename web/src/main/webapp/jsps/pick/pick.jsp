<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
	<tiles:put name="title" value="现货选购" />
	<tiles:put name="body" type="String">
	<c:set value="pick" var="modalName"></c:set>	
	<!DOCTYPE html>
	<html lang="en">
	<head>
		<meta charset="UTF-8">
		<script src="${ctx}/js/hgl/pick.js"></script>
	</head>
	<body>
	
	<!-- 内容板块开始 -->
		<div class="cantainer">
			<div class="area">
				 <div class="padding  text-gray">
					<a class="text-gray" href="${ctx}/">首页</a> <span>&nbsp;>&nbsp;</span> <a href="javascript:"><span>现货选购</span></a>
				</div> 
				<div class="clear">
					<div>
						<div class="clear margin-bottom-10 choose-left ">
							<div class="text-seek bg-gray clear line-height border-bottom-dashed">
								<div class="pull-left seek-title">热门产品  </div>
								<div class="pull-left seek-class">
									<a href="javascript:">150*50 扳手</a>
									<a href="javascript:">18 钳子</a>
									<a href="javascript:">150*50 扳手</a>
									<a href="javascript:">18 钳子</a>
									<a href="javascript:">150*50 扳手</a>
									<a href="javascript:">18 钳子</a>
								</div>
							</div>
							<div class="text-seek border-bottom-dashed clear">
								<div class="pull-left seek-title">大分类  </div>
								<div class="pull-left seek-class">
									<a href="javascript:" class="active" onclick="linkageAllPage()">全部 </a>
									<c:forEach var="item" items="${pList}" varStatus="s">
										<a href="javascript:" onclick="firstProductType(${item.id})">${item.name}</a>
									</c:forEach>
								</div>
							</div>
							<div class="text-seek border-bottom-dashed clear">
								<div class="pull-left seek-title seek-title-active">品&nbsp;&nbsp; 牌  </div>
								<div class="pull-left seek-class seek-title-active" style="padding-left:15px;margin-right:-13px" id="brandForAjax">
									<p class="line-height">红色边框表示为您已代理的品牌</p>
									<div class="seek-img">	
										<c:forEach var="tbBrand" items="${tbBrandList}" varStatus="s">		
											<div class="seek-label pull-left">
												<label for="name${tbBrand.id}"> 
													<input class="brandCheckbox" type="checkbox" value="${tbBrand.id}" id="name${tbBrand.id}">	
													<img src='../generateImage?id=${tbBrand.id}'   alt="${tbBrand.logoname}">
												</label>
											</div>
									<!-- <div class="seek-label pull-left border">
										<label for="name3"> 
											<input type="checkbox" id="name3">	
											<img src="${ctx}/images/warp-brand.png"   alt="${tbBrand.logoname}" >
										</label>
									</div>	 -->	
										</c:forEach>
									</div>			
								</div>
							</div>
							<div class="text-seek clear">
								<div class="pull-left seek-title">品&nbsp;&nbsp; 名  </div>
								<div class="pull-left seek-class" id="secondForAjax">
								 	<c:forEach var="sItem" items="${secondList}" varStatus="s">
										<a href="javascript:" onclick="secondProductType(${sItem.id});">${sItem.name}</a>
									</c:forEach>
								</div>
							</div>
							<div class="text-seek border-bottom-dashed clear" id="thirdForAjaxDiv" style="display: none">
								<div class="pull-left seek-title">产&nbsp;&nbsp; 品  </div>
								<div class="pull-left seek-class" id="thirdForAjax">
									<!-- <a href="javascript:">xx</a> -->
								</div>
							</div>
					</div>
					<!-- 表单提交 -->
					<form id="key_${modalName}_qryFrm">
						<input type="hidden" name="cursor" id="cursor" value="0">
						<input type="hidden" name="firstTypeId" id="firstTypeId" value="">
						<input type="hidden" name="secondTypeId" id="secondTypeId" value="">
						<input type="hidden" name="thirdTypeId" id="thirdTypeId" value="">
						<input type="hidden" name="brandIds" id="brandIds" value="">
						<input type="hidden" name="orderby" id="orderby" value="">
						<input type="hidden" name="ordersort" id="ordersort" value="">
						<input type="hidden" name="minprice" id="minprice" value="">
						<input type="hidden" name="maxprice" id="maxprice" value="">
						<input type="hidden" name="shoppingType" id="shoppingType" value="${st}">
					</form>
					<!-- 表格模块 -->
					<div id="key_${modalName}_list">
						<%@include file="productList.jsp"  %>
					</div>	
				</div>
				
				</div>
	
			</div>
		</div>
		
		<!-- 弹出层:收藏提示 -->	
		<div class="myModal">
			<div class="myModal-bg"></div>
			<div class="succeed">
				<h3>提示</h3>
				<p><i class="iconfont">&#xe620;</i><span id="modalSpan"></span></p>
			</div>		
		</div>	
	<!-- 内容板块结束 -->
		<script type="text/javascript">
			EcWeb.currentModalName = '${modalName}';
		</script>
	</body>
	</html>
	</tiles:put>
</tiles:insert>