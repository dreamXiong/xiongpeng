<%@page pageEncoding="UTF-8"%>
	<c:forEach var="item" items="${adminRoleList}" varStatus="s">
		<li onclick="selectRole('${item.id}',this);"
			class="adminUser_${s.index} clearfix">
			<div class="pull-left">
				<input type="hidden" id="adminRole_${s.index}" value="${item.id}">
				${item.name}
			</div>
		</li>
	</c:forEach>
