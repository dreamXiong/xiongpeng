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
		<div class="code">
			<div>
				<img src="${ctx}/images/code.png" height="60" width="60" alt="" class="pull-left">
				<a href="javascript:;" class="pull-left" onclick=" $('.suggest').dialog('open')" style="outline:none;">
					<img src="${ctx}/images/postbox.png" alt=""class="pull-left" style="margin:0 10px 0 20px">
					<div class="pull-left" style="margin-top:10px;">
						改善建议 <br>
						info@hgeili.com
					</div>    
				</a>
			</div>
		</div>
	</div>
	<div class="area commodity text-center">
		<div class="pull-left name">
			<span class="icon-list" style="vertical-align: middle;margin-left:5px"></span>
			商品分类
			<div class="name-list cantainer-left">
				<ul class="cantainer-warp-ui" id="productFirstTypeList">
					<c:forEach items="${sessionScope.productFstTypes}" var="productFstType">
						<li><a href="javascript:void(0)"><i class="iconfont">${productFstType.icon}</i>${productFstType.name}</a>
							<br>
							<div class="ui-drop-down">
								<c:forEach items="${productFstType.brands}" var="brand">
									<a href="${brand.url}"> 
										<img src='${ctx}/generateImage?id=${brand.id}'/>
									</a>
								</c:forEach>
							</div>
						</li>
					</c:forEach>
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


<!-- 改善建议 -->
<div  class="suggest" style="display:none">
	<div>
		<label>联系方式(选填)</label>	<input type="text">	
	</div>
	<div>
		<label>意见/建议</label> <textarea name="" id="" cols="30" rows="5"></textarea>
	</div>	
</div>
<!-- 提交建议完成弹出层 -->
<div class="suggest-ensure" style="display:none">    
	感谢你的建议，有您的支持我们会更加完美!	
</div>	

