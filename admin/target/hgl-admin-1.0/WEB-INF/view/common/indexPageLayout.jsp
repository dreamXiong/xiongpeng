<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta content="no-cache" http-equiv="cache-control" />
<meta content="IE=Edge" http-equiv="X-UA-Compatible" />
<%@include file="/WEB-INF/view/common/taglib-defs.jsp" %>
<%@include file="/WEB-INF/view/common/var-defs.jsp" %>
<%@include file="/WEB-INF/view/common/styles.jsp" %>
<%@include file="/WEB-INF/view/common/javascript.jsp" %>
<body class="skin-blue fixed">

<div class="wrapper">
	<tiles:insert attribute="header" />
	<tiles:insert attribute="menu" />	
	<tiles:insert attribute="body" />	
	<tiles:insert attribute="footer" />	
</div>	

</body>
</html>