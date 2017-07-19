<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<ul class="news-list">
	<c:forEach items="${merchNotices}" var="merchNotice">
		<li><a href="${ctx}/merchnotice/doSearchMerchNoticeDetail?id=${merchNotice.id}"> 【<c:choose>
			<c:when test="${merchNotice.typeId==290}">招商公告</c:when>
			<c:when test="${merchNotice.typeId==292}">中标公告</c:when>
			</c:choose>】${merchNotice.name}
			</a> <small class="pull-right"><jsp:useBean id="merchCreateddatetime" class="java.util.Date"/>  
 			<jsp:setProperty name="merchCreateddatetime" property="time" value="${merchNotice.createddatetime}"/>  
 			<fmt:formatDate value="${merchCreateddatetime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></small>
 		</li>
	</c:forEach>
</ul>   
<liguo:pagination dataDomId="key_merchNotice_list" pageAction="doSearchResult"/>