<!DOCTYPE html>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"> -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://jakarta.apache.org/struts/tags-tiles"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta content="no-cache" http-equiv="cache-control" />
	<tiles:definition id="blank" page="/WEB-INF/view/common/blankLayout.jsp">
	</tiles:definition>
	
	<tiles:definition id="login" page="/WEB-INF/view/common/loginLayout.jsp">
		<tiles:put name="header" value="/WEB-INF/view/common/header.jsp" />
		<tiles:put name="footer" value="/WEB-INF/view/common/footer.jsp" />
	</tiles:definition>
	<tiles:definition id="indexPage" page="/WEB-INF/view/common/indexPageLayout.jsp">
		<tiles:put name="header" value="/WEB-INF/view/common/indexPageHeader.jsp" />
		<tiles:put name="menu" value="/WEB-INF/view/common/menu.jsp" />
		<tiles:put name="footer" value="/WEB-INF/view/common/footer.jsp" />
	</tiles:definition>
</head>
</html>