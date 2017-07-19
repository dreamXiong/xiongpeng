<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insert beanName="indexPage">
	<tiles:put name="title" value="配置修改" />
	<tiles:put name="body" type="String">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>配置修改</title>
	<script src="${ctx}/js/hgl/systemConfig.js"></script> 
	<c:set var="ctx" value="${ctx}"/>
</head>
<body class="skin-blue fixed">
<div class="wrapper">
  <div class="content-wrapper">
  	<section class="content-header">
      <h1>我的主页
        <small>配置修改</small>
      </h1>
    </section>
    <section class="contet">
    	<div class="row no-margin">       
    		<div class="box box-primary">
    			 <div class="box-body">
             <form action="updateSystemConfig" id="saveUpdateSystemConfig" method="post" class="form-horizontal">
             <input name="id" type="hidden" id="id" value="${systemConfig.id }">
              <div class="form-group">
                <label  class="col-sm-2 control-label">配置名称</label>
                <div class="col-sm-8">
                  <input type="text" id="configKey" class="form-control" value="${systemConfig.configKey }" name="configKey" readonly="readonly" />
                </div>
              </div>
              <div class="form-group">
                <label  class="col-sm-2 control-label">配置值</label>
                <div class="col-sm-8">
                  <input type="text" id="configValue" onblur="deleteSystemConfigClass('configValue');" 
                  		  class="form-control inputNotNull"  maxlength="30" value="${systemConfig.configValue}" name="configValue" />
                </div>
              </div>  
              <div class="form-group">
                <label  class="col-sm-2 control-label">配置描述</label>
                <div class="col-sm-8">
                  <textarea class="form-control" rows="3" name="remark" maxlength="300">${systemConfig.remark}</textarea>
                </div>   
              </div>
              <div class="row">
                <div class="col-sm-6 text-right">
                  <button type="button" onclick="saveUpdateSystemConfig();" class="btn btn-primary">确定</button>
                </div>
                <div class="col-sm-6 text-left">
                  <button onclick="goUpdateSystemConfigIndex();" type="button" class="btn btn-default">取消</button>
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