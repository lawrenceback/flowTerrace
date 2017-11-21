<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<title>用户管理-查看用户</title>

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
		#province,#city, #county{
			width: auto;
			display: initial;
		}

		.form-control[readonly]{
			background: #fff!important;
		}

		::-webkit-input-placeholder { /* WebKit browsers */
			color:    #A9A9A9!important;
		}
		:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
			color:    #A9A9A9!important;
			opacity:  1;
		}
		::-moz-placeholder { /* Mozilla Firefox 19+ */
			color:    #A9A9A9!important;
			opacity:  1;
		}
		:-ms-input-placeholder { /* Internet Explorer 10+ */
			color:    #A9A9A9!important;
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
	</style>
	<script>document.write('<script src="${pageContext.request.contextPath}/config/cg.js?' + new Date().getTime() + '"><\/script>');</script>
	<script>document.write('<script src="'+cg_vte.iconfont_js+'?' + new Date().getTime() + '"><\/script>')</script>
	<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
	<script>
		var this_data = {
			user_pac: '${LOCAL_CLINET_USER.pac }',
			pac:  '',
		};
		var param = {}

		var $dom = {};

	</script>
</head>

<body>


<div class="gray-bg">


	<div class="row">
		<div class="col-sm-12">

			<div class="ibox">
				<div class="ibox-content">
					<form class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-3 control-label"> </label>
							<div class="col-sm-3" >
								<img src="${pageContext.request.contextPath}/${user.headPortrait}" alt="" style="width:80px;margin:0 auto;border-radius:50%;" class="m-t-xs img-responsive" >
							</div>
						</div>

						<div class="form-group">

							<label class="col-sm-3 control-label">所属行政区</label>
							<div class="col-sm-3">
								<p class="form-control" style="border: transparent" id="pac"></p>
							</div>
							<label class="col-sm-2 control-label" >帐号</label>
							<div class="col-sm-3" style="position: relative;">
								<input class="form-control" type="text" placeholder="22" readonly id="account"/>
								<svg class="icon" aria-hidden="true" style="position:absolute;top:7px;right:18px;z-index: 99999;color:red;">
									<use xlink:href="#icon-icon_bitianxiang"></use>
								</svg>
							</div>


						</div>

						<div class="form-group">

							<label class="col-sm-3 control-label">用户名</label>
							<div class="col-sm-3">
								<input class="form-control" id="name" type="text" readonly/>
								<svg class="icon" aria-hidden="true" style="position:absolute;top:7px;right:18px;z-index: 99999;color:red;">
									<use xlink:href="#icon-icon_bitianxiang"></use>
								</svg>
							</div>

							<label class="col-sm-2 control-label">用户类型</label>
							<div class="col-sm-3" style="position: relative">
								<input class="form-control" id="accountType" type="text" readonly >
								<svg class="icon" aria-hidden="true" style="position:absolute;top:7px;right:18px;z-index: 99999;color:red;">
									<use xlink:href="#icon-icon_bitianxiang"></use>
								</svg>
							</div>

						</div>

						<div class="form-group">

							<label class="col-sm-3 control-label">所属角色</label>
							<div class="col-sm-3">
								<input class="form-control" id="role" type="text" readonly/>
							</div>
							<label class="col-sm-2 control-label">所属部门</label>
							<div class="col-sm-3">
								<input class="form-control" id="departs" type="text" readonly/>
							</div>

						</div>



						<div class="form-group">

							<label class="col-sm-3 control-label" >调度帐户</label>
							<div class="col-sm-3" style="position: relative">
								<input class="form-control" id="voipAccount" type="text" readonly/>
								<svg class="icon" aria-hidden="true" style="position:absolute;top:7px;right:18px;z-index: 99999;color:red;">
									<use xlink:href="#icon-icon_bitianxiang"></use>
								</svg>
							</div>

							<label class="col-sm-2 control-label" >调度密码</label>
							<div class="col-sm-3" style="position: relative">
								<input class="form-control" id="voipPassword" type="text" readonly/>
								<svg class="icon" aria-hidden="true" style="position:absolute;top:7px;right:18px;z-index: 99999;color:red;">
									<use xlink:href="#icon-icon_bitianxiang"></use>
								</svg>
							</div>

						</div>

						<div class="form-group">

							<label class="col-sm-3 control-label">电话</label>
							<div class="col-sm-3" style="position: relative">
								<input class="form-control" id="phone" type="text" readonly/>
								<svg class="icon" aria-hidden="true" style="position:absolute;top:7px;right:18px;z-index: 99999;color:red;">
									<use xlink:href="#icon-icon_bitianxiang"></use>
								</svg>
							</div>

							<label class="col-sm-2 control-label">添加时间</label>
							<div class="col-sm-3">
								<input class="form-control" id="createTime" type="text" readonly/>
							</div>

						</div>

						<div class="form-group">

							<label class="col-sm-3 control-label">设备代码</label>
							<div class="col-sm-3">
								<input class="form-control" id="deviceId" type="text" readonly/>
							</div>

							<label class="col-sm-2 control-label">设备名称</label>
							<div class="col-sm-3">
								<input class="form-control" id="deviceName" type="text" readonly/>
							</div>

						</div>

						<div class="form-group">


							<label class="col-sm-3 control-label">最后经度</label>
							<div class="col-sm-3">
								<input class="form-control" id="longitude" type="text" readonly/>
							</div>

							<label class="col-sm-2 control-label">最后纬度</label>
							<div class="col-sm-3">
								<input class="form-control" id="latitude" type="text" readonly/>
							</div>

						</div>

						<div class="form-group">

							<label class="col-sm-3 control-label">最后一次登录时间</label>
							<div class="col-sm-3" style="position: relative">
								<input class="form-control" id="lastTime" type="text" readonly/>
								<svg class="icon" aria-hidden="true" style="position:absolute;top:7px;right:18px;z-index: 99999;color:red;">
									<use xlink:href="#icon-icon_bitianxiang"></use>
								</svg>
							</div>

							<label class="col-sm-2 control-label">最后一次登录IP</label>
							<div class="col-sm-3" style="position: relative">
								<input class="form-control" id="lastIp" type="text" readonly/>
								<svg class="icon" aria-hidden="true" style="position:absolute;top:7px;right:18px;z-index: 99999;color:red;">
									<use xlink:href="#icon-icon_bitianxiang"></use>
								</svg>
							</div>

						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label">启用状态</label>
							<div class="col-sm-3">
								<input class="form-control" id="state" type="text" readonly/>
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
	<script src="${pageContext.request.contextPath}/plug-in-xenon/assets/js/jquery-validate/jquery.validate.min.js"></script>
	<script src="${pageContext.request.contextPath}/plug-in-xenon/assets/js/jquery-validate/localization/messages_zh.min.js"></script>
	<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
	<script src="${pageContext.request.contextPath}/plug-in/tools/getUrlParam.js"></script>
	<script>


		//起始
		$(document).ready(function ()
		{


			this_data.pac_level = formatPacLevel(this_data.user_pac);

			this_data.user_manage_eyes = JSON.parse( window.sessionStorage.getItem('user_manage_eyes') );

			console.log(this_data.user_manage_eyes)

			peggingPac(this_data.user_manage_eyes.pac, function(){

				$('#account').val( this_data.user_manage_eyes.account);
				$('#name').val( this_data.user_manage_eyes.name);
				$('#voipAccount').val( this_data.user_manage_eyes.voipAccount);
				$('#voipPassword').val( this_data.user_manage_eyes.voipPassword);
				$('#phone').val( this_data.user_manage_eyes.phone);
				$('#createTime').val( this_data.user_manage_eyes.createTime);
				$('#deviceId').val( this_data.user_manage_eyes.deviceId);
				$('#deviceName').val( this_data.user_manage_eyes.deviceName);
				$('#longitude').val( this_data.user_manage_eyes.longitude);
				$('#latitude').val( this_data.user_manage_eyes.latitude);
				$('#lastTime').val( this_data.user_manage_eyes.lastTime);
				$('#lastIp').val( this_data.user_manage_eyes.lastIp);
				$('#state').val( function () {
					switch (this_data.user_manage_eyes.state){
						case 0: case '':case null: return "启用";
						case 1: return "禁用";
					}
				});

				$('#accountType').val( function(){
					switch (this_data.user_manage_eyes.accountType){
						case -1: return "超级管理员"
						case 1: return "智能终端";
						case 2: return "移动终端";
						case 3: return "系统用户";
						default: return ""
					}
				})

				$('#role').val( function(){
					var roles = this_data.user_manage_eyes.roles;
					if (!roles){
						return '';
					}
					var strs = "";
					for(var i in roles){
						if (roles.hasOwnProperty(i)){
							if (i < 1){
								strs = roles[i].roleName;
								continue;
							}
							strs += ", " + roles[i].roleName;
						}
					}
					return strs;
				} );

				$('#departs').val( function(){
					var departs = this_data.user_manage_eyes.departs;
					if (!departs){
						return '';
					}
					var strs = "";
					for(var i in departs){
						if (departs.hasOwnProperty(i)){
							if (i < 1){
								strs = departs[i].depart_name;
								continue;
							}
							strs += " — " + departs[i].depart_name;
						}
					}
					return strs;
				} )

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

						var str = '';
						for(var i in obj){
							if (obj.hasOwnProperty(i)){
								if (i< 1){
									str += obj[i].name;
									continue;
								}
								str  += " / " + obj[i].name;
							}
						}
						$('#pac').html( str );
					}
					try {
						callback()
					} catch (e) {

					}

				}
			})
		}







	</script>
</div>
</body>
</html>

