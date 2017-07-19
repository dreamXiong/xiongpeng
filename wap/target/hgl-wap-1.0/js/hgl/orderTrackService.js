/*删除材料*/
function deleteOrderServiceDetail(orderServiceDetailId){
	layer.open({
		content:'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;确认要删除该材料吗？&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;',
		btn:['确定','取消'],      
		yes:function(){
			$.ajax({
				type:"post",
				url:"doDeleteOrderServiceDetail",
				data:{"id":orderServiceDetailId},
				success:function(data){
					if(data=="true"){
						$("#"+orderServiceDetailId).hide();
						layer.close();   
						layer.open({
							content: '已成功删除',
							time: 1 //2秒后自动关闭
						});  
					}
				}
			});
		},
		no: function(){
			layer.close();
		}
	});
}
		
/*开始服务*/
function startServer(id){
	var orderStatus = $("#operateBtn").val();
	if(orderStatus==802){	
		$.ajax({                       
			type: "POST",
			url: "../wapOrderService/doOrderService",
			data: "id="+id,
			async:false,
			success: function(response){     
				if(response.errcode =="0"){
					$("#operateBtn").val(804);
					$("#pushBtn").val(804);  
					window.location.href = window.location.href;
				}else if(response.errcode =="1"){
					layer.close();
					layer.open({
						content: '失败！',
						time: 1 //2秒后自动关闭
					});  
				}
			},
			  
		});
	}else if(orderStatus == 806){
		$.ajax({                       
			type: "POST",
			url: ctx+"/wapOrderService/doAltService",
			data: "id="+id,
			async:false,
			success: function(response){     
				if(response.errcode =="0"){
					window.location.href = window.location.href;
				}else if(response.errcode =="1"){
					layer.close();   
					layer.open({
						content: '失败！',
						time: 1 //2秒后自动关闭
					});  
				}
			},
			  
		});
	}else{	
		layer.open({
			content:'服务已经被开启',
			time: 1 //2秒后自动关闭
		});
	}
}

//推送材料
function pushShoppingCart(orderServiceId,orderStatus){
	
	var orderStatus = $("#pushBtn").val();
	if(orderStatus==804){
		location.href="../wapOrderService/pushShoppingCart?orderServiceId="+orderServiceId;
	}else{
		layer.open({
			content:'不能推送材料',
			time:1 //2秒后自动关闭
		}); 
	}	
}
		
//确认推送材料
function pushCart(id,repairmanId){	
	var orderStatus = $("#orderStatus"+id).val();
	if(orderStatus<808 ||orderStatus==820){
		layer.open({
			content:'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;确认材料吗？&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;',
			btn:['确定','取消'],      
			yes:function(){
				var orderListNum = [];
				orderListNum.push($("#cartIds"+id).val());
			    $("#pushCartForm #repairmanId").val(repairmanId);
			    $("#pushCartForm #orderListNum").val(orderListNum);
			    $("#pushCartForm").submit();
			    layer.close(); 
			    layer.open({
					content:'确认成功',
					time: 2 //2秒后自动关闭
				}); 	
			},
			no: function(){
				layer.close();
			}
		});
	}else{
		layer.open({
			content:'材料已确认',
			time: 2 //2秒后自动关闭
		}); 
		return;
	}
	
}