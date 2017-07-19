<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
	<tiles:put name="title" value="我的推荐" />
	<tiles:put name="body" type="String">
	<c:set value="takl" var="modalName"></c:set>	
	<title>提现申请成功</title>
	<link rel="stylesheet" href="${ctx}/css/me.css">
	<script src="${ctx}/js/hgl/datepicker-zh.js"></script>
	<script src="${ctx}/js/hgl/account.js"></script>
<script src="${ctx}/Font-Awesome/src/assets/js/ZeroClipboard-1.1.7.min.js"></script>
<body>
<div class="container" >              
	<h3 class="text-center" style="padding-top:20px;margin-bottom:15px;">        
		<span class="icon-ok-sign" style="color:#11CD6D;font-size:30px;display:inline-block;vertical-align: middle;"></span>      
		恭喜你提现申请成功！！
	</h3>
	<div class="text-center">
		<a href="${ctx}/cashAccount/accountWithdraw" style="border:1px solid #aaa;border-radius:5px;padding:5px 8px;">回到提现</a>	
	</div>	    
</div>	      
</body>
<script>

</script>
</html>
</tiles:put>
</tiles:insert>