<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${ctx}/js/jquery/jquery1.9.2/jquery-1.8.3.js"></script>	
<style type="text/css">
</style>
</head>
<body style="margin:0;">
<div style="width: 540px;height:1018px; margin: 0 auto;background-image: url('${ctx}/images/bj.png');backgroud-repeat:no-repeat;" >
<div style="width:540px;padding-left:20px;text-align:center;font-weight:800; height: 165px;font-size:25px;margin: 0 auto;line-height:165px; color: orange;">
	2016年度晚会节目单
</div>
		<c:forEach var="item" items="${programList}"  varStatus="s">	
		<div style="background-image: url('${ctx}/images/bk.png');height:115PX; background-repeat:no-repeat; margin: 0 auto;">
			<table>
				<tr>
					<td width="110"><img width="95" height="95" style="margin: 5px;" src="${ctx}${item.image}"></td>
					<td width="390">
					<div style="font-size: 16px;height:25px; margin-top: 0px; "><strong>${item.title}</strong></div>
					
					<div style="margin-top: 0px;width:390;height:60px; text-overflow: ellipsis;float:left;overflow:hidden;word-warp:break-word;word-brek:break-all;">${item.remark}</div></td>
				</tr>
			</table>
		</div>
		</c:forEach>
</div>
</body>
</html>
