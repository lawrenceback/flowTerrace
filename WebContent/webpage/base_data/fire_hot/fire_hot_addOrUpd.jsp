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
	<title>添加远程监控</title>


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
			<div class="col-sm-12 animated fadeInUp">
				<div class="row">
					<div class="col-sm-12">


						<form method="post" class="form-horizontal" name="form1" id="form1">

							<input type="hidden" name="f" id="f" value="4111001">

							<input type="hidden" id="id" name="id" value="${fireHot.id}" placeholder="id">

							<div class="form-group">
								<label class="col-xs-2 control-label" for="no">no</label>

								<div class="col-xs-10">
									<input type="text" class="form-control" name="no" id="no" value="${fireHot.no}" placeholder="no" disabled>
								</div>
							</div>

							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-xs-2 control-label" for="satellite">satellite</label>

								<div class="col-xs-10">
									<input type="text" class="form-control" name="satellite" id="satellite" value="${fireHot.satellite}" placeholder="satellite">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-xs-2 control-label" for="pac">pac</label>

								<div class="col-xs-10">
									<input type="text" class="form-control" name="pac" id="pac" value="${fireHot.pac}" placeholder="pac">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-xs-2 control-label" for="county">county</label>

								<div class="col-xs-10">
									<input type="text" class="form-control" name="county" id="county" value="${fireHot.county}" placeholder="county">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-xs-2 control-label" for="note">note</label>

								<div class="col-xs-10">
									<input type="text" class="form-control" name="note" id="note" value="${fireHot.note}" placeholder="note">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-xs-2 control-label" for="reporter">reporter</label>

								<div class="col-xs-10">
									<input type="text" class="form-control" name="reporter" id="reporter" value="${fireHot.reporter}" placeholder="reporter">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-xs-2 control-label" for="opinion">opinion</label>

								<div class="col-xs-10">
									<input type="text" class="form-control" name="opinion" id="opinion" value="${fireHot.opinion}" placeholder="opinion">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-xs-2 control-label" for="duty">duty</label>

								<div class="col-xs-10">
									<input type="text" class="form-control" name="duty" id="duty" value="${fireHot.duty}" placeholder="duty">
								</div>
							</div>

							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-xs-2 control-label" for="longitude">longitude</label>

								<div class="col-xs-10">
									<input type="text" class="form-control" name="longitude" id="longitude" value="${fireHot.longitude}" placeholder="longitude">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-xs-2 control-label" for="latitude">latitude</label>

								<div class="col-xs-10">
									<input type="text" class="form-control" name="latitude" id="latitude" value="${fireHot.latitude}" placeholder="latitude">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-xs-2 control-label" for="type">type</label>

								<div class="col-xs-10">
									<input type="text" class="form-control" name="type" id="type" value="${fireHot.type}" placeholder="type">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-xs-2 control-label" for="pixels">pixels</label>

								<div class="col-xs-10">
									<input type="text" class="form-control" name="pixels" id="pixels" value="${fireHot.pixels}" placeholder="pixels">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-xs-2 control-label" for="smoke">smoke</label>

								<div class="col-xs-10">
									<input type="text" class="form-control" name="smoke" id="smoke" value="${fireHot.smoke}" placeholder="smoke">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<label class="col-xs-2 control-label" for="continuous">continuous</label>

								<div class="col-xs-10">
									<input type="text" class="form-control" name="continuous" id="continuous" value="${fireHot.continuous}" placeholder="continuous">
								</div>
							</div>

							<div class="hr-line-dashed"></div>

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

	$(document).ready(function() {

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

	});


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
						window.parent.addOrUpdHotCallback();
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