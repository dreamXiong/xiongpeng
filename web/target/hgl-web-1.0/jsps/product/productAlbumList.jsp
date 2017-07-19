<ul class="clear">
	<c:forEach items="${tbAlbumSpaceList}" var="item">
		<li><img
			src="${ctx}/albumspace/generateAlbumspaceImage?id=${item.imageUrl}&imageNameNow=${item.imageNameOld}">
				<span data-endWith="${fn:split(item.imageNameOld,'.')[1]}"> ${fn:split(item.imageNameOld,'.')[0]} </span></li>
	</c:forEach>
</ul>