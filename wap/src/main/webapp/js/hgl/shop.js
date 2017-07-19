function selectProduct(){
	var param = $("#selectForm").serialize();
	$.ajax({
         type: "POST",
         async: false,
         url: "productInfo",  
         data:param,
         success: function(response){
        	$("#productInfoList").html(response);
        	laxyImgFun();
        	$('.dealer-list img').height($('.dealer-list img').width());
         }
	});
}
/* 去店铺导航*/
	 function goShop(){
		layer.open({
	    content: '导航需消耗流量，确定使用吗？',
	    btn: ['确认', '取消'],
	    shadeClose: true,
	    yes: function(){
	    	$("#addressForm").submit();
	    	
	    }, no: function(){
	    	layer.close();
	    }
	});
}
	 
	 $(function() {
			var swiper = new Swiper('.swiper-container', {
				autoplay : 5000,
		        pagination: '.swiper-pagination',
		    });

			var winW = $(window).width();                
			var height = winW*0.6;
			$('.swiper-wrapper .swiper-slide').height(height);   
			$("#orderProduct").val('');
			$("#productTypeSelect").val('');
			$("#brandIdsSelect").val('');         
				$('.nav-tab').click(function(){
					var span = $(this).find('span');
					if ($(this).hasClass('active')) {
		 				if(span.hasClass('icon-angle-up')){
		 					$("#orderProduct").val($(this).attr("id")+"_desc");
							span.attr('class','icon-angle-down');
							selectProduct();
		 				}else{
		 					span.attr('class','icon-angle-up');
		 					$("#orderProduct").val($(this).attr("id")+"_asc");
							selectProduct();
		 				}
		 			}else{
		 				if($(this).attr("id") == 'saleNum'){
		 					$("#orderProduct").val($(this).attr("id")+"_desc");
		 				}else{
		 					$("#orderProduct").val($(this).attr("id")+"_asc");
		 				}
		 				$(this).addClass('active').siblings('.nav-tab').removeClass('active');
		 				span.attr('class','icon-angle-up');
		 				selectProduct();
		 				$('#saleNum').find('span').attr('class','icon-angle-down');
		 			};
				});
				$('#screen').click(function(event) {
					$('html').addClass('hidden classification');
					$('.screen-warp').css('display','block').animate({
						left:'0'
					},300);
				});
				
				$('.save').click(function(event) {
					$('.screen-warp').animate({
						left:'100%'
					},300,function(){
						$(this).css('display','none');
					});
					$('html').removeClass('hidden');
				});
				$('.back').click(function(event) {
					$('.screen-warp').animate({
						left:'100%'
					},300,function(){
						$(this).css('display','none');
					});
					$('body').attr('class','');
					$(".nav-r-main li .active").removeClass("active");
				});
				
				$('.selectProductInfo').click(function(event) {
					$('.screen-warp').animate({
						left:'100%'
					},300,function(){
						$(this).css('display','none');
					});
					$('body').attr('class','');
					selectProduct();
				});
				$('.stair li').click(function(event) {
					var title = $(this).find('a').text();
					$(this).parent('.stair').hide();
					$('.movers').show();
					$('.screen-header').html('<span class="icon-angle-left text-center pull-left back" ></span>' +
				title +
				'<a href="javascript:;" class="pull-right save" >确定</a>');
				});
				$('.movers li').click(function(event) {
					$(this).toggleClass('currten');
				});
				var divH = 44;
				var winH = $(window).height();
				$('.class-nav-l').height(winH-divH);
				$('.class-nav-r').height(winH-divH); 
				$('.class-nav-l li').click(function(event) {
					var index = $(this).attr("name");
					$(".productShowHide").hide();
					$(".brandShowHide").hide();
					$("#product_"+index).show();
					$("#brand_"+index).show();
					$(this).addClass('cur').siblings('li').removeClass('cur');
					/* $(".class-nav-l li").removeClass("active"); */
					$(".nav-r-main li .active").removeClass("active");
			});
			$('.hgl-list').delegate('a','click',function(){
				$(this).toggleClass('active');
				var p = '';
				var b = '';
				var pLen = $("#productActive .active").length;
				var pObj = $("#productActive .active");
				for(var i=0 ;i<pLen;i++){
					var s =$(pObj[i]).attr("name");
					if(i == pLen-1){
						p += s;
					}else{
						p += s+",";
					};
				}  
				var bLen = $("#brandActive .active").length;
				var bObj = $("#brandActive .active");
				for(var v=0 ;v < bLen;v++){
					var a =$($(bObj[v])).attr("name");
					if(v == bLen-1){
						b += a;
					}else{
						b += a+",";
					};
				}
				$("#brandIdsSelect").val(b);
				$("#productTypeSelect").val(p);
			});
			$('.dealer-list img').height($('.dealer-list img').width());
			$('.dealer-list a').height($('.dealer-list img').width());
			
			
			$(document).delegate('.collect','click',function(event) {
				/* $('.swiper-wrapper .swiper-slide').height(height);  */ 
			
				var id = this.id;	
				var typeId = $("#hide"+id).val();
				$.ajax({
					type:"post",
					url:"doOperateShopSaveInfo",
					data:{"id":id,"typeId":typeId},
					success:function(data){
						if(data=="1"){
							$("#saveInfo").html("<span class='collect' id='"+id+"'><span style='background:rgba(242,0,14,.8);padding:8px 10px;position:absolute;top:0;right:0;'>取消收藏</span></span><input type='hidden' value='0' id='hide"+id+"'/>");
							//_this.find('span').text('取消收藏');
						}else if(data=="-1"){
							layer.open({
							    content:'收藏失败',
							    type:0,
							    time:1
							});
						}else if(data=="0"){
							layer.open({
							    content:'您已收藏该店铺',
							    type:0,
							    time:1
							});
						}else if(data=="2"){
							$("#saveInfo").html("<span class='collect' id='"+id+"'><span style='background:rgba(242,0,14,.8);padding:8px 10px;position:absolute;top:0;right:0;'>收藏店铺</span></span><input type='hidden' value='1' id='hide"+id+"'/>");
							//_this.find('span').text('收藏店铺');
						}else if(data=="-2"){
							layer.open({
							    content:'取消失败',
							    type:0,
							    time:1
							});
						}else if(data=="3"){
							window.location.href="${ctx}/login/"; 
						}
					}
				});	
			});

		});
		function pickDetail(id){
			 $("#productId").val(id);
			 $("#goPickDetail").submit();
		}


		function isNull(str) {
		    if (str == "")
		        return true;
		    var regu = "^[ |\\n|\\r]+$";
		    var re = new RegExp(regu);
		    return re.test(str);
		}
		var map, geolocation;
		//加载地图，调用浏览器定位服务
		$(function(){
			var city = $("#userLat").val();
			if(!isNull(city)){
				return;
			}
			map = new AMap.Map('container', {
			    resizeEnable: true
			});
			map.plugin('AMap.Geolocation', function() {
			    geolocation = new AMap.Geolocation({
			        enableHighAccuracy: true,
			        timeout: 8000,
			        buttonOffset: new AMap.Pixel(10, 20),
			        zoomToAccuracy: true,
			        buttonPosition:'RB'
			    });
			    map.addControl(geolocation);
			    geolocation.getCurrentPosition();
			    AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息
			    AMap.event.addListener(geolocation, 'error', onError);      //返回定位出错信息
			});
		});
		//解析定位结果  
		function onComplete(data) {
			$("#userLonShop").val(data.position.getLng());
			$("#userLatShop").val(data.position.getLat());
			var param = $("#addressForm").serialize();
			$.ajax({
		      type: "POST",
		      async: false,
		      url: ctx+"/shop/getAddressInfo",  
		      data:param,
		      success: function(response){
		    	  $("#blance").text(response.blance);
		     	}
			});
		}
		
		function onError(){
			alert("定位失败！");
		}