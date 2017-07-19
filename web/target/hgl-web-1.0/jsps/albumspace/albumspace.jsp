<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
	<tiles:put name="title" value="个人相册空间" />
	<tiles:put name="body" type="String">
		<c:set value="albumspace" var="modalName"></c:set>
		<html>
<link rel="stylesheet" href="${ctx}/css/me.css">
		<link href="${ctx}/css/toastr.css" rel="stylesheet" type="text/css" /> 
		<script src="${ctx}/js/hgl/toastr.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<div class="area me">
		<div class="main-right pull-right">
			<div class="details">
				<div class="details-list stock_nav">
					<!-- 	<h3 class="bg-gray">个人相册空间</h3> -->
					名称
					<input type="text" placeholder="请输入需要搜索的内容" class="imageSearch"
						style="width: auto;" />
					<button onclick="searchImage(); " class="btn-bg">
						<span style="margin-right: 0;">搜索</span>
					</button>
					<a type="button" class="btn-bg"
						href="${ctx }/albumspace/albumspaceAdd"> <span
						style="margin-right: 0;">添加相册</span>
					</a>
					<div class="ul-list" id="list">

						<%@include file="albumspaceList.jsp"%>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	EcWeb.currentModalName = '${modalName}';
	$(function() {
		$(document).delegate('#list li','mouseover',function() {
			$(this).find('.bianji').stop().animate({
				top : '0',
			}, 300);
		}); 
		$(document).delegate('#list li','mouseout',function() {
			$(this).find('.bianji').stop().animate({
				top : '-30px',
			}, 300);
		});
		
		 
		/* 	$(".bianji").delegate(".reomve",'click',function(){
				//$(this).parents('li').remove();
			}); */

		$(document).delegate(".rechristen", 'click', function() {
			
			$(this).parents('li').find('input[type="text"]').css('display', 'block');
			$(this).parents('li').find('input[type="text"]').focus();
		})
		$("#list").delegate('input[type="text"]','blur',function(){   
			var imageNameOld =$(this).val();
			if(imageNameOld.indexOf("&")!=-1||imageNameOld.indexOf("?")!=-1){
				alert(imageNameOld);
				toastr.error("图片名称中不能包含:&?等这些特殊字符");
				return false;
			}
			var id =$(this).attr("id");
			var version =$(this).attr("name");
			var endsWith = $(this).attr('data-endsWith');
			imageNameOld=trimStr(imageNameOld);
			imageNameOld = imageNameOld + '.' + endsWith;
			$.ajax({
				type : 'POST',
				url : 'alterImageName_ajax',
				data : {
					"id" : id,
					"imageNameOld" : imageNameOld,
					"version" : version
				},
				success : function(data) {
					$("#list").html(data);
					reName();
					if($("#errorTips").val()==='0'){
						toastr.error("重命名的名称已经存在!");
					}
				},
				error:function(){
					
				}
			});
			return false;
		});
			
			function reName(){
				$("input[name='hideText']").each(function() {
					$(this).siblings('input[type="text"]').hide();
				})
				$(document).delegate('#list li','mouseover',function() {
					$(this).find('.bianji').stop().animate({
						top : '0',
					}, 300);
				}); 
				$(document).delegate('#list li','mouseout',function() {
					$(this).find('.bianji').stop().animate({
						top : '-30px',
					}, 300);
				});
			}
			
			

		$(document).delegate('.reomve', 'click', function() {
			var id = $(this).parents('li').find('input[type="text"]').attr("id");

			if (!confirm("确定删除该图片吗?")) {
				return false;
			}
			$.ajax({
				type : 'POST',
				url : 'deleteImageName_ajax',
				data : {
					"id" : id
				},
				success : function(data) {
					$("#list").html(data);
					$(document).delegate('#list li','mouseover',function() {
						$(this).find('.bianji').stop().animate({
							top : '0',
						}, 300);
					}); 
					$(document).delegate('#list li','mouseout',function() {
						$(this).find('.bianji').stop().animate({
							top : '-30px',
						}, 300);
					});
				}
			});
		});

	});

	function searchImage() {
		var searchVal = $(".imageSearch").val();
		$.ajax({
			type : 'POST',
			url : 'searchImage',
			data : {
				"likeKey" : searchVal
			},
			success : function(data) {

				$("#list").html(data);
			}
		});
		
	}
	function trimStr(str){return str.replace(/(^\s*)|(\s*$)/g,"");}
</script>
		</html>
	</tiles:put>
</tiles:insert>