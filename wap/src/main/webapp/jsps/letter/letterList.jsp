<%@page pageEncoding="UTF-8"%>
	<c:forEach var="item" items="${letters}" varStatus="s"> 
	<c:if test="${item.letterType == 1 }" var="isfasong">    
		<div class="letter-inser left">
			<p class="text-center">
				<span>
					<liguo:dateFormatLabel value="${item.createDt }" pattern="yyyy-MM-dd  HH:mm:ss" />
				</span>
			</p>
			<div class="col-10 clearfix">
				<div class="text-hidden">
					${item.recipientname }
				</div>
				<div class="details">
					<c:if test="${item.type ==0 }">
							${item.content }
					</c:if>	
					<c:if test="${item.type ==1 }">
						<a href="${ctx}/activityInfo/doSearchActivityInfo?id=${item.activityId}">
							<p class="text-hidden">${item.activityName}</p>
							<img src="${ctx}/shop/showActivityImage?id=${item.activityId}&imgName=titleImage.jpg" alt="">
						</a>
					</c:if>
				</div>
			</div>
		</div>
	</c:if>
	<c:if test="${!isfasong}" >   
	<div class="letter-inser right">
		<p class="text-center">
			<span>
				<liguo:dateFormatLabel value="${item.createDt }" pattern="yyyy-MM-dd  HH:mm:ss" />
			</span>
		</p>
		<div class="col-10 clearfix">
			<div class="details">
				<c:if test="${item.type ==0 }">
					${item.content }
				</c:if>	
				<c:if test="${item.type ==1 }">
					<a href="${ctx}/activityInfo/doSearchActivityInfo?id=${item.activityId}">
						<p class="text-hidden">${item.activityName}</p>
						<img src="${ctx}/shop/showActivityImage?id=${item.activityId}&imgName=titleImage.jpg" alt="">
					</a>
				</c:if>
			</div>
			<div class="text-hidden">
				我
			</div>
		</div>
	</div>
	</c:if>
	</c:forEach>
	
	

