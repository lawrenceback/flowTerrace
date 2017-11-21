<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/3
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>用户选择部门</title>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/bootstrap.min.css?v=3.3.6">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/font-awesome.min.css?v=4.4.0">


	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?dz">


	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/jquery-easyui-1.5.2/themes/default/easyui.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/jquery-easyui-1.5.2/themes/bootstrap/tree.css">


</head>
<style>.ovelauto{
	overflow: auto;
}</style>
<body class="gray-bg">
<div class="ibox float-e-margins animated fadeInUp" style="margin-bottom: 0">

	<div class="ibox-title">
		<h5>
			选择部门
		</h5>
		<div class="ibox-tools">
			<a onclick="choiceUserDeparts()">确定</a>
			<a onclick="window.parent.choiceDepartClose()">
				<i class="fa fa-times"></i>
			</a>
		</div>
	</div>
	<div class="ibox-content" style="    height: calc(100% - 50px);  overflow: scroll; padding-bottom: 0;">


		<table id="test" class="easyui-treegrid" style="width:100%;"></table>

	</div>

</div>


<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/jquery.min.js?v=2.1.4"></script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/bootstrap.min.js?v=3.3.6"></script>
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/hplus/contabs.js"></script>
<script src="${pageContext.request.contextPath}/config/cg.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plug-in/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>

<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
<script>

	var ids = [];
	<c:forEach items="${ids}" var="id">
		ids.push("${id}");
	</c:forEach>

	$(document).ready(function () {

		loadFunction();

	});

	function loadFunction() {

		$('#test').treegrid({
			url: cg_vte.service + '?f=940001',
			idField:'id',
			treeField:'depart_name',
			checkbox: true,
			//cascadeCheck: false,
			lines: true,
			columns:[[
				{title:'名称',field:'depart_name'},
				{title:'部门编号',field:'org_code'}
			]],
			loadFilter: function(data){
				if (data.d){
					return data.d;
				} else {
					return data;
				}
			},
			onLoadSuccess: function (row, data) {
				expandAll();

				getChildren(data)
			}
		});

	}

	function expandAll() {
		var node = $('#test').treegrid('getSelected');

		if (node){
			$('#test').treegrid('expandAll', node.target);
		} else {
			$('#test').treegrid('expandAll');
		}
	}

	function getChildren(data) {

		for (var i in ids){
			if (ids.hasOwnProperty(i)){

				try{
					//在这里需要进行异常的捕捉，因为在动态获得节点时将多次执行
					//有时将可能存在不存在行
					$('#test').treegrid('checkNode', ids[i]);
				} catch (e){

				}


			}

		}

	}

	var formData = {};
	var userId= '${user.id}';
	function choiceUserDeparts() {

		var nodes = $('#test').treegrid('getCheckedNodes');

		if (nodes.length > 0){

			formData = {};

			for(var i in nodes){
				if (nodes.hasOwnProperty(i)){
					formData["departUsers["+i+"].depart.id"] = nodes[i].id;
					formData["departUsers["+i+"].user.id"] = userId;
				}
			}

			formData.id= userId;

			submitForm(formData)

		} else {
			layer.msg('请选择数据。')
		}
	}

	function submitForm(formData) {

		$.ajax({
			url: cg_vte.path + "userController.do?setUserDeparts",
			type: 'post',
			dataType: 'json',
			data: formData,
			success: function (data) {

				show_msg(data);
			},
			error: function (data) {

				console.log(data);
			}
		})
	}

	function show_msg(data) {

		switch (data.status){
			case 10000:
				swal({
					title: "提示",
					text: "成功",
					type: "success",
					showCancelButton: true,
					confirmButtonColor: "#1AB394",
					confirmButtonText: "确定",
					cancelButtonText: "取消",
					closeOnConfirm: true
				}, function () {
					window.parent.choiceDepartClose();
				});
				break;
			default:
				layer.msg(data.msg);
				break;
		}
	}

</script>
</body>
</html>
