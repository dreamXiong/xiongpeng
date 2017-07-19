<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="web">
	<tiles:put name="title" value="帮助" />
	<tiles:put name="body" type="String">
	<style type="text/css">
	body{
		margin:0;
		padding:0;
	}
	.stretch{
		width:100%;
	}
	</style>
	<div>
		<img src="./images/lc_01.jpg" class="stretch" />
		<img src="./images/lc_02.jpg" class="stretch" />
	</div>
	
	</tiles:put>
</tiles:insert>