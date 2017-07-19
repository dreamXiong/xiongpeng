<%@page pageEncoding="UTF-8"%>
<script src="${pageContext.request.contextPath}/js/hgl/shop.js"></script>
	<div class="screen">
		<header class="text-center">
			<span class="icon-angle-left text-center back"></span>筛选<a class='selectProductInfo'>确定</a>  
		</header>
		<div class="class-nav">
				<div class="class-nav-l">
					<ul>
					<c:forEach var="item" items="${productTypeList}" varStatus="s1">
						<li name="${s1.index}" <c:if test="${s1.index == 0}">  class='cur'</c:if>>
							${item.mainType}</li>
						</c:forEach>
					</ul>
				</div>
				<div class="class-nav-r">
					<div class="nav-r-main">
					<c:forEach var="item" items="${productTypeList}" varStatus="s2">
							<div class="hgl-list productShowHide" id="product_${s2.index }" 
								<c:if test="${s2.index != 0 }">
									style='display:none'
								</c:if>
							>
								<h3>产品分类</h3>
								<ul class="clearfix" id="productActive">
									<c:forEach var="p" items="${item.wapProductTypeDtoList}" varStatus="wp">
											<li><a name="${p.secondId }">${p.secondType }</a></li>
									</c:forEach>
								</ul>
							</div>
						</c:forEach>
						
						 <c:forEach var="item" items="${productTypeList}" varStatus="s3">
							<div class="hgl-list brandShowHide" id="brand_${s3.index}"
							<c:if test="${s3.index != 0 }">
									style='display:none'
								</c:if>
							>
								<h3>品牌分类</h3>
								<ul class="clearfix" id="brandActive">
									<c:forEach var="b" items="${item.wapBrandDtoList}" varStatus="wb">
										<li><a name="${b.brandId}" >${b.brandName}</a></li>
									</c:forEach>
								</ul>
							</div>	
						</c:forEach>
					</div>
				</div>
		</div>
	</div>