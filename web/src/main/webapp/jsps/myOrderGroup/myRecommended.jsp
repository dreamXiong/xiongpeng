<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
	<tiles:put name="title" value="我的推荐" />
	<tiles:put name="body" type="String">
	<c:set value="takl" var="modalName"></c:set>	

	
	<title>我的推荐</title>
	
	<link rel="stylesheet" href="${ctx}/css/me.css">
	<script src="${ctx}/js/hgl/datepicker-zh.js"></script>
	<script src="${ctx}/js/hgl/pick.js"></script>
<script src="${ctx}/Font-Awesome/src/assets/js/ZeroClipboard-1.1.7.min.js"></script>

<body>


<!-- 内容板块开始 -->
	<div class="" style="border-top:none;">
		<div class="area me">
			<div class="main-right pull-right">
				<div class="store">
					<div class="store-list invite-list-one">
						<h3 class="bg-gray">邀请好友得奖励</h3>
						<div>
							<p><b class="text-red">1.VIP抵用券：</b>使用邀请码注册的被邀请人，注册后立刻获得30元VIP抵用券。</p>
							<p><b class="text-red">2.现金奖励：</b>当被邀请人待收总额首次超过1万元(含)，邀请人在24小时内获得100元奖励。</p>
							<p><b class="text-red">3.积分奖励：</b>被邀请人待收总额首次超过1万元(含)，邀请人与被邀请人均可获得1000积分奖励。</p>
							<p><b class="text-red">4.提成奖励：</b>被邀请人注册后的36个自然月内，邀请人可得得到被邀请人每月月底账户待收的相应提成比例作为提成奖励。</p>
						</div>
					</div>
					<div class="store-list invite-list-two">
						<h3 class="bg-gray">邀请码邀请</h3>
						<div>
							
							<div class="code">
								推荐经销商：
								
								<input id="copylinkvlaue"  value="http://www.hgeili.com/register/jxregister?recommendCode=${code}" readonly="readonly"  type="text" 
									style="width: 500px; height: 30px;line-height:30px; border: 1px solid #ccc; padding-left: 10px; font-size: 14px;" />
								<button type="button" class="btn-bg" style="margin-top:3px;" id="copylinkspan">
									<span>复制</span>
								</button>
								<!-- <input type="button" id="copylinkspan" class="btn-bg" 
						value="复制" />
								 -->
							</div>
							<p>您可以让好友在注册页面填写邀请码或者扫描你的二维码，好友将获得39元VIP抵用券。我的二维码:${code}</p>
							
							
							<img alt="" data-original="logoCode?code=${code}" src="${ctx}/images/no-load.gif" class="lazy"
							style="width: 120px;height: 120px;">
							
						</div>
					</div>
					<div class="store-list invite-list-three clear">
						<h3 class="bg-gray">邀请列表</h3>
						<div>
							<table class="hover col-10">
								<thead>
									<tr>
										<th width="192">我的邀请的用户</th>
										<th width="168">角色</th>
										<th width="184">注册时间</th>
										<c:if test="${!isJxs}">
										<th width="186">首单金额</th>
										<th width="175">返利</th>
										</c:if>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${recommendedDtos}" varStatus="s" >
									
										<tr>
										<td>${item.userName}</td>
										<td>${item.userIdentity}</td>
										<td><liguo:dateFormatLabel
										value="${item.createDate}" pattern="yyyy-MM-dd  HH:mm:ss" /></td>
										<c:if test="${!isJxs}">
										<td>${item.firstMoney}元</td>
										<td>${item.unit}</td>
										</c:if>
									</tr>
									
									</c:forEach>
									
									<!-- 让tbody的2n个tr加odd的类名 -->
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<!-- 内容板块结束 -->







</body>
<script>
function copyUrl()
 {

 
	 var clipBoardContent=this.location.href;
	 window.clipboardData.setData("Text",clipBoardContent);
	alert("复制成功!");
	 
 }

$(function(){
	$('#copylinkspan').click(function(){
		var spanText = $('#copylinkvlaue').val();
		if(window.clipboardData){
			window.clipboardData.setData('text',spanText);
			alert('复制成功,你可以使用Ctrl+V 粘贴');
		}else{
			alert('此功能不支持该浏览器,请手工复制文本框中内容');
		}
	});
		
});


/* $(document).ready(function(){
	
    $('#copylinkspan').zclip({
        path : '/Font-Awesome/src/assets/js/ZeroClipboard-1.1.7.swf',
        copy : $('#copylinkvlaue').val(),
       
        afterCopy:function(){alert("复制成功");}//console.log("复制之后");
    });
   
}); */

	
</script>
</html>
</tiles:put>
</tiles:insert>