<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insert beanName="indexPage">
<tiles:put name="title" value="积分商城类型添加" />
<tiles:put name="body" type="String">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>积分商城类型添加</title>
	<!--文件上传 -->
	<script src="${ctx}/js/jquery/uploadPreview.min.js"></script>
	<script src="${ctx}/js/hgl/brand.js"></script> 
	<script src="${ctx}/js/jquery/jquery.ui.widget.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload.js"></script>
	<script src="${ctx}/js/jquery/jquery.iframe-transport.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload-process.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload-validate.js"></script>
	<script src="${ctx}/js/hgl/integralMallType.js"></script> 
	<c:set var="ctx" value="${ctx}"/>
	<style>
	#goodsImageDiv{
		width:165px;
		height:100px;
		position: relative;
	}
	#goodsImageDiv img{
		width:100%;
		height:100%;
	}
	#goodsImageDiv span{
		position: absolute;
		left:170px;
		top:5px;
		width:160px;
	}
	.onerrInput{
	  border:solid 1px red ;
	} 
	
	</style>
</head>
<body class="skin-blue fixed">
<div class="wrapper">
  <div class="content-wrapper">
  	<section class="content-header">
      <h1>我的主页
        <small>积分商城类型添加</small>
      </h1>
    </section>
    <section class="content">
    	<div class="row no-margin">       
    		<div class="box box-primary">
    			<div class="box-body">
		             <form action="${ctx }/integralMallType/insertIntegralMallType" id="saveIntegralMallType" method="post" class="form-horizontal">
		              <input name="goodsTypeImage" type="hidden" id="goodsTypeImage">
		              <div class="form-group">
		                <label  class="col-sm-2 control-label">商品类型名称</label>
		                <div class="col-sm-8">
		                  <input type="text" id="goodsTypeName" class="form-control inputNotNull" maxlength="50" name="goodsTypeName" />
		                </div>
		              </div> 
		             <div class="form-group"> 
		                <label  class="col-sm-2 control-label">商品类型图片</label>
		                <div class="col-sm-8" style="display:inline">
		                 <div class="input-group" id="imgErrorDiv">   
		                    <input type="text"  class="form-control" id="input1" value="">
		                    <input type="file" name="imgFile" class="form-control inputNotNull" id="input2" style="opacity:0;position:absolute;top:0;">
		                    <div class="input-group-addon">浏览...</div>
		                 </div>    
		                   <span style="color: red; position: absolute;width:200px ;right:-200px;top:10px" id="imgError" ></span>     
		                  <div id="goodsImageDiv" style="border:1px solid #d2d6de;margin-top:5px ">     
		                    <img id="goodsImageShow" src="">
		                    <span style="width:500px">建议传165*100px的图片，必须是.gif,jpeg,jpg,png格式且不大于5MB。</span>
		                  </div>
		              	</div>
		              </div>
		              <div class="form-group">
		                <label  class="col-sm-2 control-label">商品类型描述</label>
		                <div class="col-sm-8">
		                  <textarea type="text" id="goodsTypeDescribe" class="form-control inputNotNull" maxlength="500" name="goodsTypeDescribe" rows="2"></textarea>
		                </div>
		              </div>  
		              <div class="row">
		                <div class="col-sm-6 text-right">
		                  <button type="button" onclick="subAddIntegralMallType();" class="btn btn-primary">确定</button>
		                </div>
		                <div class="col-sm-6 text-left">
		                  <button onclick="window.location.href='${ctx}/integralMallType/index'" type="button" class="btn btn-default">取消</button>
		                </div>
		              </div>
		             </form>
    			 </div>
    		</div>
    	</div>    
    </section>
  </div>
</div>
</body>
</html>
<script>
$(function(){
	new uploadPreview({UpBtn: "input2", DivShow: "goodsImageDiv", ImgShow: "goodsImageShow" });
	$("#input2").fileupload({
	    url : "../integralMallType/validateImg",
	    autoUpload : true,
	    singleFileUploads : true,
	    acceptFileTypes : /(\.|\/)(|gif|jpe?g|png|bmp)$/i,
	    maxFileSize : 5097152
	}).on('fileuploaddone',
        function(e, data) {
            if (data.result.code == '0'){
            	ImgInfoMsg = true;
                new uploadPreview({ UpBtn: "input2", DivShow: "goodsImageDiv", ImgShow: "goodsImageShow" });
                $("#imgErrorDiv").removeClass("onerrInput");
            	$("#imgError").text("");
            	$("#goodsTypeImage").val(data.result.imgName);
            }else{
            	new uploadPreview({ UpBtn: "input2", DivShow: "goodsImageDiv", ImgShow: "goodsImageShow" });
            	$("#imgErrorDiv").addClass("onerrInput");
            	$("#imgError").text("文件不大于5MB");
            	ImgInfoMsg = false;
            }
	});
});
</script>
</tiles:put>
</tiles:insert>