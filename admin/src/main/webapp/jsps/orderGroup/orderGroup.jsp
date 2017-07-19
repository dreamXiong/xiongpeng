<%@ page language="java" pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
	<tiles:put name="title" value="订单列表" />
	<tiles:put name="body" type="String">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>订单列表</title>
</head>
<c:set value="orderGroup" var="modalName"></c:set>
  <link rel="stylesheet" href="${ctx}/css/me.css">
  <link rel="stylesheet" href="${ctx}/css/jquery-ui.min.css">
  <link rel="stylesheet" href="${ctx}/css/jq-ui-rest.css">
  
  <script src="${ctx}/js/hgl/jquery-ui.min.js"></script> 
  <script src="${ctx}/js/hgl/datepicker-zh.js"></script>  
  <script src="${ctx}/js/hgl/orderGroup.js"></script>
  <style>
	  a {
	  cursor:pointer;
		}
  </style>
<body class="skin-blue fixed">
<div class="wrapper">
  <div class="content-wrapper">
  	<section class="content-header">
      <h1>我的主页<small>我的订单</small>
      </h1>
    </section>
    <section class="content">
      <div class="row no-margin">
    		<div class="box box-primary">
    			 <div class="box-body">
             <form id="key_${modalName}_qryFrm" class="form-horizontal">
				<input type="hidden" value="0" id="orderType" name="orderType"/>
				<input type="hidden" value="0" id="orderState" name="orderState"/>
				<input type="hidden" id="orderGroupId" name="orderGroupId"/>
				<input type="hidden" id="stopReason" name="stopReason"/>
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
                <div class="deal order orderType">
                  <label>订单类型</label>
	                <!--  <div class="pull-left navs orderType"> -->
						<a class="active" name="0">全部</a>
						<a class="" name="262" >招商订单</a>
						<a class="" name="264">订货订单</a>
					<!-- </div> -->
                </div>
              </div>
              <div class="row no-margin">
                <div class="deal rade" id="orderStateSel">
                  	 <label >交易类型</label>   
						<a id="allOrderGroup" class="active" name="0">全部</a>
						<a id="staus200" name="200">待确定(${orderStatesCount.staus200 })</a>
						<a id="staus202" name="202">待付款(${orderStatesCount.staus202 })</a>
						<a id="staus204" name="204">待补发货(${orderStatesCount.staus204 })</a>
						<a id="staus208" name="208">待发货(${orderStatesCount.staus208 })</a>  
						<a id="staus210" name="210">待确定收货(${orderStatesCount.staus210 })</a>
						<a id="staus218" name="218">待确定终止(${orderStatesCount.staus218 })</a>
						<a id="staus220" name="220">交易完成(${orderStatesCount.staus220 })</a>
                </div>
                
              </div>
             </form>
              <div id="key_${modalName}_list">
               		<%@include file="orderGroupList.jsp"%>
              </div>
    			 </div>
    		</div> 
    	</div>
    </section>
    
  </div>
</div>
<!-- 订单取消时弹出框 -->


	<!-- bootstrap订单取消弹出框  -->
	<div class="modal fade" id="showCancleOrder">
  		<div class="modal-dialog">
    		<div class="modal-content">
      			<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        			<h4 class="modal-title" style="font-weight:bold;">订单取消</h4>
      			</div>
      			<div class="modal-body">
        			<form class="form-horizontal">
		              <div class="form-group">
		                <label for="recipient-name" class="control-label col-sm-2" style="font-weight:normal;">取消原因</label>
		                <div class="col-sm-10">	
		                	<textarea type="text" class="form-control" id="textShow" maxlength="100" rows="3"></textarea>
		                </div>
		              </div>
		            </form>
      			</div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        <button type="button" class="btn btn-primary" onclick="cancleOrderGroup();">确认</button>
		      </div>
    		</div><!-- /.modal-content -->  
  		</div><!-- /.modal-dialog -->
	</div>
	
	
	<!-- bootstrap终止订单弹出框  -->
	<div class="modal fade" id="showStopOrder">
  		<div class="modal-dialog">
    		<div class="modal-content">
      			<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        			<h4 class="modal-title" style="font-weight:bold;">终止订单</h4>
      			</div>
      			<div class="modal-body">   
        			<form class="form-horizontal">
		              <div class="form-group">
		                <label for="recipient-name" class="control-label col-sm-2" style="font-weight:normal;">终止原因</label>
		                <div class="col-sm-10">	
		                	<textarea type="text" class="form-control" id="stopTextShow" maxlength="100" rows="3"></textarea>
		                </div>
		              </div>
		            </form>
      			</div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        <button type="button" class="btn btn-primary" onclick="stopOrderGroup();">确认</button>
		      </div>
    		</div><!-- /.modal-content -->  
  		</div><!-- /.modal-dialog -->
	</div>
	

<!-- 订单改价弹出窗体   单价 -->	
<div class="modal fade" id="priceModal_Upd" style="padding:0px;">
	<div class="modal-dialog modal-sm">
		<div class="modal-content" style="box-shadow:8px 8px 12px #3c8dbc;border-radius:10px;width:500px;">
      		<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="updStateClose" onclick="javascript:location.reload()"><span aria-hidden="true">&times;</span></button>
        		<span class="modal-title" style="background:#3c8dbc;border-radius:10px;color:white;font-size:14px;font-weight:normal;padding:4px;margin:0">修改订单价格信息 </span>       		
      		</div>
     		<div class="modal-body" id="modalBody">
     		</div>
    		<div class="modal-footer" style="text-align:center">
				<button type="button" class="btn btn-primary" id="updPriceBtn">确定</button>
        		<button type="button" class="btn btn-default" data-dismiss="modal" onclick="javascript:location.reload()">取消</button>
    		</div>
    	</div>
	</div>
</div>


<!-- 订单改价弹出窗体   总价 -->	
<div class="modal fade" id="payMoneyModal_Upd" style="padding:0px;">
	<div class="modal-dialog modal-sm">
		<div class="modal-content" style="box-shadow:8px 8px 12px #3c8dbc;border-radius:10px;width:500px;">
      		<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="updStateClose" onclick="javascript:location.reload()"><span aria-hidden="true">&times;</span></button>
        		<span class="modal-title" style="background:#3c8dbc;border-radius:10px;color:white;font-size:14px;font-weight:normal;padding:4px;margin:0">修改订单价格信息 </span>       		
      		</div>
     		<div class="modal-body" id="modalBody">
     		</div>
    		<div class="modal-footer" style="text-align:center">
				<button type="button" class="btn btn-primary" id="updMoneyBtn">确定</button>
        		<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
    		</div>
    	</div>
	</div>
</div>
	
	
	<!-- <div id="dialog" class="dialog">
	  <form>
	    <label for="name">取消原因</label>
	     <textarea style="width: 409px; height: 78px;" type="text" id="textShow" maxlength="100"></textarea>
	  </form>
	</div> -->
	<!-- 订单终止 -->
	<!-- <div id="stopOrderDivlog" class="dialog">
	  <form>
	    <label for="name">终止原因</label>
	     <textarea style="width: 409px; height: 78px;" type="text" id="stopTextShow" maxlength="100"></textarea>
	  </form>
	</div> -->
	
</body>
	<script type="text/javascript">
		EcWeb.currentModalName = '${modalName}';
	</script>
</html>
</tiles:put>
</tiles:insert>