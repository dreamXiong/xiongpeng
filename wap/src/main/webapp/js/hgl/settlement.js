// 为编辑收获地址板块添加高度
(function compileH(){
	var winH = $(window).height();
	var headH = $('.compile .verify-head').height();
	$('.compile-body').css('height',winH-headH +'px');
})();

$(function() {
	/* var arr = ['送货（您所在区域不提供送货服务）','送货（该店不提供送货服务）',
   '送货（超出8公里不提供送货服务）','送货（加收送货费10元）',
   '送货（满300包邮）','送货（免费送货）','送货（送货费与卖家协商）']; */

	$(document).delegate('dl dt','click',function(){
		$(this).siblings('dd').slideToggle(100);
	});
	
	/* setInterval(function(){
	$('#text').html(arr[Math.floor(Math.random()*arr.length)]);
	},3000); */
	
	$(document).delegate('input[type="radio"]','click',function(event) {
		$(this).addClass('on').parent('label').siblings('label').children('input').removeClass('on');
	});
	
	$('.varify-choose dl').delegate('input', 'click', function(event) {
		$(this).parents('dd').slideUp();
		var text = $(this).siblings('span').text();
		$(this).parents('dl').find('.reap').text(text);
	});
	
	$('li').delegate('.addr-state', 'click', function(event) {
		$('.reap-addr li').removeClass('unique');
		$(this).parents('li').addClass('unique');
		backT(this);
	});
	
	//点击收货地址  
//	$('.verify-info').click(function(event) {
//		$.ajax({
//	        type: "POST",
//	        url: "showMyAddress",
//	        data: "addressId="+$("#mainAddressId").val()+"&random="+Math.random(),
//	        success: function(response){
//	        	$("#addressListDiv").html(response);
//	        	revamp();
//	        }
//	    });             
//	});
	
	$('.back').click(function(event) {
		backT(this);
	});

	$(document).delegate('#submitOrder','click',function(event) {  //提交订单
		if($("#submitOrderForm #isSubmit").val() == '0'){     
			var addressId = $("#mainAddressId").val();
			var recipientInput = $("#recipientInput").val();
			var phoneInput = $("#phoneInput").val();
			if(recipientInput == undefined || recipientInput == ''){
			 	layer.open({
			 		content: '请填写收货人',
			 		time: 2
				});  
				return;
			}
			if(phoneInput == undefined || phoneInput == ''){
			 	layer.open({
			 		content: '请填写手机号码',
			 		time: 2
				});  
				return;
			}
			if(checkMobile(phoneInput) || isTel(phoneInput)){
			}else{
				layer.open({
			 		content: '手机号码格式不正确',
			 		time: 2
				}); 
				return;
			}
			if(addressId == undefined || addressId == ''){
			 	layer.open({
			 		content: '请选择收货地址',
			 		time: 2
				});  
				return;
			}
			var payTypeList = []; 
			var goodsTypeList = []; 
			var cartIdList = []; 
			var buyMessageList = []; 
			var distributionList = [];
			var checkFlag = false;                                                       
			if($(".clearfix.details-head input[name='shopCheck']").size() >1){
				layer.open({
			 		content: '只能购买一家店铺的商品',
			 		time: 2
				});  
				return;
			}
		    $(".clearfix.details-head input[name='shopCheck']").each(function () {         
			   $(".details-lists input[name='shop"+this.value+"']").each(function () {        
					cartIdList.push(this.value);
		       });
			   
			   //收货方式
			   var goodsTypeValue = $("input[name='goodsType"+this.value+"']:checked").val(); 
			   if(goodsTypeValue == undefined || goodsTypeValue == ''){
				   layer.open({
				 		content: '请选择收货方式',
				 		time: 2
				   });  
				   checkFlag = true;
				   return false;
			   }
			   goodsTypeList.push(this.value + '=' + goodsTypeValue); //收货方式
			   
			   //支付方式
			   var payTypeValue = $("input[name='pay"+this.value+"']:checked").val(); 
			   if(payTypeValue == undefined || payTypeValue == ''){
				   layer.open({
				 		content: '请选择支付方式',
				 		time: 2
				   });  
				   checkFlag = true;
				   return false;
			   }
			   payTypeList.push(this.value + '=' + payTypeValue); //支付方式
			   buyMessageList.push(this.value + '=' + $("#buyMessage"+this.value).val()); //买家留言
			   distributionList.push(this.value + '=' + $("input[name='discount"+this.value+"']:checked").val()); //优惠折扣
		    });
		    
		    //判断是否校验失败
		    if(checkFlag == true){
		    	return;
		    }
		    
			if(cartIdList != '' && cartIdList.length >0){
				$("#submitOrderForm #cartIdList").val(cartIdList);
				$("#submitOrderForm #buyMessageList").val(buyMessageList);
				$("#submitOrderForm #addressId").val(addressId);
				$("#submitOrderForm #payTypeList").val(payTypeList);
				$("#submitOrderForm #goodsTypeList").val(goodsTypeList); 
				$("#submitOrderForm #distributionList").val(distributionList); 
				$("#submitOrderForm #recipient").val(recipientInput); 
				$("#submitOrderForm #phone").val(phoneInput); 
				$("#submitOrderForm #isSubmit").val('1');  
				$('#submitOrderForm').submit();
			}
		}else{
			layer.open({
		 		content: '订单已提交',
		 		time: 2
			});  
		}
	});
	
	calculateMoney();
});

// 显示收获地址
function revamp(){
	//var winW = $(window).width();
	$('.manage').css({
		transform:'translate(0,0)',
	});
	$('body').addClass('out');
}

// 返回到结算页面
function backT(obj){
	$(obj).parents('section').css({
		transform:'translate(100%,0)',
	});
	$('body').removeClass('out');
}

// 计算数量和金额
function calculateMoney(){
   var totalCount = 0;
   var totalMoney = 0;
   var freightMoney = 0;
   var arrayStr = [];
   $(".clearfix.details-head input[name='shopCheck']").each(function () {      
	   var goodsCount = 0;
	   var goodsMoney = 0;
	   $(".details-lists input[name='shop"+this.value+"']").each(function () {   
			var orderNum = $("#num"+this.value).val();
			orderNum = parseInt(orderNum);     
			var discountPrice = $("#discounts"+this.value).val();
			totalCount = (totalCount + orderNum);  				//计算单个品牌的商品合计   
			totalMoney = totalMoney + (discountPrice*orderNum); //计算单个品牌的商品合计金额
			goodsCount = (goodsCount + orderNum);  				//计算单个品牌的商品合计   
			goodsMoney = goodsMoney + (discountPrice*orderNum); //计算单个品牌的商品合计金额
			arrayStr = splitPrice(discountPrice);
			$("#inventoryMax"+this.value).text(arrayStr[0]+'.');
			$("#inventoryMin"+this.value).text(arrayStr[1]);
        });
	   $("#goodsCount"+this.value).text(goodsCount);
	   var freightValue = $("#freight"+this.value).text();
	   freightMoney = freightMoney + parseFloat(freightValue == '' ? 0.0 : freightValue);
	   goodsMoney = goodsMoney + freightMoney;
	   var sumMoney = toDecimal2(goodsMoney);
	   arrayStr = splitPrice(sumMoney);
	   $("#shopMax"+this.value).text(arrayStr[0]+'.');
	   $("#shopMin"+this.value).text(arrayStr[1]);
   });
   $("#totalCount").text(totalCount);
   var totalPrice = toDecimal2(totalMoney);
   arrayStr = splitPrice(totalPrice); 
   $("#totalMoneyMax").text(arrayStr[0]+'.');
   $("#totalMoneyMin").text(arrayStr[1]);
   $("#totalFreightMoney").text(toDecimal2(freightMoney));
   totalMoney = totalMoney + freightMoney; 
   $("#totalMoney").text(toDecimal2(totalMoney));
}

//设置收货方式
function receivingWay(type){
   $(".clearfix.details-head input[name='shopCheck']").each(function () {
	   if(type == '0'){ //自提
			$("#freight"+this.value).text('0.0');
	   }else{  //送货
			$("#freight"+this.value).text($("#hidFreight"+this.value).text());
	   }
   });
   calculateMoney();
}

//跳转产品详请
function openProductDetail(id){
	$("#productDetailForm #id").val(id);
	$("#productDetailForm").submit();
}