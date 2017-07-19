<%@page pageEncoding="UTF-8"%>
<div class="main-left pull-left">
	<div class="main-left-in">
		<div class="main-list">
			<c:forEach items="${parentList}" var="item">
				<dl>
					<dt><span class="icon-fire icon"></span>${ item.name }</dt>
					<c:forEach items="${item.childs}" var="itemChild">
						<dd class="list-hederTitle" style="padding-left:5px;">
							<a href="${ctx }${itemChild.actionUrl }"> ${ itemChild.name }</a>
							<c:if test="${itemChild.name =='站内信息'}">
								(<span id="letterCountNum" style="color: red;"title="未读消息">${sessionScope.letterCount}</span>)
							</c:if>
						</dd>
					</c:forEach>
					<dl>
			</c:forEach>
		</div>
	</div>
</div>


<script>
	$(document).ready(function(){
		$(".list-hederTitle a").each(function(){ 
			//if($($(this))[0].href==String(window.location)) 
				var hrefStr =$(this)[0].href;
				var hrefArr =hrefStr.split("?");
				
				var winStr =String(window.location);
				var winArr =winStr.split("?");
				if(hrefArr[0]==winArr[0]){
					$(this).parent().addClass('active'); 
					return false;
				}
		});
	});

	$(".list-hederTitle a").click(function(event) {
		$(this).parent().addClass('active').siblings("span").removeClass('active');
	});
</script>