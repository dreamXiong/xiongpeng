<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="cart-body">
<head>
	<meta charset="UTF-8">
	<c:set value="邀请分享" var="titleName"></c:set>
	<title><c:out value="${titleName}"/></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/my.css"/>  
	<%@include file="../common/common.jsp"%> 
</head>	
<body>

	<header class="cart verify-head ">
		<div>
	<%@include file="../common/header.jsp"%>
</div>
	</header>
	

<div class="container share">
		<div class="share-info">
			<p><span class="text-red">1.VIP抵用券：</span>使用邀请码注册的被邀请人，注册后立刻获得30元VIP抵用券。</p>
			<p><span class="text-red">2.现金奖励：</span>当被邀请人待收总额首次超过1万元(含)，邀请人在24小时内获得100元奖励。</p>
			<p><span class="text-red">3.积分奖励：</span>被邀请人待收总额首次超过1万元(含)，邀请人与被邀请人均可获得1000积分奖励。</p>
			<p><span class="text-red">4.提成奖励：</span>被邀请人注册后的36个自然月内，邀请人可得得到被邀请人每月月底账户待收的相应提成比例作为提成奖励。</p>
		</div>
		<div style="margin-bottom:10px;">我的推荐码：<!-- <input value="" readonly="readonly" style="width: 100px;"> -->${code}</div>
		<img alt="" src="logoCode?code=${code}" style="width: 120px;height: 120px;">
		<div>
			<table>
				<thead>
					<tr>
						<th>用户名</th>
						<th>注册时间</th>
						<th>返利</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="item" items="${recommendedDtos}" varStatus="s" >
					<tr>
						<td>${item.name}</td>
						<td>
							
							<liguo:dateFormatLabel
						value="${item.createDate}" pattern="yyyy-MM-dd  HH:mm:ss" />
						</td>
						<td>
						
						
							${item.earnings}
						
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			
		</div>

</div>



  		
</body>

</html>


