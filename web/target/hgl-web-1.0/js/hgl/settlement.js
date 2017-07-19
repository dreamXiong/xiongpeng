	$(function() {
		var deleteId = 0;
		
		$(document).delegate('.list-title','click',function(event) {
			$(this).addClass('active').siblings('.list-title').removeClass('active');
		});
		
		var tr_len=$('tbody tr').length;
		$('.num,.sum').attr('rowspan',tr_len);
		$(document).delegate(".shrink",'click',function(){
			var table = $(this).parents('.chat-title').siblings('.table');
			$(this).toggleClass('out');
			table.slideToggle(500);
		});
	
		$('#dialog').dialog({
			autoOpen: false,
			modal:true,
			resizable: false,//禁止弹出层缩放
        	draggable :false,//禁止拖动
        	width:'500',
        	closeText:'关闭',
        	buttons:{
        		保存收货人信息:function(){
        			var validateFalg = true;
        			var recipient = $("#dialog #recipient").val();
        			var country = $("#dialog #country").val();
        			var street = $("#dialog #streetDetail").val();
        			var phone = $("#dialog #phone").val();
        			var telephone = $("#dialog #telephone").val();
        			if(recipient == ''){
        				$("#dialog #recipientError").text('不能为空');
        				validateFalg = false;
        			}else{
        				if(recipient.length>5){
        					$("#dialog #recipientError").text('名称过长');
            				validateFalg = false;
        				}else{
        					$("#dialog #recipientError").text('');
        				}
        			}
        			if(country == '0' || country == null){
        				$("#dialog #countryError").text('不能为空');
        				validateFalg = false;
        			}else{
        				$("#dialog #countryError").text('');
        			}
        			if(street == ''){
        				$("#dialog #streetError").text('不能为空');
        				validateFalg = false;
        			}else{
        				if(street.length>30){
        					$("#dialog #streetError").text('地址过长');
            				validateFalg = false;
        				}else{
        					$("#dialog #streetError").text('');
        				}
        			}
        			if(phone == ''){
        				$("#dialog #phoneError").text('不能为空');
        				validateFalg = false;
        			}else{
        				if(checkMobile(phone) || isTel(phone)){
        					$("#dialog #phoneError").text('');
        				}else{
        					$("#dialog #phoneError").text('格式不正确');
        					validateFalg = false;
        				}
        			}
        			if(telephone != ''){
        				if(isTel(telephone)){
        					$("#dialog #telephoneError").text('');
        				}else{
        					$("#dialog #telephoneError").text('格式不正确');
        					validateFalg = false;
        				}
        			}
        			if(validateFalg == false){
        				return;
        			}
//        			var province = $("#dialog #province option:selected").text();
//        	    	var city = $("#dialog #city option:selected").text();
//        	    	var county = $("#dialog #country option:selected").text();
//        	    	var street = $("#dialog #street").val();
//        	    	county = county.replace(/\s+/g,"");
//        	    	var addre = province+city+county+street;
//        	    	$("#dialog #provinceName").val(province);
//        			$("#dialog #extensionField").val(addre);
        			var address = $("#addressForm").serialize();
        			var url = "../myAddress/addAddress";
        			if($("#dialog #id").val() != ''){
        				url = "../myAddress/modifyAddress";
        			}
        			$.ajax({
        		        type: "POST",
        		        url: url,
        		        data: "address="+address+"&random="+Math.random(),
        		        success: function(response){
        		        	$("#myAddress").html(response);
        		        	$("#addressForm")[0].reset();
        		        	$("#dialog #province").val('0');
        		    		$("#dialog #provinceCode").val('0');
        		    		$("#dialog #id").val('');
        		    		gradeChange();
        		        },
        		        error: function() {
        		        	
        		        }   
        		    });
        			$(this).dialog('close');
        		},
        		取消:function(){
        			$(this).dialog('close');
        			$("#addressForm")[0].reset();
		        	$("#dialog #province").val('0');
		    		$("#dialog #provinceCode").val('0');
		    		$("#dialog #recipientError").text('');
		    		$("#dialog #countryError").text('');
		    		$("#dialog #streetError").text('');
		    		$("#dialog #phoneError").text('');
		    		$("#dialog #telephoneError").text('');
		    		gradeChange();
        		}
        	}
		});
		
		$('#dialog3').dialog({
			autoOpen: false,
			modal:true,
			resizable: false,//禁止弹出层缩放
        	draggable :false,//禁止拖动
        	width:'340',
        	closeText:'关闭',
        	buttons:{
        		确定:function(){
        			$(this).dialog('close');
        		}
        	}
		});
		
		$('#dialog4').dialog({
			autoOpen: false,
			modal:true,
			resizable: false,//禁止弹出层缩放
        	draggable :false,//禁止拖动
        	width:'340',
        	closeText:'关闭',
        	buttons:{
        		确定:function(){
        			$(this).dialog('close');
        		}
        	}
		});
		
		$('#dialog5').dialog({
			autoOpen: false,
			modal:true,
			resizable: false,//禁止弹出层缩放
        	draggable :false,//禁止拖动
        	width:'340',
        	closeText:'关闭',
        	buttons:{
        		确定:function(){
        			$(this).dialog('close');
        		}
        	}
		});

		//弹框
	    $("#dialog1").dialog({          
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
        			$.ajax({
        		        type: "POST",
        		        url: "../myAddress/deleteAddress",
        		        data: "id="+deleteId+"&random="+Math.random(),
        		        success: function(response){
        		        	$("#myAddress").html(response);
        		        },
        		        error: function() {
        		        	
        		        }   
        		    });
		    		$(this).dialog("close");
		       }
		   }
		 });
	    
	    $(document).delegate('#addAdde','click',function(event) {
	    	
			$('#dialog').dialog('open');
		});
		
		$(document).delegate('.remove','click',function(event) {
			if($("#default"+this.id).attr("class").indexOf('none') != -1){
				$('#dialog3').dialog('open');
			}else{
				deleteId = this.id;
				$('#dialog1').dialog('open');
			}
		});
		
		$(document).delegate('.succeed .clear li','click',function(event) {
			$(this).addClass('active').siblings('li').removeClass('active');
		});

		$('.addr-lists').delegate('.default','click',function(event){
			var id = $(".default.none").attr('id');
			var newId = this.id;
			if(id != newId){
				id = id.substring(7,id.length);
				newId = newId.substring(7,newId.length);
				$.ajax({
			        type: "POST",
			        url: "../myAddress/defaultAddress",
			        data: "id="+id+"&newId="+newId+"&random="+Math.random(),
			        success: function(response){
			        	$("#myAddress").html(response);
			        },
			        error: function() {
			        	
			        }   
			    });
			}
		});
		
		$(document).delegate('#submitOrder','click',function(event) {  //提交订单
			if($("#submitOrderForm #isSubmit").val() == '0'){
				var payType = $("#payDiv input[name='pay']:checked").val();
				var couponsPrice = $(".verify-pri #couponsPrice").val();
				var addressId = $(".adde-list.list-title.active").attr('id');
				if(addressId == undefined){
					$('#dialog4').dialog('open');
					return;
				}
				var cartIdList = []; 
				$(".chart-list table").each(function () {
					$(".chart-list table input[name='"+this.id+"']").each(function () {
						var cartId = $("#hidcart"+this.value).val();
						cartIdList.push(cartId);
					});
				});
				if(cartIdList != '' && cartIdList.length >0){
					$("#submitOrderForm #cartIdList").val(cartIdList);
					$("#submitOrderForm #addressId").val(addressId);
					$("#submitOrderForm #payType").val(payType);
					/*$("#submitOrderForm #couponsMoney").val(couponsPrice);*/
					$("#submitOrderForm #isSubmit").val('1');
					$("#submitOrderForm #cartIds").val();
					$('#submitOrderForm').submit();
				}
			}else{
				$('#dialog5').dialog('open');
			}
		});
		
	    calculateMoney();  //页面加载完后计算金额
	});
	
	//修改收货地址
	function modifyAddress(id,recipient,province,city,area,street,streetDetail,phone,code,telephone,provinceName){
		$("#dialog #id").val(id);
		$("#dialog #recipient").val(recipient);
		$("#dialog #province").val(province);
		$("#dialog #provinceCode").val(province);
		$("#dialog #cityCode").val(city);
		$("#dialog #countryCode").val(area);
		$("#dialog #streetCode").val(street);   
		$("#dialog #streetDetail").val(streetDetail);
		$("#dialog #phone").val(phone);
		$("#dialog #code").val(code);
		$("#dialog #telephone").val(telephone);
		$("#dialog #provinceName").val(provinceName);
		gradeChange();
		$('#dialog').dialog('open');
	}
	
	//计算金额和件数
	function calculateMoney(){
		   var totalCount = 0;
		   var totalMoney = 0;
		   $(".chart-list table").each(function () {
			   var goodsCount = 0;
			   var goodsMoney = 0;
			   $(".chart-list table input[name='"+this.id+"']").each(function () {
					var orderNum = $("#num"+this.value).val();
					var orderCount = $("#count"+this.value).text();
					var discountPrice = $("#discounts"+this.value).text();
					goodsCount = goodsCount + (orderNum*orderCount);  	//计算单个品牌的商品合计
					goodsMoney = goodsMoney + (discountPrice*(orderNum*orderCount)); //计算单个品牌的商品合计金额
					$("#subtotal"+this.value).text(toDecimal2((discountPrice*(orderNum*orderCount))));
		        });
			   var sumMoney = toDecimal2(goodsMoney);
			   totalCount = (totalCount+goodsCount);	//计算总品牌的商品合计
			   totalMoney = (totalMoney+goodsMoney);	//计算总品牌的商品合计金额
			   $(".chart-list table #numCount"+this.id).text(goodsCount);
			   $(".chart-list table #sumMoney"+this.id).text(sumMoney);
		   });
		   $("#totalCount").text(totalCount);
		   $("#totalMoney").text(toDecimal2(totalMoney));
		   $("#totalAllMoney").text(toDecimal2(totalMoney));
		     
		   var couponRule = $("#couponRule").val()/100;    
		   var couponRemainingAmt = $("#couponRemainingAmt").attr("name");
		   
		   var youhuiMoney = totalMoney*couponRule;
		   if(youhuiMoney > couponRemainingAmt){
			   $("#couponRemainingAmtHide").hide();
		   }else{   
			   $("#couponRemainingMoney").text(toDecimal2(youhuiMoney));
		   }
	}
	$(document).delegate('#couponsPrice','click',function(event) { 
		if($('#couponsPrice').is(':checked')) {
			$("#couponsMoney").val('1');
		}else{
			$("#couponsMoney").val('0');
		}
	});
