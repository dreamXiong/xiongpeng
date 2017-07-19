<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
	<tiles:put name="title" value="品牌列表" />
	<tiles:put name="body" type="String">
	<html>

	<meta charset="UTF-8">
	<title>品牌列表</title>
	<link rel="stylesheet" href="${ctx}/css/me.css">
	<script src="${ctx}/js/jquery/jquery1.9.2/ui/minified/jquery-ui.min.js"></script>
	<script src="${ctx}/js/hgl/datepicker-zh.js"></script>
	
	<script src="${ctx}/js/jquery/jquery1.9.2/ui/jquery.ui.widget.js"></script>
	
	<c:set value="brand" var="modalName"></c:set>
<body>
<!-- 内容板块开始 -->
		<div class="main-right" style="width:auto;">
			<div id="key_${modalName}_list">     
				<%@include file="display_brandList.jsp" %>
			</div>	
		</div>	
</body>
	<script type="text/javascript">
	EcWeb.currentModalName = '${modalName}';
	</script>
	<script>
		$(function(){
			
			$('.main-left').remove();
			
		})
		
	</script>	
</html>
	</tiles:put>
</tiles:insert>