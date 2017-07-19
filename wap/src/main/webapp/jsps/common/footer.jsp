
<!-- 师傅 -->
<%@page pageEncoding="UTF-8"%>
	<footer class="footer">
	<ul class="clearfix nav-bar text-center">
		<li>
			<span class="nav-bar-a">
				<a
					<c:if test="${session_key.typeId != 114}">
						<c:if test="${not empty shop_lon}">href="${ctx}/customerIndex/index" </c:if>
					</c:if>
					<c:if test="${session_key.typeId == 114}">
						<c:if test="${empty shop_lon}">href="${ctx}"</c:if>
						<c:if test="${not empty shop_lon}">href="${ctx}/indexShop" </c:if>
					</c:if>
				>
					<img src="${ctx}/images/nav-bar-a.png" alt="">
				</a>
			</span>
		</li>
		
		<li>
			<span class="nav-bar-b"> 
				<!-- 是师傅 -->
				<c:if test="${session_key.typeId == 114}">
					<a href="${ctx}/wapOrderService/service">
				</c:if>
				<!-- 不是师傅 -->
				 <c:if test="${session_key.typeId != 114}">
					<a href="${ctx}/master/index">
				</c:if> 
				
				<c:if test="${session_key.typeId == 114}">
					 <img src="${ctx}/images/nav-bar-b2.png">
				</c:if>
				
				<!-- 不是师傅 -->
				<c:if test="${session_key.typeId != 114}">
					<img src="${ctx}/images/nav-bar-b1.png">
				</c:if>
				</a>
			</span>
		</li>
		
		<li>
			<span class="nav-bar-c">
				<a href="${ctx}/pick/pickList">
					<img src="${ctx}/images/nav-bar-c.png" alt="">
				</a>
			</span>
		</li>
		
		<li>
			<span class="nav-bar-d">
				<a href="${ctx}/personalCenter/index">
					<img src="${ctx}/images/nav-bar-d.png" alt="">
				</a>
			</span>
		</li>
	</ul>
</footer>