<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  TSUser: YanWei
  Date: 2017/6/28
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>角色修改</title>


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?dds">


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

						<input type="hidden" name="f" value="931001">
						<input type="hidden" name="id" id="id" value="${role.id}">





						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label" for="roleName">角色名称</label>

							<div class="col-xs-8 col-md-4 col-md-4">
								<input type="text" class="form-control" name="roleName" id="roleName" value="${role.roleName}" placeholder="名称">
								<span class="help-block m-b-none"></span>
							</div>
						</div>

						<div class="form-group">
							<label class="col-xs-2 col-xs-offset-2 control-label" for="roleCode">角色编号</label>

							<div class="col-xs-8 col-md-4 col-md-4">
								<input type="text" class="form-control" name="roleCode" id="roleCode" value="${role.roleCode}" placeholder="编号【字母 [ 大、小写 ]，数字 】" <c:if test="${!empty role.roleCode}">disabled</c:if>>
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
<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>
<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
<script>

	$(document).ready(function() {


		$dom.roleCode = $('#roleCode');

		$("form#form1").validate({
			rules: {
				roleName: {
					required: true
				},
				roleCode: {
					required: true
				},

			},

			messages: {},

			// Form Processing via AJAX
			submitHandler: function(form)
			{
				_submit(form);

			}
		});

	});




	function _submit(form){


		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: $(form).serialize(),
			success: function (data) {
				data = JSON.parse(data);

				if (data.status == 10000){
					window.parent.completeCallback();
				} else if (data.status == 10007){
					layer.msg('角色编号已经存在，请更换。');

					$dom.roleCode.focus();

				} else {
					layer.msg(data.msg);
				}
			}
		})
	}
</script>
</body>
</html>