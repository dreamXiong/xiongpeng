<%@page pageEncoding="UTF-8"%>
<script src="${ctx}/js/hgl/topBar.js"></script>
<div class="nav">
	<div class="area">
		<div class="nav-left">
			<c:if test="${null==sessionScope.session_key.name}">
				<ul>
					<li style="padding-left: 0"><a href="#"
						onclick="JavaScript:addFavorite2()" rel="sidebar" >收藏网站</a></li>
					<li><a href="${ctx}/register/jxregister">注册</a></li>
					<li><a href="${ctx}/login/index">登录</a></li>
				</ul>
			</c:if>
			<c:if test="${null!=sessionScope.session_key.name }">
				<ul>
					<li style="padding-left: 0"><a
						href="${ctx}/webuser/doInitEditWebUser">欢迎
							${sessionScope.session_key.name}~</a></li>
					<li><a href="${ctx}/login/logout"> <i
							style="margin-top: 25px;" class="fa fa-sign-out"></i>退出</span>
					</a></li>
				</ul>
			</c:if>
		</div>      
		<div class="nav-right">   
			<ul>
				<li><a href="${ctx}/saleOrderGroup/index">销售订单</a></li>
				<li class="two-ac"><a href="${ctx}/register/jxregister">商家入驻</a></li>
				<li id="relation">
					<a href="javascript:">联系我们</a>
					<div class="addr">   
						<p>地址 : 深圳市龙华新区展滔科技大厦1201</p>
						<p>邮编 : 518005</p>
						<p>服务热线 : 0755-23772661</p>
						<p>传真 : 0755-23772661</p>
						<p>服务邮政 : sv@hgeili.com</p>
					</div>
				</li>
				<li style="padding-right: 0"><span class="icon-phone text-red"
					style="font-size: 16px"></span>&nbsp; <i class="text-red font-bold"   
					style="font-size: 16px">4006-718-278</i></li>
			</ul>
		</div>
	</div>    
</div>
<!-- 头部菜单结束 -->
<!-- 头部广告开始 -->
<div id="kitten" class="dropdown">
	<b class="colse">×</b>
	<div class="big">
		<img src="${ctx}/images/advertising-2.jpg" height="300" alt=""> 
	</div>
	<div class="small">
		<img src="${ctx}/images/advertising-1.jpg" height="90" >
	</div>
</div>