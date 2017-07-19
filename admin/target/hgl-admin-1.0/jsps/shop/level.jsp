<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
<tiles:put name="title" value="用户等级列表" />
<tiles:put name="body" type="String">
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>用户等级列表</title>
  
</head>
<body class="skin-blue fixed">
<div class="wrapper">
<input id="msgPosition" value="false" type="hidden"/>
  <div class="content-wrapper">
  	<section class="content-header">
      <h1>
        我的主页
        <small>等级设置</small>
      </h1>
    </section>
    <section class="content">
      <div class="row no-margin">
    		<div class="box box-primary">
    			 <div class="box-body">
              <table class="table table-hover">
                  <thead>
                    <tr>
                      <th>会员等级</th>
                      <th>分界经验值</th>
                      <th>经验获取规则</th>
                      <th>优惠券使用规则</th>
                      <th>可获得折扣</th>
                      <th>免费提现次数</th>
                      <th>等级说明</th>
                      <th>操作</th>
                    </tr>
                  </thead>
                  <tbody> 
                   <c:forEach items="${leveList}" var="item">
                    <tr>
                      <td>${item.levelName}</td>
                      <td>${item.minExp} ≤ 经验值<  ${item.maxExp}</td>
                      <td>${item.expProportion}分</td>
                      <td>${item.couponRule}</td>
                      <td>${item.userSale}</td>
                      <td>${item.cashNum}</td>
                      <td>${item.levelRemark}</td>
                      
                      <td>
                        
                         <button type="button" onclick="getupdateLevel('${item.id}');" class="btn btn-primary btn-sm" data-toggle="modal">修改</button>
                      </td>
                    </tr>
                    </c:forEach>
                  </tbody>
              </table>
              <div style="margin-top:20px;color:#999">
              	<div>注："经验获取规则"表示购买成功后，每1元可获得经验，实际获得的经验为：金额乘以规则后四舍五入.</div>
              	<div style="text-indent:2em;">"免费提现次数"表示每月值，在次月归零.</div>
              	<div style="text-indent:2em;">"优惠券使用规则"表示在购买时，每100元能使用的优惠卷，实际的计算值为：订单金额*优惠券使用规则/100后取整.</div>
              	  
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
<div class="modal fade" id="getupdateLevel" data-backdrop="static">
  <%@include file="updateLevel.jsp"%>
</div>
</body>
</html>
 <script>

  
  function getupdateLevel(id){
		 $.ajax({
		        type:"POST",
		        url :"getUpdateLevel",
		        data:{
		        	id:id,
		        },
		        success: function(response){
		        	
		        	$("#getupdateLevel").html(response);
		        }
		 	});
		$("#getupdateLevel").modal("show");
	}
  
  function updateLevel(){
	  
		var addType = validateForm("updateLevel");
		if(!addType){
			return;
		}
		 var minExp= $("#minExp").val();
		 var maxExp= $("#maxExp").val();
		 minExp = parseInt(minExp);
		 maxExp = parseInt(maxExp);                 
		if(minExp>=maxExp){ 
			$("#maxExp").addClass("onerrInput");
			return;
		}
		$("#updateLevel").submit();
	}
</script> 

</tiles:put>
</tiles:insert>