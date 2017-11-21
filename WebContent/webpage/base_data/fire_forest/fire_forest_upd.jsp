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
	<title>添加无线电台</title>


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?dds">

	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">

	<script>document.write('<script src="${pageContext.request.contextPath}/config/cg.js?' + new Date().getTime() + '"><\/script>');</script>
</head>

<body>
<div class="wrapper wrapper-content">
	<div class="row">
		<div class="col-sm-12 animated fadeInRight">
			<div class="row">
				<div class="col-sm-12">


					<form method="post" class="form-horizontal" name="form1" id="form1">

						<input type="hidden" name="f" id="f" value="4152001">
						<input type="hidden" id="id" name="id">


						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="pac">行政代码</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="pac" id="pac" value="" placeholder="行政代码">
							</div>
						</div>

						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="name">名称</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="name" id="name" value="" placeholder="名称">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="shape">形状</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="shape" id="shape" value="" placeholder="形状">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="phone">值班电话</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="phone" id="phone" value="" placeholder="值班电话">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="director">办公室主任</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="director" id="director" value="" placeholder="办公室主任">
							</div>
						</div>

						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="dir_phone">主任电话</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="dir_phone" id="dir_phone" value="" placeholder="主任电话">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="longitude">经度</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="longitude" id="longitude" value="" placeholder="经度">
							</div>
						</div>

						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="latitude">纬度</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="latitude" id="latitude" value="" placeholder="纬度">
							</div>
						</div>

						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="num_people">机构人数</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="num_people" id="num_people" value="" placeholder="机构人数">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="institutions">机构编制</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="institutions" id="institutions" value="" placeholder="institutions">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="type">类型</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="type" id="type" value="" placeholder="类型">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="status">状态</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="status" id="status" value="" placeholder="状态">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="level">级别</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="level" id="level" value="" placeholder="级别">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="note">说明</label>

							<div class="col-sm-10">
								<textarea class="form-control" name="note" id="note" placeholder="note" rows="3"></textarea>
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="commander">指挥长</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="commander" id="commander" value="" placeholder="指挥长">
							</div>
						</div>
						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="commander_phone">指挥长电话</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="commander_phone" id="commander_phone" value="" placeholder="指挥长电话">
							</div>
						</div>

						<div class="hr-line-dashed"></div>

						<div id="imgs_box">

						</div>

						<button class="btn btn-primary" type="submit" id="submit" style="display: none;">保存内容</button>
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
	$(document).ready(function() {


		$dom.imgs_box = $('#imgs_box');

		setFormValue(validation_());

	});

	function validation_(){

		$("form#form1").validate({
			rules: {
				pac: {
					required: true
				},
				name: {
					required: true
				},


			},
			messages: {
				pac: {
					required: '行政代码不得为空'
				},
				name: {
					required: 'name不得为空'
				},


			},

			// Form Processing via AJAX
			submitHandler: function(form)
			{
				_submit(form);

			}
		});


	}
	function setFormValue(callback) {

		var remotemonitoring = window.sessionStorage.getItem('data_tr');
		remotemonitoring = JSON.parse(remotemonitoring);

		console.log(remotemonitoring)


		$('#id').val(remotemonitoring.id);
		$('#name').val(remotemonitoring.name);
		$('#pac').val(remotemonitoring.pac);
		$('#shape').val(remotemonitoring.shape);
		$('#phone').val(remotemonitoring.phone);
		$('#director').val(remotemonitoring.director);
		$('#dir_phone').val(remotemonitoring.dir_phone);
		$('#longitude').val(remotemonitoring.longitude);
		$('#latitude').val(remotemonitoring.latitude);
		$('#num_people').val(remotemonitoring.num_people);
		$('#institutions').val(remotemonitoring.institutions);
		$('#type').val(remotemonitoring.type);
		$('#status').val(remotemonitoring.status);
		$('#level').val(remotemonitoring.level);
		$('#note').val(remotemonitoring.note);
		$('#commander').val(remotemonitoring.commander);
		$('#commander_phone').val(remotemonitoring.commander_phone);


		var mediaFiles = remotemonitoring.mediaFiles;
		for(var i in mediaFiles){
			if (mediaFiles.hasOwnProperty(i)){
				switch (mediaFiles[i].fileType){
					case 1:
						$dom.imgs_box.append('<img src="${pageContext.request.contextPath}/'+mediaFiles[i].fileUrl+'">')
						break;
					case 2:
						$dom.imgs_box.append('<video width="320" height="240" controls><source src="${pageContext.request.contextPath}/'+mediaFiles[i].fileUrl+'" type="video/'+mediaFiles[i].fileSuffix+'">您的浏览器不支持Video标签。</video>');
						break;
					case 3:
						$dom.imgs_box.append('<audio width="320" height="240" controls><source src="${pageContext.request.contextPath}/'+mediaFiles[i].fileUrl+'" type="audio/'+mediaFiles[i].fileSuffix+'">您的浏览器不支持Video标签。</audio>');
						break;
				}

			}
		}

		try{
			callback()
		} catch (e){

		}
	}
	function _submit(form){

		var number = $('#number').val();

		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: $(form).serialize(),
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
						window.parent.completeCallback();
					});
				} else {
					layer.msg(data.msg);
				}
			}
		})
	}



</script>
</body>
</html>