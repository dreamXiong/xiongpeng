<%@ page language="java" pageEncoding="UTF-8"%>

  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
      	 <button class="btn btn-default pull-right btn-xs" onclick="showAddUserTr();" type="button">添加</button>
        <h4 class="modal-title">已有管理员</h4>
      </div>
      <div class="modal-body">
      
        <table class="table table-bordered" id="table1">
          <tr>
            <th>用户名</th>
            <th>姓名</th>
            <th>操作</th>
          </tr>
          <c:forEach var="item" items="${warehouseUserList}" varStatus="s">
	          <tr>
	            <td>${item.userAccount }</td> 
	            <td>${item.userName }</td>
	            <td><button class="btn btn-primary" onclick="deleteWarehouseUserInfo('${item.id}');" type="button">删除</button></td>
	          </tr> 
          </c:forEach>
           <tr id="trShow" style="display:none;">
           <td colspan="2">
	           <select id="tbAdminUser" class="form-control">
       		       <c:forEach var="item" items="${tbAdminUser}" varStatus="s">
       		       		<option value="${item.id }">${item.name }</option>
       		       </c:forEach>
	           </select>
           </td>
           <td>
           		<button class="btn btn-primary" onclick="saveAddUserInfo();" type="button">保存</button>
			</td>
		</tr>
        </table>
       
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      </div>
    </div> 
  </div>
<script>
function showAddUserInfo(id){
	$("#warehouseId").val(id);
	   $.ajax({
	        type: "POST",
	        url: "showAddUserInfo",
	        data:{
	        	wId:id
	        },
	        success: function(response){
	        	$("#allot").html(response);
	        }
	    });
     $('#allot').modal('show');
	}
function deleteWarehouseUserInfo(id){
	 var warehouseId = $("#warehouseId").val();
	  var tbAdminUserId = id;
	  $.ajax({
	      type: "POST",
	      url: "deleteWarehouseUserInfo",
	      data:{
	    	  wId:warehouseId,
	    	  userId:tbAdminUserId
	      },
	      success: function(response){
	      	$("#allot").html(response);
	      }
	  });
}
function saveAddUserInfo(){
  var warehouseId = $("#warehouseId").val();
  var tbAdminUserId = $("#tbAdminUser").val();
  $.ajax({
      type: "POST",
      url: "saveAddWarehouseUserInfo",
      data:{
    	  wId:warehouseId,
    	  userId:tbAdminUserId
      },
      success: function(response){
      	$("#allot").html(response);
      }
  });
}
function showAddUserTr(){
	$("#trShow").show();
}
</script>