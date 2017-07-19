<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
	<tiles:put name="title" value="品牌列表" />
	<tiles:put name="body" type="String">
	<html>

	<meta charset="UTF-8">
	<title>品牌列表</title>
	<link rel="stylesheet" href="${ctx}/css/me.css">
	<script src="${ctx}/js/jquery/jquery1.9.2/ui/minified/jquery-ui.min.js"></script>
	<script src="${ctx}/js/hgl/datepicker-zh.js"></script>
	
	<script src="${ctx}/js/jquery/jquery1.9.2/ui/jquery.ui.widget.js"></script>
	
	<c:set value="brand" var="modalName"></c:set>
<body>
<!-- 内容板块开始 -->
	<div>
		<div class="area me">
			
				<div class="main-right pull-right">
				<div class="details-list clear" >
					<!-- <h3 class="bg-gray">品牌列表</h3> -->
					
					<div style="margin-bottom: 10px;" >
						<form id="key_${modalName}_qryFrm" >
							名称
							<input type="text" name="name" id="name" placeholder="请输入查询名称" 
									style="line-height:30px;border:1px solid #eee;padding-left:5px;margin-right:36px;border-color:#eee;"/>
							大类 
							<select class="form-control" name="producttypeId" id="producttypeId" style="margin-right:36px;border-color:#eee;">
								<option value="">---请选择---</option>
								<c:forEach items="${mLists}" var="item" varStatus="s">
									<option value="${item.id}">${item.name}</option>
								</c:forEach>
							</select>
							类型 <select	class="form-control" name="type" id="type" style="border-color:#eee;margin-right:5px;">
								<option value="">---请选择---</option>
								<option value="0">系统</option>
								<option value="1">非系统</option>	
							</select>
							<button type="button" onclick="submitform();" class="btn-bg" >
								<span style="margin-right:0;">搜索</span>
							</button>           
							<a class="btn-bg" href="${ctx}/brand/addBrand">
								<span style="margin-right:0;">添加品牌</span>
							</a>
						</form>
						
					</div>
				</div>
				  <div id="key_${modalName}_list">
					<%@include file="brandList.jsp" %>
				</div>	
				</div>
				
				
			</div>
		</div>
	</div>
<div id="datepicker"></div>
</body>
	<script type="text/javascript">
	$('tbody tr:odd').addClass('odd');
	EcWeb.currentModalName = '${modalName}';
	 /* 表单提交 */
	 function submitform(){
		 var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
		    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
		    var frm = $("#" + frmId);
		    var params=frm.serialize();
			$.ajax({
		        type : "POST",
		        url : 'serachBrand',
		        data : params,
		        success : function(response) {
		        	$('tbody tr:odd').addClass('odd');
		            $("#" + dataDomId).html(response);
		        }
		    }); 
	 }
	</script>
</html>
	</tiles:put>
</tiles:insert>