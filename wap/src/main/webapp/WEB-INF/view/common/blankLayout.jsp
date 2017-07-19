<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@include file="/WEB-INF/view/common/styles.jsp" %>
<title> <fmt:message key="page_title" bundle="${bundle}" />   ---  <tiles:getAsString name="title" /></title>
<!--[if IE 6]>
<script type="text/javascript" src="/WEB-INF/view/js/philip/DD_belatedPNG.js" ></script>
<script type="text/javascript">DD_belatedPNG.fix('*');</script>
<![endif]-->
<body>
	<tiles:insert attribute="body" /> 
<%@include file="/WEB-INF/view/common/javascript.jsp" %>
</body>
</html>