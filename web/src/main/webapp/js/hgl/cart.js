	$(function() {
		$('.list-title').click(function(event) {
			$(this).addClass('active').siblings('.list-title').removeClass('active');
		});
		
		var tr_len=$('tbody tr').length;
		$('.num,.sum').attr('rowspan',tr_len);
		$(document).delegate(".shrink",'click',function(){
			var table = $(this).parents('.chat-title').siblings('.table');
			$(this).toggleClass('out');
			table.slideToggle(500);
		});
		
		//弹框
	    $("#dialog1").dialog({            
		   title:'提示', //弹出层的标题
		   autoOpen: false,		//禁止自己弹出
		   resizable: false,		//禁止弹出层缩放
		   draggable :true,		//禁止拖动
		   modal: true,			//是否有模态框
		   width:'340',     		//设置宽高
		   closeText:'关闭',			//closetitle
		   buttons:{         		//创建btn
		      	 关闭:function(){       //btn的回调函数
		           $(this).dialog("close");
		       },
		      	 确定:function(){
		      		 var tabId = $("#tabId").text();
		      		 var deleteGoodsList = []; 
		      		 var cartIds = $("#hiddenCartId").text();
		      		 if(cartIds == ''){
		      			 $(".chart-main table").each(function(){
			    			 $(".chart-main table input[name='"+this.id+"']").each(function(){
			    				if(this.checked == true){
			    					var cartId = $(".chart-main table #hidcart"+this.value).val();
			    					deleteGoodsList.push(cartId);
			    				}
			    			 });
			    		});
		      		 }else{
		      			$("#hiddenCartNumber").text($("#hiddenOneboxCount").text());
		      			deleteGoodsList.push(cartIds);
		      		 }
		      		$("#hiddenCartId").text('');
		      		$("#hiddenOneboxCount").text('');
		    		deleteShoppingCart(deleteGoodsList,tabId);
		    		$(this).dialog("close");
		       }
		   }
		 });

	    $("#dialog2").dialog({            
			   title:'提示', //弹出层的标题
			   autoOpen: false,		//禁止自己弹出
			   resizable: false,		//禁止弹出层缩放
			   draggable :true,		//禁止拖动
			   modal: true,			//是否有模态框
			   width:'340',     		//设置宽高
			   closeText:'关闭',			//closetitle
			   buttons:{         		//创建btn
			      	 确定:function(){       //btn的回调函数
			           $(this).dialog("close");
			       }
			   }
		});
	    
	    $("#dialog3").dialog({            
			   title:'提示', //弹出层的标题
			   autoOpen: false,		//禁止自己弹出
			   resizable: false,		//禁止弹出层缩放
			   draggable :true,		//禁止拖动
			   modal: true,			//是否有模态框
			   width:'340',     		//设置宽高
			   closeText:'关闭',			//closetitle
			   buttons:{         		//创建btn
			      	 确定:function(){       //btn的回调函数
			           $(this).dialog("close");
			       }
			   }
		});
	    
	    $("#dialog4").dialog({            
			   title:'提示', //弹出层的标题
			   autoOpen: false,		//禁止自己弹出
			   resizable: false,		//禁止弹出层缩放
			   draggable :true,		//禁止拖动
			   modal: true,			//是否有模态框
			   width:'340',     		//设置宽高
			   closeText:'关闭',			//closetitle
			   buttons:{         		//创建btn
			      	 取消:function(){       //btn的回调函数
			           $(this).dialog("close");
			       },
			       继续:function(){
			    	   $('#settlementForm').submit();
			        	 $(this).dialog("close");
			          },
			   }
		});
	    
		$(document).delegate("#check-onlys",'click',function(){  //全选
			 var checke = this.checked;
			 $(".chart-main table").each(function () {
				 $(".chart-main table input[name='rootCheckbox"+this.id+"']").prop("checked",checke); 
				 $(".chart-main table input[name='"+this.id+"']").prop("checked",checke); 
			 });
			 if(checke == false){
				 $("#totalCount").text('0');
				 $("#totalMoney").text('0.00');
			 }else{
				 calculateMoney('false',true);
			 }
		}); 
		
		$(document).delegate(".chart-nav div",'click',function(){  //tab切换
			tabShowOrHide(this.id);
		}); 
		
	    $('#delShop1').click(function(){  //删除
	    	var deleteFlag = false;
		   $(".chart-main table").each(function () {
			   $(".chart-main table input[name='"+this.id+"']").each(function () {
					if(this.checked == true){
						deleteFlag = true;
					}
		        });
		   });
		   if(deleteFlag){
			   $('#dialog1').dialog('open');
		   }else{
			   $('#dialog2').dialog('open');
		   }
	    });
	    
		//var isCartSettlement= true;//是否可以去结算
		function isSettlement(cartIdsList){
			//alert(cartIdsList);
		    $.ajax({
		        type: "POST",
		        url:ctx+'/shoppingCar/isSettlement?cartIdsList='+cartIdsList,
		        success: function(response){
		           if(response==null || response.errcode == '0'){
		        	   $("#cartIds").val(null);
		        	   $('#settlementForm').submit();
		           }else{
		        	   $("#cartIds").val(response.cartIds);
		        	   $("#msg").text(response.msg);
		        	   $('#dialog4').dialog('open');
		        	 //  return;
		           }
		        }
		    });
		};
	    
	    $(document).delegate("#settlementButton",'click',function(){  //去结算
	    	var orderListNum = []; 
	    	var cartIdsList = []; 
		    $(".chart-main table").each(function(){
			   $(".chart-main table input[name='"+this.id+"']").each(function(){
					if(this.checked == true){
						var orderNum = $("#num"+this.value).val();
						var orderCount = $("#hid"+this.value).val();
						var cartId = $("#hidcart"+this.value).val();
						if(orderNum != orderCount){ 
							orderListNum.push(cartId + '=' + orderNum);
						}
						cartIdsList.push(cartId);
					}
		        });
		    });
		   if(cartIdsList != '' && cartIdsList.length >0){
			   $("#cartNumber").val($("#hiddenCartNumber").text());
			   $("#orderListNum").val(orderListNum);
			   $("#cartIdsList").val(cartIdsList);
			   isSettlement(cartIdsList);
			   //alert(isCartSettlement+"-1--");
//			   if(isCartSettlement){
//				 // alert(isCartSettlement+"--2-");
//				   $('#settlementForm').submit();
//			   }else{
//				   $('#dialog4').dialog('open');
//			   }
		   }else{
			   //alert("没有需要去结算的商品,请添加!");
			   $('#dialog3').dialog('open');
		   }
	    });
	    
	    calculateMoney('true',false);  //页面加载完后计算金额
	});

	
	
	//处理tab的隐藏和显示
   function tabShowOrHide(id){
	    $("#tabId").text(id);
	    $(".chart-main .chart-table").hide();
		if(id == 'receipt'){
			$(".chart-main #rt").show();
		}else{
			$(".chart-main #bran"+id).show();
		}
		$("#"+id).addClass('active').siblings('div').removeClass('active');
   } 
 	
	//点击加或减实现订单数量的操作
	function addOrSubtract(obj,numType,productId){
		if(checkOrderNum(obj) == true){
			var currentNum = $("#num"+obj).val();
			currentNum = parseInt(currentNum);
			if(numType == 'add'){ //增
				currentNum = currentNum + 1;
				$("#num"+obj).val(currentNum);
				if($("#check"+obj).is(':checked') == true){
					calculateMoney('true',true);
				}
			}else{ //减
				if(currentNum > 1){
					currentNum = currentNum - 1;
					$("#num"+obj).val(currentNum);
					if($("#check"+obj).is(':checked') == true){
						calculateMoney('true',true);
					}
				}
			}
		}
	}
	
	//计算金额和件数
	function calculateMoney(isCalculateTotal,checkedFlag){
		   var totalCount = 0;
		   var totalMoney = 0;
		   $(".chart-main table").each(function () {
			   var goodsCount = 0;
			   var goodsMoney = 0;
			   $(".chart-main table input[name='"+this.id+"']").each(function () {
					if(this.checked == checkedFlag){
						var orderNum = $("#num"+this.value).val();
						var orderCount = $("#count"+this.value).text();
						var discountPrice = $("#discounts"+this.value).text();
						goodsCount = goodsCount + (orderNum*orderCount);  	//计算单个品牌的商品合计
						goodsMoney = goodsMoney + (discountPrice*(orderNum*orderCount)); //计算单个品牌的商品合计金额
						$("#subtotal"+this.value).text(toDecimal2((discountPrice*(orderNum*orderCount))));
					}
		        });
			   var sumMoney = toDecimal2(goodsMoney);
			   totalCount = (totalCount+goodsCount);	//计算总品牌的商品合计
			   totalMoney = (totalMoney+goodsMoney);	//计算总品牌的商品合计金额
			  
			   if(isCalculateTotal == 'true'){
			   	  $(".chart-main table #numCount"+this.id).text(goodsCount);
			   	  $(".chart-main table #sumMoney"+this.id).text(sumMoney);
			   }
			   if(checkedFlag == true){
				   $(".chart-main #sing"+this.id).text(sumMoney);
			   }
		   });
		   $("#hiddenCartNumber").text(totalCount);
		  // $(".shopping i").html(totalCount);
		   if(checkedFlag == true){
			   $("#totalCount").text(totalCount);
			   $("#totalMoney").text(toDecimal2(totalMoney));
		   }
	}

	//判断是否需要把全选按钮选中
	function singleChecked(name){
		var recodeCount = 0;
		$(".chart-main table input[name='"+name+"']").each(function () {
			if(this.checked == false){
				if(recodeCount == 0){
				   recodeCount = 1;
				}
			}
        });
		if(recodeCount == 1){
			$(".chart-main table input[name='rootCheckbox"+name+"']").prop("checked",false); 
		}else{
			$(".chart-main table input[name='rootCheckbox"+name+"']").prop("checked",true); 
		}
		futureChecked();
		calculateMoney('false',true);
	}
	
	//是否把全选 选中
	function futureChecked(){
		var recodeCount = 0;
		 $(".chart-main table").each(function(){
			 $(".chart-main table input[name='"+this.id+"']").each(function(){
				if(this.checked == false){
					if(recodeCount == 0){
					   recodeCount = 1;
					}
				}
			 });
		});
		if(recodeCount == 1){
			$("#check-onlys").prop("checked",false); 
		}else{
			$("#check-onlys").prop("checked",true); 
		}
	}
	
	//单个商品的全选
	function singleAllChecked(name){
		 var checked = $(".chart-main table input[name='rootCheckbox"+name+"']").is(':checked');
		 $(".chart-main table input[name='"+name+"']").prop("checked",checked); 
		 futureChecked();
		 calculateMoney('false',true);
	}
	
	//校验输入的购买数
	function checkOrderNum(obj){
		var currentNum = $("#num"+obj).val();
		if(currentNum == '' || currentNum <1){
			$("#num"+obj).val(1);
			calculateMoney('true',true);
			return false;
		}
		var t=/^\d+(\.\d+)?$/; 
		if(t.test(currentNum) == false){
			$("#num"+obj).val(1);
			calculateMoney('true',true);
			return false;
		}
		if(currentNum.length >3){
			$("#num"+obj).val(1);
			calculateMoney('true',true);
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
	function validateOrderNum(obj,productId){
		if(checkOrderNum(obj) == true){
			if($("#check"+obj).is(':checked') == true){
				calculateMoney('true',true);
			}
		}
	}
	
	function deleteDownGoods(cartId,oneboxCount,buyNum){
		$("#hiddenCartId").text(cartId);
		var bnum = oneboxCount*buyNum;
		$("#hiddenOneboxCount").text(bnum);
		$('#dialog1').dialog('open');
	}
	
	//删除购物车ajax
	function deleteShoppingCart(deleteGoodsList,tabId){
		$.ajax({
	        type: "POST",
	        url: "../shoppingCar/deleteShoppingCart",
	        data: "deleteGoodsList="+deleteGoodsList+"&cartNumber="+ $("#hiddenCartNumber").text()+"&random="+Math.random(),
	        success: function(response){
	        	window.location.href = window.location.href;
//	        	$("#cartDiv").html(response); //重新显示列表块
//	        	$("#check-onlys").prop("checked",true);  //全选按钮选中
//	        	tabShowOrHide(tabId); 	//默认显示在当前tab
//	        	calculateMoney('true'); //重新计算金额
	        },
	        error: function() {
	        	
	        }   
	    });
	}
	
	//提交到产品详情
	function productDetail(id,st,mid){
		$("#submitProductDetail #id").val(id);
		$("#submitProductDetail #st").val(st);
		$("#submitProductDetail #mid").val(mid);
		$("#submitProductDetail").submit();
	}