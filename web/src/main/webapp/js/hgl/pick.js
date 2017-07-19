		function nameHide(){
			$('.name-list').stop().slideUp(300);
		}
		$(function() {
			var timer= null;
			var tag = 1;
			$('.name').hover(function(){
				$(this).find('.name-list').stop().slideDown(100);
				clearTimeout(timer);
			},function(){
				timer = setTimeout(nameHide,500);
			});
			
			// js对新添加的元素不生效可以改用 live  delegate 
			$(document).delegate(".seek-class a","click",function(event) {
				$(this).addClass('active').siblings('a').removeClass('active');
			});
			
			/*点击排序 综合 价格*/
			$(document).delegate(".seek-nav .current","click",function(event) {
				//alert(1);
				$(this).addClass('active').siblings('.current').removeClass('active');
				//选择综合查询时 去掉销量和价格的图标
				var id=$(this).attr("id");
				if(id=='autoOrderBy'){
					$(".current").children("a").children(".iconfont").html("");
					$("#orderby").val("");
					$("#ordersort").val("");
				}else{
					var icon=$(this).children("a").children(".iconfont").attr("icon");
					$("#orderby").val($(this).attr("for"));
					if(icon=='' || icon=='xe61b'){
						$(".current").children("a").children(".iconfont").html("");
						$(".current").children("a").children(".iconfont").attr("icon","");
						$(this).children("a").children(".iconfont").html("&#xe61a;");
						$(this).children("a").children(".iconfont").attr("icon","xe61a");
						$("#ordersort").val("desc");
					}else if(icon=='xe61a'){
						$(".current").children("a").children(".iconfont").html("");
						$(".current").children("a").children(".iconfont").attr("icon","");
						$(this).children("a").children(".iconfont").html("&#xe61b;");
						$(this).children("a").children(".iconfont").attr("icon","xe61b");
						$("#ordersort").val("asc");
					}
				}
				moneyCommit();
			});
			
			/*点击品牌图片上面的复选框*/
			$(document).delegate(".brandCheckbox","click",function(){
				
				var brandIds='';
				$('.brandCheckbox').each(function() {
			        if ($(this).prop('checked') =='checked' || $(this).prop('checked') ==true) {
			               if(brandIds!=''){
			            	   brandIds+=",";
			               }
			               brandIds+=$(this).val();
			        }
				});
				$("#brandIds").val(brandIds);
				moneyCommit();
			});
			
			/*点击框排列还是列表排列的小图标*/
			$(document).delegate(".seek-icon .cursor","click",function(){
				var index = $(this).index();
				$('.show .style-list').eq(index).show().siblings('.style-list').hide();
				$('.seek-icon .cursor').eq(index).css("color","red").siblings('.cursor').css("color","black");
				var cursor=$("#cursor").val();
				if(cursor==0){
					$("#cursor").val(1);
				}else{
					$("#cursor").val(0);
				}
			});
			
		});
	
		/*把价格筛选条件去除非数字的字符并且*/
		$(document).delegate("#moneyCommit","click",function(){
			moneyCommit();
		});
		
		function moneyCommit(){
			var num1=$("#stringToFloat1").val();
			if(num1!=null && num1!=''){
				$("#stringToFloat1").val(stringToFloat(num1));
				$("#minprice").val(stringToFloat(num1));
			}else{
				$("#minprice").val("");
			}
			var num2=$("#stringToFloat2").val();
			if(num2!=null && num2!=''){
				$("#stringToFloat2").val(stringToFloat(num2));
				$("#maxprice").val(stringToFloat(num2));
			}else{
				$("#maxprice").val("");
			}
			formsubmit();
		}
		/**********************************现货选购品类联动********************************************/
		function linkageAllPage(){
			$("#firstTypeId").val("");
			$("#secondTypeId").val("");
			$("#thirdTypeId").val("");
			$("#brandIds").val("");
			firstProductTypeX(0,"../pick/linkageAllPage");
			moneyCommit();
		}
		function firstProductType(id){
			$("#firstTypeId").val(id);
			$("#secondTypeId").val("");
			$("#thirdTypeId").val("");
			$("#brandIds").val("");
			firstProductTypeX(id,"../pick/linkageMainPage");
			moneyCommit();
		}
		function firstProductTypeX(id,url){
			 $.ajax({
		         type:"POST",
		         url :url,
		         data:{
		             id:id
		         },
		         dataType:"json",
		         success:function(response){
		        	 $("#secondForAjax").children("a").remove(); 
		        	 $("#thirdForAjaxDiv").css("display","none");
		        	 var len=0;
		        	 if(response.secondList!=null){
		        		 len = response.secondList.length;
		        	 }
		        	 if(len > 0){ 
		             	for(var i=0; i<len; i++){
		             		$("#secondForAjax").append( "<a onclick='secondProductType("+response.secondList[i].id+");'>"+response.secondList[i].name +"</a>");
		             	}
		             }
		        	 $("#brandForAjax").children("div").remove(); 
			        	 len=0;
			        	 if(response.tbBrandList!=null){
			        		 len = response.tbBrandList.length;
			        	 }
		        	 if(len > 0){ 
		        		 var str =  "<div class='seek-img'>	";
		              	for(var i=0; i<len; i++){
		              		str+= "<div class='seek-label pull-left'>"
		 									+"<label for='name_"+response.tbBrandList[i].id +"'>"
		 									+	"<input type='checkbox' value='"+response.tbBrandList[i].id+"' class='brandCheckbox' id='name_"+response.tbBrandList[i].id +"'>"	
		 									+	"<img src='../generateImage?id="+response.tbBrandList[i].id+"'   alt='"+response.tbBrandList[i].id +"'>"
		 									+"</label></div>";
		              	}
		              	str+="</div>";
		        		 $("#brandForAjax").append(str);
		              }
		         }
		     });
		}
	
		function secondProductType(id){
			$("#secondTypeId").val(id);
			$("#thirdTypeId").val("");
			formsubmit();
			 $.ajax({
		        type:"POST",
		        url :"../pick/linkageMainPageSecond",
		        data:{
		            id:id
		        },
		        dataType:"json",
		        success:function(response){
		        	 $("#thirdForAjax").children("a").remove();
		           	var tLen = response.thirdList.length;
		           	if(tLen > 0){ 
		           		for(var i=0; i<tLen; i++){
			           		$("#thirdForAjax").append( "<a onclick='thirdProductType("+response.thirdList[i].id+");'>"+response.thirdList[i].name+"</a>");
		               	}
		           		$("#thirdForAjaxDiv").css("display","block");
		           	}else{
		           		$("#thirdForAjaxDiv").css("display","none");
		           	}
		        }
			 });
		}
		function thirdProductType(id){
			$("#thirdTypeId").val(id);
			moneyCommit();
		}	
		/***************************************************************************************************************/
		function stringToFloat(num){
			num = num.replace(/[^0-9\.]/g,'');
			if(num==''){
				num=0;
			}
			return parseFloat(num).toFixed(2);
		}
		
		/*************************************************************************************************/
		function formsubmit(){
			var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		    var frm = $("#" + frmId);
		    var params=frm.serialize();
			$.ajax({
		        type : "POST",
		        url : '../pick/serachProduct',
		        data : params,
		        success : function(response) {
		            $("#" + dataDomId).html(response);
		        }
		    });
		}
		
		
		/*操作收藏信息 --横向(typeId:0表示取消收藏，1表示收藏 )*/
		function OperateSaveInfo(productId,typeId){	
			$.ajax({
				type:"post",
				url:"../pick/doOperateSaveInfo",
				data:{"id":productId,"typeId":typeId},
				success:function(data){
					var productSaveId =document.getElementById("productId"+productId);
					var	productSaveIdVer =document.getElementById("productSaveId"+productId);
					if(data=="1"){  						
						productSaveId.innerHTML="<span class='iconfont' title='已收藏' onclick='OperateSaveInfo("+productId+",0)'>&#xe609;</span>";
						productSaveIdVer.innerHTML="<span class='iconfont' title='已收藏' onclick='addProductToSaveInfo("+productId+",0)'>&#xe609;</span>";
					}else if(data=="-1"){
						showModifyTips("收藏失败");
					}else if(data=="2"){				
						productSaveId.innerHTML="<span class='iconfont' title='收藏' onclick='OperateSaveInfo("+productId+",1)'>&#xe608;</span>";
						productSaveIdVer.innerHTML="<span class='iconfont' title='收藏' onclick='addProductToSaveInfo("+productId+",1)'>&#xe608;</span>";
					}else if(data=="-2"){
						showModifyTips("取消失败");
					}else if(data=="false"){
						showModifyTips("操作失败");
					}
				}
			});
		}
		
		/*添加到收藏 --纵向*/
		function addProductToSaveInfo(productId,typeId){
			$.ajax({
				type:"post",
				url:"doOperateSaveInfo",
				data:{"id":productId,"typeId":typeId},
				success:function(data){
					var productSaveId =document.getElementById("productId"+productId);
					var	productSaveIdVer =document.getElementById("productSaveId"+productId);				
					if(data=="1"){  	
						productSaveId.innerHTML="<span class='iconfont' title='已收藏' onclick='OperateSaveInfo("+productId+",0)'>&#xe609;</span>";
						productSaveIdVer.innerHTML="<span class='iconfont' title='已收藏' onclick='addProductToSaveInfo("+productId+",0)'>&#xe609;</span>";
					}else if(data=="-1"){
						showModifyTips("收藏失败");
					}else if(data=="2"){	
						productSaveId.innerHTML="<span class='iconfont' title='收藏' onclick='OperateSaveInfo("+productId+",1)'>&#xe608;</span>";
						productSaveIdVer.innerHTML="<span class='iconfont' title='收藏' onclick='addProductToSaveInfo("+productId+",1)'>&#xe608;</span>";
					}else if(data=="-2"){
						showModifyTips("取消失败");
					}else if(data=="false"){
						showModifyTips("操作失败");
					}
				}
			});
		}
		
		/*产品收藏 ---招商
		 * typeId:1表示收藏，0表示取消收藏
		 * */
		function addMerchantsShopInfoSave(brandId,typeId){
			$.ajax({
				type:"post",
				url:"doAddSaveInfo",
				data:{"brandId":brandId,"typeId":typeId},
				success:function(data){
					var shopSaveInfo=document.getElementById("addShopInfo"+brandId);
					if(data=="1"){
						shopSaveInfo.innerHTML="<span class='iconfont' title='已收藏' onclick='addMerchantsShopInfoSave("+brandId+",0)'>&#xe609;</span>";
					}else if(data=="-1"){
						showModifyTips("收藏失败");
					}else if(data=="2"){
						shopSaveInfo.innerHTML="<span class='iconfont' title='收藏' onclick='addMerchantsShopInfoSave("+brandId+",1)'>&#xe608;</span>";
					}else if(data=="-2"){
						showModifyTips("取消收藏失败");
					}else if(data=="false"){
						showModifyTips("操作错误");
					}
				}
			});
		}
		
		
		function showModifyTips(tipsInfo){
			$('.myModal #modalSpan').html(tipsInfo);
			$('.myModal').fadeIn(100);
			timer = setTimeout(function(){
				$('.myModal').fadeOut(100);
			},500);
		}