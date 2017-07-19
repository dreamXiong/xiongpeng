<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.7.1.min.js"></script>
<title>已签到用户</title>
<style type="text/css">
body{padding:0; margin:0;  background:url(${ctx}/images/map.jpg) repeat;}
#contant{ position:relative;height: 100%;top: 5px; left: 5px;}
#contant ul{ padding:0; list-style:none;}
#contant ul li{ float:left; display:block; margin:0 32px 32px 0px; }

.shakeImg { -webkit-animation: hand_move infinite 0.1s; }

 @-webkit-keyframes hand_move {
        0% {
            -webkit-transform: rotate(0);
               -moz-transform: rotate(0);
                -ms-transform: rotate(0);
                 -o-transform: rotate(0);
                    transform: rotate(0); }
        25% {
            -webkit-transform: rotate(15deg);
               -moz-transform: rotate(15deg);
                -ms-transform: rotate(15deg);
                 -o-transform: rotate(15deg);
                    transform: rotate(15deg); }
        50% {
            -webkit-transform: rotate(0);
               -moz-transform: rotate(0);
                -ms-transform: rotate(0);
                 -o-transform: rotate(0);
                    transform: rotate(0); }
        75% {
            -webkit-transform: rotate(-15deg);
               -moz-transform: rotate(-15deg);
                -ms-transform: rotate(-15deg);
                 -o-transform: rotate(-15deg);
                    transform: rotate(-15deg); }
        100% {
            -webkit-transform: rotate(0);
               -moz-transform: rotate(0);
                -ms-transform: rotate(0);
                 -o-transform: rotate(0);
                    transform: rotate(0); }
    }
    
    
.minImg{width:28px;
	height:28px;
     position:absolute;
	float:left;
	-webkit-transition:-webkit-transform 1s,opacity 1s,background 1s,width 1s,height 1s,font-size 1s;
     -o-transition-property:width,height,-o-transform,background,font-size,opacity;
	-o-transition-duration:1s,1s,1s,1s,1s,1s;
	-moz-transition-property:width,height,-o-transform,background,font-size,opacity;
	-moz-transition-duration:1s,1s,1s,1s,1s,1s;
	transition-property:width,height,transform,background,font-size,opacity;
	transition-duration:1s,1s,1s,1s,1s,1s; }

.bigImg{-moz-transform: rotate(360deg);
	-webkit-transform: rotate(360deg);
	-o-transform: rotate(360deg);
	transform: rotate(360deg);
    background:#1ec7e6;
	width:150px; left:30%;
	height:150px; position:absolute; z-index:9999;}
</style>
</head>

<body>
<div id="contant">
<ul>
<c:forEach var="item" begin="1" end="465" step="1" varStatus="s">
	<li><img id="head${item}"  class="minImg " title="${item}" src="${ctx}/images/star.jpg"/></li>
</c:forEach>
</ul></div>
</body>
<script type="text/javascript">
var newIndex=1;
var canRresh=true;
function refersh(){
    $.ajax({
        type: "POST",
        url: "../signInUser/refreshUser",
        data: "stateNum="+newIndex,
        success: function (data) {
           data = eval(data)[0];
           if(data!=null && data.headimgurl!=null && data.headimgurl!=""){
               addSignInUser(data);
           }
           window.setTimeout(refersh,3500);
        }
    });
}

function addSignInUser(data){
    $("#head"+newIndex).addClass("bigImg");
    $("#head"+newIndex).attr("src",data.headimgurl);
    setTimeout(function(){
        $("#head"+newIndex).removeClass("bigImg");
        $("#head"+newIndex).attr("title",data.nickname);
        $("#head"+newIndex).attr("id",data.openId);
        newIndex++;
    }, 3000);
}
refersh();

function refreshShake(){
    $.ajax({
        type: "POST",
        url: "../signInUser/refreshNewShake",
        data: null,
        success: function (data) {
           var data = eval(data);
           $(".minImg").removeClass("shakeImg");
           $.each(data, function(i, n){
               $("#"+n).addClass("shakeImg");
           });
        }
    });
}
window.setInterval(refreshShake,2000);

</script>
</html>
