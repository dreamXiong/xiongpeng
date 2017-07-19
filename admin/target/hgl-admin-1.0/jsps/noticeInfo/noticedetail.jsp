<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
<tiles:put name="title" value="品牌列表" />
<tiles:put name="body" type="String">
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>公告详细信息</title>	
  	<style type="text/css">
  		.errorText{display:block;width:150px;height:34px;position:absolute;
  				 right:-150px;top:0;line-height:34px;color:red;font-size:12px;}
  		.border-red{border-color:red!important;}
  		.form-group{margin-bottom:8px}
  	</style>
</head>
<body class="skin-blue fixed">
<div class="wrapper">
	<header class="main-header">
  <a href="javascript:;" class="logo">
   惠给利后台管理系统
  </a>
  <nav class="navbar navbar-static-top" role="navigation">
    <div class="navbar-custom-menu">
      <ul class="nav navbar-nav">
        <li>
        	<a href="javascript:;">当前用户 : admin</a> 
        </li>
        <li>
        	<a href="javascript:;">修改密码</a>
        </li>
         <li>
        	<a href="javascript:;">安全退出</a>
        </li>
      </ul>
    </div>
  </nav>
</header>
  <div class="content-wrapper">
  	<section class="content-header">
      <h1>
        我的主页
        <small>公告详细信息</small>
      </h1>
    </section>
    <section class="content">
    	<div class="p8">
        	<a href="queryNoticeInfoList" class="btn btn-primary">返回列表</a>
        </div>
    	<div class="row no-margin">
    		<div class="box box-primary">
    			 <div class="box-body">
             <form action="" name="noticeInfoForm" class="form-horizontal" method="post">
             <input type="hidden" name="optStatus" value="1"/>	
             <div class="row">
                <div class="form-group">
                  <label class="col-sm-2 control-label">公告名称</label>
                  <div class="col-sm-6" style="padding-top:7px">${noticeInfo.noticeName}</div>
                </div>
              </div>
              <div class="row">
                <div class="form-group">
                  <label class="col-sm-2 control-label">类型</label>
                  <div class="col-sm-6" style="padding-top:7px">
                  	<c:choose>
                  		<c:when test="${noticeInfo.noticeType==1}">升级</c:when>
                  		<c:when test="${noticeInfo.noticeType==2}">公告</c:when>
                  	</c:choose>
 				  </div>
                </div>
              </div>
               <div class="row">
                <div class="form-group">
                  <label class="col-sm-2 control-label">公告内容</label>
                  <div class="col-sm-8" >
                  	${noticeInfo.noticeContent}		
 				  </div>
                </div>
              </div>
              <div class="row">
                <div class="form-group">
                  <label class="col-sm-2 control-label">发布时间</label>
                  <div class="col-sm-6" style="padding-top:7px">
                  	<jsp:useBean id="createDt" class="java.util.Date"/>
                  	<jsp:setProperty  name="createDt" property="time" value="${noticeInfo.createDt}"/>
                  	<fmt:formatDate value="${createDt}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>                  	
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="form-group">
                  <label class="col-sm-2 control-label">发布人</label>
                  <div class="col-sm-6" style="padding-top:7px">
                    ${noticeInfo.createBy}
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="form-group">
                  <label class="col-sm-2 control-label sr-only">按钮</label>
                  <div class="col-sm-6 ">
                  	<a class="btn btn-primary"  href="doInitNoticeInfo" id="goBackBtn">返回</a>
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