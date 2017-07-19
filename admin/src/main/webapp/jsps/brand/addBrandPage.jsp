<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insert beanName="indexPage">
	<tiles:put name="title" value="品牌添加" />
	<tiles:put name="body" type="String">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>品牌添加</title>
	<!--文件上传 -->
	<script src="${ctx}/js/jquery/uploadPreview.min.js"></script>
	<script src="${ctx}/js/hgl/brand.js"></script> 
	<script src="${ctx}/js/jquery/jquery.ui.widget.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload.js"></script>
	<script src="${ctx}/js/jquery/jquery.iframe-transport.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload-process.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload-validate.js"></script>
	<c:set var="ctx" value="${ctx}"/>
	<style>
	#imgdiv{
		width:165px;
		height:100px;
		position: relative;
	}
	#imgdiv img{
		width:100%;
		height:100%;
	}
	#imgdiv span{
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
      <h1>
        		我的主页
        <small>添加品牌</small>
      </h1>
    </section>
    <section class="content">
    	<div class="row no-margin">       
    		<div class="box box-primary">
    			 <div class="box-body">
             <form action="saveBrandPage" id="saveBrandPage" enctype="multipart/form-data" method="post" class="form-horizontal">
             
             <input name="imgNameInfo" type="hidden" id="imgNameInfo">
              <div class="form-group">
                <label  class="col-sm-2 control-label">品牌名称</label>
                <div class="col-sm-8">
                  <input type="text" id="nameErrorRm" onchange="addValidateBrandInfo();" 
                  		 onblur="deleteBrandClass('nameErrorRm');" class="form-control inputNotNull" maxlength="30" name="name" />
                  		  <span id="nameReVaildate" style="color: red; position: absolute;width:200px ;right:-200px;top:10px" id="imgError" ></span> 
                </div>   
              </div>
              <div class="form-group">
                <label  class="col-sm-2 control-label">品牌分类</label>
                <div class="col-sm-8"> 
                 <select class="form-control inputNotNull" onchange="addValidateBrandInfo();" name="producttypeId" id="producttypeId"> 
	                <c:forEach var="item" items="${mLists}" varStatus="s">
	                    	<option value="${item.id }"> ${item.name}</option>
	                 </c:forEach> 
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label  class="col-sm-2 control-label">公司名称</label>
                <div class="col-sm-8">
                  <input type="text" id="manufacturerNameErrorRm" onblur="deleteBrandClass('manufacturerNameErrorRm');" 
                  					class="form-control inputNotNull" maxlength="30" name="manufacturerName" />
                </div>
              </div>  
              <div class="form-group">
                <label  class="col-sm-2 control-label">平台网址</label>
                <div class="col-sm-8">
                  <input type="text" id="urlErrorRm" onblur="deleteBrandClass('urlErrorRm');" class="form-control inputNotNull" name="url" maxlength="50" placeholder="http://">
                </div>
              </div>
              <div class="form-group">
                <label  class="col-sm-2 control-label">状态</label>
                <div class="col-sm-8">
                 <select name="state" class="form-control">
                    <option value="0">显示</option>
                    <option selected="" value="1">隐藏</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label  class="col-sm-2 control-label">排序</label>
                <div class="col-sm-8">
                   <input name="sort" id="sortErrorRm" onblur="deleteBrandClass('sortErrorRm');" onchange="onCKeyupNumber('sortErrorRm');" type="text" maxlength="5" class="form-control inputNotNull" />
                </div>
              </div>
              <div class="form-group"> 
                <label  class="col-sm-2 control-label">品牌logo</label>
                <div class="col-sm-8" style="display:inline">
                <div class="input-group" id="imgErrorDiv">   
                    <input type="text"  class="form-control" id="input1">
                    <input type="file" name="imgFile" class="form-control inputNotNull" id="input2" style="opacity:0;position:absolute;top:0">
                    <div class="input-group-addon">浏览...</div>
                  </div>    
                   <span style="color: red; position: absolute;width:200px ;right:-200px;top:10px" id="imgError" ></span>     
                  <div id="imgdiv" style="border:1px solid #d2d6de;margin-top:5px ">     
                    <img id="imgShow">
                    <span style="width:500px">建议传165*100px的图片，必须是.gif,jpeg,jpg,png格式且不大于5MB。</span>
                  </div>
              </div>
              </div>
              <div class="form-group">
                <label  class="col-sm-2 control-label">品牌描述</label>
                <div class="col-sm-8">
                  <textarea class="form-control" maxlength="300" name="remark" rows="3"></textarea>
                </div>   
              </div>
              <div class="row">
                <div class="col-sm-6 text-right">
                  <button type="button" onclick="subAddBrand();" class="btn btn-primary">确定</button>
                </div>
                <div class="col-sm-6 text-left">
                  <button onclick="goBrandIndex();" type="button" class="btn btn-default">取消</button>
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
<script>
$(function(){
	  new uploadPreview({ UpBtn: "input2", DivShow: "imgdiv", ImgShow: "imgShow" });
});
 $(function(){
	$("#input2").fileupload({
	    url : "../brand/validateImg",
	    autoUpload : true,
	    singleFileUploads : true,
	    acceptFileTypes : /(\.|\/)(|gif|jpe?g|png|bmp)$/i,
	    maxFileSize : 5097152
	}).on(
	        'fileuploaddone',
	        function(e, data) {
	            if (data.result.code == '0'){
	            	ImgInfoMsg = true;
	              new uploadPreview({ UpBtn: "input2", DivShow: "imgdiv", ImgShow: "imgShow" });
	               $("#imgErrorDiv").removeClass("onerrInput");
	            	$("#imgError").text("");
	            	$("#imgNameInfo").val(data.result.imgName);
	            }else{
	            	new uploadPreview({ UpBtn: "input2", DivShow: "imgdiv", ImgShow: "imgShow" });
	            	$("#imgErrorDiv").addClass("onerrInput");
	            	$("#imgError").text("文件不大于5MB");
	            	ImgInfoMsg = false;
	            }
	});
}); 
</script>
</html>
</tiles:put>
</tiles:insert>