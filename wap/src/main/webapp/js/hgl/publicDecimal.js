//js保留2位小数
function toDecimal2(x) {   
	var f = parseFloat(x); 
	if (isNaN(f)) {
		return false;       
	}       
	var f = Math.round(x*100)/100;    
	var s = f.toString();      
	var rs = s.indexOf('.'); 
	if (rs < 0) {      
		rs = s.length;      
		s += '.';    
	}       
	while (s.length <= rs + 2) {  
		s += '0';      
	}       
	return s;     
}

//分割价格
function splitPrice(price){ 
	var resultArray = [];
	if(price.indexOf(".") != -1){
		resultArray = price.split(".");
		var minPrice = resultArray[1];
		if(minPrice.length <=1){
			resultArray[1] = minPrice+'0';
		}
	}else{
		cartIdsList.push(price);
		cartIdsList.push('00');
	}
	return resultArray;
}