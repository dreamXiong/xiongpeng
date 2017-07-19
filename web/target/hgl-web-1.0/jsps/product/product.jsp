<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
	<tiles:put name="title" value="产品信息" />
	<tiles:put name="body" type="String">
		<c:set value="takl" var="modalName"></c:set>
		<html>
<link rel="stylesheet" href="${ctx}/css/me.css">
<link rel="stylesheet" href="${ctx}/css/jquery-ui.min.css">
<link rel="stylesheet" href="${ctx}/css/jq-ui-rest.css">
<link href="${ctx}/css/toastr.css" rel="stylesheet" type="text/css" /> 
<script src="${ctx}/js/hgl/toastr.js"></script>
<script src="${ctx}/js/jquery/jquery1.9.2/ui/jquery.ui.widget.js"></script>
<script src="${ctx}/js/jquery/jquery.fileupload.js"></script>
<script src="${ctx}/js/jquery/jquery.fileupload-process.js"></script>
<script src="${ctx}/js/jquery/jquery.fileupload-validate.js"></script>
<script src="${ctx}/js/jquery/uploadPreview.min.js"></script>
<script src="${ctx}/js/hgl/product.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.form.js"></script>
<style>
	#btn{
		position:relative;
		overflow:hidden;
	}
	#btn input{
		position:absolute;
		height:100%;
		width:100%;
		padding:0;
		margin:0;
		top:0;
		left:0;
		opacity:0;
		filter:alpha(opacity = 0);
	}
</style>


<body>
	<c:set value="product" var="modalName"></c:set>
	<!-- 内容板块开始 -->
	<div class="area me">
		<div class="main-right pull-right">
			<div class="stock" id="reset-input" style="margin-bottom: 10px;">
				<form action="${ctx}/import/productImport"
					enctype="multipart/form-data" method="post" class="form-inline"
					id="key_${modalName}_qryFrm">
					<div class="stock_nav">
						名称 <input class="long" type="text" name="name"> 大类 <select
							class="form-control" name="mainId" id="mainIdSelect">
							<option value="">---请选择---</option>
							<c:forEach items="${mList}" var="item" varStatus="s">
								<option value="${item.id}">${item.name}</option>
							</c:forEach>
						</select> 品牌 <select class="form-control" name="brandId" id="brandIdSelect" style="margin-right:5px;">
							<option value="">---请选择---</option>
						</select>
						<div style="margin-top: 5px;display:inline-block;">
							<a class="btn-bg" onclick="submitform()" href="javascript:;">
								<span>查询</span>          
							</a> <a class="btn-bg" href="#" id="add"> <span>添加产品</span>
							</a>
						</div>
					</div>
				</form>
				<div id="key_${modalName}_list" class="col-sm-12">
					<%@include file="productList.jsp"%>
				</div>
			</div>

		</div>
	</div>
	<!-- 添加商品的modal开始 -->
	<div id="dialog1" class="add-pick" title="自有产品管理">
		<%-- <%@include file="addProduct.jsp"%> --%>
	</div>
	<!-- 详情商品的modal开始 -->
	<div id="dialog3" class="add-pick">
		<%-- <%@include file="productDetail.jsp"%> --%>
	</div>
	<!-- 导入modal开始 -->
	<div id="dialog4" title="提示"></div>
	<!-- 弹出层相册 -->
	<div id="dialog5" style="display: none;">
		<div>
<%-- 			<a class="btn-bg" href="${ctx }/albumspace/getMyAlbumspaceList" style="color:#fff;"> --%>
<!-- 				<span>去相册</span> -->
<!-- 			</a> -->
			<form onsubmit="return false;"
				enctype="multipart/form-data" method="post" class="form-inline"
				id="albumFileUpload">
				<input class="imageSearchToPro" />
				<button class="btn-bg" onclick="searchImage()">
					<span>搜索</span>
				</button>
				<a class="btn-bg" id="btn" href="${ctx }/albumspace/getMyAlbumspaceList" style="color:#fff;">
					<span>上传</span>
					<input type="file" name="file" style="border:1px solid #eee;"/>
				</a>
				
			</form>  
		</div>
		<div id="photo">
		</div>
	</div>
</body>
		</html>
		<script>
		$(function() {
			$('input[type="file"]').change(function(){  
				var fileVal =$('input[name="file"]').val();
				var options = {  
					       url : ctx+"/albumspace/upload",  
					       type : "POST",  
					       dataType : "json",  
					       success : function(data) {
					    	   toastr.success("上传成功","操作提示"); 
					    	   ajaxFileUpload();
					       }  
					    };
				$("#albumFileUpload").ajaxSubmit(options);
				 return false;  
			});                          
		});
		  function ajaxFileUpload() { 
			  $.ajax({
					type:'POST',
					url:ctx+'/albumspace/getMyAlbumspaceListToPro',
					success:function (data){   
						$("#photo").html(data);
					}
				});
		}  		
		EcWeb.currentModalName = '${modalName}';
			function searchImage(){
			var searchVal =$(".imageSearchToPro").val();
			$.ajax({
				type:'POST',
				url:ctx+'/albumspace/searchImageToPro',
				data:{"likeKey":searchVal},   
				success:function (data){     
					$("#photo").html(data);
				}
			});
		};
		
// 		function gotoAlbum(){
// 			alert("11");
// 			window.location.href=${ctx }+"/albumspace/getMyAlbumspaceList";
// 		}
			
			
		function aaaa(){
			alert("11");
		}
			
		</script>
	</tiles:put>
</tiles:insert>