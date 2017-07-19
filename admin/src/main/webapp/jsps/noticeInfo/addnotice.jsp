<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
<tiles:put name="title" value="添加公告" />
<tiles:put name="body" type="String">
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>添加系统公告</title>
	<style type="text/css">
	.errorText{color:red;position:absolute;width:150px;line-height:34px;top:0px;right:-150px;}
	.border-red{border:1px solid red!important;}
	</style>
	<script src="${ctx}/js/hgl/noticeInfo.js" ></script>
</head>
<body class="skin-blue fixed">	
<div class="wrapper">
  <div class="content-wrapper">
  	<section class="content-header">
      <h1>
        我的主页
        <small>添加公告 </small>
      </h1>
    </section>
    <section class="content">
    	<div class="p8">
              <a href="queryNoticeInfoList" class="btn btn-primary">返回列表</a>
         </div>
    	<div class="row no-margin">
    		<div class="box box-primary">
    			 <div class="box-body">
             <form action="" id="noticeInfoForm" name="noticeInfoForm" class="form-horizontal" method="post">
             <input type="hidden" name="optStatus" value="1"/>	
             <div class="row">
                <div class="form-group">
                  <label class="col-sm-2 control-label">公告名称</label>
                  <div class="col-sm-6">
                    <input type="text" name="noticeName" id="noticeName" class="form-control inputNotNull" maxlength="30">
                    <span id="nameError" style="color:red;font-size:12px;"></span>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="form-group">
                  <label class="col-sm-2 control-label">公告类型</label>
                  <div class="col-sm-6">
                    <select  name="noticeType" id="noticeType" class="form-control">
                      <option value="1">升级</option> 
                      <option value="2">公告</option> 
                    </select>
                  </div>
                </div>
              </div>
               <div class="row">
                <div class="form-group">
                  <label class="col-sm-2 control-label">公告内容</label>
                  <div class="col-sm-6">
                  	<textarea rows="25" cols="113" name="noticeContent"  id="noticeContent" class="form-control inputNotNull"></textarea>
                  	<span id="contentError" style="color:red;font-size:12px;"></span>
                  </div>
                </div>
              </div>          
              <div class="row">
                <div class="form-group">
                  <label class="col-sm-2 control-label sr-only">按钮</label>
                  <div class="col-sm-6 ">
                  	<a class="btn btn-primary" id="addNoticeBtn">确认</a>
                  	<a class="btn btn-primary"  href="doInitNoticeInfo" id="goBackBtn">取消</a>
                  </div>
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