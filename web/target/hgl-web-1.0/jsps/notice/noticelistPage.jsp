<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<ul class="news-list">
	<c:forEach items="${noticeInfos}" var="noticeInfo">
		<li><a href="${ctx}/noticeInfo/getNoticeInfoDetail?id=${noticeInfo.id}"> 【<c:choose>
			<c:when test="${noticeInfo.noticeType==1}">升级</c:when>
			<c:when test="${noticeInfo.noticeType==2}">公告</c:when>
			</c:choose>】${noticeInfo.noticeName}
			</a> <small class="pull-right"><jsp:useBean id="createddatetime" class="java.util.Date"/>  
 			<jsp:setProperty name="createddatetime" property="time" value="${noticeInfo.createddatetime}" />  
 			<fmt:formatDate value="${createddatetime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></small>
 		</li>
	</c:forEach>
</ul>   
<liguo:pagination dataDomId="key_noticeInfo_list"  pageAction="searchNoticeInfo"/>