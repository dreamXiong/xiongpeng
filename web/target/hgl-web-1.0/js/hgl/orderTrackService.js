function doAltService3(id){
		if(!confirm("确定要恢复服务订单吗？")){
			return false;
		}
		doAltService(id,"恢复服务成功!");
	}; 
	
	/********完成服务start********************************/
	 //初始化修改订单价格
	function updatePrice(id,totalMoney){	
		$("#shipcost #id").val(id);
		$("#shipcost #totalMoney").val(totalMoney.toFixed(2));
		$('#shipcost').dialog('open');			
	}
	
	$(function() {              
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
            关闭:function(){          //btn的回调函数
			     $(this).dialog("close");
			  },
			确定:function(){
				var id =$("#shipcost #id").val();
				var price =$("#shipcost #totalMoney").val();
				window.location.href = ctx+"/serviceOrder/doServicePrice?id="+id+"&price="+price;
			  },
	        },
	    });
	  });
	/********完成服务end  **********************************/
	
	
	
	 function doAltService1(id){
		 if(!confirm("确定要取消接的订单吗？")){
				return false;
			}
		 doAltService(id);
		}; 
		
	 function doAltService2(id){
			if(!confirm("确定要挂起服务订单吗？")){
				return false;
			}
			doAltService(id,"服务订单挂起成功!");
		};
		
		
	function startServer(id){
		if(!confirm("确定现在开始服务吗？")){
			return false;
		}
		doOrderService(id,"开始服务成功");
	}; 
	
		 function doAltService(id,message){
			 $.ajax({                       
					type: "POST",
					url: ctx+"/serviceOrder/doAltService",
					data: "id="+id,
					success: function(response){
						$("#key_myService_list").html(response);
						toastr.success(message,"操作提示");
					},
					error:function(response){
						toastr.error("失败","操作提示");
					}
					  
				});
		 }
		 
			
			 function doOrderService(id,message){   
				 $.ajax({                       
						type: "POST",
						url: ctx+"/serviceOrder/doOrderService",
						data: "id="+id,
						success: function(response){
							$("#key_myService_list").html(response);
							toastr.success(message,"操作提示");
						},
						error:function(response){
							toastr.error("失败","操作提示");
						}
					});
			 }
			
	/***分配订单***/
			 function findMymaster(id){
				 window.location.href = ctx+"/serviceOrder/findMymaster?id="+id;
//				 $.ajax({                       
//						type: "POST",
//						url: ctx+"/serviceOrder/findMymaster",
//						data: "id="+id,
//						success: function(response){
//							alert($("#findMymasterListAAAA").html()); 
//							$("#findMymasterListAAAA").html(response);
//						},
//						error:function(response){
//							alert("222");
//							toastr.error("失败","操作提示");
//						}
//					});
				 
			 }
			 
				 
					
