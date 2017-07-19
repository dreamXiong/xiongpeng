<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
<tiles:put name="title" value="添加用户"/>
<tiles:put name="body" type="String">
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>添加用户</title>
	<link rel="stylesheet" href="${ctx}/css/move-border.css">
	<script type="text/javascript" src="${ctx}/js/hgl/adminuser.js"></script>
	<script type="text/javascript">
	
	</script>
</head>
<body class="skin-blue fixed">
<div class="wrapper">
  <div class="content-wrapper">
  	<section class="content-header">
      <h1>
       	 我的主页
        <small>添加用户信息</small>
      </h1>
    </section>
    <section class="content">
      <div class="row no-margin">
    		<div class="box box-primary">
    		<div class="p8">
      			<a href="init" class="btn btn-primary key">返回列表</a>
      		</div>
    		<div class="box-body">
            <form  id="addAdminUser" name="addAdminUser" method="post" class="form-horizontal">
                <table class="table">
                  <tr>
                    <td class="th" width="20%">用户名</td>
                    <td class="td" width="15%"><input type="text" id="userName" name="userName" class="form-control inputNotNull" maxlength="30" ></td>                  
                    <td align="left" width="65%" id="userNameError"></td>
                  </tr>
                  <tr>
                    <td class="th" width="20%">密码</td>
                    <td class="td" width="15%"><input type="password" id="password" name="password" class="form-control inputNotNull input.password" maxlength="20" onkeyup="value=value.replace(/\s/g,'')"></td>
                    <td align="left" width="65%" id="passwordError"></td>
                  </tr>
                  <tr>
                     <td class="th">确认密码</td>
                     <td class="td"><input type="password" id="cfdPassword" class="form-control inputNotNull input.password" onkeyup="value=value.replace(/\s/g,'')" maxlength="20"></td>
                     <td align="left" id="cfdPasswordError"></td>
                  </tr>
                  <tr>
                    <td class="th">姓名</td>
                    <td class="td"><input type="text" id="name" name="name" class="form-control inputNotNull" maxlength="30"></td>
                    <td align="left" id="nameError"></td>
                  </tr>
                  <tr>
                    <td class="th">常用地址</td>
                    <td class="td"><input type="text" id="address" name="address" class="form-control inputNotNull" maxlength="250"></td>
                    <td align="left" id="addressError"></td>
                  </tr>
                  <tr>
                    <td class="th">电话号码</td>
                    <td class="td"><input type="text" id="mobile" name="mobile" class="form-control inputNotNull phone" maxlength="11"></td>
                    <td align="left" id="mobileError"></td>
                  </tr>
                  <tr>
                    <td class="th">邮箱地址</td>
                    <td class="td"><input type="text" id="email" name="email" class="form-control inputNotNull email" maxlength="100"></td>
                    <td align="left" id="emailError"></td>
                  </tr>  
                  <tr>
                  	<td class="th">角色</td>
                  	<td style="text-align:left">
                  		<select id="roleId" name="roleId">
                  			<option value="0">===请选择===</option>
                  			<c:forEach items="${adminRoles}" var="role">
                  				<option value="${role.id}">${role.name}</option>
                  			</c:forEach>
                  		</select>                 		
                  	</td>
                  	<td id="roleIdError"></td>
                  </tr>  
                  <tr>
                  	<td class="th">类型</td>
                  	<td style="text-align:left">
                  		<select name="type" id="type">
                  			<option value="0">===请选择===</option>
                  			<option value="102">厂家</option>
                  			<option value="104">经销商</option>
                  			<option value="106">终端客户</option>
                  			<option value="108">地推人员</option>
                  			<option value="110">仓库管理员</option>
                  			<option value="112">店铺管理员</option>
                  		</select>
                  	</td>
                  	<td id="typeError"></td>
                  </tr> 
				  <tr>
				  	<td class="th">状态</td>
				  	<td style="text-align:left">
					  	<input type="radio" name="status" value="132" id="selectValid"><label for="selectValid" style="font-weight:normal">启用</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  	<input type="radio" name="status" value="134" id="selectInValid" checked="checked"><label for="selectInValid" style="font-weight:normal">禁用</label>
					</td>
				  </tr>
				  <tr>
				  	<td class="th">身份证号</td>
				  	<td class="td"><input type="text" id="idCard" name="idCard" class="form-control inputNotNull idno" maxlength="18" onkeyup="value=value.replace(/\s/g,'')"></td>
				  	<td align="left" id="idCardError" style="text-align:left;"></td>
				  </tr> 
				  <tr>
				  	<td class="th">备注</td>
				  	<td class="td"><textarea cols="48" rows="5" id="remark" name="remark" maxlength="500"></textarea></td>
				  </tr>         
                  <tr>
                    <td class="th"></td>
                    <td class="td">
                    	<a href="javascript:void(0)" class="btn btn-primary btn-sm" id="addBtnInsert">确定</a>
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