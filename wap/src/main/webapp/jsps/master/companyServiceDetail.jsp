<%@page pageEncoding="UTF-8"%>
	<!doctype html>
<html>
<head>
 <meta charset="UTF-8">
	<title>公司信息</title>
	<c:set value="${pifd.companyName}" var="titleName"></c:set>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css?v=1.0"/>
	<%@include file="../common/common.jsp"%> 
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=d7f07d528cd92643efcd3d2e73c09548"></script>
   <style>
		.particulars{
			white-space:pre-line;
		}
	</style> 
   
</head>          
<body style="background:#fff;">
	<%-- <form action="${ctx}/wapOrderService/goAppointmentPage" id="goAppointmentPage" method="post">
		<input type="hidden" name="id" id="custromId"/>
	</form> --%>
	<%@include file="../common/header.jsp"%>
<div class="container">
	<div class="swiper-container outlet" id="serve">
	    <div class="swiper-wrapper">
	       <c:if test="${not empty pifd.companyImage1}">
               <div class="swiper-slide"><img src='generateComImage?id=${pifd.id}&imgName=${pifd.companyImage1}'/></div>
             </c:if>
             <c:if test="${not empty pifd.companyImage2}">
               <div class="swiper-slide"><img src='generateComImage?id=${pifd.id}&imgName=${pifd.companyImage2}'/></div>
             </c:if>
             <c:if test="${not empty pifd.companyImage3}">
               <div class="swiper-slide"><img src='generateComImage?id=${pifd.id}&imgName=${pifd.companyImage3}'/></div>
             </c:if>
	    </div>
	    <div class="swiper-pagination"></div>
	    <span class="navigation" onclick="goCompany();">           
			<span style="background:rgba(0,122,255,.8);padding:8px 10px;position:absolute;top:0;left:0;">导航</span>
		</span>
	</div>
	<%-- <div>
		距离${pifd.balance}km<br>
		公司地址：${pifd.provinceName }${pifd.cityName }${pifd.countryName }${pifd.streetName}${pifd.regAddress}<br>
		联系人：${pifd.contract }<br>
		<p style="font-size:14px;" "><a href="tel:${pifd.contractPhone }" class="icon-phone-sign" style="font-weight:bold;"> ${pifd.contractPhone }</a></p>
		<p style="font-size:24px;" "><a onclick="goCompany();" style="font-weight:bold;"> 导航</a></p>
	</div> --%>
	<div class="contact">
	<div class="contact-top clearfix">
		<p>
			<span class="icon-user" style="font-size:0.9rem;color:#66afe9"></span> 联系人：<span style="color:#f2000e;font-size:1rem;"></span>  
			${pifd.contract}
		</p>
		<p style="font-size:14px;" "><a href="tel:${pifd.contractPhone }" class="icon-phone-sign" style="font-weight:bold;"> ${pifd.contractPhone }</a></p>
	</div>
	          
	<div class="contact-top clearfix contact-bottom box">
		<p class="box2"><span class="icon-home" style="font-size:0.9rem;color:#f40;"></span>&nbsp;${pifd.provinceName }${pifd.cityName }${pifd.countryName }${pifd.streetName}${pifd.regAddress}</p>
		<p class="box1"><span class="icon-map-marker" style="font-size:0.9rem;"></span> <span id="blance">${pifd.balance}</span>km</p>
		<!-- <p style="font-size:24px;" "><a onclick="goCompany();" style="font-weight:bold;"> 导航</a></p> -->
	</div>
</div>
	<form id="addressForm" action="${ctx }/master/companyMap">
		<input type="hidden" name="companyLat" value="${pifd.lat }" />
		<input type="hidden" name="companyLon" value="${pifd.lon }" />   
	</form>
	<input type="hidden" name="userLat" id="userLatShop" value="${shop_lat}"/> 
	<input type="hidden" name="userLon" id="userLonShop" value="${shop_lon}"/> 
	
	 
	<div class="serve_show">
		<h3>
	 		 公司介绍
	 	</h3>
		<div class="serve_info" style="display:block;">
			<div class="referral">
				<div>
					<p  class="particulars">${pifd.describes}</p>
					
				</div>
			</div>
		</div>
	</div> 
</div>
<footer class="footer serve-foot">
	<button type="button" onclick="companyConsult();">信息咨询</button>
</footer>

<form id="companyConsult" action="${ctx }/companyConsult/index" method="post">
	<input type="hidden" name="companyName" value="${pifd.companyName}" />
	<input type="hidden" name="id" value="${pifd.id }" />   
</form>
</body>
<script>
	
	$(function() {	
		//头部轮播图
		var swiper = new Swiper('.swiper-container', {
			autoplay : 5000,
		    pagination: '.swiper-pagination'
		});
		var winW = $(window).width();
		var height = winW*0.7;
		$('#serve .swiper-slide').height(height); 
		
	    $('tbody tr:odd').css('background','#fdfdfd');
	});
	/* 去店铺导航*/
	 function goCompany(){
		layer.open({
	    content: '导航需消耗流量，确定使用吗？',
	    btn: ['确认', '取消'],
	    shadeClose: true,
	    yes: function(){
	    	$("#addressForm").submit();
	    	
	    }, no: function(){
	    	layer.close();
	    }
	});
}
	function companyConsult(){
		$("#companyConsult").submit();
	}
</script>
</html>