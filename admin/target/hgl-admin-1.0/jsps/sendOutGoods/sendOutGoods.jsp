<%@ page language="java" pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
	<tiles:put name="title" value="发货列表" />
	<tiles:put name="body" type="String">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>发货列表</title>
</head>
<c:set value="sendOutGoods" var="modalName"></c:set>
  <link rel="stylesheet" href="${ctx}/css/me.css">
  <link rel="stylesheet" href="${ctx}/css/jquery-ui.min.css">
  <link rel="stylesheet" href="${ctx}/css/jq-ui-rest.css">
  
  <script src="${ctx}/js/hgl/jquery-ui.min.js"></script> 
  <script src="${ctx}/js/hgl/datepicker-zh.js"></script>  
  <script src="${ctx}/js/hgl/sendOutGoods.js"></script> 
  <style>
	  a {
	  cursor:pointer;
		}
  </style>
<body class="skin-blue fixed">
<div class="wrapper">
  <div class="content-wrapper">
  	<section class="content-header">
      <h1>我的主页<small>发货列表</small>
      </h1>
    </section>
    <section class="content">
      <div class="row no-margin">
    		<div class="box box-primary">
    			 <div class="box-body">
             <form id="key_${modalName}_qryFrm" class="form-horizontal">
				<input type="hidden" id="orderGroupId" name="orderGroupId"/>
				<input type="hidden" id="version" name="version"/>
              <div class="row no-margin">
                <div class="pull-left">
                  <label>订单时间</label>
                  <input type="text" class="short" name="startTime" readonly id="from"> ~ <input type="text" class="short"  name="endTime" readonly id="to">
                </div>
                <div class="pull-right">
                  <div>
                    <input name="searchText" type="text" class="long" placeholder="请输入订单号或商品信息"> 
                    <button type="button" onclick="submitform();">搜索订单</button>
                  </div>
                </div>
              </div> 
              <div class="row no-margin">
              </div>
             </form>
             
	             <div id="key_${modalName}_list">
	          		<%@include file="sendOutGoodsList.jsp"%>
	          	 </div>
    		</div>
    		</div>
    	</div>
    </section>
  </div>
</div>
</body>
	<script type="text/javascript">
		EcWeb.currentModalName = '${modalName}';
	</script>
</html>
</tiles:put>
</tiles:insert>