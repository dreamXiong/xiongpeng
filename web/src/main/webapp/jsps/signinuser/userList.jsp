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
body{padding:0; margin:0;  background:url(${ctx}/images/bg2.jpg) repeat;}
#contant{ position:relative;top: 40px; left: 35px;width: 98%; }
#contant ul{ margin:0; padding:0; list-style:none;}
#contant ul li{ float:left; display:block;  margin:0 58px 58px 0px; }

.shakeImg { -webkit-animation: hand_move infinite 0.15s; }

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
    
    
img{width:50px;
	height:50px;
     position:absolute;
	float:left;
	-webkit-transition:-webkit-transform 1s,opacity 1s,background 1s,width 1s,height 1s,font-size 1s;
     -o-transition-property:width,height,-o-transform,background,font-size,opacity;
	-o-transition-duration:1s,1s,1s,1s,1s,1s;
	-moz-transition-property:width,height,-o-transform,background,font-size,opacity;
	-moz-transition-duration:1s,1s,1s,1s,1s,1s;
	transition-property:width,height,transform,background,font-size,opacity;
	transition-duration:1s,1s,1s,1s,1s,1s;}

.bigImg{-moz-transform: rotate(360deg);
	-webkit-transform: rotate(360deg);
	-o-transform: rotate(360deg);
	transform: rotate(360deg);
    background:#1ec7e6;
	width:450px; left:30%;
	height:450px; position:absolute; z-index:9999;}
	
.winner_title{
	position: relative;
	text-align: center;
	left:0px;
	font-size:60px;
	font-weight:600;
	color:#09f;
	-text-shadow: 0px 0px 2px #999;
}

</style>
</head>

<body>
<div id="contant">
<div class="winner_title">已签到 <font id="signUserNumFont" color="#fefe00">${signUserNum}</font> 人，当前摇一摇 <font  id="shakeUserNumFont" color="#fefe00">${shakeUserNum}</font> 人，可摇奖  <font  id="canErnieCountFont" color="#fefe00">${canErnieCount}</font> 人</div>
<br />
<ul>
<c:forEach var="item" begin="1" end="512" step="1" varStatus="s">
	<li><img id="head${item}"  class="minImg "  title="${item}"  src="${ctx}/images/star.jpg"/></li>
</c:forEach>
</ul></div>
</body>
<script type="text/javascript">
var newIndex=1;
var canRresh=true;
{
	var signInUserStr=${signInUserStr};
	var signInUser=eval(signInUserStr);
	for(var i = 0, l = signInUser.length; i < l; i++) {
		$("#head"+newIndex).attr("src",signInUser[i].headimgurl);
	    $("#head"+newIndex).attr("title",signInUser[i].nickname);
	    $("#head"+newIndex).attr("id",signInUser[i].openId);
	    newIndex++;
	}	
}
function refersh(){
    $.ajax({
        type: "POST",
        url: "signInUser/refreshUser",
        data: "stateNum="+newIndex,
        success: function (data) {
           var userCount= eval(data)[1];
           addUserdata = eval(data)[0];
           $("#signUserNumFont").html(userCount.signUserNum);
           $("#shakeUserNumFont").html(userCount.shakeUserNum);
           $("#canErnieCountFont").html(userCount.canErnieCount);
           if(addUserdata!=null && addUserdata.headimgurl!=null && addUserdata.headimgurl!=""){
               addSignInUser(addUserdata);
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
    }, 2000);
}
refersh();

function refreshShake(){
    $.ajax({
        type: "POST",
        url: "signInUser/refreshShake",
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
