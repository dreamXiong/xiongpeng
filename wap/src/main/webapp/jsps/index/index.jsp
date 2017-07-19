<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>惠给力</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<%@include file="../common/common.jsp"%>
</head>	
<body>
<form method="post" id="indexShopInfo" action="indexShopInfo">
    <input type="hidden" value="" name="lon" id="lon" />
	<input type="hidden" value="" name="lat" id="lat" />
</form>
<div class="swiper-container">
    <div class="swiper-wrapper">
        <div class="swiper-slide"><img src="${pageContext.request.contextPath}/images/13.jpg" alt=""></div>
        <div class="swiper-slide"><img src="${pageContext.request.contextPath}/images/11.jpg" alt=""></div>
        <div class="swiper-slide"><img src="${pageContext.request.contextPath}/images/13.jpg" alt=""></div>
    </div>
    <div class="swiper-pagination"></div>
</div>

<div class="container" style="padding-top:0;">
	<div class="consult bg-white clearfix common">
		<div class="pull-left left-bg"></div>
		<div class="pull-left left-text">
			<ul class="consult-list">
			<c:forEach var="item" items="${tbNoticeInfolist}" varStatus="s">
				<li><a href="${ctx}/getNoticeInfoDetail?id=${item.id}">${item.noticeName}</a></li>
			</c:forEach>
			</ul>
		</div>
	</div>
	<div class="common nav-activity clearfix text-center bg-white">
		<div class="pull-left">
			<a href="${ctx}/master/index">找师傅</a>
		</div>
		<div class="pull-left">
			<a href="${ctx}/pick/pickList">找材料</a>
		</div>
	</div>
	<div class="nav-list" id="shopInfoList">
		 <%@include file="shopInfoList.jsp"%> 
	</div>
</div>
  	<footer class="footer">
		<ul class="clearfix nav-bar text-center">  
			<li><span class="nav-bar-a"><a href="${ctx}"><img src="${ctx}/images/index-red.png"></a></span></li>
			<li><span class="nav-bar-b"><a href="${ctx}/wapOrderService/service"><img src="${ctx}/images/order.png"></a></span></li>
			<li><span class="nav-bar-c"><a href="${ctx}/pick/pickList"><img src="${ctx}/images/pick.png"></a></span></li>
			<li><span class="nav-bar-d"><a href="${ctx}/personalCenter/index"><img src="${ctx}/images/mine.png"></a></span></li>
		</ul>
	</footer>
</body>
<script>
	var swiper = new Swiper('.swiper-container', {
		autoplay : 1000,
        pagination: '.swiper-pagination',
    });
	 function goShop(id,distance){
		window.location.href ="shop/index?id="+id+"&distance="+distance;
	 }
	$(function(){
		$('.nav-list li img').height($('.nav-list li img').width());
	});
	$(function(){
		$("img.lazy").lazyload({
			threshold : 20,
			effect : "fadeIn",
		});
	});
</script>
</html>