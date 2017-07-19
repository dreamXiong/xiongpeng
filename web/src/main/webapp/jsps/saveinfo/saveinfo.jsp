<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
<tiles:put name="title" value="我的收藏夹"/>   
<tiles:put name="body" type="String"> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${ctx}/css/me.css">
<script src="${ctx}/js/hgl/saveinfo.js"></script>
</head>
<body>
	<div class="area me">
		<div class="main-right pull-right">
			<div class="main-nav">
				<div class="details main-title">
					<div class="details-list">
						<div class="main-head clear">
							<h3 class="active">商品收藏</h3>
							<h3>店铺收藏</h3>
						</div>
						<div class="ul-list">
							<ul class="clear" >
								<c:forEach items="${saveGoodInfos}" var="saveGoodInfo">
									<li id="goodInfo${saveGoodInfo.id}">		
										<a href="${ctx}/pick/pickDetail?id=${saveGoodInfo.saveId}&st=264">
											<img src="${ctx}/generateProductImage?id=${saveGoodInfo.saveId}&imgName=${saveGoodInfo.saveUrl}" title="${saveGoodInfo.saveName}" height="80%" width="100%">
										</a>								
										<div class="text-center"><a href="${ctx}/pick/pickDetail?id=${saveGoodInfo.saveId}&st=264" style="font-weight:bold;">${saveGoodInfo.brandName}</a></div>
										<b class="text-red" style="display:block;text-align:center;">￥ ${saveGoodInfo.price}</b>
										<span class="close" onclick="deleteGoodInfo(${saveGoodInfo.id})" title="取消">&times;</span>																					
									</li>
								</c:forEach>									
							</ul>
							<ul class="clear" style="display:none"> <!-- 店铺收藏 -->
								<c:forEach items="${saveShopInfos}" var="saveShopInfo">
									<li id="shoInfo${saveShopInfo.id}">
										<a href="${ctx}/merchants/merchantsDetails?bid=${saveShopInfo.brandId}">
											<img src="generateImage?brandId=${saveShopInfo.brandId}" title="${saveShopInfo.saveName}" width="100%" height="90%">
										</a>								
										<div class="text-center"><a href="${ctx}/merchants/merchantsDetails?bid=${saveShopInfo.brandId}" style="font-weight:bold;">${saveShopInfo.brandname}</a></div>
										<span class="close" onclick="deleteShopInfo(${saveShopInfo.id})" title="取消">&times;</span>	
									</li>
								</c:forEach>								
							</ul>
						</div>
					</div>
				</div>
			</div>				
		</div>
	</div>
	
<!-- 弹出层 -->
<div class="myModal">
	<div class="myModal-bg"></div>
	<div class="succeed">
		<h3>提示</h3>
		<p><i class="iconfont">&#xe620;</i><span id="modalSpan"></span></p>
	</div>		
</div>
</body>
</html>
</tiles:put>
</tiles:insert>