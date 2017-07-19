<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<c:set value="${empty address.id ? '新增地址' : '修改地址'}" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<%@include file="../common/common.jsp"%> 
	<style>
		html,body{
			height:100%;
			width:100%;
		}
		#test{
			width:100%;
			height:100%;
		}
	</style>
</head>
<body>
	<script src="${pageContext.request.contextPath}/js/hgl/load.js"></script>    
	<%-- <%@include file="../common/header.jsp"%> --%>
	<c:choose>          
		<c:when test="${location != null}">
			<iframe id="test" src="http://m.amap.com/picker/?center=${location}&zoom=17&key=d7f07d528cd92643efcd3d2e73c09548" marginheight="0" marginwidth="0" frameborder="0" scrolling="no"></iframe>
		</c:when>
		<c:otherwise>
			<iframe id="test" src="http://m.amap.com/picker/?zoom=17&key=d7f07d528cd92643efcd3d2e73c09548" marginheight="0" marginwidth="0" frameborder="0" scrolling="no"></iframe>
		</c:otherwise> 
	</c:choose>
	 
	<form id="addLocationAddressForm" action="${ctx }/myAddress/AddLocationMyAddressConfirm" method="post">
		<input type="hidden" value="${param.cartIdsList }" id="cartIdsList" name="cartIdsList"/>
		<input type="hidden" value="${param.serviceTypeId }" id="serviceTypeId" name="serviceTypeId"/>
		<input type="hidden" value="${param.messageStr }" id="messageStr" name="messageStr"/>
		<input type="hidden" value="${param.updateServiceId }" id="updateServiceId" name="updateServiceId"/>
		<input type="hidden" value="${param.appointmentsStr }" id="appointmentsStr" name="appointmentsStr"/>
		<input type="hidden" value="${param.appointmentsStrId }" id="appointmentsStrId" name="appointmentsStrId"/>
		<input type="hidden" name="id" id="id" value="${address.id}"/>
		<input type="hidden" name="doorplate" id="doorplate" value="${address.streetDetail}"/>
		<input type="hidden" name="name" id="name" />
		<input type="hidden" name="address" id="address" />
		<input type="hidden" name="lon" id="lon" />
		<input type="hidden" name="lat" id="lat" />
		<input type="hidden" value="${param.integralMallId }" id="integralMallId" name="integralMallId"/> 
		<input type="hidden" value="${param.exchangeNumber}" id="exchangeNumber" name="exchangeNumber"/>
	</form>
	<div class="text-center" id="back" style="height:47px;width:50px;background:#F8F8F8;position:fixed;top:0;left:0;opacity: 0;z-index:10;" onclick="history.back(-1);">
		<span class="icon-angle-left" style="font-size:30px;font-weight:400;line-height:47px;">
		</span>	
	</div>
</body>
<script>
$(function(){  
    var iframe = document.getElementById('test').contentWindow;
    setInterval(function(){
        iframe.postMessage('hello','http://m.amap.com/picker/?random='+Math.random());
        if(window.attachEvent){   
       		window.attachEvent("message",function(e){ 
       			addData(e);
       		});
        }else if(window.addEventListener){                                
        	window.addEventListener("message", function(e){
            	addData(e);
            }, false);
        }
    },1000);              
});

function addData(e){
	if(e.data.location != undefined){
    	document.getElementById("name").value = e.data.name;
    	document.getElementById("address").value = e.data.address;
    	var locas = e.data.location.split(",");
    	document.getElementById("lon").value = locas[0];
    	document.getElementById("lat").value =  locas[1];    
    	document.getElementById("addLocationAddressForm").submit();  
        //console.log(e.data);     
	}
}
</script>
</html>