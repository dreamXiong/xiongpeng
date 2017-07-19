<%@page pageEncoding="UTF-8"%>
	<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<c:set value="下单成功" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<%@include file="../common/common.jsp"%>
</head>	
<body>  
    <form action="" method="post" >  
        <input type="button" value="确认支付" name="ajaxLoadId" id="test"/>  
    </form>  
    <script type="text/javascript">  
    $("#test").one("click",function(){  
        $.ajax({  
           url:"${ctx}/weixinPay/goPay"            //<span style="font-family:微软雅黑;">ajax调用微信统一接口获取prepayId</span>  
        }).done(function(obj){  
        	//alert(obj.appId);
        	//alert(obj.timeStamp);
        	//alert(obj.nonceStr);
        	//alert(obj.packageValue);
        	//alert(obj.signType);
        	//alert(obj.paySign);
           // var obj = eval('(' + data + ')');  
           /*  if(parseInt(obj.agent)<5){  
                alert("您的微信版本低于5.0无法使用微信支付");  
                return;  
            }   */
            WeixinJSBridge.invoke('getBrandWCPayRequest', {
                "appId" : obj.appId,                  
                "timeStamp":obj.timeStamp,          
                "nonceStr" : obj.nonceStr,        
                "package" : obj.packageValue,      
                "signType" : obj.signType,         
                "paySign" : obj.paySign          
               },function(res){      
                    alert(res.err_msg);  
                if(res.err_msg == "get_brand_wcpay_request:ok" ) {  
                   alert("success");
                }else{  
                    alert("fail");  
                    //window.location.href="http://183.45.18.197:8016/wxweb/config/oauth!execute.action";     
                                      //<span style="font-family:微软雅黑;">当失败后，继续跳转该支付页面让用户可以继续付款，贴别注意不能直接调转jsp，</span><span style="font-size:10.5pt">不然会报</span><span style="font-size:12.0pt"> system:access_denied。</span>  
                }  
            });  
        });  
    });  
    </script>  
  </body>  
</html>