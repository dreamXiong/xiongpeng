<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
	<tiles:put name="title" value="店铺管理员管理" />
	<tiles:put name="body" type="String">
	<c:set value="shopUserRef" var="modalName"></c:set>	
	<html>
		<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /></head>
		<body>
			<form id="key_modalName_qryFrm">
			
			<input id="changeRefId" type="text"/>
				<div class="content-wrapper">
				    <section class="content-header">
				      <h1>
				       	 我的主页
				        <small>店铺管理员管理</small>
				      </h1>
				    </section>
				    <section class="content">
				    	<div class="row no-margin">         
				    		<div class="box box-primary">
				    			<div class="box-body pad table-responsive" id="pox">
				    				  <div class="fil details">
				                <div>
				                <ul class="nav text-center one" id="shop">
					                    <li class="nav-title">管理员</li>
							                <c:forEach var="item" items="${adminUserList}" varStatus="s">
							                	 <li onclick="selectRef('${item.id}',this);" class="adminUser_${s.index} clearfix" >
							                	 	<div class="pull-left">
							                	 	<input type="hidden"  id="adminUser_${s.index}" value="${item.id}">
								                      ${item.name}
								                    </div>
							                    </li>
							                </c:forEach> 
					                	</li>
					                	 <ul class="nav text-center two" id="adminUser">
											<li class="nav-title">厂家</li>
											<c:forEach items="${shopInfoList}" var="item"
												varStatus="loop">
												<li class="clearfix">
													<div class="pull-left">
														<input name="shopRef"
															onchange="shopRefAdd('${item.id }');" type="checkbox"
															value="${item.id}" />${item.companyName}
													</div>
												</li>
											</c:forEach>
										</ul>
				               		 </ul>
				               		 
				                 
				                    
				                  </ul>
				                </div>
				              </div>
				    			</div>
				    		</div>
				    	</div>
    </section>
  </div>
			</form>
		</body>
		<script type="text/javascript">
		jQuery(function($) {
			
// 			$(document).delegate('click','.nav .clearfix',function(){
// 				 $(this).addClass('currten').siblings('clearfix').removeClass('currten');
// 			});
			
			//默认选中第一个管理员
			$("[class='adminUser_0 clearfix']").css('background','#aaa');
			var userId =$("#adminUser_0").val();
			$("#changeRefId").val(userId);
			//没有管理员返回false
			if(!userId){
				return false;
			}
			userToShop(userId);
			    
		});
		
		function userToShop(userId){
			$.ajax({
				type : 'GET', 
				url : 'userToShop_ajax?userId=' +userId , 
				dataType : 'json',
				success : function(jsonList) {
					userToShopHtml(jsonList[0],jsonList[1]);
				}
			});
		};
		
		function userToShopHtml(response1,response2){
			var tableStr="";
			var tableStr1="";
			tableStr+="<li class='nav-title'>厂家</li>";
            	 for(var i=0;i<response1.length;i++){
            		 tableStr+="<li class='clearfix'>"
            	 	 +"<div class='pull-left' >";
            		 var shopId =response1[i].shopId;
            		 var shopName =response1[i].companyName;
            		 if(shopName==undefined){
            			 shopName="";
            		 }
            		 tableStr+= "<input name='shopRef' checked='checked' onchange='shopRefAdd("+shopId+")'  type='checkbox' value="+shopId+" />"+"<span style='visibility: hidden'>1</span>"+shopName+"</div></li>";
            	 }
            	 
            	 for(var i=0;i<response2.length;i++){
            		 
            		 tableStr1+="<li class='clearfix'>"
            	 	 +"<div class='pull-left' >";
            		 var shopId =response2[i].shopId;
            		 var shopName =response2[i].companyName;
            		 if(shopName==undefined){
            			 shopName=shopId;
            		 }
            		 tableStr1+= "<input name='shopRef'  type='checkbox' onchange='shopRefAdd("+shopId+")' value="+shopId+" />"+"<span style='visibility: hidden'>1</span>"+shopName+"</div></li>";
            	 }
            	 $("#adminUser").html(tableStr+tableStr1);
		};
		
		 
		 
		function selectRef(id,obj){
			$("#changeRefId").val(id);
			//取消全部class的样式
			$("li[class^=adminUser_]").css("background-color","");
			//添加样式
			$(obj).css("background-color","#aaa");
			userToShop(id);
		}
		
		function shopRefAdd(shopId){
			var userId=$("#changeRefId").val();
			$.ajax({
				type : 'GET', 
				url : 'shopRefAdd_ajax?userId=' +userId+"&shopId="+shopId , 
				dataType : 'json',
			});
		}





		
		</script>
	</html>
	</tiles:put>
</tiles:insert>