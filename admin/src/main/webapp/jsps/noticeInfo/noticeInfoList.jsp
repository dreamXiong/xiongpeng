<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="table table-bordered table-hover dataTable text-center table-bordered">
	<tr class="info">
		<th>公告ID</th>
		<th>公告名称</th>
		<th>类型</th>
		<th>发布时间</th>
		<th>发布人</th>
		<th>操作选项</th>
	</tr>
	<c:forEach items="${list}" var="noticeInfo">			
	<tr >
		<td>${noticeInfo.id}</td>
		<td>${noticeInfo.noticeName}</td>
		<td>
			<c:choose>
				<c:when test="${noticeInfo.noticeType==1}">升级</c:when>
				<c:when test="${noticeInfo.noticeType==2}">公告</c:when>
			</c:choose>
		</td>

		<td>
			<jsp:useBean id="createDt" class="java.util.Date" />  
			<jsp:setProperty name="createDt" property="time" value="${noticeInfo.createDt}" />  
			<fmt:formatDate value="${createDt}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
		</td>
		<td>${noticeInfo.createBy}</td>
		<td>
			<a href="queryNoticeInfoDetail?id=${noticeInfo.id}" class="btn btn-primary btn-sm">详情</a>
			<a href="initUpdateNoticeInfo?id=${noticeInfo.id}" class="add btn btn-primary btn-sm" id="updBtn">修改</a>&nbsp;
			<a href="#" class="delete btn btn-primary btn-sm " onclick="deleteNoticeInfo(${noticeInfo.id})" id="dtBtn${noticeInfo.id}" data-toggle="modal" data-target="#model">删除</a>
		</td>					
	</tr>
    </c:forEach>   
</table>
<liguo:pagination dataDomId="key_noticeInfo_list"  pageAction="doSearchResult"/>
