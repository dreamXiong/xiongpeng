<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>排期表</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/my.css"/>
	<%@include file="../common/common.jsp"%> 
</head>
<body>
	<header class="collect-head">
		<span class="icon-angle-left text-center" onclick="return_prepage();"></span>
		<h3 class="text-center">排期表</h3>
	</header>
	<nav class="collect-nav box-shadow">
		<ul class="box clearfix text-center date">
			<li class="box1 active">
				<a href="javascript:;">
					<span>06月06日</span>
					<span>日</span>
				</a>
			</li>
			<li class="box1">
				<a href="javascript:;">
					<span>06月07日</span>
					<span>日</span>
				</a>
			</li>
			<li class="box1">
				<a href="javascript:;">
					<span>06月08日</span>
					<span>日</span>
				</a>
			</li>
		</ul>
	</nav>
<div class="container" style="margin-top:105px;margin-bottom:0;">
	<div class="date-title">
		<div class="date-list bg-gray">
			<strong>08:00-12:00</strong><br>
			<span>(正常收费)</span>
			<img src="${ctx }/images/active.png" alt="">
		</div>
		<div class="date-list">
			<strong>12:00-19:00</strong><br>
			<span>(正常收费)</span>
			<img src="${ctx }/images/active.png" alt="">
		</div>
		<div class="date-list">
			<strong>19:00-23:00</strong><br>
			<span>(收费较平常会稍贵)</span>
			<img src="${ctx }/images/active.png" alt="">
		</div>
	</div>
</div>
</body>
</html>
<script>
	$(function() {
		$('.collect-nav li').click(function(event) {
			$(this).addClass('active').siblings('li').removeClass('active');
		});
		$('.date-list').click(function(event) {
			$('.date-list').find('img').hide();
			if ($(this).hasClass('bg-gray')) {
				return false;
			}else{
				$(this).find('img').show();
			};
		});
	});
</script>