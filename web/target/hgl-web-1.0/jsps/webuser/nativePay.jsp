<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
<tiles:put name="title" value="审核修改"/> 
<tiles:put name="body" type="String">   
<head>
	<meta charset="UTF-8">
	<title>微信扫码支付</title>
	<link rel="stylesheet" href="${ctx}/css/me.css">
	<script src="${ctx}/js/jquery/jquery1.9.2/ui/minified/jquery-ui.min.js"></script>
	<script src="${ctx}/js/hgl/datepicker-zh.js"></script>
	<script src="${ctx}/js/hgl/pick.js"></script>
	<script src="${ctx}/js/jquery/uploadPreview.min.js"></script>
	<script src="${ctx}/js/hgl/district.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload-process.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload-validate.js"></script>
	<script src="${ctx}/js/jquery/uploadPreview.min.js"></script>
<script src="${ctx}/js/hgl/userimageupdate.js"></script>
<style type="text/css">
.onerrInput {
    border: 1px solid red!important;
}
</style>
</head>
<body>
<div class="main-right pull-right">
	<div style="margin-left: 325px;">
		<span style="font-size:3em;">惠给利微信支付</span>
	</div>
	<span style="color:red;padding: 0 0 0 290px;">（支付金额为：${payMoney}元，支付成功后请点击退出重新登陆。）</span>
  	<img src='${ctx }/shop/showWeiXinPay?imgName=${orderSerialNo}.jpg' style="margin:0 auto;"/>
</div>         
</body>

</tiles:put>
</tiles:insert>