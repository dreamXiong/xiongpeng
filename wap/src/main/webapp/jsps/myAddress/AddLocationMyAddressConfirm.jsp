<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<c:set value="完善资料" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/my.css"/>  
	<%@include file="../common/common.jsp"%> 
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=d7f07d528cd92643efcd3d2e73c09548&plugin=AMap.Geocoder"></script>
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>   
    
</head>
<body onload="regeocoder()">
<header class="cart verify-head" style="position:fixed;z-index:10000;top:0;left:0;width:100%;background:#fff;box-shadow:0 2px 15px rgba(0,0,0,.5)">
	<span class="icon-angle-left text-center" onclick="return_prepage();"></span>
	<h3 class="text-center">${titleName}</h3>
	<span class="icon-reorder"></span>
	<%@include file="../common/topBar.jsp"%>
</header>
<div class="container" id="container" style="position:fixed;top:44px;">

</div>
<footer class="site-footer" style="position:fixed;z-index:10000;bottom:0;left:0;width:100%;background:#fff;box-shadow:2px 0 15px rgba(0,0,0,.5)">
	<div class="box">
		<span style="position: absolute;height:100%;width:30px;left:0px;top:0;" onclick="$('#modifyLocationAddressInitForm').submit();"></span>	
		<div class="box2">
			<span style="color:#999;font-size:12px;margin-bottom:5px;">街道小区</span>
			<P style="line-height:1.2;padding:5px 0;" id="addressDetailLable"></P>
		</div>
		<div class="box1">
			<span style="color:#999;font-size:12px;margin-bottom:5px;">楼号门牌</span>
			<input type="text" style="margin-bottom:0;border-color:#ccc;" id="doorplate" maxlength="30" value="${doorplate }">
		</div>
	</div>
	<div  style="padding:5px 0 0 0 ;">
		<button class="button" style="width:100%;padding:8px 0;font-size:1rem;border-radius:3px;"  onclick="submitAddress()" >确认地址</button>
	</div>
</footer>
<form id="addLocationAddressSubmitForm" action="addAddress" method="post">
	<input type="hidden" value="${param.cartIdsList }" id="cartIdsList" name="cartIdsList"/>
	<input type="hidden" value="${param.serviceTypeId }" id="serviceTypeId" name="serviceTypeId"/>
	<input type="hidden" value="${param.messageStr }" id="messageStr" name="messageStr"/>
	<input type="hidden" value="${param.updateServiceId }" id="updateServiceId" name="updateServiceId"/>
	<input type="hidden" value="${param.appointmentsStr }" id="appointmentsStr" name="appointmentsStr"/>
	<input type="hidden" value="${param.appointmentsStrId }" id="appointmentsStrId" name="appointmentsStrId"/>
	<input type="hidden" name="id" id="id" value="${id }"/>
	<input type="hidden" name="province" id="province" value="${address }"/>
	<input type="hidden" name="address" id="address" />
	<input type="hidden" name="streetDetail" id="streetDetail" />
	<input type="hidden" name="provinceName" id="provinceName" value="${name }" />
	<input type="hidden" name="lon" id="lon" value="${lon }" />
	<input type="hidden" name="lat" id="lat" value="${lat }" />
	<input type="hidden" value="${param.integralMallId }" id="integralMallId" name="integralMallId"/> 
	<input type="hidden" value="${param.exchangeNumber}" id="exchangeNumber" name="exchangeNumber"/>
</form>
<form id="modifyLocationAddressInitForm" action="modifyLocationAddressInit" method="post">
	<input type="hidden" name="id" id="id" value="${id }"/> 
	<input type="hidden" name="lon" id="lon" value="${lon }" />
	<input type="hidden" name="lat" id="lat" value="${lat }" />
	<input type="hidden" value="${param.cartIdsList }" id="cartIdsList" name="cartIdsList"/>
	<input type="hidden" value="${param.serviceTypeId }" id="serviceTypeId" name="serviceTypeId"/>
	<input type="hidden" value="${param.messageStr }" id="messageStr" name="messageStr"/>
	<input type="hidden" value="${param.appointmentsStr }" id="appointmentsStr" name="appointmentsStr"/>
	<input type="hidden" value="${param.appointmentsStrId }" id="appointmentsStrId" name="appointmentsStrId"/>
	<input type="hidden" value="${param.integralMallId }" id="integralMallId" name="integralMallId"/> 
	<input type="hidden" value="${param.exchangeNumber}" id="exchangeNumber" name="exchangeNumber"/>
</form>
</body>
<script type="text/javascript">
    var map = new AMap.Map("container", { 
        resizeEnable: true,
        dragEnable: false,   
        //zoomEnable : false,    
		zoom: 17     
    });
    
    function regeocoder() {
    	var lon = $("#addLocationAddressSubmitForm #lon").val();
    	var lat = $("#addLocationAddressSubmitForm #lat").val();
    	var lnglatXY = [lon,lat];
        var geocoder = new AMap.Geocoder({
            radius: 1000,
            extensions: "all"
        });        
        geocoder.getAddress(lnglatXY, function(status, result) {
            if (status === 'complete' && result.info === 'OK') {
                geocoder_CallBack(result);
            }
        });        
        new AMap.Marker({
            map: map,
            position: lnglatXY
        });
        map.setFitView();
    }
    
    function geocoder_CallBack(data) {
        var address = data.regeocode.formattedAddress;
        var provinceName = $("#provinceName").val();
        $("#addressDetailLable").text(provinceName);
        $("#addLocationAddressSubmitForm #address").val(address+'('+provinceName+')');
    }
    
    $(function() {
		var footH = $('footer').innerHeight();
		var headH = $('header').height();
		var winH = $(window).height();
		var conH  = (winH - footH - headH)+15;
		$('.container').css({
			height: conH
		});
	});
    
    //提交地址
    function submitAddress(){
    	var doorplate = $("#doorplate").val();
    	if(doorplate != ''){
    		$("#addLocationAddressSubmitForm #streetDetail").val(doorplate);
    		var id = $("#addLocationAddressSubmitForm #id").val();
    		//地址修改
    		if(id != ''){
    			$("#addLocationAddressSubmitForm").attr("action","modifyAddress");
    		}
    		//材料订单跳转
    		if($("#addLocationAddressSubmitForm #cartIdsList").val() != ''){
    			$("#addLocationAddressSubmitForm").attr("action","../shoppingCar/addAddress");
    		}
    		//下单跳转
    		if($("#addLocationAddressSubmitForm #serviceTypeId").val() != ''){
    			$("#addLocationAddressSubmitForm").attr("action","../wapOrderService/addAddress");
    		}
    		//积分商城提交订单 
    		if($("#addLocationAddressSubmitForm #integralMallId").val() != ''){
    			$("#addLocationAddressSubmitForm").attr("action","../integralMall/addAddress");
    		}
    		$("#addLocationAddressSubmitForm").submit();
    	}else{
    		layer.open({
    	 		content: '请填写门牌号码',
    	 		time: 2
    		}); 
    	}
    }
</script>
</html>