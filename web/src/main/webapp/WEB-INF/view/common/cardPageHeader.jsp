<%@page pageEncoding="UTF-8"%>
<div class="topBar">
	<%@include file="topBar.jsp" %>
</div>
<div class="top clearfix">
	<div class="logo" style=" float:left;"><a href="/"></a></div>
	<div class="buildVersion"></div> 
	<div class="topLine"></div>
	<!-- 搜索板块开始 -->
	<div class="search area">
		<div class="logo">
			<h1>
				<a href="${ctx}/">
					<img src="${ctx}/images/logo.png" height="100" width="200" alt="">
				</a>
			</h1>
		</div>
		<div class="search-box">
			<div class="search-bg">
				<input type="text" placeholder="输入厂家、品名、规格、材质、产地">
				<button type="button">搜 索</button>
			</div>
			<div class="search-text">
				<span><a href="javascript:" class="text-red">六角螺母</a></span>
				<span><a href="javscript:" class="text-red">八角扳手</a></span>
				<span><a href="javascript:">满额送</a></span>
				<span><a href="javascript:">招商</a></span>
			</div>
		</div>
		<div class="shopping" onclick="javascript:window.location.href='${ctx}/shoppingCar/index'">
			<i>${session_cart == null ? 0 : session_cart}</i>
			<span class="iconfont">&#xe622;</span>
			进货单
		</div>
		
	</div>
	
<!-- 搜索板块结束 -->
</div>




