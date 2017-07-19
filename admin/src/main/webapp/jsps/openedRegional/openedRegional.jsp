<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
	<tiles:put name="title" value="已开通区域设置" />
	<tiles:put name="body" type="String">
		<c:set value="adminRolePer" var="modalName"></c:set>
		<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="${ctx}/css/zTreeStyle.css">
	<link href="${ctx}/css/toastr.css" rel="stylesheet" type="text/css" />

	<script type="text/javascript" src="${ctx}/js/hgl/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="${ctx}/js/hgl/jquery.ztree.excheck.js"></script>
	<script src="${ctx}/js/hgl/openedRegional.js"></script>
	<script src="${ctx}/js/hgl/toastr.js"></script>
</head>
<body>
	<div class="content-wrapper">
		<form id="key_${modalName}_qryFrm">

			<input id="adminRoleId" type="hidden" /> <input id="version"
				type="hidden" value="${version}" />
			<section class="content-header">
			<h1>
				<small>已开通区域设置</small>
			</h1>
			</section>
			<section class="content">
			<div class="row no-margin">
				<div class="box box-primary">
					<div class="box-body pad table-responsive" id="pox">
						<div class="fil details">
							<div style="position:relative;">
								<ul class="nav text-center one" id="shop">
									<div id="key_${modalName}_list">
										<%@include file="openedRegionalList.jsp"%>
									</div>
									</li>
								</ul>
								<ul id="treeDemo" class="ztree" style="margin-left:450px;"></ul>
								<table class="table-striped table-bordered table-hover" id="datatable" style="width:300px;position:absolute;right:0;top:0;line-height:30px;text-align;center;">
									<thead class="table-thead text-center">
										<tr>
											<th class="text-center" width="150">已开通市</th>
											<th class="text-center" width="150">最低价</th>
										</tr>
									</thead>
									<tbody id="cityLowestPriceList">
										<!-- <tr>
											<td class="text-center">深圳</td>
											<td class="text-center">
												<input class="form-control"/>	
											</td>		
										</tr>	 -->
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			</section>
	</div>
	</form>

	<!-- 添加角色模态框开始 --> 
	<!-- /.modal-dialog -->
	</div>
</body>
<script>
function aaaaa(cityLowestPriceList){
	alert();
}
</script>
		</html>
	</tiles:put>
</tiles:insert>