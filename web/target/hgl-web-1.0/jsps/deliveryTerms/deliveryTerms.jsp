<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
<tiles:put name="title" value="送货条款"/>
<tiles:put name="body" type="String">    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>送货条款</title>
	<link rel="stylesheet" href="${ctx}/css/me.css">
	<script type="text/javascript" src="${ctx}/js/hgl/deliveryTerms.js"></script>
</head>
<body>
	<div class="area me">
		<!-- <h2>我的惠给力</h2> -->            
		<div class="main-right pull-right">
			<div class="details" style="line-height:normal;">
				<div class="details-list">
					<div style="margin-bottom:5px;">
						<a href="javascript:void(0)" class="btn-bg" onclick="addDeliveryTerms(${deliveryTerm.id})">
							<span style="margin-right:0;">新增送货条款</span>
						</a>
					</div>
					
					<table class="col-10 hover" id="clause">
						<thead class="bg-gray">
							<tr>
								<th>描述</th>
								<th>计算方式</th>
								<th>起始距离(公里)</th>
								<th>截止距离(公里)</th>
								<th>起始金额(元)</th>
								<th>截止金额(元)</th>
								<th>运费(元)</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${deliveryTerms}" var="deliveryTerm">
								<tr id="deliveryTerm${deliveryTerm.id}">          
									<td id="description${deliveryTerm.id}">${deliveryTerm.description}</td>
									<td id="calcMethod${deliveryTerm.id}">
										<c:if test="${deliveryTerm.calcMethod==410}">按距离</c:if>
										<c:if test="${deliveryTerm.calcMethod==412}">按总金额</c:if>
									</td>
									<td id="minDistance${deliveryTerm.id}">${deliveryTerm.minDistance}</td>
									<td id="maxDistance${deliveryTerm.id}">${deliveryTerm.maxDistance}</td>
									<td id="minAmount${deliveryTerm.id}">${deliveryTerm.minAmount}</td>
									<td id="maxAmount${deliveryTerm.id}">${deliveryTerm.maxAmount}</td>
									<td id="freight${deliveryTerm.id}">￥${deliveryTerm.freight}</td>
									<td>
										<a href="javascript:void(0)" class="btn" style="line-height:normal;" onclick="updateDeliveryTerms(${deliveryTerm.id})">修改</a> |
										<a href="javascript:void(0)" class="btn" style="line-height:normal;" onclick="deleteDeliveryTerms(${deliveryTerm.id})">删除</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>			
				<div style="margin-top:20px;color:#aaa;">
					<p>操作说明：</p>
					<p>1、您可以通过"新增送货条款"按钮进入"添加送货条款信息"输入卡来添加送货条款信息.</p>
					<p>2、描述表示您希望给客户看到的送货条款内容.</p>
					<p>3、计算方式表示您希望按照那种方式来计算客人运费.选择"按距离"表示您希望按照运输距离来计算运费;选择"按总金额"表示
					       您希望按照用户购买货品的总金额来计算运费.</p>
					<p>4、例如您在描述中输入:总金额在5000元到10000元之前并且距离小于100公里运费500元,那么计算方式选择"按总金额",
					  "起始金额(元)"填写5000,"截止金额(元)"填写10000,此处有距离限制,所以"截止距离(公里)"填写100.</p>
					
				</div>	
				<div style="margin-top:20px;color:#aaa;">
					<p>当前规则:</p>
					<ol style="padding-left:20px;">
						<c:forEach items="${deliveryTerms}" var="deliveryTerm">
							<li style="list-style:decimal;">
								<c:if test="${deliveryTerm.calcMethod==410}">
									运输距离在${deliveryTerm.minDistance}公里到${deliveryTerm.maxDistance}公里之间
									<c:if test="${deliveryTerm.minAmount!=null}">且总金额大于${deliveryTerm.minAmount}元</c:if>
									<c:if test="${deliveryTerm.maxAmount!=null}">小于${deliveryTerm.maxAmount}元</c:if>
									运费${deliveryTerm.freight}元
								</c:if>
								<c:if test="${deliveryTerm.calcMethod==412}">
									购买总金额在${deliveryTerm.minAmount}元到${deliveryTerm.maxAmount}元之间
									<c:if test="${deliveryTerm.minDistance!=null}">且运输距离大于${deliveryTerm.minDistance}公里</c:if>
									<c:if test="${deliveryTerm.maxDistance!=null}">小于${deliveryTerm.maxDistance}公里</c:if>
									运费${deliveryTerm.freight}元
								</c:if>
							</li>
						</c:forEach>
					</ol>				
				</div>	
			</div>					
		</div>		
	</div>
	
	<div id="dialogAdd" class="add-pick info" style="display:none;"><!-- 新增 弹出框 -->
   		<form class="form-horizontal" id="deliveryTerms_Add" name="deliveryTerms_Add">     				 			
   			<div class="clear" id="descriptionBox">
				<label>描述</label>
				<input type="text" name="description" id="description" maxlength="250">
				<span id="descriptionError"></span>
			</div>
			<div class="clear" id="calcMethodBox">
				<label>计算方式</label>
				<select name="calcMethod" id="calcMethod">
					<option value="0">===请选择===</option>
					<option value="410">按距离</option>
					<option value="412">按总金额</option>
				</select>	
				<span id="calcMethodError"></span>				
			</div>
			
			<div class="clear" id="minDistanceBox">
				<label>起始距离(公里)</label>
				<input type="text" name="minDistance" id="minDistance" maxlength="20" class="">
				<span id="minDistanceError"></span>
			</div>
			<div class="clear" id="maxDistanceBox">
				<label>截止距离(公里)</label>
				<input type="text" name="maxDistance" id="maxDistance" maxlength="20" class="">
				<span id="maxDistanceError"></span>
			</div>
			<div class="clear" id="minAmountBox">
				<label>起始金额(元) </label>    
				<input type="text" name="minAmount" id="minAmount" maxlength="20" class="">
				<span id="minAmountError"></span>
			</div>
			<div class="clear" id="maxAmountBox">
				<label>截止金额(元)</label>
				<input type="text" name="maxAmount" id="maxAmount" maxlength="20" class="">
				<span id="maxAmountError"></span>
			</div>
			<div class="clear" id="freightBox">
				<label>运费(元)</label>
				<input type="text" name="freight" id="freight" maxlength="20" class="">
				<span id="freightError"></span>
			</div>	
   		</form>	 	
   	</div>
	
	<div id="dialogUpd" class="add-pick info" style="display:none;"><!-- 修改弹出框 -->
   		<form class="form-horizontal" id="deliveryTerms_Upd" name="deliveryTerms_Upd"> 
   			<input type="hidden" id="id" name="id">    		
   			<input type="hidden" id="version" name="version">		  			
   			<div class="clear" id="descriptionBox">
				<label>描述</label>
				<input type="text" name="description" id="description" maxlength="250" class="">
				<span id="descriptionErrorUpd"></span>
			</div>
			<div class="clear" id="calcMethodBox">
				<label>计算方式</label>
				<select name="calcMethod" id="calcMethod">
					<option value="0">===请选择===</option>
					<option value="410">按距离</option>
					<option value="412">按金额</option>
				</select>
				<span id="calcMethodErrorUpd"></span>	
			</div>
			<div class="clear" id="minDistanceBox">
				<label>起始距离(公里)</label>
				<input type="text" name="minDistance" id="minDistance" maxlength="20" class="">
				<span id="minDistanceErrorUpd"></span>
			</div>
			<div class="clear" id="maxDistanceBox">
				<label>截止距离(公里)</label>
				<input type="text" name="maxDistance" id="maxDistance" maxlength="20" class="">
				<span id="maxDistanceErrorUpd"></span>
			</div>
			<div class="clear" id="minAmountBox">
				<label>起始金额(元) </label>    
				<input type="text" name="minAmount" id="minAmount" maxlength="20" class="">
				<span id="minAmountErrorUpd"></span>
			</div>
			<div class="clear" id="maxAmountBox">
				<label>截止金额(元)</label>
				<input type="text" name="maxAmount" id="maxAmount" maxlength="20" class="">
				<span id="maxAmountErrorUpd"></span>
			</div>
			<div class="clear" id="freightBox">
				<label>运费(元)</label>
				<input type="text" name="freight" id="freight" maxlength="20" class="">
				<span id="freightErrorUpd"></span>
			</div>	
   		</form>	 	
   	</div>
   	
   	
   	<div id="dialogDlt" class="add-pick info" style="display:none;"><!-- 删除提示框 -->
   		<input type="hidden" id="dltId" name="dltId">
   		<p>您确认删除该条记录吗?</p>
   	</div>
   	
</body>
</html>
</tiles:put>
</tiles:insert>
