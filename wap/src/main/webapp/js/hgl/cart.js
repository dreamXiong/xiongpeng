$(function() {
	$('dl dt').click(function(){
		$(this).siblings('dd').slideToggle(100);
	});
	
	$(document).delegate("#checkbox_all",'click',function(){  //全选
		 var checke = this.checked;
		 $(".clearfix input[name='shopCheck']").prop("checked",checke); 
		 $(".clearfix input[name='shopCheck']").each(function(){         
			 $(".clearfix input[name='shop"+this.value+"']").prop("checked",checke); 
		 });
		 if(checke == false){
			 $("#totalCount").text('0');
			 $("#totalMoneyMax").text('00.');
			 $("#totalMoneyMin").text('00');
		 }else{
			 calculateMoney();
		 }
	});
	
    $(document).delegate("#settlementButton",'click',function(){  //去结算
    	var orderListNum = []; 
    	var cartIdsList = []; 
    	var checkFlag = "";
    	var shopIdInput = $("#shopIdInput").val(); //店铺id
	    $(".clearfix input[name='shopCheck']").each(function () {
	       var pushFlagValue = '';
	       var shopId = this.value;
	 	   $(".clearfix input[name='shop"+this.value+"']").each(function () {           
	 			if(this.checked == true){   
	 				if(shopIdInput == shopId){
	 			       checkFlag = '不能购买自己店铺的商品!';
	 			       return false;
	 			    }
	 				var orderNum = $("#num"+this.value).val();
	 				var orderCount = $("#hid"+this.value).val();
					var cartId = $("#check"+this.value).val();
					if(orderNum != orderCount){ 
						orderListNum.push(cartId + '=' + orderNum);
					}
					cartIdsList.push(cartId);
					if(pushFlagValue == ''){
						pushFlagValue = $("#pushFlag"+this.value).val();
					}else{
						if(pushFlagValue != $("#pushFlag"+this.value).val()){
							checkFlag = '推送材料与购买的材料不能同时结算!';
							return false;
						}
					}
	 			}  
	         });
	    });
	   if(checkFlag != ""){
		   layer.open({
		 		content: checkFlag,
		 		time: 3 //2秒后自动关闭
			}); 
		   return;
	   }
	   if(cartIdsList != '' && cartIdsList.length >0){
		   $("#orderListNum").val(orderListNum);
		   $("#cartIdsList").val(cartIdsList);
		   $('#settlementForm').submit();
	   }else{
		   layer.open({
		 		content: '没有需要去结算的商品,请添加!',
		 		time: 2 //2秒后自动关闭
			}); 
	   }
    });
	
    //分割显示单个库存的价格
    splitShowPrice();
    
	var deleteIdsList = []; 
	$("input[name='failureCart']").each(function(){
		deleteIdsList.push(this.value);
	});
	if(deleteIdsList.length > 0){  
		$("#failureCartDiv").show();
	}
});

//判断是否需要把全选按钮选中
function singleChecked(name){
	var recodeCount = 0;
	$(".clearfix input[name='shop"+name+"']").each(function () {
		if(this.checked == false){
			if(recodeCount == 0){
			   recodeCount = 1;
			}
		}
    });
	if(recodeCount == 1){
		$(".clearfix #s"+name).prop("checked",false); 
	}else{
		$(".clearfix #s"+name).prop("checked",true); 
	}
	futureChecked();
	calculateMoney();
}

//是否把全选 选中
function futureChecked(){
	var recodeCount = 0;
	$(".clearfix input[name='shopCheck']").each(function(){
		if(this.checked == false){
			if(recodeCount == 0){
			   recodeCount = 1;
			}
		}
	 });
	if(recodeCount == 1){
		$("#checkbox_all").prop("checked",false); 
	}else{
		$("#checkbox_all").prop("checked",true); 
	}
}

//单个商品的全选
function singleAllChecked(name){
	 var checked = $(".clearfix #s"+name).is(':checked');
	 $(".clearfix input[name='shop"+name+"']").prop("checked",checked); 
	 futureChecked();
	 calculateMoney();
}

//计算数量和金额
function calculateMoney(){
   var totalCount = 0;
   var totalMoney = 0;
   $(".clearfix input[name='shopCheck']").each(function () {         
	   $(".clearfix input[name='shop"+this.value+"']").each(function () {        
			if(this.checked == true){   
				var orderNum = $("#num"+this.value).val();
				orderNum = parseInt(orderNum);     
				var discountPrice = $("#discounts"+this.value).val();
				totalCount = (totalCount + orderNum);  				//计算单个品牌的商品合计   
				totalMoney = totalMoney + (discountPrice*orderNum); //计算单个品牌的商品合计金额
			}
        });
   });
   $("#totalCount").text(totalCount);
   var totalPrice = toDecimal2(totalMoney);
   var arrayStr = splitPrice(totalPrice); 
   $("#totalMoneyMax").text(arrayStr[0]+'.');
   $("#totalMoneyMin").text(arrayStr[1]);
}

//分割显示单个价格
function splitShowPrice(){
   $(".clearfix input[name='shopCheck']").each(function () {         
	   $(".clearfix input[name='shop"+this.value+"']").each(function () {        
			var discountPrice = $("#discounts"+this.value).val();
			var arrayStr = splitPrice(discountPrice);     
			$("#cartMax"+this.value).text(arrayStr[0]+'.');
			$("#cartMin"+this.value).text(arrayStr[1]);
        });
   });
}

//单击加或者减操作
function addOrSubtract(obj,numType){
	if(checkOrderNum(obj) == true){
		var currentNum = $("#numText"+obj).val();
		currentNum = parseInt(currentNum);
		if(numType == 'add'){ //增
			if(currentNum < 999){
				currentNum = currentNum + 1;
				$("#numText"+obj).val(currentNum);
				$("#num"+obj).val(currentNum);     
			}
		}else{ //减
			if(currentNum > 1){
				currentNum = currentNum - 1;
				$("#numText"+obj).val(currentNum);
				$("#num"+obj).val(currentNum);     
			}
		}
		if($("#check"+obj).is(':checked') == true){
			calculateMoney();
		}
	}
}

//检查购买数量
function checkOrderNum(obj){
	var currentNum = $("#numText"+obj).val();
	if(currentNum == '' || currentNum == '0'){             
		$("#numText"+obj).val(1);
		$("#num"+obj).val(1);
		calculateMoney();
		return false;
	}
	var t=/^\d+(\.\d+)?$/; 
	if(t.test(currentNum) == false){
		$("#numText"+obj).val(1);
		$("#num"+obj).val(1);
		calculateMoney();
		return false;
	}
	if(currentNum.length >3){
		$("#numText"+obj).val(1);
		$("#num"+obj).val(1);
		calculateMoney();
		return false;
	}
	if(parseInt(currentNum) != currentNum){
		$("#numText"+obj).val(1);
		$("#num"+obj).val(1);
		calculateMoney();
		return false;
	}
	return true;
}

//输入数量校验
function validateOrderNum(obj){
	if(checkOrderNum(obj) == true){
		$("#num"+obj).val($("#numText"+obj).val());    
		if($("#check"+obj).is(':checked') == true){        
			calculateMoney();    
		}
	}
}

//删除
function removeCart(cartId,shopId){  
	_this=$("#delete"+cartId);     
	layer.open({
		// title: '提示',    
		 content:'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;确认要删除这个产品吗？&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;',
		 btn:['确定','取消'],      
		 yes:function(){
				$.ajax({                       
			        type: "POST",
			        url: "../shoppingCar/deleteShoppingCart",
			        data: "deleteId="+cartId+"&random="+Math.random(),
			        success: function(response){     
			        	//删除节点
			        	var itemLength = $(".shopping-lists.item"+shopId).length;
						if(itemLength > 1){          
							_this.parents('.shopping-lists').remove();
						}else{
							_this.parents('.shopping-lists').parents('.cart-shopping').remove();
						}  
						//判断是否购物车是空的
						if($("#cartListCountDiv").text().trim() != ''){
							calculateMoney();  //重新计算数量与价格  
						}else{
							$("#cartInfoDiv").hide();
							$("#cartInfoFooter").hide();
							$("#cartNullDiv").show();
						}
					 	layer.close();   
					 	layer.open({
					 		content: '已成功删除',
					 		time: 1 //2秒后自动关闭
						});  
			        },
			        error: function() {
			        	
			        }   
			    });
		 },
		 no: function(){
			 layer.close();
		 }
	});
}

//删除失效的产品
function removeFailureCart(){  
	var deleteIdsList = []; 
	$("input[name='failureCart']").each(function(){
		deleteIdsList.push(this.value);
	});
	if(deleteIdsList.length > 0){  
		layer.open({
			content:'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;确认要删除失效产品吗？&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;',
			btn:['确定','取消'],      
			yes:function(){
				$.ajax({                       
					type: "POST",
					url: "../shoppingCar/deleteFailureShoppingCart",
					data: "deleteIds="+deleteIdsList+"&random="+Math.random(),
					success: function(response){     
						if(response.isSuccess == true){
							layer.close();   
							layer.open({
								content: '已成功删除',
								time: 1 //2秒后自动关闭
							});  
							window.location.href = window.location.href;
						}
					},
					error: function() {
						
					}   
				});
			},
			no: function(){
				layer.close();
			}
		});
	}
}

//跳转产品详请
function openProductDetail(id,position){
	$("#productDetailForm #id").val(id);
	$("#productDetailForm #distance").val(position);
	$("#productDetailForm").submit();
}