 <%@ page language="java" pageEncoding="UTF-8"%>
  <div class="modal-dialog ">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">规则修改</h4>
      </div>
      <div class="modal-body">
         <form id="updateRecommended" action="updateRecommended" class="form-horizontal" method="post">
         <input type="hidden"  id="id" name="id" value="${recommendedType.id}"/> 
		 <input type="hidden"  id="version" name="version" value="${recommendedType.version}"/> 
		 
		 	<div class="group" style="margin-bottom:10px;">
		  		<span style="margin-left: 150px; font-weight:700;">
		  			<c:if test="${recommendedType.recommendedType == 114 }">师傅</c:if>
					<c:if test="${recommendedType.recommendedType == 106 }">个人</c:if>
					
					<c:if test="${recommendedType.recommendedType == 108 }">递推人员</c:if>
					<c:if test="${recommendedType.recommendedType == 104 }">经销商</c:if>
					>推荐>
					<c:if test="${recommendedType.isRecommendedType == 104 }">
					经销商
					</c:if>
		  		</span>
		  	</div>
		  	
		  	<div class="form-group">
		  		<label class="col-sm-3 control-label">返利的方法</label>
		  		<div class="col-sm-9">
		  			<select id="way" name="way" value="${recommendedType.way}" class="form-control">
			  			<option value="0">直接给予</option>
			  			<option value="1">经予</option>
			  		</select>
		  		</div>
		  		
		  		
		  	</div>
		  	<div class="form-group">
		  		<label class="col-sm-3 control-label">返利的 类型</label>
		  		<div class="col-sm-9">
		  			<select id="rewardType" name="rewardType" value="${recommendedType.rewardType}" class="form-control">
		  				<option value="0" >积分</option>
		           	 	<option value="1" >金额</option>
		  			</select>	
		  		</div>
		  		
		  	</div>
		  	<div class="form-group">
		  		<label class="col-sm-3 control-label">返利的方式</label>
		  		<div class="col-sm-9">
		  			<select id="rewardWay" name="rewardWay" value="${recommendedType.rewardWay}" class="form-control">
			  			<option value="0">百分比</option>
			            <option value="1">固定</option>
		  			</select>	
		  		</div>
		  		
		  	</div>
		  	<div class="form-group">
		  		<label class="col-sm-3 control-label">返利的金额</label>
		  		<div class="col-sm-9">
		  			<input type="text" value="${recommendedType.reward}" id="reward" name="reward" class="form-control"/>
		  		</div>
		  		
		  			
		  	</div>
		    <div class="group"><label></label><span id="msgrules" style="color: red;"></span>		</div>
       
        </form>
      </div>
      <div class="modal-footer">
        <div class="col-md-11">
          <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            <button type="button" onclick="updateRecommended();" class="btn btn-primary btn-sm">保存</button>
        </div>
      </div>
    </div>
  </div>
 <script>
 $("#rewardType").val("${recommendedType.rewardType}");
 $("#rewardWay").val("${recommendedType.rewardWay}");
 </script>