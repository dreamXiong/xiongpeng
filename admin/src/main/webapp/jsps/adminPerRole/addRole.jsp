<%@page pageEncoding="UTF-8"%>
	<!-- 添加角色模态框开始 -->
	<div class="modal fade" id="modal" >
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">添加角色</h4>
				</div>
				<div class="modal-body">
					<form action="" id="formHorizontal" class="form-horizontal">
						<div class="form-group">
							<label for="" class="col-sm-2">角色名称</label>
							<div class="col-sm-10">						
								<input type="text" name="roleName" class="form-control inputNotNull" placeholder="请输入角色名称" maxlength="30">
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-sm-2">备注</label>
							<div class="col-sm-10">
								<textarea rows="3" class="form-control" maxlength="255"></textarea>
							</div>
						</div>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="save">确定</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- 添加角色模态框结束 -->
