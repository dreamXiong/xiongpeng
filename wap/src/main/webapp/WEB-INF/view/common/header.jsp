<%@page pageEncoding="UTF-8"%>
<div class="topBar">
	<%@include file="topBar.jsp" %>
</div>
<div class="top clearfix">
	<%-- <%@include file="topBar.jsp" %> --%>
	<div class="logo" style=" float:left;"><a href="${f:url('/')}"></a> </div>
	<div class="buildVersion"></div> 
</div>

<!-- 

<div class="logo">电商项目名称 </div>
<div class="user">
	<a href="${f:url('/login/redo')}" class="userLine">退出</a><a href="${f:url('/lizcare04')}" class="userLine">个人帐号管理</a><span>你好，
	<c:if test="${f:br(f:h(commonSessionDto.name)) != ''}">${f:br(f:h(commonSessionDto.name))}</c:if>
	<c:if test="${f:br(f:h(commonSessionDto.name)) == ''}">${f:br(f:h(commonSessionDto.account))}</c:if>
	</span><span style="padding-top:11px;"><img src="${f:url('/styles/images/top.user.jpg')}" width="12" height="14" alt=" " /></span>
</div>

 -->
 