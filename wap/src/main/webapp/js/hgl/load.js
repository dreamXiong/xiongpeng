var oBody =document.getElementsByTagName('body')[0];
var winH = window.screen.availHeight;
var winW = window.screen.availWidth;
var oDiv = document.createElement('div');
oBody.appendChild(oDiv);
oDiv.id = 'load';
oDiv.style.width = winW + 'px';
oDiv.style.height = winH + 'px';
oDiv.style.background = 'url(../images/open.gif) no-repeat center center #fff';
oDiv.style.position = 'fixed';
oDiv.style.left = '0';
oDiv.style.top = '0';

window.onload=function(){
	var oDiv = document.getElementById('load');
	oDiv.style.display = 'none';
}