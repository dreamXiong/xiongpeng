<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>    
<tiles:insert beanName="indexPage">
<tiles:put name="tile" value="街道管理"/>
<tiles:put name="body" type="String">    
<html>
<c:set value="address" var="modalName"></c:set>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>街道管理</title>
<script type="text/javascript" src="${ctx}/js/hgl/address.js"></script>
</head>
<body class="skin-blue fixed">
  <div class="content-wrapper">
  	<section class="content-header">
      <h1>
       	 我的主页
        <small>用户列表</small>
      </h1>
    </section>
   	<section class="content">
      <div class="row no-margin">
          <div class="box box-primary">
    	      <div class="box-body">
                  <form  onsubmit="return false;" class="form-horizontal" id="key_${modalName}_qryFrm" name="addressForm">
	                  <div  class="col-sm-2">
			                <div class="form-group">
			                  <label class="col-sm-4 control-label">省名称</label>
			                  <div class="col-sm-8">
			                    <input type="text" name="provinceName" class="form-control" placeholder="请输入省名称">
			                  </div>
			                </div>
			          </div>
			          <div  class="col-sm-2">
			                <div class="form-group">
			                  <label class="col-sm-4 control-label">城市名称</label>
			                  <div class="col-sm-8">
			                    <input type="text" name="cityName" class="form-control" placeholder="请输入市名称" size="5">
			                  </div>
			                </div>
			          </div>
			          <div  class="col-sm-2">
			                <div class="form-group">
			                  <label class="col-sm-4 control-label">区县名称</label>
			                  <div class="col-sm-8">
			                    <input type="text" name="countryName" class="form-control" placeholder="请输入区县名称" size="5">
			                  </div>
			                </div>
			          </div>
                  <div class="row">
                      <div  class="col-sm-2">
                      	<div class="form-group">
                  			<label class="col-sm-4 control-label">街道名称</label>
               				<div class="col-sm-8">
                   				<input type="text" name="streetName" class="form-control" placeholder="请输入街道名称" size="5">
               				</div>
               			</div>
              		  </div>		              		              
		              <div class="col-sm-2 ">
		              	  <a href="javascript:void(0)" class="btn btn-primary" onclick="submitform()">查询</a>
		              	  <a href="javascript:void(0)" class="btn btn-primary" onclick="addItem()">添加</a>
		              </div>
             	</div>
             </form>
          	<div id="key_${modalName}_list" class="col-sm-12">
               	<%@include file="addressList.jsp" %>
            </div>
     </div>
   </div>
  </div>
  <script type="text/javascript">
	EcWeb.currentModalName = '${modalName}';
	/*表单提交*/
	function submitform(){
    	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		var frm = $("#" + frmId);
		var params=frm.serialize();
		$.ajax({
		   type:"post",
		   url:"doSearchResult",
		   data:params,
		   success:function(response){
		        $("#" + dataDomId).html(response);
		    }
		}); 
	}
	</script>
    </section>
 </div>
 
 
<!-- 新增信息弹出层 --> 
<div class="modal fade" id="addressModal_Add" style="padding:0px;">
	<div class="modal-dialog modal-sm">
		<div class="modal-content" style="box-shadow:8px 8px 12px #3c8dbc;border-radius:10px;width:500px;">
      		<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="updStateClose" onclick="javascript:location.reload()"><span aria-hidden="true">&times;</span></button>
        		<span class="modal-title" style="background:#3c8dbc;border-radius:10px;color:white;font-size:14px;font-weight:normal;padding:4px;margin:0">新增信息 </span>       		
      		</div>
     		<div class="modal-body" id="addModalBody">
     			<div>
     				<label>
     					新增街道<input type="radio" id="addStreet" name="addType" checked="checked" class="addRadioItem" value="1">
     				</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     				<label>
     					新增区县<input type="radio" id="addCountry" name="addType" class="addRadioItem" value="2">
     				</label>     				
     			</div>
     			<div>
     				<label style="width:100px;">省直辖市名称</label>
     				<select id="provinceName" name="provinceName">
     					<option value='0'>===请选择===</option>
     				</select>
     			</div>
     			
     			<div>
     				<label style="width:100px;">城市名称</label>
     				<select id="cityName" name="cityName">
     					<option value='0'>===请选择===</option>
     				</select>
     				<span id="cityNameAddError"></span>
     			</div>   
     			<div id="streetBox">
	     			<div>
	     				<label style="width:100px;">区县名称</label>
	     				<select id="countryName" name="countryName">
	     					<option value='0'>===请选择===</option>
	     				</select>
	     				<span id="countryNameError"></span>
	     			</div> 
	     			<div>
	     				<label style="width:100px;">街道编码</label>
	     				<input type="text" id="streetId" name="streetId">    
	     				<span id="streetIdError"></span>				
	     			</div> 
	     			<div>
	     				<label style="width:100px;">街道名称</label>
	     				<input type="text" id="streetName" name="streetName">
	     				<span id="streetNameError"></span>
	     			</div>  
	     		</div>	
	     		
	     		<div id="countryBox" style="display:none;"> 
	     			<div>
	     				<label style="width:100px;">区县编码</label>
	     				<input type="text" name="countryId_Add" id="countryId_Add">    
	     				<span id="countryIdAddError"></span>				
	     			</div> 
	     			<div>
	     				<label style="width:100px;">区县名称</label>
	     				<input type="text" name="countryName_Add" id="countryName_Add">
	     				<span id="countryNameAddError"></span>
	     			</div>  
	     		</div>		  			  				
    		</div>
    		<div class="modal-footer" style="text-align:center">
				<button type="button" class="btn btn-primary" id="addStreetBtn">确定</button>
        		<button type="button" class="btn btn-default" data-dismiss="modal" onclick="javascript:location.reload()">取消</button>
    		</div>
    	</div>
	</div>
</div>



  <!-- 修改信息弹出层 --> 
<div class="modal fade" id="addressModal_Upd" style="padding:0px;">
	<div class="modal-dialog modal-sm">
		<div class="modal-content" style="box-shadow:8px 8px 12px #3c8dbc;border-radius:10px;width:500px;">    
			<div class="modal-header">        		
        		<p style="background:#3c8dbc;border-radius:3px;color:white;font-size:14px;padding:2px;margin:0">修改街道信息 <p>      		
      		</div>  		
     		<div class="modal-body" id="updModalBody">
     			 	  			  				
    		</div>
    		<div class="modal-footer" style="text-align:center">
				<button type="button" class="btn btn-primary" id="updStreetBtn">确定</button>
        		<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
    		</div>
    	</div>
	</div>
</div>

<!-- 删除街道弹出层 --> 
<div class="modal fade" id="addressModal_Dlt" style="padding:0px;">
	<div class="modal-dialog modal-sm">
		<div class="modal-content" style="box-shadow:8px 8px 12px #3c8dbc;border-radius:10px;width:300px;">
      		<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="updStateClose" onclick="javascript:location.reload()"><span aria-hidden="true">&times;</span></button>
        		<span class="modal-title" style="background:#3c8dbc;border-radius:10px;color:white;font-size:14px;font-weight:normal;padding:4px;margin:0">删除街道信息 </span>       		
      		</div>
     		<p style="text-align:center;">您确认删除该街道吗？</p>
    		<div class="modal-footer" style="text-align:center">
				<button type="button" class="btn btn-primary" id="dltStreetBtn">确定</button>
        		<button type="button" class="btn btn-default" data-dismiss="modal" onclick="javascript:location.reload()">取消</button>
    		</div>
    	</div>
	</div>
</div>
</body>
</html>
</tiles:put>
</tiles:insert>