<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
<tiles:put name="title" value="公告列表" />
<tiles:put name="body" type="String">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>系统公告列表</title>
</head>
<script type="text/javascript">
function nameHide(){
	$('.name-list').stop().slideUp(300);
}

$("document").ready(function(){
		
	var timer = null;
	$('.name').hover(function(){
		$(this).find('.name-list').stop().slideDown(100);
	},
	function(){
		timer = setTimeout(nameHide,500);
	});		
});
		
</script>
<c:set value="noticeInfo" var="modalName"></c:set>
<body>
	<!-- 内容板块开始 -->
	<div class="cantainer">
		<div class="area news">
			<form action="queryNoticeInfoList" name="noticeForm" id="key_${modalName}_qryFrm" class="form-horizontal" method="post">
			</form>
			<div class="news-left pull-left ">
				<h4 class="bg-gray">系统公告</h4>      
				<div class="border-gray">
					<h5 class="line-height">
						标题 <span class="pull-right">发表时间</span>
					</h5>
					<div id="key_${modalName}_list" style="padding-bottom:10px;">					
						<%@include file="noticelistPage.jsp"%>	
					</div>					
				</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
	EcWeb.currentModalName = '${modalName}';
</script>
</html>
</tiles:put>
</tiles:insert>