<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="table table-bordered table-hover dataTable text-center table-bordered">
	<tr class="info">
	  <th>姓名</th>
	  <th>帐号</th>
	  <th>手机号码</th>
	  <th>邮箱</th>
	  <th>角色</th>
	  <th>类型</th>
	  <th width="30%">意见反馈</th>
	  <th>状态</th>
	  <th>操作</th>
	</tr>
                      
	<c:forEach items="${webUserDto}" var="webUser">
		<tr>			
			<td>${webUser.name}</td>
			<td>${webUser.userName}</td>
			<td>${webUser.mobile}</td>
			<td>${webUser.email}</td>
			<td>${webUser.roleName}</td>                 		
			<td>
				<c:if test="${webUser.typeId==102}">厂家</c:if>
				<c:if test="${webUser.typeId==104}">经销商</c:if>
				<c:if test="${webUser.typeId==106}">终端客户</c:if>
				<c:if test="${webUser.typeId==108}">地推人员</c:if>
				<c:if test="${webUser.typeId==110}">仓库管理员</c:if>
				<c:if test="${webUser.typeId==112}">店铺管理员</c:if>
			</td>
			<td>${webUser.feedBack}</td>
			<td id="td${webUser.id}">
				<c:if test="${webUser.state==1}">启用</c:if>
				<c:if test="${webUser.state==3}">禁用</c:if>

			</td>
			<td>
				<c:if test="${webUser.userName!='admin'}">
					<a href="javascript:void(0)" class="btn btn-primary btn-xs" id="updateState${webUser.id}" onclick="updateWebUserStatus(${webUser.id})">审核</a>						
					<a href="doInitUpdateWebUser?id=${webUser.id}" class="btn btn-primary btn-xs">修改</a>
					<a href="javascript:void(0)" class="btn btn-primary btn-xs" id="passwordCfm" onclick="updateWebUserPsd(${webUser.id})">重置密码</a>
				</c:if>	
			</td>
		</tr>
	</c:forEach>   
</table>
<liguo:pagination pageAction="doSearchResult"/>