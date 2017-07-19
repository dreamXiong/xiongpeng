<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="cart-body">
<head>
	<meta charset="UTF-8">
	<c:set value="个人中心" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/my.css"/>  
	<%@include file="../common/common.jsp"%> 
	<script type="text/javascript">
		$("document").ready(function(){		
			//上传图像
			$("#image").change(function(){
				var objUrl = getObjectURL(this.files[0]);
				console.log(objUrl);
				var image = document.getElementById("image");
				var imageSize = image.files[0].size;
				if(imageSize>5*1024*1024){
					alert("上传图片大小不能超过5M");
					return false;
				}
				
				$("#showImage").attr("src",objUrl);
				$("#picForm").submit();				
			});
					
		});	
	
		function getObjectURL(file){
			var url = null;	
			if (window.createObjectURL!=undefined){ // basic 
		        url = window.createObjectURL(file) ; 
		    } else if (window.URL!=undefined) { // mozilla(firefox) 
		        url = window.URL.createObjectURL(file) ; 
		    } else if (window.webkitURL!=undefined) { // webkit or chrome 
		        url = window.webkitURL.createObjectURL(file) ; 
		    } 
		    return url ; 
		}
	</script>
</head>
<body>
<%@include file="../common/header.jsp"%>
<div class="container">
	<div class="personal box-shadow">
		<div class="user box">
			<div class="user-img text-right">				
				<form action="${ctx}/personalCenter/doUploadHeaderImage" method="post" enctype="multipart/form-data" id="picForm" name="picForm">				
					<img src="${ctx}/personalCenter/showHeaderImage" alt="" onclick="image.click()" id="showImage" style="width:68px;height:68px;">
					<input type="file" name="image" id="image" accept="image/*" class="upload-input" style="display:none;"/>
				</form>		
			</div>	
			<div class="user-info box1" style="margin-top:25px;">
					     
				<div style="margin-bottom:10px;position:relative;line-height:25px;"> 
					<a href="javascript:;" style="color:#666;">昵称:${session_key.name} 
						<span style="border:1px solid #999;border-radius:20px;color:#999;padding:3px 5px;margin-left:5px;">
							<c:if test="${session_key.typeId==114}">
								&nbsp;师傅&nbsp;
							</c:if>
							<c:if test="${session_key.typeId==104}">
								&nbsp;经销商&nbsp;
							</c:if>
							<c:if test="${session_key.typeId==106}">
								&nbsp;个人&nbsp;
							</c:if>
						</span>
						<!-- <span style="border:1px solid #fff;border-radius:20px;color:#fff;padding:3px 5px;margin-left:5px;">师傅</span> -->
					</a>
					       
				</div>	
				<div style="position:relative;">          
				<!--  <a href="${ctx}/personalCenter/doInitUpdateWebUser" style="color:#fff;">手机号:${session_key.mobile }</a>-->
					<!-- <span style="border:1px solid #fff;border-radius:20px;color:#fff;padding:3px 5px;">个人</span> -->
					<!-- <span style="border:1px solid #fff;border-radius:20px;color:#fff;padding:3px 5px;">师傅</span> -->
				
					<div class="bound">  
						<%-- <c:if test="${bindingWeixinFlag == true}">
							<button type="button" onclick="location.href = '${ctx}/personalCenter/bindingWeixinUser'">绑定帐号</button>
						</c:if> --%>
						<button type="button" onclick="window.location.href='${ctx}/personalCenter/doInitUpdateWebUser'">修改资料</button>	 
					</div>	
				</div>      
			</div>
			
		</div>           
	</div>    
	<div class="mylist">
		<ul class="box-shadow">   
			<li>            
				<a href="${ctx }/wapOrderService/myService">   
					<img src="${ctx }/images/icon-1.png" alt="">
					<span>服务订单</span> 
					<i class="right"></i>
				</a>    
				<c:if test="${unCompleteCnt>0}">
					<span class="float-icon">${unCompleteCnt}</span> 
				</c:if>                     
			</li>
			<li>
				<a href="${ctx }/shoppingCar/index">
					<img src="${ctx }/images/icon-2.png" alt="">
					<span>购物车</span>
					<i class="right"></i>
				</a>
			</li>
			<li>
				<a href="${ctx }/myWallet/integral">
					<img src="${ctx }/images/icon-20.png" alt="">
					<span>我的积分</span>
					<i class="right"></i>
				</a>
			</li>        
			<li>
				<a href="${ctx }/myWallet/index">
					<img src="${ctx }/images/icon-21.png" alt="">
					<span>我的钱包</span>
					<i class="right"></i>
				</a>
			</li>
			<li>
				<a href="javascript:;">
					<img src="${ctx }/images/icon-3.png" alt="">
					<span>优惠券</span>
					<i class="right"></i>
				</a>
			</li>
			<li>
				<a href="${ctx }/myAddress/index">
					<img src="${ctx }/images/icon-4.png" alt="">
					<span>我的地址</span>
					<i class="right"></i>
				</a>
			</li>
			<li>
				<a href="${ctx}/personalCenter/myRecommended">
					<img src="${ctx }/images/icon-5.png" alt="">
					<span>邀请分享</span>
					<i class="right"></i>
				</a>
			</li>
		</ul>
		<ul class="box-shadow">
			<li>
				<a href="${ctx }/wapOrderGroup/selectOrderGroup">
					<img src="${ctx }/images/icon-6.png" alt="">
					<span>材料订单</span>
					<i class="right"></i>
				</a>
				<c:if test="${unCompleteOrderCnt>0}">
					<span class="float-icon">${unCompleteOrderCnt}</span>
				</c:if>				
			</li>
			<c:if test="${session_key.typeId == 114 || session_key.typeId == 104}">
				<li>
					<a href="${ctx}/serviceType/doSearchMySkill">
						<img src="${ctx}/images/icon-10.png" alt="">
						<span>我的技能</span>
						<i class="right"></i>
					</a>
				</li>
			</c:if>
			<li>
				<a href="${ctx}/saveInfo/doSearchResult">
					<img src="${ctx}/images/icon-7.png" alt="">
					<span>我的收藏</span>
					<i class="right"></i>
				</a>
			</li>
			<li>
				<a href="${ctx}/feedBack/doInitFeedBack">
					<img src="${ctx }/images/icon-8.png" alt="">
					<span>意见反馈</span>
					<i class="right"></i>
				</a>
			</li>
			<li>
				<a href="${ctx}/letter/userLetterList">
					<img src="${ctx }/images/icon-9.png" alt="">
					<span>我的消息</span>
					<i class="right"></i>
				</a>
				<c:if test="${letterCount !=0 }">
				<span class="float-icon">
				
				${letterCount}</span>	   
				</c:if>
				
				 
			</li>
		</ul>
		<button type="button" class="quit box-shadow" onclick="location.href='../personalCenter/logout'" style="margin-bottom:0;">退出登录</button>
	</div>
</div>
<footer class="footer">
	<ul class="clearfix nav-bar text-center">
		<li><span class="nav-bar-a">
			<%-- <c:if test="${session_key.typeId == 114}">
				<a href="${ctx}/indexShopInfo">
			</c:if>
			
			<c:if test="${session_key.typeId != 114}">
				<a href="${ctx}/customerIndex/index">
			</c:if> --%>
			<a href="${ctx}/shop/userShop">
			<img src="${ctx}/images/index.png"></a></span></li>
			
		<li>
			<span class="nav-bar-b">
				<c:if test="${session_key.typeId == 114 || session_key.typeId == 104}">
					<a href="${ctx}/wapOrderService/service"><img src="${ctx}/images/order.png"></a>
				</c:if>
				
				<c:if test="${session_key.typeId != 114 && session_key.typeId != 104}">
					<a href="${ctx}/master/index"><img src="${ctx}/images/master.png"></a>
				</c:if>
			</span>
		</li>
		<li>
			<span class="nav-bar-c"><a href="${ctx}/pick/pickList"><img src="${ctx}/images/pick.png"></a></span>
		</li>
		<li>
			<span class="nav-bar-d">
				<a href="${ctx}/personalCenter/index"><img src="${ctx}/images/mine-red.png"></a>
				<c:if test="${letterCount+unCompleteCnt+unCompleteOrderCnt !=0}">
				<span class="float-icon">
					${letterCount+unCompleteCnt+unCompleteOrderCnt}
				</span>	  
				</c:if>      
			</span>
			
		</li>
	</ul>
</footer>
</body>
</html>