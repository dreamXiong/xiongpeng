/*镇*/
function countryChange(){
	var countryCode=$("#countryCode").val();
	
	  	$.ajax({
		  		url: ctx+'/address/streetInfos?cid='+countryCode,
		  		success : function(data) {
					var selectHtml = "<option value='0'>请选择&nbsp;&nbsp;</option>";
					
					var street=$("#streetCode").val();
					for ( var i = 0; i < data.streetInfos.length; i++) {
						var code = data.streetInfos[i].id;
						var provinceName = data.streetInfos[i].name;
						if(street !=null && street==code){
							selectHtml += "<option selected='selected' value='"+code+"'>"+provinceName+"</option>";
						}else{
						selectHtml += "<option value='" + code + "'>" + provinceName
								+ "&nbsp;&nbsp;</option>";
						}
					}
					
					$("#street").html(selectHtml);
				}
		  	});
 }

 function cityChange(){
	 var cityCode = $("#cityCode").val();
 	  	$.ajax({
		  		url: ctx+'/address/countryInfos?cid='+cityCode,
		  		success : function(data) {
					var selectHtml = "<option value='0'>请选择&nbsp;&nbsp;</option>";
					var countryCode=$("#countryCode").val();
					for ( var i = 0; i < data.countryInfos.length; i++) {
						var code = data.countryInfos[i].id;
						var name = data.countryInfos[i].name;
						if(countryCode != null && countryCode == code){
							selectHtml += "<option selected='selected' value='"+code+"'>"+name+"</option>";
						}else{
						selectHtml += "<option value='" + code + "'>" + name
								+ "&nbsp;&nbsp;</option>";
						}
					}
					$("#country").html(selectHtml);
					countryChange();
				}
		  	});
    }

 function gradeChange(){
	 var provinceFlag = false;
	 var opt = $("#province").val();
	 var cityCode = $("#cityCode").val();
	  	$.ajax({
		  		url: ctx+'/address/cityInfos?pid='+opt,
		  		success : function(data) {
					var selectHtml = "<option value='0'>请选择&nbsp;&nbsp;</option>";
					for(var i=0;i<data.cityInfos.length;i++){
						var city = data.cityInfos[i];
	             		var code = city.id;
	             		var name = city.name;
	             		if(cityCode != null && cityCode == code){
	             			selectHtml += "<option selected='selected' value='"+code+"'>"+name+"</option>";
	             			provinceFlag = true;
	             		}else{
	             			selectHtml += "<option value='"+code+"'>"+name+"</option>";
	             		}
	             	}
					$("#city").html(selectHtml);
					if(provinceFlag == false){
	             		//$("#city").html("<option value='0'>-请选择-</option>");
	             		$("#country").html("<option  value='0'>-请选择-</option>");
	             		$("#street").html("<option  value='0'>-请选择-</option>");
	             		return;
	             	}
					cityChange();
		  		}
		  	});
 }
 
$(function(){
	var provinceCode = $("#provinceCode").val();
	if(provinceCode != 0 && provinceCode!=null){
		 gradeChange();
	}
 });
 
//获取地市信息
 function getCitys(province){
  	$.ajax({ 
         type:"POST",
         url:ctx+'/address/cityInfos?pid='+province,
         data:"provinceCode=" + province,
         success:function(data){
         	var selectHtml = "<option value='0'>-请选择-</option>";
         	var cityCode ="";
         	
         	for(var i=0;i<data.cityInfos.length;i++){
				var city = data.cityInfos[i];
         		var code = city.id;
         		var name = city.name;
         		selectHtml += "<option value='"+code+"'>"+name+"</option>";
         		}
         	
         	$("#city").html(selectHtml);
         	cityCode =$("#city option:selected").val();
         	getCountrys(cityCode);
         }
     }); 
 }
 //获取区域信息
 function getCountrys(cityCode){
	//发送请求获取地市信息
	 var city=cityCode;
  	$.ajax({
         type:"POST",
         url: ctx+'/address/countryInfos?cid='+city,
         data:"cityCode=" + city,
         success:function(data){
         	var selectHtml = "<option value='0'>-请选择-</option>";
         	var countryCode ="";
        	for ( var i = 0; i < data.countryInfos.length; i++) {
				var code = data.countryInfos[i].id;
				var name = data.countryInfos[i].name;
				
				selectHtml += "<option value='" + code + "'>" + name
						+ "&nbsp;&nbsp;</option>";
				
			}
			$("#country").html(selectHtml);
			countryCode =$("#country option:selected").val();
			getStreet(countryCode);
         }
     });      	
 } 
 
 function getStreet(countryCode){
	
		 var country=countryCode;
	  	$.ajax({
	         type:"POST",
	         url: ctx+'/address/streetInfos?cid='+country,
	         data:"countryCode=" + country,
	         success:function(data){
	         	
	         	var selectHtml = "<option value='0'>-请选择-</option>";;
	         //	var streetCode =$("#street option:selected").val();
	         	for ( var i = 0; i < data.streetInfos.length; i++) {
					var code = data.streetInfos[i].id;
					var name = data.streetInfos[i].name;
					
					selectHtml += "<option value='" + code + "'>" + name
							+ "&nbsp;&nbsp;</option>";
					
				}
	         	
				$("#street").html(selectHtml);
	         }
	     });      	
	 } 
 
 function changeGrade(){
	 var province = $("#province option:selected").val();
	 if(province == "0"){
		$("#city").html("<option value='0'>-请选择-</option>");
  		$("#country").html("<option value='0'>-请选择-</option>");
  		$("#street").html("<option value='0'>-请选择-</option>");
  		//changestreet();
		return;
	 }
	 getCitys(province);
 }
 function changeCity(){
	 var city = $("#city option:selected").val();
	 
	 if(city == "0"){
  		$("#country").html("<option value='0'>-请选择-</option>");
  		$("#street").html("<option value='0'>-请选择-</option>");
  		//changestreet();
		return;
	 }
	 getCountrys(city);
 }
 
 function changeCountry(){
	 var country = $("#country option:selected").val();
	 getStreet(country);
 }