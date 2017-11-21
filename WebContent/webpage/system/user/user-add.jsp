<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>用户管理</title>


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<%--<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?dds">--%>

	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">
	<link href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/plugins/chosen/chosen.css?555" rel="stylesheet">


	<style>

		.navbar-top-links li a {
			padding: 15px 10px;
		}
		.ibox{
			margin-bottom: 0;
		}

		#countries, #province, #city, #county{
			width: auto;
			display: initial;
		}

		input[type="checkbox"]{
		 position: relative;
			top:2px;
		}
		.chosen-container-single .chosen-single span{
			line-height: 30px;
		}
		#page-wrapper .datagrid-btable tr{height: 40px;}

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

		.form-horizontal .control-label {
			color: #000;
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
		.departbox{
			border: 1px solid #e1e1e1;
			position: relative;
			padding: 2px 20px 9px 5px;
			border-radius: 3px;
			display: inline-block;
			height: 21px;
			color: #000;
			/* background-color: #e4e4e4; */
			font-size: 12px;
			background: -webkit-linear-gradient(#f4f4f4 20%, #f0f0f0 50%, #e8e8e8 52%, #eeeeee 100%);
		}
		.none{
			display: none;
		}
	</style>
	<script>document.write('<script src="${pageContext.request.contextPath}/config/cg.js?' + new Date().getTime() + '"><\/script>');</script>
	<script>document.write('<script src="'+cg_vte.iconfont_js+'?' + new Date().getTime() + '"><\/script>')</script>
	<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
	<script>
		var this_data = {
			pac:  '${LOCAL_CLINET_USER.pac }',
		};
	</script>
</head>

<body>

	<div class="">

		<div class="row">
			<div class="col-sm-12">

				<div class="ibox">
					<div class="ibox-content" style="padding-bottom: 100px;">

						<form method="post" class="form-horizontal" action="" name="user" id="user">


							<div class="form-group">
								<label class="col-sm-2 control-label">头像</label>
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

								<label class="col-sm-2 control-label">帐号</label>

								<div class="col-sm-4" style="position: relative;">
									<input type="text" class="form-control" name="account" id="account" placeholder=" ">
									<svg class="icon" aria-hidden="true" style="position:absolute;top:7px;right:18px;z-index: 99999;color:red;">
										<use xlink:href="#icon-icon_bitianxiang"></use>
									</svg>
								</div>


							</div>



							<div class="form-group">
								<label for="province" class="col-sm-2  control-label">所属行政区</label>

								<div class="col-sm-10">
									<select class="form-control none" id="countries"></select>
									<select class="form-control" id="province" onchange="pac_change(this)"></select>
									<select class="form-control" id="city" onchange="pac_change(this)"></select>
									<select class="form-control" id="county" onchange="pac_change(this)"></select>
								</div>
							</div>



							<div class="form-group">
								<label class="col-sm-2  control-label">登录密码</label>
								<div class="col-sm-4" style="position: relative;">
									<input type="password" class="form-control" name="password" id="password" placeholder="">
									<span class="help-block m-b-none"></span>
									<svg class="icon" aria-hidden="true" style="position:absolute;top:7px;right:18px;z-index: 99999;color:red;">
										<use xlink:href="#icon-icon_bitianxiang"></use>
									</svg>
								</div>
								<label class="col-sm-2  control-label">确认登录密码</label>

								<div class="col-sm-4" style="position: relative;">
									<input type="password" class="form-control" name="password2" placeholder="">
									<svg class="icon" aria-hidden="true" style="position:absolute;top:7px;right:18px;z-index: 99999;color:red;">
										<use xlink:href="#icon-icon_bitianxiang"></use>
									</svg>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-2  control-label">用户名</label>

								<div class="col-sm-4" style="position: relative;">
									<input type="text" class="form-control" name="name" placeholder="">
									<svg class="icon" aria-hidden="true" style="position:absolute;top:7px;right:18px;z-index: 99999;color:red;">
										<use xlink:href="#icon-icon_bitianxiang"></use>
									</svg>
								</div>

							</div>

							<div class="form-group">
								<label class="col-sm-2  control-label">所属角色</label>

								<div class="col-sm-4">
									<select id="roles" data-placeholder="点击选择角色" class="  form-control"  multiple tabindex="4">
										<option value="">请选择角色</option>
									</select>
								</div>

								<label class="col-sm-2  control-label">所属部门</label>

								<div class="col-sm-4" style="position: relative">
									<div class="form-control" id="depart_name">
										<span onclick="depart()" id="depart_choice">点击选所属部门</span>
										<div class="departbox none">
											<span><depart id="depart">吃饭</depart><a onclick="clearDepart()" style="position: absolute;right: 5px;color: #929191;bottom: 0px;">X</a></span>
										</div>
									</div>
									<%--<input type="text" class="form-control" placeholder="点击点选所属部门" >--%>
									<input type="hidden" id="depart_id" >

								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-2  control-label">电话</label>

								<div class="col-sm-4"  style="position: relative;">
									<input type="text" class="form-control" name="phone" placeholder="电话">
									<svg class="icon" aria-hidden="true" style="position:absolute;top:7px;right:18px;z-index: 99999;color:red;">
										<use xlink:href="#icon-icon_bitianxiang"></use>
									</svg>
								</div>
								<label class="col-sm-2  control-label">启用状态</label>

								<div class="col-sm-4">
									<div class="radio fl mr20">
										<label>
											<input type="radio" name="state" value="1">
											禁用
										</label>
									</div>
									<div class="radio fl mr20">
										<label>
											<input type="radio" name="state" value="0" checked>
											启用
										</label>
									</div>

								</div>
							</div>


							<div class="subtnfix" style="position: fixed;bottom:0px;z-index: 1000;width: 100%;background: #fff;padding:20px 0;    border-top: 1px solid #dedede;">
								<div class="  col-xs-offset-10 ">
									<button class="btn btn-primary" type="submit" id="submit">提交</button>
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


		<script>




			var $dom = {};
			$(document).ready(function ()
			{

				$dom.countries = $('#countries');
				$dom.province = $('#province');
				$dom.city = $('#city');
				$dom.county = $('#county');

				this_data.pac_level = formatPacLevel(this_data.pac);

				var data_tr = window.sessionStorage.getItem('data_tr');

				this_data.data_tr = JSON.parse(data_tr);

				peggingPac(function(){
					initPac(this_data.pac, function () {

						initRole(function(){
							validateForm()
						})

					})
				});



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
						sort: 'sorting',
						order: 'desc'
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

						$roles.append($select.html()).chosen({disable_search_threshold:10});

						try{
							callback()
						} catch(e){

						}

					}
				})
			}


			function peggingPac(callback) {
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

				$("#submit").attr("disabled", true).html("提交中...");

				var formData = new FormData(form);
				formData.append("pac", this_data.pac);

				if (!$('#roles').val() || $('#roles').val().length < 1){
//					layer.msg('请选择所属角色')
//					return ;
				} else {
					var roles = $('#roles').val();
					for(var i in roles){
						if (roles.hasOwnProperty(i)){
							formData.append("roles["+i+"].id", roles[i]);
						}
					}

				}
				if (!$('#depart_id').val() || $('#depart_id').val().length < 1){
//					layer.msg('请选择所属部门')
//					return ;
				} else {
					formData.append("depart.id", $('#depart_id').val());
				}




				var files = document.getElementById("mediaFile");
				files = files.files;
				for (var i in files){
					formData.append("file",  files[i]);
				}


				$.ajax({
					url: cg_vte.path + "if/userController/addUser",
					type: 'post',
					dataType: 'text',
					processData: false,
					contentType: false,
					data: formData,
					success: function (data) {

						$("#submit").removeAttr("disabled");

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
							text: "添加成功",
							type: "success",
							showCancelButton: false,
							closeOnConfirm: false,
							confirmButtonText: "OK",
							confirmButtonColor: "#1AB394"
						}, function() {


                            window.parent.initDataGrid()

                            var index=parent.layer.getFrameIndex(window.name);

                            window.parent.layer.close(index);

						});
						break;
					case 20000:
						layer.msg('用户名被占用');
						return;
					default:
						layer.msg(res.msg);
						break;
				}

			}

			function pac_change(dom) {
				this_data.pac = dom.value;

				initPac(this_data.pac, function(){
					//节点树加载完成后加载所有该地区的用户
					initDataGrid();
				});
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


			function readyFile(files) {

				$('#medit_file_box').empty();
				$('#medit_file_input').val('');
				var fileNames = '';
				files = files.files;
				for (var i in files){
					if (files.hasOwnProperty(i)){
						if (files[i].type.indexOf('video') >= 0){
							$('#medit_file_box').append('<hr/><div class="medit_file_value_box medit_file_video"><video src="'+getObjectURL(files[i])+'" controls="controls">您的浏览器不支持 video 标签。</video><p>'+files[i].name+'</p></div>')
						} else if(files[i].type.indexOf('audio') >= 0){
							$('#medit_file_box').append('<hr/><div class="medit_file_value_box medit_file_audio"><audio src="'+getObjectURL(files[i])+'" controls="controls">您的浏览器不支持 audio 标签。</audio><p>'+files[i].name+'</p></div>')
						} else if(files[i].type.indexOf('image') >= 0){
							$('#medit_file_box').append('<hr/><div class="medit_file_value_box medit_file_image"><img src="'+getObjectURL(files[i])+'"><p>'+files[i].name+'</p></div>')
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

			function depart() {
				layer.open({
					type: 2,
					title: '',
					closeBtn: 0,
					maxmin: false, //开启最大化最小化按钮
					area: ['80%', '80%'],

					content: cg_vte.path + 'webpage/system/user/user_add_choice_depart.jsp',
					fix: false,
					scrollbar: false,
				});
			}

			function clearDepart() {
				$('#depart_id').val("");
				$('#depart_name').val("");
				$('#depart_choice').removeClass('none');
				$('#depart').parents('div.departbox').addClass('none');
			}

		</script>
	</div>
</body>
</html>

