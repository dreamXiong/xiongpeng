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
		
		//价格获得光标，清除样式
		$("#totalMoney").focus(function(){
			$("#moneyError").text("");
		});
	});
	 /* 表单提交 */
	  function submitform(){
		  	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		    var frm = $("#" + frmId);
		    var params=frm.serialize();
			$.ajax({
		        type : "POST",
		        url : ctx+'/saleOrderGroup/serachSaleOrderGroup',
		        data : params,
		        success : function(response) {
		            $("#" + dataDomId).html(response);
		        }
		    }); 
	  }

	  $(function() {              
		    $( "#dialog" ).dialog({            
		        title:'取消订单' ,  
		        autoOpen: false ,//禁止自己弹出
		        resizable: false,//禁止弹出层缩放
		        draggable :false,//禁止拖动
		        modal: true,//是否有模态框
		    //  height:?
		        width:'440',     //设置宽高
		        closeText:'关闭',//closetitle
		        buttons:{         //创建btn
	        确定:function(){
	        	cancleOrderGroup();
	          },
            关闭:function(){          //btn的回调函数
			    $(this).dialog("close");
	          },
	        },
		    }); 
		    $( "#shipcost" ).dialog({     		    	
		        title:'商品改价' ,  
		        autoOpen: false ,//禁止自己弹出
		        resizable: false,//禁止弹出层缩放
		        draggable :false,//禁止拖动
		        modal: true,//是否有模态框
		    //  height:?
		        width:'440',     //设置宽高
		        closeText:'关闭',//closetitle
		        buttons:{         //创建btn
		        确定:function(){
		        	 doUpdatePrice(this);
		          },
	            关闭:function(){          //btn的回调函数
				     $(this).dialog("close");
				  },
		        },
		    });
		    
		    
		    $("#unitPrice").dialog({     		    	
		        title:'商品改价' ,  
		        autoOpen: false ,//禁止自己弹出
		        resizable: false,//禁止弹出层缩放
		        draggable :false,//禁止拖动
		        modal: true,//是否有模态框
		        width:'440',     //设置宽高
		        closeText:'关闭',//closetitle
		        buttons:{         //创建btn
		        确定:function(){
		        	doUpdateUnitPrice(this);
		          },
	            关闭:function(){          //btn的回调函数
				     $(this).dialog("close");
				  },
		        },
		    });
		    
		    //获得光标，清除修改单价样式
		    $("#shipcost #price").focus(function(){
				$("#shipcost #moneyError").text("");
			});
		    
		    $("#new_price").focus(function(){
		    	$("#unitPrice #moneyError").text("");
		    });
		    
		  });
	  	
	  
	  function showCancleOrderDivlog(id){
		  var version = $("#version_"+id).val();
		  $("#version").val(version);
		  $("#orderGroupId").val(id);
		  $( "#dialog" ).dialog('open');  
	  }
	  function cancleOrderGroup(){
		  var reson = $("#textShow").val();
		  $("#stopReason").val(reson);
		  	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		    var frm = $("#" + frmId);
		    var params=frm.serialize();
		  $.ajax({
		        type : "POST",
		        url : ctx+'/saleOrderGroup/cancleMyOrderGroupShop',
		        data : params,
		        success : function(response) {
		        	 $( "#dialog" ).dialog('close'); 
		        	 $("#" + dataDomId).html(response);
		        	 orderStateInfo(params);
		        }
		    }); 
	  }
	  function payOrderGroup(id){
		  	$("#orderGroupId").val(id);
		  	 var version = $("#version_"+id).val();
			  $("#version").val(version);
		  	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		    var frm = $("#" + frmId);
		    var params=frm.serialize();
		  $.ajax({
		        type : "POST",
		        url : ctx+'/saleOrderGroup/payMyOrderGroup',
		        data : params,
		        success : function(response) {
		        	 $("#" + dataDomId).html(response);
		        	 orderStateInfo(params);
		        }
		    }); 
	  }
	  function orderStateInfo(params){
		  $.ajax({
		        type : "POST",
		        url : ctx+'/saleOrderGroup/selectSaleOrderStatesCount',
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
	  
	  
	  $(function() {              
		    $( "#stopOrderDivlog" ).dialog({            
	    	   title:'终止订单' ,  
		        autoOpen: false ,//禁止自己弹出
		        resizable: false,//禁止弹出层缩放
		        draggable :false,//禁止拖动
		        modal: true,//是否有模态框
		    //  height:?
		        width:'440',     //设置宽高
		        closeText:'关闭',//closetitle
		        buttons:{         //创建btn
			
	        确定:function(){
	        	stopOrderGroup();
	          },
            关闭:function(){          //btn的回调函数
			    $(this).dialog("close");
	          },
		        },
		    });
		  });
	  
	  function showStopOrderDivlog(id){
		  var version = $("#version_"+id).val();
		  $("#version").val(version);
		  $("#orderGroupId").val(id);
		  $("#stopOrderDivlog").dialog('open');  
	  }
	  function stopOrderGroup(){
		  	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		    var frm = $("#" + frmId);
		    var reson = $("#stopTextShow").val();
			$("#stopReason").val(reson);
		    var params=frm.serialize();
		  $.ajax({
		        type : "POST",
		        url : ctx+'/saleOrderGroup/stopMyOrderGroup',
		        data : params,
		        success : function(response) {
		        	 $("#stopOrderDivlog").dialog('close');  
		        	 $("#" + dataDomId).html(response);
		        	 orderStateInfo(params);
		        }
		    }); 
	  }
	  function cancleStopOrderGroupShop(id){
		  var version = $("#version_"+id).val();
		  $("#version").val(version);
		  $("#orderGroupId").val(id);
		  	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		    var frm = $("#" + frmId);
		    var params=frm.serialize();
		  $.ajax({
		        type : "POST",
		        url : ctx+'/saleOrderGroup/cancleStopOrderGroup',
		        data : params,
		        success : function(response) {
		        	 $("#" + dataDomId).html(response);
		        	 orderStateInfo(params);
		        }
		    }); 
	  }
	  
	  function configStopOrderGroupShop(id){
		  var version = $("#version_"+id).val();
		  $("#version").val(version);
		  $("#orderGroupId").val(id);
		  	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		    var frm = $("#" + frmId);
		    var params=frm.serialize();
		  $.ajax({
		        type : "POST",
		        url : ctx+'/saleOrderGroup/configStopOrderGroup',
		        data : params,
		        success : function(response) {
		        	 $("#" + dataDomId).html(response);
		        	 orderStateInfo(params);
		        }
		    }); 
	  }
	  $(function(){
		  $( "#configOrderGroupShow" ).dialog({            
		        title:'确认订单',  
		        autoOpen: false ,//禁止自己弹出
		        resizable: false,//禁止弹出层缩放
		        draggable :false,//禁止拖动
		        modal: true,//是否有模态框
		    //  height:?
		        width:'440',     //设置宽高
		        closeText:'关闭',//closetitle
		        buttons:{         //创建btn
		          	关闭:function(){          //btn的回调函数
				    	$(this).dialog("close");
				    },
				    确定:function(){
				    	configOrderGroup();
				    	$(this).dialog("close");
		          	},
		        },
		    }); 
	 });
	  
	  function configOrderGroupShow(id){
		  $("#orderGroupId").val(id);
		  $( "#configOrderGroupShow" ).dialog('open');  
	  }
	  function configOrderGroup(){
		    var id = $("#orderGroupId").val();
		    var version = $("#version_"+id).val();
		    $("#version").val(version);
		  	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		    var frm = $("#" + frmId);
		    var params=frm.serialize();
		  $.ajax({
		        type : "POST",
		        url : ctx+'/saleOrderGroup/configOrderGroup',
		        data : params,
		        success : function(response) {
		        	 $("#" + dataDomId).html(response);
		        	 orderStateInfo(params);
		        	 $( "#configOrderGroupShow" ).dialog('close');  
		        }
		    }); 
		}
	  
	  function sendOutGoodsByShop(id){
		  var version = $("#version_"+id).val();
		    $("#version").val(version);
		    $("#orderGroupId").val(id);
		  	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		    var frm = $("#" + frmId);
		    var params=frm.serialize();
		  $.ajax({
		        type : "POST",
		        url : ctx+'/saleOrderGroup/sendOutGoodsByShop',
		        data : params,
		        success : function(response) {
		        	 $("#" + dataDomId).html(response);
		        	 orderStateInfo(params);
		        }
		    }); 
	  }
	  
		//订单详情
		function orderGroupDetail(id){
			$("#orderGroupDetailForm #orderId").val(id);
			$("#orderGroupDetailForm").submit();
		}
		
		
		//初始化修改订单价格
		function updatePrice(id,totalMoney){		
			$("#shipcost #id").val(id);		
			$("#shipcost #totalMoney").val(totalMoney.toFixed(2));
			$('#shipcost').dialog('open');			
		}
				
		//调用后台方法修改订单价格
		function doUpdatePrice(dialog){
			var id = $("#shipcost #id").val();
			var totalMoney = $("#shipcost #totalMoney").val();
			if(!isNaN(totalMoney)==false){
				$("#shipcost #moneyError").text("您的价格输入不正确哦");
				return false;
			}
			
			$.ajax({
				type:"post",
				url:"doUpdateTotalMoney",
				data:{"id":id,"totalMoney":totalMoney,},
				success:function(data){
					if(data=="true"){
						$("#item"+id).text("￥"+totalMoney+"元");
						$("#"+id).attr("onclick","updatePrice("+id+","+totalMoney+")");
					}else{
					}
				}
			});
			
			 $(dialog).dialog("close");
		}
		
		//修改订单单价
		function updateUnitPrice(groupId,detailId,unitPrice){
			$("#group_id").val(groupId);
			$("#detail_id").val(detailId);
			$("#new_price").val(unitPrice);			
			$("#unitPrice").dialog("open");
		}
		
		//调用后台方法修改订单单价
		function doUpdateUnitPrice(dialog){
			var groupId = $("#group_id").val();
			var detailId = $("#detail_id").val();
			var unitPrice = $("#new_price").val();
			if(!isNaN(unitPrice)==false){
				$("#unitPrice #moneyError").text("您的价格输入不正确");
				return false;
			}
			$.ajax({
				type:"post",
				url:"doUpdateUnitPrice",
				data:{"groupId":groupId,"detailId":detailId,"unitPrice":unitPrice},
				success:function(data){
					if(data!="false"){
						var html = unitPrice+"元<br><a class='btn' href='javascript:void(0);' onclick='updateUnitPrice("
						+groupId+","+detailId+","+unitPrice+")'>改价</a><br>";
						var buyNum = $("#buyNum"+detailId).text().trim();
						$("#td"+detailId).html(html);
						$("#netAmount"+detailId).html(buyNum*unitPrice+"元");
						$("#item"+detailId).text("￥"+data+"元");					
						$("#"+groupId).attr("onclick","updatePrice("+groupId+","+data+")");
					}
				}
			});
			
			$(dialog).dialog("close");
		}		
		
		 $(function(){
			  $( "#configReceivePaymentShow" ).dialog({            
			        title:'确认订单',  
			        autoOpen: false ,//禁止自己弹出
			        resizable: false,//禁止弹出层缩放
			        draggable :false,//禁止拖动
			        modal: true,//是否有模态框
			    //  height:?
			        width:'440',     //设置宽高
			        closeText:'关闭',//closetitle
			        buttons:{         //创建btn
			          	关闭:function(){          //btn的回调函数
					    	$(this).dialog("close");
					    },
					    确定:function(){
					    	configReceivePayment();
					    	$(this).dialog("close");
			          	},
			        },
			    }); 
		 });
		
		  function configReceivePaymentShow(id){
			  $("#orderGroupId").val(id);
			  $( "#configReceivePaymentShow" ).dialog('open');  
		  }
		
		function configReceivePayment(){
			  var id = $("#orderGroupId").val();
			    var version = $("#version_"+id).val();
			    $("#version").val(version);
			  	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
			    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
			    var frm = $("#" + frmId);
			    var params=frm.serialize();
			  $.ajax({
			        type : "POST",
			        url : ctx+'/saleOrderGroup/configReceivePayment',
			        data : params,
			        success : function(response) {
			        	 $("#" + dataDomId).html(response);
			        	 orderStateInfo(params);
			        	 $( "#configReceivePaymentShow" ).dialog('close');  
			        }
			    }); 
		}
		
		
		