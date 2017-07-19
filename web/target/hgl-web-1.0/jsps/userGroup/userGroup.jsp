<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
	<tiles:put name="title" value="客户分组" />
	<tiles:put name="body" type="String">
	<c:set value="userGroup" var="modalName"></c:set>	
	<html>
		<link rel="stylesheet" href="${ctx}/css/me.css">
		<link rel="stylesheet" href="${ctx}/css/jquery-ui.min.css">
		<link rel="stylesheet" href="${ctx}/css/jq-ui-rest.css">
		<script src="${ctx}/js/hgl/datepicker-zh.js"></script>
		<%--<script src="${ctx}/js/hgl/group.js"></script> --%>
		<style>
		label{
			width:100px;
			display: inline-block;
			text-align:
		}
		</style>
	<body>
<!-- 内容板块开始 -->
		
		<div class="area me"> 
			
			<div class="main-right pull-right">
				<!-- /hgl-web/group/index -->             
					
				 	<form id="key_${modalName}_qryFrm" style="margin-bottom:10px;" >
				 		                        
						分组                       
						<input type="text" name="groupName" placeholder="请输入组名" style="border: 1px solid #eee; height: 14px; line-height: 14px; padding: 8px;margin-right:20px;"/>
						类型 
						<select name="typeId" id="typeId" style="border: 1px solid #eee; height: 32px; line-height: 32px;width:170px;margin-right:20px;">
							<option value="" selected="selected">请选择</option>
							<option value="104">经销商</option>
							<option value="106">终端客户</option>    
							<option value="114">师傅</option>
						</select>
						用户名
						<input  type="text" name="name" placeholder="请输入用户名"  style="border: 1px solid #eee; height: 14px; line-height: 14px; padding: 8px;margin-right:20px;"/>
						<button type="button" class="btn-bg" onclick="submitform();">
							<span>查询</span>
						</button>
						<button class="btn-bg" type="button" onclick="goGroupIndex();" style="margin-right:20px;">          
								<span>分组管理</span>
						</button>  	
						<!-- <span class="select-time-big pull-right" style="margin-bottom:10px;">
							<input type="text" name="name" placeholder="请输入用户名"/>
							<button type="button" onclick="submitform();">搜索用户</button>         
						</span>  -->      
					</form>
					 <div id="key_userGroup_list">
			 			<%@include file="userGroupList.jsp" %>
			 		 </div>
		 	</div
		</div>	
	</html>
	<script type="text/javascript">
		EcWeb.currentModalName = '${modalName}';
		  $(document).delegate(".orderGoupList" ,'change',function(){
		    	$(this).removeClass('onerrInput');
		    	var chk_value = [];
				var indexs = $(this).attr("name");
				var userId = $(this).attr("id");
				var names = "checkbox_" + indexs;
				$('input[name=' + names + ']:checked').each(function() {
					chk_value.push($(this).val());
				});
				var str = "";
				for ( var i = 0; i < chk_value.length; i++) {
					if (i == (chk_value.length - 1)) {
						str += chk_value[i];
					} else {
						str += chk_value[i] + ",";
					}
				}  
				$.ajax({
					type : "POST",
					url : ctx + '/userGroup/updateUserGroup',
					data : {
						userId : userId,
						userGroup : str,
					},
					success : function(response) {
						 $(document).delegate(".pg","click", function(evt) {
					        linkon.web.toPage(evt);
					    });
					}
				});
		    });
		 /* 表单提交 */
		  function submitform(){
			  	var frmId = "key_" + EcWeb.currentModalName + "_qryFrm";
			    var  dataDomId = "key_" + EcWeb.currentModalName + "_list";
			    var frm = $("#" + frmId);
			    var params=frm.serialize(); 
				$.ajax({
			        type : "POST",
			        url : ctx+'/userGroup/serachUserGroup',
			        data : params,
			        success : function(response) {
			            $("#" + dataDomId).html(response);
			        }
			    }); 
		  }
		 
		  function goGroupIndex(){
				window.location.href = ctx+'/group/index';
			}
	</script>
	</tiles:put>
</tiles:insert>