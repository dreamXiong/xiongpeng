<%@page pageEncoding="UTF-8"%>
<script src="${ctx}/js/jquery/jquery-1.12.2.min.js"></script>
<script src="${ctx}/js/hgl/base.js"></script>
<script src="${ctx}/js/jquery/jquery1.9.2/ui/jquery-ui.js"></script>
<script src="${ctx}/js/common/publicCheckFormat.js"></script>
<script src="${ctx}/js/common/common.js"></script>
<script src="${ctx}/js/common/jquery.linkon.web.js"></script>
<script src="${ctx}/js/hgl/jquery.lazyload.min.js"></script>
<script src="${ctx}/js/hgl/jquery.jqzoom.js"></script>
<script type="text/javascript">
$(function(){
	//全局的AJAX访问，处理AJAX清求时SESSION超时
	$.ajaxSetup({
	    contentType:"application/x-www-form-urlencoded;charset=utf-8",
	    complete:function(XMLHttpRequest,textStatus){
          //通过XMLHttpRequest取得响应头，sessionstatus           
          var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus"); 
          if(sessionstatus=="timeout"){
			   //跳转到登录页面
               window.location.href = "${ctx}/login/index";
	       }
	    }
	});
});
</script>