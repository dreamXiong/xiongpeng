<%@page pageEncoding="UTF-8"%>
<c:set var="ctx" value="<%=request.getContextPath() %>"></c:set>
<fmt:setBundle basename="application" var="bundle"/> 
<script type="text/javascript">
var ctx='${ctx}';
var isLogin='${isLogin}';
</script>