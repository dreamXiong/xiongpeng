<%@page pageEncoding="UTF-8"%>
<div class="viewport">	
	<c:forEach var="acItem" items="${activityDtos}" varStatus="s">
	<div class="info-list"> 
		
		<div class="text-center timer">
			<liguo:dateFormatLabel value="${acItem.createDt }" pattern="yyyy-MM-dd  HH:mm:ss" /></td>
		</div>
		
		<c:if test="${acItem.letterType == 0 }" var="isSend">
			<div class="clear">
				<p class="text-ellipsis" style="background:#eee;" title="我">我</p>
				  <c:if test="${acItem.type ==0 }">
					<p>${acItem.content }</p>
			 	 </c:if>	
			 	 <c:if test="${acItem.type ==1 }">       
					<p>
						<img src="${ctx}/activity/displayImage?id=${acItem.activityId}&imageName=titleImage.jpg">
						<span>${acItem.activityName}</span>
					</p>
				</c:if>
				 
				</div>  
				<div class="user text-center clear">             
					<div class="pull-right text-right" title="${acItem.recipientname}">
						发给了：${acItem.userNames }
					</div>
				</div>  
		</c:if>
		              
		<c:if test="${!isSend}" >
				<div class="clear left">
				<p class="text-ellipsis" style="width:60px;background:#eee;" title="${acItem.recipientname }">${acItem.recipientname }</p>
				 <c:if test="${acItem.type ==0 }">
					<p>${acItem.content }</p>
			 	 </c:if>	
			 	 <c:if test="${acItem.type ==1 }">       
					<p>
						<img src="${ctx}/activity/displayImage?id=${acItem.activityId}&imageName=titleImage.jpg">
						<span>${acItem.activityName}</span>
					</p>
				</c:if>  
				</div>  
		</c:if>
		       
		</div>
	</c:forEach>
	<div id="box"></div>
	</div>
	
