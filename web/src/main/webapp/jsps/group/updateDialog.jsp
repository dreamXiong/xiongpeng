<%@page pageEncoding="UTF-8"%>
  <form id="updateGroup"> 
		<div>
	    <label style="width:46px;">名称:</label>
	    <input type="text" id="updateName" name="name" maxlength="6" onblur="updateValidateGroup();" class="inputNotNull" value="${tbUserGroup.name }" />
	    <div id="updateNameError" style="width: 180px;color: red;padding: 0 0 0 60px;display: none;" >组名重复</div>
	    <input type="hidden" id="groupId" name="id" value="${tbUserGroup.id }" />
		</div> 
		<div>
  		<label style="width:46px;">折扣:</label>
	    <input type="text" id="discount" value="${tbUserGroup.discount }" maxlength="2" class="inputNotNull number" name="discount">  
		</div>
		
    <div id="key_info_discountdiscount" maxlength="200" style="display: none;">例: 设置95，则客户购买了100元货品提交订单时，享受9.5折优惠（范围在0~100）. </div>
    <div>
	    <label style="width:46px;">说明:</label>
	     <textarea id="remark" maxlength="200" name="remark" style="width:330px;">${tbUserGroup.remark }</textarea>
    </div>
    <div style="padding-left:53px;padding-right:17px;font-size:12px;color:#aaa;margin-top:-8px;">
    	例: 设置95，则客户购买了100元货品提交订单时，享受9.5折优惠（范围在0~100）.  
    </div>
     <br>
  </form>