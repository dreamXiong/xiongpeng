<%@page pageEncoding="UTF-8"%>
<div>
	<div class="pull-left">
	<div class="line-height bg-gray seek-nav clear">
		<div class="pull-left">
			<ul>
				<li class="current <c:if test="${orderby==null}">active</c:if>" id="autoOrderBy"><a
					href="javascript:">综合</a></li>
				<li class="current <c:if test="${orderby=='saleNum'}">active</c:if>" for="saleNum" ><a href="javascript:">销量<i icon="<c:if test="${orderby=='saleNum'}"><c:if test="${ordersort=='desc'}">xe61a</c:if><c:if test="${ordersort=='asc'}">xe61b</c:if></c:if>"
						class='iconfont'><c:if test="${orderby=='saleNum'}"><c:if test="${ordersort=='desc'}">&#xe61a;</c:if><c:if test="${ordersort=='asc'}">&#xe61b;</c:if></c:if></i></a></li>
				<li class="current <c:if test="${orderby=='price'}">active</c:if>" for="price"><a href="javascript:">价格<i icon="<c:if test="${orderby=='price'}"><c:if test="${ordersort=='desc'}">xe61a</c:if><c:if test="${ordersort=='asc'}">xe61b</c:if></c:if>"
						class='iconfont'><c:if test="${orderby=='price'}"><c:if test="${ordersort=='desc'}">&#xe61a;</c:if><c:if test="${ordersort=='asc'}">&#xe61b;</c:if></c:if></i></a></li>
				<!-- <li class="current"><a href="javascript:">评论</a></li> -->
				<li class="input-btn margin-left"><input type="text"
					placeholder="￥" id="stringToFloat1" value="${minprice}"> - <input type="text"
					placeholder="￥" id="stringToFloat2" value="${maxprice}">
					<button type="button" id="moneyCommit">确 定</button></li>
			</ul>
		</div>
		<div class="pull-right seek-icon">
			<span class="icon-th margin-right-10 cursor"
				style="<c:if test="${cursor==0}">color: red;</c:if> line-height: 36px;"></span><span
				class="icon-list margin-right cursor" 
                style="<c:if test="${cursor==1}">color: red;</c:if> line-height: 36px;"></span>
			<div class="pull-right margin-right margin-left">
				<liguo:pagination pageAction="../pick/serachProduct" location="top" />
			</div>
		</div>
	</div>
	<div class="padding margin-bottom-10 border-gray show">
		<ul class="clear vertical style-list ul collect" style="display: <c:if test="${cursor==1}">none</c:if><c:if test="${cursor==0}">block</c:if>;margin-bottom:10px;">
			<c:forEach var="tbProduct" items="${tbProductList}" varStatus="s">
				<li class="flicker"><a href="#" onclick="productDetail('${tbProduct.id}','${st}','${param.id}')"><img
						src="${ctx}/generateProductImage?id=${tbProduct.id}&imgName=${tbProduct.pimgOne}"   
						alt=""></a>
					<p>
						<strong><span class="text-red">￥ ${tbProduct.price}</span><small>/${tbProduct.meterageunit}</small></strong>
					</p>
					<p class="describe text-ellipsis">
						<a href="#" onclick="productDetail('${tbProduct.id}','${st}','${param.id}')">${tbProduct.brandName}/${tbProduct.name}</a>
					</p>
					<div class="clear">
						<p class="pull-left text-gray">
							销量<span>${tbProduct.saleNum}</span>
						</p>
						<p class="pull-right text-gray" id="productId${tbProduct.id}">
							<c:choose>
								<c:when test="${tbProduct.saveId!=null && tbProduct.saveId!=0}">
									<span class="iconfont" title="已收藏" onclick="OperateSaveInfo(${tbProduct.id},0)">&#xe609;</span>
								</c:when>
								<c:otherwise>
									<span class="iconfont" title="收藏" onclick="OperateSaveInfo(${tbProduct.id},1)">&#xe608;</span>
								</c:otherwise>
							</c:choose>
						</p>
					</div></li>
			</c:forEach>
		</ul>
		<table class="style-list col-10 table-border none" style="display: <c:if test="${cursor==0}">none</c:if><c:if test="${cursor==1}">block</c:if>;margin-bottom:10px;">
			<tr class="line-height text-center ">
				<th class="col-2">图例</th>
				<th class="col-2">价格/规格数/销量</th>
				<th class="col-2">品牌/名称</th>
				<th class="col-3">产品说明</th>
				<th class="col-1">关注</th>
			</tr>
			<c:forEach var="tbProduct" items="${tbProductList}" varStatus="s">
				<tr>
					<td class="padding"><a href="#" onclick="productDetail('${tbProduct.id}','${st}','${param.id}')"><img
							src="${ctx}/generateProductImage?id=${tbProduct.id}&imgName=${tbProduct.pimgOne}"
							alt=""></a></td>
					<td class="padding">
						<p>
							<span class="text-red"> <small>￥</small> <big>${tbProduct.price}</big>
							</span> <b>起</b>
						</p>
						<p>
							含 <span class="text-red">${tbProduct.specNum}</span> 个规格
						</p>
						<p>
							销量:${tbProduct.saleNum}
						</p>
					</td>
					<td class="padding">${tbProduct.brandName}/${tbProduct.name}</td>
					<td class="padding">
					    <p>${tbProduct.describes}</p>
						<!-- <p>加长：加长25%(与传统扳手相比)</p>
						<p>强劲：扭力输出提高25%(与传统扳手相比)。</p>
						<p>高效：操作时手与扳手的接触面积增加5倍(与传统扳手相比)</p> -->
					</td>
					<td class="text-center collect" id="productSaveId${tbProduct.id}">
					<c:choose>
						<c:when test="${tbProduct.saveId!=null && tbProduct.saveId!=0}">
							<span class="iconfont" title="已收藏" onclick="addProductToSaveInfo(${tbProduct.id},1)">&#xe609;</span> 
						</c:when>
							<c:otherwise>
								<span class="iconfont" title="收藏" onclick="addProductToSaveInfo(${tbProduct.id},1)">&#xe608;</span> 
						</c:otherwise>
					</c:choose>
					</td>
				</tr>
			</c:forEach>
		</table>
		<liguo:pagination pageAction="serachProduct" />
	</div>
	</div>
	<div class="choose-right pull-right clear margin-bottom-10">
					<div class="line-height bg-gray text-center font-bold">
						推广产品
					</div>
					<ul>
						<li class="padding">
							<a href="javascript:"><img src="${ctx}/images/diandong-2.png" alt=""></a>
							<p><span class="text-red">￥5.8</span> <b>起 </b>含 <span class="text-red">5</span>个规格</p>
							<p class="text-ellipsis"><a href="javascript:">扳手/两用扳手/梅花呆扳手7mm，双头</a></p>
						</li>
						<li class="padding">
							<a href="javascript:"><img src="${ctx}/images/diandong-2.png" alt=""></a>
							<p><span class="text-red">￥5.8</span> <b>起 </b>含 <span class="text-red">5</span>个规格</p>
							<p class="text-ellipsis"><a href="javascript:">扳手/两用扳手/梅花呆扳手7mm，双头</a></p>
						</li>
						<li class="padding">
							<a href="javascript:"><img src="${ctx}/images/diandong-2.png" alt=""></a>
							<p><span class="text-red">￥5.8</span> <b>起 </b>含 <span class="text-red">5</span>个规格</p>
							<p class="text-ellipsis"><a href="javascript:">扳手/两用扳手/梅花呆扳手7mm，双头</a></p>
						</li>
						<li class="padding">
							<a href="javascript:"><img src="${ctx}/images/diandong-2.png" alt=""></a>
							<p><span class="text-red">￥5.8</span> <b>起 </b>含 <span class="text-red">5</span>个规格</p>
							<p class="text-ellipsis"><a href="javascript:">扳手/两用扳手/梅花呆扳手7mm，双头</a></p>
						</li>
					</ul>
				</div>
</div>
<form id="submitProductDetail" method="post" action="${ctx}/pick/pickDetail">
	<input type="hidden" value="" id="id" name="id"/>
	<input type="hidden" value="" id="st" name="st"/>
	<input type="hidden" value="" id="mid" name="mid"/>
</form>
<script type="text/javascript">
	function productDetail(id,st,mid){
		$("#submitProductDetail #id").val(id);
		$("#submitProductDetail #st").val(st);
		$("#submitProductDetail #mid").val(mid);
		$("#submitProductDetail").submit();
	}
</script>
