<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
	<tiles:put name="title" value="产品信息" />
	<tiles:put name="body" type="String">
		<c:set value="takl" var="modalName"></c:set>
		<html>

<script src="${ctx}/js/jquery/jquery1.9.2/ui/jquery.ui.widget.js"></script>
<script src="${ctx}/js/jquery/jquery.fileupload.js"></script>
<script src="${ctx}/js/jquery/jquery.fileupload-process.js"></script>
<script src="${ctx}/js/jquery/jquery.fileupload-validate.js"></script>
<script src="${ctx}/js/jquery/uploadPreview.min.js"></script>
<link href="${ctx}/css/toastr.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctx}/css/jquery-ui.min.css">

<link rel="stylesheet" href="${ctx}/css/me.css">
<link rel="stylesheet" href="${ctx}/css/jq-ui-rest.css">                          

<script src="${ctx}/js/hgl/toastr.js"></script>

<body>               
	<c:set value="product" var="modalName"></c:set>
	<!-- 内容板块开始 -->
	<div class="area me">
		<div class="main-right pull-right">            
			<div class="stock">
				<div class="stock_nav">
					<!--头部，相册选择和格式选择-->
					<div id="uploader">    
						<div class="queueList">
							<div id="dndArea" class="placeholder">
								<div id="filePicker"></div>
								<p>或将照片拖到这里，单次最多可选300张</p>
							</div>
						</div>
						<div class="statusBar" style="display: none;">
							<div class="progress">
								<span class="text">0%</span> <span class="percentage"></span>
							</div>
							<div class="info"></div>
							<div class="btns">
								<div id="filePicker2"></div>
								<div class="uploadBtn">开始上传</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${ctx}/js/webupload/jquery.js"></script>
	<script src="${ctx}/js/webupload/webuploader.js"></script>
	<script type="text/javascript" src="${ctx}/js/hgl/albumspace.js"></script>
</body>

<!-- 详情商品的modal开始 -->
</body>
		</html>
		<script>
			EcWeb.currentModalName = '${modalName}';
		</script>
	</tiles:put>
</tiles:insert>