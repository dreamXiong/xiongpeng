<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>    
<tiles:insert beanName="indexPage">
<tiles:put name="tile" value="WAP端师傅的审核"/>
<tiles:put name="body" type="String">    
<html>
<c:set value="webUser" var="modalName"></c:set>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>前台用户管理</title>
<script type="text/javascript">
	$("document").ready(function(){
		$("input:radio[name='state']").change(function(){
			var state = $(this).val();
			$("#stateError").text("");			
		});
		
		$("#remark").focus(function(){
			$("#remarkError").text("");
		});
	});
	
	
	function updateWorkUserStatus(){
		var isError = true;
		//状态
		var status = $("input[name='state']:checked").val();
		if(status==null||status==""){
			$("#stateError").text("状态必须选择");
			isError = false;
		}else if(status==2){
			var remark = $("#remark").val();
			if(remark==null||remark==""){
				$("#remarkError").text("拒绝理由必须填写");
				isError = false;
			}
		}
		
		if(isError == false){
			return;
		}		
		
		updateWebUser.action ="doUpdateWorkerStatus";
		updateWebUser.submit();
	}
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
      					<a href="doInitWorker" class="btn btn-primary key">返回列表</a>
      	  			</div>
    	  		<div class="box-body">
            		<form  action="" id="updateWebUser" name="updateWebUser" method="post" class="form-horizontal">
                		<table class="table" style="width:100%;height:100%;">
                  			<tr>
				  				<td class="th" style="width:15%">姓名                  				
				  					<input type="hidden" value="${webUser.id}" name="id">
				  				</td>
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
			                  	<td class="th">类型 </td>
			                  	<td style="text-align:left">
			                  		<c:if test="${webUser.typeId==114}">师傅</c:if>		                  			
			                  	</td>
			                 </tr>
			                 <tr>
			                 	<td>服务</td>
			                 	<td style="text-align:left;">
			                 		<c:forEach items="${serviceTypeDtos}" var="serviceTypeDto">
										<div class="skill-warp clearfix box-shadow">
											<h3 id="${serviceTypeDto.id}">${serviceTypeDto.name}</h3>
												<c:forEach items="${serviceTypeDto.childList}" var="childServiceType">
													<label for="${childServiceType.id}">
														<input type="checkbox" name="skill" id="${childServiceType.id}" value="${childServiceType.id}" <c:if test="${childServiceType.checked==1}">checked</c:if>>
														<span>${childServiceType.name}</span>
													</label>
												</c:forEach>
										</div>
									</c:forEach>
			                 	</td>
			                 </tr> 
			                 <tr>
			                  	<td class="th">头像</td>
			                  	<td style="text-align:left">
			                  		<img alt="" src="showImage?id=${userInfo.id}&url=${userInfo.image}" width="300" height="200">		                  			
			                  	</td>
			                 </tr> 
			                 <tr>
			                  	<td class="th">身份证正面照</td>
			                  	<td style="text-align:left">
			                  		<img alt="" src="showImage?id=${userInfo.id}&url=${userInfo.imageFace}" width="300" height="200">		                  			
			                  	</td>
			                 </tr> 
			                 <tr>
			                  	<td class="th">身份证反面照</td>
			                  	<td style="text-align:left">
			                  		<img alt="" src="showImage?id=${userInfo.id}&url=${userInfo.iamgeBack}" width="300" height="200">		                  			
			                  	</td>
			                 </tr>
			                 <tr>
			                 	<td>状态</td>
			                 	<td style="text-align:left;">
			                 		<label><input type="radio" name="state" value="1" <c:if test="${webUser.state==1}">checked</c:if>>审核通过</label>
			                 		<label><input type="radio" name="state" value="2" <c:if test="${webUser.state==2}">checked</c:if> >审核拒绝</label>
			                 		<span style="margin-left:20px;color:red;font-size:12px;" id="stateError"></span>
			                 	</td>
			                 </tr> 				  
							 <tr>
							  	<td class="th">拒绝理由</td>
							  	<td class="td" style="text-align:left">
							  		<textarea cols="48" rows="5" name="remark" maxlength="500" id="remark"></textarea>	
							  		<span id="remarkError" style="font-size:12px;color:red;margin-left:20px;"></span>				  		
							  	</td>
							 </tr>                      
			                 <tr>
			                    <td class="th"></td>
			                    <td class="td" style="text-align:left;">
			                    	<a href="javascript:void(0);" class="btn btn-primary btn-sm" onclick="updateWorkUserStatus()">确定</a>
			                    	<a href="doInitWorker" class="btn btn-primary btn-sm">返回</a>
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