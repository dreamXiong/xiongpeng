<%@page pageEncoding="UTF-8"%>
<tiles:insert beanName="myHglPage">
	<tiles:put name="title" value="我的经验值" />
	<tiles:put name="body" type="String">
	<c:set value="experience" var="modalName"></c:set>	
	<html>
<head>
	<meta charset="UTF-8">
	<title>添加品牌</title>
	<link rel="stylesheet" href="${ctx}/css/me.css">
	<script src="${ctx}/js/jquery/jquery1.9.2/ui/minified/jquery-ui.min.js"></script>
	<script src="${ctx}/js/hgl/datepicker-zh.js"></script>
	<script src="${ctx}/js/hgl/pick.js"></script>
	<script src="${ctx}/js/hgl/brand.js"></script>
	
	<script src="${ctx}/js/jquery/jquery1.9.2/ui/jquery.ui.widget.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload-process.js"></script>
	<script src="${ctx}/js/jquery/jquery.fileupload-validate.js"></script>
	 <script src="${ctx}/js/jquery/uploadPreview.min.js"></script>
</head>
<body>
<!-- 内容板块开始 -->
	<div>
		<div class="area me">
				<div class="main-right pull-right">
				<div class="details-list">
					<!-- <h3 class="bg-gray">
						添加品牌
					</h3> -->
					 <form action="saveBrand" id="saveBrand" enctype="multipart/form-data" method="post" class="form-horizontal">
					   <input type="hidden" name="logoName" id="logoName1_Path"/>
					    <input type="hidden" name="producttypeName" id="producttypeName"/>
					 <table class="my-pick" style="width: 600px;" id="my-pick">
						<tbody>
						<tr>
							<th>品牌分类</th>
							<td colspan="3">
								<select id=producttypeId name="producttypeId" class="select1" >
			                          <c:forEach  var="item" items="${mLists}" varStatus="status">
			                          <option value="${item.id}">${item.name}</option>
			                          </c:forEach>
								</select>
							</td>
							<td></td>
						</tr>
						<tr>
							<th>品牌名称</th>
							<td colspan="3">
								<input id="name" name="name" type="text" class="inputNotNull zss" style="float: left;" maxlength="30" onblur="checkBrand()">
							</td>
							<td id="key_info_namename"><p class="errMsgLabel"></p></td>
						</tr>
						<tr>
							<th>厂商名称</th>
							<td colspan="3">
								<input id="manufacturerName" name="manufacturerName" class="" type="text" style="float: left;" maxlength="30">
							</td>
							<td></td>
						</tr>
						<tr>
							<th>平台网址</th>
							<td colspan="3">
								<input id="url" name="url" type="text"  class="" maxlength="50">
							</td>
							<td id="key_info_urlurl"><p class="errMsgLabel"></p></td>
						</tr>
						<tr>
							<th>品牌logo图</th>
							<td>
								<div class="box">
									<span class="icon-plus"></span>
									
											<input id="logoName1" name="imgFile" class="pImg" type="file">
										  <div id="pImgFourdiv">
						                    <img id="pImgFourShow">
						                  </div>
								</div>
							</td>
							<td id="key_info_logoName1imgFile"><p class="errMsgLabel"></p></td>
						</tr>
				
					<tr>
						<th style="padding:0 5px;">品牌描述</th>     
						<td colspan="3" style="height:100px;padding:0 5px;">
							<textarea id="remark" name="remark" rows="1" maxlength="300" style="height:100%;line-height:1.2;padding:2px;"></textarea>     
						</td>
						<td style="padding:0 5px;"></td>
					</tr>      
					<tr>       
						<th></th>         
						<td colspan="3">
							<a type="button" onclick="saveBrand();" class="btn-bg" href="javascript:;">
								<span style="margin-right:0;">确定</span>      
							</a>       
							<a type="button" class="btn-bg1"  href="${ctx}/brand/brand">      
								<span style="margin-right:0;">取消</span>
							</a>     
						</td>
						
					
						<td></td>
					</tr>
					
					</tbody>
					</table>
					</form>
				</div>
				</div>
				
				
			</div>
		</div>
	</div>
<div id="datepicker"></div>
</body>
<script>
	
</script>
</html>
	</tiles:put>
</tiles:insert>