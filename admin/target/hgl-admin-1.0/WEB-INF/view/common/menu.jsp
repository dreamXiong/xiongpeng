<%@page pageEncoding="UTF-8"%>
<script src="${ctx}/js/hgl/nav.js"></script>
<link rel="stylesheet" href="${ctx}/css/nav.css">
 <aside class="main-sidebar">
    <section class="sidebar" style="height: auto;">
	<ul class="sidebar-menu">
		 <li class="header">菜单管理</li>
		 
		<c:forEach items="${parentList}" var="item">
            	<li class="treeview">
            		<a href="javascript:;">
            			<i class="fa fa-tags"></i> <span>${ item.name }</span> <i class="fa fa-angle-left pull-right"></i>
          			</a>
                 <ul class="treeview-menu">
                     <c:forEach items="${item.childs}" var="itemChild">
                         <li><a href="${ctx }${itemChild.actionUrl }" ><i class="fa fa-circle invisible-o invisible"></i>  ${ itemChild.name }</a></li>
                     </c:forEach>
                 </ul>
             </li>
         </c:forEach>
	</ul>
	    </section>
  </aside>    

<script>
	$(document).ready(function(){
		$(".treeview-menu a").each(function(){ 
			//if($($(this))[0].href==String(window.location)) 
				var hrefStr =$(this)[0].href;
				var hrefArr =hrefStr.split("?");
				
				var winStr =String(window.location);
				var winArr =winStr.split("?");
				if(hrefArr[0]==winArr[0]){
					$(this).parents('.treeview').addClass('active'); 
					$(this).parent('li').addClass('active'); 
					return false;
				}
		});
	});

	$(".treeview-menu a").click(function(event) {
		$(this).parent().addClass('active').siblings("span").removeClass('active');
	});
</script> 
