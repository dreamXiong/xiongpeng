<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
	<tiles:put name="title" value="添加产品分类" />
	<tiles:put name="body" type="String">
	<c:set value="takl" var="modalName"></c:set>
	<html>
<head>
	<meta charset="UTF-8">
	<title>添加产品分类</title>
	<link rel="stylesheet" href="${ctx}/css/me.css">
	<link rel="stylesheet" href="${ctx}/css/type.css">
	<script src="${ctx}/js/jquery/jquery1.9.2/ui/minified/jquery-ui.min.js"></script>
	<script src="${ctx}/js/hgl/datepicker-zh.js"></script>
	<script src="${ctx}/js/hgl/productType.js"></script>
</head>
<body>
<c:set value="addProductType" var="modalName"></c:set>
<input type="hidden" value="${mainId}" name="mainId" id="mainId"/>
<input type="hidden" value="${parentId}" name="parentId" id="parentId"/>
<input type="hidden" value="${mainName}" name="mainName" id="mainName"/>
<input type="hidden" value="${parentName}" name="parentName" id="parentName"/>
<!-- 内容板块开始 -->
	<div>
		<div class="area me">
			<div class="main-right pull-right">
				<div class="details">
					<div class="details-list">
						<h3 class="bg-gray">分类列表</h3>
						<div style="margin-top: 10px;float: right;">
							<a type="button" class="btn" style="height: 20px;margin-bottom: 2px;display: none;" href="${ctx}/product/index">返回库存列表</a>
						</div>
						<div id="types" class="clearfix">
							<div class="class-list">
								<ul class="one">
									<li>
										<a>一级分类</a>
									</li>
									<c:forEach var="item" items="${pList}" varStatus="s">
										<li onclick="firstProductType(${item.id},'${item.name}');" class="clearfix${s.index==0 ? ' cur' : ''}">  
					                      	<a herf="javascript:;" style="cursor:default;">${item.name}</a>
					                    </li>
									</c:forEach>
									<!-- <li class="cur">
										王者
									</li> -->
								</ul>
								 <c:if test="${not empty pList}">
								<ul class="two"  id="secondForAjax">
									<li  onclick="addSecond();" style="cursor:pointer;">
										<a  href="javascript:;">添加二级菜单</a>
									</li>
									</c:if>
									 <c:forEach var="sItem" items="${secondList}" varStatus="s">
					                	 <li onclick="scoendProductType(${sItem.id},'${sItem.name}');" class="clearfix${s.index==0 ? ' cur' : ''}" >
					                	 
						                      <a herf="javascript:;" style="cursor:default;">${sItem.name}</a>
						                  
					                    </li>
					                </c:forEach> 
								</ul>
								<c:if test="${not empty secondList}">
								<ul class="three" id="thirdForAjax">
									<li onclick="addthird();" style="cursor:pointer;">
										添加三级菜单
									</li>
									 <c:forEach var="tItem" items="${thirdList}" varStatus="s">
				                        <li  class="clearfix">
				                       
				                          <a herf="javascript:;" style="cursor:default;">${tItem.name }</a>
				                        
				                        </li>
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
	</div>
<!-- 弹出层==添加二级菜单-->
<div id="dialog" class="class-dialog" style="display:none;">

	<div class="add-pick">
		<input type="hidden" name="firstId" id="firstId" />
		<div>
			<label for="">产品大类:</label>
			<input   type="text" readonly="readonly" id="firstName" />
		</div>		
		<div>
			<label for="">二级分类</label>
			<input type="text" id="name" name="name" onblur="addValidateProductType();">
			<div id="errorText" style="color: red;padding-left:88px;" ></div> 
		</div>
		<div>
			<label for="">描述</label>
			<textarea name="describes" id="describes" style="width: 348px;height: 63px;"></textarea>
		</div>	
	</div>
	
</div>
<!-- 弹出层==添加二级菜单-->
<div id="dialog1" class="class-dialog" style="display:none;">

	<div class="add-pick">
		<input type="hidden" name="firstId" id="firstId1" />
		<input type="hidden" name="secondId" id="secondId" />
		<div>
			<label for="">产品大类:</label>
			<input   type="text" readonly="readonly" id="firstName1" />
		</div>
		<div>
			<label for="">二级分类:</label>
			<input   type="text" readonly="readonly" id="secondName1" />
		</div>		
		<div>
			<label for="">三级分类</label>
			<input type="text" id="thirdName" name="thirdName" onblur="addValidateThirdProductType();">
			<div id="errorText1" style="color: red;padding-left:88px;" ></div> 
		</div>
		<div>
			<label for="">描述</label>
			<textarea name="describes1" id="describes1" style="width: 348px;height: 63px;"></textarea>
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
	
    
	      
});
</script>
</html>
	</tiles:put>
</tiles:insert>