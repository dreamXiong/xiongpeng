<%@page pageEncoding="UTF-8"%>
	<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<c:set value="网站公告" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	<%@include file="../common/common.jsp"%>
</head>	
<body>
<div>
<%@include file="../common/header.jsp"%>
</div>
<div class="container container-order eva">
	<div class="eva-info box-shadow">
		<div style="text-align: center;color: #12adff;">${noticeInfo.noticeName}  --</div>
		<div style="text-align: center;">
		<liguo:dateFormatLabel
						value="${noticeInfo.createddatetime}" pattern="yyyy.MM.dd" />
		</div>
		<div style="margin-top: 5px;">
			<span>${noticeInfo.noticeContent}</span>
		</div>
		
	</div>
</div>
</body>
<script>

</script>
</html>
