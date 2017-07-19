<%@ page language="java" pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
	<tiles:put name="title" value="库存管理" />
	<tiles:put name="body" type="String">
		<c:set value="inventory" var="modalName"></c:set>
		<html lang="en">
<head>
<meta charset="UTF-8">
<title>库存管理</title>
<link rel="stylesheet" href="${ctx}/css/stock.css">
<%-- <script src="${ctx}/js/uploadPreview.min.js"></script> --%>
<script type="text/javascript" src="${ctx}/js/hgl/inventory.js"></script>
<script src="${ctx}/js/hgl/district.js"></script>
</head>
<body class="skin-blue fixed">
	<div class="wrapper">

		<div class="content-wrapper">
			<section class="content-header">
				<h1>
					我的主页 <small>库存管理</small>
				</h1>
			</section>
			<section class="content">
				<div class="row no-margin">
					<div class="box box-primary">
						<div class="box-body">
							<div class="nav-tabs-custom">
								<ul class="nav nav-tabs">
									<li class="active"><a href="#activity" data-toggle="tab"
										aria-expanded="true">库存列表</a></li>
									<li class=""><a href="#timeline" data-toggle="tab"
										aria-expanded="false">库存发布</a></li>
								</ul>

								<div class="tab-content">
									<div class="tab-pane active" id="activity">
										<form onsubmit="return false;" class="form-inline"
											id="key_${modalName}_qryFrm">
											<div class="row">
													<div class="form-group">
														<label>名称</label>
														<div class="input-group">
															<input type="text" class="form-control"
																placeholder="请输入查询名称" name="name">
														</div>
													</div>
													<div class="form-group">
														<label>大类</label>
														<div class="input-group">
															<select class="form-control" name="mainId"
																id="mainIdSelect">
																<option value="-1">---请选择---</option>
																<c:forEach items="${mList}" var="item" varStatus="s">
																	<option value="${item.id}">${item.name}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													<div class="form-group">
														<label>品牌</label>
														<div class="input-group">
															<select class="form-control" name="brandId"
																id="brandIdSelect">
																<option value=""></option>
															</select>
														</div>
													</div>
													<div class="form-group">  
														<label>产品</label>
														<div class="input-group">
															<select class="form-control" name="productId"
																id="productIdSelect">
																<option value=""></option>
															</select>
														</div>
													</div>
													<div class="form-group">
														<label >仓库</label>
														<div class="input-group">
															<select class="form-control" name="warehouseId">
																<option value="-1">---请选择---</option>
																<c:forEach items="${warehouseList}" var="item" varStatus="s">
																	<option value="${item.id}">${item.warehouseName}</option>
																</c:forEach>
															</select>
														</div>
													</div>
													<div class="form-group">
														<label>状态</label>
														<div class="input-group">
															<select class="form-control" name="status">
																<option value="-1">全部</option>
																<option value="1">上架中</option>
																<option value="0">已下架</option>
															</select>
														</div>
													</div>
												<div class="form-group">
													<button type="submit" class="btn btn-primary" onclick="submitform()">查询</button>
													<button type="button" class="btn btn btn-primary" onclick="modalBatchPriceAjax();">批量定价</button>
												</div>
											</div>

										</form>
										<div id="key_${modalName}_list" class="col-sm-12">
											<%@include file="inventoryList.jsp"%>
										</div>
									</div>
									<div class="tab-pane " id="timeline">
											<div class="auto-width">
											<form onsubmit="return false;" id="timelineForm">
												<h3>库存信息</h3>
												<table class="table table-bordered">
													<tr>
														<%-- <td>省</td>
														<td>
															<select id="province" name="province" class="form-control select1" onchange="changeGradeCC();">
																<option value="0">-请选择-</option>
										                          <c:forEach  var="item" items="${provinceInfos}" varStatus="status">
										                          	<option value="${item.id}">${item.name}</option>
										                          </c:forEach>	
															</select>
                        								</td>
														<td>市</td>
														<td>
															<select id="city" name="city" class="form-control select1" onchange="changeCityCC();"></select>
														</td>
														<td>区/县</td>
														<td>
															<select id="country" name="country" class="form-control select1" onchange="changeCountryCC();"></select>
														</td> --%>
														<td>仓库</td>
														<td>
															<!-- <select id="warehouse" name="warehouse" class="form-control select1" ></select> -->
															
															
															<select id="warehouse" class="form-control select1" name="warehouse">
																<option value="-1">---请选择---</option>
																<c:forEach items="${warehouseList}" var="item" varStatus="s">
																	<option value="${item.id}">${item.warehouseName}</option>
																</c:forEach>
															</select>
														</td>
													</tr>
												</table>
												<h3>产品分类</h3>
							                    <table class="table table-bordered" style="margin-bottom:5px">
							                      <tr>
							                        <td>
							                            <div class="input-group">
							                              <input type="text" class="form-control" name="name">
							                              <span class="input-group-btn">
							                                <button class="btn btn-primary" type="button" id="searchProduct" onclick="searchProductx()">搜索</button>
							                              </span>
							                            </div>
							                        </td>
							                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							                        <td>分类</td>
							                        <td>
							                        	<select class="form-control" id="mainTypeSelect2" name="firstTypeId">
							                        		<option value="-1">---请选择---</option>
															<c:forEach items="${mList}" var="item" varStatus="s">
																<option value="${item.id}">${item.name}</option>
															</c:forEach>
							                        	</select>
							                        </td>
							                        <td><select class="form-control" id="secondTypeSelect2" name="secondTypeId"></select></td>
							                        <td><select class="form-control" id="thirdTypeSelect2" name="thirdTypeId"></select></td>
							                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							                        <td>品牌</td>
							                        <td><select class="form-control" id="brandIdSelect2" name="brandIds"></select></td>
							                      </tr>
							                    </table>
											</form>
												<div id="info" class="info" for="">
													<label>双击选择你所需要的产品名称和附加属性</label> <a href="javascript:;"
														class="hide" id="choice">重新选择</a>
													<div class="conceal">
														<table class="table no-margin">
															<tr class="bg-info">
																<th width="300">名称</th>
																<th width="300">分类</th>
																<th width="300">品牌</th>
																<th width="300">附加属性</th>
															</tr>
														</table>

														<div class="table-height" style="width: 1216px">
															<table class="table table-hover" id="tab">
															</table>
														</div>
													</div>
													<div></div>
												</div>
												<div id="specDiv" style="display: none">
													<%@include file="spec.jsp" %>
												</div>
											</div>
										
									</div>
								</div>
							</div>
						</div>
					</div>
			</section>

		</div>

	</div>
	</div>

	<div id="modalDiv">
		<!-- 模态对话框插入的地方 -->
	</div>
	<div class="modal fade " id="promptModal">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">温馨提示</h4>
				</div>
				<div class="modal-body">
					<p></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	
<!-- 批量定价开始 -->
<div class="modal fade" id="modal2" data-backdrop="static">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">批量定价</h4>
      </div>
      <div class="modal-body clearfix">
       <form id="BatchPriceForm">
       		 <div class="one-head">
	          <label  class="one-title" for="bp">
	            <input type="radio" name="asPrice" id="bp" value="1"> 按入库价格
	          </label>
	          <div class="two-title" style="padding-left:20px;display:none">
	            <div>
	              <label for="bp-num1">
	                <input type="radio" name="priceMethod1" id="bp-num1" value="1"> 按比例 <input type="text" class="cleanInfo" style="width:88px" value="" name="method1Price1"> %
	              </label>
	            </div>
	            <div>
	              <label for="bp-num2">
	                <input type="radio" name="priceMethod1" id="bp-num2" value="2"> 按单价 <input type="text" style="width:88px" class="cleanInfo" value="" name="method1Price2"> 元
	              </label>
	            </div>
	          </div>
	        </div>
	        <div class="one-head">
	          <label class="one-title" for="pb">
	            <input type="radio" name="asPrice" id="pb" value="2"> 按零售标价
	          </label>
	          <div class="two-title" style="padding-left:20px;display:none">
	            <div>
	              <label for="bp-num3">
	                <input type="radio" name="priceMethod2" id="bp-num3" value="1"> 按比例 <input type="text" style="width:88px" value="" class="cleanInfo" name="method2Price1"> %
	              </label>
	            </div>
	            <div>
	              <label for="bp-num4">
	                <input type="radio" name="priceMethod2" id="bp-num4" value="2"> 按单价 <input type="text" style="width:88px" value="" class="cleanInfo" name="method2Price2"> 元
	              </label>
	            </div>
	          </div>
	        </div>
       </form>
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="batchPriceSubmit()">保存</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div>
<!-- 批量定价结束 -->
</body>
		<script type="text/javascript">
			EcWeb.currentModalName = '${modalName}';
		</script>
		</html>
	</tiles:put>
</tiles:insert>