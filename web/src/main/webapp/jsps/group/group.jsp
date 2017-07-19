<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
	<tiles:put name="title" value="分组管理" />
	<tiles:put name="body" type="String">
	<c:set value="group" var="modalName"></c:set>	
	<html>
		<link rel="stylesheet" href="${ctx}/css/me.css">
		<link rel="stylesheet" href="${ctx}/css/jquery-ui.min.css">
		<link rel="stylesheet" href="${ctx}/css/jq-ui-rest.css">
		<script src="${ctx}/js/hgl/datepicker-zh.js"></script>
		<script src="${ctx}/js/hgl/group.js"></script>
	<body>
<!-- 内容板块开始 -->
		
		<div class="area me">
			<div class="main-right pull-right">
			 <div id="key_stopOrder_list">
				 <c:if test="${fn:length(gList) < 5}">
				 	<button class='btn-bg' type="button" onclick="addGroup();">
						<span>添加分组</span>
					</button>
				</c:if>
				<button class='btn-bg' type="button" onclick="goUserGroup();">
					<span>用户分组</span>
				</button>
				<div id="updateGroupListInfo">
	 				<%@include file="groupList.jsp" %>
	 			</div>
	 		</div>
		 	</div
		</div>	
	<div id="dialog2" class="dialog add-pick info">
	  <form id="addGroup"> 
	  		<div>
			    <label style="width:46px;">名称:</label>
			    <input type="text" maxlength="6" onblur="addValidateGroup();" id="addName" class="inputNotNull" name="name">
			    <div id="addNameError" style="width: 180px;color: red;padding: 0 0 0 60px;display: none;" >组名重复</div>
	  		</div> 
	  		<div>
		  		<label style="width:46px;">折扣:</label>
			    <input type="text" id="discount" maxlength="2" class="inputNotNull number" name="discount">
	  		</div>
		    <div id="key_info_discountdiscount" style="display: none"></div>
		    <div>
			    <label style="width:46px;">说明:</label>
			     <textarea id="remark" maxlength="200" name="remark" style="width:330px;"></textarea>
		    </div>
		    <div style="padding-left:53px;padding-right:17px;font-size:12px;color:#aaa;margin-top:-8px;">
		    	例: 设置95，则客户购买了100元货品提交订单时，享受9.5折优惠（范围在0~100）.  
		    </div>
		     <br>
	  </form>
	  <input type="hidden" id="userGroupId" />
	</div>
	
		<!-- 内容板块结束 -->
		<div id="dialog" class="dialog">
			  <form>
			  	<span>该分组存在用户，不能删除！</span>
			  </form>
		</div>
		
		<!-- 内容板块结束 -->
		<div id="dialog4" class="dialog">
			  <form>
			  	<span>你确认要删除该分组吗?</span>
			  </form>
		</div>
		
		<div id="dialog3" class="dialog add-pick info">
		 	<%@include file="updateDialog.jsp" %>
		</div>
	</html>
	<script type="text/javascript">
/* 	/hgl-web/userGroup/index */
		function goUserGroup(){
			window.location.href = ctx+'/userGroup/index';
		}
	</script>
	</tiles:put>
</tiles:insert>