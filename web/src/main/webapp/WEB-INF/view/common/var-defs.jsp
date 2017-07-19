<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@page pageEncoding="UTF-8"%>
<c:set var="ctx" value="<%=request.getContextPath() %>"></c:set>
<!-- global vars list -->
<script type="text/javascript">
var ctx='${ctx}';
var isLogin='${isLogin}';
</script>

<fmt:setBundle basename="application" var="bundle"/> 