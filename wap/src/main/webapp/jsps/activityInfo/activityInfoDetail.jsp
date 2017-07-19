<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<c:set value="活动详情" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<link rel="stylesheet" href="${ctx}/css/my.css">
	<%@include file="../common/common.jsp"%>        
                
</head>	      
<body>     
	<%@include file="../common/header.jsp"%>                                
</body>       
	<div class="container" style="margin-bottom:10px;">               
		<h4 style="line-height:30px;padding:0 8px;" class="text-hidden">活动名称：
			<span style="font-weight:normal;">${activityInfo.activityName}</span>          
		</h4><!-- 活动名称 -->    
		<h4 style="padding:5px 8px 0;">活动详情：</h4>	
		<p style="line-height:1.2;font-size:0.9rem;padding:5px 8px;">
			${activityInfo.activityDetail} 
		</p><!-- 活动详情 -->
		<c:forEach items="${imageList}" var="image"><!-- 轮播图 -->
			<div style="padding:0 8px;">
				<img alt="" src="${ctx}/activityInfo/showActivityImage?id=${image.id}&imgName=${image.image}" style="width:100%;">
			</div>	
		</c:forEach>
	</div>
</html>