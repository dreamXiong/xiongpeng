<!DOCTYPE html>
<%@page pageEncoding="UTF-8"%>
<c:set var="ctx" value="<%=request.getContextPath() %>"></c:set>
<!-- global vars list -->
<script type="text/javascript">
var ctx='${ctx}';
var isLogin='${isLogin}';
</script>

<fmt:setBundle basename="application" var="bundle"/> 