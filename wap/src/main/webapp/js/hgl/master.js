	$(function() {
 		var winH = $(window).height();
 		var winW = $(window).width();
 		var liW = $('#masterListId li').width(); 
 		var headH = $('.screen header').outerHeight(true);
 		var listW = $('#classify li:first').width();
 		var hW = $('#classify h3').outerHeight();
 		var imgW = $('.price .col-6:last').width();
 		$('.price .col-6').height(imgW/2); 
 		$('#classify .img-warp').height(listW-hW);
 		$('.nav-list a').height(liW);                
 		$('#screen').click(function(event) {         
 			$('html').addClass('hidden classification');                                   
 			$('.screen-warp').css('display','block').animate({
 				left:'0'               
 			},300);                  
 		});
 		$('.swiper-slide').height(winW*0.6);
 		$('#save').click(function(event) {
 			 $('.screen-warp').animate({    
 				left:'100%'         
 			},300,function(){     
 				$(this).css('display','none');
 			});
 			
 			$('body').removeClass('hidden');
 		});
 		$('.back').click(function(event) {
 			$('.screen-warp').animate({
 				left:'100%'
 			},300,function(){
 				$(this).css('display','none');
 			});
 			$('html').attr('class','');
 			$(".hgl-list li").removeClass('active');
 		});
 		
 		//$('#masterListId img').height(liW);
 		$('#masterListId a').height($('#masterListId p').outerHeight(true)+$('#masterListId img').outerHeight(true));    
 		$('.selectMaster').click(function(event) {
 			$('.screen-warp').fadeOut(30).css('left','100%');            
 			$('html').attr('class','');  
 			$('.nav-list img').height($('.nav-list li').width());
 			var p = '';
 			var pLen = $("#masterWorkerInfo .active").length;
 			var pObj = $("#masterWorkerInfo .active");
 			for(var i=0 ;i<pLen;i++){
 				var s =$(pObj[i]).attr("name");
 				if(i == pLen-1){
 					p += s;
 				}else{
 					p += s+",";
 				}      
 			}  
 			$("#serviceId").val(p);
 			selectMasterInfo();
 		});
 		$('.class-nav,.class-nav-l,.class-nav-r').height(winH-headH);
 		$('.nav-list img').height($('.nav-list li').width());
 		$('.hgl-list').delegate('li','click',function(){
 			$(this).toggleClass('active');
 		});
 		
 		$('.class-nav-l li').click(function(event) {
 			$(this).addClass('cur').siblings('li').removeClass('cur');
 			$(".hgl-list").hide();
 			$("#s"+$(this).attr('id')).show();
 			$(".hgl-list li").removeClass('active');
 	 	}); 
 	});
 
 	function selectMasterInfo(){
		var serviceId = $("#serviceId").val();
		var serchText = $("#serchText").val();
 		$.ajax({
 	         type: "POST",
 	        /* async: false,*/
 	         url: ctx+"/master/selectMasterInfo",  
 	         data:{
 	        	serviceId:serviceId,
 	        	serchText:serchText
 	         },
 	         success: function(response){
 	        	$("#masterListId").html(response);
 	        	$('.nav-list img').height($('.nav-list li').width());
 	        	laxyImgFun();
 	         }/*,error: function (jqXHR, textStatus, errorThrown) {
 	            alert(jqXHR.responseText+"aaaaaaaa"+jqXHR.status+"aaaaaaaa"+jqXHR.readyState+"aaaaaaaa"+textStatus+"aaaaaaaa"+errorThrown);
 	        }*/

 		});
 	}
	function goServiceDetail(id){
		$("#serviceIdInfo").val(id);
		$("#goServiceDetail").submit();
	}
  	$('#serchText').bind('keyup', function(event) {
        if (event.keyCode == "13") {
            //回车执行查询
        selectMasterInfo(); 
        }
    });