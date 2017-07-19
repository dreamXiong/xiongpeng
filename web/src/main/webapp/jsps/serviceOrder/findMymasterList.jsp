<div class="area me">
		<div class="main-right pull-right">
			<c:if test="${fn:length(tbWapOrderServiceList)==0}">
				<div class="no-data"></div>	
			</c:if>
			<c:if test="${fn:length(tbWapOrderServiceList)>0}">
			<button type="button" class="btn-bg" 
					onclick="addMaster()" 
				style="margin-top: -1px; margin-left: 5px;">
				<span style="margin-right: 0;">添加</span>
			</button>
				<div class="stock">
					<form action="${ctx}/import/productImport"
						enctype="multipart/form-data" method="post" class="form-inline"
						id="key_${modalName}_qryFrm">
						<div class="stock_nav">
							<table class="hover col-10" id="list" >
								<thead class="bg-gray">
									<tr>   
										<th>选择</th>
										<th>师傅姓名</th>
										<th>联系电话</th>
										<th>身份证号码</th>
										<th>师傅住址</th>
										<th>评价星级</th>
									</tr>
								</thead>
								<tbody>
								<input type="hidden" id="orderMymasterId" value="${id}">
									<c:forEach var="item" items="${tbWapOrderServiceList}" varStatus="s">
										<tr>
											<td>
												<input style="height:auto;width:auto;" type="radio" value="${item.id}"  name="addMaster">
											</td>
											<td>${item.name }</td>
											<td>${item.mobile }</td>
											<td>${item.idCard }</td>
											<td>${item.address }</td>
											<td>${item.evaluateNumAvg }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
								
						</div>
					</form>
				</div>
			</c:if>
		</div>
	</div>