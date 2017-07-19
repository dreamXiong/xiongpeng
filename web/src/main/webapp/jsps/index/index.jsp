<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
<tiles:put name="title" value="首页" />
<tiles:put name="body" type="String">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>惠给利首页</title>
<script type="text/javascript" src="${ctx}/js/hgl/banner.js"></script>
<script type="text/javascript">
	$("document").ready(function(){	
		//显示系统公告
		$.ajax({
			type:"post",
			url:"queryNewestNoticeInfo",
			data:"",
			success:function(data) {
			    var jsonData = eval(data);
				var html = "";
				for(var i = 0;i < jsonData.length;i++) {
					 var noticeType = "";
					 if (jsonData[i].noticeType == 1) {
					 	noticeType = "升级";
					 } else if (jsonData[i].noticeType == 2) {
						noticeType = "公告";
					 }
					 var path = "${ctx}/noticeInfo/getNoticeInfoDetail?id="
								+ jsonData[i].id;
					 var displayTxt = "【" + noticeType+ "】"+ jsonData[i].noticeName;
					 if (displayTxt.length > 18) {
					 	displayTxt = displayTxt.substring(0, 18)+ "...";
					 }
					 listId = "listInfo"+jsonData[i].id;
					 
					 
					 html += "<p class='noticeListInfo' title='"+jsonData[i].noticeName+"'><a href='"+path+"'>"+ displayTxt + "</a></p>";						 
				}
				html += "<p>&nbsp;&nbsp;<a href='${ctx}/noticeInfo/queryNoticeInfoList'>更多...</a></p>";
				$("#noticeInfo").append(html);
			 }
		});
		/* $('.investment li').eq(3).addClass('no-right');  */
			
			
			
		//显示招商公告
		$.ajax({
			type:"post",
			url:"${ctx}/merchnotice/doSearchResultShow",
			data:"",
			success:function(data){
				var jsonData = eval(data);
				var html = "";
				for(var i=0;i<jsonData.length;i++){
					var typeId="";
					if(jsonData[i].typeId ==290){
						typeId="招商公告";
					}else if(jsonData[i].typeId==292){
						typeId="中标公告";
					}
					var path = "${ctx}/merchnotice/doSearchMerchNoticeDetail?id="+jsonData[i].id;
					var displayTxt = "【"+typeId+"】"+jsonData[i].name;
					html+="<p class='merchNoticeInfo' title='"+jsonData[i].name+"'><a href='"+path+"'>"+displayTxt+"</a></p>";				
				}
				html += "<p>&nbsp;&nbsp;<a href='${ctx}/merchnotice/queryMerchNotices'>更多...</a></p>";
				$("#merchNotice").html(html);
			}
		});
			
	});
</script>
</head>
<body>
	
	<!-- banner板块开始 -->
	<!-- <div class="cantainer">
		<div class="area"> -->
		
			<%-- <div class="margin-bottom clear">  
				<div class="cantainer-left pull-left" style="position: relative">
					<ul class="cantainer-warp-ui" id="productFirstTypeList">
						<c:forEach items="${productFstTypes}" var="productFstType">
							<li><a href="javascript:void(0)"><i class="iconfont">${productFstType.icon}</i>${productFstType.name}</a>
								<br>
								<div class="ui-drop-down">
									<c:forEach items="${productFstType.brands}" var="brand">
										<a href="${brand.url}"> 
											<img src='generateImage?id=${brand.id}'/>
										</a>
									</c:forEach>
								</div></li>
						</c:forEach>
					</ul>				
				</div>
					
				<div class="cantainer-center pull-left">					
					<ul class="banner">
						<li style="z-index:1">
							<img src="${ctx}/images/banner1.jpg  " alt="">
						</li>
						<li>
							<img src="${ctx}/images/banner-1.png" alt="">
						</li>
						<li>
							<img src="${ctx}/images/banner-2.png"  alt="">
						</li>
						<li>
							<img src="${ctx}/images/banner.png"  alt="">
						</li>
					</ul>
					
					<ol class="index">
						<li class="active"></li>
						<li></li>
						<li></li>
						<li></li>
					</ol>
					<a href="javascript:" class="banner-left"> <span
						class=" icon-angle-left"></span>
					</a> <a href="javascript:" class="banner-right"> <span
						class=" icon-angle-right"></span>
					</a>
				</div>
				<div class="cantainer-right pull-right">
					<div class="cantainer-in text-center clear">
						<div class="in pull-left in-one">
							<span></span> <i>正品保障</i>
						</div>
						<div class="in pull-left in-two">
							<span></span> <i>先付赔行</i>
						</div>
						<div class="in pull-left in-three">
							<span></span> <i>退换货无忧</i>
						</div>
					</div>
					<div class="border-gray">
						<ul class="clear">
							<li class="text-center pull-left active"><a
								href="javascript:void(0)">系统公告</a>
							</li>
							<li class="text-center pull-left">
								<a href="javascript:void(0)">招商公告</a>
							</li>
						</ul>

						<div class="notice">
							<div style="display: block" id="noticeInfo"></div>
							<div id="merchNotice"></div>

						</div>
					</div>
				</div>
			</div> --%>
			
			
			<!-- banner板块结束 -->
			<!-- 招商板块开始 -->
			
			
			<%-- <div class="margin-bottom">
				<h2 class="border-bottom h2-title margin-bottom">
					限时招商<small><a href="javascript:">招商规则</a></small>
				</h2>
				<div class="investment">
					<ul class="clear investment-list">
						<c:forEach items="${merchantsDtos}" var="merchantDto">
							<li>
								<div class="investment-logo  border-hover">
									<a href="merchants/merchantsDetails?id=${merchantDto.id}&bid=${merchantDto.brandid}" class="warp-logo">
										<img alt="" src='generateImage?id=${merchantDto.brandid}'>
									</a>
									<p class="padding-left font-bold line-height text-center">
										<span>${merchantDto.addressName}</span>
									</p>
									<a href="merchants/merchantsDetails?id=${merchantDto.id}&bid=${merchantDto.brandid}" class="warp-logo-1">
										<img src="merchants/merchantsImage?id=${merchantDto.id}" >
									</a>
									<div class="font-bold clear">
										<p class="pull-left">
											<span class="text-gray">招商金额 :</span><span
												class="text-red number"> ￥ ${merchantDto.number}</span>
										</p>
										<p class="pull-right padding-right">
											<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<a href="merchants/merchantsDetails?id=${merchantDto.id}&bid=${merchantDto.brandid}" class="text-white">参与</a>											
											</span>
										</p>
									</div>
								</div>
								<div class="col-10">
								<div class="clear  line-height-30">
									<p class="pull-left">
										已中标的商家 : <span class="text-red">${merchantDto.winning}</span>
									</p>
									<p class="pull-right">
										围观的商家 : <span class="text-red">${merchantDto.views}</span>
									</p>
								</div>
								<div class="clear line-height-30">
									<p class="pull-left">
										已参与的商家 : <span class="text-red">${merchantDto.participate}</span>
									</p>
									<p class="pull-right site ">
										招商剩余 : <span class="text-white font-bold text-center">${merchantDto.remainingPlaceNumber}</span>
									</p>
								</div>
							</div>
							</li>
							
						</c:forEach>	
						
					</ul>
				</div>
			</div> --%>
			
			
			<!-- 招商板块结束 -->
			<!-- 手动工具板块开始 -->
			
			
			<%-- <div class="margin-bottom">
				<h2 class="border-bottom h2-title margin-bottom">
					<span>1</span>F 手动工具
				</h2>
				<div class="tool">
					<ul class="clear">
						<li class="border-hover"><a href="javascript:"
							class="tool-img"> <img
								src="${ctx}/images/shoudong-banshou.png"
								alt="">
						</a>
							<p class="text-center font-bold  tool-name">工具钢制扳手</p>
							<p class="text-center tool-name">
								<span class="text-red font-bold">￥<span>55.00</span></span>
							</p>
							<p class="text-center">
								<a href="javascript:" class="text-gray">立即购买</a>
							</p></li>
						<li class="border-hover"><a href="javascript:"
							class="tool-img"> <img
								src="${ctx}/images/shoudong-banshou-1.png"
								alt="">
						</a>
							<p class="text-center font-bold  tool-name">工具钢制扳手</p>
							<p class="text-center tool-name">
								<span class="text-red font-bold">￥<span>25.00</span></span>
							</p>
							<p class="text-center">
								<a href="javascript:" class="text-gray">立即购买</a>
							</p></li>
						<li class="border-hover"><a href="javascript:"
							class="tool-img"> <img
								src="${ctx}/images/shoudong-banshou-2.png"
								alt="">
						</a>
							<p class="text-center font-bold  tool-name">工具钢制扳手</p>
							<p class="text-center tool-name">
								<span class="text-red font-bold">￥<span>155.00</span></span>
							</p>
							<p class="text-center">
								<a href="javascript:" class="text-gray">立即购买</a>
							</p></li>
						<li class="no-right border-hover"><a href="javascript:"
							class="tool-img"> <img
								src="${ctx}/images/shoudong-banshou-1.png"
								alt="">
						</a>
							<p class="text-center font-bold  tool-name">工具钢制扳手</p>
							<p class="text-center tool-name">
								<span class="text-red font-bold">￥<span>55.00</span></span>
							</p>
							<p class="text-center">
								<a href="javascript:" class="text-gray">立即购买</a>
							</p></li>
					</ul>
				</div>
			</div> --%>
			
			
			<!-- 手动工具板块结束 -->
			<!-- 自动工具板块开始 -->
			
			
			<%-- <div class="margin-bottom">
				<h2 class="border-bottom h2-title margin-bottom">
					<span>2</span>F 电动工具
				</h2>
				<div class="tool">
					<ul class="clear">
						<li class="border-hover"><a href="javascript:"
							class="tool-img"> <img
								src="${ctx}/images/diandong-1.png"
								alt="">
						</a>
							<p class="text-center font-bold  tool-name">工具钢制扳手</p>
							<p class="text-center tool-name">
								<span class="text-red font-bold">￥<span>55.00</span></span>
							</p>
							<p class="text-center">
								<a href="javascript:" class="text-gray">立即购买</a>
							</p></li>
						<li class="border-hover"><a href="javascript:"
							class="tool-img"> <img
								src="${ctx}/images/diandong-2.png"
								alt="">
						</a>
							<p class="text-center font-bold  tool-name">工具钢制扳手</p>
							<p class="text-center tool-name">
								<span class="text-red font-bold">￥<span>55.00</span></span>
							</p>
							<p class="text-center">
								<a href="javascript:" class="text-gray">立即购买</a>
							</p></li>
						<li class="border-hover"><a href="javascript:"
							class="tool-img"> <img
								src="${ctx}/images/diandong-3.png"
								alt="">
						</a>
							<p class="text-center font-bold  tool-name">工具钢制扳手</p>
							<p class="text-center tool-name">
								<span class="text-red font-bold">￥<span>55.00</span></span>
							</p>
							<p class="text-center">
								<a href="javascript:" class="text-gray">立即购买</a>
							</p></li>
						<li class="no-right border-hover"><a href="javascript:"
							class="tool-img"> <img
								src="${ctx}/images/diandong-1.png"
								alt="">
						</a>
							<p class="text-center font-bold  tool-name">工具钢制扳手</p>
							<p class="text-center tool-name">
								<span class="text-red font-bold">￥<span>55.00</span></span>
							</p>
							<p class="text-center">
								<a href="javascript:" class="text-gray">立即购买</a>
							</p></li>
					</ul>
				</div>
			</div> --%>
			
			
		<!-- </div>
	</div> -->
	<!-- 自动工具板块结束 -->
	<!-- 热门商家板块开始 -->
	<%-- <div class="rot">
		<div class="area rot-border-top rot-list">
			<div class="move">
				<a href="javascript:"><img
					src="${ctx}/images/hot-1.png" alt=""></a>
				<a href="javascript:"><img
					src="${ctx}/images/hot-2.png" alt=""></a>
				<a href="javascript:"><img
					src="${ctx}/images/hot-3.png" alt=""></a>
				<a href="javascript:"><img
					src="${ctx}/images/hot-4.png" alt=""></a>
				<a href="javascript:"><img
					src="${ctx}/images/hot-5.png" alt=""></a>
				<a href="javascript:"><img
					src="${ctx}/images/hot-6.png" alt=""></a>
				<a href="javascript:"><img
					src="${ctx}/images/hot-7.png" alt=""></a>
				<a href="javascript:"><img
					src="${ctx}/images/hot-8.png" alt=""></a>
				<a href="javascript:"><img
					src="${ctx}/images/hot-9.png" alt=""></a>
				<a href="javascript:"><img
					src="${ctx}/images/hot-10.png" alt=""></a>
				<a href="javascript:"><img
					src="${ctx}/images/hot-11.png" alt=""></a>
				<a href="javascript:"><img
					src="${ctx}/images/hot-12.png" alt=""></a>
				<a href="javascript:"><img
					src="${ctx}/images/hot-13.png" alt=""></a>
				<a href="javascript:"><img
					src="${ctx}/images/hot-14.png" alt=""></a>
				<a href="javascript:"><img
					src="${ctx}/images/hot-15.png" alt=""></a>
				<a href="javascript:"><img
					src="${ctx}/images/hot-16.png" alt=""></a>
			</div>


		</div>
		<div class="rot-title text-center font-bold">热门商家</div>
	</div> --%>
	<!-- 热门商家板块结束 -->
</body>
</html>
</tiles:put>
</tiles:insert>