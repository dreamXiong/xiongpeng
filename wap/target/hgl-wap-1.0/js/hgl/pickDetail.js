$(function() {
	$('.meter dt').on('click',function(){  //滑动效果//展开规格详情信息
		var dl = $(this).parent('dl');
		dl.siblings('.icon-caret-right').toggleClass('active');
		dl.find('dd').stop().slideToggle(500);
	});
	
	// 此方法为轮播插件，虽然代码都一样但是不能共用
	var swiper = new Swiper('.swiper-container', {
		//autoplay : 1000,
		pagination: '.swiper-pagination',
	});
	
	//分割显示单价
	$("#pickDiv input[name='text_pick']").each(function(i){
		var pickId = this.id.substring(3,this.id.length);      
		var arrayStr = splitPrice($("#outstockMoney"+pickId).val());     
		$("#inventoryMax"+pickId).text(arrayStr[0]+'.');
		$("#inventoryMin"+pickId).text(arrayStr[1]);
	});     
	
});

//单击加或者减操作
function addOrSubtract(obj,numType){
	validateOrderNum(obj);
	var currentNum = $("#num"+obj).val();
	currentNum = parseInt(currentNum);
	if(numType == 'add'){ //增
		if(currentNum < 999){    
			currentNum = currentNum + 1;
			$("#num"+obj).val(currentNum);
		}
	}else{ //减
		if(currentNum > 0){
			currentNum = currentNum - 1;
			$("#num"+obj).val(currentNum);
		}
	}
}

//输入数量校验
function validateOrderNum(obj){
	var currentNum = $("#num"+obj).val();
	if(currentNum == ''){
		$("#num"+obj).val(0);
		return;
	}
	var t=/^\d+(\.\d+)?$/; 
	if(t.test(currentNum) == false){
		$("#num"+obj).val(0);
		return;
	}
	if(currentNum.length >3){
		$("#num"+obj).val(0);
		return;
	}
	if(parseInt(currentNum) != currentNum){
		$("#num"+obj).val(0);
		return;
	}
}

//产品和详情却换
var clickFlag = false;
function switchs(one){
	$("#"+one).parent().addClass('active').siblings('li').removeClass('active');
	if(one == 'producta'){
		$("#detailDiv").hide();
		$("#productDiv").show();
	}else{
		$("#productDiv").hide();
		$("#detailDiv").show();
		if(clickFlag == false){
			clickFlag = true;
			$("img.lazy").lazyload({
				threshold : 20,         
				effect : "fadeIn", 
			});
		}
	}
}

//加入购物车
$(document).delegate(".added a",'click',function(){  //加入购物车
//	if($("#isLogin").text() == '0'){  //没有登录场景
//		$("#submitLogin").submit();
//		return;
//	}
	var shopIdInput = $("#shopIdInput").val(); //店铺id
	var shopIdInputDto = $("#shopIdInputDto").val(); //店铺id
	if(shopIdInput == shopIdInputDto){
		layer.open({
	 		content: '不能购买自己店铺的商品!',
	 		time: 2 //2秒后自动关闭
		});
       return;
    }
	var deleteIdsList = []; 
	var orderListNum = []; 
	var tipsFlag = false;
	$("#pickDiv input[name='text_pick']").each(function(i){
		var text_pickId = this.id;
		if(text_pickId != '' && this.value != '0'){  
			tipsFlag = true;
		    text_pickId = text_pickId.substring(3,text_pickId.length);      
		    if($("#hid"+text_pickId).val() != ''){ //记录反选的id
				deleteIdsList.push(text_pickId);
			}
			orderListNum.push(text_pickId + '=' + $("#num"+text_pickId).val());
		}
	});            
	if($("#orderServiceId").text() != '' && $("#orderServiceId").text() >0){//确认推送
		if($("#submitConfirmPushCart #orderServiceSubmitFlag").val() != '0'){  
			layer.open({
		 		content: '此材料已经推送过了!',
		 		time: 2 //2秒后自动关闭
			});
			return;
		}
		if(orderListNum != ''){    
			$("#submitConfirmPushCart #inventoryListNum").val(orderListNum);
			$("#submitConfirmPushCart #orderServiceSubmitFlag").val('1');
			$("#submitConfirmPushCart").submit();
		}else{
			layer.open({
		 		content: '请至少选择一个商品推送!',
		 		time: 2 //2秒后自动关闭
			});
		}
	}else{
		addShopCart(orderListNum,deleteIdsList,tipsFlag); //添加到购物车ajax
	}
});

//立即购买
$(document).delegate(".shopping a",'click',function(){  //立即购买
//	if($("#isLogin").text() == '0'){  //没有登录场景
//		$("#submitLogin").submit();
//		return;
//	}
	var deleteIdsList = []; 
	var orderListNum = []; 
	var shopIdInput = $("#shopIdInput").val(); //店铺id
	var shopIdInputDto = $("#shopIdInputDto").val(); //店铺id
	if(shopIdInput == shopIdInputDto){
		layer.open({
	 		content: '不能购买自己店铺的商品!',
	 		time: 2 //2秒后自动关闭
		});
       return;
    }
	$("#pickDiv input[name='text_pick']").each(function(i){  
		var text_pickId = this.id;
		if(text_pickId != '' && this.value != '0'){  
		    text_pickId = text_pickId.substring(3,text_pickId.length);
		    if($("#hid"+text_pickId).val() != ''){ //记录反选的id
				deleteIdsList.push(text_pickId);
			}  
			orderListNum.push(text_pickId + '=' + $("#num"+text_pickId).val());
		}
	});                 
	if(orderListNum != ''){
		$("#submitNowBuy #orderListNum").val(orderListNum);
		$("#submitNowBuy #deleteIdsList").val(deleteIdsList);
		$("#submitNowBuy").submit();
	}else{
		layer.open({
	 		content: '请至少选择一个商品购买!',
	 		time: 2 //2秒后自动关闭
		});
	}
});

//添加购物车的ajax
function addShopCart(orderListNum,deleteIdsList,tipsFlag){   
	if(orderListNum != ''){    
		$.ajax({  
	        type: "POST",
	        url: "../shoppingCar/addShoppingCart",
	        data: "orderListNum="+orderListNum+"&deleteIdsList="+deleteIdsList+"&random="+Math.random(),
	        success: function(response){
	        	if(response.isSuccess){
	        		for(var i=0;i<orderListNum.length;i++){
	    				var strs = orderListNum[i];
	    				var str = strs.split('=');
	    				$("#hid"+str[0]).val(str[1]);
	    			} 
	        	 	layer.open({
				 		content: '商品已成功添加到购物车',
				 		time: 2 //2秒后自动关闭
					}); 
	        	}else{    
//	        		if(response.isSuccess == undefined){                           
//	        			location.href = $("#loginUrl").text();    
//	        		}
	        	}
	        },
	        error: function() {
	        	
	        }   
	    });
	}else{
		if(tipsFlag){
			layer.open({
		 		content: '没有新增的产品加入到购物车!',
		 		time: 2 //2秒后自动关闭
			});
		}else{
			layer.open({
		 		content: '请至少选择一个商品加入购物车!',
		 		time: 2 //2秒后自动关闭
			});
		}
	}
}
