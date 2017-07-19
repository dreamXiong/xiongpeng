 function doCancelOrder(id){
		layer.open({
		    content: '确定取消订单么？',
		    btn: ['确认', '取消'],
		    shadeClose: true,
		    yes: function(){
		    	$.ajax({                       
					type: "POST",
					url: ctx+"/wapOrderService/doCancelOrder",
					data: "id="+id,
					success: function(response){     
						if(response.errcode =="0"){
							layer.close();   
							layer.open({
								content: '取消成功！',
								time: 3 //2秒后自动关闭
							});  
							window.location.href = window.location.href;
						}else if(response.errcode =="1"){
							layer.close();   
							layer.open({
								content: '取消失败！',
								time: 2 //2秒后自动关闭
							});  
							window.location.href = window.location.href;
						}
					},
					  
				});
		    }, no: function(){
		    	layer.close();
		    }
		});
	}; 
	
	
	 function doAltService1(id){
			layer.open({
			    content: '确定取消接的订单么？',
			    btn: ['确认', '取消'],
			    shadeClose: true,
			    yes: function(){
			    	doAltService(id);
			    }, no: function(){
			    	layer.close();
			    }
			});
		}; 
		
	 function doAltService2(id){
			layer.open({
			    content: '确定要挂起服务订单么？',
			    btn: ['确认', '取消'],
			    shadeClose: true,
			    yes: function(){
			    	doAltService(id);
			    }, no: function(){
			    	layer.close();
			    }
			});
		};
		
	function doAltService3(id){
		layer.open({
		    content: '确定要恢复服务订单么？',
		    btn: ['确认', '取消'],
		    shadeClose: true,
		    yes: function(){
		    	doAltService(id);
		    }, no: function(){
		    	layer.close();
		    }
		});
	}; 
		 function doAltService(id){
			 $.ajax({                       
					type: "POST",
					url: ctx+"/wapOrderService/doAltService",
					data: "id="+id,
					success: function(response){     
						if(response.errcode =="0"){
							layer.close();   
							layer.open({
								content: '成功！',
								time: 3 //2秒后自动关闭
							});  
							window.location.href = window.location.href;
						}else if(response.errcode =="1"){
							layer.close();   
							layer.open({
								content: '失败！',
								time: 3 //2秒后自动关闭
							});  
							window.location.href = window.location.href;
						}
					},
					  
				});
		 }
		 
		 function doOrderService1(id){
				layer.open({
				    content: '确定开始服务么？',
				    btn: ['确认', '取消'],
				    shadeClose: true,
				    yes: function(){
				    	doOrderService(id);
				    }, no: function(){
				    	layer.close();
				    }
				});
			}; 
			
			 function doOrderService(id){   
				 $.ajax({                       
						type: "POST",
						url: ctx+"/wapOrderService/doOrderService",
						data: "id="+id,
						success: function(response){     
							if(response.errcode =="0"){
								layer.close();   
								layer.open({
									content: '成功！',
									time: 3 //2秒后自动关闭
								});  
								window.location.href = window.location.href;
							}else if(response.errcode =="1"){
								layer.close();   
								layer.open({
									content: '失败！',
									time: 3 //2秒后自动关闭
								});  
								window.location.href = window.location.href;
							}
						},
						  
					});
			 }
			
			function doOrderService2(id){
				layer.open({
				    content: '确定已经收款服务款项了么？',
				    btn: ['确认', '取消'],
				    shadeClose: true,
				    yes: function(){
				    	doOrderService(id);
				    }, no: function(){
				    	layer.close();
				    }
				});
			}; 
			 
			 
			 function doPayService(id){
					layer.open({
					    content: '确定使用线下支付？',
					    btn: ['确认', '取消'],
					    shadeClose: true,  
					    yes: function(){
					    	doPayService(id);
					    }, no: function(){
					    	layer.close();
					    }
					});
				}; 
				 function doPayService(id){
					 $.ajax({                       
							type: "POST",
							url: ctx+"/wapOrderService/doPayService",
							data: "id="+id,
							success: function(response){     
								if(response.errcode =="0"){
									layer.close();   
									layer.open({
										content: '成功！',
										time: 3 //2秒后自动关闭
									});  
									window.location.href = window.location.href;
								}else if(response.errcode =="1"){
									layer.close();   
									layer.open({
										content: '失败！',
										time: 3 //2秒后自动关闭
									});  
									window.location.href = window.location.href;
								}
							},
							  
						});
				 }
				 
				 function doDeleteService(id){
						layer.open({
						    content: '确要删除订单么？',
						    btn: ['确认', '取消'],
						    shadeClose: true,
						    yes: function(){
						    	$.ajax({                       
									type: "POST",
									url: ctx+"/wapOrderService/doDeleteService",
									data: "id="+id,
									success: function(response){     
										if(response.errcode =="0"){
											layer.close();   
											layer.open({
												content: '删除成功！',
												time: 3 //2秒后自动关闭
											});  
											window.location.href = window.location.href;
										}else if(response.errcode =="1"){
											layer.close();   
											layer.open({
												content: '删除失败！',
												time: 2 //2秒后自动关闭
											});  
											window.location.href = window.location.href;
										}
									},error:function(response){
										layer.close();   
										layer.open({
											content: '删除失败！',
											time: 3 //2秒后自动关闭
										});  
										//window.location.href = window.location.href;
									}
								});
						    }, no: function(){
						    	layer.close();
						    }
						});
					};
					
/****************** 师傅出发***************************************************************/					
		
		  /* 师傅要出发了 */
	 	//1：弹出导航页面
	 	 function goBeiZhan(id,repairmanId){
	 		layer.open({
			    content: '导航需消耗流量，确定使用吗？',
			    btn: ['确认', '取消'],
			    shadeClose: true,
			    yes: function(){
			    	 $("#orderId").val(id);
		 			   $.ajax({
		 			        type: "POST",
		 			        async: false,
		 			        url: ctx+"/wapOrderService/goCustomeraddress",
		 			        data:{
		 			        	orderId:id
		 			        },
		 			        success: function(response){
		 			        	goFindMaster(repairmanId);
		 			        	/*重新开一个窗口进行导航*/
		 			        	var from = encodeURI(response.g.regeocode.formatted_address);
		 			        	var to = encodeURI(response.t.extensionField);
		 			        	var url = 'http://m.amap.com/navigation/walkmap/saddr='
		 			        				+response.lon+'%2C'+response.lat+'%2C'+from+'&daddr='
		 			        				+response.t.lon+'%2C'+response.t.lat+'%2C'+to+'&saddr_lonlat=&daddr_lonlat=%2C%2C'
		 			        				+to+'&maddr=&sort=&addPassing=remove';
		 			        	window.open(url,'_blank');
		 			       	}
		 				});
			    }, no: function(){
			    	layer.close();
			    }
			});
	   }
	 	 function goFindMaster(repairmanId){
	     	$("#repairmanId").val(repairmanId);
	     	var t1 = window.setInterval(findMasterAddress,180000); 
	     	$("#cInterval").val(t1);
	     }
	 	/**
	 	 * 定时刷新师傅位置
	 	 * */
	 	function findMasterAddress(){
	 		var map = new AMap.Map('container', {
	 		    resizeEnable: true
	 		});
	 		map.plugin('AMap.Geolocation', function() {
	 		    geolocation = new AMap.Geolocation({
	 		        enableHighAccuracy: true,//是否使用高精度定位，默认:true
	 		        timeout: 8000,          //超过10秒后停止定位，默认：无穷大
	 		        buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
	 		        zoomToAccuracy: true,      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
	 		        buttonPosition:'RB'
	 		    });
	 		    map.addControl(geolocation);
	 		    geolocation.getCurrentPosition();
	 		    AMap.event.addListener(geolocation, 'complete',successAlert );//返回定位信息
	 		    AMap.event.addListener(geolocation, 'error',failAlert );      //返回定位出错信息
	 		});
	 	} 

	 	function successAlert(data){
	 		$("#dateTime").val(new Date());
	 		var param = $("#blankShopInfo").serialize();
	 		$.ajax({
	 	        type: "POST",
	 	        async: false,
	 	        url: ctx+"/wapOrderService/findMasterAddress",  
	 	        data:param,
	 	        success: function(response){
	 	       	}
	 		});
	 	}
	 	function stopInterval(){
	 		window.clearInterval( $("#cInterval").val()); 
	 	}
	 	function failAlert(){
	 		alert("失败");
	 	}