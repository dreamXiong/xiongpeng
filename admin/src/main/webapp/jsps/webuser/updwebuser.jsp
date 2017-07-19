<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<tiles:insert beanName="indexPage">
<tiles:put name="title" value="前台用户修改"/>
<tiles:put name="body" type="String">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>前台用户修改</title>
<script type="text/javascript">
	$("document").ready(function(){
			
		$("#updWebUser #updWebUserBtn").click(function(){
			var isError = validateForm("updWebUser");
			
			var roleId = $("#roleId").val();
			if(roleId==0){
				$("#roleId").css("border","1px solid red");
				isError = false;
			}
			
			var state = $("#typeId").val();
			if(state == 0){
				$("#typeId").css("border","1px solid red");
				isError = false;
			}
			
			if(isError==false){
				return;
			}			
			updWebUser.action="doUpdateWebUser";
			updWebUser.submit();
		});
		
		$("#roleId").click(function(){
			$("#roleId").removeAttr("style");
		});
		
		$("#typeId").click(function(){
			$("#typeId").removeAttr("style");
		});
	});
</script>
</head>
<body class="skin-blue fixed">
<div class="wrapper">
  <div class="content-wrapper">
  	<section class="content-header">
      <h1>
        	我的主页
        <small>修改前台用户信息</small>
      </h1>
    </section>
    <section class="content">     
      <div class="row no-margin">
		<div class="box box-primary">
    	  <div class="p8">
      		<a href="init" class="btn btn-primary key">返回列表</a>
      	  </div>
    	  <div class="box-body">
            <form  action="" id="updWebUser" name="updWebUser" method="post" class="form-horizontal">
                <table class="table">
                  <tr>
                  	<input type="hidden" name="id"  id="id" value="${webUser.id}"/>
                  	<input type="hidden" name="version" value="${webUser.version}"/>
                  </tr>
                  <tr>
				  	<td class="th">姓名</td>
				  	<td class="td" style="text-align:left;">${webUser.name}</td>
				  </tr>  
				    <tr>
				  	<td class="th">账号</td>
				  	<td class="td" style="text-align:left;">${webUser.userName}</td>
				  </tr>  
				    <tr>
				  	<td class="th">手机号码</td>
				  	<td class="td" style="text-align:left;">${webUser.mobile}</td>
				  </tr>  
				    <tr>
				  	<td class="th">邮箱</td>
				  	<td class="td" style="text-align:left;">${webUser.email}</td>
				  </tr>  
                  <tr>
                  	<td class="th" width="20%">角色</td>
                  	<td  width="15%" style="text-align:left">
                  		<select id="roleId" name="roleId">
                  			<option value="0">===请选择===</option>
                  			<c:forEach items="${webRoles}" var="webRole">
                  				<option value="${webRole.id}" <c:if test="${webRole.id==webUser.roleId}">selected</c:if> >${webRole.name}</option>
                  			</c:forEach>
                  		</select>
                  	</td>
                  	<td class="td" width="65%"></td>
                  </tr> 
                  <tr>
                  	<td class="th">类型 </td>
                  	<td style="text-align:left">
                  		<select id="typeId" name="typeId">
                  			<option value="0">===请选择 ===</option>
                  			<option value="102" <c:if test="${webUser.typeId==102}">selected</c:if> >厂家</option>
                  			<option value="104" <c:if test="${webUser.typeId==104}">selected</c:if> >经销商</option>
                  			<option value="106" <c:if test="${webUser.typeId==106}">selected</c:if> >终端客户</option>
                  		</select>
                  	</td>
                  </tr> 
                  <tr>
                  	<td>意见反馈</td>
                  	<td>${userInfo.feedBack}</td>
                  </tr>
                  <tr>
				  	<td class="th">状态</td>
				  	<td style="text-align:left">
					  	<input type="radio" name="state" value="1" id="selectValid" <c:if test="${webUser.state==1}">checked</c:if>> <label for="selectValid" style="font-weight:normal">启用</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  	<input type="radio" name="state" value="3" id="selectInValid" <c:if test="${webUser.state==3}">checked</c:if>><label for="selectInValid" style="font-weight:normal">禁用</label>
					</td>
				  </tr>				  
				  <tr>
				  	<td class="th">备注</td>
				  	<td class="td"><textarea cols="48" rows="5" name="remark" maxlength="500">${webUser.remark}</textarea></td>
				  </tr>                      
                  <tr>
                    <td class="th"></td>
                    <td class="td">
                    	<a href="javascript:void(0)" class="btn btn-primary btn-sm" id="updWebUserBtn">确定</a>
                    	<a href="init" class="btn btn-primary btn-sm">取消</a>
                    </td>
                  </tr>
                </table>
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