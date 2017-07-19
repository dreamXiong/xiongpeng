<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="cart-body">
<head>
	<meta charset="UTF-8">
	<c:set value="意见反馈" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/my.css"/>  
	<%@include file="../common/common.jsp"%> 
	<script type="text/javascript">
	$("document").ready(function(){
		$("#submit").click(function(){
			var feedBack =$("#feedBack").val();
			if(feedBack==""){             
				$("#feedBackTips").text("请您输入需要反馈的信息");
				return false;
			}
			feedBackForm.action="doSendFeedBack";
		});
		
		$("#feedBack").focus(function(){
			$("#feedBackTips").text("");
		});
	});		        
	</script>
</head>	
<body>
	<%@include file="../common/header.jsp"%>
	
	<div class="container" style="padding-top:20px;margin-top:25px;">
		<form action="" method="post" name="feedBackForm" id="feedBackForm">
			<textarea name="feedBack" id="feedBack" rows="7" placeholder="请输入您的宝贵意见，我们会更加完善的...谢谢您" maxlength="500" style="margin-bottom:0;" >${userInfo.feedBack}</textarea>
			
			<div class="idea-info">		
				<span id="feedBackTips" style="font-size:12px;color:red;"></span>
				<p>您也可以拨打我们的客服热线 <a href="tel:4006-718-278" class="text-red font-bold" style="font-size:14px;"> 4006-718-278</a></p>
				<button type="submit" class="subimt" id="submit">提交建议</button>
			</div>
		</form>		
	</div> 		
</body>
<script>
	
</script>

</html>


