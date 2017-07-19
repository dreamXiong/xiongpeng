<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
	<tiles:put name="title" value="我的等级" />
	<tiles:put name="body" type="String">
	<c:set value="takl" var="modalName"></c:set>	
	<html>


	<title>我的等级</title>
	<link rel="stylesheet" href="${ctx}/css/me.css">
	<script src="${ctx}/js/jquery/jquery1.9.2/ui/minified/jquery-ui.min.js"></script>
	<script src="${ctx}/js/hgl/datepicker-zh.js"></script>
	<script src="${ctx}/js/hgl/pick.js"></script>

<body>
<!-- 内容板块开始 -->
	<div>
		<div class="area me">
		
			
			<div class="main-right pull-right">
				<div class="details">
					<div class="details-list grade">
						<h3 class="bg-gray">我的等级${experience}</h3>
						<div class="clear">
							<div class="pull-left">
								<span>${shopName}</span> <em  id="grade" class="grade-icon" ></em>
							</div>
							<div class="pull-left">
								<i>总经验值 : </i> <i class="text-red" id="exp" >${experience}</i> ( 还须要 <i id="max">3000</i> 分才可以升级 )
							</div>
						</div>
						<h3 class="bg-gray">VIP等级示意图</h3>
						<div class="grade-bg-warp">
							
							<div class="grade-bg">
								<div class="grade-flag" id="flag" ></div>
							</div>
							<div class="grade-icon-warp">
							  <c:forEach items="${leveList}" var="item11" varStatus="s">
								<em class=" grade-icon grade-icon${s.index+1}">${item11.minExp}</em>
								
								</c:forEach>
							</div>
							<div>VIP成长体系包含10个等级，等级由“总积分”决定，不同等级享有特权不同。</div>
						</div>
						<h3 class="bg-gray">VIP等级区别对比</h3>
						<table class="col-10" style="margin-bottom:10px;">
							<thead>
								<tr>
									<td width="81">会员等级</td>
									<td width="248">经验值</td>
									<td width="119">经验获取规则</td>
									<td width="99">可获得折扣</td>
									<td width="140">优惠券使用规则</td>
									<td width="80">每月免费<br>提现次数</td>
									<td width="180">等级说明</td>
								</tr>
							</thead>
							<tbody>
						   <c:forEach items="${leveList}" var="item">
		                    <tr>
		                      <td>${item.levelName}</td>
		                      <td>${item.minExp} ≤ 经验值<  ${item.maxExp}  </td>
		                      <td>${item.expProportion}分</td>
		                      <td>${item.userSale}</td>
		                      <td>${item.couponRule}</td>
		                      <td>${item.cashNum}</td>
		                      <td>${item.levelRemark}</td>
		                    </tr>
		                    </c:forEach>
							</tbody>
						</table>
						<div class="explain">
							注 : "经验获取规则"表示购买成功后，每1元可获得经验，实际获得的经验为：金额乘以规则后四舍五入
							<p>"免费提现次数"表示每月值，在次月归零</p>
							<p>"优惠券使用规则"表示在购买时，每100元能使用的优惠卷，实际的计算值为：订单金额*优惠券使用规则/10后取整</p>
						</div>
						<h3 class="bg-gray">VIP等级如何成长</h3>
						<div style="padding-left:30px;">
							用户可通过获取经验值的方式，让“总经验值”上升，从而提升VIP等级
						</div>
						<h3 class="bg-gray">如何获得经验值</h3>
						<div>
							<p>1.惠给利用户可通过认证、购买操作、论坛行为等获得相应经验值；</p>
							<p>2.网站会不定期推出各类经验值活动，参与也可获得相应经验值，详情以活动说明为主。</p>
						</div>
					</div>
				</div>
			
					
			</div>
		</div>
	</div>
<!-- 内容板块结束 -->




</body>
<script>
	$(function() {
		
		var exp = [];
		$(".grade-icon-warp em").each(function(){
			exp.push($(this).html());
		});
		exp.shift();
		                     
		var seep = 1000;   
		var rank0 = 90/(exp[0]), rank1 = 91/(exp[1]-exp[0]), rank2 = 92/(exp[2]-exp[1]),rank3 = 92/(exp[3]-exp[2]),rank4 = 92/(exp[4]-exp[3]),rank5 = 92/(exp[5]-exp[4]);
		var rank6 = 93/(exp[6]-exp[5]),rank7 = 92/(exp[7]-exp[6]),rank8 = 92/(exp[8]-exp[7]);
		
		   
		    
			/* var ran = ${experience};   
			var seep = 1000;
			var rank1 = 90/3000, rank2 = 92/5000,rank3 = 92/7000,rank4 = 92/15000,rank5 = 92/20000;
			var rank6 = 93/30000,rank7 = 92/20000,rank8 = 92/50000,rank9 = 91/30000,rank10 = 92/ 30000000; */
			
		
			/* var exp = [3000,8000,15000,30000,50000,80000,100000,150000,180000]; */
			var arr =[
				exp[1]-exp[0],exp[2]-exp[1],exp[3]-exp[2],
				exp[4]-exp[3],exp[5]-exp[4],exp[6]-exp[5],
				exp[7]-exp[6],exp[8]-exp[7]
			];
			var ran = ${experience};      
			var num = $('#exp').text(ran);      
			num = ran;  
			if(num<exp[0]){  
				$('#max').text(exp[0]-num);   
				$('#flag').animate({      
					left:rank1*num,
				},seep);
			}else if(num>=exp[0]&&num<exp[1]){
				$('#grade').attr('class','grade-icon grade-icon2');
				$('#max').text(exp[1]-num);
				$('#flag').animate({
					left:(exp[0]*rank0)+(rank1*(num-exp[0])),
				},seep);
			}else if(num>=exp[1]&&num<exp[2]){
				$('#grade').attr('class','grade-icon grade-icon3');
				$('#max').text(exp[2]-num);
				$('#flag').animate({
					left:(exp[0]*rank0)+(rank1*arr[0])+(rank2*(num-exp[1])),
				},seep);
			}else if(num>=exp[2]&&num<exp[3]){
				$('#grade').attr('class','grade-icon grade-icon4');
				$('#max').text(exp[3]-num);
				$('#flag').animate({
					left:(exp[0]*rank0)+(rank1*arr[0])+(rank2*arr[1])+(rank3*(num-exp[2])),
				},seep);
			 }else if(num>=exp[3]&&num<exp[4]){
				$('#grade').attr('class','grade-icon grade-icon5');
				$('#max').text(exp[4]-num);
				$('#flag').animate({
					left:(exp[0]*rank0)+(rank1*arr[0])+(rank2*arr[1])+(rank3*arr[2])+(rank4*(num-exp[3])),
				},seep);
			}else if(num>=exp[4]&&num<exp[5]){
				$('#grade').attr('class','grade-icon grade-icon6');
				$('#max').text(exp[5]-num);
				$('#flag').animate({
					left:(exp[0]*rank0)+(rank1*arr[0])+(rank2*arr[1])+(rank3*arr[2])+(rank4*arr[3])+(rank5*(num-exp[4])),
				},seep);
			}else if(num>=exp[5]&&num<exp[6]){
				$('#grade').attr('class','grade-icon grade-icon7');
				$('#max').text(exp[6]-num);
				$('#flag').animate({
					left:(exp[0]*rank0)+(rank1*arr[0])+(rank2*arr[1])+(rank3*arr[2])+(rank4*arr[3])+(rank5*arr[4])
						+(rank6*(num-exp[5])),
				},seep);
			}else if(num>=exp[6]&&num<exp[7]){
				$('#grade').attr('class','grade-icon grade-icon8');
				$('#max').text(exp[7]-num);
				$('#flag').animate({
					left:(exp[0]*rank0)+(rank1*arr[0])+(rank2*arr[1])+(rank3*arr[2])+(rank4*arr[3])+(rank5*arr[4])+(rank6*arr[5])
					+(rank7*(num-exp[6])),
				},seep);
			}else if(num>=exp[7]&&num<exp[8]){
				$('#grade').attr('class','grade-icon grade-icon9');
				$('#max').text(exp[8]-num);
				$('#flag').animate({
					left:(exp[0]*rank0)+(rank1*arr[0])+(rank2*arr[1])+(rank3*arr[2])+(rank4*arr[3])+(rank5*arr[4])+(rank6*arr[5])
					+(rank7*arr[6])+(rank8*(num-exp[7])),
				},seep);
			}else if(num>=exp[8]){
				$('#grade').attr('class','grade-icon grade-icon10');
				$('#max').text('0');
				$('#flag').animate({
					left:(exp[0]*rank0)+(rank1*arr[0])+(rank2*arr[1])+(rank3*arr[2])+(rank4*arr[3])+(rank5*arr[4])+(rank6*arr[5])
					+(rank7*arr[6])+(rank8*arr[7])+20,          
				},seep);
			}


			$('#flag').attr('title',num);

		

		
    	
	});
</script>
</html>
	</tiles:put>
</tiles:insert>