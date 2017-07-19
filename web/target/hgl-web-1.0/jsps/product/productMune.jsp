<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
	<tiles:put name="title" value="产品信息" />
	<tiles:put name="body" type="String">
		<c:set value="takl" var="modalName"></c:set>
		<html>
<link rel="stylesheet" href="${ctx}/css/me.css">

<script src="${ctx}/js/hgl/product.js"></script>
<body>
	<div class="main-right pull-right">
		<form action="${ctx}/import/productImport"
			enctype="multipart/form-data" method="post" class="form-inline"
			id="key_${modalName}_qryFrm">
			<div class="stock clear">
				<div class="pull-left">
<!-- 					<button type="button" class="btn" id="downloadTemplate">下载导入模板</button> -->
					<a  class="btn-bg" href="${ctx}/import/downloadTemplate">
						<span>下载导入模板</span>
					</a>
					<%-- <a type="button" class="btn" href="${ctx}/brand/productType">添加新分类</a> --%>
				</div>
			</div>
			<div id="uploader">
				<div class="queueList" style="position:relative;">
					<div id="dndArea" class="placeholder">
						<div id="filePicker" class="webuploader-container">
							<div class="webuploader-pick">点击选择文件</div>
							<div id="rt_rt_1al4b7b8skp91j50ct219991ksv1"
								style="position: absolute; top: 178px; left: 367px; width: 168px; height: 44px; overflow: hidden; bottom: auto; right: auto;">
								<input  type="file" name="productData"
									class="webuploader-element-invisible" multiple="multiple"
									accept=""><label
									style="opacity: 0; width: 100%; height: 100%; display: block; cursor: pointer; background: rgb(255, 255, 255);"></label>
							</div>
						</div>
					</div>     
					<ul class="filelist"></ul>
					<div class="load"  style="display:none;background:url(${ctx}/images/noload.gif) no-repeat center center #fff;position:absolute;height:100%;width:100%;top:0;">
				</div>
				</div>
				<div style="color:#aaa;">
					<li>温馨提示:使用请下载导入模板,阅读帮助</li>
				</div>
				
			</div>
		</form>        
	</div>
</body>
<script>
	EcWeb.currentModalName = '${modalName}';
	$(function() {
		$(".webuploader-element-invisible").on("change", function() {
			debugger;
			var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
			var frm = $("#" + frmId);
			frm.submit();
		});
		$('input[type="file"]').change(function(){   
			$('.load').fadeIn();
		});                          
	});
	
	$("#downloadTemplate").on("click",function(){
		alert();
		$.ajax({
			type:'post',   
			url:ctx+'/import/downloadTemplate',
			success:function(data){
				var success = data.success;
				if(success==1){
				toastr.success(data.message, "操作提示:");
				}
				if(success==2){
				toastr.error(data.message, "操作提示:");
				}
			 },
			 error:function(){
				 alert(11111);
			 }
		});
		
	});
	
</script>
		</html>
	</tiles:put>
</tiles:insert>