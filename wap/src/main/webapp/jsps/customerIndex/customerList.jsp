<%@page pageEncoding="UTF-8"%>
<ul class="clearfix">
<c:forEach var="item" items="${shopInfoList}" varStatus="s">
	<li>
		<a>
			<img onclick="goServiceDetail(${item.id});" class="lazy" src="${ctx}/images/no-load.gif" data-original="${ctx}/customerIndex/generateImage?id=${item.id}&imgName=${item.cimgOne}"/> 
		</a>	
		<div class="details">
			<h3 class="name box clearfix">
				<span class="pull-left box1">${item.name}</span>
				<span class="pull-right box1 text-right"><i style="color:#f2000e;">${item.price}</i>元起</span>
			</h3>
			<p class="distance">${item.describes}&nbsp;</p>
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
	
	function goServiceDetail(id){
		$("#serviceId").val(id);
		$("#goServiceDetail").submit();
	}
	
</script>