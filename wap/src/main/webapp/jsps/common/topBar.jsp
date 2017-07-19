<%@page pageEncoding="UTF-8"%>
	<ul class="nav">
		<li>
		<%-- 	<c:if test="${empty session_key || session_key.typeId != 106}">
				<a href="${ctx}/indexShop"><span class="icon-home"></span> 首页</a>
			</c:if>
			<c:if test="${session_key.typeId == 106}">
				<a href="${ctx}/customerIndex/index"><span class="icon-home"></span> 首页</a>
			</c:if> --%>
			
			<a href="${ctx}/shop/userShop"><span class="icon-home"></span> 首页</a>
		</li>
		<li>
			<c:if test="${empty session_key || session_key.typeId == 106}">
				<a href="${ctx}/master/index"><span class="icon-search"></span> 找服务</a>
			</c:if>
			<c:if test="${session_key.typeId == 114}">
				<a href="${ctx}/wapOrderService/service"><span class="icon-th-large"></span> 接单</a>
			</c:if>
		</li>
		<li>
			<a href="${ctx}/pick/pickList"><span class="icon-shopping-cart"></span> 买材料</a>
		</li>
		<li>
			<a href="${ctx}/personalCenter/index"><span class="icon-user"></span> 我的</a>
		</li>
	</ul>    
	
	    