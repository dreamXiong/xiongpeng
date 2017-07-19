<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
	<tiles:put name="title" value="" />
	<tiles:put name="body" type="String">
	<c:set value="pick" var="modalName"></c:set>	
<head>
	<meta charset="UTF-8">
	<title>招商服务购买</title>
	<link rel="stylesheet" href="${ctx}/css/tender-list.css">
	<link href="${ctx}/css/toastr.css" rel="stylesheet" type="text/css" /> 
	<script src="${ctx}/js/hgl/agency.js"></script>
	<script src="${ctx}/js/hgl/toastr.js"></script>
</head>
<body>
	<div class="cantainer serve">
		<div class="area">
			<c:if test="${isMerchantService }">
			<div class="matter">
				<h3 class="bg-gray">招商商品购买量</h3>
				<span class="text-red" > ${merchants.number} </span>元
			</div>
			<div class="matter">
				<h3 class="bg-gray">代理区域</h3>
				<span>${merchants.placesName}</span>
			</div>
			<div class="matter">
				<h3 class="bg-gray">代理品牌</h3>
				<span>${merchants.producttypeName} - ${merchants.brandName}</span>
			</div>
			<div class='syn'>
				您将获得<span class="text-red"> ${merchants.placesName}</span>的
				<span class="text-red">"${merchants.producttypeName} - ${merchants.brandName}"</span> 品牌的独家代理权，同时我们会附送您
				<span class="text-red font-bold">  ${merchants.coupons} </span> 元优惠券，同时你需要继续完成招商货品约定的购买额。
			</div>
			<form id="agency" action="agency" class="form-horizontal" method="post">
			<input type="hidden" id="mid" name="mid" value="${merchants.id}">
			<input type="hidden" id="bid" name="bid" value="${merchants.brandid}">
			<div class="text-right money" >
				<div class="referrer">
					推荐码 : <input type="text" id="recommendCode" name="recommendCode" maxlength="4" >
				</div>
				<div id="referrerMsg" style="color: red;"></div>
				<label for="checked">
					<input id="cbAgreement" type="checkbox" id="checked">
					我已阅读并同意 《惠给力代理商协议》
				</label>
				<div class="pay">
					需支付 : <span class="font-bold text-red">￥${merchants.coupons}</span>
				</div>
				
				<div class="succeed2 text-center">
					<ul class="clear">
						<li class="active"><a href="javascript:;"><img src="${ctx}/images/alipay.png" height="65" width="180" alt=""></a></li>
						<li><a href="javascript:;"><img src="${ctx}/images/wechat.png" height="65" width="180" alt=""></a></li>
						<li><a href="javascript:;"><img src="${ctx}/images/line.png" height="65" width="180" alt=""></a></li>
					</ul>
				</div>
				<div class=" text-center">
					<button type="button" onclick="agency();" class="btn">进入支付</button>
				</div>
			</div>
			</form>
			</c:if>
			<div class="attract-nav">
				<ul class="clear">
					<li class="active">招商政策</li>
					<li>招商规则</li>
					<li>经销商感言</li>
				</ul>
				<div class="slide">
					<div class="show-bg one">
						<div class="abso">
							一个好汉三个帮，成功的公司通常因为有卓越的伙伴，惠给力的健康成长，离不开分销商伙伴的支持和帮助。无论您是创业初期的小微贸易公司，还是特定领域的行业翘楚，惠给力诚邀您与我们合作共赢，共创美化的未来。
						</div>
						<ul class="slide-list clear">
							<li>
								<h3 class="text-red text-center">价格优势</h3>
								<p>惠给力充分理解分销商伙伴面临的市场挑战，我们愿意与您分享基于集中采购机制而获得的优惠价格，惠给力将提供给您远低于普通终端客户的产品价格，以协助您提高市场竞争力。</p>
							</li>
							<li>
								<h3 class="text-red text-center margin-bottom">海量渠道</h3>
								<p>与数百家国际国内知名品牌建立了良好的合作关系，众多厂商的资源全部在惠给力上体现，构建了核心竞争优势。选择惠给力，您将不必再为寻找产品采购渠道而烦恼。</p>
							</li>
							<li>
								<h3 class="text-red text-center margin-bottom">技术支持</h3>
								<p>客户对服务的需求正逐渐超出对产品的需求，您是否正在为无法满足客户的技术咨询而苦恼？惠给力拥有自建的化学品实验室，更组建了由公司资深员工及社会行业精英组成的专家团队，竭诚为您提供在线、电话及上门的技术支持服务，解决您的后顾之忧。</p>
							</li>
						</ul>
					</div>
					
					<div class="show-bg two none">
						<div class="abso">
							一定的进货量，您将获得您区域内的独家经营权，充分保证了经营区域的品牌效应，后续的海量数据分析，实时满足您的经营，后续的供货保障，让您无后顾之忧。
						</div>
						<ul class="slide-list clear">
							<li>
								<h3 class="text-red text-center">招商限制</h3>
								<p>惠给力为单区域内的品牌独立建立保护，在该区域的品牌已经获得招商后其他的经销商将不能再获得经营权。</p>
							</li>
							<li>
								<h3 class="text-red text-center margin-bottom">招商渠道</h3>
								<p>惠给力平台会在已经建立市场的区域发布招商，经销商在获得平台认证以后将可以参与招商，在招商的时候需要完成规定的购货量。</p>
							</li>
							<li>
								<h3 class="text-red text-center margin-bottom">招商事项</h3>
								<p>在开始注册时，您需要提供相关的证件证明您的经营权，营业执照，法人身份证，惠给力也会需要实地考察您的店面时候满足招商要求</p>
							</li>
						</ul>
					</div>
					<div class="show-bg three none">
						<div class="abso">
							惠给力有众多优秀的经销商，他们为惠给力的成长带来了众多的机遇和帮助。惠给力有众多优秀的经销商，他们为惠给力的成长带来了众多的机遇和帮助。
						</div>
						<ul class="slide-list clear">
							<li>
								<h3 class="text-red text-center">肖伟</h3>
								<img src="${ctx}/images/geren.jpg" height="150" width="200">
								<p>和惠给力合作了将近10年，全部正品和贴心服务，是我选择震坤行的理由。</p>
							</li>
							<li>
								<h3 class="text-red text-center margin-bottom">招商渠道</h3>
								<p>惠给力平台会在已经建立市场的区域发布招商，经销商在获得平台认证以后将可以参与招商，在招商的时候需要完成规定的购货量。</p>
							</li>
							<li>
								<h3 class="text-red text-center margin-bottom">招商事项</h3>
								<p>在开始注册时，您需要提供相关的证件证明您的经营权，营业执照，法人身份证，惠给力也会需要实地考察您的店面时候满足招商要求</p>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>


<!-- 确定进货单开始 -->
<div class="myModal">
	<div class="myModal-bg"></div>
	<div class="succeed">
		<h3>提示</h3>
		<p><i class="iconfont">&#xe620;</i>恭喜，该商品成功加入购物车!</p>
	</div>		
</div>
<!-- 确定进货单结束 -->
</body>
<script>
	
	$(function() {
		var timer= null;
		$('.name').hover(function(){
			$(this).find('.name-list').stop().slideDown(100);
			clearTimeout(timer)
		},function(){
			timer = setTimeout(nameHide,500);
		})
		
		$('.succeed2 .clear li').click(function(event) {
			$(this).addClass('active').siblings('li').removeClass('active')
		});
		
		function nameHide(){
			$('.name-list').stop().slideUp(300)
		}
		
		
		
	});
</script>
	</tiles:put>
</tiles:insert>