<%--
  Created by IntelliJ IDEA.
  User: YanWei
  Date: 2017/7/10
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>添加 行政区</title>


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?dds">

	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">

	<style>
		#city,#county{
			width: 45%;
			margin-right: 5px;
		}

		/******************************************* 上传进度条 Start ********************************************/
		#bar-progress {
			display: block;
			height: 5px;
			width: 0%;
			position: fixed;
			top: 0;
			left: 0;
			background-color: #1AB394;
			z-index: 99999;
		}

		#bar-progress-text {padding-top: 5px;}
		/******************************************* 上传进度条 End  *********************************************/

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

	<div id="bar-progress"><label id="bar-progress-text"></label></div>

	<div class="wrapper wrapper-content">
		<div class="row">
			<div class="col-sm-12 animated fadeInUp">
				<form method="post" class="form-horizontal" name="form1" id="form1">

					<div class="form-group">
						<label class="col-xs-2 col-xs-offset-2 control-label" for="name">行政区名称</label>

						<div class="col-xs-8 col-md-4 col-md-4">
							<input type="text" class="form-control" name="name" id="name" placeholder="如：广西壮族自治区">
						</div>
					</div>
					<div class="hr-line-dashed"></div>


					<div class="col-sm-4 col-md-offset-4 col-lg-offset-4 col-xs-offset-4">
						<button class="btn btn-primary" type="submit">提交内容</button>
					</div>


				</form>

			</div>
		</div>
	</div>


<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/plug-in-xenon/assets/js/jquery-validate/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>

<script>

	$(document).ready(function() {


		this_data.pac = window.sessionStorage.getItem( 'pac' );


		validate_form();
	});
	

	function validate_form() {
		$("form#form1").validate({
			rules: {
				name: {
					required: true
				},

			},

			messages: {
				name: {
					required: '行政区名称不得为空.'
				},

			},

			// Form Processing via AJAX
			submitHandler: function(form)
			{
				_submit(form);

			}
		});
	}


	function _submit(form){

		var $form = $(form);

		var formData = new FormData();

		formData.append("f", 211001 );
		formData.append("code", this_data.pac );
		formData.append("name", $form.find('input[name="name"]').val() );

		$.ajax({
			url: cg_vte.service,
			type: 'post',
			processData: false,  // 不处理数据
			contentType: false,   // 不设置内容类型
			xhr: function () {
				//这里我们先拿到jQuery产生的 XMLHttpRequest对象，为其增加 progress 事件绑定，然后再返回交给ajax使用
				var xhr = $.ajaxSettings.xhr();
				if (onprogress && xhr.upload) {
					xhr.upload.addEventListener("progress", onprogress, false);
					return xhr;
				}
			},
			dataType: 'text',
			data: formData,
			success: function (data) {
				data = JSON.parse(data);
				console.log(data);
				if (data.status == 10000){
					swal({
						title: "提示",
						text: "success",
						type: "success",
						showCancelButton: false,
						closeOnConfirm: false,
						confirmButtonText: "OK",
						confirmButtonColor: "#1AB394"
					}, function() {
						window.parent.location.reload();
					});
				} else {
					layer.msg(data.msg);
				}
			}
		})
	}

	/**
	 *    侦查附件上传情况    ,这个方法大概0.05-0.1秒执行一次
	 */
	function onprogress(evt) {
		var loaded = evt.loaded;                  //已经上传大小情况
		var tot = evt.total;                      //附件总大小
		var per = Math.floor(100 * loaded / tot);     //已经上传的百分比

		$("#bar-progress").css("width", per + "%");
		$('#bar-progress-text').text(per + "%")
		console.log(per)
		if (per == 100) {

			console.log("上传成功")
			$("#bar-progress").css("width", '0');
			$('#bar-progress-text').text('')
		}

	}

</script>
</body>
</html>