<%--
  Created by IntelliJ IDEA.
  User: YanWei
  Date: 2017/6/23
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>ICON 修改</title>


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?dds">


	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">

	<script>document.write('<script src="${pageContext.request.contextPath}/config/cg.js?' + new Date().getTime() + '"><\/script>');</script>
	<script>
		var $dom = {};
		var this_data = {};
		var param = {};
	</script>
</head>

<body>
<div class="wrapper wrapper-content">
	<div class="row">
		<div class="col-sm-12 animated fadeInRight">
			<div class="row">
				<div class="col-sm-12">


					<form method="post" class="form-horizontal" name="form1" id="form1">

						<div class="form-group">
							<label class="col-sm-2 control-label" for="iconName">图标名称</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="iconName" id="iconName" value="${icon.iconName}" placeholder="名称">
								<span class="help-block m-b-none"></span>
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label" for="iconClass">图标样式</label>

							<div class="col-sm-10">
								<input type="text" class="form-control" name="iconClass" id="iconClass" value="${icon.iconClass}" placeholder="图标样式代码">
								<span class="help-block m-b-none"></span>
							</div>
						</div>

						<div class="hr-line-dashed"></div>
						<div class="form-group">
							<div class="col-sm-4 col-sm-offset-2">
								<button class="btn btn-primary" type="submit" id="submit" style="display: none;">保存内容</button>
							</div>
						</div>
					</form>


				</div>
			</div>
		</div>
	</div>
</div>


<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/plug-in-xenon/assets/js/jquery-validate/jquery.validate.min.js"></script>
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>
<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
<script src="${pageContext.request.contextPath}/plug-in/tools/getUrlParam.js"></script>
<script>

	$(document).ready(function() {


		param.id = $.getUrlParam("id");

		$("form#form1").validate({
			rules: {
				iconName: {
					required: true
				},
				iconClass: {
					required: true
				},

			},

			messages: {
				iconName: {
					//required: '行政区号代码不得为空.'
				},
				iconClass: {
					//required: '行政区号代码不得为空.'
				}

			},

			// Form Processing via AJAX
			submitHandler: function(form)
			{
				_submit(form);

			}
		});

	});




	function _submit(form){

		var $form = $(form);

		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: {
				f: 912001,
				id: '${icon.id}',
				iconName: $form.find('input[name="iconName"]').val(),
				iconClass: $form.find('input[name="iconClass"]').val()
			},
			success: function (data) {
				data = JSON.parse(data);

				if (data.status == 10000){
					window.parent.completeCallback();
				} else {
					layer.msg(data.msg);
				}
			}
		})
	}
</script>
</body>
</html>