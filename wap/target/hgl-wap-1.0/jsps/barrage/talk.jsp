<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>弹幕审核</title>
<script type="text/javascript" src="${ctx }/js/jquery/jquery1.9.2/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx }/js/wap/talk.js"></script>
</head>
<body>

	<input type="button" value="刷新" onclick="flushData();" id="flushBtn"/>
	<form id="talkForm" method="POST">
		<div id="key_talk_list">
			<%@include file="talkList.jsp" %>
		</div>
	</form> 
	
	<input type="hidden" id="ctx" value="${ctx}"/>
</body>
</html>