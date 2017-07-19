	$(function() {
		//防止页面刷新时浏览器缓存问题
		$("#orderState").val(0);
		$("#orderType").val(0);
		
		$('dd').click(function(event) {
			$('dd').removeClass('active');
			$(this).addClass('active');
		});
		$('.orderType a').on('click',function(){
			$(this).addClass('active').siblings('a').removeClass('active');
			$("#orderType").val($(this).attr("name"));
			submitform();
		});
		
	  $(document).delegate("#orderStateSel a" ,'click',function(){
		  var oLen = $("#orderStateSel .active").length;
		 
		  if($(this).hasClass('active')){
			 if(oLen != 1){
				  $(this).removeClass('active');
			 }
			}else{
				$(this).addClass('active');
			}
		  if($(this).attr("name") == '0'){
			  $("#orderStateSel .active").removeClass("active");
			  
			  $("#allOrderGroup").addClass("active");   
		  }else{
			  $("#allOrderGroup").removeClass("active");   
		  }
		  
		  var os = $("#orderStateSel .active");
			var orderState = '';
			for(var i=0 ; i < os.length ; i++){
				if(os.length == (i+1) ){
					orderState += $(os[i]).attr("name");
				}else{ 
					orderState += $(os[i]).attr("name")+",";
				}
				$("#orderState").val(orderState);
			}
			submitform();
	    });
		
	  $( "#from" ).datepicker({
	      onClose: function( selectedDate ) {
	            $( "#to" ).datepicker( "option", "minDate", selectedDate );
	          }
	    });
	    $( "#to" ).datepicker({ 
	      maxDate:0,
	      onClose: function( selectedDate ) {
	            $( "#from" ).datepicker( "option", "maxDate", selectedDate );
	          }
	    });
	 // 获取时间对象
		var oDate = new Date();
		// 获取当前的时间 年-月-日
		var to_year = oDate.getFullYear();
		var to_month = oDate.getMonth()+1;
		var to_date = oDate.getDate();	
		// 30天前的日期
		oDate.setTime(oDate.getTime()- 30 * 24 * 60 * 60 * 1000);
		// 获取30天前的 年-月-日
		var new_year = oDate.getFullYear();
		var new_month = oDate.getMonth()+1;
		var new_date = oDate.getDate();	

		//月-日的length小于两位数 
		if(to_month < 10){
			to_month = '0'+to_month;
		}
		if(to_date < 10){
			to_date = '0'+to_date;
		}
		if(new_month < 10){
			new_month = '0'+new_month;
		}
		if(new_date < 10){
			new_date = '0'+new_date;
		}
		// 获取时间的 值给input
		$('#from').val(new_year+'-'+new_month+'-'+new_date);
		$('#to').val(to_year+'-'+to_month+'-'+to_date);
		
		
		
	    $(document).delegate('.indent-head', 'click', function(event) {
	    	var span=$(this).find('.icon');
	    	if(span.hasClass('fa fa-angle-right icon')){
	    		span.attr('class','fa fa-angle-down icon');
	    	}else{
	    		span.attr('class','fa fa-angle-right icon');
	    	}
	        $(this).siblings('table').toggle();
	    });
	  });
	/* $( "#stopOrderDivlog" ).dialog({            
		    	   title:'终止订单' ,  
			        autoOpen: false ,//禁止自己弹出
			        resizable: false,//禁止弹出层缩放
			        draggable :false,//禁止拖动
			        modal: true,//是否有模态框
			        width:'440',     //设置宽高
			        closeText:'关闭',//closetitle
			        buttons:{         //创建btn
				关闭:function(){          //btn的回调函数
				    $(this).dialog("close");
				          },
		        确定:function(){
		        	stopOrderGroup();
		          },
			        },
			    });
		  });*/
	  
	  function showCancleOrderDivlog(id){
		  var version = $("#version_"+id).val();
		  $("#version").val(version);
		  $("#orderGroupId").val(id);
		  $("#showCancleOrder").modal("show");
	  }
	  function cancleOrderGroup(){
		  var reson = $("#textShow").val();
		  $("#stopReason").val(reson);
		  	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		    var frm = $("#" + frmId);
		    var params=frm.serialize();
		    $("#showCancleOrder").modal("hide");
		  $.ajax({
		        type : "POST",
		        url : ctx+'/orderGroup/cancleMyOrderGroupAdmin',
		        data : params,
		        success : function(response) {
		        	 $( "#dialog" ).dialog('close'); 
		        	 $("#" + dataDomId).html(response);
		        	 orderStateInfo(params);
		        }
		    });
	  }
	  function orderStateInfo(params){
		  $.ajax({
		        type : "POST",
		        url : ctx+'/orderGroup/selectMyOrderStatesCount',
		        data : params,
		        dataType:"json",
		        success : function(response) {
		        	$("#staus200").text("待确定("+response.tbProductList.staus200+")");
		        	$("#staus202").text("待付款("+response.tbProductList.staus202+")");
		        	$("#staus204").text("待补发货("+response.tbProductList.staus204+")");
		        	$("#staus208").text("待发货("+response.tbProductList.staus208+")");
		        	$("#staus210").text("待确定收货("+response.tbProductList.staus210+")");
		        	$("#staus218").text("待确定终止("+response.tbProductList.staus218+")");
		        	$("#staus220").text("交易完成("+response.tbProductList.staus220+")");
		        }
		    }); 
	  }
	  
	  /* 表单提交 */  
	  function submitform(){
		  	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		    var frm = $("#" + frmId);
		    var params=frm.serialize();
			$.ajax({
		        type : "POST",
		        url : ctx+'/orderGroup/serachOrderGroup',
		        data : params,
		        success : function(response) {
		            $("#" + dataDomId).html(response);
		        }
		    }); 
	  }
	function configOrderGroup(id){
	    var version = $("#version_"+id).val();
	    $("#version").val(version);
	    $("#orderGroupId").val(id);
	  	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
	    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
	    var frm = $("#" + frmId);
	    var params=frm.serialize();
	  $.ajax({
	        type : "POST",
	        url : ctx+'/orderGroup/configOrderGroup',
	        data : params,
	        success : function(response) {
	        	 $("#" + dataDomId).html(response);
	        	 orderStateInfo(params);
	        }
	    }); 
	}
  function showStopOrderDivlog(id){
	  var version = $("#version_"+id).val();
	  $("#version").val(version);
	  $("#orderGroupId").val(id);
	  $("#showStopOrder").modal("show");
  }
	  function stopOrderGroup(){
		  var reson = $("#stopTextShow").val();
		  $("#stopReason").val(reson);
		  	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		    var frm = $("#" + frmId);
		    var params=frm.serialize();
		  $.ajax({
		        type : "POST",
		        url : ctx+'/orderGroup/stopOrderGroupAdmin',
		        data : params,
		        success : function(response) {
		        	 $("#showStopOrder").modal("hide");
		        	 $("#" + dataDomId).html(response);
		        	 orderStateInfo(params);
		        }
		    }); 
	  }
	  function  configStopOrderGroupAdmin(id){
		  var version = $("#version_"+id).val();
		  $("#version").val(version);
		  $("#orderGroupId").val(id);
		  	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		    var frm = $("#" + frmId);
		    var params=frm.serialize();
		  $.ajax({
		        type : "POST",
		        url : ctx+'/orderGroup/configStopOrderGroupAdmin',
		        data : params,
		        success : function(response) {
		        	 $("#" + dataDomId).html(response);
		        	 orderStateInfo(params);
		        }
		    }); 
	  }
	  //确认收到货款
	  function  configReceivePayment(id){
		  var version = $("#version_"+id).val();
		  $("#version").val(version);
		  $("#orderGroupId").val(id);
		  	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		    var frm = $("#" + frmId);
		    var params=frm.serialize();
		  $.ajax({
		        type : "POST",
		        url : ctx+'/orderGroup/configReceivePayment',
		        data : params,
		        success : function(response) {
		        	 $("#" + dataDomId).html(response);
		        	 orderStateInfo(params);
		        }
		    }); 
	  }
	  function cancleStopOrderGroupAdmin(id){
		  var version = $("#version_"+id).val();
		  $("#version").val(version);
		  $("#orderGroupId").val(id);
			var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		    var frm = $("#" + frmId);
		    var params=frm.serialize();
		  $.ajax({
		        type : "POST",
		        url : ctx+'/orderGroup/cancleStopOrderGroupAdmin',
		        data : params,
		        success : function(response) {
		        	 $("#" + dataDomId).html(response);
		        	 orderStateInfo(params);
		        }
		    }); 
	  }
	  
	  //订单改价 -- 单价
	  function updateItemPrice(orderDetailId){
		  var isError = true;
		  var html ="";
		  $("#modalBody").empty();
		  $.ajax({
			  type:"post",
			  url:"../orderDetail/doInitUpdateOrderDetailPrice",
			  data:{"id":orderDetailId},
			  dataType:"json",
			  success:function(data){
				  html+="<div><label style='margin-right:5px;'>订单号</label><span>"+data.orderSerialNo+"</span></div>"+
					  	"<div><label style='margin-right:5px;'>原始价格</label><span>"+data.price+"</span></div>"+
					  	"<div><label style='margin-right:5px;'>调整价格</label><input type='text' id='newPrice' onfocus='clearPriceStyle()' placeholder='请输入调整价格'>" +
					  	"<span id='newPriceError'></span></div>";
				  $("#modalBody").append(html);
			  }
		  });
		  
		  $("#priceModal_Upd").modal("show");
		  
		  $("#priceModal_Upd #updPriceBtn").click(function(){			  
			  var newPrice = $("#priceModal_Upd #newPrice").val();
			  			  
			  //调整价格不能为空
			  if(newPrice == ""){
				  $("#priceModal_Upd #newPriceError").text("调整价格不能为空");
				  $("#priceModal_Upd #newPriceError").css("color","red");
				  $("#priceModal_Upd #newPriceError").css("font-size","12px");
				  $("#priceModal_Upd #newPrice").css("border","1px solid red");
				  isError = false;
			  }
			  
			  //调整价格必须为数字
			  if(newPrice!="" && isNaN(newPrice)){
				  $("#priceModal_Upd #newPriceError").text("调整价格必须为数字");
				  $("#priceModal_Upd #newPriceError").css("color","red");
				  $("#priceModal_Upd #newPriceError").css("font-size","12px");
				  $("#priceModal_Upd #newPrice").css("border","1px solid red");  
				  isError = false;
			  }
			  			  
			  if(isError == false){
				  return;
			  }
			  
			  //ajax调用改价方法
			  $.ajax({
				  type:"post",
				  url:"../orderDetail/doUpdateOrderDetailPrice",
				  data:{"id":orderDetailId,"price":newPrice},
				  success:function(data){
					  if(data=="true"){						  
						  $("#priceModal_Upd").modal("hide"); 
						  location.reload(true);
					  }
				  }
			  });
		  });
	  }
	  
	  //光标移除，清除样式
	  function clearPriceStyle(){
		  $("#priceModal_Upd #newPriceError").text("");
		  $("#priceModal_Upd #newPriceError").removeAttr("style");
		  $("#priceModal_Upd #newPrice").removeAttr("style");
	  }
	  
	  //订单改价 --总价
	  function updateTotalPrice(orderGroupId){	  
		  var html = "";
		  $("#payMoneyModal_Upd #modalBody").empty();
		  $.ajax({
			  type:"post",
			  url:"doInitOrderGroup",
			  data:{"id":orderGroupId},
			  dataType:"json",
			  success:function(data){
				  html += "<div><label style='margin-right:5px;'>原始总价</label><span>"+data.payMoney+"</span></div>"+
				  		  "<div><label style='margin-right:5px;'>调整总价</label><input type='text' id='newPayMoney' onfocus='clearPayMoneyStyle()'>" +
				  		  "<span id='newPayMoneyError'></span></div>";
				  
				  $("#payMoneyModal_Upd #modalBody").append(html);
			  }
		  });
		  
		  $("#payMoneyModal_Upd").modal("show");
		  
		  $("#payMoneyModal_Upd #updMoneyBtn").click(function(){
			  var isError = true;
			  var payMoney = $("#payMoneyModal_Upd #newPayMoney").val();
			  
			  //判断输入总价不能为空
			  if(payMoney==""){
				  $("#payMoneyModal_Upd #newPayMoneyError").text("调整总价不能为空");
				  $("#payMoneyModal_Upd #newPayMoneyError").css("color","red");
				  $("#payMoneyModal_Upd #newPayMoneyError").css("font-size","12px");
				  $("#payMoneyModal_Upd #newPayMoney").css("border","1px solid red");
				  isError = false;
			  }
			  
			  //判断输入调整总价必须为数字
			  if(payMoney!="" && isNaN(payMoney)){
				  $("#payMoneyModal_Upd #newPayMoneyError").text("调整总价必须为数字");
				  $("#payMoneyModal_Upd #newPayMoneyError").css("color","red");
				  $("#payMoneyModal_Upd #newPayMoneyError").css("font-size","12px");
				  $("#payMoneyModal_Upd #newPayMoney").css("border","1px solid red");
				  isError = false;
			  }
			  
			  if(isError == false){
				  return;
			  }
			  
			  $.ajax({
				  type:"post",
				  url:"doUpdatePayMoney",
				  data:{"id":orderGroupId,"payMoney":payMoney},
				  success:function(data){
					  if(data=="true"){
						  $("#payMoneyModal_Upd").modal("hide");
						  location.reload(true);
					  }
				  }
			  });
		  });
	  }
	  
	  //清除调整总价样式
	  function clearPayMoneyStyle(){
		  $("#payMoneyModal_Upd #newPayMoneyError").text("");
		  $("#payMoneyModal_Upd #newPayMoneyError").removeAttr("style");
		  $("#payMoneyModal_Upd #newPayMoney").removeAttr("style");
	  }
	  
	  