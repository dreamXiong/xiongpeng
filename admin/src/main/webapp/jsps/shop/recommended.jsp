<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
<tiles:put name="title" value="用户等级列表" />
<tiles:put name="body" type="String">
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>平台推荐规则列表</title>
  
</head>
<body class="skin-blue fixed">
<div class="wrapper">
<input id="msgPosition" value="false" type="hidden"/>
  <div class="content-wrapper">
  	<section class="content-header">
      <h1>
        我的主页
        <small>推荐设置</small>
      </h1>
    </section>
    <section class="content">
      <div class="row no-margin">
    		<div class="box box-primary">
    			 <div class="box-body">
              <table class="table table-hover table-bordered">
                  <thead>
                    <tr>
                      <th>关系类型</th>
                      <th>奖励形式</th>
                      <th>奖励类型</th>
                      <th>奖励方式</th>
                      <th>奖励</th>
                      <th>操作</th>
                    </tr>
                  </thead>
                  <tbody> 
                   <c:forEach items="${recommendedTypes}" var="item">
                    <tr>
                      <td>
                      	<c:if test="${item.recommendedType == 114 }">师傅</c:if>
							
							<c:if test="${item.recommendedType == 106 }">个人</c:if>
							
							<c:if test="${item.recommendedType == 108 }">递推人员</c:if>
							<c:if test="${item.recommendedType == 104 }">经销商</c:if>
							推荐
							<c:if test="${item.isRecommendedType == 104 }">
							经销商
							</c:if>
							
                      </td>
                      <td><c:if test="${item.way == 0 }">直接给予</c:if>
							<c:if test="${item.way == 1 }">经予</c:if>			
						</td>
                      <td>
                      	<c:if test="${item.rewardType == 0 }" var="isjifen">积分</c:if>
						<c:if test="${item.rewardType == 1 }" var="isjine">金额</c:if>
                      </td>
                      <td><c:if test="${item.rewardWay == 0 }">	百分比</c:if>
							<c:if test="${item.rewardWay == 1 }">固定	</c:if>	
						</td>
                      <td>${item.reward}
						<c:if test="${item.rewardWay == 0 }">%</c:if>
						<c:if test="${item.rewardWay == 1&& isjine}">元</c:if>
						<c:if test="${item.rewardWay == 1&& isjifen}">积分</c:if>
										
					</td>
                      
                      
                      <td>
                        
                         <button type="button" onclick="getupdateRecommended('${item.id}');" class="btn btn-primary btn-sm" data-toggle="modal">修改</button>
                      </td>
                    </tr>
                    </c:forEach>
                  </tbody>
              </table>
              <div style="margin-top:20px;color:#999">
              	<div>注："平台推荐规则"表示客户推荐用户注册给予奖励.</div>
              	  
             </div>
    			 </div>
    		</div>
    	</div>
    </section>
  </div>
</div>
<!-- <div class="modal fade no-modal" id="modal">

</div> -->

 <!--   修改弹出层 -->
<div class="modal fade" id="recommendedTypes" data-backdrop="static">
  <%@include file="updateRecommendedTypes.jsp"%>
</div>
</body>
</html>
 <script>

  
  function getupdateRecommended(id){
		 $.ajax({
		        type:"POST",
		        url :"recommendedTypes",
		        data:{
		        	rId:id,
		        },
		        success: function(response){
		        	
		        	$("#recommendedTypes").html(response);
		        }
		 	});
		$("#recommendedTypes").modal("show");
	}
  
  function updateRecommended(){
	  
	  var rewards = $("#reward").val();
		var twoScale = new RegExp(/(^\d+(\.\d{0,2})?$)/);
		if(rewards=="" || rewards==null){
			$("#msgrules").text("奖励不能为空,并且为数字");
			return false;
		}
		$("#msgrules").text();
		//alert(!twoScale.test(rewards));      
		if(isNaN(rewards) || !twoScale.test(rewards)){
			$("#msgrules").text("奖励不能为空,并且为数字(最多两位小数)");
			return false;
		}
		$("#updateRecommended").submit();
	}
</script> 

</tiles:put>
</tiles:insert>