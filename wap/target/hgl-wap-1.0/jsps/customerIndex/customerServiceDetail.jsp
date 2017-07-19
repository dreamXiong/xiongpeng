<%@page pageEncoding="UTF-8"%>
	<!doctype html>
<html>
<head>
 <meta charset="UTF-8">
	<title>服务详情</title>
	<c:set value="${c.name}" var="titleName"></c:set>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<%@include file="../common/common.jsp"%>
	<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css?v=1.0"/>
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=d7f07d528cd92643efcd3d2e73c09548"></script>
   <style>
		.particulars{
			white-space:pre-line;
			border-bottom:1px solid #eee;
		}
	</style> 
    
</head>
<body style="background:#fff;">

	<form action="${ctx}/wapOrderService/goAppointmentPage" id="goAppointmentPage" method="post">
		<input type="hidden" name="id" id="custromId"/>
	</form>
	
	<%@include file="../common/header.jsp"%>
<div class="container">
	<div class="swiper-container outlet" id="serve">
	    <div class="swiper-wrapper">
	       <c:if test="${not empty c.cimgOne}">
               <div class="swiper-slide"><img src='generateImage?id=${c.id}&imgName=${c.cimgOne}'/></div>
             </c:if>
             <c:if test="${not empty c.cimgTwo}">
               <div class="swiper-slide"><img src='generateImage?id=${c.id}&imgName=${c.cimgTwo}'/></div>
             </c:if>
             <c:if test="${not empty c.cimgThree}">
               <div class="swiper-slide"><img src='generateImage?id=${c.id}&imgName=${c.cimgThree}'/></div>
             </c:if>
	    </div>
	    <div class="swiper-pagination"></div>
	    <span class="collect" id="${c.id}">
	    	<input type="hidden" id="webUserId" value="${webUser.id}">
	    	<c:if test="${empty saveInfo}">
	    		<span style="background:rgba(242,0,14,.8);padding:8px 10px;position:absolute;top:0;right:0;" id="saveTxt">收藏服务</span>
	    		<input type="hidden" value="1" id="hiddenSaveInfo">
	    	</c:if>   
	    	<c:if test="${not empty saveInfo}">
	    		<span style="background:rgba(242,0,14,.8);padding:8px 10px;position:absolute;top:0;right:0;" id="saveTxt">取消收藏</span>
	    		<input type="hidden" value="0" id="hiddenSaveInfo">	
	    	</c:if>	    	 	
	    </span> 	       
	</div>
	<div class="serve_head">
		<ul class="clearfix" id="serve_list">
			<li class="pull-left text-center">
				<a href="javascript:;" class="cur">服务说明</a>
			</li>
			<li class="pull-left text-center">
				<a href="javascript:;">服务介绍</a>
			</li>
		</ul>
	</div>
	<div class="serve_show">
		<div class="serve_info" style="display:block;">
			<div class="referral">
				<div>
					<p  class="particulars">${c.describes}</p>
					
				</div>
			</div>
		</div>
		<div class="serve_info">
			
			<div class="referral">
				<h3>服务范围</h3>
				<div>
					<p>上门服务时间：8:00~23:00</p>
					<p>19:00~23:00为夜间服务段，需加收20元夜间服务费。</p>
				</div>
			</div>
			<div class="referral">
				<h3>服务特色</h3>
				<div>
					<div class="box">
						<div class="box1"><img src="${pageContext.request.contextPath}/images/15.jpg" alt=""></div>
						<div class="box1"><img src="${pageContext.request.contextPath}/images/16.jpg" alt=""></div>
					</div>	
					<div class="box">
						<div class="box1">
							<img src="${pageContext.request.contextPath}/images/17.jpg" alt="">
						</div>
						<div class="box1">
							<img src="${pageContext.request.contextPath}/images/18.jpg" alt="">
						</div>
					</div>	
				</div>
			</div>
			<div class="referral">
				<h3>用户保障</h3>
				<div class="box">
					<div class="box1">
						<img src="${pageContext.request.contextPath}/images/19.jpg" alt="">
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<footer class="footer serve-foot">
	<button type="button" onclick="goAppointmentPage('${c.id}');">立即预约</button>
</footer>
</body>
<script>
	$(function() {
		var winW = $(window).width();
		var height = winW*0.7;
		$('#serve .swiper-slide').height(height); 
		// 头部轮播图
		var swiper = new Swiper('.swiper-container', {
			autoplay : 5000,
	        pagination: '.swiper-pagination',
	    });
	    // tab切换
	    $('#serve_list li').on('click',function(){
	    	var index = $(this).index();
	    	var oUl = $(this).parents('#serve_list');
	    	oUl.find('a').removeClass('cur');
	    	$(this).find('a').addClass('cur');
	    	$('.serve_info').eq(index).show().siblings('.serve_info').hide();
	    });
	    $('tbody tr:odd').css('background','#fdfdfd');
	    // 收藏按钮
	    $('.collect').click(function(event) {
	    	var userId = $("#webUserId").val();
	    	var id = this.id;
	    	var typeId = $("#hiddenSaveInfo").val();
	    	if(userId=="" ||userId==null){
	    		window.location.href="${ctx}/login/";
	    	}
	    	$.ajax({
	    		type:"post",
	    		url:"${ctx}/customerIndex/doOperateServiceSaveInfo",
	    		data:{"id":id,"typeId":typeId},
	    		success:function(data){
					if(data=="1"){
						layer.open({
		    			    content:'收藏成功',
		    			    type:0,
		    			    time:1
		    			});
						$("#saveTxt").text("取消收藏");
						$("#hiddenSaveInfo").val(0);
					}else if(data=="-1"){
						layer.open({
		    			    content:'收藏失败',
		    			    type:0,
		    			    time:1
		    			});
					}else if(data=="0"){
						layer.open({
		    			    content:'您已收藏该服务',
		    			    type:0,
		    			    time:1
		    			});
					}else if(data=="2"){
						layer.open({
		    			    content:'取消成功',
		    			    type:0,
		    			    time:1
		    			});
						$("#saveTxt").text("收藏服务");
						$("#hiddenSaveInfo").val(1);
					}else if(data=="-2"){
						layer.open({
		    			    content:'取消失败',
		    			    type:0,
		    			    time:1
		    			});
					}else if(data=="3"){
						window.location.href="${ctx}/login/";
					}				
	    		}
	    	});

		});
	});
	function goAppointmentPage(id){
		$("#custromId").val(id);
		$("#goAppointmentPage").submit();
	}
</script>
</html>