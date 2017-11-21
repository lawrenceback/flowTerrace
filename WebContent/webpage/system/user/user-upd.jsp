<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<link href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/plugins/chosen/chosen.css" rel="stylesheet">

	<style>


		.piaochecked {
			width: 17px;
			height: 17px;
			float: left;
			cursor: pointer;
			margin-left: 10px;
			text-align: center;
			background-repeat: no-repeat;
			background-position: 0 0;
		}

		.on_check {
			background-position: 0 -21px;
		}
		.radioclass {
			opacity: 0;
			cursor: pointer;
			-ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
			filter: alpha(opacity=0);
		}
		input[type=radio] {
			margin-top: 2px!important;
		}

		.navbar-top-links li a {
			padding: 15px 10px;
		}
		.ibox{
			margin-bottom: 0;
		}

		#page-wrapper .datagrid-btable tr{height: 40px;}
		#countries, #province, #city, #county{
			width: auto;
			display: initial;
		}
		code{
			padding:10px 13px;
			margin-right:10px;
		}
		.form-horizontal .control-label {
			color: #000;
		}



		#medit_file_box img,#medit_file_box_append img{
			width: inherit;
			max-width: 100px;
		}
		#medit_file_box audio,#medit_file_box_append audio{
			width: 100%;
		}

		#medit_file_box .medit_file_value_box,#medit_file_box_append .medit_file_value_box{
			padding: 10px;
			background-color: #f9f9f9;
			margin-bottom: 20px;
			border-radius: 3px;
			box-shadow: 0 0 10px #c1c1c1;
		}
		#medit_file_box .medit_file_value_box,#medit_file_box_append .medit_file_value_box{
			border: 1px solid transparent;
			text-align: center;
		}
		#medit_file_box .medit_file_value_box:hover,#medit_file_box_append .medit_file_value_box:hover{
			border: 1px dashed #a9a9a9;
		}
		.chosen-container-single .chosen-single span{
			line-height: 30px;
		}
		.icon {
			/* 通过设置 font-size 来改变图标大小 */
			width: 1.5em; height: 1.5em;
			/* 图标和文字相邻时，垂直对齐 */
			vertical-align: -0.15em;
			/* 通过设置 color 来改变 SVG 的颜色/fill */
			fill: currentColor;
			/* path 和 stroke 溢出 viewBox 部分在 IE 下会显示
               normalize.css 中也包含这行 */
			overflow: hidden;
		}

	</style>
	<script>document.write('<script src="${pageContext.request.contextPath}/config/cg.js?' + new Date().getTime() + '"><\/script>');</script>
	<script>document.write('<script src="'+cg_vte.iconfont_js+'?' + new Date().getTime() + '"><\/script>')</script>
	<script>
		var this_data = {
			user_pac: '${LOCAL_CLINET_USER.pac }',
			pac:  '',
		};
		var param = {}

		var $dom = {};

		var roles_db = [];

		<c:forEach var="role" items="${user.roles}" varStatus="status">
			roles_db.push('${role.id}')
		</c:forEach>

		console.log(roles_db);
	</script>
</head>

<body>


<div class="" style="padding-bottom:80px">


	<div class="row">
		<div class="col-sm-12">

			<div class="ibox">
				<div class="ibox-content  ">
					<form method="post" class="form-horizontal" action="" name="user" id="user">


						<div style="display: none">

							id<input type="text" name="id" id="id" value="${user.id}">
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label" style="line-height: 80px;">当前头像</label>
							<div class="col-sm-4">
								<img src="${pageContext.request.contextPath}/${user.headPortrait}" alt="头像" style="width:80px;margin:0 auto;border-radius:50%;" class="m-t-xs img-responsive" >
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">修改头像</label>
							<div class="col-sm-4">
								<style>	#mediaFile{opacity: 0;position: absolute;cursor: pointer}#medit_file_input{overflow: hidden; white-space: nowrap; text-overflow: ellipsis;}</style>
								<input type="file" class="form-control" id="mediaFile" accept="image/*" onchange="readyFile(this)" placeholder="点击选择头像">
								<div class="input-append input-group" onclick="checkfile()">
										<span class="input-group-btn">
											<button class="btn btn-white" type="button">选择文件</button>
										</span>
									<input class="input-large form-control" type="text" id="medit_file_input">
								</div>

								<div id="medit_file_box">

								</div>
							</div>

						</div>



						<div class="form-group">
							<label for="province" class="col-sm-2 control-label">所属行政区</label>

							<div class="col-sm-10">
								<select class="form-control margin-10" id="countries"></select>
								<select class="form-control margin-10" id="province" ></select>
								<select class="form-control margin-10" id="city" onchange="changeCity(this)"></select>
								<select class="form-control" id="county" onchange="changeCounty(this)"></select>
							</div>
						</div>


						<div class="form-group">
							<label class="col-sm-2 control-label">帐号</label>

							<div class="col-sm-4" style="position: relative">
								<input type="text" class="form-control" name="account" placeholder="${user.account}" disabled>
								<svg class="icon" aria-hidden="true" style="position:absolute;top:7px;right:18px;z-index: 99999;color:red;">
									<use xlink:href="#icon-icon_bitianxiang"></use>
								</svg>
							</div>

							<label class="col-sm-2 control-label">用户名</label>

							<div class="col-sm-4" style="position: relative">
								<input type="text" class="form-control" name="name" placeholder="用户名" value="${user.name }">
								<svg class="icon" aria-hidden="true" style="position:absolute;top:7px;right:18px;z-index: 99999;color:red;">
									<use xlink:href="#icon-icon_bitianxiang"></use>
								</svg>
							</div>
						</div>


						<div class="form-group">
							<label class="col-sm-2 control-label">登录密码</label>

							<div class="col-sm-4" style="position: relative">
								<input type="password" class="form-control" name="password" id="password" >
								<svg class="icon" aria-hidden="true" style="position:absolute;top:7px;right:18px;z-index: 99999;color:red;">
									<use xlink:href="#icon-icon_bitianxiang"></use>
								</svg>
							</div>

							<label class="col-sm-2 control-label">确认登录密码</label>

							<div class="col-sm-4" style="position: relative">
								<input type="password" class="form-control" name="password2" id="password2" value="">
								<svg class="icon" aria-hidden="true" style="position:absolute;top:7px;right:18px;z-index: 99999;color:red;">
									<use xlink:href="#icon-icon_bitianxiang"></use>
								</svg>
							</div>
							<div class="col-xs-10 col-xs-offset-2">
								<%--<span class="help-block m-b-none">提示：此处显示的密码为密文，不会影响用户正常登陆！</span>--%>
								<%--<span class="help-block m-b-none" style="display:inline-block;padding-left: 40px;">需要更改用户密码，直接输入新的用户新密码</span>--%>
							</div>
						</div>



						<div class="form-group">

							<label class="col-sm-2 control-label">电话</label>

							<div class="col-sm-4" style="position: relative">
								<input type="text" class="form-control" name="phone" placeholder="电话" value="${user.phone }">
								<svg class="icon" aria-hidden="true" style="position:absolute;top:7px;right:18px;z-index: 99999;color:red;">
									<use xlink:href="#icon-icon_bitianxiang"></use>
								</svg>
							</div>

							<label class="col-sm-2  control-label">用户类型</label>

							<div class="col-sm-4">
								<select class="form-control" name="accountType" >
									<option value="1" ${user.accountType == 1 ? "selected" : ""}>系统用户</option>
								</select>
							</div>

						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">所属角色</label>

							<div class="col-sm-10">
								<select id="roles" multiple style="width: 300px"></select>
							</div>

						</div>


						<div class="form-group">
							<label class="col-sm-2 control-label">所属部门</label>

							<div class="col-sm-10"  style="display: flex">
								<div class="form-control" style="border: transparent;" >
									<div id="role_box" style="display: inline">
										<c:forEach var="depart" items="${user.departs}" varStatus="status">
											${status.index > 0 ? " —&nbsp&nbsp " : ""} <code class="margin-right:0px;">${depart.depart_name }</code>
										</c:forEach>
									</div>
									<input type="hidden" name="depart.id" id="depart_id">
									<a onclick="depart()" class="btn btn-danger">重新选择</a>
								</div>
							</div>
						</div>



						<div class="form-group">



							<label class="col-sm-2 control-label">启用状态</label>
							<div class="col-sm-4">
								<div class="radio fl mr20">
									<label>
										<input type="radio" name="state" value="1" ${user.state == 1 ? "checked" : ""} >
										禁用
									</label>
								</div>
								<div class="radio fl mr20">
									<label>
										<input type="radio" name="state" value="0" ${user.state == 0 ? "checked" : "" } >
										启用
									</label>
								</div>

							</div>
						</div>







						<div class="subtnfix" style="position: fixed;bottom:0px;z-index: 1000;width: 100%;background: #fff;padding:20px 0;">
							<div class="  col-xs-offset-9 ">
								<button class="btn btn-primary" type="submit" id="submit">提交内容</button>
								<button class="btn btn-warning" onclick="window.parent.layer.closeAll();">取消</button>
							</div>
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
	<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/plugins/chosen/chosen.jquery.js"></script>
	<script src="${pageContext.request.contextPath}/plug-in-xenon/assets/js/jquery-validate/jquery.validate.min.js"></script>
	<script src="${pageContext.request.contextPath}/plug-in-xenon/assets/js/jquery-validate/localization/messages_zh.min.js"></script>
	<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
	<script src="${pageContext.request.contextPath}/plug-in/tools/getUrlParam.js"></script>
	<script>


		//起始
		$(document).ready(function ()
		{

			param.id = $.getUrlParam("id");


			$dom.countries = $('#countries');
			$dom.province = $('#province');
			$dom.city = $('#city');
			$dom.county = $('#county');

			this_data.pac_level = formatPacLevel(this_data.user_pac);

			initRole()

			$('input[type="password"]').each(function (index) {
				var $this = $(this);
				var password = '${user.password}';
				var str = '';
				for(var i in password){
					str += '*';
				}
				$this.attr('placeholder', str);
			})


			peggingPac(this_data.user_pac, function(){
				initPac(this_data.user_pac, function () {



					this_data.pac = '${user.pac }';

					this_data_pac()

					validateForm();

				})
			});


		});


		function peggingPac(pac, callback) {
			$.ajax({
				url: cg_vte.service,
				type: 'post',
				dataType: 'text',
				data: {
					f: 210006,
					code: pac
				},
				success: function (data) {
					data = JSON.parse(data);
					if (data.status == 10000) {
						var obj = data.obj;
						for(var i in obj){
							if (obj.hasOwnProperty(i)){

								switch (formatPacLevel(obj[i].code)){
									case 0:
										$dom.countries.html('<option>'+obj[i].name+'</option>').attr('disabled', 'disabled');
										break;
									case 1:
										$dom.province.html('<option>'+obj[i].name+'</option>').attr('disabled', 'disabled');
										break;
									case 2:
										$dom.city.html('<option>'+obj[i].name+'</option>').attr('disabled', 'disabled')
										break;
									case 3:
										$dom.county.html('<option>'+obj[i].name+'</option>').attr('disabled', 'disabled')
										break;
								}

							}
						}
					}
					try {
						callback()
					} catch (e) {

					}

				}
			})
		}


		function this_data_pac() {
			$.ajax({
				url: cg_vte.service,
				type: 'post',
				dataType: 'text',
				data: {
					f: 210006,
					code: this_data.pac
				},
				success: function (data) {
					data = JSON.parse(data);
					if (data.status == 10000) {
						var obj = data.obj;

						for(var i in obj){

							if (obj.hasOwnProperty(i)){

								switch (formatPacLevel(obj[i].code)){
									case 1:
										if (this_data.pac_level == 0){
											$dom.province.val(obj[i].code);
										} else {
											$dom.province.append('<option value="' + obj[i].code + '" selected>'+obj[i].name+'</option>');
										}
										break;
									case 2:
										if (this_data.pac_level == 1){
											$dom.city.val(obj[i].code);
										} else {
											$dom.city.append('<option value="' + obj[i].code + '" selected>'+obj[i].name+'</option>');
										}
										break;
									case 3:
										if (this_data.pac_level == 2){
											$dom.city.val(obj[i].code);
										} else {
											$dom.county.append('<option value="' + obj[i].code + '" selected>'+obj[i].name+'</option>');
										}
										break;
								}


							}


						}
					}
				}
			})
		}


		//工具-拉取pac数据
		function initPac(id, callback) {
			$.ajax({
				url: "/if/serviceController/action",
				data: {
					f: 210002,
					pac: id,
					fetchType: 1,
				},
				dataType: 'text',
				success: function (data) {

					data = JSON.parse(data);

					var children = data.rows;

					for (var i in children) {
						if (children.hasOwnProperty(i)) {
							if (i < 1){
								switch (formatPacLevel(children[i].code)){
									case 0:
										$dom.countries.show()
										$dom.province.append('<option value="' + children[i].code + '">全部</option>');
										$dom.city.empty().append('<option value="">全部</option>');
										$dom.county.empty().append('<option value="">全部</option>');
										break;
									case 1:
										$dom.province.append('<option value="' + children[i].code + '">全部</option>');
										$dom.city.empty().append('<option value="">全部</option>');
										$dom.county.empty().append('<option value="">全部</option>');
										break;
									case 2:
										$dom.city.append('<option value="' + children[i].code + '">全部</option>');
										$dom.county.empty().append('<option value="">全部</option>');
										break;
									case 3:
										$dom.county.append('<option value="' + children[i].code + '">全部</option>');
										break;
								}
								continue;
							}

							switch ( formatPacLevel(children[i].code) ){
								case 1:
									$dom.province.append('<option value="' + children[i].code + '">'+children[i].name+'</option>');
									break;
								case 2:
									$dom.city.append('<option value="' + children[i].code + '">'+children[i].name+'</option>');
									break;
								case 3:
									$dom.county.append('<option value="' + children[i].code + '">'+children[i].name+'</option>');
									break;
							}

						}
					}

					try{
						callback();
					} catch (e){

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
                    },
                    password2: {
                        equalTo: '#password'
                    },
                    voipAccount: {
                        required: true
                    },
                    voipPassword: {
                        required: true
                    },
                    phone: {
                        required: true
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
                    password2: {
                        equalTo: "两次密码不一致"
                    },
                    name: {
                        required: '请输入你的用户名.'
                    },
                    voipAccount: {
                        required: '请输入你的调度帐号.'
                    },
                    voipPassword: {
                        required: '请输入你的调度密码.'
                    },
                    phone: {
                        required: '请输入你的电话.'
                    },
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

			var formData = new FormData(form);
			formData.append("pac", this_data.pac);

			var roles = $('#roles').val();
			for(var i in roles){
				if (roles.hasOwnProperty(i)){
					formData.append("roles["+i+"].id", roles[i]);
				}
			}


			var files = document.getElementById("mediaFile");
			files = files.files;
			for (var i in files){
				if (files.hasOwnProperty(i)){
					formData.append("headPortrait_file",  files[i]);
				}
			}

			$.ajax({
				url: cg_vte.path + "if/userController/edit",
				type: 'post',
				dataType: 'text',
				data: formData,
				processData: false,
				contentType: false,
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

		function depart() {
			var tr_data = {
				id: '${user.id }',
			};
			window.sessionStorage.setItem('user_upd_choice_departs', JSON.stringify(tr_data));
			layer.open({
				type: 2,
				title: '点击选择部门',
				closeBtn:1,
				maxmin: false, //开启最大化最小化按钮
				area: ['80%', '90%'],

				content: cg_vte.path + 'webpage/system/user/user_upd_choice_depart.jsp',
				fix: false,
				scrollbar: false,
			});
		}



		function readyFile(files) {

			$('#medit_file_box').empty();
			$('#medit_file_input').val('');
			var fileNames = '';
			files = files.files;
			for (var i in files){
				if (files.hasOwnProperty(i)){
					if (files[i].type.indexOf('video') >= 0){
						$('#medit_file_box').append('<div class="medit_file_value_box medit_file_video"><video src="'+getObjectURL(files[i])+'" controls="controls">您的浏览器不支持 video 标签。</video><p>'+files[i].name+'</p></div>')
					} else if(files[i].type.indexOf('audio') >= 0){
						$('#medit_file_box').append('<div class="medit_file_value_box medit_file_audio"><audio src="'+getObjectURL(files[i])+'" controls="controls">您的浏览器不支持 audio 标签。</audio><p>'+files[i].name+'</p></div>')
					} else if(files[i].type.indexOf('image') >= 0){
						$('#medit_file_box').append('<div class="medit_file_value_box medit_file_image"><img src="'+getObjectURL(files[i])+'"><p>'+files[i].name+'</p></div>')
					}

					if (i == 0){
						fileNames =  files[i].name;
						continue;
					}
					fileNames += "," + files[i].name;
				}
			}
			$('#medit_file_input').val(fileNames)

		}
		//获取文件地址，显示预览用
		var getObjectURL = function (file) {
			var url = null;
			if (window.createObjectURL != undefined) { // basic
				url = window.createObjectURL(file);
			} else if (window.URL != undefined) { // mozilla(firefox)
				url = window.URL.createObjectURL(file);
			} else if (window.webkitURL != undefined) { // webkit or chrome
				url = window.webkitURL.createObjectURL(file);
			}
			return url;
		};
		function checkfile() {
			$('#mediaFile').click()
		}




		function initRole(callback) {

			var $roles = $('#roles');
			var format_option = '<option value="{id}" hassubinfo="true"  {selected}>{roleName}</option>';


			$.ajax({
				url: cg_vte.service,
				type: 'post',
				dataType: 'text',
				data: {
					f: 930001,
					page: 1,
					rows: 1000,
					sort: 'sorting',
					order: 'asc'
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
								selected: function(){
									for(var j in roles_db){
										if (roles_db.hasOwnProperty(j)){
											if (roles_db[j] == rows[i].id){
												return "selected"
											}
										}
									}
								}
							}))
						}
					}

					$roles.append($select.html()).chosen({disable_search_threshold:10});

					try{
						callback()
					} catch(e){

					}

				}
			})
		}

	</script>
</div>
</body>
</html>

