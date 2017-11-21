<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: YanWei
  Date: 2017/7/3
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>添加部门</title>


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?dds">


	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">

	<style>

		.navbar-top-links li a {
			padding: 15px 10px;
		}

		#page-wrapper .datagrid-btable tr{height: 40px;}
	</style>
	<script>document.write('<script src="${pageContext.request.contextPath}/config/cg.js?' + new Date().getTime() + '"><\/script>');</script>
	<script>
		var $dom = {};
	</script>
</head>

<body>

<div class="wrapper wrapper-content animated fadeInRight">


	<div class="row">

		<form method="post" class="form-horizontal" action="" name="user" id="user">

			<input type="hidden" name="f" value="941001">
			<input type="hidden" name="id" id="id" value="${depart.id}">
			<div class="form-group">
				<label class="col-sm-2 control-label" for="departName">名称</label>

				<div class="col-sm-8">
					<input type="text" class="form-control" name="departName" id="departName" value="${depart.departName}" placeholder="部门名称">
				</div>
			</div>
			<div class="hr-line-dashed"></div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="description">描述</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" name="description" id="description" value="${depart.description }" placeholder="部门描述">
					<span class="help-block m-b-none"></span>
				</div>
			</div>
			<div class="hr-line-dashed"></div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="orgCode">父部门</label>

				<div class="col-sm-8">
					<input type="hidden" name="orgCode" id="orgCode" value="${depart.orgCode }">
					<input type="text" class="form-control" id="parentName" placeholder="选择父菜单" onclick="choiceParentDepart('${depart.orgCode }',this)">
				</div>
				<div class="col-sm-2">
					<a onclick="deleteDepartParent()">移除</a>
				</div>
			</div>

			<input type="submit" id="submit" value="submit" style="display: none">
		</form>
	</div>


	<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
	<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
	<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>
	<script src="${pageContext.request.contextPath}/plug-in-xenon/assets/js/jquery-validate/jquery.validate.min.js"></script>
	<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
	<script>


		$(document).ready(function ()
		{

			$dom.parentFunctionId = $('#parentFunctionId');
			$dom.iconId = $('#iconId');

			//表单校验
			validateForm();

			<c:if test="${empty depart.orgCode}">
				getOrgCode('');
			</c:if>
			<c:if test="${!empty depart.orgCode}">
				getParentDepart('${depart.orgCode}');
			</c:if>


		});


		//表单校验
		function validateForm() {

			// Validation and Ajax action
			$("form#user").validate({
				rules: {
					departName: {
						required: true
					},

					orgCode: {
						required: true
					}
				},

				messages: {
                    departName: {
                        required: '名称不能为空，'
					}
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

			var $form = $(form);

			$.ajax({
				url: cg_vte.service,
				type: 'post',
				dataType: 'text',
				data: $form.serialize(),
				success: function (data) {

					data = JSON.parse(data);

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
						text: "操作成功",
						type: "success",
						showCancelButton: false,
						closeOnConfirm: false,
						confirmButtonText: "OK",
						confirmButtonColor: "#1AB394"
					}, function() {
						window.parent.completeCallback();
					});
					break;
				default:
					layer.msg(res.msg);
					break;
			}

		}



		function getOrgCode(orgCode) {
			$.ajax({
				url: cg_vte.service,
				type: 'post',
				dataType: 'text',
				data: {
					f: 940002,
					orgCode: orgCode
				},
				success: function (data) {

					data = JSON.parse(data);

					$('#orgCode').val(data.obj)
				}
			})
		}
		
		function getParentDepart(orgCode) {
			$.ajax({
				url: cg_vte.service,
				type: 'post',
				dataType: 'text',
				data: {
					f: 940003,
					orgCode: orgCode
				},
				success: function (data) {

					data = JSON.parse(data);

					if (data.status == 10000){
						$('#parentName').val(data.obj.depart_name);
					}

				}
			})
		}

		//移除父部门
		function deleteDepartParent() {
			getOrgCode('');
			$('#parentName').val('');
		}


		//================================== 点击选择父菜单 Start ==============================//
		var parentDepartWarp={};
		function choiceParentDepart(id,dom) {
			parentDepartWarp.dom = dom;
			parentDepartWarp.layer = layer.open({
				type: 2,
				title: '',
				closeBtn: 0,

				area: ['60%', '80%'],
				content: cg_vte.path + 'departController.do?goHref&href=system/depart/depart-choice-parentDepart&id=' + id,
				fix: false,
				scrollbar: false,
			});
		}
		//点击选择父菜单回调事件
		function choiceParentDepartCallback(id,org_code_parent,depart_name) {
			parentDepartWarp.dom.value = depart_name;
			getOrgCode(org_code_parent);
			layer.close(parentDepartWarp.layer);
		}
		function choiceParentDepartClose() {
			layer.close(parentDepartWarp.layer);
		}
		//================================== 点击选择父菜单 End ================================//
	</script>
</div>
</body>
</html>
