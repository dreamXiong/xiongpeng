<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
	<tiles:put name="title" value="产品分类" />
 <tiles:put name="body" type="String"> 
		<c:set value="takl" var="modalName"></c:set>
		<html>
<head>
<meta charset="UTF-8">
<title>产品分类</title>
<link rel="stylesheet" href="${ctx}/css/me.css">
<link rel="stylesheet" href="${ctx}/css/type.css">
<script src="${ctx}/js/jquery/jquery1.9.2/ui/minified/jquery-ui.min.js"></script>
<script src="${ctx}/js/hgl/datepicker-zh.js"></script>
<script src="${ctx}/js/hgl/display_productType.js"></script>
</head>
<body>
	<c:set value="addProductType" var="modalName"></c:set>
	<input type="hidden" value="${mainId}" name="mainId" id="mainId" />
	<input type="hidden" value="${parentId}" name="parentId" id="parentId" />
	<input type="hidden" value="${mainName}" name="mainName" id="mainName" />
	<input type="hidden" value="${parentName}" name="parentName"
		id="parentName" />
	<!-- 内容板块开始 -->
	<div>
		<div class="area me">
			<div class="main-right pull-right">
				<div class="details">
					<div class="details-list">
						<h3 class="bg-gray">分类列表</h3>
						<div id="types" class="clearfix">
							<div class="class-list">
								<ul class="one">
									<li><a>一级分类</a></li>
									<c:forEach var="item" items="${pList}" varStatus="s">
										<li onclick="firstProductType(${item.id},'${item.name}');"
											class="clearfix${s.index==0 ? ' cur' : ''}"><a
											herf="javascript:;" style="cursor: default;">${item.name}</a>
										</li>
									</c:forEach>
									<!-- <li class="cur">
										王者
									</li> -->
								</ul>
								<c:if test="${not empty pList}">
									<ul class="two" id="secondForAjax">
										<li style="cursor: pointer;"><span>二级菜单</span></li>
								</c:if>
								<c:forEach var="sItem" items="${secondList}" varStatus="s">
									<li onclick="scoendProductType(${sItem.id},'${sItem.name}');"
										class="clearfix${s.index==0 ? ' cur' : ''}"><a
										herf="javascript:;" style="cursor: default;">${sItem.name}(${sItem.id})</a>
									</li>
								</c:forEach>
								</ul>
								<c:if test="${not empty secondList}">
									<ul class="three" id="thirdForAjax">
										<li style="cursor: pointer;"><span>三级菜单</span></li>
										<c:forEach var="tItem" items="${thirdList}" varStatus="s">
											<li class="clearfix"><a herf="javascript:;"
												style="cursor: default;">${tItem.name }(${tItem.id})</a></li>
										</c:forEach>

									</ul>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
</body>
<script>
EcWeb.currentModalName = '${modalName}';
$(function() {

	$('.class-list ul li').eq(0).css({
		'cursor':'pointer'
	});
	$('.class-list ul').delegate('li:gt(0)','click',function(){
		$(this).addClass('cur').siblings('li').removeClass('cur');            
	});
	$('.main-left').remove();
    
	      
});
</script>
		</html>
	</tiles:put>
</tiles:insert>