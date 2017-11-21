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
	<title>添加热点</title>


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?dds">

	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">

	<script>document.write('<script src="${pageContext.request.contextPath}/config/cg.js?' + new Date().getTime() + '"><\/script>');</script>
	<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
</head>

<body>

	<div id="bar-progress"><label id="bar-progress-text"></label></div>

	<div class="wrapper wrapper-content">
		<div class="row">
			<div class="col-sm-12 animated fadeInUp">
				<div class="row">
					<div class="col-sm-12">


						<form method="post" class="form-horizontal" name="form1" id="form1">

							<div class="form-group">
								<label class="col-xs-4 control-label" for="fireType">火灾类型</label>

								<div class="col-xs-6">
									<select class="form-control" name="fireType" id="fireType">
										<option value="1">火灾</option>
										<option value="2">违规炼山</option>
										<option value="3">农用火</option>
										<option value="4">计划烧除</option>
										<option value="5">其他</option>
										<option value="6">境外火</option>
									</select>
								</div>
							</div>
							<div class="hr-line-dashed"></div>

							<div class="form-group">
								<label class="col-xs-4 control-label" for="fireFighting">fireFighting</label>

								<div class="col-xs-6">
									<select class="form-control" name="fireFighting" id="fireFighting">
										<option value="1">没有人</option>
										<option value="2">少许人</option>
										<option value="3">很多人</option>
									</select>
								</div>
							</div>
							<div class="hr-line-dashed"></div>

							<div class="form-group">
								<label class="col-xs-4 control-label" for="area">面积</label>

								<div class="col-xs-6">
									<input type="number" name="area" id="area" class="form-control" placeholder="area">
								</div>
							</div>
							<div class="hr-line-dashed"></div>

							<div class="form-group">
								<label class="col-xs-4 control-label" for="description">description</label>

								<div class="col-xs-6">
									<textarea class="form-control" name="description" id="description" placeholder="description" rows="4"></textarea>
								</div>
							</div>
							<div class="hr-line-dashed"></div>

							<div class="form-group">
								<label class="col-xs-4 control-label">媒体文件</label>
								<div class="col-xs-6">
									<style>	#mediaFile{opacity: 0;position: absolute;cursor: pointer}#medit_file_input{overflow: hidden; white-space: nowrap; text-overflow: ellipsis;}</style>
									<input type="file" class="form-control" id="mediaFile" accept="audio/*,video/*,image/*,*.mkv" onchange="readyFile(this)" multiple>
									<div class="input-append input-group" style="z-index: -1;">
								<span class="input-group-btn">
									<button class="btn btn-white" type="button">选择文件</button>
								</span>
										<input class="input-large form-control" type="text" id="medit_file_input">
									</div>
									<span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 支持的类型有: <code>image/*,video/*,audio/*</code></span>
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
		</div>
	</div>


<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/plug-in-xenon/assets/js/jquery-validate/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>
<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
<script>


	var $dom = {};


	function readyFile(files) {
		files = files.files;
		$('#medit_file_input').val('');
		var fileNames = '';
		for (var i in files){
			if (files.hasOwnProperty(i)){
				if (i == 0){
					fileNames =  files[i].name;
					continue;
				}
				fileNames += "," + files[i].name;
			}
		}
		$('#medit_file_input').val(fileNames)
	}


	$(document).ready(function() {

		validate_form();

	});

	function validate_form() {

		$("form#form1").validate({
			rules: {
				pac: {
					required: true
				},
			},

			messages: {
				pac: {
					required: '行政区号代码不得为空.'
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

		var data_tr = window.sessionStorage.getItem('data_tr');
		data_tr = JSON.parse(data_tr);

		var formData = new FormData();

		formData.append("f", 4111003);
		formData.append("hot_id", data_tr.id);
		formData.append("state", $form.find('select[name="state"]').val());
		formData.append("initiator", '${LOCAL_CLINET_USER.id}');
		formData.append("fireType", $form.find('select[name="fireType"]').val());
		formData.append("fireFighting", $form.find('select[name="fireFighting"]').val());
		formData.append("description", $form.find('textarea[name="description"]').val());
		formData.append("area", $form.find('input[name="area"]').val());


		//媒体文件
		var files = document.getElementById('mediaFile');
		files = files.files;
		for(var i in files){
			if (files.hasOwnProperty(i)){
				formData.append("file" + i, files[i] );
			}
		}

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
						window.parent.addOrUpdCallback();
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