<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insert beanName="indexPage">
<tiles:put name="title" value="配置添加" />
<tiles:put name="body" type="String">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>经销商微信支付配置添加</title>
	<script src="${ctx}/js/hgl/dealersWeixinConfig.js"></script> 
	<c:set var="ctx" value="${ctx}"/>
</head>
<body class="skin-blue fixed">
<div class="wrapper">
  <div class="content-wrapper">
  	<section class="content-header">
      <h1>我的主页
        <small>经销商微信支付配置添加</small>
      </h1>
    </section>
    <section class="content">
    	<div class="row no-margin">       
    		<div class="box box-primary">
    			<div class="box-body">
		             <form action="../dealersWeixinConfig/insertDealersWeixinConfig" id="saveDealersWeixinConfigPage" method="post" class="form-horizontal">
		              <div class="form-group">
		                <label  class="col-sm-2 control-label">店铺名称</label>
		                <div class="col-sm-8">
			                 <select class="form-control inputNotNull" name="shopId" id="shopId"> 
			                    <option value="">请选择</option>
				                <c:forEach var="item" items="${shopInfoList}" varStatus="s">
				                    <option value="${item.id }">${item.shopName}</option>
				                </c:forEach> 
			                 </select>
		                 </div>
		              </div>  
		              <div class="form-group">
		                <label  class="col-sm-2 control-label">公众号ID</label>
		                <div class="col-sm-8">
		                  <input type="text" id="appId" class="form-control inputNotNull" maxlength="100" name="appId" />
		                </div>
		              </div> 
		              <div class="form-group">
		                <label  class="col-sm-2 control-label">商户号ID</label>
		                <div class="col-sm-8">
		                  <input type="text" id="mchId" class="form-control inputNotNull" maxlength="100" name="mchId" />
		                </div>
		              </div> 
		              <div class="form-group">
		                <label  class="col-sm-2 control-label">商户密钥</label>
		                <div class="col-sm-8">
		                  <input type="text" id="appKey" class="form-control inputNotNull" maxlength="100" name="appKey" />
		                </div>
		              </div> 
		              <div class="form-group">
		                <label  class="col-sm-2 control-label">微信回调URL</label>
		                <div class="col-sm-8">
		                  <input type="text" id="notifyUrl" class="form-control inputNotNull" maxlength="100" name="notifyUrl" />
		                </div>
		              </div> 
		              <div class="row">
		                <div class="col-sm-6 text-right">
		                  <button type="button" onclick="subAddDealersWeixinConfig();" class="btn btn-primary">确定</button>
		                </div>
		                <div class="col-sm-6 text-left">
		                  <button onclick="goDealersWeixinConfigIndex();" type="button" class="btn btn-default">取消</button>
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