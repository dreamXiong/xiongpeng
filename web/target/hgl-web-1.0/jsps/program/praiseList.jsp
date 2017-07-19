<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
</head>
<body style="margin: 0 auto;background-image: url('${ctx}/images/bg_p.jpg');">
<div style="width:100%;margin: 0 auto 0 ;" >
<div style="width:100%;background-image: url('${ctx}/images/head_bg.jpg') ;">
<img alt="" width="100%" src="${ctx}/images/head.jpg" >
</div>

		<c:forEach var="item" items="${programList}"  varStatus="s">	
		<div style="border:3px solid red; background-color:#ffffff; margin: 0 auto 5px;width:98%; height:105px;">
			<table width="100%">
				<tr>
					<td width="26%"><img width="100%" height="100%" src="${ctx}${item.image}"></td>
					<td width="2%"></td>
					<td width="55%">
					<div style="height:20px;"><strong>${item.title}</strong></div>
					<div style="margin-top: 0px; width:100%;text-overflow: ellipsis;height:73px; float:left;overflow:hidden;word-warp:break-word;word-brek:break-all;">${item.remark}</div>
					</td>
					<td width="15%">
						<span style="cursor: pointer;display: ${item.praiseStatu==null || item.praiseStatu == 0?'black':'none'};"  onclick="praiseClice(this,${item.id});">
							<img alt="投票" style="" width="100%"  src="${ctx}/images/praise1.png">
						</span>
						<span id="praise_${item.id}" style="display: ${item.praiseStatu!=null&&item.praiseStatu != 0?'black':'none'};">
							<img alt="已投票" style="" width="100%"  src="${ctx}/images/praise2.png">
						</span>
					</td>
				</tr>
			</table>
		</div>
		</c:forEach>
</div>
<script src="${ctx}/js/jquery/jquery1.9.2/jquery-1.8.3.js"></script>	
<script type="text/javascript">
function praiseClice(obj,programId){
	 $.ajax({
	        type: "POST",
	        url: "${ctx}/vote/praiseSave",
	        data: 'programId='+programId,
	        success: function (data) {
	        	if(data.code == 0){
	        		$(obj).hide();
	        		$("#praise_"+programId).show();
	        	}else if(data.code == 100){
	        		location.href='${ctx}/vote';
	        	}else{
	        		alert(data.message);
	        	}
	        }
	    });
}
</script>
</body>
</html>
