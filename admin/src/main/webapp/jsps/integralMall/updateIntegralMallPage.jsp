<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insert beanName="indexPage">
<tiles:put name="title" value="积分商城修改" />
<tiles:put name="body" type="String">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>积分商城修改</title>
	<!--文件上传 -->
	<script src="${ctx}/js/jquery/uploadPreview.min.js"></script>
	<script src="${ctx}/js/hgl/brand.js"></script> 
	<script src="${ctx}/js/jquery/jquery.ui.widget.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload.js"></script>
	<script src="${ctx}/js/jquery/jquery.iframe-transport.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload-process.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload-validate.js"></script>
	<script src="${ctx}/js/hgl/integralMall.js"></script> 
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
        <small>积分商城修改</small>
      </h1>
    </section>
    <section class="content">
    	<div class="row no-margin">       
    		<div class="box box-primary">
    			<div class="box-body">
		             <form action="${ctx }/integralMall/updateIntegralMall" id="updateIntegralMall" method="post" class="form-horizontal">
		              <input name="id" type="hidden" id="id" value="${integralMall.id }">       
		              <input name="goodsImage" type="hidden" id="goodsImage" value="${integralMall.goodsImage }">
		              <div class="form-group">
		                <label  class="col-sm-2 control-label">商品类型名称</label>
		                <div class="col-sm-8">
		                   <select class="form-control inputNotNull" name="integralMallTypeId" id="integralMallTypeId"> 
				                <c:forEach var="item" items="${integralMallTypeList}" varStatus="s">
				                	<c:if test="${integralMall.integralMallTypeId == item.id}">
				                		<option value="${item.id }" selected="selected"> ${item.goodsTypeName}</option>
				                	</c:if>
				                    <c:if test="${integralMall.integralMallTypeId != item.id}">
				                		<option value="${item.id }"> ${item.goodsTypeName}</option>
				                	</c:if>
				                </c:forEach> 
			               </select>
		                </div>
		              </div> 
		              <div class="form-group">
		                <label  class="col-sm-2 control-label">商品名称</label>
		                <div class="col-sm-8">
		                  <input type="text" id="goodsName" class="form-control inputNotNull" maxlength="50" name="goodsName" value="${integralMall.goodsName }"/>
		                </div>
		              </div> 
		              <div class="form-group">
		                <label  class="col-sm-2 control-label">积分</label>
		                <div class="col-sm-8">
		                  <input type="text" id="integral" class="form-control inputNotNull" maxlength="10" name="integral" value="${integralMall.integral }"/><span style="color: red;" id="integralError"></span>
		                </div>
		              </div> 
		              <div class="form-group">
		                <label  class="col-sm-2 control-label">需支付金额</label>
		                <div class="col-sm-8">
		                  <input type="text" id="payAmount" class="form-control inputNotNull" maxlength="10" name="payAmount" value="${integralMall.payAmount }"/><span style="color: red;" id="payAmountError"></span>
		                </div>
		              </div> 
		              <div class="form-group">
		                <label  class="col-sm-2 control-label">商品市场价</label>
		                <div class="col-sm-8">
		                  <input type="text" id="marketAmount" class="form-control inputNotNull" maxlength="10" name="marketAmount" value="${integralMall.marketAmount }"/><span style="color: red;" id="marketAmountError"></span>
		                </div>
		              </div> 
		             <div class="form-group"> 
		                <label  class="col-sm-2 control-label">商品图片</label>
		                <div class="col-sm-8" style="display:inline">
		                 <div class="input-group" id="imgErrorDiv">   
		                    <input type="text"  class="form-control" id="input1" value="${integralMall.goodsImage }">
		                    <input type="file" name="imgFile" class="form-control inputNotNull" id="input2" style="opacity:0;position:absolute;top:0;">
		                    <div class="input-group-addon">浏览...</div>
		                 </div>    
		                   <span style="color: red; position: absolute;width:200px ;right:-200px;top:10px" id="imgError" ></span>     
		                  <div id="goodsImageDiv" style="border:1px solid #d2d6de;margin-top:5px ">     
		                    <img id="goodsImageShow" src="${ctx}/integralMall/generateImage?imgName=${integralMall.goodsImage }">
		                    <span style="width:500px">建议传165*100px的图片，必须是.gif,jpeg,jpg,png格式且不大于5MB。</span>
		                  </div>
		              	</div>
		              </div>
		              <div class="form-group">
		                <label  class="col-sm-2 control-label">商品描述</label>
		                <div class="col-sm-8">
		                  <textarea type="text" id="goodsDescribe" class="form-control inputNotNull" maxlength="500" name="goodsDescribe" rows="2" >${integralMall.goodsDescribe }</textarea>
		                </div>
		              </div>  
		              <div class="form-group">
		                <label  class="col-sm-2 control-label">注意事项</label>
		                <div class="col-sm-8">
		                  <textarea type="text" id="attentionMatters" class="form-control" maxlength="250" name="attentionMatters" rows="2" >${integralMall.attentionMatters }</textarea>    
		                </div> 
		              </div> 
		              <div class="form-group">
		                <label  class="col-sm-2 control-label">兑换流程</label>
		                <div class="col-sm-8">
		                  <textarea type="text" id="exchangeProcess" class="form-control" maxlength="250" name="exchangeProcess" rows="2" >${integralMall.exchangeProcess }</textarea>
		                </div>
		              </div> 
		              <div class="row">
		                <div class="col-sm-6 text-right">
		                  <button type="button" onclick="updateIntegralMall();" class="btn btn-primary">确定</button>
		                </div>
		                <div class="col-sm-6 text-left">
		                  <button onclick="window.location.href='${ctx}/integralMall/index'" type="button" class="btn btn-default">取消</button>
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
	    url : "../integralMall/validateImg",
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
            	$("#goodsImage").val(data.result.imgName);
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