<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${ctx}/js/jquery/jquery1.9.2/jquery-1.8.3.js"></script>	
</head>
<body style="text-align: center;">
	<H2>
	<span id="startSign" style="cursor: pointer; display: ${isOpenSign?'none':'black'} " onclick="startSign();">开启签到 | </span>
	<span id="closeSign" style="cursor: pointer; display: ${isOpenSign?'black':'none'}" onclick="closeSign();">关闭签到 | </span>
	<span id="openInteract" style="cursor: pointer; display: ${isOpenInteract?'none':'black'} " onclick="openInteract();">开启弹幕 | </span>
	<span id="closeInteract" style="cursor: pointer; display: ${isOpenInteract?'black':'none'}" onclick="closeInteract();">关闭弹幕 | </span>
	<span id="startPraise" style="cursor: pointer; display: ${isStartPraise?'none':'black'}" onclick="startPraise();">投票开始 | </span>
	<span id="endPraise" style="cursor: pointer; display: ${isStartPraise?'black':'none'}" onclick="endPraise();">投票结束  | </span>
	<span id="hidePraise" style="cursor: pointer; display: ${isShowPraiseNum?'black':'none'}" onclick="hidePraise();">投票不显示 | </span>
	<span id="showPraise"  style="cursor: pointer; display: ${isShowPraiseNum?'none':'black'}" onclick="showPraise();">投票显示 | </span>
	
	
	<span id="startErnie" style="cursor: pointer; display: ${isOpenErnie?'none':'black'} " onclick="startErnie();">开启摇奖 | </span>
	<span id="closeErnie" style="cursor: pointer; display: ${isOpenErnie?'black':'none'}" onclick="closeErnie();">关闭摇奖 | </span>
	
	
	<span style="cursor: pointer;" onclick="refreshCeche();">刷新缓存</span>
	</H2>
	<H2>
	节目单起始序号：<input type="text" name="index" value="${index}" id="index"> <input onclick="changeIndex()" type="button" name="修改" value="修改">
	节目单显示数量：<input type="text" name="size" value="${size}" id="size"> <input onclick="changeSize()" type="button" name="修改" value="修改">
	</H2>
	<H3><div id="showMsg"></div></H3>
	<script type="text/javascript">
	
	function changeIndex(){
		 $.ajax({
		        type: "POST",
		        url: "${ctx}/manager/changeIndex",
		        data: "index="+$("#index").val(),
		        success: function (data) {
		        	$("#showMsg").html($("#showMsg").html()+"<BR/>"+data.message);
		        }
		    });
	}
	
	function changeSize(){
		 $.ajax({
		        type: "POST",
		        url: "${ctx}/manager/changeSize",
		        data: "size="+$("#size").val(),
		        success: function (data) {
		        	$("#showMsg").html($("#showMsg").html()+"<BR/>"+data.message);
		        }
		    });
	}
	
	function startSign(){
		  $.ajax({
		        type: "POST",
		        url: "${ctx}/manager/openSign",
		        data: null,
		        success: function (data) {
		        	$('#startSign').hide();
		        	$('#closeSign').show();
		        	$("#showMsg").html($("#showMsg").html()+"<BR/>"+data.message);
		        }
		    });
	}
	
	function closeSign(){
		  $.ajax({
		        type: "POST",
		        url: "${ctx}/manager/closeSign",
		        data: null,
		        success: function (data) {
		        	$('#startSign').show();
		        	$('#closeSign').hide();
		        	$("#showMsg").html($("#showMsg").html()+"<BR/>"+data.message);
		        }
		    });
	}
	
	function openInteract(){
		  $.ajax({
		        type: "POST",
		        url: "${ctx}/manager/openInteract",
		        data: null,
		        success: function (data) {
		        	$('#openInteract').hide();
		        	$('#closeInteract').show();
		        	$("#showMsg").html($("#showMsg").html()+"<BR/>"+data.message);
		        }
		    });
	}
	
	function closeInteract(){
		  $.ajax({
		        type: "POST",
		        url: "${ctx}/manager/closeInteract",
		        data: null,
		        success: function (data) {
		        	$('#openInteract').show();
		        	$('#closeInteract').hide();
		        	$("#showMsg").html($("#showMsg").html()+"<BR/>"+data.message);
		        }
		    });
	}
	
	function startPraise(){
		  $.ajax({
		        type: "POST",
		        url: "${ctx}/manager/startPraise",
		        data: null,
		        success: function (data) {
		        	$('#startPraise').hide();
		        	$('#endPraise').show();
		        	$("#showMsg").html($("#showMsg").html()+"<BR/>"+data.message);
		        }
		    });
	}
	
	function endPraise(){
		  $.ajax({
		        type: "POST",
		        url: "${ctx}/manager/endPraise",
		        data: null,
		        success: function (data) {
		        	$('#startPraise').show();
		        	$('#endPraise').hide();
		        	$("#showMsg").html($("#showMsg").html()+"<BR/>"+data.message);
		        }
		    });
	}
	
	function hidePraise(){
		  $.ajax({
		        type: "POST",
		        url: "${ctx}/manager/hidePraise",
		        data: null,
		        success: function (data) {
		        	$('#showPraise').show();
		        	$('#hidePraise').hide();
		        	$("#showMsg").html($("#showMsg").html()+"<BR/>"+data.message);
		        }
		    });
	}
	
	function showPraise(){
		  $.ajax({
		        type: "POST",
		        url: "${ctx}/manager/showPraise",
		        data: null,
		        success: function (data) {
		        	$('#showPraise').hide();
		        	$('#hidePraise').show();
		        	$("#showMsg").html($("#showMsg").html()+"<BR/>"+data.message);
		        }
		    });
	}
	
	function refreshCeche(){
		  $.ajax({
		        type: "POST",
		        url: "${ctx}/manager/refreshCeche",
		        data: null,
		        success: function (data) {
		        	$("#showMsg").html($("#showMsg").html()+"<BR/>"+data.message);
		        }
		    });
	}
	
	
	
	function startErnie(){
		  $.ajax({
		        type: "POST",
		        url: "${ctx}/manager/openErnie",
		        data: null,
		        success: function (data) {
		        	$('#startErnie').hide();
		        	$('#closeErnie').show();
		        	$("#showMsg").html($("#showMsg").html()+"<BR/>"+data.message);
		        }
		    });
	}
	
	function closeErnie(){
		  $.ajax({
		        type: "POST",
		        url: "${ctx}/manager/closeErnie",
		        data: null,
		        success: function (data) {
		        	$('#startErnie').show();
		        	$('#closeErnie').hide();
		        	$("#showMsg").html($("#showMsg").html()+"<BR/>"+data.message);
		        }
		    });
	}
	</script>
</body>
</html>
