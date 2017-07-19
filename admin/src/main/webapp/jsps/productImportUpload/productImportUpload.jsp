<%@ page language="java" pageEncoding="UTF-8"%>
<tiles:insert beanName="indexPage">
	<tiles:put name="title" value="产品批量导入" />
	<tiles:put name="body" type="String">
		<html lang="en">
<head>
<meta charset="UTF-8">
<title>产品批量导入</title>
</head>
<body>
	<c:set value="product" var="productImportUpload"></c:set>
	<div class="content-wrapper">
		<section class="content-header">
			<h1>
				<small>产品批量导入</small>
			</h1>
		</section>
		<section class="content">
			<div class="row no-margin">
				<div class="box box-primary">
					<div class="box-body">
						<c:if test="${success==1 }">
							<div class="main-right text-center" style="line-height:40px;">
								<span class="icon-ok-sign" style="color:#3CAD3C;font-size:30px;line-height:40px;display:inline-block;vertical-align:middle;"></span> 上传成功
							</div>
						
								<a class="btn btn-primary btn-sm" href="${ctx}/productImportUpload/init">返回</a>
					
							
						</c:if>
						
						<c:if test="${success==2 }">
							<div class="main-right text-center" style="line-height:40px;">
								<span class="icon-remove-sign" style="color:#E85445;font-size:30px;line-height:40px;display:inline-block;vertical-align:middle;"></span> ${message }
							</div>
								<a class="btn btn-primary btn-sm" href="${ctx}/productImportUpload/init">返回</a>
						
							
						</c:if>
						
						<c:if test="${success==3 }">
							<form action="${ctx}/productImportUpload/upload"
								enctype="multipart/form-data" method="post" class="form-inline"
								id="key_${modalName}_qryFrm">
								<input type="file" name="file" style="border:1px solid #eee;" />
								<button type="submit"  class="btn btn-primary btn-sm" style="margin-top:5px;">上传</button> 
							</form>         
						</c:if>
					</div>
				</div>
			</div>
		</section>
	</div>
</body>
		</html>
	</tiles:put>
</tiles:insert>