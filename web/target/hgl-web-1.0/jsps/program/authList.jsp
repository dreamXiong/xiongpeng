<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>节目投票（点赞）审核</title>
<script type="text/javascript" src="${ctx }/js/jquery/jquery1.9.2/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx }/js/wap/program.js"></script>
</head>
<body>

	<form id="talkForm" method="POST">
		<div id="key_talk_list">
			  <table width="1198" class="aBreast y_xian">
			  <tr>
			    <th width="199" >名称</th>
			    <th width="145" >标题</th>
			    <th width="200">简介</th>
			    <th width="100">投票数</th>
			    <th width="100">投票（点赞）状态</th>
			    <th width="100">操作</th>
			  </tr>
			  
			  <c:if test="${not empty programList}">
			  	<c:forEach var="item" items="${programList}" varStatus="s">
			  		<tr id="tr_${item.id }" align="center">
					    <td><p>${item.name}</p>
					    
					    	<input type="hidden" name="talkId" value="${item.id }" /> 
					    </td>
					    <td>
					    	<p><em>${item.title}</em></p>
					    </td>
					    <td><p>${item.remark}</p></td>
					    <td><p>${item.praise}</p></td>
					     <td>
					     	<p>
					     		<c:if test="${item.praiseFlag ==0}">不允许点赞</c:if>
					     		<c:if test="${item.praiseFlag ==1}">允许点赞</c:if></p>
					     </td>
					    <td>
					    	<c:if test="${item.praiseFlag ==0}">
					    		<input type="button" value="允许投票（点赞）" onclick="authOpt('${item.id}')"; />
					    	</c:if>
					    	
					    </td>
					   
					  
					  
			   		</tr>
			   		
			  	</c:forEach>
			  </c:if>
			 		
			
			</table>

			
		</div>
	</form> 
	
	<input type="hidden" id="ctx" value="${ctx}"/>
</body>
</html>