$("document").ready(function(){		
		//选择省的时候
		$("#provinceName").change(function(){			
			var html = "";
			var provinceId = $("#provinceName").val();
			$("#cityName").html("");
			$.ajax({
				type:"post",
				url:"doSearchCityResult",
				data:{"parentId":provinceId},
				success:function(data){
					var jsonData = eval(data);
					$("#cityName").html("<option value='0'>===请选择===</option>");
					for(var i=0;i<jsonData.length;i++){
						html+="<option value='"+jsonData[i].id+"'>"+jsonData[i].name+"</option>";
					}
					$("#cityName").append(html);
				}
			});
		});
		
		
		//选择市的时候
		$("#cityName").change(function(){
			var html = "";
			var cityId = $("#cityName").val();
			$("#countryName").html("");
			$.ajax({
				type:"post",
				url:"doSearchCountryResult",
				data:{"parentId":cityId},
				success:function(data){
					$("#countryName").html("<option value='0'>===请选择===</option>");
					var jsonData = eval(data);
					for(var i=0;i<jsonData.length;i++){
						html +="<option value='"+jsonData[i].id+"'>"+jsonData[i].name+"</option>";
					}
					$("#countryName").append(html);
				}
			});
		});
		
		//选择区县的时候
		$("#addressModal_Upd #countryName").change(function(){
			var html ="<option value='0'>===请选择===</option>";
			var countryId = $("#addressModal_Upd #countryName").val();
			alert(countryId);
			$("#addressModal_Upd #streetName").html("");
			$.ajax({
				type:"post",
				url:"doSearchStreetResult",
				data:{"parentId":countryId},
				success:function(data){
					$("#addressModal_Upd #streetName").append("<option value='0'>===请选择===</option>");
					var jsonData = eval(data);
					for(var i=0;i<jsonData.length;i++){
						html += "<option value='"+jsonData[i].id+"'>"+jsonData[i].name+"</option>";
					}
					$("#addressModal_Upd #streetName").append(html);
				}
			});
		});
		
		//选择新增街道或是新增区县
		$(".addRadioItem").change(function(){
			var addType = $('input[name="addType"]:checked').val(); 
			if(addType==1){
				$("#streetBox").show();
				$("#countryBox").hide();
			}else if(addType==2){
				$("#streetBox").hide();
				$("#countryBox").show();
			}
		});
		
		//新增街道区县点击确定
		$("#addressModal_Add #addStreetBtn").click(function(){
			var isError = true;			
			//如果选择的是新增街道
			var addType = $('input[name="addType"]:checked').val();
			if(addType==1){
				//区县id
				var countryId_add = $("#addressModal_Add #countryName").val();
				//街道id
				var streetId_add = $("#addressModal_Add #streetId").val();
				//街道名称
				var streetName_add = $("#addressModal_Add #streetName").val();
				
				//验证区县有没有选择
				if(countryId_add == 0){
					$("#addressModal_Add #countryNameError").text("区县必须选择");
					$("#addressModal_Add #countryNameError").css("color","red");
					$("#addressModal_Add #countryName").css("border","1px solid red");
					isError = false;
				}
				
				//街道编码没有填写
				if(streetId_add==""){
					$("#addressModal_Add #streetIdError").text("街道编码必须填写");
					$("#addressModal_Add #streetIdError").css("color","red");
					$("#addressModal_Add #streetId").css("border","1px solid red");
					isError = false;
				}
				
				//街道编码必须为数字
				if(streetId_add !="" && isNaN(streetId_add)){
					$("#addressModal_Add #streetIdError").text("街道编码必须为数字编码");
					$("#addressModal_Add #streetIdError").css("color","red");
					$("#addressModal_Add #streetId").css("border","1px solid red");
					isError = false;
				}
				
				//街道名称没有填写
				if(streetName_add==""){
					$("#addressModal_Add #streetNameError").text("街道名称必须填写");
					$("#addressModal_Add #streetNameError").css("color","red");
					$("#addressModal_Add #streetName").css("border","1px solid red");
					isError = false;
				}
			}else if(addType==2){ //如果选择的是新增区县
				//城市id
				var cityId_add = $("#addressModal_Add #cityName").val();
				//区县编码
				var countryId_add = $("#addressModal_Add #countryId_Add").val();
				//区县名称
				var countryName_add = $("#addressModal_Add #countryName_Add").val();
				
				//如果城市没有选择
				if(cityId_add==0){
					$("#addressModal_Add #cityNameAddError").text("城市必须选择");
					$("#addressModal_Add #cityNameAddError").css("color","red");
					$("#addressModal_Add #cityName").css("border","1px solid red");
					isError = false;
				}
				
				//如果区县编码没有填写
				if(countryId_add==""){
					$("#addressModal_Add #countryIdAddError").text("区县编码必须填写");
					$("#addressModal_Add #countryIdAddError").css("color","red");
					$("#addressModal_Add #countryId_Add").css("border","1px solid red");
					isError = false;
				}
				
				//如果区县不全部为数字
				if(countryId_add !="" && isNaN(countryId_add)){
					$("#addressModal_Add #countryIdAddError").text("区县编码必须为数字编码");
					$("#addressModal_Add #countryIdAddError").css("color","red");
					$("#addressModal_Add #countryId_Add").css("border","1px solid red");
					isError = false;
				}
				
				//区县名称没有填写
				if(countryName_add==""){
					$("#addressModal_Add #countryNameAddError").text("区县名称必须填写");
					$("#addressModal_Add #countryNameAddError").css("color","red");
					$("#addressModal_Add #countryName_Add").css("border","1px solid red");
					isError = false;
				}				
			}
					
			if(isError==false){
				return;
			}
			$.ajax({
				type:"post",
				url:"doAddStreetInfo",
				data:{"addType":addType,"cityId":cityId_add,
					  "countryId":countryId_add,"countryName":countryName_add,
					  "streetId":streetId_add,"streetName":streetName_add},
				success:function(data){
					if(data=="true"){
						$("#addressModal_Add").modal("hide");
						location.reload(true);
						//添加成功后重新加载数据
						addressForm.action="doInitAddress";
						addressForm.submit();
					}
				}
			});
		});
		
		//区县获得光标 --添加
		$("#addressModal_Add #countryName").focus(function(){
			$("#addressModal_Add #countryNameError").text("");
			$("#addressModal_Add #countryNameError").removeAttr("style");
			$("#addressModal_Add #countryName").removeAttr("style");
		});
		
		//街道编码获得光标 --添加
		$("#addressModal_Add #streetId").focus(function(){
			$("#addressModal_Add #streetIdError").text("");
			$("#addressModal_Add #streetIdError").removeAttr("style");
			$("#addressModal_Add #streetId").removeAttr("style");
		});
		
		//街道名称获得光标 --添加
		$("#addressModal_Add #streetName").focus(function(){
			$("#addressModal_Add #streetNameError").text("");
			$("#addressModal_Add #StreetNameError").removeAttr("style");
			$("#addressModal_Add #StreetName").removeAttr("style");
		});
		
		//城市获得光标 --修改
		$("#addressModal_Add #cityName").focus(function(){
			$("#addressModal_Add #cityNameAddError").text("");
			$("#addressModal_Add #cityNameAddError").removeAttr("style");
			$("#addressModal_Add #cityName").removeAttr("style");
		});
		
		//区县编码获得光标--修改
		$("#addressModal_Add #countryId_Add").focus(function(){
			$("#addressModal_Add #countryIdAddError").text("");
			$("#addressModal_Add #countryIdAddError").removeAttr("style");
			$("#addressModal_Add #countryId_Add").removeAttr("style");
		});
		
		//区县名称获得光标 -- 修改
		$("#addressModal_Add #countryName_Add").focus(function(){
			$("#addressModal_Add #countryNameAddError").text("");
			$("#addressModal_Add #countryNameAddError").removeAttr("style");
			$("#addressModal_Add #countryName_Add").removeAttr("style");
		});
		
	});
	
	//新增街道信息
	function addItem(){
		//查询出所有的省信息	
		var htmlPro ="";
		$.ajax({
			type:"post",
			url:"doSearchProvinceResult",
			data:"",
			success:function(data){
				var jsonData = eval(data);
				for(var i=0;i<jsonData.length;i++){
					htmlPro += "<option value='"+jsonData[i].id+"'>"+jsonData[i].name+"</option>";
				}
				$("#addressModal_Add #provinceName").append(htmlPro);
			}
		});
		$("#addressModal_Add").modal("show");		
	}
	
	
	
	//修改街道信息
	function updateItem(itemProvinceId,itemCityId,itemCountryId,itemStreetId){
		var html="";
		$("#updModalBody").html("");
		/*弹出框之前先查询出对应的省市区街道信息*/
		$.ajax({
			type:"post",
			url:"doInitUpdateAddress",
			data:{"provinceId":itemProvinceId,"cityId":itemCityId,"countryId":itemCountryId,"streetId":itemStreetId},
			dataType:"json",
			success:function(data){
				html="<div><label style='width:100px;'>省名称</label><span>"+data.cityName+"</span></div>"
					+"<div><label style='width:100px;'>城市名称</label><input type='text' id='newCityName' name='newCityName' onclicke='clearCityInput()' value='"+data.cityName+"'><span id='cityError'></span></div>" 
     				+"<div><label style='width:100px;'>区县名称</label><input type='text' id='newCountryName' name='newCountryName' onclick='clearCountryInput()' value='"+data.countryName+"'><span id='countryError'></span></div>" 
     				+"<div><label style='width:100px;''>街道名称</label><input type='text' id='newStreetName' name='newStreetName' onclick='clearStreetInput()' value='"+data.streetName+"'><span id='streetError'></span></div>";
				$("#updModalBody").append(html);
			}			
		});
		
		$("#addressModal_Upd").modal("show");
		

		$("#addressModal_Upd #updStreetBtn").click(function(){			
			var isError = true;
			var cityName = $("#addressModal_Upd #newCityName").val();
			var countryName = $("#addressModal_Upd #newCountryName").val();
			var streetName = $("#addressModal_Upd #newStreetName").val();
			//验证城市、区县、街道不能为空
			if(cityName==""){
				$("#addressModal_Upd #cityError").text("城市不能为空");
				$("#addressModal_Upd #cityError").css("color","red");
				$("#addressModal_Upd #cityError").css("font-size","12px");
				$("#addressModal_Upd #newCityName").css("border","1px solid red");
				isError = false;
			}
			if(countryName==""){
				$("#addressModal_Upd #countryError").text("区县不能为空");
				$("#addressModal_Upd #countryError").css("color","red");
				$("#addressModal_Upd #countryError").css("font-size","12px");
				$("#addressModal_Upd #newCountryName").css("border","1px solid red");
				isError = false;
			}
			if(streetName==""){
				$("#addressModal_Upd #streetError").text("区县不能为空");
				$("#addressModal_Upd #streetError").css("color","red");
				$("#addressModal_Upd #streetError").css("font-size","12px");
				$("#addressModal_Upd #newStreetName").css("border","1px solid red");
				isError = false;
			}
			
			if(isError == false){
				return;
			}
			
			$.ajax({
				type:"post",
				url:"doUpdateAddress",
				async:true,
				data:{"cityId":itemCityId,"cityName":cityName,
					  "countryId":itemCountryId,"countryName":countryName,
					  "streetId":itemStreetId,"streetName":streetName},
				success:function(data){
					$("#cityName"+itemCityId).text(cityName);
					$("#countryName"+itemCountryId).text(countryName);
					$("#streetName"+itemStreetId).text(streetName);
					$("#addressModal_Upd").modal("hide");
				}
			});
		});		
	}
	
	//清除城市样式
	function clearCityInput(){
		$("#addressModal_Upd #newCityName").removeAttr("style");
		$("#addressModal_Upd #cityError").text("");
		$("#addressModal_Upd #cityError").removeAttr("style");
	}
	
	//清除区县样式
	function clearCountryInpnt(){
		$("#newCityName").removeAttr("style");
		$("#cityError").text("");
		$("#cityError").removeAttr("style");				
	}
	
	//清除街道样式
	function clearStreetInput(){
		$("#newStreetName").removeAttr("style");
		$("#streetError").text("");
		$("#streetError").removeAttr("style");
	}
	
	//删除街道
	function deleteStreet(streetId){
		$("#addressModal_Dlt").modal("show");
		$("#dltStreetBtn").click(function(){
			$.ajax({
				type:"post",
				url:"doDeleteStreet",
				data:{"streetId":streetId},
				success:function(data){
					if(data=="1"){
						$("#addressModal_Dlt").modal("hide");
						location.reload(true);
					}
				}
			});
		});
	}