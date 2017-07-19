<%@page pageEncoding="UTF-8"%>
	<script src="${ctx}/js/jquery/jquery1.9.2/ui/jquery.ui.widget.js"></script>
<form id="updateRules"  action="updateRules" class="form-horizontal" method="post">
	<input type="hidden" id="id" name="id" value="${rules.id}">
	 <input type="hidden"  id="version" name="version" value="${rules.version}"/> 
  	<div class="group">
  		<span style="margin-left: 150px;"><c:if test="${rules.recommendedType == 114 }">
		师傅
		</c:if>
		<c:if test="${rules.recommendedType == 106 }">
		个人
		</c:if>
		推荐
		<c:if test="${rules.isRecommendedType == 114 }">
		师傅
		</c:if>
		<c:if test="${rules.isRecommendedType == 106 }">
		个人
		</c:if></span>
  	</div>
  	
  	<div class="group">
  		<label>返利的方法</label>
  		<select id="way" name="way" value="${rules.way}">
  			<option value="0">直接给予</option>
  		</select>
  		
  	</div>
  	<div class="group">
  		<label>返利的 类型</label>
  		<select id="rewardType" name="rewardType" value="${rules.rewardType}">
  			<!-- <option value="0" >积分</option> -->
            <option value="1" >金额</option>
  		</select>	
  	</div>
  	<div class="group">
  		<label>返利的方式</label>
  		<select id="rewardWay" name="rewardWay" value="${rules.rewardWay}">
  			<option value="0">百分比</option>
            <option value="1">固定</option>
  		</select>	
  	</div>
  	<div class="group">
  		<label>返                利</label>
  		<input type="text" value="${rules.reward}" id="reward" name="reward"/>
  			
  	</div>
    <div class="group"><label></label><span id="msgrules" style="color: red;"></span>		</div>
  </form>
  <script>
  $("#rewardType").val("${rules.rewardType}");
  $("#rewardWay").val("${rules.rewardWay}");
  </script>