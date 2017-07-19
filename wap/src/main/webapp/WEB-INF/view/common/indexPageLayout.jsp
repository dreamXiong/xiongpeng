<!DOCTYPE html>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"> -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>惠给利</title>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta content="no-cache" http-equiv="cache-control" />
<%@include file="/WEB-INF/view/common/taglib-defs.jsp" %>
<%@include file="/WEB-INF/view/common/var-defs.jsp" %>
<%@include file="/WEB-INF/view/common/styles.jsp"%>
<%@include file="/WEB-INF/view/common/javascript.jsp"%>
</head>
<body>	
	
	<tiles:insert attribute="header" />
	<div class="container">
		<div class="area">
			<div>
				<tiles:insert attribute="menu" />
			</div>
			<div class="clear">
				<tiles:insert attribute="body" />
			</div>
		</div>
	</div>
	<tiles:insert attribute="footer" />
</body>
</html>