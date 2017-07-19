<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="/WEB-INF/view/common/var-defs.jsp" %>
<%@include file="/WEB-INF/view/common/taglib-defs.jsp" %>
<%@include file="/WEB-INF/view/common/styles.jsp" %>
<meta content="IE=Edge" http-equiv="X-UA-Compatible" />
<body>
	<tiles:insert attribute="header" />
	<tiles:insert attribute="body" />
	<tiles:insert attribute="footer" />		
<%@include file="/WEB-INF/view/common/javascript.jsp" %>
</body>
</html>