<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
	<tiles:put name="title" value="我的经验值" />
	<tiles:put name="body" type="String">
	<c:set value="experience" var="modalName"></c:set>	
	<html>
<head>
	<meta charset="UTF-8">
	<title>我的等级</title>
	<link rel="stylesheet" href="${ctx}/css/me.css">
	<script src="${ctx}/js/jquery/jquery1.9.2/ui/minified/jquery-ui.min.js"></script>
	<script src="${ctx}/js/hgl/datepicker-zh.js"></script>
	<script src="${ctx}/js/hgl/pick.js"></script>
</head>
<body>
<!-- 内容板块开始 -->
	<div>
		<div class="area me">
			
			<div class="main-right pull-right">
				<div class="details">
					<div class="details-list">
						<h3 class="bg-gray">我的经验值</h3>
						<form id="key_${modalName}_qryFrm"  onsubmit="return false;" class="form-horizontal">
						<div class="select-time">
							时间 &nbsp;&nbsp;
							<input type="text" name="startTime" readonly id="from"> ~ <input type="text" name="endTime" readonly id="to" >
							<button type="submit"  class="select-btn" onclick="submitform()" style="height:28px;">搜索</button>
						</div>
						</form>
						<div>
							当前经验值 : <span class="text-red">${experience}</span>
						</div>
					</div>
				</div>
				  <div id="key_${modalName}_list">
					<%@include file="myExperienceList.jsp" %>
				</div>	
			</div>
		</div>
	</div>
<div id="datepicker"></div>
</body>
<script>
	$(function() {
		
		$( "#from" ).datepicker({
			onClose: function( selectedDate ) {
        		$( "#to" ).datepicker( "option", "minDate", selectedDate );
      		}
		})
		$( "#to" ).datepicker({ 
			maxDate:0,
			onClose: function( selectedDate ) {
        		$( "#from" ).datepicker( "option", "maxDate", selectedDate );
      		}

		})
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
	
	EcWeb.currentModalName = '${modalName}';
	 /* 表单提交 */
	 function submitform(){
		  var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		    var frm = $("#" + frmId);
		    var params=frm.serialize();;
			$.ajax({
		        type : "POST",
		        url : 'serachExperience',
		        data : params,
		        success : function(response) {
		            $("#" + dataDomId).html(response);
		        }
		    }); 
	 }
</script>
</html>
	</tiles:put>
</tiles:insert>