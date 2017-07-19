<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="classification">
<head>
	<meta charset="UTF-8">
	<title> 找师傅</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">  
	<%@include file="../common/common.jsp"%>
</head>	

<style>

	.cleanType{
		background:rgba(242,0,14,.8);
		color:#fff;
	}
	.active{
		display: block;
		width:100%;
		height:100%;
		position: absolute;
		top:0;
		left:0;
	}
</style>
<body >
<header class="text-center">
	<span onclick="return_prepage();" class="icon-angle-left text-center"></span>
	找师傅
</header>
<div class="class-nav">
	<div class="class-nav-l">
		<ul>
			<c:forEach var="item" items="${s}" varStatus="sindex">
				<li onclick="" name="${item.id}"
					<c:if test='${sindex.index == 0 }'>
						class='cur'
					</c:if>
				>${item.name}</li>
			</c:forEach>
		</ul>
	</div>
	<div class="class-nav-r" id="masterWorkerInfo">
		<%@include file="masterWorkerList.jsp"%>
	</div>
</div>
</body>
<script>
	$(function() {
		$("input[type='checkbox']:checked").attr("checked",false);  
		var divH = 44;   
		var winH = $(window).height();
		$('.class-nav-l').height(winH-divH);
		$('.class-nav-r').height(winH-divH);
		$('.class-nav-l li').click(function(event) {
			$(this).addClass('cur').siblings('li').removeClass('cur');
			$("input[type='checkbox']:checked").attr("checked",false);  
			var id = $(this).attr('name');
			$.ajax({
		        type: "POST",
		        async: false,
		        url: "getServiceTypeInfo",  
		        data:{
		        	id:id
		        },
		        success: function(response){
			       	$("#masterWorkerInfo").html(response);
		       	}
			}); 
		});
	});
	$('.hgl-list').delegate('input','click',function(){
		if($(this).is(":checked")){
			
		}else{
			
		}
	});
	
</script>
</html>