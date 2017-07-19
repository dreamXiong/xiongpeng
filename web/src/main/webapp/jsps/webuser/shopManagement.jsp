<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
	<tiles:put name="title" value="店铺管理" />
	<tiles:put name="body" type="String">
<head>
	<meta charset="UTF-8">
	<title>店铺管理</title>
	<link rel="stylesheet" href="${ctx}/css/me.css">
	<script src="${ctx}/js/jquery/jquery-ui.min.js"></script>
	<script src="${ctx}/js/hgl/pick.js"></script>
	<script src="${ctx}/js/hgl/datepicker-zh.js"></script>
	<script src="${ctx}/js/hgl/shop.js"></script>
	<style>
		.pay h3{
			font-size:12px;
		}
		.pay>label{ 
			padding-left:40px;
			display:block;
			margin-bottom:5px;
		}
		.pay label input[type="radio"]{
			margin-right:5px!important;
		}
		.pay .hidden{
			display:none;
		}	
	</style>
</head>
<body>
<c:set value="shopManagement" var="modalName"></c:set>
<!-- 内容板块开始 -->
	<div class="" style="border-top:none;">
		<div class="area me">
			
			<div class="main-right pull-right">
				<div class="store" id="shopmm">
					<div class="store-list store-list-one">
						<h3 class="bg-gray">店铺详情</h3>
						<div style="position:relative">
							<table>
								<tr><th>店铺名称：</th><td>${shopInfo.shopName}</td></tr>
								<tr><th>手机号：</th><td>${shopInfo.contractPhone}</td></tr>
								<tr><th>管理帐号：</th><td>${shopInfo.userName}</td></tr>
								<tr>
									<th>店铺图片：</th>
									<td>
							             <c:if test="${not empty shopInfo.shopImage1}">
							             <div title="点击更换头像">
							             	<form action="${ctx}/shop/updateShopImage" id="updateImage" method="post" enctype="multipart/form-data">
						             			<input id="input" type="file"  name="image"  accept="image/*" class="upload-input" >
						             			
												<div id="div" class="showDiv">
								                   	 <img id="img2w" src='generateImage?id=${shopInfo.id}&imgName=${shopInfo.shopImage1}' style="height: 100px;width: 100px;">
								                 </div>
							                 </form>
							              </div>   
							              </c:if>
							            <c:if test="${not empty shopInfo.shopImage2}">
							            	<div>
							                     <img id="dImgOneShowUp" src='generateImage?id=${shopInfo.id}&imgName=${shopInfo.shopImage2}' style="height: 100px;width: 100px;">
							                 </div>
							            </c:if>
					                    <c:if test="${not empty shopInfo.shopImage3}">
					                     <div>
					                      <img id="dImgOneShowUp" src='generateImage?id=${shopInfo.id}&imgName=${shopInfo.shopImage3}' style="height: 100px;width: 100px;">
					                	 </div>
					                  </c:if>
										<!-- 店铺图片最多三个。最少一张 -->
									</td>
								</tr>
							</table>
							<div style="position:absolute;right:50px;top:10px;">
								<h3>店铺二维码</h3>	
								<img  src='shopCode?id=${shopInfo.id}' style="height: 120px;width: 120px;">
							</div>
						</div>
						
					</div>
					<div class="store-list store-list-two">
						<h3 class="bg-gray">店铺设置</h3>
						<div>
							<table>
								<tr>
									<th>是否开启自动确定订单：</th>
									<td>
										<c:if test="${shopInfo.isAutomaticOrder == 0}">
										<label for="on" style="margin-right:10px;">
											<input type="radio" name="on" id="on" value="1" onclick="doAutomaticOder(${shopInfo.id})"> 开启
										</label>
										<label for="off">
											<input type="radio" name="on" id="off" value="0" checked="checked" > 关闭
										</label>
										</c:if>
										<c:if test="${shopInfo.isAutomaticOrder == 1}">
										<label for="on" style="margin-right:10px;">
											<input type="radio" name="on" id="on" value="1" checked="checked" > 开启
										</label>
										<label for="off">
											<input type="radio" name="on" id="off" value="0" onclick="doAutomaticOder(${shopInfo.id})"> 关闭
										</label>
										</c:if>
									</td>
								</tr>
								<tr>
									<th>师傅返利设置：</th>
									<td>
										<span>${shopInfo.rebate}%</span>
										<button type="button" class="btn-bg" id="revamp" onclick="updateEarnings(${shopInfo.id},${shopInfo.rebate})">
											<span>修改</span>
										</button>	
									</td>
								</tr>
							</table>
							<p style="color:#aaa;">
								<b>注:</b> "师傅返利设置"仅仅对师傅购买本店货品有效，且师傅的每次购买都将会返利。
							</p>
							<p style="color:#aaa;">
								<b>例:</b> 设置返利比例为8%，则师傅购买了100元货品，师傅可获得8元返利，您将收到92元货款.
							</p>
						</div>
					</div>
					<div class="store-list store-list-three award" >
						<h3 class="bg-gray">积分奖励设置</h3>
						<div>
							<ul class="clearfix bg-gray">
								<li class="active">按百分比</li>
								<li>满额送</li>	    
							</ul>	
							<table style="display:block;">
								<c:forEach var="item" items="${tbIntegralRules}" varStatus="s">	
									<c:if test="${item.type == 0 }">
									<tr>        
										<td>按百分比</td>
										<td>订单支付金额</td>
										<td>${item.money }</td>          
										<td> 
											<c:if test="${s.index < 2 }">
												<label>
													<input onChange="selectUseSituation('${item.type}','${item.id }');" id="useSituation_${item.id}"  name="useSituation" type="radio" value="${item.useSituation == 1 ? 0 : 1}"
														<c:if test='${item.useSituation == 0}'>
															checked='checked'
														</c:if>
													/>
												</label> 
											</c:if>
										</td>
										  
										<td class='useSituation_${item.type}'>
											${item.useSituation == 0 ? "启用" : "禁用"}
										</td>
										
										<td>
											<input type="hidden" id="payMoney_${item.id }" value="${item.payMoney }" />
											<input type="hidden" id="money_${item.id }" value="${item.money }" />
											<button type="button" class="btn-bg" id="" onclick="showIntegralog('${item.id}','${item.type}')"><span>修改</span></button>	
											
										</td>
									</tr>
									</c:if>
								</c:forEach>
							</table>
							
							<table>                         
									<c:forEach var="item" items="${tbIntegralRules}" varStatus="s">	
									<c:if test="${item.type == 1 }">
									<tr>        
										<td>满额送</td>
										<td>满&nbsp;${item.payMoney}</td>
										<td>送&nbsp;${item.money}
										</td>          
										<td> 
											<c:if test="${s.index < 2 }">
												<label>
													<input onChange="selectUseSituation('${item.type}','${item.id }');" id="useSituation_${item.id}"  name="useSituation" type="radio" value="${item.useSituation == 1 ? 0 : 1}"
														<c:if test='${item.useSituation == 0}'>
															checked='checked'
														</c:if>
													/>
												</label> 
											</c:if>
										</td>
										<td class='useSituation_${item.type}'>${item.useSituation == 0 ? "启用" : "禁用"}</td>
										<td>
											<input type="hidden" id="payMoney_${item.id }" value="${item.payMoney }" />
											<input type="hidden" id="money_${item.id }" value="${item.money }" />
											<button type="button" class="btn-bg" id="" onclick="showIntegralog('${item.id}','${item.type}')"><span>修改</span></button>	
											
										</td>
									</tr>
									</c:if>
								</c:forEach>
							</table>  
							
							<p style="margin-top: 10px;color:#aaa;">
								<b>注:</b> "积分奖励设置"在有客户购买本店货品时，将获得相应的奖励.
							</p>
							<p style="color:#aaa;">
								<b>例:</b> 百分比设置：设置为10时，则用户购买150元货品会得到15积分。
							</p>
							<p style="color:#aaa;">
								<b>例:</b> 满额设置：满200送20，满400送50等（所有积分将在店铺的积分账户中扣除）。
							</p>
						</div>
					</div>
					
					
					
					<div class="store-list store-list-three">
						<h3 class="bg-gray">店铺返利设置</h3>
						<div>
							<table>
								<c:forEach var="item" items="${recommendedRules}" varStatus="s">	
								<tr>
									<td>
										<c:if test="${item.recommendedType == 114 }">
										师傅
										</c:if>
										<c:if test="${item.recommendedType == 106 }">
										个人
										</c:if>
										推荐
										<c:if test="${item.isRecommendedType == 114 }">
										师傅
										</c:if>
										<c:if test="${item.isRecommendedType == 106 }">
										个人
										</c:if>
									</td>
									<td>
										<c:if test="${item.way == 0 }">
										直接给予
										</c:if>
										
									</td>
									<td>
										<c:if test="${item.rewardType == 0 }" var="isjifen">
										积分
										</c:if>
										<c:if test="${item.rewardType == 1 }" var="isjine">
										金额
										</c:if>
									</td>
									<td>
										<c:if test="${item.rewardWay == 0 }">
										百分比
										</c:if>
										<c:if test="${item.rewardWay == 1 }">
										固定
										</c:if>
									</td>
									<td>
										
										${item.reward}
										<c:if test="${item.rewardWay == 0 }">
										%
										</c:if>
										<c:if test="${item.rewardWay == 1&& isjine}">
										元
										</c:if>
										<c:if test="${item.rewardWay == 1&& isjifen}">
										积分
										</c:if>
									</td>
									<td>
										
										<button type="button" class="btn-bg" id="" onclick="showRules(${item.id})">
											<span>修改</span>
										</button>	
									</td>
								</tr>
								</c:forEach>
								
							</table>       
							     
							<p style="margin-top: 10px;color:#aaa;">
								<b>注:</b> "返利设置"在有师傅或者客户介绍其他客户注册来购买本店货品时，将获得相应的奖励.
							</p>
							<p style="color:#aaa;">
								<b>例:</b> 设置师傅推荐个人为直接给予金额为百分比10，在个人购买商品时，师傅将获得10元。
							</p>
							<p style="color:#aaa;">
								<b>例:</b> 设置师傅推荐个人为直接给予金额为百分比10%，在个人购买商品是100元，师傅将获得10元，店铺收入为90元。
							</p>
						</div>
					</div>
					
					
					
					<div class="store-list store-list-three">
						<h3 class="bg-gray">店铺权限</h3>
						<div>
							<table>
							
								<c:if test="${empty shopInfo.settlement && shopInfo.balance == 0}">
									<tr>
										<th>上架货品平台结算权限：</th>
										<td>
											<span style="color:green;">未开通</span>
											<button type="button" onclick="getSettlementShow('1','${shopInfo.id}','${shopInfo.version}');" class="btn-bg">
												<span>获取</span>
											</button>
										</td>
									</tr>
								</c:if>
								
									<c:if test="${(empty shopInfo.settlement) && shopInfo.balance != 0}">
										<c:if test="${shopInfo.balance == 1}">
											<tr>
												<th>上架货品平台结算权限：</th>
												<td>
													<span style="color:green;">审核中...</span>
												</td>
											</tr>
										</c:if>
									</c:if>
									<c:if test="${shopInfo.settlement == 1}">
										<tr>
											<th>上架货品平台结算权限：</th>
											<td>
												<span style="color:green;">已开通</span>
											</td>
										</tr>
									</c:if>
								<tr>
									<th>金牌代理商权限：</th>
									<td>
										<c:if test="${shopInfo.medalAgentFlag !=1 and shopInfo.medalAgentFlag !=2}">
											<span style="color:green;">未开通</span>
											<button type="button" onclick="getMedalAgentShow('1','${shopInfo.id}','${shopInfo.version}');" class="btn-bg">
												<span>获取</span>
											</button>
										</c:if>
										<c:if test="${shopInfo.medalAgentFlag ==1}">
											<span style="color:green;">审核中...</span>
										</c:if>
										<c:if test="${shopInfo.medalAgentFlag ==2}">
											<span style="color:green;">已开通</span>
										</c:if>
									</td>
								</tr>
								<tr>
									<th>微商城权限：</th>
									<td>
										<span style="color:red;">敬请期待</span><!-- <button type="button" class="btn-bg"><span>获取</span></button> -->
									</td>
								</tr>
								<tr>
									<th>排名靠前：</th>
									<td>
										<span style="color:red;">敬请期待</span><!-- <button type="button" class="btn-bg"><span>获取</span></button> -->
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<!-- 内容板块结束 -->
<div id="dialog" class="dialog">
	  <form>
	  	<span>确定更改自动确认订单设置么?</span>
	    <input type="hidden" id="id" name="id">
	  </form>
	</div>
	
	<div id="selectUseSituation" class="dialog">
	  	<span>确定启用该设置么?</span>
	</div>
	
<div id="dialog2" class="dialog">
	  <form>
	    <input type="hidden" id="bid" name="bid">
	     <label for="name">师傅返利:</label>
	    <input type="" id="rebate" name="rebate">
	    <span>%</span>
	    <div>
	    <span id="msg" style="color: red;"></span>
	    </div>
	  </form>
	</div>
<!-- 修改规则 -->
	<div id="myrules" class="dialog">
	 <%@include file="myRecommedRules.jsp" %>
	</div>
	
	<div id="myIntegralrules" class="dialog">
		 <form id="saveIntegralrules"  action="saveIntegralrules" class="form-horizontal" method="post">
	  	 <div class="group">
	  		<span style="margin-left: 150px;">
	  		</span>
	  	 </div>
	  	 <input id="integralId" type="hidden" name="integralId" value=""/>
	  	 <div class="group">
	  		<label>满</label>
	  		<input type="text" id="savePayMoney" class="number" maxlength="5" title="金额" placeholder="请输入整数" name="payMoney"/><!-- <span>%</span> -->
	  		<input type="hidden" id="key_info_payMoneyreward"/>
	  		<input type="hidden" id="key_info_moneyreward"/>
	  	 </div>
	  	 <div class="group">
	  		<label>送</label>
	  		<input type="text" id="saveMoney" class="number" maxlength="4" placeholder="请输入整数" title="金额" name="money"/>
	  	 </div>
	  	 <span>例：满100元送20积分。</span>
	  </form>
	</div>
	
	
	<div id="myIntegralrulesBFB" class="dialog">
		 <form id="saveIntegralrulesBFB"  action="saveIntegralrules" class="form-horizontal" method="post">
	  	 <div class="group">
	  		<span style="margin-left: 150px;">
	  		</span>
	  	 </div>
	  	 <input id="integralIdBFB" type="hidden" name="integralId" value=""/>
	  	 <input id="payMoneyBFB" type="hidden" name="payMoney" value="0"/>
	  	 <div class="group">
	  		<label>百分比</label>
	  		<input type="text" id="saveMoneyBFB" class="number" maxlength="4" placeholder="请输入整数" title="金额" name="money"/>%
	  		<input type="hidden" id="key_info_moneysaveMoneyBFB"/>
	  	 </div>
	  	 <span>例：设置为3，则买100元送3%的积分。</span>
	  </form>
	</div>
	
	
	<div id="dialog5" class="dialog pay">
		<input type="hidden" id="payMoneyInput" value="${configValue}" />
		<span style="color:red;">您将要支付：<span id="payMoneySpan">${configValue}</span></span>
			<h3>请选择支付方式：</h3>
			<label>
				<input type="radio" name="payFun" value="0" checked style="width:auto;margin:0;padding:0;">微信支付
			</label>		
			<label>
				<input type="radio" name="payFun" value="1" style="width:auto;margin:0;padding:0;">账户支付
			</label>
			<label>
				<input type="radio" name="payFun" value="2" style="width:auto;margin:0;padding:0;">线下支付
			</label>
		    <div class="hidden">
		    	 <h3>汇款账户</h3>
			    <img src="${ctx }/images/accountBank.png" />
			    <label>
			    	账户名称：<input maxlength="10" name="remarkInfo" id="remarkInfo" type="text" style="margin-left:5px;width:215px;" value="${shopInfo.shopName}" />
			    </label>
		    </div>	
	</div>
		<div id="dialog4" class="dialog add-pick">
				 服务中申成功！！
		</div>
	 <input type="hidden" id="settlementShow" name="id">
	 <input type="hidden" id="shopIdShow" name="id">
	 <input type="hidden" id="versionShow" name="id">
	 <input type="hidden" id="openType" name="openType">
</body>

 <input type="hidden" id="selectUseSituationId" name="id">
 <input type="hidden" id="selectUseSituationType" name="id">

<script>
	EcWeb.currentModalName = '${modalName}';
	function selectUseSituation(type,id){
	    $("#selectUseSituationId").val(id);
	    $("#selectUseSituationType").val(type);
	    $("#selectUseSituation").dialog('open');  
	}           
	function showIntegralog(id,type){
		if(type == 0){
			$("#integralIdBFB").val(id);
			$("#saveMoneyBFB").val($("#money_"+id).val());
			$("#myIntegralrulesBFB").dialog('open');  
		}
		if(type == 1){
			$("#integralId").val(id);
			$("#savePayMoney").val($("#payMoney_"+id).val());
			$("#saveMoney").val($("#money_"+id).val());
			$("#myIntegralrules").dialog('open');  
		}
	}     
	$(function(){
		$('.award li').click(function(){
			var index = $(this).index();
			$(this).addClass('active').siblings('li').removeClass('active');
			$('.award table').eq(index).show().siblings('table').hide();
		});
	});

</script>
</html>
	</tiles:put>
</tiles:insert>