<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insert beanName="indexPage">
<tiles:put name="title" value="配置添加" />
<tiles:put name="body" type="String">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>配置添加</title>
	<script src="${ctx}/js/hgl/systemConfig.js"></script> 
	<c:set var="ctx" value="${ctx}"/>
</head>
<body class="skin-blue fixed">
<div class="wrapper">
  <div class="content-wrapper">
  	<section class="content-header">
      <h1>我的主页
        <small>配置添加</small>
      </h1>
    </section>
    <section class="content">
    	<div class="row no-margin">       
    		<div class="box box-primary">
    			<div class="box-body">
		             <form action="../../systemConfig/insertSystemConfig" id="saveSystemConfigPage" method="post" class="form-horizontal">
		              <div class="form-group">
		                <label  class="col-sm-2 control-label">配置名称</label>
		                <div class="col-sm-8">
		                  <input type="text" id="configKey" onchange="addValidateSystemConfigInfo();" 
		                  		 onblur="deleteSystemConfigClass('configKey');" class="form-control inputNotNull" maxlength="30" name="configKey" />
		                  		  <span id="configKeyReVaildate" style="color: red; position: absolute;width:200px ;right:-200px;top:10px" id="imgError" ></span> 
		                </div>   
		              </div>
		              <div class="form-group">
		                <label  class="col-sm-2 control-label">配置值</label>
		                <div class="col-sm-8">
		                  <input type="text" id="configValue" onblur="deleteSystemConfigClass('configValue');" class="form-control inputNotNull" maxlength="30" name="configValue" />
		                </div>
		              </div>  
		              <div class="form-group">
		                <label  class="col-sm-2 control-label">配置描述</label>
		                <div class="col-sm-8">
		                  <textarea class="form-control" maxlength="300" name="remark" rows="3"></textarea>
		                </div>   
		              </div>
		              <div class="row">
		                <div class="col-sm-6 text-right">
		                  <button type="button" onclick="subAddSystemConfig();" class="btn btn-primary">确定</button>
		                </div>
		                <div class="col-sm-6 text-left">
		                  <button onclick="goSystemConfigIndex();" type="button" class="btn btn-default">取消</button>
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