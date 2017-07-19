<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="classification">
<head>
	<meta charset="UTF-8">
	<c:set value="产品分类" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<%@include file="../common/common.jsp"%>
</head>	
<body>
<%@include file="../common/header.jsp"%>
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
</body>
<script>
	$(function() {
		var divH = 44;
		var winH = $(window).height();
		$('.class-nav-l').height(winH-divH);
		$('.class-nav-r').height(winH-divH);
		$('.class-nav-l li').click(function(event) {
			$(this).toggleClass('active');
			var index = $(this).attr("name");
			$(".productShowHide").hide();
			$(".brandShowHide").hide();
			$("#product_"+index).show();
			$("#brand_"+index).show();
			$(this).addClass('cur').siblings('li').removeClass('cur');
			$(".nav-r-main li .active").removeClass("active");
		});
	});
	$('.hgl-list').delegate('a','click',function(){
		$(this).toggleClass('active');
		
	});
</script>
</html>