function nameHide(){
		$('.name-list').stop().slideUp(300)
	}
	$(function() {
		var timer= null;
		$('.name').hover(function(){
			$(this).find('.name-list').stop().slideDown(100);
			clearTimeout(timer)
		},function(){
			timer = setTimeout(nameHide,500);
		})
		$('.seek-class a').click(function(event) {
			$(this).addClass('active').siblings('a').removeClass('active')
		});
		$('.seek-nav .current').click(function(event) {
			$(this).addClass('active').siblings('.current').removeClass('active')
		});
		$('.referral-title li').click(function(){
			var index = $(this).index();
			$(this).addClass('active').siblings('li').removeClass('active');
			$('.referral-body div').eq(index).show().siblings('div').hide();
		})
		$('table tr:odd').css('background','#f9f9f9');
		//点击小图片，大图片切换
		$('.pd-botton-left li').click(function(event) {
			var imgSrc = $(this).children('img').attr('src');
			var bigImg = $('.pd-top-left').children('img');
			bigImg.attr('src',imgSrc).attr('jqimg',imgSrc);
			
			$(this).addClass('active').siblings('li').removeClass('active');
		});
		$('.pd-botton-left li').click(function(event) {
			$(this).addClass('active').siblings('li').removeClass('active');
		});
		$("img.lazy").lazyload({
			threshold : 200,
			effect : "fadeIn",
		});
		$(".jqzoom").jqueryzoom({
			xzoom: 360, //放大图的宽度(默认是 200)
			yzoom: 360, //放大图的高度(默认是 200)
			offset: 10, //离原图的距离(默认是 10)
			position: "right", //放大图的定位(默认是 "right")
			preload:1   
		});
	});
	
	$(function(){
		$(document).delegate("#pickTable input[name='rootCheckbox']",'click',function(){  //全选
			if($("#pickTable input[name='checkbox_pick']") != null && $("#pickTable input[name='checkbox_pick']").size() >0){
				$("#pickTable input[name='checkbox_pick']").prop("checked",this.checked); 
				if(this.checked == false){
					//$(".shopping i").html(goodsCount);
					$("#goodsCount").text('0');
					$("#goodsMoney").text('0.00');
				}else{
					var goodsCount = 0;
					$("#pickTable input[name='checkbox_pick']").each(function () {
						var orderNum = $("#num"+this.value).val();
						var orderCount = $("#count"+this.value).text();
						goodsCount = goodsCount + (orderNum*orderCount);
			        });
					var orderListNum = []; 
					getOrderNum(orderListNum);
					getGoodsMoney(goodsCount,orderListNum);
				}
			}
		}); 
		
		$(document).delegate("#pickTable input[name='checkbox_pick']",'click',function(){  //单个选
			checkedRoot();
			getGoodsInfo();
		}); 
		
		$(document).delegate("#addBetchCart",'click',function(){  //批量加入进货单
			var deleteIdsList = []; 
			var orderListNum = []; 
			var tipsFlag = false;
			var mid= $("#merchantId").val();
			$("#pickTable input[name='checkbox_pick']").each(function(i){
				if(this.checked == true){
					tipsFlag = true;
					var text_pickId = this.id;
					if(text_pickId != ''){
					    text_pickId = text_pickId.substring(5,text_pickId.length);
					    if($("#hid"+text_pickId).val() != $("#num"+text_pickId).val()){  //如果反选数量没有发生改变不需要添加
							if($("#hid"+text_pickId).val() != ''){ //记录反选的id
								deleteIdsList.push(text_pickId);
							}
							orderListNum.push(text_pickId + '=' + $("#num"+text_pickId).val());
						}
					}
				}
			});
			addShopCartAjax(orderListNum,deleteIdsList,$("#pickTable input[name='rootCheckbox']").val(),tipsFlag,mid); //单个商品添加到购物车ajax
		});
		
		//页面加载完后勾选已经购买的商品
		$("#pickTable input[name='checkbox_pick']").each(function () {
			var text_pickId = this.id;
			if(text_pickId != ''){
			    text_pickId = text_pickId.substring(5,text_pickId.length);
			    var hiddenNum = $("#hid"+text_pickId).val();
			    if(hiddenNum >0){
			    	this.checked = true;
			    }
			}
        });
		checkedRoot();
		getGoodsInfo();
	});
	
	//判断是否需要把全选按钮选中
	function checkedRoot(){
		var recodeCount = 0;
		$("#pickTable input[name='checkbox_pick']").each(function () {
			if(this.checked == false){
				if(recodeCount == 0){
				   recodeCount = 1;
				}
			}
        });
		if(recodeCount == 1){
			$("#pickTable input[name='rootCheckbox']").prop("checked",false); 
		}else{
			$("#pickTable input[name='rootCheckbox']").prop("checked",true); 
		}
	}
	
	//获取选购的商品
	function getGoodsInfo(){
		var goodsCount = 0;
		$("#pickTable input[name='checkbox_pick']").each(function () {
			if(this.checked == true){
				var orderNum = $("#num"+this.value).val();
				var orderCount = $("#count"+this.value).text();
				goodsCount = goodsCount + (orderNum*orderCount);
			}
        });
		var orderListNum = []; 
		getOrderNum(orderListNum);
		getGoodsMoney(goodsCount,orderListNum);
	}
	
	//获取订单数量
	function getOrderNum(orderListNum){
		$("#pickTable input[name='checkbox_pick']").each(function(i){
			if(this.checked == true){
				var text_pickId = this.id;
				if(text_pickId != ''){
				    text_pickId = text_pickId.substring(5,text_pickId.length);
					var num = $("#num"+text_pickId).val();
					var discount = ($("#discount"+text_pickId).text()*$("#count"+text_pickId).text());
					orderListNum.push(num+'='+discount); 
				}
			}
		});
	}
	
	//计算金额
	var firstFlag = true;
	function getGoodsMoney(goodsCount,orderListNum){
		if(orderListNum != '' && goodsCount != '' && parseInt(goodsCount) > 0){
			var goodsMoney = 0;
			for(var i=0;i<orderListNum.length;i++){
				var discounts = orderListNum[i];
				var discount = discounts.split('=');
				goodsMoney = goodsMoney + (discount[0] * discount[1]);
			}
			if(firstFlag){
				$("#hidGoodsCount").text(goodsCount);
				firstFlag = false;
			}
			//$(".shopping i").html(goodsCount);
			$("#goodsCount").text(goodsCount);
			$("#goodsMoney").text(toDecimal2(goodsMoney));
		}else{
			firstFlag = false;
			//$(".shopping i").html(goodsCount);
			$("#hidGoodsCount").text('0');
			$("#goodsCount").text('0');
			$("#goodsMoney").text('0.00');
		}
	}
	
	/**
	* 点击加或减实现订单数量的操作
	*/
	function addOrSubtract(obj,numType,oneboxCount){
		if(checkOrderNum(obj) == true){
			var currentNum = $("#num"+obj).val();
			currentNum = parseInt(currentNum);
			if(numType == 'add'){ //增
				currentNum = currentNum + 1;
				$("#num"+obj).val(currentNum);
				if($("#check"+obj).is(':checked') == true){
					getGoodsInfo();
				}
			}else{ //减
				if(currentNum > 1){
					currentNum = currentNum - 1;
					$("#num"+obj).val(currentNum);
					if($("#check"+obj).is(':checked') == true){
						getGoodsInfo();
					}
				}
			}
		}
	}
	
	//校验输入的购买数
	function checkOrderNum(obj){
		var currentNum = $("#num"+obj).val();
		if(currentNum == '' || currentNum <1){
			$("#num"+obj).val(1);
			getGoodsInfo();
			return false;
		}
		var t=/^\d+(\.\d+)?$/; 
		if(t.test(currentNum) == false){
			$("#num"+obj).val(1);
			getGoodsInfo();
			return false;
		}
		if(currentNum.length >3){
			$("#num"+obj).val(1);
			getGoodsInfo();
			return false;
		}
		if(parseInt(currentNum) != currentNum){
			$("#num"+obj).val(1);
			calculateMoney('true',true);
			return false;
		}
		return true;
	}
	
	//校验订单数量
	function validateOrderNum(obj,oneboxCount){
		if(checkOrderNum(obj) == true){
			if($("#check"+obj).is(':checked') == true){
				getGoodsInfo();
			}
		}
	}
	
	//添加购物车
	function addCart(obj,st,mid){
		var checkObj = $("#check"+obj);
		if(checkObj.is(':checked') == false){  //默认选中
		   checkObj.prop("checked",true); 
		   checkedRoot();
		   getGoodsInfo();
		}
		if($("#hid"+obj).val() != $("#num"+obj).val()){  //如果反选数量没有发生改变不需要添加
			var deleteIdsList = []; 
			var orderListNum = []; 
			if($("#hid"+obj).val() != ''){ //记录反选的id
				deleteIdsList.push(obj);
			}
			orderListNum.push(checkObj.val() + '=' + $("#num"+obj).val());
			addShopCartAjax(orderListNum,deleteIdsList,st,true,mid); //单个商品添加到购物车ajax
		}else{
			showAddCartTips('没有新增的产品加入到购物车!');
		}
	}
	
	//添加到购物车的ajax请求
	function addShopCartAjax(orderListNum,deleteIdsList,shoppingType,tipsFlag,mid){   
		if(orderListNum != '' && shoppingType != ''){      
			$.ajax({
		        type: "POST",
		        url: "../shoppingCar/addShoppingCart",
		        data: "orderListNum="+orderListNum+"&deleteIdsList="+deleteIdsList+"&shoppingType="+shoppingType+"&merchantsId="+$("#hiddenMerchantsId").text()+"&brandId="+$("#hiddenBrandId").text()+"&random="+Math.random(),
		        success: function(response){
		        	if(response.isSuccess){
		        		for(var i=0;i<orderListNum.length;i++){
		    				var strs = orderListNum[i];
		    				var str = strs.split('=');
		    				$("#hid"+str[0]).val(str[1]);
		    			}
		        		$("#hidGoodsCount").text($("#goodsCount").text());
		        		$(".shopping i").text(response.cartNumber);
		        		
		        		if(shoppingType == 262){
		        			if(mid!=null&& mid>0){
		        				pickMerchants(mid,shoppingType);
		        			}
		        		}
		        		showAddCartTips('恭喜，该商品成功加入购物车!');
		        	}else{
		        		showAddCartTips('加入购物车失败!');
		        	}
		        },
		        error: function() {
		        	
		        }   
		    });
		}else{
			if(tipsFlag){
				showAddCartTips('没有新增的产品加入到购物车!');
			}else{
				showAddCartTips('请至少选择一个商品加入购物车!');
			}
		}
	}
	
	//加入购物车提示
	function showAddCartTips(tipsInfo){
		$('.myModal #modalSpan').html(tipsInfo);
		$('.myModal').fadeIn(500);
		timer = setTimeout(function(){
			$('.myModal').fadeOut(500);
		},2000);
	}
	
	
	
	function pickMerchants(mid,st){
		$.ajax({
	        type: "POST",
	        url : ctx+'/pick/pickMerchants',
	        data: "mid="+mid+"&st="+st,
	        success: function(response){
	          // alert(response);          
	        	$("#merchants").html(response);
	        },
	       
	    });
	}