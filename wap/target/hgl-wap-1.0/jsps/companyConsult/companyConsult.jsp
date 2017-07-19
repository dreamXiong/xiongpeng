<%@page pageEncoding="UTF-8"%>
	<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<c:set value="信息咨询" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">

	<%@include file="../common/common.jsp"%>
	<style>

	.border-red:hover,.border-red:focus{
		border-color:rgb(102,175,233);
		box-shadow:inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6);
	}
	select{
		 appearance:none;
		-moz-appearance:none; 
		-webkit-appearance:none;
	}
	.form-group .icon-plus{
		position:absolute;
		line-height:40px;
		width:40px;
		text-align:center;
		top:1px;
		right:1px;
	}
	
	</style>
</head>	
<body>
<div>
<%@include file="../common/header.jsp"%>
</div>
<div class="container  eva">
	<div class="eva-info box-shadow">
		 
		 <form action="insertCompanyConsult" id="saveEvaluate" method="post">
			公司名称：<input name="companyName" value="${companyName}" type="text" readonly="readonly"></input>
					<input name="companyId" value="${id}" type="hidden"></input>
			联系人：<input id="contract" name="contract" type="text" maxlength="5"></input>
			联系电话：<input name="contractPhone" id="contractPhone"  maxlength="15" type="text"></input>
			咨询信息：<textarea name="describes" id="describes" rows="3" maxlength="100"></textarea>
		</form> 
		<div class="refer">
			<button type="button">提交</button>
		</div>
	</div>
</div>
</body>
<script>
	$(function() {
		var winH = $(window).height();
		var winW = $(window).width();
		$('body').css({
			width:winW,
			height:winH,
			overflow:'hidden'
		});
		$('.star nav').delegate('a', 'click', function(event) {
			var input = $(this).parents('li').find('input');
			$(this).addClass('active');
			$(this).prevAll().addClass('active');
			$(this).nextAll().removeClass('active');
			var len = $(this).parents('li').find('.active').length;
			$("#evaluate").val(len);
		});
		$('.refer').delegate('button','click',function(){
			$("#startL").val($("#evaluate").val());
			
			var contract = isNull($("#contract").val());
			var contractPhone = isNull($("#contractPhone").val());
			var describes = isNull($("#describes").val());
			if(contract){
				$('#contract').toggleClass('border-red'); 
			}
			if(contractPhone){
				$('#contractPhone').toggleClass('border-red'); 
			}
			if(describes){
				$('#describes').toggleClass('border-red'); 
			}
			if(contract || contractPhone || describes){
				return ;
			}
			$("#saveEvaluate").submit();
			/* layer.open({content: '提交成功！',time: 2}); */
		});
	});
	
	function goOrderGroup(){
		window.location.href = ctx+"/wapOrderGroup/selectOrderGroup";
	}
	function isNull(str) {
	    if (str == "")
	        return true;
	    var regu = "^[ |\\n|\\r]+$";
	    var re = new RegExp(regu);
	    return re.test(str);
	}
</script>
</html>
