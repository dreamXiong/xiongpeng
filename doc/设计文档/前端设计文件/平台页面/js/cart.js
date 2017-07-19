
	$(function() {
		$('.chart-nav div').click(function(event) {
			$(this).addClass('active').siblings('div').removeClass('active');
		});
		
		var tr_len=$('tbody tr').length;
		$('.num,.sum').attr('rowspan',tr_len);
		$('.shrink').click(function(event) {
			var table = $(this).parents('.chat-title').siblings('.table');
			$(this).toggleClass('out');
			table.slideToggle(500);
			
		});
		$('#check-only').click(function(event) {
			var check = $(this).prop('checked');
			$('input[type="checkbox"]').prop('checked',check);
		});
		$('#dialog1').dialog({
			title:'提示',
			autoOpen: false,
			modal:true,
			resizable: false,//禁止弹出层缩放
        	draggable :false,//禁止拖动
        	width:'300',
        	closeText:'关闭',
        	buttons:{
        		确定 : function(){
        			$(this).dialog('close'); 
        		},
        		取消 : function(){
        			$(this).dialog('close'); 
        		}
        	},

		})
		$('#dialog').dialog({
			title:'新增收货地址' ,
			autoOpen: false,
			modal:true,
			resizable: false,//禁止弹出层缩放
        	draggable :false,//禁止拖动
        	width:'500',
        	closeText:'关闭',
        	buttons:{
        		保存收货人信息:function(){
        			var odiv = $('<div class="adde-list list-title">'+
        							'<div class="adde-look">'+
										'<p class="names">'+ 1 +'</p>'+
										'<div class="age">'+
											'<p class="text-ellipsis" title="">'+1+'</p>'+
											'<p>'+1+'</p>' +
										'</div>'+
										'<div class="operation">'+
											'<a href="javascript:;">修改</a> '+
											' <a class="remove" href="javascript:;">删除</a>'+
										'</div>'+
									'</div>'+
        						 '</div>');
        			$('#addAdde').before(odiv);
        			$(this).dialog('close');
        		},
        		取消:function(){
        			$(this).dialog('close');
        		}
        	}
		});
		$('#addAdde').click(function(event) {
			$('#dialog').dialog('open');
		});
		$('.adde-list').delegate('.remove','click',function(){
			$('#dialog1').dialog('open');
			
		});
		$('.succeed2 .clear li').click(function(event) {
			$(this).addClass('active').siblings('li').removeClass('active')
		});

		$('.addr-lists').delegate('.default','click',function(event){
			$('.default').removeClass('none');
			$('.list-title').removeClass('active');
			$(this).addClass('none')
				   .parents('.list-title').addClass('active');
		});
		
	});
	