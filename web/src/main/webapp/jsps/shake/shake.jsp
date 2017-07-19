<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>联创集团2015~2016年会摇奖</title>
<link rel="stylesheet" href="${ctx}/css/shake.css" />
<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.7.1.min.js"></script>
<style type="text/css">
.demo{width:320px; margin:40px auto 0 auto; }
.hand { width: 190px; height: 300px; margin:0 auto; background: url(${ctx}/images/hand.png) no-repeat; }
.hand-animate { -webkit-animation: hand_move infinite 0.1s; }
.result { background: #393B3C; border: #2C2C2C 1px solid; box-shadow: inset #4D4F50 0 0 0 1px; border-radius: 10px; color: #fff; padding: 10px; width: 300px; opacity: 0;
        -webkit-transition: all 1s;
           -moz-transition: all 1s;
            -ms-transition: all 1s;
             -o-transition: all 1s;
                transition: all 1s; }
.result-show { opacity: 1; margin-top: 50px; }

 @-webkit-keyframes hand_move {
        0% {
            -webkit-transform: rotate(0);
               -moz-transform: rotate(0);
                -ms-transform: rotate(0);
                 -o-transform: rotate(0);
                    transform: rotate(0); }
        50% {
            -webkit-transform: rotate(15deg);
               -moz-transform: rotate(15deg);
                -ms-transform: rotate(15deg);
                 -o-transform: rotate(15deg);
                    transform: rotate(15deg); }
        100% {
            -webkit-transform: rotate(0);
               -moz-transform: rotate(0);
                -ms-transform: rotate(0);
                 -o-transform: rotate(0);
                    transform: rotate(0); }
    }
</style>
</head>

<body>

<div id="main">
   <h2 class="top_title">联创集团2015~2016年会摇奖</h2>
  <input type="hidden" id="openId" value="${openId}">
   <div class="demo">
		<div id="hand" class="hand"></div>
		<div id="result"></div>
   </div>
  
  <br/>
</div>
  <audio style="display:hide" id="musicBox" preload="auto"  src="${ctx}/music/yaoyiyao.wav"  > </audio> 
<script src="${ctx}/js/wap/shake.js"></script>
<script src="${ctx}/js/wap/shakebusiness.js"></script>


<script>
window.onload = function() {
    var myShakeEvent = new Shake({
        threshold: 15
    });
    myShakeEvent.start();

    window.addEventListener('shake', shakeEventDidOccur, false);
    function shakeEventDidOccur () {
        //media.setAttribute("src","/music/yaoyiyao.wav"); 
		var media=document.getElementById("musicBox"); 
        media.play();  
		var result = document.getElementById("result");
		var hand = document.getElementById("hand");
		result.className = "result";
		hand.className = "hand hand-animate";        
        var openId=document.getElementById("openId").value;
        $.ajax({
            type: "POST",
            url: "yaoyiyao",
            data: "openId="+openId,
            success: function (data) {
            	result.innerHTML = data;
                setTimeout(function(){
                    result.className = "result result-show";
            		hand.className = "hand";
                }, 1000);
            }
        });
        
		
    }
};
</script>
</body>
</html>