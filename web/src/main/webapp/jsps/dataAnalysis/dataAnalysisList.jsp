<%@page pageEncoding="UTF-8"%>
<div class="report">
			<div class="main-body">
				<div class="main-info">
					<h3><a href="javascript:void(0)" onclick="goodsDetail('/dataAnalysis/inventoryWarningDetail','sale_day asc')">库存预警</a></h3>
					<table class="col-10 hover">
						<thead>
							<tr>
								<th>序号</th>
								<th>货品</th>
								<th>日销售</th>
								<th>剩余量</th>
								<th>预计销售天数</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${fn:length(inventoryWarningList) <= 0}">
								<tr>
									<td colspan="5">没有数据</td>
								</tr>
							</c:if>
							<c:set var="startFlag1" value="0"></c:set>
							<c:forEach var="item" items="${inventoryWarningList}" varStatus="s">
								<c:if test="${item.startCount != 0 && startFlag1==0}">
									<c:set var="startFlag1" value="${item.startCount}"/>  
									<tr>
										<td>...</td>
										<td>...</td>
										<td>...</td>
										<td>...</td>
										<td>...</td>
									</tr>
								</c:if>
								<tr>
								<c:if test="${item.startCount != 0}">
									<c:set var="startFlag1" value="${startFlag1+1 }"></c:set>
									<td>${startFlag1 }</td>
								</c:if>
								<c:if test="${item.startCount == 0}">
									<td>${s.index+1 }</td>
								</c:if>
									<td>${item.inventoryName }</td>
									<td>${item.saleNumDay }</td>
									<td>${item.saleInventory }</td>
									<td>${item.saleNumDay==0?'-':item.saleDay }</td>
								</tr>
							</c:forEach>
						</tbody>	
					</table>
				</div>
			</div>
			<div class="main-body">
				<div class="main-info">
					<h3><a href="javascript:void(0)" onclick="goodsDetail('/dataAnalysis/goodsProfitDetail','profit_money desc')">货品盈利 (${empty goodsProfit.profitMoney ? 0 : goodsProfit.profitMoney})</a></h3>
					<table class="col-10 hover">
						<thead>
							<tr>
								<th>序号</th>
								<th>货品</th>
								<th>盈利</th>
								<th>销售额</th>
								<th>盈利占比</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${fn:length(goodsProfitList) <= 0}">
								<tr>
									<td colspan="5">没有数据</td>
								</tr>
							</c:if>
							<c:set var="startFlag2" value="0"></c:set>
							<c:forEach var="item" items="${goodsProfitList}" varStatus="s">
								<c:if test="${item.startCount != 0 && startFlag2==0}">
									<c:set var="startFlag2" value="${item.startCount}"/>  
									<tr>
										<td>...</td>
										<td>...</td>
										<td>...</td>
										<td>...</td>
										<td>...</td>
									</tr>
								</c:if>
								<tr>
								<c:if test="${item.startCount != 0}">
									<c:set var="startFlag2" value="${startFlag2+1 }"></c:set>
									<td>${startFlag2 }</td>
								</c:if>
								<c:if test="${item.startCount == 0}">
									<td>${s.index+1 }</td>
								</c:if>
									<td>${item.inventoryName }</td>
									<td>${item.profitMoney }</td>
									<td>${item.saleMoney }</td>
									<td>${item.profitProportion }%</td>
								</tr>
							</c:forEach>
						</tbody>	
					</table>
				</div>
			</div>
			<div class="main-body">
				<div class="main-info">
					<h3><a href="javascript:void(0)" onclick="goodsDetail('/dataAnalysis/goodsSaleNumDetail','sale_num desc')">货品销量 (${empty goodsProfit.saleNum ? 0 : goodsProfit.saleNum})</a></h3>
					<table class="col-10 hover">
						<thead>
							<tr>
								<th>序号</th>
								<th>货品</th>
								<th>销量</th>
								<th>销售额</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${fn:length(goodsSaleNumList) <= 0}">
								<tr>
									<td colspan="4">没有数据</td>
								</tr>
							</c:if>
							<c:set var="startFlag3" value="0"></c:set>
							<c:forEach var="item" items="${goodsSaleNumList}" varStatus="s">
								<c:if test="${item.startCount != 0 && startFlag3==0}">
									<c:set var="startFlag3" value="${item.startCount}"/>  
									<tr>
										<td>...</td>
										<td>...</td>
										<td>...</td>
										<td>...</td>
									</tr>
								</c:if>
								<tr>
								<c:if test="${item.startCount != 0}">
									<c:set var="startFlag3" value="${startFlag3+1 }"></c:set>
									<td>${startFlag3 }</td>
								</c:if>
								<c:if test="${item.startCount == 0}">
									<td>${s.index+1 }</td>
								</c:if>
									<td>${item.inventoryName }</td>
									<td>${item.saleNum }</td>
									<td>${item.saleMoney }</td>
								</tr>
							</c:forEach>
						</tbody>	
					</table>
				</div>
			</div>
			<div class="main-body">
				<div class="main-info">
					<h3><a href="javascript:void(0)" onclick="goodsDetail('/dataAnalysis/goodsSaleMoneyDetail','sale_money desc')">货品销售额 (${empty goodsProfit.saleMoney ? 0 : goodsProfit.saleMoney})</a></h3>
					<table class="col-10 hover">
						<thead>
							<tr>
								<th>序号</th>
								<th>货品</th>
								<th>销售额</th>
								<th>销量</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${fn:length(goodsSaleMoneyList) <= 0}">
								<tr>
									<td colspan="4">没有数据</td>
								</tr>
							</c:if>
							<c:set var="startFlag4" value="0"></c:set>
							<c:forEach var="item" items="${goodsSaleMoneyList}" varStatus="s">
								<c:if test="${item.startCount != 0 && startFlag4==0}">
									<c:set var="startFlag4" value="${item.startCount}"/>  
									<tr>
										<td>...</td>
										<td>...</td>
										<td>...</td>
										<td>...</td>
									</tr>
								</c:if>
								<tr>
								<c:if test="${item.startCount != 0}">
									<c:set var="startFlag4" value="${startFlag4+1 }"></c:set>
									<td>${startFlag4 }</td>
								</c:if>
								<c:if test="${item.startCount == 0}">
									<td>${s.index+1 }</td>
								</c:if>
									<td>${item.inventoryName }</td>
									<td>${item.saleMoney }</td>
									<td>${item.saleNum }</td>
								</tr>
							</c:forEach>
						</tbody>	
					</table>
				</div>
			</div>
			<div class="main-body">
				<div class="main-info">
					<h3><a href="javascript:void(0)" onclick="profitsChangeDetail()">利润变化</a></h3>
					<table class="col-10 hover">
						<thead>
							<tr>
								<th>年月份</th>
								<th>利润</th>
								<th>销量</th>
								<th>销售额</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${fn:length(profitsChangeList) <= 0}">
								<tr>
									<td colspan="4">没有数据</td>
								</tr>
							</c:if>
							<c:forEach var="item" items="${profitsChangeList}" varStatus="s">
								<tr>
									<td>${item.yearName }</td>
									<td>${item.profitMoney }</td>
									<td>${item.saleNum }</td>
									<td>${item.saleMoney }</td>
								</tr>
							</c:forEach>
						</tbody>	
					</table>
				</div>
			</div>
			<div class="main-body">
				<div class="main-info">
					<h3><a href="javascript:void(0)" onclick="goodsDetail('/dataAnalysis/goodsProfitsRateDetail','profits_rate desc')">货品利润率 (${empty goodsProfitsRateSum.profitsRate ? 0 : goodsProfitsRateSum.profitsRate}%)</a></h3>
					<table class="col-10 hover">
						<thead>
							<tr>
								<th>序号</th>
								<th>货品</th>
								<th>盈利</th>
								<th>成本</th>
								<th>利润率</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${fn:length(goodsProfitsRateList) <= 0}">
								<tr>
									<td colspan="5">没有数据</td>
								</tr>
							</c:if>
							<c:set var="startFlag6" value="0"></c:set>
							<c:forEach var="item" items="${goodsProfitsRateList}" varStatus="s">
								<c:if test="${item.startCount != 0 && startFlag6==0}">
									<c:set var="startFlag6" value="${item.startCount}"/>  
									<tr>
										<td>...</td>
										<td>...</td>
										<td>...</td>
										<td>...</td>
										<td>...</td>
									</tr>
								</c:if>
								<tr>
								<c:if test="${item.startCount != 0}">
									<c:set var="startFlag6" value="${startFlag6+1 }"></c:set>
									<td>${startFlag6 }</td>
								</c:if>
								<c:if test="${item.startCount == 0}">
									<td>${s.index+1 }</td>
								</c:if>
									<td>${item.inventoryName }</td>
									<td>${item.profitMoney }</td>
									<td>${item.sumInstockPrice }</td>
									<td>${item.profitsRate }%</td>
								</tr>
							</c:forEach>
						</tbody>	
					</table>
				</div>
			</div>
			<div class="main-body">
				<div class="main-info">
					<h3><a href="javascript:void(0)" onclick="customerInterestsDetail('/dataAnalysis/customerInterestsDetail','profit_money desc')">客户盈利 (${empty customerInterests.profitMoney ? 0 : customerInterests.profitMoney})</a></h3>
					<table class="col-10 hover">
						<thead>
							<tr>
								<th>序号</th>
								<th>客户</th>
								<th>盈利</th>
								<th>销售额</th>
								<th>推荐人</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${fn:length(customerInterestsList) <= 0}">
								<tr>
									<td colspan="5">没有数据</td>
								</tr>
							</c:if>
							<c:set var="startFlag7" value="0"></c:set>
							<c:forEach var="item" items="${customerInterestsList}" varStatus="s">
								<c:if test="${item.startCount != 0 && startFlag7==0}">
									<c:set var="startFlag7" value="${item.startCount}"/>  
									<tr>
										<td>...</td>
										<td>...</td>
										<td>...</td>
										<td>...</td>
										<td>...</td>
									</tr>
								</c:if>
								<tr>
								<c:if test="${item.startCount != 0}">
									<c:set var="startFlag7" value="${startFlag7+1 }"></c:set>
									<td>${startFlag7 }</td>
								</c:if>
								<c:if test="${item.startCount == 0}">
									<td>${s.index+1 }</td>
								</c:if>
									<td>${item.userName }</td>
									<td>${item.profitMoney }</td>
									<td>${item.salePrice }</td>
									<td>${item.recommendName }</td>
								</tr>
							</c:forEach>
						</tbody>	
					</table>
				</div>
			</div>
			<div class="main-body">
				<div class="main-info">
					<h3><a href="javascript:void(0)" onclick="customerInterestsDetail('/dataAnalysis/effectivecustomerDetail','total_profit desc')">推荐有效客户</a></h3>
					<table class="col-10 hover">
						<thead>
							<tr>
								<th>序号</th>
								<th>客户</th>
								<th>推荐人数</th>
								<th>总盈利</th>
								<th>推荐人</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${fn:length(effectivecustomerList) <= 0}">
								<tr>
									<td colspan="5">没有数据</td>
								</tr>
							</c:if>
							<c:set var="startFlag8" value="0"></c:set>
							<c:forEach var="item" items="${effectivecustomerList}" varStatus="s">
								<c:if test="${item.startCount != 0 && startFlag8==0}">
									<c:set var="startFlag8" value="${item.startCount}"/>  
									<tr>
										<td>...</td>
										<td>...</td>
										<td>...</td>
										<td>...</td>
										<td>...</td>
									</tr>
								</c:if>
								<tr>
								<c:if test="${item.startCount != 0}">
									<c:set var="startFlag8" value="${startFlag8+1 }"></c:set>
									<td>${startFlag8 }</td>
								</c:if>
								<c:if test="${item.startCount == 0}">
									<td>${s.index+1 }</td>
								</c:if>
									<td>${item.userName }</td>
									<td>${item.recommendCount }</td>
									<td>${item.totalProfit }</td>
									<td>${item.recommendName }</td>
								</tr>
							</c:forEach>
						</tbody>	
					</table>
				</div>
			</div>
		</div>