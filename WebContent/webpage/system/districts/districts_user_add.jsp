<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>添加行政单位用户</title>


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?dds">
	<link href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/plugins/chosen/chosen.css" rel="stylesheet">

	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">

	<style>

		.navbar-top-links li a {
			padding: 15px 10px;
		}
		.ibox{
			margin-bottom: 0;
		}

		#city,#county{
			width: 45%;
			margin-right: 5px;
		}

		#page-wrapper .datagrid-btable tr{height: 40px;}

	</style>
	<script>document.write('<script src="${pageContext.request.contextPath}/config/cg.js?' + new Date().getTime() + '"><\/script>');</script>
	<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
	<script>
		var this_data = {
			pac:  formatPacUnderline(cg_vte.initPac),
		};
	</script>
</head>

<body>

	<div class="wrapper wrapper-content animated fadeInRight">

		<div class="row">
			<div class="col-sm-12">

				<form method="post" class="form-horizontal" action="" name="user" id="user">

					<div class="form-group">
						<label class="col-xs-2 col-xs-offset-2 control-label">角色</label>

						<div class="col-xs-8 col-md-4 col-md-4">
							<select id="roles" data-placeholder="选择角色" class="chosen-select" multiple style="width:350px;" tabindex="4">
								<option value="">请选择角色</option>
							</select>
						</div>
					</div>

					<div class="hr-line-dashed"></div>
					<div class="form-group">
						<label class="col-xs-2 col-xs-offset-2 control-label">帐号</label>

						<div class="col-xs-8 col-md-4 col-md-4">
							<input type="text" class="form-control" name="account" id="account" placeholder="登陆帐号">
						</div>
					</div>

					<div class="hr-line-dashed"></div>
					<div class="form-group">
						<label class="col-xs-2 col-xs-offset-2 control-label">密码</label>
						<div class="col-xs-8 col-md-4 col-md-4">
							<input type="password" class="form-control" name="password" id="password" placeholder="登陆密码">
							<span class="help-block m-b-none"></span>
						</div>
					</div>
					<div class="hr-line-dashed"></div>
					<div class="form-group">
						<label class="col-xs-2 col-xs-offset-2 control-label">密码</label>

						<div class="col-xs-8 col-md-4 col-md-4">
							<input type="password" class="form-control" name="password2" placeholder="确认密码">
						</div>
					</div>
					<div class="hr-line-dashed"></div>
					<div class="form-group">
						<label class="col-xs-2 col-xs-offset-2 control-label">用户名</label>

						<div class="col-xs-8 col-md-4 col-md-4">
							<input type="text" class="form-control" name="name" placeholder="用户名">
						</div>
					</div>

					<div class="hr-line-dashed"></div>
					<div class="form-group">
						<label class="col-xs-2 col-xs-offset-2 control-label">调度台帐号</label>

						<div class="col-xs-8 col-md-4 col-md-4">
							<input type="text" class="form-control" name="voipAccount" placeholder="调度台帐号">
						</div>
					</div>

					<div class="hr-line-dashed"></div>
					<div class="form-group">
						<label class="col-xs-2 col-xs-offset-2 control-label">调度台密码</label>

						<div class="col-xs-8 col-md-4 col-md-4">
							<input type="text" class="form-control" name="voipPassword" placeholder="调度台密码">
						</div>
					</div>

					<div class="hr-line-dashed"></div>
					<div class="form-group">
						<label class="col-xs-2 col-xs-offset-2 control-label">电话</label>

						<div class="col-xs-8 col-md-4 col-md-4">
							<input type="text" class="form-control" name="phone" placeholder="电话">
						</div>
					</div>


					<div class="hr-line-dashed"></div>
					<div class="col-sm-4 col-md-offset-7 col-lg-offset-4 col-xs-offset-4">
						<button class="btn btn-primary" type="submit">提交内容</button>
						<button class="btn btn-warning" type="button" onclick="window.parent.addOrUpdCallback(true)">关闭</button>
					</div>

				</form>

			</div>
		</div>


		<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
		<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
		<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
		<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/plugins/chosen/chosen.jquery.js"></script>
		<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>
		<script src="${pageContext.request.contextPath}/plug-in-xenon/assets/js/jquery-validate/jquery.validate.min.js"></script>
		<script src="${pageContext.request.contextPath}/plug-in-xenon/assets/js/jquery-validate/localization/messages_zh.min.js"></script>

		<script>

			var $dom = {};

			//模板
			var temp = {
				selected_option_count : '<option value="{code}">{text}</option>'
			};


			//起始
			$(document).ready(function ()
			{

				var data_tr = window.sessionStorage.getItem('data_tr');

				this_data.data_tr = JSON.parse(data_tr);

				initRole(function () {
					validateForm();
				})


			});

			function initRole(callback) {

				var $roles = $('#roles');
				var format_option = '<option value="{id}" hassubinfo="true">{roleName}</option>';


				$.ajax({
					url: cg_vte.service,
					type: 'post',
					dataType: 'text',
					data: {
						f: 930001,
						page: 1,
						rows: 1000,
					},
					success: function (data) {

						data = JSON.parse(data);
						console.log(data)

						var rows = data.rows;

						var $select = $('<select>');

						for (var i in rows){
							if (rows.hasOwnProperty(i)){
								$select.append(format_option.format({
									id: rows[i].id,
									roleName: rows[i].roleName,
								}))
							}
						}

						$roles.append($select.html()).chosen({max_selected_options: 5,disable_search_threshold:10});

						try{
							callback()
						} catch(e){

						}

					}
				})
			}



			//表单校验
			function validateForm() {

				// Validation and Ajax action
				$("form#user").validate({
					rules: {
						account: {
							required: true
						},

						password: {
							required: true
						},
						password2: {
							equalTo: '#password'
						},
						name: {
							required: true
						}
					},

					messages: {
						account: {
							required: '请输入你的帐号.'
						},

						password: {
							required: '请输入你的密码.'
						},

						name: {
							required: '请输入你的用户名.'
						}
					},

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

				var formData = {};

				var pac = window.sessionStorage.getItem('pac');

				formData.account = $form.find('input[name="account"]').val();
				formData.password = $form.find('input[name="password"]').val() ;
				formData.name = $form.find('input[name="name"]').val() ;
				formData.voipAccount = $form.find('input[name="voipAccount"]').val() ;
				formData.voipPassword =  $form.find('input[name="voipPassword"]').val() ;
				formData.phone = $form.find('input[name="phone"]').val() ;
				formData.f = 221001;
				formData.pac =   pac;

				$.ajax({
					url: cg_vte.service,
					type: 'post',
					dataType: 'text',
					data: formData,
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
						submit_role(res.obj);
						submit_depart(res.obj);
						break;
					case 20000:
						layer.msg('用户名被占用');
						return;
					default:
						layer.msg(res.msg);
						break;
				}

			}
			function submit_role(obj) {

				var formData = {};

				console.log(obj);
				var role_ids = $('#roles').val();
				for (var i in role_ids){
					if (role_ids.hasOwnProperty(i) ){
						formData["roleUsers["+i+"].role.id"] = role_ids[i];
						formData["roleUsers["+i+"].user.id"] = obj.id;
					}
				}

				formData.id = obj.id;

				$.ajax({
					url: cg_vte.path + "userController.do?setUserRole",
					type: 'post',
					dataType: 'text',
					data: formData,
					success: function (data) {
						data = JSON.parse(data);

						if (data.status == 10000){
							change_depart_role_submit()
							return;
						}

						layer.msg('保存角色信息失败');

					}
				})
			}

			function submit_depart(obj){
				var formData = {};

				formData["departUsers[0].depart.id"] = this_data.data_tr.id;
				formData["departUsers[0].user.id"] = obj.id;

				formData.id = obj.id;

				$.ajax({
					url: cg_vte.path + "userController.do?setUserDeparts",
					type: 'post',
					dataType: 'text',
					data: formData,
					success: function (data) {
						data = JSON.parse(data);

						if (data.status == 10000){
							change_depart_role_submit()
							return;
						}

						layer.msg('保存部门信息失败');

					}
				})


			}

			var depart_role_status = 0;
			function change_depart_role_submit() {
				if (depart_role_status > 1){
					depart_role_status++;
					return;
				}

				swal({
					title: "提示",
					text: "添加成功",
					type: "success",
					showCancelButton: false,
					closeOnConfirm: false,
					confirmButtonText: "OK",
					confirmButtonColor: "#1AB394"
				}, function() {
					window.parent.addOrUpdCallback();
				});
			}

		</script>
	</div>
</body>
</html>

