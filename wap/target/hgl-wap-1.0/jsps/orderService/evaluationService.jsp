<%@page pageEncoding="UTF-8"%>
	<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<c:set value="服务评价" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<%@include file="../common/common.jsp"%>
	<script src="${pageContext.request.contextPath}/js/hgl/myOrderGroup.js"></script>
</head>	
<body>
<div>
<%@include file="../common/header.jsp"%>
</div>
<div class="container eva">        
	<div class="eva-info box-shadow" style="margin-top:10px;">
		<h1>
			<span>${serviceName}</span>           
		</h1>
		<div class="star">
			<ul>
				<li id="evaluateInfo">
					<span>总体评价</span>
					<nav>
						<a href="javascript:;" class="active"></a>
						<a href="javascript:;"></a>
						<a href="javascript:;"></a>
						<a href="javascript:;"></a>
						<a href="javascript:;"></a>
					</nav>
					<input id="evaluate" type="hidden" value="1">
				</li>
			</ul>
		</div>
		<form action="saveEvaluate" id="saveEvaluate" method="post">
			<textarea name="evaluate" id=""  rows="3" maxlength="100"></textarea>
			<input name="start" id="startL" type="hidden"></input>
			<input name=orderServiceId id="orderServiceId" value='${serciveId}' type="hidden"></input>
		</form>
		<div class="refer">
			<button type="button">提交</button>
		</div>
	</div>
</div>
</body>
<script>
	$(function() {
		var winH = $(window).height();
		var winW = $(window).width();
		$('body').css({
			width:winW,
			height:winH,
			overflow:'hidden'
		});
		$('.star nav').delegate('a', 'click', function(event) {
			var input = $(this).parents('li').find('input');
			$(this).addClass('active');
			$(this).prevAll().addClass('active');
			$(this).nextAll().removeClass('active');
			var len = $(this).parents('li').find('.active').length;
			$("#evaluate").val(len);
		});
		$('.refer').delegate('button','click',function(){
			$("#startL").val($("#evaluate").val());
			$("#saveEvaluate").submit();
			layer.open({content: '提交成功！',time: 2});
			
		});
	});
	
	
</script>
</html>
