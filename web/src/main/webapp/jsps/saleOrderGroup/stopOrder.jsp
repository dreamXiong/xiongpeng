<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
	<tiles:put name="title" value="结束订单" />
	<tiles:put name="body" type="String">
	<c:set value="stopOrder" var="modalName"></c:set>	
	<html>
		<link rel="stylesheet" href="${ctx}/css/me.css">
		<link rel="stylesheet" href="${ctx}/css/jquery-ui.min.css">
		<link rel="stylesheet" href="${ctx}/css/jq-ui-rest.css">
		<script src="${ctx}/js/hgl/datepicker-zh.js"></script>
		<script src="${ctx}/js/hgl/pick.js"></script>
		<script src="${ctx}/js/hgl/saleOrderGroup.js"></script>
	<body>
<!-- 内容板块开始 -->
		<div class="area me">
			<div class="main-right pull-right">
				<form id="key_${modalName}_qryFrm" >
					<input type="hidden" value="0" id="orderType" name="orderType"/>
					<input type="hidden" value="0" id="orderState" name="orderState"/>  
					<input type="hidden" id="orderGroupId" name="orderGroupId"/>
					<input type="hidden" id="stopReason" name="stopReason"/>
					<input type="hidden" id="version" name="version"/>
					<div class="main-nav" style="border-bottom:none;">
						<div class="clear time">
							<div class="pull-left label">订单时间</div>
							<div class="pull-left navs">
								<span class="select-time pull-left">
									<input type="text" name="startTime" readonly id="from"> ~ <input type="text" name="endTime" readonly id="to" >
								</span>
								<span class="select-time-big pull-right ">
									<input type="text" name="searchText" placeholder="请输入订单号或商品信息"/>
									<button type="button" onclick="serachStopOrder();" >搜索订单</button>
								</span>
							</div>
						</div>
					</div>
				</form>
			 <div id="key_stopOrder_list">
	 			<%@include file="stopOrderList.jsp" %>
	 		</div>
		 	</div
		</div>	
	<!-- 内容板块结束 -->
		<div id="datepicker"></div>
		
		<!-- 删除订单终止 -->
		<div id="removeOrderDivlog" class="dialog">
		   	您是否确定删除订单？？
		</div>
	</body>    
<!-- 底部结束 -->
	<div id="datepicker"></div>
	<form action="${ctx}/myOrderGroup/orderGroupDetail" method="post" id="orderGroupDetailForm">
		<input type="hidden" id="orderId" name="orderId" value=""/>
	</form>
	</html>
	<script type="text/javascript">
		$(function(){
			  $( "#removeOrderDivlog" ).dialog({            
			        title:'删除订单' ,  
			        autoOpen: false ,//禁止自己弹出
			        resizable: false,//禁止弹出层缩放
			        draggable :false,//禁止拖动
			        modal: true,//是否有模态框
			    //  height:?
			        width:'440',     //设置宽高
			        closeText:'关闭',//closetitle
			        buttons:{         //创建btn
			        	确定:function(){
			        		deleteOrderGroup();
			          	},
			          	关闭:function(){          //btn的回调函数
					    	$(this).dialog("close");
					    },
			        },
			    }); 
		});
		EcWeb.currentModalName = '${modalName}';
		 /* 表单提交 */
		  function serachStopOrder(){
			  	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
			    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
			    var frm = $("#" + frmId);
			    var params=frm.serialize();
				$.ajax({
			        type : "POST",
			        url : ctx+'/saleOrderGroup/serachStopOrder',
			        data : params,
			        success : function(response) {
			            $("#" + dataDomId).html(response);
			        }
			    }); 
		  }
		 /**删除订单**/
	 function deleteOrderGroup(){
			 
			 var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		     var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		     var frm = $("#" + frmId);
		    
		     var params=frm.serialize();
			 $.ajax({
			        type : "POST",
			        url : ctx+'/saleOrderGroup/deleteOrderGroup',
			        data : params,
			        success : function(response) {
			        	$("#removeOrderDivlog").dialog("close");
			            $("#" + dataDomId).html(response);
			           
			        }
			    }); 
		 }
		 
	function removeOrderDivlog(orderGroupId){
		 $("#orderGroupId").val(orderGroupId); 
		 $( "#removeOrderDivlog" ).dialog('open');
	}
	</script>
	</tiles:put>
</tiles:insert>