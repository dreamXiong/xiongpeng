<%@page pageEncoding="UTF-8"%>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/base.css"/>  
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>  
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Font-Awesome/css/font-awesome.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/dist/css/swiper.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/layer-mobile/layer/need/layer.css">	
	<script src="${pageContext.request.contextPath}/js/common/jquery-1.12.2.min.js"></script>
	<script src="${pageContext.request.contextPath}/layer-mobile/layer/layer.js"></script>	
	<script src="${ctx}/js/common/jquery.lazyload.min.js"></script>
	<script src="${ctx}/js/dist/js/swiper.min.js"></script>
	<script src="${ctx}/js/dist/js/swiper.jquery.min.js"></script>  
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>  
	<%@taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
	<%@taglib prefix="liguo" uri="/WEB-INF/PageTagLib"%>  
	
	<c:set var="ctx" value="<%=request.getContextPath()%>"></c:set>
	<script type="text/javascript">
		var ctx='${ctx}';
		$(function(){
			$("img.lazy").lazyload({
				threshold : 20,                               //距离相片多少距离加载
				effect : "fadeIn",                            //加载方式<fmt:formatNumber value="${countOrderDto.sumTotalWeight}" pattern="#,##0.000" ></fmt:formatNumber>
			});
			
			//弹出nav导航
			$('.icon-reorder').click(function(event) {
		 		$(this).siblings('ul').toggleClass('click');
		 		event.stopPropagation();
		 	});
		 	$(document).click(function(event) {
		 		$('header .click').removeClass('click');
		 	});
			
			
			//全局的AJAX访问，处理AJAX清求时SESSION超时
			$.ajaxSetup({
			    contentType:"application/x-www-form-urlencoded;charset=utf-8",
			    complete:function(XMLHttpRequest,textStatus){
		          //通过XMLHttpRequest取得响应头，sessionstatus           
		          var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus"); 
		          if(sessionstatus=="timeout"){
					   //跳转到登录页面
		               window.location.href = ctx+"/login/";
			       }
			    }
			});
		});
		
		//用于页面异步刷新时图片懒加载失效问题，在aJax返回成功时调用
		function laxyImgFun(){
			$("img.lazy").lazyload({
				threshold : 20,                               
				effect : "fadeIn",                            
			});
		}
		
		/*返回上一页*/
		function return_prepage(){   
			history.back(-1);
		}
	</script>