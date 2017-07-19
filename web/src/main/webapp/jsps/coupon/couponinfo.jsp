<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
<tiles:put name="title" value="我的优惠券"/>
<tiles:put name="body" type="String">    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>我的优惠券</title>
	<link rel="stylesheet" href="${ctx}/css/me.css">
	<script src="${ctx}/js/hgl/pick.js"></script>
	<script src="${ctx}/js/hgl/datepicker-zh.js"></script>
</head>
<c:set value="couponInfo" var="modalName"></c:set>
<body>
	<div class="area me">
		<div class="main-right pull-right">
			<div class="details">   
				<div class="details-list">
					<h3 class="bg-gray">我的优惠券</h3>
					<div class="select-time coupon clear">
						<form action="" id="key_${modalName}_qryFrm" method="post" onsubmit="return false;" >
							<div class="pull-left">
								使用类型 : &nbsp;&nbsp;<label for="gain"><input type="radio" id="gain" name="status" value="400">获得</label>
													 <label for="employ"><input type="radio" id="employ" name="status" value="402">使用</label>
							</div>
							<div class="pull-left">
								时间 &nbsp;&nbsp;<input type="text" readonly id="from" name="from"> ~ <input type="text" readonly id="to" name="to">
								<a href="javascript:;" class="select-btn" onclick="submitform()">搜索</a>
							</div>
						</form>
					</div>
					<div class="coupon clear">
						<div class="pull-left">
							当前优惠券余额  <span class="text-red">${remainingAmt}</span>
						</div>
						<div class="pull-left">
							总收入  <span class="text-red">${gainAmt}</span>
						</div>
						<div class="pull-left">
							总支出  <span class="text-red">${employAmt}</span>
							<a href="${ctx}/pick/index" class="select-btn">立即使用</a>
						</div>							
					</div>
				</div>
			</div>
				<div id="key_${modalName}_list">
					<%@include file="couponinfolist.jsp" %>
				</div>
		</div>
	</div>
	<div id="datepicker"></div>
	<script type="text/javascript">
	EcWeb.currentModalName = '${modalName}';
	/*表单提交*/
	function submitform(){
    	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		var frm = $("#" + frmId);
		var params=frm.serialize();
		$.ajax({
		       type:"POST",
		       url:'doSearchResult',
		       data:params,
		       success : function(response) {
		            $("#" + dataDomId).html(response);
		        }
		    }); 
	 }
	
	$( "#from" ).datepicker({
		onClose: function( selectedDate ){
    		$( "#to" ).datepicker( "option", "minDate", selectedDate );
  		}
	});
	$( "#to" ).datepicker({ 
		maxDate:0,
		onClose: function( selectedDate ) {
    		$( "#from" ).datepicker( "option", "maxDate", selectedDate );
  		}

	});
	
	$("document").ready(function(){
		var oDate = new Date();
		var time = oDate.getTime()-30 * 24 * 60 * 60 * 1000;
		var result = new Date(time);
		var year = oDate.getFullYear();
		var month = oDate.getMonth()+1;
		var date = oDate.getDate();
		var year1 = result.getFullYear();
		var month1 = result.getMonth()+1;
		var date1 = result.getDate();
		 if(month<10){
		 	month='0'+(month);
		 };
		 if(date<10){
		 	date='0'+date;
		 };
		 if(month1<10){
			month1='0'+(month1);
		};
		if(date1<10){
			date1='0'+date1;
		};
		$('#to').val(year+'-'+month+'-'+date);
		$('#from').val(year1+'-'+month1+'-'+date1);
	}); 
	</script>
</body>
</html>
</tiles:put>
</tiles:insert>