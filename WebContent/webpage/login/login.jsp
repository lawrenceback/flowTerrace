<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="Zh-cn">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>登录</title>
	<script>
		//当该页面不是最顶层窗口时，刷新父窗口
		if (window !== window.top){
			window.top.location.reload();
		}
	</script>
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/logo_ico.ico" >
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-xenon/assets/css/fonts/linecons/css/linecons.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-xenon/assets/css/fonts/fontawesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-xenon/assets/css/bootstrap.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-xenon/assets/css/xenon-core.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-xenon/assets/css/xenon-forms.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-xenon/assets/css/custom.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/izi-toast/css/iziToast.min.css">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/beauty-input/css/input-style.css">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/webpage/login/css/component.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/webpage/login/css/demo.css">

	<script src="${pageContext.request.contextPath}/plug-in-xenon/assets/js/jquery-1.11.1.min.js"></script>
	<script src="https://cdn.bootcss.com/snap.svg/0.5.1/snap.svg-min.js"></script>

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->


	<script src="${pageContext.request.contextPath}/config/cg.js"></script>
	<style>
		html, body{
			height: auto;
		}
		.login-container {
			max-width: 960px;
			margin: 0 auto;
		}

		.login-page .errors-container {
			margin-bottom: 20px;
			padding: 0 30px;
		}
		.login-page.login-light .errors-container, .login-page.login-light .external-login {
			padding: 0;
		}
		.login-page .login-form {
			position: relative;
			padding: 30px;
			margin-bottom: 20px;
		}
		.login-page.login-light .login-form {
			background: #fff;
			color: #979898;
			position: relative;
			padding-top: 95px;
		}
		.login-page .login-form .login-header {
			margin-bottom: 40px;
			background: #1ab394;
			padding: 5px 0;
			text-align: center;
			font-family: "微软雅黑";
			position: absolute;
			width: 100%;
			top: 0px;
			color: white;
			left: 0;
		}
		#bar-progress{
			display: block;
			height: 5px;
			width: 0%;
			position: fixed;
			top: 0;
			left: 0;
			background-color: #0A64A4;
		}
		#bar-progress-text{
			padding-top: 5px;
		}

		.login-page.login-light .login-form {
			background: #fff;
			color: #979898;

			position: relative;
			padding-top: 95px;
			border-radius: 15px;
			box-shadow: 0px 8px 16px rgba(7, 17, 27, 0.1);
		}
		.login-header{
			border-top-left-radius: 15px;
			border-top-right-radius: 15px;
		}

		/* 中等屏幕（桌面显示器，大于等于 992px） */
		@media (min-width: 992px) {
			.login-page{
				padding-top: 92px;
			}
			.form-group{
				margin-bottom: 20px;
			}
			.login-page.login-light {
				background: #eee url(/plug-in-xenon/assets/images/bg.jpg) no-repeat top center;
			}
		}

		/* 大屏幕（大桌面显示器，大于等于 1200px） */
		@media (min-width:1400px) {
			.login-page.login-light {
				background: #eee url(images/bg2.png) no-repeat top center;
			}

		.login-page {
			background: #2c2e2f;
			color: #fff;
			padding-top: 13.6%;
		}
		.login-page .login-form .form-group {
			position: relative;
			margin-bottom: 20px;
			margin-top: 30px;
		}
			.login-header h3 {
				height: 55px;
				line-height: 55px;
				font-size: 32px;
				font-family: Microsoft YaHei;
			}
		}

		input[type="password"] {
			font: 15px/24px "Lato", Arial, sans-serif;
			color: #333;
			width: 100%;
			box-sizing: border-box;
			letter-spacing: 1px;
		}
		input[type="text"]{
			border-radius: 3px;
		}
		.effect-8 ~ .focus-border:before, .effect-8 ~ .focus-border:after, .effect-8 ~ .focus-border i:before, .effect-8 ~ .focus-border i:after{
			background-color: #31c5ad;
		}
		.sub-btn{
			background: #1ab394;
			color: white;
			text-align: center;
			border-radius: 5px;
			line-height: 25px;
		}
		label.error{
			position: absolute;
			top: 47px;
			width: 100%;
			left: 0px;
			font-size: 14px;
			color:red;
		}
	</style>
</head>
<body class="page-body login-page login-light">

<div id="bar-progress">
	<label id="bar-progress-text"></label>
</div>

<!--[if IE 8 ]>
<html class="pc-ie8">
<style type="text/css">
	.col-sm-6-nofl{
		width:496px;
		margin:0 auto;
		float:none;
	}
</style>
<![endif]-->
<div class="login-container">

	<div class="row">

		<div class="col-sm-6 col-sm-6-nofl">


			<!-- Errors container -->
			<div class="errors-container"></div>

			<!-- Add class "fade-in-effect" for login form effect -->
			<form method="post" role="form" id="login" class="login-form">

				<div class="login-header">
					<h3>流量刷量平台后台综合管理系统</h3>
				</div>

				<div class="form-group" style="position: relative">
					<div class="input-effect">
						<input type="text" class="effect-8" name="account" id="account" placeholder="帐号">
						<span class="focus-border">
			                <i></i>
			            </span>
					</div>
				</div>
				<div class="form-group" style="position: relative">
					<div class="input-effect">
						<input type="password" class="effect-8" name="passwd" id="passwd" placeholder="密码">
						<span class="focus-border">
			                <i></i>
			            </span>
					</div>
				</div>

				<div class="form-group tac">
					<button type="submit" class="btn btn-block   sub-btn">
						<i class="fa-lock"></i>
						登陆
					</button>
				</div>

			<!--

				<div class="login-footer">
					<a href="#">忘记密码?</a>
				</div>
            -->
			</form>

			<!-- External login -->


		</div>

	</div>

</div>

<div class="footer tac">

	<p>  版权所有 江西华宇软件股份有限公司  Copyright © 2017 XXXXXXX</p>
</div>


<div id="loader" class="pageload-overlay" data-opening="M 40 -21.875 C 11.356078 -21.875 -11.875 1.3560784 -11.875 30 C -11.875 58.643922 11.356078 81.875 40 81.875 C 68.643922 81.875 91.875 58.643922 91.875 30 C 91.875 1.3560784 68.643922 -21.875 40 -21.875 Z">
	<svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" viewBox="0 0 80 60" preserveAspectRatio="xMidYMid slice">
		<path d="M40,30 c 0,0 0,0 0,0 0,0 0,0 0,0 0,0 0,0 0,0 0,0 0,0 0,0 Z"/>
	</svg>
</div><!-- /pageload-overlay -->

<!-- Bottom Scripts -->
<script src="${pageContext.request.contextPath}/plug-in-xenon/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in-xenon/assets/js/resizeable.js"></script>
<script src="${pageContext.request.contextPath}/plug-in-xenon/assets/js/jquery-validate/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in-xenon/assets/js/toastr/toastr.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/izi-toast/js/iziToast.min.js"></script>


<!-- JavaScripts initializations and stuff -->
<script src="${pageContext.request.contextPath}/plug-in-xenon/assets/js/xenon-custom.js"></script>
<script src="${pageContext.request.contextPath}/webpage/login/js/classie.js"></script>
<script src="${pageContext.request.contextPath}/webpage/login/js/svgLoader.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($)
	{
		$(".col-3 input").val("");


		$(".input-effect input").focusout(function(){
			if($(this).val() != ""){
				$(this).addClass("has-content");
			}else{
				$(this).removeClass("has-content");
			}
		})



		var loader = new SVGLoader( document.getElementById( 'loader' ), { speedIn : 300, easingIn : mina.backin() } );


		// Validation and Ajax action
		$("form#login").validate({
			rules: {
                account: {
					required: true
				},

				passwd: {
					required: true
				}
			},

			messages: {
                account: {
					required: '请输入你的帐号.'
				},

				passwd: {
					required: '请输入密码.'
				}
			},

			// Form Processing via AJAX
			submitHandler: function(form)
			{

				iziToast.warning({
					title: '提示',
					message: '登陆中...'
				});



				$.ajax({
					url: cg_vte.login,
					method: 'post',
					dataType: 'text',
					data: {
                        account: $(form).find('#account').val(),
						password: $(form).find('#passwd').val(),
						deviceType: 4,
					},
					xhr: function(){
						var xhr = $.ajaxSettings.xhr();
						if(onprogress && xhr.upload) {
							xhr.upload.addEventListener("progress" , onprogress, false);
							return xhr;
						}
					},
					success: function(resp)
					{

						iziToast.destroy();

						resp = JSON.parse(resp);

						switch (resp.status){
							case 10000:
								loader.show();
								location.href = cg_vte.service_href + cg_vte.pagePath.index;
								break;
							default:

								setTimeout(function(){
									loader.hide();
								},300);

								iziToast.error({
									title: '警告',
									message: resp.msg
								});
								break;
						}

					}
				});

			}
		});

		// Set Form focus
		$("form#login .form-group:has(.form-control):first .form-control").focus();
	});



	/**
	 *    侦查附件上传情况    ,这个方法大概0.05-0.1秒执行一次
	 */
	function onprogress(evt){
		var loaded = evt.loaded;                  //已经上传大小情况
		var tot = evt.total;                      //附件总大小
		var per = Math.floor(100*loaded/tot);     //已经上传的百分比

		$("#bar-progress").css("width" , per +"%");
		$('#bar-progress-text').text(per + "%")

		if(per == 100) {

			$("#bar-progress").css("width", '0');
			$('#bar-progress-text').text('')
		}

	}


</script>

</body>
</html>