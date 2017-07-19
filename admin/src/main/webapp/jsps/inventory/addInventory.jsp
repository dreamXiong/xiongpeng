<%@ page language="java" pageEncoding="UTF-8"%>
<div class="modal fade" id="modal-1" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">
					库存
					<c:choose>
						<c:when test="${operation=='update'}">修改</c:when>
						<c:when test="${operation=='add'}">添加</c:when>
						<c:otherwise>详情</c:otherwise>
					</c:choose>
				</h4>
			</div>
			<div class="modal-body" style="height: 500px; overflow-y: scroll">
				<form class="form-horizontal" action="saveInventory" method="post" id="saveInventory">
					<div class="row margin-bottom">
						<div class="col-xs-1"></div>
						<div class="col-xs-3">
							<div class="form-group">
								<label class="col-sm-6 control-label">锁定量</label>
								<div class="col-sm-6 no-right" style="padding-top: 7px">
									<span>${tbpi.unsaleInventory}</span>
								</div>
							</div>
						</div>
						<div class="col-xs-3">
							<div class="form-group">
								<label class="col-sm-6 control-label">入库量</label>
								<div class="col-sm-6 no-right" style="padding-top: 7px">
									<span>${tbpi.totalInventory}</span>
								</div>
							</div>
						</div>
						<div class="col-xs-4">
							<div class="form-group">
								<label class="col-sm-6 control-label">销售量</label>
								<div class="col-sm-6 no-right" style="padding-top: 7px">
									<span>${tbpi.salesCount}</span>
								</div>
							</div>
						</div>
						<div class="col-xs-1"></div>

					</div>
					<table class="table" id="inventoryTable">
						<tr>
							<td>状态</td>
							<td colspan="3"><input type="text" class="form-control" value="<c:choose><c:when test="${tbpi.status==0}">已下架 </c:when><c:otherwise>上架中</c:otherwise></c:choose>" disabled></td>
						</tr>
						<tr>
							<td>分类</td>
							<td>
								<select  id="linkMainType" class="form-control" <c:if test="${operation!='add'}">readonly="readonly"</c:if>>
									<c:if test="${not empty mLists}">
										<option value="">---请选择---</option>
										<c:forEach var="m" items="${mLists}" varStatus="s">
											<option value="${m.id}">${m.name}</option>
										</c:forEach>
									</c:if>
									<c:if test="${!not empty mLists}">
										<option>${tbpi.firstProductTypeName}</option>
									</c:if>
								</select>
							</td>
							<td>
								<select id="linkSecondType" class="form-control"<c:if test="${operation!='add'}">readonly="readonly"</c:if>>
									<option>${tbpi.secondProductTypeName}</option>
								</select>
							</td>	
							<td>
								<select id="linkThirdType" class="form-control" <c:if test="${operation!='add'}">readonly="readonly"</c:if>>
									<option>${tbpi.thirdProductTypeName}</option>
								</select>
							</td>
						</tr>	
						<tr>
							<td>品牌</td>
							<td colspan="3">
								<select id="linkBrand" class="form-control" <c:if test="${operation!='add'}">readonly="readonly"</c:if>>
									<option>${tbpi.brandName}</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>产品</td>
							<td colspan="3">
								<select id="productSelect" class="form-control inputNotNull" name="productId" <c:if test="${operation!='add'}">readonly="readonly"</c:if>>
								<option value="${tbpi.productId}">${tbpi.productName}</option>
							</select>
							</td>
						</tr>
						<tr>
							<td>货号</td>
							<td colspan="3">
								<input type="text" maxlength="30" name="code" class="form-control inputNotNull" value='${tbpi.code}' <c:if test="${operation!='add'}">readonly="readonly"</c:if>>
							</td>
						</tr>
						<tr>
							<td>名称</td>
							<td colspan="3">
								<input name="name" maxlength="100" type="text" class="form-control" value='${tbpi.name}'<c:if test="${operation=='info'}">readonly="readonly"</c:if>>
							</td>
						</tr>
						<tr>
							<td>厂家指导价</td>
							<td colspan="3">
								<input type="text" maxlength="10" name="salesPrice" class="form-control inputNotNull float"value="${tbpi.salesPrice}"<c:if test="${operation=='info'}">readonly="readonly"</c:if>>
							</td>
						</tr>
						<tr>
							<td>入库价格</td>
							<td colspan="3">
								<input type="text" maxlength="10" name="instockPrice" class="form-control inputNotNull float" value="${tbpi.instockPrice}"<c:if test="${operation=='info'}">readonly="readonly"</c:if>>
							</td>
						</tr>
						<tr>
							<td>出库价格</td>
							<td colspan="3">
								<input type="text" maxlength="10" name="outstockPrice" class="form-control <c:if test="${tbpi.status==1}">inputNotNull</c:if> float" value="${tbpi.outstockPrice}"<c:if test="${operation=='info'}">readonly="readonly"</c:if>>
							</td>
						</tr>
						<tr>
							<td>规格</td>
							<td colspan="3">
								<input type="text" maxlength="9" name="spec" class="form-control inputNotNull" value='${tbpi.spec}'<c:if test="${operation=='info'}">readonly="readonly"</c:if>>
							</td>
						</tr>
						<tr>
							<td>库存量</td>
							<td colspan="3">
								<input title="库存量" id="saleInventoryInfo" type="text" maxlength="8" name="saleInventory" class="form-control inputNotNull" value="${tbpi.saleInventory}"<c:if test="${operation=='info'}">readonly="readonly"</c:if>>
							</td>
						</tr>
						<tr>
							<td>材质</td>
							<td colspan="3">
								<input type="text" maxlength="9" name="material" class="form-control inputNotNull" value='${tbpi.material}'<c:if test="${operation=='info'}">readonly="readonly"</c:if>>
							</td>
						</tr>
						<tr>
							<td>装箱数</td>
							<td colspan="3">
								<input type="text" maxlength="8" name="oneboxCount" class="form-control inputNotNull number"value="${tbpi.oneboxCount}"<c:if test="${operation=='info'}">readonly="readonly"</c:if>>
							</td>
						</tr>
						<c:forEach var="attributeValue" items="${tbpi.attributeValueList}" varStatus="s">
							<tr class="attributeValueDIV">
								<td>${attributeValue.key}</td>
								<td colspan="3">
									<input type="text" name="attributeValues[]" class="form-control" value="${attributeValue.value}"<c:if test="${operation=='info'}">readonly="readonly"</c:if>>
								</td>
							</tr>
						</c:forEach>
					</table>	
					<input type="hidden" value="${tbpi.id}" name="id">
					<input type="hidden" value="${tbpi.warehouseId}" name="warehouseId">
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<c:if test="${operation=='update' || operation=='add'}">
					<button type="button" class="btn btn-primary" onclick="saveInventory('saveInventory')">确定</button>
				</c:if>
				
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
