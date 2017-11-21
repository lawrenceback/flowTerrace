<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>添加菜单</title>


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?dds">


	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">

	<style>

		.navbar-top-links li a {
			padding: 15px 10px;
		}
		.ibox{
			margin-bottom: 0;
		}

		#page-wrapper .datagrid-btable tr{height: 40px;}
	</style>
	<script>document.write('<script src="${pageContext.request.contextPath}/config/cg.js?' + new Date().getTime() + '"><\/script>');</script>
	<script>
		var $dom = {};
	</script>
</head>

<body>

<div class="wrapper wrapper-content animated fadeInRight">


	<div class="row">
		<div class="col-sm-8 col-sm-offset-2">
			<div class="alert alert-warning alert-dismissable">
				<button aria-hidden="true" data-dismiss="alert" class="close" type="button">×</button>
				提示：如果该菜单中存在子菜单，那么该菜单中的”<code>URL</code>“不允许输入任何内容。
			</div>
		</div>
	</div>
	<div class="row">




		<form method="post" class="form-horizontal" action="" name="user" id="user">



			<input type="hidden" name="f" value="921001">
			<input type="hidden" name="id" id="id" value="${function.id}">
			<div class="form-group">
				<label class="col-sm-2 control-label" for="functionName">名称</label>

				<div class="col-sm-8">
					<input type="text" class="form-control" name="functionName" id="functionName" value="${function.functionName}" placeholder="菜单名称">
				</div>
			</div>
			<div class="hr-line-dashed"></div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="functionUrl">URL</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" name="functionUrl" id="functionUrl" value="${function.functionUrl }" placeholder="菜单地址">
					<span class="help-block m-b-none"></span>
				</div>
			</div>
			<div class="hr-line-dashed"></div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="functionOrder">菜单排序</label>

				<div class="col-sm-8">
					<input type="text" class="form-control" name="functionOrder" id="functionOrder" value="${function.functionOrder }" placeholder="输入数字，该值决定了菜单的先后顺序">
				</div>
			</div>
			<div class="hr-line-dashed"></div>
			<div class="form-group">
				<label class="col-sm-2 control-label">选择图标</label>

				<div class="col-sm-8">
					<input type="hidden" name="iconId" id="iconId" value="${function.icon.id}">
					<input type="text" class="form-control" name="iconClass" value="${function.icon.iconClass}" placeholder="点击选择图标" onclick="choiceIcon('${function.icon.id}',this)">
				</div>
			</div>
			<div class="hr-line-dashed"></div>
			<div class="form-group">
				<label class="col-sm-2 control-label">父菜单</label>

				<div class="col-sm-8">
					<input type="hidden" name="parentFunctionId" id="parentFunctionId" value="${function.functionParent.id }">
					<input type="text" class="form-control" id="parentFunctionName" value="${function.functionParent.functionName }" placeholder="选择父菜单" onclick="choiceParentFunction('${function.functionParent.id }',this)">
				</div>
				<div class="col-sm-2">
					<a onclick="removeFunctionParent()">移除</a>
				</div>
			</div>

			<input type="submit" id="submit" value="submit" style="display: none">
		</form>
	</div>


	<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
	<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
	<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>
	<script src="${pageContext.request.contextPath}/plug-in-xenon/assets/js/jquery-validate/jquery.validate.min.js"></script>
	<script src="${pageContext.request.contextPath}/plug-in-xenon/assets/js/jquery-validate/localization/messages_zh.min.js"></script>
	<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
	<script>


		$(document).ready(function ()
		{

			$dom.parentFunctionId = $('#parentFunctionId');
			$dom.iconId = $('#iconId');

			//表单校验
			validateForm();

		});



		//表单校验
		function validateForm() {

			// Validation and Ajax action
			$("form#user").validate({
				rules: {
					functionName: {
						required: true
					},

					functionOrder: {
						required: true
					},
					iconClass: {
						required: true
					},
				},

				messages: {},

				// Form Processing via AJAX
				submitHandler: function(form)
				{

					ajaxAddUser(form);

				}
			});
		}



		//提交信息
		function ajaxAddUser(form) {

			var $form = $(form);

			$.ajax({
				url: cg_vte.service,
				type: 'post',
				dataType: 'text',
				data: $form.serialize(),
				success: function (data) {

					data = JSON.parse(data);
					console.log(data);

					state_msg(data);

				}
			})

		}

		//返回处理
		function state_msg(res){

			switch (res.status){
				case 10000:
					swal({
						title: "提示",
						text: "成功",
						type: "success",
						showCancelButton: false,
						closeOnConfirm: false,
						confirmButtonText: "OK",
						confirmButtonColor: "#1AB394"
					}, function() {
						window.parent.completeCallback();
					});
					break;
				default:
					layer.msg(res.msg);
					break;
			}

		}
		
		function removeFunctionParent() {
			$dom.parentFunctionId.val('');
			$('#parentFunctionName').val('');
		}


		//================================== 点击选择图标 Start ================================//
		var iconWarp={};
		function choiceIcon(id,dom) {
			iconWarp.dom = dom;
			iconWarp.layer = layer.open({
				type: 2,
				title: '',
				closeBtn: 0,

				area: ['80%', '80%'],
				content: cg_vte.path + 'functionController.do?choiceIcon&id='+id,
				fix: false,
				scrollbar: false,
			});
		}
		//点击图标回调事件
		function choiceIconCallback(iconClass,id) {
			$dom.iconId.val(id);
			iconWarp.dom.value = iconClass;
			layer.close(iconWarp.layer);
		}
		function choiceIconClose() {
			layer.close(iconWarp.layer);
		}
		//================================== 点击选择图标 End ================================//


		//================================== 点击选择父菜单 Start ==============================//
		var parentFunctionWarp={};
		function choiceParentFunction(id,dom) {
			parentFunctionWarp.dom = dom;
			parentFunctionWarp.layer = layer.open({
				type: 2,
				title: '',
				closeBtn: 0,

				area: ['80%', '80%'],
				content: cg_vte.path + 'functionController.do?choiceParentFunction&id=' + id,
				fix: false,
				scrollbar: false,
			});
		}
		//点击选择父菜单回调事件
		function choiceParentFunctionCallback(id,function_name) {
			$dom.parentFunctionId.val(id);
			parentFunctionWarp.dom.value = function_name;
			layer.close(parentFunctionWarp.layer);
		}
		function choiceParentFunctionClose() {
			layer.close(parentFunctionWarp.layer);
		}
		//================================== 点击选择父菜单 End ================================//
	</script>
</div>
</body>
</html>

