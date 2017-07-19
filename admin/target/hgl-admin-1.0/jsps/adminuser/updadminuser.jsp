<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
<tiles:put name="title" value="修改用户信息" />
<tiles:put name="body" type="String">
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>用户详情</title>
	<link rel="stylesheet" href="${ctx}/css/move-border.css">
	<script type="text/javascript" src="${ctx}/js/hgl/adminuser.js"></script>
	
</head>
<body class="skin-blue fixed">
<div class="wrapper">
  <div class="content-wrapper">
  	<section class="content-header">
      <h1>
        我的主页
        <small>修改用户信息</small>
      </h1>
    </section>
    <section class="content">     
      <div class="row no-margin">
    		<div class="box box-primary">
    			<div class="box-body">
            <form  action="" id="updAdminUser" name="updAdminUser" method="post" class="form-horizontal">
                <table class="table">
                  <tr>
                  	<input type="hidden" name="recommendCode"  id="recommendCode" value="${adminUser.recommendCode}"/>
                  	<input type="hidden" name="id"  id="id" value="${adminUser.id}"/>
                  	<input type="hidden" name="version" value="${adminUser.version}"/>
                    <td class="th" width="20%">用户名</td>
                    <td class="td" width="15%"><input type="text" id="userName" name="userName" class="form-control inputNotNull" value="${adminUser.userName}" maxlength="30"></td>
					<td class="td" width="65%" id="userNameError"></td>
                  </tr>
                  <tr>
                    <td class="th" width="20%">姓名</td>
                    <td class="td" width="15%"><input type="text" id="name" name="name" class="form-control inputNotNull" value="${adminUser.name}" maxlength="30"></td>
                    <td class="td" width="65%" id="nameError"></td>
                  </tr>
                  <tr>
                    <td class="th" width="20%">常用地址</td>
                    <td class="td" width="15%"><input type="text" id="address" name="address" class="form-control inputNotNull" value="${adminUser.address}" maxlength="250"></td>
                    <td class="td" width="65%" id="addressError"></td>
                  </tr>
                  <tr>
                    <td class="th">电话号码</td>
                    <td class="td"><input type="text" id="mobile" name="mobile" class="form-control inputNotNull phone" value="${adminUser.mobile}" maxlength="11"></td>
                    <td class="td" id="mobileError"></td>
                  </tr>
                  <tr>
                    <td class="th">邮箱地址</td>
                    <td class="td"><input type="text" id="email" name="email" class="form-control inputNotNull email" value="${adminUser.email}" maxlength="100"></td>
                    <td class="td" id="emailError"></td>
                    <input type="hidden" value="${adminUser.version}" name="version"/>
                  </tr>  
                  <tr>
                  	<td class="th" width="20%">角色</td>
                  	<td  width="15%" style="text-align:left">
                  		<select id="roleId" name="roleId" class="form-control">
                  			<option value="0">===请选择===</option>
                  			<c:forEach items="${adminRoles}" var="adminRole">
                  				<option value="${adminRole.id}" <c:if test="${adminRole.id==adminUser.roleId}">selected</c:if> >${adminRole.name}</option>
                  			</c:forEach>
                  		</select>
                  	</td>
                  	<td class="td" width="65%"></td>
                  </tr> 
                  <tr>
                  	<td class="th">类型 </td>
                  	<td style="text-align:left">
                  		<select id="type" name="type" class="form-control">
                  			<option value="0">===请选择 ===</option>
                  			<option value="102" <c:if test="${adminUser.type==102}">selected</c:if> >厂家</option>
                  			<option value="104" <c:if test="${adminUser.type==104}">selected</c:if> >经销商</option>
                  			<option value="106" <c:if test="${adminUser.type==106}">selected</c:if> >终端客户</option>
                  			<option value="108" <c:if test="${adminUser.type==108}">selected</c:if>>地推人员</option>
                  			<option value="110" <c:if test="${adminUser.type==110}">selected</c:if>>仓库管理员</option>
                  			<option value="112" <c:if test="${adminUser.type==112}">selected</c:if>>店铺管理员</option>
                  		</select>
                  	</td>
                  </tr> 
                  <tr>
				  	<td class="th">状态</td>
				  	<td style="text-align:left">
					  	<input type="radio" name="status" value="132" id="selectValid" <c:if test="${adminUser.status==132}">checked</c:if>> <label for="selectValid" style="font-weight:normal">启用</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  	<input type="radio" name="status" value="134" id="selectInValid" <c:if test="${adminUser.status==134}">checked</c:if>> <label for="selectInValid" style="font-weight:normal">禁用</label>
					</td>
				  </tr>
				  <tr>
				  	<td class="th">身份证号</td>
				  	<td class="td"><input type="text" class="inputNotNull idno form-control" id="idCard" name="idCard" value="${adminUser.idCard}" maxlength="18" onkeyup="value=value.replace(/\s/g,'')"></td>
				  	<td align="left" id="idCardError"></td>
				  </tr> 
				  <tr>
				  	<td class="th">备注</td>
				  	<td class="td"><textarea cols="48" rows="5" name="remark" maxlength="500" class="form-control">${adminUser.remark}</textarea></td>
				  </tr>                      
                  <tr>
                    <td class="th"></td>
                    <td class="td" style="text-align:left;">
                    	<a href="javascript:void(0)" class="btn btn-primary btn-sm" id="updBtn">确定</a>
                    	<a href="init" class="btn btn-primary btn-sm">返回</a>
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