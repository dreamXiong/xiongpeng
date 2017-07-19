<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
<tiles:put name="title" value="品牌列表" />
<tiles:put name="body" type="String">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改系统公告</title>
<script src="${ctx}/js/hgl/noticeInfo.js"></script>
<style type="text/css">
	.errorText{color:red;position:absolute;width:150px;line-height:34px;top:0px;right:-150px;}
	.border-red{border:1px solid red!important;}
</style>
	
</head>
<body class="skin-blue fixed">
	<div class="wrapper">
		<header class="main-header">
			<a href="javascript:;" class="logo"> 惠给利后台管理系统 </a>
			<nav class="navbar navbar-static-top" role="navigation">
				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">
						<li><a href="javascript:;">当前用户 : admin</a></li>
						<li><a href="javascript:;">修改密码</a></li>
						<li><a href="javascript:;">安全退出</a></li>
					</ul>
				</div>
			</nav>
		</header>
		
		<div class="content-wrapper">
			<section class="content-header">
				<h1>
					我的主页 <small>修改公告</small>
				</h1>
			</section>
			<section class="content">
				<div class="p8">
              		<a href="queryNoticeInfoList" class="btn btn-primary">返回列表</a>
         		</div>
				<div class="row no-margin">
					<div class="box box-primary">
						<div class="box-body">
							<form action="" name="noticeForm" class="form-horizontal"
								method="post">
								<input type="hidden" name="id" value="${noticeInfo.id}" /> <input
									type="hidden" name="optStatus" value="1" />
								<div class="row">
									<div class="form-group">
										<label class="col-sm-2 control-label">公告名称</label>
										<div class="col-sm-6">
											<input type="text" name="noticeName" id="noticeName" class="form-control"
												value="${noticeInfo.noticeName}"> <span id="nameError" style="color:red;font-size:12px;"></span>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group">
										<label class="col-sm-2 control-label">类型</label>
										<div class="col-sm-6">
											<select name="noticeType" id="noticeType"
												class="form-control">
												<option value="1"
													<c:if test="${noticeInfo.noticeType==1}">selected</c:if>>升级</option>
												<option value="2"
													<c:if test="${noticeInfo.noticeType==2}">selected</c:if>>公告</option>
											</select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group">
										<label class="col-sm-2 control-label">公告内容</label>
										 <div class="col-sm-6">
                  							<textarea rows="25" cols="113" name="noticeContent"  id="noticeContent" class="form-control inputNotNull">${noticeInfo.noticeContent}</textarea>
                  							<span id="contentError" style="color:red;font-size:12px;"></span>
                  						</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group">
										<label class="col-sm-2 control-label sr-only">按钮</label>
										<div class="col-sm-6 ">
											<a class="btn btn-primary" id="updNoticeBtn">确认</a> <a
												class="btn btn-primary" href="doInitNoticeInfo">取消</a>
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