<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="table table-bordered table-hover dataTable text-center table-bordered">
	<tr class="info">
	  <th>姓名</th>
	  <th>帐号</th>
	  <th>手机号码</th>
	  <th>邮箱</th>
	  <th>角色</th>
	  <th>类型</th>
	  <th>状态</th>
	  <th>操作</th>
	</tr>
                      
	<c:forEach items="${adminUsersDto}" var="adminUser">
		<tr>			
			<td>${adminUser.name}</td>
			<td>${adminUser.userName}</td>
			<td>${adminUser.mobile}</td>
			<td>${adminUser.email}</td>
			<td>${adminUser.roleName}</td>                 		
			<td>
				<c:if test="${adminUser.type==102}">厂家</c:if>
				<c:if test="${adminUser.type==104}">经销商</c:if>
				<c:if test="${adminUser.type==106}">终端客户</c:if>
				<c:if test="${adminUser.type==108}">地推人员</c:if>
				<c:if test="${adminUser.type==110}">仓库管理员</c:if>
				<c:if test="${adminUser.type==112}">店铺管理员</c:if>
			</td>
			<td id="td${adminUser.id}">
				<c:if test="${adminUser.status==132}">启用</c:if>
				<c:if test="${adminUser.status==134}">禁用</c:if>
			</td>
			<td>
				<c:if test="${adminUser.userName!='admin'}">				
					<a href="javascript:void(0)" class="btn btn-primary btn-xs" id="updateStatus${adminUser.id}" onclick="updateAdminUserStatus(${adminUser.id})">
						<c:if test="${adminUser.status==132}">禁用</c:if>
						<c:if test="${adminUser.status==134}">启用</c:if>
					</a>
					<a href="initUpdateAdminUser?id=${adminUser.id}" class="btn btn-primary btn-xs">修改</a>
					<a href="javascript:void(0)" id="adminUser${adminUser.id}" onclick="deleteAdminUser(${adminUser.id})" class="btn btn-primary btn-xs">删除</a>
					<a href="javascript:void(0)" class="btn btn-primary btn-xs" id="passwordCfm" onclick="resetPassword(${adminUser.id})">重置密码</a>
				</c:if>	
			</td>
		</tr>
	</c:forEach>   
</table>
<liguo:pagination pageAction="doSearchResult"/>
         