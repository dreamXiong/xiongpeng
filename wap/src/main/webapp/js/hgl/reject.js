$(function(){  
	$(document).delegate('#serve','click',function(){
		$(this).children('.icon-angle-right').stop().toggleClass('click');
		$(this).siblings('.no-skill').stop().slideToggle(500);
	});
	$('.icon-reorder').click(function(){
		$(this).siblings('.nav').toggleClass('click');
	});
		
	$("#image").change(function(){
		$("#imageError").text("");
		var objUrl = getObjectURL(this.files[0]);
		console.log(objUrl);
		var image = document.getElementById("image");
		var imageSize = image.files[0].size;
		if(imageSize>5*1024*1024){
			$("#imageError").text("上传图片大小不能超过5M");
			$("#showImage").attr("src","");
			return false;
		}		
		$("#showImage").attr("src",objUrl);			
	});
	
	$("#imageFace").change(function(){
		$("#imageFaceError").text("");
		var objUrl = getObjectURL(this.files[0]);
		console.log(objUrl);
		var imageFace = document.getElementById("imageFace");
		var imageFaceSize = imageFace.files[0].size;
		if(imageFaceSize>5*1024*1024){
			$("#imageFaceError").text("上传图片大小不能超过5M");
			$("#showImageFace").attr("src","");
			return false;
		}
		$("#showImageFace").attr("src",objUrl);
	});
	
	$("#imageBack").change(function(){
		$("#imageBackError").text("");
		var objUrl = getObjectURL(this.files[0]);
		console.log(objUrl);
		var imageBack = document.getElementById("imageBack");
		var imageBackSize = imageBack.files[0].size;
		if(imageBackSize>5*1024*1024){
			$("#imageBackError").text("上传图片大小不能超过5M");
			$("#showImageBack").attr("src","");
			return false;
		}
		$("#showImageBack").attr("src",objUrl);		
	}); 
	
	$("#registerBtn").click(function(){
		var isError = true;
		debugger;							
		//判定师傅必须选择技能
		var skill = $("input[name='skill']:checked").val();
		if(skill==null ||skill==""){
			$("#skillError").text("请您选择技能");
			isError = false;
		}
							
		//判定图片有没有上传
		var image = $("#image").val();
		if(image!="" &&!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(image)){
			$("#imageError").text("图片类型必须是.gif,jpeg,jpg,png中的一种");
			isError = false;
		}
		
		//判定身份证有没有上传
		var imageFace = $("#imageFace").val();
		if(imageFace!="" &&!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(imageFace)){
			$("#imageFaceError").text("图片类型必须是.gif,jpeg,jpg,png中的一种");
			isError = false;
		}
		
		//判定身份证反面照片
		var imageBack = $("#imageBack").val();
		if(imageBack!="" &&!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(imageBack)){
			$("#imageBackError").text("图片类型必须是.gif,jpeg,jpg,png中的一种");
			isError = false;
		}

		if(isError == false){
			return;
		}
		updateForm.action="doUpdateWorker";
		updateForm.submit();	
	});
});

//建立一個可存取到該file的url 
function getObjectURL(file){ 
    var url = null ;  
    if (window.createObjectURL!=undefined){ // basic 
        url = window.createObjectURL(file) ; 
    } else if (window.URL!=undefined) { // mozilla(firefox) 
        url = window.URL.createObjectURL(file) ; 
    } else if (window.webkitURL!=undefined) { // webkit or chrome 
        url = window.webkitURL.createObjectURL(file) ; 
    } 
    return url ; 
}