<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="table table-bordered table-hover dataTable text-center table-bordered">
	<tr class="info">
	  <th>序号ID</th>
	  <th>用户账号</th>
	  <th>手机号码</th>
	  <th>用户类型</th>
	  <th>反馈意见</th>
	  <th>反馈时间</th>
	</tr>
                      
	<c:forEach items="${list}" var="feedBackDto">
		<tr>			
			<td>${feedBackDto.id}</td>
			<td>${feedBackDto.userName}</td>
			<td>${feedBackDto.mobile}</td>
			<td>
				<c:choose>
					<c:when test="${feedBackDto.typeId==104}">经销商</c:when>
					<c:when test="${feedBackDto.typeId==106}">终端客户</c:when>
					<c:otherwise>师傅</c:otherwise>
				</c:choose>
			</td>
			<td>${feedBackDto.feedBack}</td>                 		
			<td>
				<jsp:useBean id="createDt" class="java.util.Date"/>
				<jsp:setProperty name="createDt" property="time" value="${feedBackDto.createDt}"/>
				<fmt:formatDate value="${createDt}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>			
		</tr>
	</c:forEach>   
</table>
<liguo:pagination pageAction="doSearchResult"/>