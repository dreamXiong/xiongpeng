<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="table table-bordered table-hover dataTable text-center table-bordered">
	<tr class="info">
	  <th>姓名</th>
	  <th>帐号</th>
	  <th>手机号码</th>
	  <th>类型</th>
	  <th>状态</th>
	  <th>备注</th>
	  <th>操作</th>
	</tr>
                      
	<c:forEach items="${webUserDtos}" var="webUser">
		<tr>			
			<td>${webUser.name}</td>
			<td>${webUser.userName}</td>
			<td>${webUser.mobile}</td>               		
			<td>
				<c:if test="${webUser.typeId==114}">师傅</c:if>
			</td>			
			<td id="td${webUser.id}">
				<c:if test="${webUser.state==0}">待审核</c:if>
				<c:if test="${webUser.state==1}">审核通过</c:if>
				<c:if test="${webUser.state==2}">审核拒绝</c:if>
			</td>
			<td>${webUser.remark}</td>
			<td>
				<c:if test="${webUser.userName!='admin'}">
					<a href="doInitReviewWorker?id=${webUser.id}" class="btn btn-primary btn-xs" id="updateState${webUser.id}">审核</a>						
					<a href="javascript:void(0)" class="btn btn-primary btn-xs" id="passwordCfm" onclick="updateWebUserPsd(${webUser.id})">重置密码</a>
				</c:if>	
			</td>
		</tr>
	</c:forEach>   
</table>
<liguo:pagination pageAction="doSearchWorker"/>