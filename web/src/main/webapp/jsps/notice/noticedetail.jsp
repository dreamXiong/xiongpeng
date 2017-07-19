<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
<tiles:put name="title" value="公告详情" />
<tiles:put name="body" type="String">  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>系统公告详情页</title>
	<script type="text/javascript">
	$("document").ready(function(){
		var timer = null;
		$('.name').hover(function(){
			$(this).find('.name-list').stop().slideDown(100);
			clearTimeout(timer);
		},
		function(){
			timer = setTimeout(nameHide,500);
		});
		
		function nameHide(){
			$('.name-list').stop().slideUp(300);
		}
	});
	</script>	
</head>
<body>
<!-- 内容板块开始 -->
	<div class="cantainer">
		<div class="area details">
			<div class="navigate">
				<ul class="clear">
					<li><a href="${ctx}/">首页></a></li>
					<li><a href="queryNoticeInfoList">列表页></a></li>
				</ul>
			</div>
			<div>
			<div class="pull-left details-left">
				<article>
					<header>
						<h3 class="text-center">${noticeInfo.noticeName}</h3>
						<p class="text-right"><small>发布时间：
							<jsp:useBean id="createddatetime" class="java.util.Date"/>  
    						<jsp:setProperty name="createddatetime" property="time" value="${noticeInfo.createddatetime}" />  
    						<fmt:formatDate value="${createddatetime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" />
						</small></p>
					</header>
					<section style="line-height: 2em">${noticeInfo.noticeContent}</section>					
				</article>
			</div>
			<div class="choose-right pull-right clear ">
				<div class="line-height text-center font-bold border-bottom-gray">
					推广产品
				</div>
				<ul>
					<li class="padding">
						<a href="javascript:"><img src="${ctx}/images/diandong-2.png" alt=""></a>
						<p><span class="text-red">￥5.8</span> <b>起 </b>含 <span class="text-red">5</span>个规格</p>
						<p class="text-ellipsis"><a href="javascript:">扳手/两用扳手/梅花呆扳手7mm，双头</a></p>
					</li>
					<li class="padding">
						<a href="javascript:"><img src="${ctx}/images/diandong-2.png" alt=""></a>
						<p><span class="text-red">￥5.8</span> <b>起 </b>含 <span class="text-red">5</span>个规格</p>
						<p class="text-ellipsis"><a href="javascript:">扳手/两用扳手/梅花呆扳手7mm，双头</a></p>
					</li>
					<li class="padding">
						<a href="javascript:"><img src="${ctx}/images/diandong-2.png" alt=""></a>
						<p><span class="text-red">￥5.8</span> <b>起 </b>含 <span class="text-red">5</span>个规格</p>
						<p class="text-ellipsis"><a href="javascript:">扳手/两用扳手/梅花呆扳手7mm，双头</a></p>
					</li>
					<li class="padding">
						<a href="javascript:"><img src="${ctx}/images/diandong-2.png" alt=""></a>
						<p><span class="text-red">￥5.8</span> <b>起 </b>含 <span class="text-red">5</span>个规格</p>
						<p class="text-ellipsis"><a href="javascript:">扳手/两用扳手/梅花呆扳手7mm，双头</a></p>
					</li>
				</ul>
			</div>
			
		</div>
	</div>
<!-- 内容板块结束 -->
</body>
</html>
</tiles:put>
</tiles:insert>