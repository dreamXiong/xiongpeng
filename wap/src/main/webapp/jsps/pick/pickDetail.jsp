<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<c:set value="产品详情" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<%@include file="../common/common.jsp"%>
	<script src="${pageContext.request.contextPath}/js/hgl/publicDecimal.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/hgl/pickDetail.js"></script>  
	<style>
		.particulars{
			white-space:pre-line;
			border-bottom:1px solid #eee;
		}
	</style> 
</head>	
<body>
<header style="-webkit-box-shaodow:none;box-shadow:none;">
	<input type="hidden" id="shopIdInput" value="${session_key.shopId}"/>
	<input type="hidden" id="shopIdInputDto" value="${dto.shopId }"/>
	<span id="loginUrl" style="display: none;">${ctx}/login/</span>
	<span id="isLogin" style="display: none;">${isLogin}</span>
	<span id="orderServiceId" style="display: none;">${orderServiceId}</span>
	<span class="icon-angle-left text-center" onclick="return_prepage();"></span>
	<div class="nav-header"> 
		<ul class="clearfix text-center">
			<li class="active">
				<a href="#" onclick="switchs('producta');" id="producta">产品</a>
			<li>
				<a href="#" onclick="switchs('detaila');" id="detaila">详情</a>
			</li>
		</ul>
		
	</div>
	<span class="icon-reorder"></span>
	<%@include file="../common/topBar.jsp"%>
</header>
<footer class="pick-footer">
	<div class="hgl-left pull-left">
		<div class="collect-save" id="${dto.id}">
			<c:choose>
				<c:when test="${saveInfo==null}">
					<span class="icon-heart-empty"></span>
					收藏
					<input type="hidden" value="1" id="operateBtn"/>          
				</c:when>
				<c:otherwise>
					<span class="icon-heart" style="color:red;"></span>
					收藏
					<input type="hidden" value="0" id="operateBtn"/>
				</c:otherwise>
			</c:choose>			
		</div>
		<div class="collect" onclick="window.location.href = '${ctx}/letter/letter?uId=${shopUserId}'">
			<span class="icon-github-alt"></span>
			客服
		</div>
		<div class="cart">
			<span class=" icon-shopping-cart" onclick="window.location.href = '${pageContext.request.contextPath}/shoppingCar/index'"></span>
			购物车
		</div>
	</div>
	<div class="hgl-right pull-right">
		<div class="added">
			<a href="javascript:;">${not empty orderServiceId ? '确认推送' : '加入购物车'}</a>
		</div>
		<div class="shopping">
			<a href="javascript:;">立即购买</a>
		</div>
	</div>
</footer>

<div class="container pick" id="productDiv" style="margin-bottom:0;margin-top:0;">
	<div class="swiper-container">
    	<div class="swiper-wrapper">       
 			<c:if test="${dto.pimgOne!=null && dto.pimgOne!=''}">
 				 <div class="swiper-slide"><img data-original="${ctx}/pick/generateImage?id=${dto.id}&imgName=${dto.pimgOne}" alt=""  src="${ctx}/images/no-load.gif" class="lazy" ></div>
 		    </c:if>
			<c:if test="${dto.pimgTwo!=null && dto.pimgTwo!=''}">
				<div class="swiper-slide"><img data-original="${ctx}/pick/generateImage?id=${dto.id}&imgName=${dto.pimgTwo}" alt=""  src="${ctx}/images/no-load.gif" class="lazy" ></div>
			</c:if>
			<c:if test="${dto.pimgThree!=null && dto.pimgThree!=''}">
				 <div class="swiper-slide"><img data-original="${ctx}/pick/generateImage?id=${dto.id}&imgName=${dto.pimgThree}" alt=""  src="${ctx}/images/no-load.gif" class="lazy" ></div>
			</c:if>	
    	</div>
    	<div class="swiper-pagination"></div>
	</div>
	    
	<div class="pick-title" style="margin-bottom:0;font-weight:bold;font-size:.9rem;color:#666;">${dto.name}@${dto.brandName}@${dto.mainTypeName}</div>
	
	<div class="pick-list" id="pickDiv"  style="margin-bottom:10px;">
		<ul>   
		<c:forEach items="${inventoryDtoList}" var="inventoryDto" varStatus="s">
			<li class="clearfix">            
				<div class="meter">  
					<span class="icon-caret-right"></span>   
					<dl>                  
						<dt class="clearfix">    
							<div>								
								<b style="font-size:.8rem;">规格:</b>
								<span>${inventoryDto.spec}</span>
							</div>
							<div>
								<b style="font-size:.8rem;">价格:</b>
								<%-- <span class='text-red'>${inventoryDto.outstockPrice}</span> --%>
								<span class="text-red font-bold">
									<span class="dollar">¥</span>
									<span class="unit" id="inventoryMax${inventoryDto.id}">00.</span>
									<span class="mark" id="inventoryMin${inventoryDto.id}">00</span>
								</span>
							</div>	
						</dt>
							
						<dd>材质: <span>${inventoryDto.material}</span></dd>
						<c:forEach var="attributeValue" items="${inventoryDto.attributeValueList}" varStatus="s">
							<dd>${attributeValue.key}: <span>${attributeValue.value}</span></dd>
						</c:forEach>
					</dl>
				</div>
				<div class="number">
					<input type="hidden" value="${inventoryDto.outstockPrice}" id="outstockMoney${inventoryDto.id}" />
				    <input type="hidden" value="${inventoryDto.buyNum}" id="hid${inventoryDto.id}" />
					<a href="javascript:;" class="min" id="min" onclick="addOrSubtract(${inventoryDto.id},'subtract')"></a>
					<input type="number" value="0" class="input" id="num${inventoryDto.id}" 
							onblur="validateOrderNum(${inventoryDto.id})" name="text_pick" maxlength="3"/>
					<a href="javascript:;" class='add' id="add" onclick="addOrSubtract(${inventoryDto.id},'add')"></a>
				</div>
			</li>
		</c:forEach>
		</ul>
	</div>
	<div class="pick-title" style="margin-bottom:60px;">
		<div class="box">
			<div class="box1 text-hidden" onclick="window.location.href = '${ctx}/shop/index?id=${dto.shopId }&distance=${dto.shopDistance }'">
				<span class="icon-home" style="color:#f2000e;"></span>
				${dto.shopName }
			</div>       
			<div class="box1 text-right">   
				<span class="icon-phone-sign"></span>	
				<a href="tel:${dto.shopPhone }">${dto.shopPhone }</a>	
			</div>    
		</div>   
		<div class="pick-title-info">
			&nbsp;<span class="icon-map-marker"></span>&nbsp;
			${dto.province }${dto.city }${dto.country }${dto.street }
		</div>
	</div>
</div>
<div class="referral-body" id="detailDiv" style="display: none;padding-top:44px;">
	<div>
		<p class="particulars">${dto.describes }</p>        
		<%-- <img class="lazy" data-original="${ctx}/images/advertising-1.jpg" src="${ctx}/images/blank.gif" alt="" >
		<img class="lazy" data-original="${ctx}/images/advertising-2.jpg" src="${ctx}/images/blank.gif"  alt=""> --%>
		<c:if test="${dto.dimgOne!=null && dto.dimgOne!=''}">   
			<img class="lazy" data-original="${ctx}/pick/generateImage?id=${dto.id}&imgName=${dto.dimgOne}" src="${ctx}/images/no-load.gif" alt="">
		</c:if>
		<c:if test="${dto.dimgTwo!=null && dto.dimgTwo!=''}">
			<img class="lazy" data-original="${ctx}/pick/generateImage?id=${dto.id}&imgName=${dto.dimgTwo}" src="${ctx}/images/no-load.gif"  alt="">
		</c:if>
		<c:if test="${dto.dimgThree!=null && dto.dimgThree!=''}">
			<img class="lazy" data-original="${ctx}/pick/generateImage?id=${dto.id}&imgName=${dto.dimgThree}" src="${ctx}/images/no-load.gif"  alt="">
		</c:if>
	</div>
</div>

<form id="submitLogin" method="post" action="${ctx}/login/">
	<input type="hidden" value="${dto.id }" id="productId" name="productId"/>
	<input type="hidden" value="${distance }" id="distance" name="distance"/>
</form>
<form id="submitNowBuy" method="post" action="${ctx}/shoppingCar/nowBuy">
	<input type="hidden" value="" id="orderListNum" name="orderListNum"/>
	<input type="hidden" value="" id="deleteIdsList" name="deleteIdsList"/>
</form>
<form id="submitConfirmPushCart" method="post" action="${ctx}/wapOrderService/confirmPushCart">
	<input type="hidden" value="" id="inventoryListNum" name="inventoryListNum"/>
	<input type="hidden" value="0" id="orderServiceSubmitFlag" name="orderServiceSubmitFlag"/>
</form>
</body>
<script type="text/javascript">
	$("document").ready(function(){
		var winW = $(window).width();
		var height = winW*0.7;
		$('.collect-save').click(function(event) {	
		    var id = this.id;
		    var typeId =$("#operateBtn").val();
			$.ajax({
				type:"post",
				url:"${ctx}/pick/doOperateProductSaveInfo",
				data:{"id":id,"typeId":typeId}, //1表示收藏,0表示取消收藏
				async:false,
				success:function(data){
					if(data=="1"){  //如果返回值为1表明收藏成功
						$("#"+id).html("");
						$("#"+id).html("<span class='icon-heart' style='color:red;'></span>收藏<input type='hidden' value='0' id='operateBtn'/>");
						layer.open({
						    content: '收藏成功',
						    type:0,
						    time:1,
						 });
					}else if(data=="-1"){  //如果返回-1则表明收藏失败
						layer.open({
						    content: '收藏失败',
						    type:0,
						    time:1,   
						 });
					}else if(data=="0"){
						layer.open({
						    content: '您已收藏该产品',
						    type:0,
						    time:1,
						});
					}else if(data=="2"){ //取消收藏成功
						layer.open({
						    content: '取消收藏成功',
						    type:0,
						    time:1,
						 });
						$("#"+id).html("");
						$("#"+id).html("<span class='icon-heart-empty'></span>收藏<input type='hidden' value='1' id='operateBtn'/>");
					}			
				},
			});		
		});
		
		$('#productDiv .swiper-slide').height(height);
	});
</script>
</html>