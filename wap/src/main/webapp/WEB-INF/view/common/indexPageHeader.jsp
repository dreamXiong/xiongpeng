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
			<i>${session_cart }</i>
			我的购物车
			<div class="shopping-in">
				<div class="shop-in"></div>
				购物车中还没有商品，赶紧选购吧！
			</div>
		</div>
	</div>
	<div class="area commodity text-center">
		<div class="pull-left name">
			<span class="icon-list" style="vertical-align: middle;margin-left:5px"></span>
			商品分类

			<div class="name-list cantainer-left">
				<ul class="cantainer-warp-ui">
						<li>
							
							<a href="javascript:"><i class="iconfont">&#xe601; </i> 手动工具</a>
							<div class="ui-drop-down">
								<a href="javascript:">
									<img src="${ctx}/images/warp-logo.png"   alt="">
								</a>
								<a href="javascript:">
									<img src="${ctx}/images/warp-logo.png"   alt="">
								</a>
								<a href="javascript:">
									<img src="${ctx}/images/warp-logo.png"   alt="">
								</a>
								<a href="javascript:">
									<img src="${ctx}/images/warp-logo.png"   alt="">
								</a>
								<a href="javascript:">
									<img src="${ctx}/images/warp-logo.png"   alt="">
								</a>
								<a href="javascript:">
									<img src="${ctx}/images/warp-logo.png"   alt="">
								</a>
								<a href="javascript:">
									<img src="${ctx}/images/warp-logo.png"   alt="">
								</a>
								<a href="javascript:">
									<img src="${ctx}/images/warp-logo.png"   alt="">
								</a>
								<a href="javascript:">
									<img src="${ctx}/images/warp-logo.png"   alt="">
								</a>
								<a href="javascript:">
									<img src="${ctx}/images/warp-logo.png"   alt="">
								</a>

								<a href="javascript:">
									<img src="${ctx}/images/warp-logo.png"   alt="">
								</a>
								<a href="javascript:">
									<img src="${ctx}/images/warp-logo.png"   alt="">
								</a>
								<a href="javascript:">
									<img src="${ctx}/images/warp-logo.png"   alt="">
								</a>

								<a href="javascript:">
									<img src="${ctx}/images/warp-logo.png"   alt="">
								</a>
								<a href="javascript:">
									<img src="${ctx}/images/warp-logo.png"   alt="">
								</a>
								<a href="javascript:">
									<img src="${ctx}/images/warp-logo.png"   alt="">
								</a>
								<a href="javascript:">
									<img src="${ctx}/images/warp-logo.png"   alt="">
								</a>
								<a href="javascript:">
									<img src="${ctx}/images/warp-logo.png"   alt="">
								</a>
								<a href="javascript:">
									<img src="${ctx}/images/warp-logo.png"   alt="">
								</a>
								<a href="javascript:">
									<img src="${ctx}/images/warp-logo.png"   alt="">
								</a>
								<a href="javascript:">
									<img src="${ctx}/images/warp-logo.png"   alt="">
								</a>
								<a href="javascript:">
									<img src="${ctx}/images/warp-logo.png"   alt="">
								</a>
								<a href="javascript:">
									<img src="${ctx}/images/warp-logo.png"   alt="">
								</a>
								<a href="javascript:">
									<img src="${ctx}/images/warp-logo.png"   alt="">
								</a>
								<a href="javascript:">
									<img src="${ctx}/images/warp-logo.png"   alt="">
								</a>

							</div>
						</li>
						<li>
							
							<a href="javascript:"><i class="iconfont">&#xe607;</i> 电动工具</a>
							<div class="ui-drop-down">
								<a href="javacscript:">
									<img src="${ctx}/images/warp-brand.png"  alt="">
								</a>
								<a href="javacscript:">
									<img src="${ctx}/images/warp-brand.png"  alt="">
								</a>
								<a href="javacscript:">
									<img src="${ctx}/images/title-bg.png"  alt="">
								</a>
								<a href="javacscript:">
									<img src="${ctx}/images/logo.png"  alt="">
								</a>
								<a href="javacscript:">
									<img src="${ctx}/images/warp-brand2.png"  alt="">
								</a>
								<a href="javacscript:">
									<img src="${ctx}/images/logo.png"  alt="">
								</a>
								<a href="javacscript:">
									<img src="${ctx}/images/logo.png"  alt="">
								</a>

							</div>
						</li>
						<li>
							
							<a href="javascript:"><i class="iconfont">&#xe600;</i> 橱柜/电器</a>
							<div class="ui-drop-down">
								<a href="javacscript:">
									<img src="${ctx}/images/warp-brand.png"  alt="">
								</a>
								<a href="javacscript:">
									<img src="${ctx}/images/warp-brand.png"  alt="">
								</a>
								<a href="javacscript:">
									<img src="${ctx}/images/title-bg.png"  alt="">
								</a>
								<a href="javacscript:">
									<img src="${ctx}/images/logo.png"  alt="">
								</a>
								<a href="javacscript:">
									<img src="${ctx}/images/warp-brand2.png"  alt="">
								</a>
								<a href="javacscript:">
									<img src="${ctx}/images/logo.png"  alt="">
								</a>
								<a href="javacscript:">
									<img src="${ctx}/images/logo.png"  alt="">
								</a>
								<a href="javacscript:">
									<img src="${ctx}/images/warp-brand.png"  alt="">
								</a>
								<a href="javacscript:">
									<img src="${ctx}/images/warp-brand.png"  alt="">
								</a>
								<a href="javacscript:">
									<img src="${ctx}/images/title-bg.png"  alt="">
								</a>
								<a href="javacscript:">
									<img src="${ctx}/images/logo.png"  alt="">
								</a>
								<a href="javacscript:">
									<img src="${ctx}/images/warp-brand2.png"  alt="">
								</a>
								<a href="javacscript:">
									<img src="${ctx}/images/logo.png"  alt="">
								</a>
								<a href="javacscript:">
									<img src="${ctx}/images/logo.png"  alt="">
								</a>
							</div>
						</li>
						<li>
							
							<a href="javascript:"><i class="iconfont">&#xe605;</i> 水暖/卫浴/洁具</a>
							<div class="ui-drop-down">
								<a href="javacscript:">
									<img src="${ctx}/images/warp-brand.png"  alt="">
								</a>
								<a href="javacscript:">
									<img src="${ctx}/images/warp-brand.png"  alt="">
								</a>
								<a href="javacscript:">
									<img src="${ctx}/images/title-bg.png"  alt="">
								</a>
								<a href="javacscript:">
									<img src="${ctx}/images/logo.png"  alt="">
								</a>
								<a href="javacscript:">
									<img src="${ctx}/images/warp-brand2.png"  alt="">
								</a>
								<a href="javacscript:">
									<img src="${ctx}/images/logo.png"  alt="">
								</a>
								<a href="javacscript:">
									<img src="${ctx}/images/logo.png"  alt="">
								</a>
							</div>
						</li>
						<li>
							<a href="javascript:"><i class="iconfont">&#xe60a;</i> 照明/交电</a>
							<div class="ui-drop-down">
								<a href="javacscript:">
									<img src="${ctx}/images/warp-brand.png"  alt="">
								</a>
								<a href="javacscript:">
									<img src="${ctx}/images/warp-brand.png"  alt="">
								</a>
								<a href="javacscript:">
									<img src="${ctx}/images/title-bg.png"  alt="">
								</a>
								<a href="javacscript:">
									<img src="${ctx}/images/logo.png"  alt="">
								</a>
								<a href="javacscript:">
									<img src="${ctx}/images/warp-brand2.png"  alt="">
								</a>
								<a href="javacscript:">
									<img src="${ctx}/images/logo.png"  alt="">
								</a>
								<a href="javacscript:">
									<img src="${ctx}/images/logo.png"  alt="">
								</a>
							</div>
						</li>
						<img src="${ctx}/images/warp-logo.png" height="100" width="196" alt="">
					</ul>
			</div>
		</div>
		<div class="pull-left sort">
			<ul>
				<li><a href="${ctx}/">首页</a></li>
				<li><a href="${ctx}/pick/index">现货选购</a></li>
				<li><a href="${ctx}/merchants/merchants">招商</a></li>
				<li><a href="javascript:">活动折扣</a></li>
				<li><a href="javascript:">支付结算</a></li>
			</ul>
		</div>
	</div>
<!-- 搜索板块结束 -->
</div>

