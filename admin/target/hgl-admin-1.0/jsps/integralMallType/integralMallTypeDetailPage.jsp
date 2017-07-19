<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insert beanName="indexPage">
<tiles:put name="title" value="积分商城类型详情" />
<tiles:put name="body" type="String">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>积分商城类型详情</title>
	<!--文件上传 -->
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
        <small>积分商城类型详情</small>
      </h1>
    </section>
    <section class="content">
    	<div class="row no-margin">       
    		<div class="box box-primary">
    			<div class="box-body">
		             <form action="${ctx }/integralMallType/updateIntegralMallType" id="updateIntegralMallType" method="post" class="form-horizontal">
		              <div class="form-group">
		                <label  class="col-sm-2 control-label">商品类型名称</label>
		                <div class="col-sm-8">
		                  <input type="text" id="goodsTypeName" class="form-control inputNotNull" maxlength="50" name="goodsTypeName" value="${integralMallType.goodsTypeName }" readonly="readonly" />
		                </div>
		              </div> 
		             <div class="form-group"> 
		                <label  class="col-sm-2 control-label">商品类型图片</label>
		                <div class="col-sm-8" style="display:inline">
		                  <div id="goodsImageDiv" style="border:1px solid #d2d6de;margin-top:5px ">     
		                    <img id="goodsImageShow" src="${ctx}/integralMall/generateImage?imgName=${integralMallType.goodsTypeImage }">
		                  </div>
		              	</div>
		              </div>
		              <div class="form-group">
		                <label  class="col-sm-2 control-label">商品类型描述</label>                             
		                <div class="col-sm-8">
		                  <textarea type="text" id="goodsTypeDescribe" class="form-control inputNotNull" maxlength="500" name="goodsTypeDescribe" rows="2" readonly="readonly">${integralMallType.goodsTypeDescribe }</textarea>
		                </div>
		              </div>  
		              <div class="row">
		                <div class="col-sm-6 text-right">
		                  <button onclick="window.location.href='${ctx}/integralMallType/index'" type="button" class="btn btn-primary">确定</button>
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
</tiles:put>
</tiles:insert>