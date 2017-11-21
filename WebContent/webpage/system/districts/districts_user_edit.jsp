<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<title>用户管理-修改用户</title>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">

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
		.selectwid,.selectwid option{
			width:200px;
			height:34px;
			padding:0 1%;
			text-align: center;
		}
	</style>
	<script>document.write('<script src="${pageContext.request.contextPath}/config/cg.js?' + new Date().getTime() + '"><\/script>');</script>
	<script>
		var this_data = {
			pac: '45'
		};
		var param = {}

		var $dom = {
			pac: ''
		};
		//模板
		var temp = {
			selected_option_count : '<option value="{code}">{text}</option>'
		};

	</script>
</head>

<body>


<div class="wrapper wrapper-content animated fadeInRight">


	<div class="row">
		<div class="col-sm-12">

			<div class="ibox">
				<div class="ibox-content">
					<form method="post" class="form-horizontal" action="" name="user" id="user">


						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label"></label>
							<div class="col-xs-8 col-md-4 col-md-4">
								<img src="${pageContext.request.contextPath}/images/defaultPic.png" alt="头像" class="img-circle m-t-xs img-responsive" id="headPortrait">
							</div>
						</div>

						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label">帐号</label>

							<div class="col-xs-8 col-md-4 col-md-4">
								<input type="text" class="form-control" disabled name="account" id="account" >
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label">用户名</label>

							<div class="col-xs-8 col-md-4 col-md-4">
								<input type="text" class="form-control" name="name" id="name" placeholder="用户名" >
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label">电话</label>

							<div class="col-xs-8 col-md-4 col-md-4">
								<input type="text" class="form-control" name="phone" id="phone" placeholder="电话" >
							</div>
						</div>

						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label">voipAccount</label>

							<div class="col-xs-8 col-md-4 col-md-4">
								<input type="text" class="form-control" name="voipAccount" id="voipAccount" placeholder="voipAccount" >
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label">voipPassword</label>

							<div class="col-xs-8 col-md-4 col-md-4">
								<input type="text" class="form-control" name="voipPassword" id="voipPassword" placeholder="voipPassword" >
							</div>
						</div>

						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label">imei</label>

							<div class="col-xs-8 col-md-4 col-md-4">
								<input class="form-control" type="text" name="imei" id="imei" placeholder="imei">
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label">online</label>

							<div class="col-xs-8 col-md-4 col-md-4">
								<select name="online" id="online" class="form-control">
									<option value="0">不在线</option>
									<option value="1">在线</option>
								</select>
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label" for="accountType">accountType</label>

							<div class="col-xs-8 col-md-4 col-md-4">
								<select name="accountType" id="accountType" class="form-control" >
									<option value="-1">超级管理员</option>
									<option value="1">PC</option>
									<option value="2">移动设备</option>
								</select>
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label" for="deviceType">deviceType</label>

							<div class="col-xs-8 col-md-4 col-md-4">
								<select name="deviceType" id="deviceType" class="form-control" >
									<option value="1">Android</option>
									<option value="2">IOS</option>
									<option value="3">PC</option>
								</select>
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label" for="token">token</label>

							<div class="col-xs-8 col-md-4 col-md-4">
								<textarea class="form-control" name="token" id="token" rows="4" disabled></textarea>
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label" for="jid">jid</label>

							<div class="col-xs-8 col-md-4 col-md-4">
								<textarea class="form-control" name="jid" id="jid" rows="2"></textarea>
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label" for="longitude">经度</label>

							<div class="col-xs-8 col-md-4 col-md-4">
								<input type="number" name="longitude" id="longitude" class="form-control" placeholder="longitude" >
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label" for="longitude">纬度</label>

							<div class="col-xs-8 col-md-4 col-md-4">
								<input type="number" name="latitude" id="latitude" class="form-control" placeholder="latitude" >
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label" for="deviceId">唯一码</label>

							<div class="col-xs-8 col-md-4 col-md-4">
								<input type="text" name="deviceId" id="deviceId" class="form-control" placeholder="deviceId" >
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label" for="deviceName">设备名</label>

							<div class="col-xs-8 col-md-4 col-md-4">
								<input type="text" name="deviceName" id="deviceName" class="form-control" placeholder="deviceName">
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label" for="subscriber">subscriber</label>

							<div class="col-xs-8 col-md-4 col-md-4">
								<input type="text" name="subscriber" id="subscriber" class="form-control" placeholder="subscriber">
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label" for="competence">competence</label>

							<div class="col-xs-8 col-md-4 col-md-4">
								<select name="competence" id="competence" class="form-control">
									<option value="-1">普通用户</option>
									<option value="0">省级用户</option>
									<option value="1">市级用户</option>
									<option value="2">县级用户</option>
								</select>
							</div>
						</div>
						<div class="hr-line-dashed"></div>


						<div class="col-sm-4 col-md-offset-4 col-lg-offset-4 col-xs-offset-4">
							<button class="btn btn-primary" type="submit">提交内容</button>
							<button class="btn btn-warning" type="button" onclick="window.parent.addOrUpdCallback()">关闭</button>
						</div>

					</form>
				</div>
			</div>

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

			setValue(function () {
				validateForm()
			})
		});
		
		function setValue(callback) {

			var tr_data = window.sessionStorage.getItem( 'tr_data_districts_user' );

			tr_data = JSON.parse(tr_data);
			this_data.tr_data = tr_data;

			console.log(tr_data)

			if (tr_data.headPortrait){
				$('#headPortrait').attr('src', '${pageContext.request.contextPath}/' + tr_data.headPortrait)
			}

			$('#account').val( tr_data.account );
			$('#name').val( tr_data.name );
			$('#phone').val( tr_data.phone );
			$('#voipAccount').val( tr_data.voipAccount );
			$('#voipPassword').val( tr_data.voipPassword );
			$('#online').val( tr_data.online );
			$('#accountType').val( tr_data.accountType );
			$('#deviceType').val( tr_data.deviceType );
			$('#token').val( tr_data.token );
			$('#jid').val( tr_data.jid );
			$('#longitude').val( tr_data.longitude );
			$('#latitude').val( tr_data.latitude );
			$('#deviceId').val( tr_data.deviceId );
			$('#deviceName').val( tr_data.deviceName );
			$('#subscriber').val( tr_data.subscriber );
			$('#competence').val( tr_data.competence );


			try{
				callback()
			} catch (e){

			}
		}



		//表单校验
		function validateForm() {

			// Validation and Ajax action
			$("form#user").validate({
				rules: {

					name: {
						required: true
					}
				},

				messages: {


					name: {
						required: 'Please enter your name.'
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

			formData.account = $('#account').val() || '';
			formData.name = $('#name').val() || '';
			formData.phone = $('#phone').val() || '';
			formData.voipAccount = $('#voipAccount').val() || '';
			formData.voipPassword = $('#voipPassword').val() || '';
			formData.online = $('#online').val() || '';
			formData.accountType = $('#accountType').val() || '';
			formData.deviceType = $('#deviceType').val() || '';
			formData.token = $('#token').val() || '';
			formData.jid = $('#jid').val() || '';
			formData.longitude = $('#longitude').val() || '';
			formData.latitude = $('#latitude').val() || '';
			formData.deviceId = $('#deviceId').val() || '';
			formData.deviceName = $('#deviceName').val() || '';
			formData.subscriber = $('#subscriber').val() || '';
			formData.competence = $('#competence').val() || '';
			formData.id = this_data.tr_data.id;
			formData.f = 222001;

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
					swal({
						title: "提示",
						text: "修改成功",
						type: "success",
						showCancelButton: false,
						closeOnConfirm: false,
						confirmButtonText: "OK",
						confirmButtonColor: "#1AB394"
					}, function() {
						window.parent.addOrUpdCallback();
					});
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

