<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
	<tiles:put name="title" value="站内信管理" />
	<tiles:put name="body" type="String">
<head>
	<meta charset="UTF-8">
	<title>我的惠给力</title>
	
	<link rel="stylesheet" href="${ctx}/css/me.css">
	<script src="${ctx}/js/jquery/jquery1.9.2/ui/minified/jquery-ui.min.js"></script>
	<script src="${ctx}/js/hgl/pick.js"></script>
	<script src="${ctx}/js/hgl/datepicker-zh.js"></script>
	<script src="${ctx}/js/hgl/letter.js"></script>
</head>
<body>

<!-- 内容板块开始 -->
	<div class="">
		<div class="area me">
			
			<div class="main-right pull-right">
				<div class="message" id="message">
					<div class="pull-left">
						<ul class="one-list">
							<li data-index="0" data-list="0">
								<label for="" class="radio"> 
									<span></span>
									<input type="checkbox" name="list" id="complete">全部
								</label>
							</li>
							<li data-index="1">
								<label for=""  class="radio">
									<span class="active"></span>
									<input type="checkbox" name="list" >分组
								</label>
							</li>
							<c:forEach var="gItem" items="${groups}" varStatus="s">
							<li class="hide">
								<label for="">
									<span></span>
									
									<input type="checkbox" id="${gItem.id}">${gItem.name}
								</label>
							</li>
							</c:forEach>
							<li class="hide">
								<label for="">
									<span></span>
									
									<input type="checkbox" id="guanzhu" title="关注本店铺的客户">访客
								</label>
							</li>
						</ul>
					</div>
					<div class="pull-left">
						<ul class='two-list'>
							<c:forEach var="uItem" items="${uList}" varStatus="s">
							<li>
								<label for="">
									<span></span>
									<input type="checkbox" name="${uItem.name}" value="${uItem.id}">${uItem.name}
								</label>
							</li>
							</c:forEach>
						</ul>
						<c:forEach var="guItem" items="${groupUserDtos}" varStatus="s">
						<ul class='two-list'>
							<c:forEach var="ugItem" items="${guItem.userByGroupId}" varStatus="v">
							<li>
								<label for="">
									<span></span>
									<input type="checkbox" name="${ugItem.name}" value="${ugItem.id}">${ugItem.name}
								</label>
							</li>
							</c:forEach>
						</ul>
						</c:forEach>
						<ul class='two-list'>
							<c:forEach var="gzItem" items="${gzUserList}" varStatus="g">
							<li>
								<label for="">
									<span></span>
									<input type="checkbox" name="${gzItem.name}" value="${gzItem.id}">${gzItem.name}
								</label>
							</li>
							</c:forEach>
						</ul>
					</div>
					<div class="pull-left image-text" id="letterNum">
						<%@include file="./letterList.jsp"%>
					</div>
					
						<form id="submitLetterForm" action="addLetter" method="post">
							<input type="hidden" value="" id="userListIds" name="userListIds"/>
							<input type="hidden" value="" id="userListNames" name="userListNames"/>
							<input type="hidden" value="" id="activityId" name="activityId"/>
							<input type="hidden" value="0" id="type" name="type"/>
							<div id="textDiv">
								<textarea name="content" id="content" maxlength="240"></textarea>
							</div>
							<div id="imgDiv" style="display:none;border:1px solid #ccc;width:599px;height:97px;
								overflow-y:scroll;margin-top:-1px;background: #eee;">
								<img id="activityImge" src="" height="188" width="250" alt="" >
								<p id="activityName"></p>
							</div>
							<div class="text-right" style="margin-top:5px;padding-right:23px;">
							<button type="button" class="btn-bg" id="img-text">
								<span>选择图文</span>          
							</button>
							<button type="button" class="btn-bg" onclick="sendLetter();">
								<span>发送</span>
							</button>           
						</div>
						</form>
					
				</div>
			</div>
		</div>
	</div>
<!-- 内容板块结束 -->




<div id="dialog" class="list-textimg" style="display:none;">
	<ul>
		<c:forEach var="aItem" items="${activityInfos}" varStatus="s">
		<li>
			<img src="${ctx}/activity/displayImage?id=${aItem.id}&imageName=titleImage.jpg" height="188" width="250" alt="">
			<p id="${aItem.id}">${aItem.activityName}</p>
		</li>
		</c:forEach>
	</ul>
</div>
<!-- 图文选择的弹出层结束 -->
</body>
<script>
	$(function() {
		var winH = $(window).height()-150;      
		var winW = $(window).width()-550;
		
		$( "#dialog" ).dialog({            //弹出层初始化
			   title:'发送活动' ,                  //弹出层的标题
			   autoOpen: false ,                       //禁止自己弹出
			   resizable: false,                       //禁止弹出层缩放
			   draggable :false,                       //禁止拖动
			   modal: false,                            //是否有模态框
			   width:winW, 
			   height:winH,
			   closeText:'关闭',
			   buttons:{                               //创建btn
			     关闭:function(){
			       $(this).dialog("close");
			     },
			     确定:function (){
			    	
			    	 var imgSrc = $('#dialog .cur').find('img').attr('src');
			    	 var imgName= $('#dialog .cur').find('p').text();
			    	 var imgId = $('#dialog .cur').find('p').attr('id');
			    	 if(imgId!="" &&imgName!=""){
			    		 $("#textDiv").hide();
				    	 $("#imgDiv").show();
				    	 $("#activityImge").attr("src",imgSrc);
				    	 $("#activityName").text(imgName);
				    	 $("#type").val("1");
				    	 $("#activityId").val(imgId);
				    	 $(this).dialog("close");
			    	 }else{
			    		 alert("请选择活动");
			    	 }
			     }
			   }
		});
		$('.two-list').eq(1).show();
		$('.one-list .hide').show();
		$('.one-list .hide').eq(0).addClass('cur');
		/* $('.two-list').eq(0).find('span').addClass('active'); */
		$('.radio').click(function(event) {
			var index = $(this).parent('li').attr('data-index');
			$('.radio').find('span').removeClass('active');
			$(this).find('span').addClass('active');
			if(index == 1){
				$('#message .hide').show();
				$('#message .hide').removeClass('cur');
				$('#message .hide').eq(0).addClass('cur');
				$('#message .hide').find('span').attr('class','');
				$('.two-list').eq(1).css('display','block').siblings('.two-list').hide();
				$('.two-list').find('span').removeClass('active');
			}else if(index == 0){
				$('.two-list').eq(0).css('display','block').siblings('.two-list').hide();
				$('.two-list').eq(0).find('span').addClass('active');
				$('#message .hide').hide();
			}
		});
		$('.hide').each(function(i){
			$(this).attr('data-list',i+1);
		});
		$('.hide').click(function(event) {            
			var index = $(this).attr('data-list');
			$(this).addClass('cur').siblings('li').removeClass('cur');
			$('.two-list').eq(index).css('display','block').siblings('.two-list').hide();
		});
		$('.hide input').click(function(){
			var index = $(this).parents('li').attr('data-list');
			/*var checked = $(this).prop('checked');*/
			var span = $(this).siblings('span');
			span = $(this).siblings('span');
			if(span.hasClass('active')){
				span.attr('class','');

			}else{
				span.addClass('active');
			};
			if(span.hasClass('active')){
				$('.two-list').eq(index).find('span').addClass('active');
			}else{
				$('.two-list').eq(index).find('span').attr('class','');
				/*$('.two-list').eq(index).find('span').attr();*/
			}
			
			event.stopPropagation();
		});
		$('#img-text').click(function(event) {
			$('#dialog').dialog('open');
		});
    	$(document).delegate('.list-textimg li','click',function(){
    		$(this).addClass('cur').siblings('li').removeClass('cur');
    	});
    	$('.two-list input').click(function(event) {
    		var checked = 1;
    		var paren = $(this).parents('.two-list');
    		var index = $(this).parents('.two-list').index()-1;
    		$(this).siblings('span').toggleClass('active');
    		var checked_len = paren.find('span.active').size();
    		var len = paren.find('span').size();
    		/*paren.find('span').each(function(i){
    			if(!$(this).hasClass('active')){
    				checked = 2;
    				return false;
    			};
    		});*/
    		//alert(count);
    		if(checked_len == 0 ){
    			checked = 3;
    		}else if(checked_len == len){
				checked = 1;
    		}else{
    			checked = 2;
    		}
    		if(checked==1){
    			$('.hide').eq(index).find('span').attr('class','active');
    		}else if(checked == 2){
    			$('.hide').eq(index).find('span').attr('class','hover');
    		}else if(checked == 3){
    			$('.hide').eq(index).find('span').attr('class','');
    		}
    	});
    	/*$('.two-list input').click(function(event) {
    		var checked = true;
    		$('.two-list input').each(function(i,e){
    			if(e.checked == true){
    				$('.one-list .cur input').prop('checked',checked);
    			}else{
    				$('.one-list .cur input').prop('checked',!checked);
    			}
    		})
    	});
    	$('.one-list input').click(function(event) {
    		var checked = $(this).prop('checked');
    		$('.two-list input').prop('checked',checked);
    	});*/
    	
    	$(document).delegate('#complete','click',function(){
    		$('.two-list').find('input[type="checkbox"]').prop('checked',true);
    		$('.one-list').find('input[type="checkbox"]').prop('checked',!true);
    	});
    	$(document).delegate('#grouping','click',function(){
    		if(!$(this).checked){
    			$('input[type="checkbox"]').prop('checked',!true);
    		}
    		
    	});
    	$(document).delegate('.one-list input[type="checkbox"]','click',function(){
    		
    		/*$('.two-list').find('input[type="checkbox"]').prop('checked',!true);*/



    		$('.one-list input[type="checkbox"]').each(function(){
    			if($(this).prop('checked') == true){
					$('#grouping').prop('checked',!false);
    			}
    		});

    		
    		/*if(checkFlag){
    			$("#complete").prop('checked',true);
    			$('.one-list').find('input[type="checkbox"]').prop('checked',!true);
    			$('.two-list').find('input[type="checkbox"]').prop('checked',true);
    		}else{
    			$("#complete").prop('checked',false);
    			$('#grouping').prop('checked',!false)      
    		}*/
    		$('.two-list').find('input[type="checkbox"]').prop('checked',true);          
    		
    	});
    	setBox();

	});
	function setBox(){
		location.hash = '';
		location.hash="#box";  
	}
</script>
</html>
	</tiles:put>
</tiles:insert>