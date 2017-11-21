<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<title>用户管理-修改密码</title>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css">

	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">

	<style>

		.navbar-top-links li a {
			padding: 15px 10px;
		}

		#page-wrapper .datagrid-btable tr{height: 40px;}
	</style>
	<script>document.write('<script src="${pageContext.request.contextPath}/config/cg.js?' + new Date().getTime() + '"><\/script>');</script>
</head>

<body>


<div class="wrapper wrapper-content animated fadeInUp">


	<div class="row">
		<div class="col-sm-12">

			<form method="post" class="form-horizontal" action="" name="user" id="user">

				<input type="hidden" name="f" value="222002">

				<input type="hidden" name="id" value="${user.id}">

				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label">原始密码</label>

					<div class="col-xs-6 col-md-4 col-md-4">
						<input type="text" class="form-control" name="password" placeholder="原始密码" value="">
					</div>
				</div>
				<div class="hr-line-dashed"></div>
				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label">新密码</label>

					<div class="col-xs-6 col-md-4 col-md-4">
						<input type="password" class="form-control" name="passwordNew" id="passwordNew" placeholder="新密码">
					</div>
				</div>
				<div class="hr-line-dashed"></div>
				<div class="form-group">
					<label class="col-xs-2 col-xs-offset-2 control-label">确认新密码</label>

					<div class="col-xs-6 col-md-4 col-md-4">
						<input type="password" class="form-control" name="passwordNew2" placeholder="确认新密码">
					</div>
				</div>

				<div class="hr-line-dashed"></div>
				<div class="form-group">
					<div class="col-xs-6 col-md-4 col-md-4 col-xs-offset-2" style="text-align: center">
						<button class="btn btn-primary " type="submit">提交</button>
					</div>
				</div>

			</form>

		</div>
	</div>


	<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
	<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
	<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>
	<script src="${pageContext.request.contextPath}/plug-in-xenon/assets/js/jquery-validate/jquery.validate.min.js"></script>
	<script src="${pageContext.request.contextPath}/plug-in-xenon/assets/js/jquery-validate/localization/messages_zh.min.js"></script>
	<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
	<script>


		//起始
		$(document).ready(function ()
		{

			//表单校验
			validateForm();

			$('.dropdown.profile-element', window.parent.document).removeClass('open').find('#dropdown-toggle').attr('aria-expanded', false);

		});



		//表单校验
		function validateForm() {

			// Validation and Ajax action
			$("form#user").validate({
				rules: {

					password: {
						required: true
					},
					passwordNew: {
						required: true
					},
					passwordNew2: {
						equalTo: "#passwordNew"
					}


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
						text: "修改成功",
						type: "success",
						showCancelButton: false,
						closeOnConfirm: false,
						confirmButtonText: "OK",
						confirmButtonColor: "#1AB394"
					}, function() {

						try{
							window.parent.updatePasswordCallback();
						}catch (e){

						}
						window.top.closeIframeHplus(window.top.personal.menu.updatePwd);
					});
					break;
				case 310003:
					layer.msg('原始密码错误.')
					break;
				default:
					layer.msg(res.msg);
					break;
			}

		}
	</script>
</div>
</body>
</html>

