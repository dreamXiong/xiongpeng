<%@page pageEncoding="UTF-8"%>
<c:forEach var="item" items="${addressList}" varStatus="i">
	<div class="adde-list list-title${item.checkFlag == 0 ? ' active':''}" id="${item.id }">
		<div class="adde-look">
			<p class="names">${item.recipient }&nbsp;&nbsp;(${item.provinceName })</p>
			<div class="age">
				<p class="text-ellipsis" title="${item.extensionField }">${item.extensionField }</p>
				<p>${item.phone }</p>
			</div>
			<div class="operation">
				<a id="modifyAddress" href="javascript:;" onclick="modifyAddress('${item.id}','${item.recipient}','${item.province}','${item.city}','${item.area}','${item.street}','${item.streetDetail }','${item.phone}','${item.code}','${item.telephone}','${item.provinceName }')">修改</a>
				<a class="remove" id="${item.id }" href="javascript:;">删除</a>
				<a href="javascript:;" id="default${item.id }" class="default${item.checkFlag == 0 ? ' none':''}">设为默认地址</a>
			</div>
		</div>
	</div>
</c:forEach>
	<div class="adde-list" id="addAdde">
		<span class="iconfont">&#xe61f;</span>
		<p class="text-center">添加新地址</p>
	</div>
