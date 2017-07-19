<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="cart-body">
<head>
	<meta charset="UTF-8">
	<title>站内信</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/my.css">
	
	<%@include file="../common/common.jsp"%> 
</head>	
<body>
<div class="pox">    
	<%-- <%@include file="../common/header.jsp"%> --%>
	<header class="cart verify-head " style="z-index:10000;">
		<span class="icon-angle-left text-center" onclick="window.location.href = '${ctx}/letter/userLetterList'"></span>
		<h3 class="text-center"><c:out value="${titleName}"/></h3>
		<span class="icon-reorder"></span>
		<%@include file="../common/topBar.jsp"%>
	</header>	
</div>
	
	

<div class="container letter" id="letterNum">
<%@include file="./letterList.jsp"%>

	<%-- <c:forEach var="item" items="${letters}" varStatus="s"> 
	<c:if test="${item.letterType == 1 }" var="isfasong">    
	<div class="letter-inser left">
		<p class="text-center">
			<span><liguo:dateFormatLabel
						value="${item.createDt }" pattern="yyyy-MM-dd  HH:mm:ss" /></span>
		</p>
		<div class="box-shadow clearfix box">
			<div class="pull-left box1">
				${item.recipientname }
				<img src="images/3.jpg" alt="">
			</div>
			<div class="pull-left box5">
				
				<c:if test="${item.type ==0 }">
									${item.content }
								</c:if>	
				<c:if test="${item.type ==1 }">
					<a href="javascript:;">
					<p class="text-hidden">${item.activityName}</p>
					<img src="images/12.jpg" alt="">
					</a>
				</c:if>
			</div>
		</div>
	</div>
	</c:if>
	<c:if test="${!isfasong}" >   
	<div class="letter-inser right">
		<p class="text-center">
			<span><liguo:dateFormatLabel
						value="${item.createDt }" pattern="yyyy-MM-dd  HH:mm:ss" /></span>
		</p>
		<div class="box-shadow box-shadow clearfix box">
			<div class="pull-right box5">
				${item.content }
			</div>
			<div class="pull-right box1">
				<img src="images/2.jpg" alt="">
			</div>
		</div>
	</div>
	</c:if>
	</c:forEach> --%>
</div>
<div id="box"></div>
<footer class="letter-foot">
	<!-- <input type="text" id="info">
	<button type="button" id="send">发送</button> -->
	
	<form id="submitLetterForm" method="post">
		<input type="hidden" value="${param.uId}" id="userListIds" name="userListIds"/>
		<input type="hidden" value="${param.name}" id="userListNames" name="userListNames"/>
		<input type="hidden" value="0" id="type" name="type"/>
		<div id="textDiv">
		<input type="text" name="content" id="info"/>
		</div>
		<button type="button"  id="send" >发送</button>                           
	</form>
</footer>


  		
</body>
<script>

/* setInterval(myrefresh(),1000); //指定1秒刷新一次
function myrefresh(){
	alert(123);
	$.ajax({                       
		type: "POST",
		url: ctx+"/letter/letterByTime",
		data: 'uId='+$('#userListIds').val(),
		success: function(response){  
			if(response==""){
				
				return false;
			}else{
				 $("#letterNum").html(response);
			}
		}
	});
} */
setInterval(interval,3000); 

function interval(){

	$.ajax({                       
		type: "POST",
		url: ctx+"/letter/letterByTime",
		data: 'uId='+$('#userListIds').val(),
		success: function(response){
			if(response==""){
				
				return false;
			}else{
				 $("#letterNum").html(response);
			}
		}
	});
}
	
	$(function() {
		setBox();
		$('.box1 img').height($('.box1 img').width());
		
		$('#send').click(function(){
			setBox();
			var text = $('#info').val();
			if(text!=null && text!=""){
			var param = $("#submitLetterForm").serialize();
			$.ajax({                       
				type: "POST",
				url: ctx+"/letter/addLetterByUser",
				data: param,
				success: function(response){ 
					setBox();
					if(response.errcode==0){
						
						
						var div = $('<div class="letter-inser right" style="display:none;">'+
										'<p class="text-center">'+
											'<span>'+
												response.sendLetter.reservedstr  +
											'</span>'+
										'</p>'+
										'<div class="col-10 clearfix">'+
											'<div class="details">'+
												text +
											'</div>'+
											'<div class="text-hidden">'+
													'我'+
											'</div>'+
										'</div>'+
									'</div>');
				$('#info').val('');
				if(!text==''){
					$('.letter').append(div);
					
					div.fadeIn(100);
					setBox();
				}
						
					}else {
						layer.open({
							content: '发送失败！',
							time: 2 //2秒后自动关闭
						});  
					}
				}
			
			
			
			});
			
			}
		
			
		});
	});

	//发送消息的方法
	function addText(text){
		var div = $('<div class="letter-inser right" style="display:none;">'+
						'<p class="text-center">'+
							'<span>2016-07-03 15:20</span>'+
						'</p>'+
						'<div class="box-shadow box-shadow clearfix box">'+
							'<div class="pull-right box5">'+
								text +
							'</div>'+
							'<div class="pull-right box1">'+
								'<img src="images/2.jpg" alt="" style="height: 60px;">'+
							'</div>'+
						'</div>'+
					'</div>');
		$('.container').append(div);
		div.fadeIn(100);
	}
	//图文的方法
	function addImgText(){
		var div = $('<div class="letter-inser left">'+
			'<p class="text-center">'+
				'<span>2016-07-03 15:18</span>'+
			'</p>'+
			'<div class="box-shadow clearfix box">'+
				'<div class="pull-left box1">'+
					'<img src="images/3.jpg" alt="" style="height: 60px;">'+
				'</div>'+
				'<div class="pull-left box5">'+
					'<a href="javascript:;">'+
						'<p class="text-hidden">oppo免费送oppo免费送oppo免费送oppo免费送oppo免费送oppo免费送</p>'+
						'<img src="images/12.jpg" alt="">'+
					'</a>'+
				'</div>'+
			'</div>'+
		'</div>');
		$('.container').append(div);
		div.fadeIn(100);
	}
	function setBox(){
		 window.location.hash = '';
		 window.location.hash = '#box';  
	}
</script>

</html>


