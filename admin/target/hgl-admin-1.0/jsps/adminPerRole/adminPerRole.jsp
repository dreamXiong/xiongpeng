<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
	<tiles:put name="title" value="角色权限管理" />
	<tiles:put name="body" type="String">
		<c:set value="adminRolePer" var="modalName"></c:set>
		<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%-- <link rel="stylesheet" href="${ctx}/css/demo.css"> --%>
<link rel="stylesheet" href="${ctx}/css/zTreeStyle.css">
<link href="${ctx}/css/toastr.css" rel="stylesheet" type="text/css" /> 

	<script type="text/javascript" src="${ctx}/js/hgl/jquery.ztree.core.js"></script>
	<script type="text/javascript"
		src="${ctx}/js/hgl/jquery.ztree.excheck.js"></script>
	<script src="${ctx}/js/hgl/adminPerRole.js"></script>
	<script src="${ctx}/js/hgl/toastr.js"></script>
</head>
<body>
	<div class="content-wrapper">
		<form id="key_${modalName}_qryFrm">

			<input id="adminRoleId" type="hidden" /> <input id="version"
				type="hidden" value="${version}" />
			<section class="content-header">
			<h1>
				我的主页 <small>角色权限管理</small>
			</h1>
			</section>
			<section class="content">
			<div class="row no-margin">
				<div class="box box-primary">
					<div class="box-body pad table-responsive" id="pox">
						<div class="fil details">
							<div>
								<ul class="nav text-center one" id="shop">
									<li class="nav-title"><a href="javaScript:void(0);"
										id="addRole">添加角色</a></li>
									<div id="key_${modalName}_list">
										<%@include file="adminPerRoleList.jsp"%>
									</div>
									</li>
								</ul>
								<ul id="treeDemo" class="ztree"></ul>
							</div>
							<!-- 										<ul id="treeDemo" class="ztree"> -->
						</div>
					</div>
				</div>
			</div>
			</section>
		</form>
	</div>

	<!-- 添加角色模态框开始 -->
	<%@include file="addRole.jsp"%>
	<!-- 添加角色模态框结束 -->
</body>
<script type="text/javascript">
	$(document).delegate('#save', 'click', function(event) {
		//validateForm
		var addType = validateForm("formHorizontal");
		if (!addType) {
			return;
		}
		var txt = $('.modal-body').find('textarea');
		var remark = txt.val();
		var inp = $('.modal-body').find('input');
		var name = inp.val();
		checkRoleName(name,remark);
	});
	
	function checkRoleName(name,remark){
		$.ajax({
			type : 'GET',
			url : 'checkRoleName_ajax?name=' + name ,
			success : function(response) {
				if("error"==response){
					toastr.error("不能重复添加角色名称！", "操作提示:");
					return false;
				}
				addRole(name,remark);
			}
		});
	}
	
	function addRole(name,remark){
		$('#modal').modal('hide');
		EcWeb.currentModalName = '${modalName}';
		var dataDomId = "key_" + EcWeb.currentModalName + "_list";
		$.ajax({
			type : 'GET',
			url : 'addRole_ajax?name=' + name + '&remark=' + remark,
			success : function(response) {
				$("#" + dataDomId).html(response);
				init();
				toastr.success("添加成功！", "操作提示:");
			}
		});
	}
</script>
		</html>
	</tiles:put>
</tiles:insert>