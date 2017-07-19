<%@page pageEncoding="UTF-8"%>
<ul class="clearfix">
<c:forEach var="item" items="${shopInfoList}" varStatus="s">
	<li><!-- +"&distance="+${item.balance} -->
		<a  onclick="goShop(${item.id},${item.balance});">
			<img class="lazy" data-original="${ctx}/login/generateImage?id=${item.id}&imgName=${item.shopImage1}" src="${ctx}/images/no-load.gif"/> 
		</a>	
		<div class="details">
			<p class="name">${item.companyName}&nbsp;</p>
			<p class="distance">
				<span class="icon-map-marker" ></span>
				<c:if test="${item.balance == 0.00}">         
					定位失败</span>
				</c:if>
				<c:if test="${item.balance != 0.00}">
					距离<span id="juli_${s.index}">${item.balance}</span>km
				</c:if>
			</p>
		</div>
	</li>
</c:forEach>
</ul>
<script>   
function goShop(id,distance){
	window.location.href ="shop/index?id="+id+"&distance="+distance;
 }
 $(function(){
		$('.nav-list li img').height($('.nav-list li img').width());
	});  
	$(function(){
		$("img.lazy").lazyload({
			threshold : 20,                               //距离相片多少距离加载
			effect : "fadeIn",                            //加载方式
		});
	});
	function navListLiImg(){
		$('.nav-list li img').height($('.nav-list li img').width());
	}
</script>