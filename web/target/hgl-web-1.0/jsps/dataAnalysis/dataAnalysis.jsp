<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
	<tiles:put name="title" value="数据分析" />
	<tiles:put name="body" type="String">
	<c:set value="takl" var="modalName"></c:set>
<html>
	<link rel="stylesheet" href="${ctx}/css/me.css">
	<script src="${ctx}/js/jquery/jquery1.9.2/ui/minified/jquery-ui.min.js"></script>
	<script src="${ctx}/js/hgl/datepicker-zh.js"></script>
	<script src="${ctx}/js/jquery/jquery1.9.2/ui/jquery.ui.widget.js"></script>
<body>
	<div class="main-right pull-right">
	<div class="main-title">
		<div class="report-seek">
			<input type="text" id="datepicker" readonly placeholder="请选择时间">
			<button type="button" class="btn-bg" onclick="serach()">
				<span>查询</span>
			</button>
			<span style="color:#aaa;font-size:12px;">(数据分析为前一天日终统计结果，不包含当天数据结果。)</span>
		</div>
		<div id="listDiv">
			<%@include file="dataAnalysisList.jsp" %>
		</div>
	</div>
</div>
<form id="goodsDetailForm" method="post" action="${ctx}/dataAnalysis/goodsDetail">
	<input type="hidden" value="" id="selectDate" name="selectDate"/>
	<input type="hidden" value="" id="location" name="location"/>
	<input type="hidden" value="" id="orderByClause" name="orderByClause"/>
</form> 
</body>
</html>
<script>
	$(function(){
		$('tbody').each(function(i){
			$('tbody').eq(i).find('tr:odd').addClass('odd');
		});
		var date = new Date();
		var year = date.getFullYear();
		var month = date.getMonth()+1;
		month = (month<10)? '0'+month : ''+month;
		$( "#datepicker" ).val(year+'年'+'-'+month+'月');
	    $( "#datepicker" ).datepicker({
	       showOtherMonths: true,
	       selectOtherMonths: true,
	       dateFormat: 'yy年-mm月',
	    });
	    $('.main-body').css('background','none');
	});
	
	//查询
	function serach(){
		$.ajax({
	        type: "POST",
	        url: "${ctx}/dataAnalysis/searchList",
	        data: "selectDate="+$("#datepicker").val()+"&random="+Math.random(),
	        success: function(response){
				$("#listDiv").html(response);
	        },
	        error: function() {
	        	
	        }   
	    });
	}
	
	//库存预警明细
	function goodsDetail(location,orderByClause){
		$("#location").val(location);
		$("#orderByClause").val(orderByClause);
		$("#selectDate").val($("#datepicker").val());
		$("#goodsDetailForm").submit();
	}
	
	//利润变化
	function profitsChangeDetail(){
		$("#selectDate").val($("#datepicker").val());
		$("#goodsDetailForm").attr("action","${ctx}/dataAnalysis/profitsChangeDetail");
		$("#goodsDetailForm").submit();
	}
	
	//库存预警明细
	function customerInterestsDetail(location,orderByClause){
		$("#location").val(location);
		$("#orderByClause").val(orderByClause);
		$("#selectDate").val($("#datepicker").val());
		$("#goodsDetailForm").attr("action","${ctx}/dataAnalysis/customerInterestsDetail");
		$("#goodsDetailForm").submit();
	}
	
</script>
</tiles:put>
</tiles:insert>