<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<c:forEach var="item" items="${programList}" varStatus="s">
		<div style="background-image: url('<%=pathCh%>/images/bk.png');height:115PX; background-repeat:no-repeat; margin: 0 auto;">
			<table>
				<tr>
					<td width="110"><img width="95" height="95" style="margin: 5px;" src="<%=pathCh%>${item.image}"></td>
					<td width="340">
					<div style="font-size: 16px;height:25px; margin-top: 0px; "><strong>${item.title}</strong></div>
					
					<div style="margin-top: 0px;width:390;height:60px; text-overflow: ellipsis;float:left;overflow:hidden;word-warp:break-word;word-brek:break-all;">${item.remark}</div></td>
					
					<td width="80">
						<div style="/* background-color: #FFFF00;color: RED; */margin-right: 4px;height: 103px;">
							<p align="center" style="margin-top: 0px;margin-bottom:0px; line-height:50px; height: 50px;vertical-align: middle;"><strong>票数</strong></p>
							<p align="center" style="margin-top: 0px;">
							<c:if test="${isShow}">
								${item.praise}
							</c:if>
							<c:if test="${!isShow}">
								***
								<script>
									clearInterval(refreshEv);
								</script>
							</c:if>
							</p>
						</div>
					</td>
				</tr>
			</table>
		</div>
		
	</c:forEach>
