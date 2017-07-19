$(function() {
		$(document).delegate('dl dt','click',function(){
 			$(this).find('.icon-angle-right').toggleClass('rotate');
 			$(this).siblings('dd').slideToggle(100);
 		});
 		$('.nav-tab-top').delegate('li','click',function(){
 			$(this).addClass('cur').siblings('li').removeClass('cur');
 			selectOrderList($(this).attr("id"));
 		});
 		
 		
 		// 弹出取消订单对话框
 		$(document).delegate('.off','click',function(){
 			$('html,body').addClass('hidden');
 			$('.on').css('left','0');
 			$("#orderGroupId").val($(this).attr("id"));
 			$("#orderGroupVersion").val($(this).attr("name"));
 		});
 		// 取消对话框
 		$(document).delegate('#cancleCabolish','click',function(){
 			$('.on').css('left','100%');
 			$("#orderGroupId").val("");
 			$("#orderGroupVersion").val("");
 			$('html,body').removeClass('hidden');
 		});
 		//确认对话框
 		$(document).delegate('#cancleVerify','click',function(){
 			$('.on').css('left','100%');
 			cancleOrderGroup($("#orderGroupId").val(),$("#orderGroupVersion").val());
 			layer.open({
			    content: '订单取消成功',  //弹出的文字
			    time: 2                 //弹出两秒之后文字消失
			});
 		});
 		
 	// 弹出终止订单对话框
 		$(document).delegate('.stop','click',function(){
 			$('html,body').addClass('hidden');
 			$('.stopOrder').css('left','0');
 			$("#orderGroupId").val($(this).attr("id"));
 			$("#orderGroupVersion").val($(this).attr("name"));
 		});
 		// 取消对话框
 		$(document).delegate('#stopAbolish','click',function(){
 			$('html,body').removeClass('hidden');
 			$('.stopOrder').css('left','100%');
 			$("#orderGroupId").val("");
 			$("#orderGroupVersion").val("");
 		});
 		//确认对话框
 		$(document).delegate('#stopVerify','click',function(){
 			$('.stopOrder').css('left','100%');
 			stopOrderGroup($("#orderGroupId").val(),$("#orderGroupVersion").val());
 			layer.open({
			    content: '订单终止申请成功',  //弹出的文字
			    time: 2                 //弹出两秒之后文字消失
			});
 			
 		});
	});
	/**
	 * 取消订单
	 * */
	function cancleOrderGroup(orderGroupId,version ){
		var orderStaus = $("#orderStaus").val();
		var stopReason = $("#cancleReason").val();
		  $.ajax({
		        type : "POST",
		        url : 'cancleMyOrderGroup',
		        data : {
		        	orderState:orderStaus,
		        	orderGroupId:orderGroupId,
		        	stopReason:stopReason,
		        	version:version
		        },
		        success : function(response) {
		        	 $("#myOrderGroupListInfo").html(response);
		        }
		    });
	  }
	
	/**
	 * 订单支付操作
	 * */
	function payMyOrderGroupShow(orderGroupId,version ){
		$("#orderIdWeiXin").val(orderGroupId);
		$("#versionWeiXin").val(version);
		layer.open({
		    content: '<p>确定支付订单吗？</p>'+
		    		 '<div class="text-left">'+
		    		 	'<label>'+
		    		 		'<input type="radio" name="zhifu" value="0" checked> 微信支付'+
		    		 	'</label>'+
		    		 	'<br/>'+
		    		 	'<label>'+
		    		 		'<input type="radio" name="zhifu" value="1">账户支付'+
		    		 	'</label>'+
		    		 '</div>',
		    btn: ['确认', '取消'],
		    shadeClose: true,
		    yes: function(){
		    	var payType = $('.layermcont input:checked').val();
				if(payType == 0){
					weixinPay();
				}
				if(payType == 1){
					payMyOrderGroup();
				}
		    }, no: function(){
		    	layer.close();
		    }
		});
	  }
	
	function weixinPay(){
		$("#winxinForm").submit();
	}
	
	function payMyOrderGroup(){
		var orderGroupId = $("#orderIdWeiXin").val();
		var version = $("#versionWeiXin").val();
		$.ajax({
	        type : "POST",
	        url : ctx+'/wapOrderGroup/payMyOrderGroup',
	        data : {
	        	orderGroupId:orderGroupId,
	        	version:version
	        },
	        success : function(response) {
		        	layer.close();   
					layer.open({
						content: '支付成功！',
						time: 1 //2秒后自动关闭
					});  
	        	 $("#myOrderGroupListInfo").html(response);
	        	 
	        },
	        error : function(response) {
	        	layer.close();   
				layer.open({
					content: '支付失败，账户余额不足，请选用微信支付！',
					time: 1 //2秒后自动关闭
				});  
	        }
	    });
	  }
	
	
	/**
	 * 申请终止订单
	 * */
	  function stopOrderGroup(orderGroupId,version){
		  var orderStaus = $("#orderStaus").val();
		  var stopReason = $("#stopReason").val();
		  $.ajax({
		        type : "POST",
		        url : 'stopMyOrderGroup',
		        data : {
		        	orderState:orderStaus,
		        	orderGroupId:orderGroupId,
		        	stopReason:stopReason,
		        	version:version
		        },
		        success : function(response) {
		        	 $("#myOrderGroupListInfo").html(response);
		        }
		    }); 
	  }
	  
	  /**
	 * 确认终止订单
	 * */
	  function configStopOrderGroup(orderGroupId,version){
		  var orderStaus = $("#orderStaus").val();
		
		  layer.open({
			    content: '您同意终止订单吗？',
			    btn: ['确认', '取消'],
			    shadeClose: true,
			    yes: function(){
			    	 $.ajax({
					        type : "POST",
					        url : 'configStopOrderGroup',
					        data : {
					        	orderState:orderStaus,
					        	orderGroupId:orderGroupId,
					        	version:version
					        },
					        success : function(response) {
						        	layer.close();   
									layer.open({
										content: '订单已经终止！',
										time: 1 //2秒后自动关闭
									});  
					        	 $("#myOrderGroupListInfo").html(response);
					        }
					    }); 
			    }, no: function(){
			    	layer.close();
			    }
			});
	  }
	  
	  
	  //取消订单终止
	  function cancleStopOrderGroup(orderGroupId,version){
		  var orderStaus = $("#orderStaus").val();
		  $.ajax({
		        type : "POST",
		        url : 'cancleStopOrderGroup',
		        data : {
		        	orderState:orderStaus,
		        	orderGroupId:orderGroupId,
		        	version:version
		        },
		        success : function(response) {
		        	 $("#myOrderGroupListInfo").html(response);
		        }
		    }); 
	  }
	  
	  //确认收货
	  function configGoodsReceipt(orderGroupId,version){
		  var orderStaus = $("#orderStaus").val();
		  $.ajax({
		        type : "POST",
		        url : 'configGoodsReceipt',
		        data : {
		        	orderState:orderStaus,
		        	orderGroupId:orderGroupId,
		        	version:version
		        },
		        success : function(response) {
		        	 $("#myOrderGroupListInfo").html(response);
		        }
		    }); 
	  }
	  
	  //删除订单
	  function deleteOrderGroup(orderGroupId,version){
		  var orderStaus = $("#orderStaus").val();
		  layer.open({
			    content: '你确定要删除此订单吗？',
			    btn: ['确认', '取消'],
			    shadeClose: true,
			    yes: function(){
			    	 $.ajax({
					        type : "POST",
					        url : 'deleteOrderGroup',
					        data : {
					        	orderGroupId:orderGroupId,
					        	version:version,
					        	orderState:orderStaus
					        },
					        success : function(response) {
						        	layer.close();   
									layer.open({
										content: '删除成功！',
										time: 1 //2秒后自动关闭
									});  
					        	 $("#myOrderGroupListInfo").html(response);
					        }
					    }); 
			    }, no: function(){
			    	layer.close();
			    }
			});
		}; 
		
	  /**
		 * 根据Tab查询订单列表信息
		 * */
		function selectOrderList(orderStaus){
			$("#orderStaus").val(orderStaus);
			  $.ajax({
			        type : "POST",
			        url : 'selectOrderList',
			        data : {
			        	orderState:orderStaus
			        },
			        success : function(response) {
			        	 $("#myOrderGroupListInfo").html(response);
			        }
			    });
		  }
		
		//订单详情goEvaluate
		function orderGroupDetail(id,balance){
			$("#orderGroupDetailForm #orderId").val(id);
			$("#orderGroupDetailForm #balance").val(balance);
			$("#orderGroupDetailForm").submit();
		}
		function goevaluate(shopId,orderGroupId){
			$("#goEvaluateOrderId").val(shopId);
			$("#goEvaluateBalance").val(orderGroupId);
			$("#goEvaluateForm").submit();
		}
		function goShop(id,distance){
			window.location.href =ctx+"/shop/index?id="+id+"&distance="+distance;
		 }
		function productDetail(id){
	 		 $("#productId").val(id);
	 		 $("#goPickDetail").submit();
	 	}