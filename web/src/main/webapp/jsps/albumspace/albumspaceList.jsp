<ul class="clear">
	<input type="hidden" id="errorTips" value="${errorTips }"/>
	<c:forEach items="${tbAlbumSpaceList}" var="item">
		<li class="albumSpace" id="albumSpace${item.id}"><a
			href="javascript:void(0);"> <%-- generateAlbumspaceImage?id=${item.imageUrl}&imgName=${item.imageNameNow} --%>
				<%-- 				<img src="${ctx}/images/banner1.jpg" title="${saveGoodInfo.imageNameOld}" style="width:100%;height:160px;"> --%>
				<img
				src="${ctx}/albumspace/generateAlbumspaceImage?id=${item.imageUrl}&imageNameNow=${item.imageNameOld}"
				/>
		</a>
			<div class="albumSpace1">
				<p style="padding-left: 0;">${fn:split(item.imageNameOld,'.')[0]}</p>
				<input type="text" maxlength="30" id="${item.id }" name="${item.version }" data-endsWith="${fn:split(item.imageNameOld,'.')[1]}" value="${fn:split(item.imageNameOld,'.')[0]}">
				<%-- <textarea maxlength="30" id="${item.id }" name="${item.version }" data-endsWith="${fn:split(item.imageNameOld,'.')[1]}">${fn:split(item.imageNameOld,'.')[0]}</textarea> --%>
				<input type="hidden" name="hideText" />
			</div>
			<div class="bianji">
				<span class="rechristen">重命名</span>
				<span class="reomve">删除</span>
			</div></li>
	</c:forEach>
</ul>
