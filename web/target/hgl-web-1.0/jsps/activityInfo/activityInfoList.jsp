<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="col-10 hover" id="clause" style="margin-bottom:10px;">
	<thead class="bg-gray stock">
		<tr>
			<th>活动名称</th>
			<th>启动状态</th>
			<th>操作选项</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${activityInfos}" var="activityInfo">
		<tr id="${activityInfo.id}">			
			<td>${activityInfo.activityName}</td>
			<td>
				<c:if test="${activityInfo.status==1100}">已开始</c:if>
				<c:if test="${activityInfo.status==1102}">未开始</c:if>
			</td>
			<td>
				<a class="btn" href="javascript:void(0);" id="release" style="line-height:normal;" onclick="updateActivityInfoStatus(${activityInfo.id})">
					<c:if test="${activityInfo.status==1102}">发布</c:if>
					<c:if test="${activityInfo.status==1100}">下架</c:if>
				</a> |
				<a class="btn" href="javascript:void(0);" id="detail" style="line-height:normal;" onclick="displayActivityDetail(${activityInfo.id})">详情</a> |
				<a class="btn" href="javascript:void(0);" id="update" style="line-height:normal;" onclick="updateActivityInfo(${activityInfo.id})">修改</a> |
				<a class="btn" href="javascript:void(0);" id="delete" style="line-height:normal;" onclick="deleteActivityInfo(${activityInfo.id})">删除</a>
			</td>
		</tr>
	</c:forEach>   
	</tbody>                 	
</table>
<liguo:pagination pageAction="doSearchResult"/>