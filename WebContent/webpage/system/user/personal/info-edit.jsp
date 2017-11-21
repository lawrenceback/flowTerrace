<%--
  Created by IntelliJ IDEA.
  User: YanWei
  Date: 2017/6/30
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>用户 - 个人资料</title>
	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.4.0/css/font-awesome.css?v=4.4.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/css/style.css?v=4.1.0">
	<style>
		.por {
			position: relative;
		}

		#bar-progress {
			display: block;
			height: 5px;
			width: 0%;
			position: fixed;
			top: 0;
			left: 0;
			background-color: #0A64A4;
		}

		#bar-progress-text {
			padding-top: 5px;
		}
	</style>
</head>

<body class="gray-bg por">

<div id="bar-progress"><label id="bar-progress-text"></label></div>

<div class="wrapper wrapper-content">

	<div class="row animated fadeInRight">

		<div class="col-sm-12">

			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>修改用户资料</h5>
					<div class="ibox-tools">
						<a class="collapse-link">

						</a>
					</div>
				</div>
				<div class="ibox-content">

					<form class="form-horizontal" method="post" id="fileinfo" name="fileinfo">

						<div class="form-group">

							<div class="col-sm-10 col-sm-offset-2">
								<img src='${pageContext.request.contextPath}/${user.headPortrait }' id="headPortrait">
							</div>

						</div>

						<div class="form-group">
							<label class="col-sm-2 col-sm-2 control-label">文件选择（单选）</label>
							<div class="col-sm-4">
								<input type="file" class="upload_pic" id="upload" name="headPortrait" style="display: none;" onchange="readFile(this)">
								<div class="input-append input-group">
									<span class="input-group-btn">
										<button class="btn btn-white" type="button">选择文件</button>
									</span>
									<input class="input-large form-control" type="text" id="headPortrait_text">
								</div>
							</div>
						</div>


						<div class="form-group">
							<label class="col-sm-2 control-label">用户电话</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="phone" name="phone" value="${user.phone }" placeholder="请输入你的电话">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">用户名称</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="name" name="name" value="${user.name}"
								       placeholder="请输入你的名称">
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label"> </label>
							<div class="col-sm-4">
								<button type="button" class="btn btn-success btn-subbmit">提交</button>

								<a href="${pageContext.request.contextPath}/userController.do?getUserInfo" class="btn btn-primary">返回</a>
							</div>
						</div>

					</form>

				</div>
			</div>


		</div>
	</div>
</div>

<script src="https://cdn.staticfile.org/jquery/2.1.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
<script>

	function readFile(item) {
		var file = item.files[0];//获取上传文件列表中第一个文件
		if (!/image\/\w+/.test(file.type)) {
			//图片文件的type值为image/png或image/jpg
			alert("文件必须为图片！");
			return false;
		}
		$('#headPortrait_text').val(file.name)
		var reader = new FileReader();//实例一个文件对象
		reader.readAsDataURL(file);//把上传的文件转换成url
		reader.onload = function (e) {

			//e.target.result
			$('#headPortrait').attr('src', e.target.result)

		}
	};

	$(document).ready(function () {

		$('.dropdown.profile-element', window.parent.document).removeClass('open').find('#dropdown-toggle').attr('aria-expanded', false);
		var input1 = document.getElementById("upload");

		$('#headPortrait_text').click(function(){

			$('#upload').click();

		})





		$(".btn-subbmit").click(function () {
			var phoneval = $("#phone").val();
			var accountval = $("#account").val();
			var upload = $("#upload");

			var formData = new FormData(document.getElementById("fileinfo"));
			formData.append("f", 222001);
			formData.append("id", '${user.id}');


			$.ajax({
				url: " /if/serviceController/action",
				type: "post",
				data: formData,
				dataType: 'text',
				processData: false,
				contentType: false,
				//这里我们先拿到jQuery产生的 XMLHttpRequest对象，为其增加 progress 事件绑定，然后再返回交给ajax使用
				xhr: function () {
					var xhr = $.ajaxSettings.xhr();
					if (onprogress && xhr.upload) {
						xhr.upload.addEventListener("progress", onprogress, false);
						return xhr;
					}
				},
				success: function (data) {

					var obj = JSON.parse(data);

					if (obj.status == 10000) {

						swal({
							title: "提示",
							text: "修改成功",
							type: "success",
							showCancelButton: false,
							closeOnConfirm: false,
							confirmButtonText: "OK",
							confirmButtonColor: "#1AB394"
						}, function () {
							window.top.location.reload();
						});

					} else {
						swal("抱歉！", "请重新填写您的信息。", "error");
					}


				},
				error: function (e) {
					alert("网络错误，请重试！！");
				}
			});

		});


	})


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
