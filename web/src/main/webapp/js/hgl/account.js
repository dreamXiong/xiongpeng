function delectBankAccount(){
	var id = $("#bankAccountId").val();
	$.ajax({
        type : "POST",
        url : ctx+'/cashAccount/deleteAccountBank',
        data : {
        	accountBankId:id
        },
        success : function(response) {
        	$( "#dialog4" ).dialog('close');
        	$("#bankAccountListId").html(response);
        }
    }); 	
}
$(function(){
	  $( "#dialog4" ).dialog({            
	        title:'删除账户' ,  
	        autoOpen: false ,//禁止自己弹出
	        resizable: false,//禁止弹出层缩放
	        draggable :false,//禁止拖动
	        modal: true,//是否有模态框
	    //  height:?
	        width:'440',     //设置宽高
	        closeText:'关闭',//closetitle
	        buttons:{         //创建btn
	        	
	          	关闭:function(){          //btn的回调函数
			    	$(this).dialog("close");
			    },
			    确定:function(){
	        		delectBankAccount();
	          	},
	        },
	    }); 
});
$(function(){
	  $( "#dialog2" ).dialog({            
	        title:'添加账户' ,  
	        autoOpen: false ,//禁止自己弹出
	        resizable: false,//禁止弹出层缩放
	        draggable :false,//禁止拖动
	        modal: true,//是否有模态框
	    //  height:?
	        width:'440',     //设置宽高
	        closeText:'关闭',//closetitle
	        buttons:{         //创建btn
	        	
	          	关闭:function(){          //btn的回调函数
			    	$(this).dialog("close");
			    },
			    确定:function(){
	        		addCashAccount();
	          	},
	        },
	    }); 
});
$(function(){
	  $( "#dialog3" ).dialog({            
	        title:'删除订单' ,  
	        autoOpen: false ,//禁止自己弹出
	        resizable: false,//禁止弹出层缩放
	        draggable :false,//禁止拖动
	        modal: true,//是否有模态框
	    //  height:?
	        width:'440',     //设置宽高
	        closeText:'关闭',//closetitle
	        buttons:{         //创建btn
	          	关闭:function(){          //btn的回调函数
			    	$(this).dialog("close");
			    },
	        },
	    }); 
});
function deleteDialog(id){
	$("#bankAccountId").val(id);
	$.ajax({
        type : "POST",
        url : ctx+'/cashAccount/deleteAccountBankValidate',
        data : {
        	accountBankId:id
        },
        success : function(response) {
        	if(response.code == 99999){ 
        		$("#dialog3").dialog('open');
        	}else{
        		$("#dialog4").dialog('open');
        	}
        }
    }); 	
}

function addDialog(){
	$(".inputNotNull").val("");
	$("#bankAccountError").hide();
	$( "#dialog2" ).dialog('open');
}
function addCashAccount(){
	var addType = validateForm("addBankAccount");
	if(!addType){ 
		return;
	}
	var str = "银行";
	var bank = $("#bank").val();
	if(!bank.endWith(str)){
		$('#bank').toggleClass('onerrInput'); 
	}
	var bankAccountLen = $("#bankAccount").val().length;
	if(!addValidate || bankAccountLen < 16 ){
		$('#bankAccount').toggleClass('onerrInput'); 
		return;
	}
	if(!bank.endWith(str) || bankAccountLen < 16 ){
		return;
	}
	var param = $("#addBankAccount").serialize();
	$.ajax({
        type : "POST",
        url : ctx+'/cashAccount/addAccountBank',
        data:param,
        success : function(response) {
        	 $( "#dialog2" ).dialog('close');
        	/* $("#bankAccountListId").html(response);*/
        	 location.reload();
        }
	});
}
String.prototype.startWith=function(str){
	var reg=new RegExp("^"+str);
	return reg.test(this);
	} ;
String.prototype.endWith=function(str){
	var reg=new RegExp(str+"$");
	return reg.test(this);
	} ;
$(function(){
	  $( "#dialog5" ).dialog({            
	        title:'账户提现' ,  
	        autoOpen: false ,//禁止自己弹出
	        resizable: false,//禁止弹出层缩放
	        draggable :false,//禁止拖动
	        modal: true,//是否有模态框
	    //  height:?
	        width:'440',     //设置宽高
	        closeText:'关闭',//closetitle
	        buttons:{         //创建btn
	          	关闭:function(){
			    	$(this).dialog("close");
			    },
			    确定:function(){
	        		getMoney();
	          	},
	        },
	    }); 
});
function getMoney(){
	var accountBankId = $("#selector").val();
	var addType = validateForm("getMoneyNowgetMoney");
	var money = toDecimal2($("#money").val());
	if(!addType || money < 0.0000001 || accountBankId == null){   
		return;
	}
	$("#money").val(money);
	var maxMoney = $("#maxMoney").val();
	var t=/^\d+(\.\d+)?$/; 
	if(money == '' || !t.test(money) || !(maxMoney - money >= 0)){
		$("#moneyError").show();
		$('#money').toggleClass('border-red'); 
		return;
	}
	$("#getMoneyNowgetMoney").submit();  
}

var addValidate = true;
function addValidateAccount(){
	var bankAccount = $("#bankAccount").val();
	$.ajax({
        type : "POST",
        url : ctx+'/cashAccount/addValidateAccount',
        data:{
        	bankAccount:bankAccount
        },
        success : function(response) {
        	if(response.code == 0){
        		$("#bankAccountError").hide();
        		addValidate = true;
        	}else{
        		$("#bankAccountError").show();
        		$('#bankAccount').toggleClass('onerrInput'); 
        		addValidate = false;
        	}
        }
	});
}
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
function showGetMoneyDialog(){
	$("#moneyError").hide();
	$("#money").val("");
	$( "#dialog5" ).dialog('open');
}