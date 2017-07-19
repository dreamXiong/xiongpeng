<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
<tiles:put name="title" value="活动管理"/> 
<tiles:put name="body" type="String">   
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="${ctx}/css/jquery-ui.min.css">
	<link rel="stylesheet" href="${ctx}/css/jq-ui-rest.css">
	<link rel="stylesheet" href="${ctx}/css/me.css">
	<link rel="stylesheet" href="${ctx}/css/base.css">
	<link rel="stylesheet" href="${ctx}/css/index.css">
	<link rel="stylesheet" href="${ctx}/css/Font-Awesome/css/font-awesome.min.css">
	<!-- 图片上传 -->
	<script src="${ctx}/js/jquery/uploadPreview.min.js"></script>
	<script src="${ctx}/js/hgl/product.js"></script> 
	<script src="${ctx}/js/jquery/jquery.ui.widget.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload.js"></script>
	<script src="${ctx}/js/jquery/jquery.iframe-transport.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload-process.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload-validate.js"></script>
	
	<script src="${ctx}/jsps/activityInfo/activityInfoAdd.js"></script>
	<script src="${ctx}/js/hgl/activityInfoDetail.js"></script>
	<script src="${ctx}/jsps/activityInfo/activityInfoUpd.js"></script>
	<script src="${ctx}/js/hgl/activityInfo.js"></script>	
	<script src="${ctx}/js/hgl/activityInfoAdd.js"></script>
	<script src="${ctx}/js/hgl/activityInfoUpd.js"></script>
	<script type="text/javascript">
	function getObjectURL(file) {
		var url = null ; 
		if (window.createObjectURL!=undefined) { // basic
			url = window.createObjectURL(file) ;
		} else if (window.URL!=undefined) { // mozilla(firefox)
			url = window.URL.createObjectURL(file) ;
		} else if (window.webkitURL!=undefined) { // webkit or chrome
			url = window.webkitURL.createObjectURL(file) ;
		}
		return url ;
	}
	/*上传图片*/	
	$(function(){		
		<!--修改-->
		$(".pUpdateImg").fileupload({	
		    url : "validateImg",
		    autoUpload : true,
		    singleFileUploads : true,
		    acceptFileTypes : /(\.|\/)(|gif|jpe?g|png|bmp)$/i,
		    maxFileSize : 5097152,
		    change: function (e, data) { 
		        $.each(data.files, function (index, file) {
		        });
		    }
		}).on(
	        'fileuploaddone',
	        function(e, data) {
	        	console.log(data.result.code);
	        	if (data.result.code == '0'){	        		
	        		var imgName = data.result.imgNo.split("_");
	            	$("#dialogUpdate #"+imgName[0]+"_v").val(data.result.imgNo);
	            }else{
	            
	            }
		}).on('fileuploadsubmit', function(e,data){
	        data.formData = {imgNo: $(this).attr("id")};  //如果需要额外添加参数可以在这里添加
	    });
	}); 

	</script>
</head>
<c:set value="activityInfo" var="modalName"></c:set>
<body>
	<div class="area me">          
		<div class="main-right pull-right">
			<div class="details" style="line-height:normal;">
				<div class="details-list stock_nav">					
					<form action="" method="post" name="activityInfoForm" id="key_${modalName}_qryFrm">
						<div>
							名称<input type="text" name="activityName" id="activityName" style="width:140px;margin-right:25px;">
							状态 <select id="status" name="status" style="margin-right:10px;">
								<option value="">==全部==</option>
								<option value="1102">未开始</option>
								<option value="1100">已开始</option>								
							</select>
							<a class="btn-bg" href="javascript:void(0);" onclick="submitform()" id="query">
								<span style='margin-right:0;'>查询</span>
							</a>	
							<a class="btn-bg" href="javascript:void(0);" onclick="addActivityInfo()" id="add">
								<span style="margin-right:0;">添加</span>		
							</a>																				
						</div>
					</form>
				</div>		      
				<div id="key_${modalName}_list" class="col-sm-12">
					<%@include file="activityInfoList.jsp" %>
				</div>						
			</div>					
		</div>		
	</div>
	
		
	<div id="dialogAdd" class="add-pick info" style="display:none;"><!-- 新增 弹出框 -->
   		<form class="form-horizontal" id="activityInfoAdd" name="activityInfoAdd"> 
   			<input type="hidden" name="titleImage" id="titleImage_val"/>
           	<input type="hidden" name="detailImageOne" id="detailImageOne_val"/>
           	<input type="hidden" name="detailImageTwo" id="detailImageTwo_val"/>
           	<input type="hidden" name="detailImageThree" id="detailImageThree_val"/>	
   			<div class="clear" id="activityNameBox">
				<label style="width:118px;">活动标题</label>
				<input type="text" name="activityName" id="activityName" maxlength="60">
				<div id="activityNameError" style="padding-left:128px;font-size:12px;color:red;"></div>
			</div>
			<div class="clear" id="imageBox">
				<label style="width:118px;">标题图片</label>
				<div class="pull-left">
                  <input type="file" class="pImg" name="imgFile" id="titleImage" accept="image/*" class="upload-input"/>
                  <div id="titleImageDiv" class="showDiv">
                    <img id="titleImageShow" style="width:100%;height:100%;">
                  </div>
                  <span class="icon-plus"></span>
                </div>  				
			</div>	
			<div id="titleImageError" style="padding-left:128px;font-size:12px;color:red;margin-top:5px;"></div>
			<div class="clear" id="statusBox">
				<label style="width:118px;">活动状态</label>
				<p>
					<input type="radio" name="status" id="status" value="1102" checked="checked">未开始
					<input type="radio" name="status" id="status" value="1100">已开始
				</p>				
			</div>			
			<div class="clear" id="detailBox">
				<label style="width:118px;">活动详情 </label>
				<textarea rows="5" id="activityDetail" name="activityDetail" style="width:325px;" maxlength="500"></textarea>
				<div id="detailError" style="padding-left:128px;font-size:12px;color:red;"></div>
			</div>
			<div class="clear" id="imageBox">
				<label style="width:118px;">图片</label>
				<div class="pull-left">
                  <input type="file" class="pImg" name="imgFile" id="detailImageOne" accept="image/*" class="upload-input"/>
                  <div id="detailImageOneDiv" class="showDiv">
                    <img id="detailImageOneShow" style="width:100%;height:100%;" name="detailImageOne">
                  </div>
                  <span class="icon-plus"></span>
                  
                </div>
                <div class="pull-left">
                  <input type="file" class="pImg" name="imgFile" id="detailImageTwo" accept="image/*" class="upload-input"/>
                  <div id="detailImageTwoDiv" class="showDiv">
                    <img id="detailImageTwoShow" style="width:100%;height:100%;">
                  </div>
                  <span class="icon-plus"></span>
                  
                </div>
                <div class="pull-left">
                  <input type="file" class="pImg" name="imgFile" id="detailImageThree" accept="image/*" class="upload-input"/>
                  <div id="detailImageThreeDiv" class="showDiv">
                    <img id="detailImageThreeShow" style="width:100%;height:100%;">
                  </div>
                  <span class="icon-plus"></span>
                 
                </div> 	
			</div>
			<div id="detailImageError" style="padding-left:128px;font-size:12px;color:red;margin-top:5px;"></div>	
   		</form>	 	
   	</div>
   	
   	
   	<div id="dialogDetail" class="add-pick info" style="display:none;"><!-- 活动详情弹出框 -->     				 			
  		<div class="clear" id="activityNameBox">
			<label>活动名称：</label>
			<p id="activityName" style="color:"></p>			
		</div>
		<div class="clear" id="imageBox">
			<label>标题图片</label>
			<div class="pull-left"> 
               	<img id="title_Image" style="width:100%;height:100%;">
            </div>  				
		</div>		
		<div class="clear" id="statusBox">
			<label>活动状态：</label>
			<p id="status"></p>
		</div>
		<div class="clear" id="detailBox">
			<label>活动详情： </label>
			<p id="activityDetail"></p>
		</div>
		<div class="clear" id="imageBox">
			<label>详情图片：</label>
			<div class="pull-left">
				<img id="detailImage_One" style="width:100%;height:100%;">      
            </div>
            <div class="pull-left">
				<img id="detailImage_Two" style="width:100%;height:100%;">
            </div>
            <div class="pull-left">
                <img id="detailImage_Three" style="width:100%;height:100%;">   
            </div> 	   			
		</div>		
   	</div>
   	
   	
   	<div id="dialogUpdate" class="add-pick info" style="display:none;"><!-- 修改 弹出框 -->
   		<form class="form-horizontal" id="activityInfoUpd" name="activityInfoUpd"> 
   			<input type="hidden" name="titleImage" id="titleImage_v"/>
           	<input type="hidden" name="detailImageOne" id="detailImageOne_v"/>
           	<input type="hidden" name="detailImageTwo" id="detailImageTwo_v"/>
           	<input type="hidden" name="detailImageThree" id="detailImageThree_v"/>
   			<div class="clear" id="activityNameBox">
				<label style="width:118px;">活动标题</label>
				<input type="hidden" name="id" id="id">
				<input type="text" name="activityName" id="activityName" maxlength="60">
				<div id="activityNameError" style="padding-left:128px;font-size:12px;color:red;"></div>
			</div>
			<div class="clear" id="imageBox">
				<label style="width:118px;">标题图片</label>
				<div class="pull-left">
                  <input type="file" class="pUpdateImg" name="imgFile" id="titleImage_Upd" accept="image/*" class="upload-input"/>
                  <div id="titleUpdImageDiv" class="showDiv">
                    <img id="titleUpdImageShow" style="width:100%;height:100%;">
                  </div>
                  <span class="icon-plus"></span>                 
                </div>  				
			</div>
			<div id="titleUpdImageError" style="padding-left:128px;font-size:12px;color:red;margin-top:5px;"></div>
			<div class="clear" id="statusBox">
				<label style="width:118px;">活动状态</label>
				<p>
					<input type="radio" name="status" id="status" value="1102">未开始
					<input type="radio" name="status" id="status" value="1100">已开始
					<div id="statusError" style="padding-left:128px;font-size:12px;color:red;"></div>
				</p>				
			</div>			
			<div class="clear" id="detailBox">
				<label style="width:118px;">活动详情 </label>
				<textarea rows="5" id="activityDetail" name="activityDetail" style="width:325px;" maxlength="500"></textarea>
				<div id="detailError" style="padding-left:128px;font-size:12px;color:red;"></div>
			</div>
			<div class="clear" id="imageBox">
				<label style="width:118px;">图片</label>
				<div class="pull-left">
                  <input type="file" class="pUpdateImg" name="imgFile" id="detailImageOne_Upd" accept="image/*" class="upload-input"/>
                  <div id="detailUpdImageOneDiv" class="showDiv">
                    <img id="detailUpdImageOneShow" style="width:100%;height:100%;" name="detailImageOne">
                  </div>
                  <span class="icon-plus"></span>
                  
                </div>
                <div class="pull-left">
                  <input type="file" class="pUpdateImg" name="imgFile" id="detailImageTwo_Upd" accept="image/*" class="upload-input"/>
                  <div id="detailUpdImageTwoDiv" class="showDiv">
                    <img id="detailUpdImageTwoShow" style="width:100%;height:100%;">
                  </div>
                  <span class="icon-plus"></span>
                  
                </div>
                <div class="pull-left">
                  <input type="file" class="pUpdateImg" name="imgFile" id="detailImageThree_Upd" accept="image/*" class="upload-input"/>
                  <div id="detailUpdImageThreeDiv" class="showDiv">
                    <img id="detailUpdImageThreeShow" style="width:100%;height:100%;">
                  </div>
                  <span class="icon-plus"></span>
                  
                </div> 				
			</div>		
			<div id="detailImageUpdError" style="padding-left:128px;font-size:12px;color:red;margin-top:5px;"></div>
   		</form>	 	
   	</div>
   	
   	<div id="dialogDlt" class="add-pick info" style="display:none;"><!-- 删除提示框 -->
   		<input type="hidden" id="dltId" name="dltId">
   		<p>您确认删除该条记录吗?</p>
   	</div>
   	
   	<div id="dialogRelease" class="add-pick info" style="display:none;"><!-- 删除提示框 -->
   		<input type="hidden" id="relId" name="relId">
   		<p>您确认发布该活动吗?</p>
   	</div>
	<script type="text/javascript">
	EcWeb.currentModalName = '${modalName}';
	/*表单提交*/
	function submitform(){
		debugger;
    	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		var frm = $("#" + frmId);
		var params=frm.serialize();
		$.ajax({
	       type : "POST",
	       url : 'doSearchResult',
	       data : params,
	       success : function(response) {
	            $("#" + dataDomId).html(response);
	       }
		 }); 
	 }
	</script>
</body>
</html>
</tiles:put>
</tiles:insert>